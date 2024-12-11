import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MagentoOrderTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // Set the path of ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void placeOrderForDrivenBackpack() throws InterruptedException {
        // Given User navigates to "https://magento.softwaretestingboard.com/"
        driver.get("https://magento.softwaretestingboard.com/");

        // When I click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Then I should set the browser name as "Customer Login"
        String browserName = "Customer Login";

        // And I should set the page name as "Customer Login"
        String pageName = "Customer Login";

        // When I enter "testermail@gmail.com" in the "Email" field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        // And I enter "Tester@123" in the "Password" field as secure text
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // And I click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // When I mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("caret"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();

        // And I click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // When I click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // When I click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // And I click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // And I verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//td[@class='product-name']/a[text()='Driven Backpack']"));
        Assert.assertTrue(orderSummary.isDisplayed(), "Product 'Driven Backpack' is not present in the order summary.");

        // And I click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//div[@class='cart-totals']/following-sibling::div/button"));
        proceedToCheckoutButton.click();

        // When I click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("shipping-new-address-form"));
        newAddressButton.click();

        // And I enter "4 South Street" in the "Street" field
        WebElement streetField = driver.findElement(By.id("shipping:street1"));
        streetField.sendKeys("4 South Street");

        // And I enter "Texas" in the "City" field
        WebElement cityField = driver.findElement(By.id("shipping:city"));
        cityField.sendKeys("Texas");

        // And I select "Texas" from the "State/Province" dropdown
        Select stateDropdown = new Select(driver.findElement(By.id("shipping:region_id")));
        stateDropdown.selectByVisibleText("Texas");

        // And I enter "77567" in the "Zip/Postal Code" field
        WebElement zipCodeField = driver.findElement(By.id("shipping:postcode"));
        zipCodeField.sendKeys("77567");

        // And I enter "3456788765" in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.id("shipping:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // And I click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("shipping-buttons-container"));
        shipHereButton.click();

        // And I select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // And I click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']/span"));
        nextButton.click();

        // And I select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // And I click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.id("review-buttons-container"));
        placeOrderButton.click();

        // And I verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you for your purchase!')]"));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed.");

        // When I click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']/span"));
        changeButton.click();

        // And I click on the "Signout" link
        WebElement signOutLink = driver.findElement(By.linkText("Signout"));
        signOutLink.click();
    }
}
