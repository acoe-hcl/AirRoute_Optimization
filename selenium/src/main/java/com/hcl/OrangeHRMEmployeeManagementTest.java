import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class OrangeHRMEmployeeManagementTest {

    private WebDriver driver;
    private final String baseUrl = "https://orangehrm-demo-6x.orangehrmlive.com/";
    private final String adminUsername = "admin_user";
    private final String adminPassword = "admin_pass";
    private final String empFirstName = "John";
    private final String empLastName = "Doe";
    private final String empId = "12345";
    private final String empUsername = "johndoe";
    private final String empPassword = "Emp@1234";
    private final String invalidUsername = "invalid_user";
    private final String invalidPassword = "invalid_pass";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void testAdminLogin() {
        driver.get(baseUrl);
        driver.findElement(By.id("txtUsername")).sendKeys(adminUsername);
        driver.findElement(By.id("txtPassword")).sendKeys(adminPassword);
        driver.findElement(By.id("btnLogin")).click();
        WebElement dashboard = driver.findElement(By.id("menu_dashboard_index"));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard is not displayed after login.");
    }

    @Test(priority = 2)
    public void testAddNewEmployee() {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.id("menu_pim_addEmployee")).click();
        driver.findElement(By.id("firstName")).sendKeys(empFirstName);
        driver.findElement(By.id("lastName")).sendKeys(empLastName);
        WebElement empIdField = driver.findElement(By.id("employeeId"));
        empIdField.clear();
        empIdField.sendKeys(empId);
        driver.findElement(By.id("chkLogin")).click();
        driver.findElement(By.id("user_name")).sendKeys(empUsername);
        driver.findElement(By.id("user_password")).sendKeys(empPassword);
        driver.findElement(By.id("re_password")).sendKeys(empPassword);
        driver.findElement(By.id("btnSave")).click();
        WebElement personalDetailsHeader = driver.findElement(By.xpath("//h1[contains(text(),'Personal Details')]"));
        Assert.assertTrue(personalDetailsHeader.isDisplayed(), "Not redirected to Personal Details page after adding employee.");
    }

    @Test(priority = 3, dependsOnMethods = "testAddNewEmployee")
    public void testVerifyEmployeeCreation() {
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();
        WebElement empTable = driver.findElement(By.id("resultTable"));
        Assert.assertTrue(empTable.getText().contains(empFirstName), "Employee first name not found in search results.");
        Assert.assertTrue(empTable.getText().contains(empLastName), "Employee last name not found in search results.");
        Assert.assertTrue(empTable.getText().contains(empId), "Employee ID not found in search results.");
    }

    @Test(priority = 4, dependsOnMethods = "testVerifyEmployeeCreation")
    public void testDeleteEmployee() {
        WebElement row = driver.findElement(By.xpath("//a[text()='" + empId + "']/ancestor::tr"));
        WebElement checkbox = row.findElement(By.xpath(".//input[@type='checkbox']"));
        checkbox.click();
        driver.findElement(By.id("btnDelete")).click();
        driver.findElement(By.id("dialogDeleteBtn")).click();
        WebElement noRecordsMsg = driver.findElement(By.xpath("//td[contains(text(),'No Records Found')]"));
        Assert.assertTrue(noRecordsMsg.isDisplayed(), "Employee record was not deleted.");
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
        WebElement noRecordsMsg = driver.findElement(By.xpath("//td[contains(text(),'No Records Found')]"));
        Assert.assertTrue(noRecordsMsg.isDisplayed(), "Deleted employee still appears in the list.");
    }

    @Test(priority = 6)
    public void testInvalidLoginAttempt() {
        driver.findElement(By.id("welcome")).click();
        driver.findElement(By.linkText("Logout")).click();

        driver.findElement(By.id("txtUsername")).sendKeys(invalidUsername);
        driver.findElement(By.id("txtPassword")).sendKeys(invalidPassword);
        driver.findElement(By.id("btnLogin")).click();
        WebElement errorMsg = driver.findElement(By.id("spanMessage"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Invalid login error message not displayed.");
        Assert.assertEquals(errorMsg.getText().trim(), "Invalid credentials", "Error message text does not match expected.");
    }
}
