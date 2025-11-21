import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHRMTest {
    WebDriver driver;
    String baseUrl = "https://orangehrm-demo-url.com"; // Update as per actual OrangeHRM URL
    String adminUsername = "admin_user";
    String adminPassword = "admin_pass";
    String empFirstName = "John";
    String empLastName = "Doe";
    String empId = "12345";
    String empUsername = "johndoe";
    String empPassword = "Emp@1234";
    String invalidUsername = "invalid_user";
    String invalidPassword = "invalid_pass";

    @BeforeClass
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testValidAdminLogin() {
        driver.get(baseUrl);
        driver.findElement(By.id("txtUsername")).sendKeys(adminUsername);
        driver.findElement(By.id("txtPassword")).sendKeys(adminPassword);
        driver.findElement(By.id("btnLogin")).click();
        WebElement dashboardHeader = driver.findElement(By.xpath("//h1[text()='Dashboard']"));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header not visible - login may have failed");
    }

    @Test(priority = 2, dependsOnMethods = "testValidAdminLogin")
    public void testAddNewEmployee() {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.id("btnAdd")).click();
        driver.findElement(By.id("firstName")).sendKeys(empFirstName);
        driver.findElement(By.id("lastName")).sendKeys(empLastName);

        WebElement empIdField = driver.findElement(By.id("employeeId"));
        empIdField.clear();
        empIdField.sendKeys(empId);

        WebElement createLoginDetailsCheckbox = driver.findElement(By.id("chkLogin"));
        if (!createLoginDetailsCheckbox.isSelected()) {
            createLoginDetailsCheckbox.click();
        }
        driver.findElement(By.id("user_name")).sendKeys(empUsername);
        driver.findElement(By.id("user_password")).sendKeys(empPassword);
        driver.findElement(By.id("re_password")).sendKeys(empPassword);

        driver.findElement(By.id("btnSave")).click();

        WebElement personalDetailsHeader = driver.findElement(By.xpath("//h1[text()='Personal Details']"));
        Assert.assertTrue(personalDetailsHeader.isDisplayed(), "Personal Details page not shown after employee addition");
    }

    @Test(priority = 3, dependsOnMethods = "testAddNewEmployee")
    public void testVerifyEmployeeCreation() {
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();

        WebElement empTable = driver.findElement(By.id("resultTable"));
        WebElement empRow = empTable.findElement(By.xpath("//tr[td[text()='" + empId + "']]"));
        String empName = empRow.findElement(By.xpath("td[3]/a")).getText();

        Assert.assertTrue(empName.contains(empFirstName) && empName.contains(empLastName),
                "Employee creation verification failed. Name: " + empName);
    }

    @Test(priority = 4, dependsOnMethods = "testVerifyEmployeeCreation")
    public void testDeleteEmployee() {
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();

        WebElement checkbox = driver.findElement(By.xpath("//td/a[text()='" + empId + "']/../../td/input[@type='checkbox']"));
        checkbox.click();

        driver.findElement(By.id("btnDelete")).click();
        driver.findElement(By.id("dialogDeleteBtn")).click();

        Assert.assertTrue(driver.findElement(By.className("message")).getText().contains("Successfully Deleted"),
                "Employee was not deleted successfully");
    }

    @Test(priority = 5, dependsOnMethods = "testDeleteEmployee")
    public void testVerifyEmployeeDeletion() {
        driver.findElement(By.id("welcome")).click();
        driver.findElement(By.linkText("Logout")).click();

        driver.findElement(By.id("txtUsername")).sendKeys(adminUsername);
        driver.findElement(By.id("txtPassword")).sendKeys(adminPassword);
        driver.findElement(By.id("btnLogin")).click();

        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();

        WebElement noRecordsMsg = driver.findElement(By.xpath("//td[text()='No Records Found']"));
        Assert.assertTrue(noRecordsMsg.isDisplayed(), "Employee may still exist after deletion");
    }

    @Test(priority = 6)
    public void testInvalidLoginAttempt() {
        driver.findElement(By.id("welcome")).click();
        driver.findElement(By.linkText("Logout")).click();

        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtUsername")).sendKeys(invalidUsername);
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys(invalidPassword);
        driver.findElement(By.id("btnLogin")).click();

        WebElement errorMsg = driver.findElement(By.id("spanMessage"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed for invalid login");
        Assert.assertEquals(errorMsg.getText().trim(), "Invalid credentials", "Incorrect error message for invalid login");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
