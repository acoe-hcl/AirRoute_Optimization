import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AGWebsiteTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the path of ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Launch Chrome browser
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void testAGWebsite() {
        // User navigate to "https://www.americangirl.com" website
        driver.get("https://www.americangirl.com");

        // Click on "Sign up or sign in" button
        WebElement signInButton = driver.findElement(By.linkText("Sign up or sign in"));
        signInButton.click();

        // Login with username and password
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("your_username");
        passwordField.sendKeys("your_password");

        // Submit the login form
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Search with SKU id
        WebElement searchField = driver.findElement(By.id("search-box"));
        searchField.sendKeys("sku_id");

        WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
        searchButton.click();

        // Click on quickShop button
        WebElement quickShopButton = driver.findElement(By.cssSelector("button.quick-shop"));
        quickShopButton.click();

        // Add product into the bag
        WebElement addToBagButton = driver.findElement(By.cssSelector("button.add-to-bag"));
        addToBagButton.click();

        // Check if AG rewards displaying
        WebElement rewardsElement = driver.findElement(By.id("rewards"));
        Assert.assertTrue(rewardsElement.isDisplayed(), "AG rewards not displaying");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser window
        driver.quit();
    }
}