
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LinkedInAccountCreationTest {
    WebDriver driver;
    
    @BeforeClass
    public void setup() {
        // Set path to chromedriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider(name = "accountData")
    public Object[][] accountData() {
        return new Object[][] {
            {"validEmail@example.com", "StrongPass#1"},
            {"validPhoneNumber", "ValidPwd!123"},
        };
    }

    @Test(dataProvider = "accountData")
    public void testCreateLinkedInAccount(String emailOrPhone, String password) throws InterruptedException {
        // Step 1: Open the web browser and navigate to LinkedIn
        driver.get("https://www.linkedin.com/");
        Assert.assertEquals(driver.getTitle().contains("LinkedIn"), true, "LinkedIn home page not loaded.");

        // Step 2: Click on "New to LinkedIn? Join now"
        WebElement joinNowButton = driver.findElement(By.linkText("Join now"));
        joinNowButton.click();

        // Step 3: Verify redirection to "Create account" page
        WebElement createAccountHeader = driver.findElement(By.xpath("//h1[contains(text(), 'Create your account')]"));
        Assert.assertTrue(createAccountHeader.isDisplayed(), "Create account page not displayed.");

        // Step 4: Enter a valid email/phone number
        WebElement emailField = driver.findElement(By.id("email-or-phone"));
        emailField.clear();
        emailField.sendKeys(emailOrPhone);

        // Step 5: Enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Step 6: Click "Agree & Join"
        WebElement agreeJoinButton = driver.findElement(By.xpath("//button[contains(text(), 'Agree & Join')]"));
        agreeJoinButton.click();

        // Expected Result: Redirection to confirmation page after account creation
        WebElement confirmationMsg = driver.findElement(By.xpath("//*[contains(text(),'We sent you a confirmation')]"));
        Assert.assertTrue(confirmationMsg.isDisplayed(), "Confirmation page/message not displayed.");

        // Optionally, assert partial link text for confirmation
        WebElement confirmationEmailSMS = driver.findElement(By.xpath("//*[contains(text(),'Please check your email or phone')]"));
        Assert.assertTrue(confirmationEmailSMS.isDisplayed(), "Confirmation email/SMS notice not shown.");
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
