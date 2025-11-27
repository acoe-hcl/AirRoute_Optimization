import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class OrangeHRMEmployeeManagementTest {

    WebDriver driver;
    String baseUrl = "https://orangehrm-demo-6x.orangehrmlive.com"; // Placeholder, replace with actual URL
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testAdminLogin() {
        driver.get(baseUrl + "/auth/login");
        driver.findElement(By.id("txtUsername")).sendKeys(adminUsername);
        driver.findElement(By.id("txtPassword")).sendKeys(adminPassword);
        driver.findElement(By.id("btnLogin")).click();
        WebElement dashboard = driver.findElement(By.id("welcome"));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard welcome message not displayed. Login failed.");
    }

    @Test(priority = 2, dependsOnMethods = "testAdminLogin")
    public void testAddNewEmployee() throws InterruptedException {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.id("menu_pim_addEmployee")).click();
        driver.findElement(By.id("firstName")).sendKeys(empFirstName);
        driver.findElement(By.id("lastName")).sendKeys(empLastName);
        WebElement empIdField = driver.findElement(By.id("employeeId"));
        empIdField.clear();
        empIdField.sendKeys(empId);
        driver.findElement(By.id("chkLogin")).click(); // Enable "Create Login Details"
        driver.findElement(By.id("user_name")).sendKeys(empUsername);
        driver.findElement(By.id("user_password")).sendKeys(empPassword);
        driver.findElement(By.id("re_password")).sendKeys(empPassword);
        driver.findElement(By.id("btnSave")).click();
        WebElement personalDetailsHeader = driver.findElement(By.xpath("//h1[text()='Personal Details']"));
        Assert.assertTrue(personalDetailsHeader.isDisplayed(), "Did not redirect to Personal Details after employee creation.");
        WebElement employeeNameHeader = driver.findElement(By.id("profile-pic"));
        Assert.assertTrue(employeeNameHeader.getText().contains(empFirstName), "Employee first name not matching after creation.");
    }

    @Test(priority = 3, dependsOnMethods = "testAddNewEmployee")
    public void testEmployeeCreationVerification() {
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='resultTable']//tr"));
        Assert.assertTrue(rows.size() > 1, "Employee search results not found for given ID.");
        WebElement empNameCell = driver.findElement(By.xpath("//td[a and contains(text(),'" + empId + "')]/preceding-sibling::td[2]/a"));
        String fullName = empFirstName + " " + empLastName;
        Assert.assertEquals(empNameCell.getText().trim(), fullName, "Employee full name does not match in the list.");
        WebElement empIdCell = driver.findElement(By.xpath("//td[a and contains(text(),'" + empId + "')]"));
        Assert.assertEquals(empIdCell.getText().trim(), empId, "Employee ID does not match in the list.");
    }

    @Test(priority = 4, dependsOnMethods = "testEmployeeCreationVerification")
    public void testDeleteEmployee() throws InterruptedException {
        driver.findElement(By.id("empsearch_id")).clear();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();
        WebElement checkbox = driver.findElement(By.xpath("//a[text()='"+empId+"']/../preceding-sibling::td/input[@type='checkbox']"));
        checkbox.click();
        driver.findElement(By.id("btnDelete")).click();
        WebElement deleteDialog = driver.findElement(By.id("deleteConfModal"));
        Assert.assertTrue(deleteDialog.isDisplayed(), "Delete confirmation modal not displayed.");
        driver.findElement(By.id("dialogDeleteBtn")).click();
        // Wait for deletion to complete and table to update (could use more robust wait)
        Thread.sleep(2000);
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='resultTable']//tr"));
        if(rows.size()>1) {
            List<WebElement> ids = driver.findElements(By.xpath("//table[@id='resultTable']//td[a and text()='" + empId + "']"));
            Assert.assertTrue(ids.isEmpty(), "Employee record still present after deletion.");
        }
    }

    @Test(priority = 5, dependsOnMethods = "testDeleteEmployee")
    public void testVerifyEmployeeDeletion() {
        driver.findElement(By.id("welcome")).click();
        driver.findElement(By.linkText("Logout")).click();
        // re-login as admin
        driver.findElement(By.id("txtUsername")).sendKeys(adminUsername);
        driver.findElement(By.id("txtPassword")).sendKeys(adminPassword);
        driver.findElement(By.id("btnLogin")).click();
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        driver.findElement(By.id("empsearch_id")).sendKeys(empId);
        driver.findElement(By.id("searchBtn")).click();
        WebElement noRecords = driver.findElement(By.xpath("//td[text()='No Records Found']"));
        Assert.assertTrue(noRecords.isDisplayed(), "Deleted employee's record still showing in employee list.");
    }

    @Test(priority = 6, dependsOnMethods = "testVerifyEmployeeDeletion")
    public void testInvalidLoginAttempt() {
        driver.findElement(By.id("welcome")).click();
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.id("txtUsername")).sendKeys(invalidUsername);
        driver.findElement(By.id("txtPassword")).sendKeys(invalidPassword);
        driver.findElement(By.id("btnLogin")).click();
        WebElement errorMsg = driver.findElement(By.id("spanMessage"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed for invalid login attempt.");
        Assert.assertTrue(errorMsg.getText().toLowerCase().contains("invalid"), "Error message does not mention invalid credentials.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
