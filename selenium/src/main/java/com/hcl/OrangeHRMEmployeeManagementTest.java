import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHRMEmployeeManagementTest {

    WebDriver driver;
    String baseUrl = "https://orangehrm-demo-6x.orangehrmlive.com/";

    // Test Data
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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void login(String username, String password) {
        driver.findElement(By.id("txtUsername")).clear();
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();
    }

    public void logout() {
        WebElement welcomeMenu = driver.findElement(By.id("welcome"));
        welcomeMenu.click();
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        logoutLink.click();
    }

    @Test(priority = 1)
    public void tc01_adminLogin() {
        login(adminUsername, adminPassword);
        WebElement dashboardHeader = driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]"));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header not displayed, login might have failed.");
    }

    @Test(priority = 2, dependsOnMethods = "tc01_adminLogin")
    public void tc02_addNewEmployee() {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.id("menu_pim_addEmployee")).click();
        driver.findElement(By.id("firstName")).sendKeys(empFirstName);
        driver.findElement(By.id("lastName")).sendKeys(empLastName);
        WebElement empIdField = driver.findElement(By.id("employeeId"));
        empIdField.clear();
        empIdField.sendKeys(empId);

        driver.findElement(By.id("chkLogin")).click(); // Enable 'Create login details'
        driver.findElement(By.id("user_name")).sendKeys(empUsername);
        driver.findElement(By.id("user_password")).sendKeys(empPassword);
        driver.findElement(By.id("re_password")).sendKeys(empPassword);

        driver.findElement(By.id("btnSave")).click();

        WebElement personalDetailsHeader = driver.findElement(By.xpath("//h1[contains(text(),'Personal Details')]"));
        Assert.assertTrue(personalDetailsHeader.isDisplayed(), "Employee was not redirected to personal details page.");
    }

    @Test(priority = 3, dependsOnMethods = "tc02_addNewEmployee")
    public void tc03_verifyEmployeeCreation() {
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();

        WebElement employeeRow = driver.findElement(By.xpath("//a[contains(text(),'"+empId+"')]/parent::td/preceding-sibling::td/a[contains(text(),'"+empFirstName+" " + empLastName+"')]"));
        Assert.assertTrue(employeeRow.isDisplayed(), "Employee with given ID and name not found in search results.");
    }

    @Test(priority = 4, dependsOnMethods = "tc03_verifyEmployeeCreation")
    public void tc04_deleteEmployee() {
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();
        WebElement checkbox = driver.findElement(By.xpath("//td[a[text()='"+empId+"']]/preceding-sibling::td/input[@type='checkbox']"));
        checkbox.click();
        driver.findElement(By.id("btnDelete")).click();
        driver.findElement(By.id("dialogDeleteBtn")).click();
        // Wait for list to refresh and confirm deletion
        WebElement noRecords = driver.findElement(By.xpath("//td[contains(text(),'No Records Found')]"));
        Assert.assertTrue(noRecords.isDisplayed(), "Employee was not removed - still found in the list.");
    }

    @Test(priority = 5, dependsOnMethods = "tc04_deleteEmployee")
    public void tc05_verifyEmployeeDeletion() {
        logout();
        login(adminUsername, adminPassword);
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();
        WebElement noRecords = driver.findElement(By.xpath("//td[contains(text(),'No Records Found')]"));
        Assert.assertTrue(noRecords.isDisplayed(), "Deleted employee still appears in the employee list.");
    }

    @Test(priority = 6, dependsOnMethods = "tc05_verifyEmployeeDeletion")
    public void tc06_invalidLoginAttempt() {
        logout();
        login(invalidUsername, invalidPassword);
        WebElement errorMsg = driver.findElement(By.id("spanMessage"));
        Assert.assertTrue(errorMsg.isDisplayed(), "No error message displayed for invalid login attempt.");
        Assert.assertEquals(errorMsg.getText(), "Invalid credentials", "Error message does not match expected for invalid login.");
    }
}
