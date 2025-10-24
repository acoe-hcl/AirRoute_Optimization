import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class OrangeHRMEmployeeManagementTest {

    WebDriver driver;
    WebDriverWait wait;

    // Test Data
    String baseUrl = "https://example.orangehrm.com"; // Replace with actual URL
    String adminUsername = "admin_user";
    String adminPassword = "admin_pass";
    String employeeFirstName = "John";
    String employeeLastName = "Doe";
    String employeeId = "12345";
    String employeeUsername = "johndoe";
    String employeePassword = "Emp@1234";
    String invalidUsername = "invalid_user";
    String invalidPassword = "invalid_pass";

    @BeforeClass
    public void setUp() {
        // Replace path with actual chromedriver location if required
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void loginWithValidCredentials() {
        driver.get(baseUrl + "/auth/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(adminUsername);
        driver.findElement(By.name("password")).sendKeys(adminPassword);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        WebElement dashboardHeader = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header is not displayed, login failed");
    }

    @Test(priority = 2, dependsOnMethods = {"loginWithValidCredentials"})
    public void addNewEmployeeWithLoginDetails() {
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Add ']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys(employeeFirstName);
        driver.findElement(By.name("lastName")).sendKeys(employeeLastName);
        WebElement empIdField = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
        empIdField.clear();
        empIdField.sendKeys(employeeId);

        // Enable 'Create Login Details'
        driver.findElement(By.xpath("//span[contains(text(),'Create Login Details')]/preceding-sibling::span/input")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Username']/../following-sibling::div/input"))).sendKeys(employeeUsername);
        driver.findElement(By.xpath("//label[text()='Password']/../following-sibling::div/input")).sendKeys(employeePassword);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input")).sendKeys(employeePassword);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));
        WebElement personalDetailsHeader = driver.findElement(By.xpath("//h6[text()='Personal Details']"));
        Assert.assertTrue(personalDetailsHeader.isDisplayed(), "Employee was not redirected to Personal Details page");
    }

    @Test(priority = 3, dependsOnMethods = {"addNewEmployeeWithLoginDetails"})
    public void verifyEmployeeCreation() {
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Employee List']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"))).sendKeys(employeeId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));

        boolean found = false;
        for (WebElement row : rows) {
            String id = row.findElement(By.xpath(".//div[2]")).getText();
            String name = row.findElement(By.xpath(".//div[3]")).getText();
            if (id.equals(employeeId) && name.contains(employeeFirstName + " " + employeeLastName)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Newly added employee is not found in the Employee List");
    }

    @Test(priority = 4, dependsOnMethods = {"verifyEmployeeCreation"})
    public void deleteEmployeeAndVerifyDeletion() {
        // Locate employee with ID "12345"
        driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input")).clear();
        driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input")).sendKeys(employeeId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div"));
        boolean found = false;
        for (WebElement row : rows) {
            String id = row.findElement(By.xpath(".//div[2]")).getText();
            if (id.equals(employeeId)) {
                row.findElement(By.xpath(".//button[contains(@class,'oxd-icon-button')]")).click(); // Delete icon
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Employee with ID '12345' cannot be located for deletion");

        // Confirm deletion in popup/modal
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//button[span[text()=' Yes, Delete ']]"))).click();

        // Verify employee record is removed from the list
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));
        Boolean noRecord = driver.findElements(By.xpath("//span[normalize-space()='No Records Found']")).size() > 0
                || driver.findElements(By.xpath("//div[@class='oxd-table-body']/div")).isEmpty();
        Assert.assertTrue(noRecord, "Employee record was not deleted from the list");
    }

    @Test(priority = 5, dependsOnMethods = {"deleteEmployeeAndVerifyDeletion"})
    public void verifyEmployeeAbsenceAfterDeletion() {
        // Logout
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']"))).click();

        // Login again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(adminUsername);
        driver.findElement(By.name("password")).sendKeys(adminPassword);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));

        // Go to Employee List and search for deleted employee
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Employee List']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"))).clear();
        driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input")).sendKeys(employeeId);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));
        Boolean noRecord = driver.findElements(By.xpath("//span[normalize-space()='No Records Found']")).size() > 0
                || driver.findElements(By.xpath("//div[@class='oxd-table-body']/div")).isEmpty();
        Assert.assertTrue(noRecord, "Deleted employee is still present in the list after deletion");
    }

    @Test(priority = 6, dependsOnMethods = {"verifyEmployeeAbsenceAfterDeletion"})
    public void invalidLoginAttemptShowsErrorMessage() {
        // Logout
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(invalidUsername);
        driver.findElement(By.name("password")).sendKeys(invalidPassword);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait and verify error
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Invalid credentials') or contains(text(),'invalid credentials')]")));
        Assert.assertTrue(error.isDisplayed(), "Error message for invalid login not displayed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
