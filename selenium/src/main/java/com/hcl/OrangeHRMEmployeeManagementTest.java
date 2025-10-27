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

    // Test data
    final String baseUrl = "https://orangehrm-demo-url.com"; // Replace with actual URL
    final String adminUsername = "admin_user";
    final String adminPassword = "admin_pass";
    final String empFirstName = "John";
    final String empLastName = "Doe";
    final String empId = "12345";
    final String empUsername = "johndoe";
    final String empPassword = "Emp@1234";
    final String invalidUsername = "invalid_user";
    final String invalidPassword = "invalid_pass";

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

    // Utility login method
    public void login(String username, String password) {
        driver.get(baseUrl + "/auth/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    // Utility logout method
    public void logout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.oxd-userdropdown-tab"))).click();
        WebElement logoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']")));
        logoutBtn.click();
        wait.until(ExpectedConditions.urlContains("/auth/login"));
    }

    @Test(priority = 1)
    public void testAdminLogin() {
        login(adminUsername, adminPassword);
        // Assert dashboard loaded
        WebElement dashboardHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard is not displayed, login might have failed.");
    }

    @Test(priority = 2, dependsOnMethods = "testAdminLogin")
    public void testAddNewEmployee() {
        // Navigate to PIM
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='PIM']"))).click();
        // Click Add Employee
        WebElement addEmployeeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'/pim/addEmployee')]")));
        addEmployeeBtn.click();
        // Fill employee details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys(empFirstName);
        driver.findElement(By.name("lastName")).sendKeys(empLastName);
        WebElement empIdField = driver.findElement(By.xpath("//label[text()='Employee Id']/following::input[1]"));
        empIdField.clear();
        empIdField.sendKeys(empId);
        // Enable 'Create Login Details'
        driver.findElement(By.cssSelector("span.oxd-switch-input.oxd-switch-input--active")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Username']/following::input[1]")))
                .sendKeys(empUsername);
        driver.findElement(By.xpath("//label[text()='Password']/following::input[1]")).sendKeys(empPassword);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/following::input[1]")).sendKeys(empPassword);
        // Click Save
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Verify redirection and personal details
        WebElement personalDetailsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Personal Details']")));
        Assert.assertTrue(personalDetailsHeader.isDisplayed(), "Personal Details page not displayed.");
        WebElement createdEmpName = driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-name']/h6"));
        Assert.assertTrue(createdEmpName.getText().contains(empFirstName + " " + empLastName),
                "Employee name does not match after creation.");
    }

    @Test(priority = 3, dependsOnMethods = "testAddNewEmployee")
    public void testVerifyEmployeeCreation() {
        // Navigate to PIM > Employee List
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='PIM']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Employee List')]"))).click();
        // Search by Employee ID
        WebElement empIdSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Employee Id']/following::input[1]")));
        empIdSearch.clear();
        empIdSearch.sendKeys(empId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Validate results
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
        Assert.assertTrue(rows.size() == 1, "Expected 1 employee in search result.");
        String name = driver.findElement(By.xpath("//div[@class='oxd-table-cell'][2]")).getText();
        String id = driver.findElement(By.xpath("//div[@class='oxd-table-cell'][3]")).getText();
        Assert.assertEquals(name, empFirstName + " " + empLastName, "Employee name mismatch in list.");
        Assert.assertEquals(id, empId, "Employee ID mismatch in list.");
    }

    @Test(priority = 4, dependsOnMethods = "testVerifyEmployeeCreation")
    public void testDeleteEmployee() {
        // In Employee List, search again to ensure up-to-date list
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='PIM']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Employee List')]"))).click();
        WebElement empIdSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Employee Id']/following::input[1]")));
        empIdSearch.clear();
        empIdSearch.sendKeys(empId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']/div")));
        // Locate and click the Delete button
        WebElement deleteBtn = driver.findElement(By.xpath("//button[@class='oxd-icon-button oxd-table-cell-action-space'][1]"));
        deleteBtn.click();
        // Confirm deletion in modal
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()=' Yes, Delete ']")));
        confirmBtn.click();
        // Verify employee removed (No Records Found)
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='No Records Found']")));
        WebElement noRecordsMsg = driver.findElement(By.xpath("//span[text()='No Records Found']"));
        Assert.assertTrue(noRecordsMsg.isDisplayed(), "Employee record still present after deletion.");
    }

    @Test(priority = 5, dependsOnMethods = "testDeleteEmployee")
    public void testVerifyEmployeeDeletion() {
        logout();
        login(adminUsername, adminPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='PIM']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Employee List')]"))).click();
        WebElement empIdSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Employee Id']/following::input[1]")));
        empIdSearch.clear();
        empIdSearch.sendKeys(empId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Expect "No Records Found"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='No Records Found']")));
        WebElement noRecordsMsg = driver.findElement(By.xpath("//span[text()='No Records Found']"));
        Assert.assertTrue(noRecordsMsg.isDisplayed(), "Deleted employee still found in search.");
    }

    @Test(priority = 6, dependsOnMethods = "testVerifyEmployeeDeletion")
    public void testInvalidLoginAttempt() {
        logout();
        driver.get(baseUrl + "/auth/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(invalidUsername);
        driver.findElement(By.name("password")).sendKeys(invalidPassword);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Expect error message: "Invalid credentials"
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Invalid credentials')]")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message for invalid credentials not shown.");
    }
}
