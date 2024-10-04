import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void navigateToMagentoWebsite() {
        // Open Magento website
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @Test(priority = 2)
    public void signIn() {
        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Verify page name and browser name
        String pageName = driver.getTitle();
        String browserName = driver.getClass().getSimpleName();
        assert pageName.equals("Customer Login");
        assert browserName.contains("Chrome");
    }

    @Test(priority = 3)
    public void enterCredentials() {
        // Enter email address and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
    }

    @Test(priority = 4)
    public void navigateToDrivenBackpack() {
        // Hover over the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[@class='level0' and text()='Gear']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.xpath("//a[text()='Bags']"));
        bagsLink.click();

        // Click on the image of the "Driven Backpack"
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();
    }

    @Test(priority = 5)
    public void addToCart() {
        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();
    }

    @Test(priority = 6)
    public void viewCart() {
        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[@class='skip-link skip-cart']"));
        myCartLink.click();

        // Verify "Driven Backpack" product in the order summary
        WebElement orderSummaryText = driver.findElement(By.xpath("//td[contains(.,'Driven Backpack')]"));
        assert orderSummaryText.isDisplayed();
    }

    @Test(priority = 7)
    public void proceedToCheckout() {
        // Click on the "Proceed to Checkout" button
        WebElement checkoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        checkoutButton.click();
    }

    @Test(priority = 8)
    public void enterShippingAddress() {
        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();

        // Enter street address, city, state/province, zip/postal code, and phone number
        WebElement streetAddressField = driver.findElement(By.id("shipping:street1"));
        streetAddressField.sendKeys("123 Street");

        WebElement cityField = driver.findElement(By.id("shipping:city"));
        cityField.sendKeys("City");

        WebElement stateField = driver.findElement(By.id("shipping:region"));
        stateField.sendKeys("State");

        WebElement zipCodeField = driver.findElement(By.id("shipping:postcode"));
        zipCodeField.sendKeys("12345");

        WebElement phoneField = driver.findElement(By.id("shipping:telephone"));
        phoneField.sendKeys("1234567890");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();
    }

    @Test(priority = 9)
    public void selectShippingOption() {
        // Select "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();
    }

    @Test(priority = 10)
    public void selectBillingAddress() {
        // Select "My billing and shipping address are the same" checkbox
        WebElement billingAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        billingAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();
    }

    @Test(priority = 11)
    public void verifyOrderConfirmation() {
        // Verify confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
        assert confirmationMessage.isDisplayed();
    }

    @Test(priority = 12)
    public void signOut() {
        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
