Sure! I can help you generate the Selenium-Java TestNG code for the given scenario. Here is the code:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PlaceOrderTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Set up the WebDriver instance
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void placeOrderTest() {
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on Sign In link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        driver.executeScript("window.name = 'Customer Login'");

        // Set the page name as "Customer Login"
        driver.executeScript("document.title = 'Customer Login'");

        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on Sign In button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on Gear menu
        WebElement gearMenu = driver.findElement(By.className("account-link"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on Bags link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on Driven Backpack image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on Add to Cart button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();

        // Click on My Cart link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify Order Summary has Driven Backpack product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[contains(text(),'Order Summary')]/following-sibling::table//a[contains(text(),'Driven Backpack')]"));
        Assert.assertNotNull(orderSummary);

        // Click on Proceed to Checkout button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Click on New Address button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        Select stateDropdownSelect = new Select(stateDropdown);
        stateDropdownSelect.selectByVisibleText("Texas");

        WebElement zipCodeField = driver.findElement(By.id("postcode"));
        zipCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on Ship Here button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Select Fixed radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on Next button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Select My billing and shipping address are the same checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on Place Order button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verify the thank you message
        WebElement thankYouMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you for your purchase!')]"));
        Assert.assertNotNull(thankYouMessage);

        // Click on Change button
        WebElement changeButton = driver.findElement(By.xpath("//button[@class='button btn-edit']"));
        changeButton.click();

        // Click on Signout link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }

    @AfterTest
    public void tearDown() {
        // Quit the WebDriver instance
        driver.quit();
    }
}
```

Please note that you will need to replace "path/to/chromedriver" with the actual path to your ChromeDriver executable. You will also need to make sure that you have the necessary dependencies (Selenium, TestNG, and ChromeDriver) set up in your project.

This code executes the steps outlined in the given scenario using Selenium WebDriver and TestNG framework. It performs various interactions with the Magento website and asserts the expected results.