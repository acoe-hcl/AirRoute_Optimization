import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CustomerAccountDetailsTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set path to chromedriver as needed
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void loginToCardManagementApp() {
        driver.get("http://localhost/");
        driver.findElement(By.id("username")).sendKeys("testuser"); // Replace with actual locator and credentials
        driver.findElement(By.id("password")).sendKeys("testpass");
        driver.findElement(By.id("submit")).click(); // Replace with actual locator
        // Wait for navigation and ensure homepage loads
        Assert.assertTrue(driver.getTitle().contains("Card Management")); // Or check for homepage element
    }

    public void navigateToAccountView() {
        driver.findElement(By.id("accountViewNav")).click(); // Replace with actual locator
        Assert.assertTrue(driver.getCurrentUrl().contains("accountView")); // Or check for page element
    }

    @Test
    public void testViewAccountDetailsValidAccountId() {
        loginToCardManagementApp();
        navigateToAccountView();

        driver.findElement(By.id("accountIdInput")).sendKeys("2");
        driver.findElement(By.id("searchButton")).click();

        // Wait and Assert account information displayed
        WebElement accountInfo = driver.findElement(By.id("accountInfo")); // Replace with actual locator
        Assert.assertTrue(accountInfo.isDisplayed());
        Assert.assertTrue(accountInfo.getText().contains("Account Details"));
    }

    @Test
    public void testBlankAccountIdError() {
        loginToCardManagementApp();
        navigateToAccountView();

        driver.findElement(By.id("accountIdInput")).clear();
        driver.findElement(By.id("searchButton")).click();

        WebElement errorMsg = driver.findElement(By.id("accountIdError")); // Replace with actual locator
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText().trim(), "Account Filter must be a non-zero 11 digit number");

        WebElement prompt = driver.findElement(By.id("accountIdPrompt")); // Replace with actual locator
        Assert.assertTrue(prompt.isDisplayed());
        Assert.assertTrue(prompt.getText().contains("enter a valid account ID"));
    }

    @Test
    public void testNonNumericAccountIdError() {
        loginToCardManagementApp();
        navigateToAccountView();

        driver.findElement(By.id("accountIdInput")).sendKeys("@");
        driver.findElement(By.id("searchButton")).click();

        WebElement errorMsg = driver.findElement(By.id("accountIdError")); // Replace with actual locator
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText().trim(), "Account Filter must be a non-zero 11 digit number");

        WebElement prompt = driver.findElement(By.id("accountIdPrompt")); // Replace with actual locator
        Assert.assertTrue(prompt.isDisplayed());
        Assert.assertTrue(prompt.getText().contains("enter a valid account ID"));
    }

    @Test
    public void testLessThan11DigitsAccountIdError() {
        loginToCardManagementApp();
        navigateToAccountView();

        driver.findElement(By.id("accountIdInput")).sendKeys("1234567890");
        driver.findElement(By.id("searchButton")).click();

        WebElement errorMsg = driver.findElement(By.id("accountIdError")); // Replace with actual locator
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText().trim(), "Account Filter must be a non-zero 11 digit number");
    }

    @Test
    public void testMoreThan11DigitsAccountIdError() {
        loginToCardManagementApp();
        navigateToAccountView();

        driver.findElement(By.id("accountIdInput")).sendKeys("123456789012");
        driver.findElement(By.id("searchButton")).click();

        WebElement errorMsg = driver.findElement(By.id("accountIdError")); // Replace with actual locator
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText().trim(), "Account Filter must be a non-zero 11 digit number");
    }
}
