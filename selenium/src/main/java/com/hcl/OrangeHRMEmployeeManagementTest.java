import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHRMEmployeeManagementTest {

    private WebDriver driver;
    private final String baseUrl = "https://your-orangehrm-url.com"; // Update this with the actual URL

    // Test Data
    private final String adminUser = "admin_user";
    private final String adminPass = "admin_pass";
    private final String empFirstName = "John";
    private final String empLastName = "Doe";
    private final String empId = "12345";
    private final String empUsername = "johndoe";
    private final String empPassword = "Emp@1234";
    private final String invalidUser = "invalid_user";
    private final String invalidPass = "invalid_pass";

    @BeforeClass
    public void setUp() {
        // Assuming chromedriver is in PATH
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testAdminLoginValid() {
        driver.get(baseUrl);
        driver.findElement(By.name("username")).sendKeys(adminUser);
        driver.findElement(By.name("password")).sendKeys(adminPass);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement dashboardHeader = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header should be displayed after login");
    }

    @Test(priority = 2, dependsOnMethods = "testAdminLoginValid")
    public void testAddNewEmployee() {
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[contains(.,'Add Employee')]")).click();
        driver.findElement(By.name("firstName")).sendKeys(empFirstName);
        driver.findElement(By.name("lastName")).sendKeys(empLastName);
        WebElement empIdField = driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/following::input[1]"));
        empIdField.clear();
        empIdField.sendKeys(empId);
        driver.findElement(By.xpath("//span[contains(.,'Create Login Details')]/../following-sibling::div//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Username')]/following::input[1]")).sendKeys(empUsername);
        driver.findElement(By.xpath("//label[contains(text(),'Password')]/following::input[1]")).sendKeys(empPassword);
        driver.findElement(By.xpath("//label[contains(text(),'Confirm Password')]/following::input[1]")).sendKeys(empPassword);
        driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();

        // Personal Details page assertion
        WebElement personalDetailsHeader = driver.findElement(By.xpath("//h6[text()='Personal Details']"));
        Assert.assertTrue(personalDetailsHeader.isDisplayed(), "Should navigate to Personal Details page after adding employee");
    }

    @Test(priority = 3, dependsOnMethods = "testAddNewEmployee")
    public void testVerifyEmployeeCreation() {
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[contains(.,'Employee List')]")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/following::input[1]")).sendKeys(empId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement empRow = driver.findElement(By.xpath("//div[@role='row' and .//div[text()='" + empId + "'] and .//div[contains(text(),'" + empFirstName + " " + empLastName + "')]]"));
        Assert.assertTrue(empRow.isDisplayed(), "Employee with the given ID and name should be displayed in search result");
    }

    @Test(priority = 4, dependsOnMethods = "testVerifyEmployeeCreation")
    public void testDeleteEmployee() {
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[contains(.,'Employee List')]")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/following::input[1]")).clear();
        driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/following::input[1]")).sendKeys(empId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement checkBox = driver.findElement(By.xpath("//div[@role='row' and .//div[text()='" + empId + "']]//input[@type='checkbox']"));
        checkBox.click();
        driver.findElement(By.xpath("//button[contains(.,'Delete')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Yes, Delete')]")).click();

        // Wait for deletion (add better wait in real implementation)
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        boolean noResult = driver.findElements(By.xpath("//span[contains(.,'No Records Found')]")).size() > 0;
        Assert.assertTrue(noResult, "After deletion, employee should not be found in list");
    }

    @Test(priority = 5, dependsOnMethods = "testDeleteEmployee")
    public void testVerifyEmployeeDeletion() {
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        driver.findElement(By.name("username")).sendKeys(adminUser);
        driver.findElement(By.name("password")).sendKeys(adminPass);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[contains(.,'Employee List')]")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/following::input[1]")).clear();
        driver.findElement(By.xpath("//label[contains(text(),'Employee Id')]/following::input[1]")).sendKeys(empId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        boolean noResult = driver.findElements(By.xpath("//span[contains(.,'No Records Found')]")).size() > 0;
        Assert.assertTrue(noResult, "Employee should not be found after deletion");
    }

    @Test(priority = 6)
    public void testInvalidLoginAttempt() {
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        driver.findElement(By.name("username")).sendKeys(invalidUser);
        driver.findElement(By.name("password")).sendKeys(invalidPass);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement errorMsg = driver.findElement(By.xpath("//p[contains(.,'Invalid credentials')]"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message for invalid login should be displayed");
    }
}
