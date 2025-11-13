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

    private WebDriver driver;
    private WebDriverWait wait;

    // Test Data
    private final String baseUrl = "https://example.orangehrm.com/"; // Update with actual URL
    private final String adminUsername = "admin_user";
    private final String adminPassword = "admin_pass";
    private final String employeeFirstName = "John";
    private final String employeeLastName = "Doe";
    private final String employeeId = "12345";
    private final String employeeUsername = "johndoe";
    private final String employeePassword = "Emp@1234";
    private final String invalidUsername = "invalid_user";
    private final String invalidPassword = "invalid_pass";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) driver.quit();
    }

    @Test(priority = 1)
    public void testLoginAsAdmin() {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                .sendKeys(adminUsername);
        driver.findElement(By.name("password")).sendKeys(adminPassword);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[contains(text(),'Dashboard')]")));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard is not displayed after login!");
    }

    @Test(priority = 2, dependsOnMethods = "testLoginAsAdmin")
    public void testAddEmployeeWithLoginDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("PIM"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Add')]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys(employeeFirstName);
        driver.findElement(By.name("lastName")).sendKeys(employeeLastName);

        WebElement empIdInput = driver.findElement(By.xpath("//label[contains(.,'Employee Id')]/../following-sibling::div/input"));
        empIdInput.clear();
        empIdInput.sendKeys(employeeId);

        WebElement createLoginToggle = driver.findElement(By.xpath("//span[contains(text(),'Create Login Details')]/../..//input[@type='checkbox']"));
        if(!createLoginToggle.isSelected()) {
            createLoginToggle.click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Username')]/../following-sibling::div/input")))
                .sendKeys(employeeUsername);

        driver.findElement(By.xpath("//label[contains(text(),'Password')]/../following-sibling::div/input")).sendKeys(employeePassword);
        driver.findElement(By.xpath("//label[contains(text(),'Confirm Password')]/../following-sibling::div/input")).sendKeys(employeePassword);

        driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();

        WebElement personalDetailsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[contains(.,'Personal Details')]")));
        Assert.assertTrue(personalDetailsHeader.isDisplayed(), "Not redirected to Personal Details page!");
    }

    @Test(priority = 3, dependsOnMethods = "testAddEmployeeWithLoginDetails")
    public void testVerifyEmployeeCreation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("PIM"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'viewEmployeeList')]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(.,'Employee Id')]/../following-sibling::div/input")))
                .sendKeys(employeeId);

        driver.findElement(By.xpath("//button[.='Search']")).click();

        wait.withTimeout(Duration.ofSeconds(10));
        WebElement tableRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-card')]")));

        String rowText = tableRow.getText();
        Assert.assertTrue(rowText.contains(employeeFirstName + " " + employeeLastName), "Employee name not found in search result!");
        Assert.assertTrue(rowText.contains(employeeId), "Employee ID not found in search result!");
    }

    @Test(priority = 4, dependsOnMethods = "testVerifyEmployeeCreation")
    public void testDeleteEmployee() {
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-card')]")));

        row.findElement(By.xpath(".//button[contains(@class,'delete-action')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));
        driver.findElement(By.xpath("//button[.='Yes, Delete']")).click();

        wait.until(ExpectedConditions.invisibilityOf(row));
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-card')]"));
        boolean recordDeleted = true;
        for(WebElement r : rows) {
            if(r.getText().contains(employeeId)) {
                recordDeleted = false;
                break;
            }
        }
        Assert.assertTrue(recordDeleted, "Employee record still present after deletion!");
    }

    @Test(priority = 5, dependsOnMethods = "testDeleteEmployee")
    public void testVerifyEmployeeDeletion() {
        driver.findElement(By.xpath("//span[contains(@class,'userdropdown-tab')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(adminUsername);
        driver.findElement(By.name("password")).sendKeys(adminPassword);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("PIM"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'viewEmployeeList')]"))).click();

        WebElement empIdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Employee Id')]/../following-sibling::div/input")));
        empIdInput.clear();
        empIdInput.sendKeys(employeeId);

        driver.findElement(By.xpath("//button[.='Search']")).click();

        WebElement noRecords = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'No Records Found') or contains(text(),'No Records found')]")));
        Assert.assertTrue(noRecords.isDisplayed(), "No Records message not displayed after deletion!");
    }

    @Test(priority = 6, dependsOnMethods = "testVerifyEmployeeDeletion")
    public void testInvalidLoginAttempt() {
        driver.findElement(By.xpath("//span[contains(@class,'userdropdown-tab')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(invalidUsername);
        driver.findElement(By.name("password")).sendKeys(invalidPassword);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Invalid credentials') or contains(text(),'Invalid Credentials')]")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Invalid credentials error not displayed!");
    }
}
