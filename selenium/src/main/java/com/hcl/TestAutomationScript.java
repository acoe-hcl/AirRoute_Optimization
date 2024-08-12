import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAutomationScript {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set the chromedriver executable path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize ChromeDriver instance
        driver = new ChromeDriver();
    }

    @Test
    public void placeOrderTest() {
        // Navigate to Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Verify browser name and page name
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        // Enter email address and password
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));
        emailField.sendKeys("testermail@gmail.com");
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.xpath("//li[@class='gear']"));
        WebElement bagsLink = driver.findElement(By.xpath("//li[@class='gear']//a[contains(text(),'Bags')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();
        bagsLink.click();

        // Click on the image of the "Driven Backpack"
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[@class='minicart-link']"));
        myCartLink.click();

        // Verify that the "Order Summary" includes the "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//div[@class='cart-items']//span[@class='product-name']"));
        Assert.assertEquals(orderSummary.getText(), "Driven Backpack");

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();

        // Enter address details
        WebElement streetAddressField = driver.findElement(By.id("street_1"));
        WebElement cityField = driver.findElement(By.id("city"));
        WebElement stateField = driver.findElement(By.id("region_id"));
        WebElement zipCodeField = driver.findElement(By.id("postcode"));
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        streetAddressField.sendKeys("123 Test Street");
        cityField.sendKeys("Test City");
        stateField.sendKeys("Test State");
        zipCodeField.sendKeys("12345");
        phoneNumberField.sendKeys("1234567890");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verify the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//span[text()='Thank you for your purchase!']"));
        Assert.assertTrue(confirmationMessage.isDisplayed());

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }

    @AfterMethod
    public void teardown() {
        // Quit the browser instance
        driver.quit();
    }
}