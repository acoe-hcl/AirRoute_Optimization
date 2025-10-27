import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class OrangeHRMEmployeeManagementTest {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://orangehrm-demo-6x.orangehrmlive.com/";

    // Test Data
    String adminUsername = "admin_user";
    String adminPassword = "admin_pass";
    String empFirstName = "John";
    String empLastName = "Doe";
    String empID = "12345";
    String empUsername = "johndoe";
    String empPassword = "Emp@1234";
    String invalidUsername = "invalid_user";
    String invalidPassword = "invalid_pass";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void login(String username, String password) {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername"))).clear();
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
    }

    @Test(priority = 1)
    public void testValidAdminLogin() {
        login(adminUsername, adminPassword);
        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Dashboard']")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header is not displayed - Login failed.");
    }

    @Test(priority = 2, dependsOnMethods = {"testValidAdminLogin"})
    public void testAddNewEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_addEmployee"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys(empFirstName);
        driver.findElement(By.id("lastName")).sendKeys(empLastName);
        WebElement empIdField = driver.findElement(By.id("employeeId"));
        empIdField.clear();
        empIdField.sendKeys(empID);

        WebElement createLoginDetailsCheckbox = driver.findElement(By.id("chkLogin"));
        if (!createLoginDetailsCheckbox.isSelected())
            createLoginDetailsCheckbox.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_name"))).sendKeys(empUsername);
        driver.findElement(By.id("user_password")).sendKeys(empPassword);
        driver.findElement(By.id("re_password")).sendKeys(empPassword);

        driver.findElement(By.id("btnSave")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("personal_txtEmpFirstName")));
        String personalDetailsFirstName = driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
        String personalDetailsLastName = driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value");
        Assert.assertEquals(personalDetailsFirstName, empFirstName, "First name not matching after employee creation.");
        Assert.assertEquals(personalDetailsLastName, empLastName, "Last name not matching after employee creation.");
    }

    @Test(priority = 3, dependsOnMethods = {"testAddNewEmployee"})
    public void testVerifyEmployeeCreation() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewEmployeeList"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("empsearch_id"))).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empID);
        driver.findElement(By.id("searchBtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='resultTable']/tbody/tr")));

        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
        boolean found = false;
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() > 1 &&
                columns.get(1).getText().equals(empFirstName + " " + empLastName) &&
                columns.get(2).getText().equals(empID)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Employee 'John Doe' with ID '12345' not found in Employee List.");
    }

    @Test(priority = 4, dependsOnMethods = {"testVerifyEmployeeCreation"})
    public void testDeleteEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewEmployeeList"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("empsearch_id"))).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empID);
        driver.findElement(By.id("searchBtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='resultTable']/tbody/tr")));

        WebElement deleteButton = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td/a[contains(@href,'empNumber')]/following-sibling::a[contains(@class,'delete')]"));
        deleteButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dialogDeleteBtn"))).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//table[@id='resultTable']/tbody/tr/td[text()='" + empID + "']")));
    }

    @Test(priority = 5, dependsOnMethods = {"testDeleteEmployee"})
    public void testVerifyEmployeeDeletion() {
        logout();
        login(adminUsername, adminPassword);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewEmployeeList"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("empsearch_id"))).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empID);
        driver.findElement(By.id("searchBtn")).click();

        WebElement noRecordsMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='resultTable']/tbody/tr/td")));
        Assert.assertTrue(noRecordsMsg.getText().contains("No Records Found") || noRecordsMsg.getText().equals("No Records Found"),
                "Deleted employee still appears in Employee List.");
    }

    @Test(priority = 6)
    public void testInvalidLoginAttempt() {
        logout();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername"))).clear();
        driver.findElement(By.id("txtUsername")).sendKeys(invalidUsername);
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys(invalidPassword);
        driver.findElement(By.id("btnLogin")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("spanMessage")));
        Assert.assertTrue(errorMsg.getText().contains("Invalid credentials"),
                "Error message not displayed for invalid login attempt.");
    }
}
