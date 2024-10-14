import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrderTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void placeOrderTest() {
        // Given User navigates to "https://magento.softwaretestingboard.com/"
        driver.get("https://magento.softwaretestingboard.com/");

        // When I click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Then I should set the browser name as "Customer Login"
        String browserName = driver.getTitle();
        Assert.assertEquals(browserName, "Customer Login");

        // And I should set the page name as "Customer Login"
        String pageName = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(pageName, "Customer Login");

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
        WebElement gearMenu = driver.findElement(By.className("customer-name"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // And I click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.cssSelector("a[title='Bags']"));
        bagsLink.click();

        // When I click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//a[contains(@href, 'driven-backpack.html')]"));
        drivenBackpackImage.click();

        // When I click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // And I click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.partialLinkText("My Cart"));
        myCartLink.click();

        // And I verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[contains(text(), 'Order Summary')]"));
        String orderSummaryText = orderSummary.getText();
        Assert.assertTrue(orderSummaryText.contains("Driven Backpack"));

        // And I click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.cssSelector("button[title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // When I click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.cssSelector("button[title='New Address']"));
        newAddressButton.click();

        // And I enter "4 South Street" in the "Street" field
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        // And I enter "Texas" in the "City" field
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        // And I select "Texas" from the "State/Province" dropdown
        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");

        // And I enter "77567" in the "Zip/Postal Code" field
        WebElement zipCodeField = driver.findElement(By.id("postcode"));
        zipCodeField.sendKeys("77567");

        // And I enter "3456788765" in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // And I click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.cssSelector("button[title='Ship Here']"));
        shipHereButton.click();

        // And I select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // And I click on the "Next" button
        WebElement nextButton = driver.findElement(By.cssSelector("button[title='Next']"));
        nextButton.click();

        // And I select the "My billing and shipping address are the same" checkbox
        WebElement billingShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        billingShippingCheckbox.click();

        // And I click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.cssSelector("button[title='Place Order']"));
        placeOrderButton.click();

        // And I verify the message "Thank you for your purchase!"
        WebElement confirmationMessage = driver.findElement(By.cssSelector("h1.page-title"));
        String confirmationMessageText = confirmationMessage.getText();
        Assert.assertEquals(confirmationMessageText, "Thank You For Your Purchase!");

        // When I click on the "Change" button
        WebElement changeButton = driver.findElement(By.cssSelector("button[title='Change']"));
        changeButton.click();

        // And I click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.cssSelector("a[title='Sign Out']"));
        signoutLink.click();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
