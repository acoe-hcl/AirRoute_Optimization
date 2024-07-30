import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GuaranteeDocumentTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Set up Chrome driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

    @Test
    public void generateGuaranteeDocument() {
        // Open Trade Finance System
        driver.get("https://trade.finance.system");

        // Login as a Trade Finance Operations member
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameInput.sendKeys("TFO_member");
        passwordInput.sendKeys("password");
        loginButton.click();

        // Fill in the Transaction ID and submit the form
        WebElement transactionIdInput = driver.findElement(By.id("transactionId"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));

        transactionIdInput.sendKeys("123456");
        submitButton.click();

        // Verify AML Status is "Clear"
        WebElement amlStatus = driver.findElement(By.id("amlStatus"));
        Assert.assertEquals(amlStatus.getText(), "Clear");

        // Verify KYC Status is "Verified"
        WebElement kycStatus = driver.findElement(By.id("kycStatus"));
        Assert.assertEquals(kycStatus.getText(), "Verified");

        // Verify Sanctions is "No Hits"
        WebElement sanctions = driver.findElement(By.id("sanctions"));
        Assert.assertEquals(sanctions.getText(), "No Hits");

        // Generate Guarantee Text
        WebElement generateButton = driver.findElement(By.id("generateButton"));
        generateButton.click();

        // Verify Credit Approval is present
        WebElement creditApproval = driver.findElement(By.id("creditApproval"));
        Assert.assertTrue(creditApproval.isDisplayed());

        // Verify Approval Date is present
        WebElement approvalDate = driver.findElement(By.id("approvalDate"));
        Assert.assertTrue(approvalDate.isDisplayed());

        // Verify Dispatch Method is present
        WebElement dispatchMethod = driver.findElement(By.id("dispatchMethod"));
        Assert.assertTrue(dispatchMethod.isDisplayed());

        // Verify Notification Date is present
        WebElement notificationDate = driver.findElement(By.id("notificationDate"));
        Assert.assertTrue(notificationDate.isDisplayed());

        // Provide Comments/Notes and submit the form
        WebElement commentsInput = driver.findElement(By.id("commentsInput"));
        WebElement submitFormButton = driver.findElement(By.id("submitFormButton"));

        commentsInput.sendKeys("Approved");
        submitFormButton.click();

        // Verify Approval Level is updated to the appropriate level
        WebElement approvalLevel = driver.findElement(By.id("approvalLevel"));
        Assert.assertEquals(approvalLevel.getText(), "Updated Level");

        // Logout
        WebElement logoutButton = driver.findElement(By.id("logoutButton"));
        logoutButton.click();
    }
}