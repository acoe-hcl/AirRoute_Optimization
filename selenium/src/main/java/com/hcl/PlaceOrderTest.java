As a professional code assistant named Guru, I can help you generate the Selenium Java TestNG code for the given scenario. Here is the code:

```java
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
    public void setUp() {
        // Set up Chrome driver
        System.setProperty("webdriver.chrome.driver", "path-to-chrome-driver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        // Quit the browser after test execution
        driver.quit();
    }

    @Test
    public void placeOrderTest() {
        // Given User navigates to the website
        driver.get("https://magento.softwaretestingboard.com/");

        // When I click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Then I should set the browser name as "Customer Login"
        String expectedTitle = "Customer Login";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        // And I should set the page name as "Customer Login"
        String expectedPageName = "Customer Login";
        String actualPageName = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(actualPageName, expectedPageName);

        // When I enter the email and password
        WebElement emailField = driver.findElement(By.name("login[username]"));
        emailField.sendKeys("testermail@gmail.com");
        WebElement passwordField = driver.findElement(By.name("login[password]"));
        passwordField.sendKeys("Tester@123");

        // And I click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // When I mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("customer-name"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // And I click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // When I click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[contains(@name, 'product_list')]"));
        drivenBackpackImage.click();

        // When I click on the "Add to Cart" button
         WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // And I click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // And I verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//div[@class='cart-totals']/table/tbody/tr[1]/td[2]/span"));
        String expectedProduct = "Driven Backpack";
        String actualProduct = orderSummary.getText();
        Assert.assertEquals(actualProduct, expectedProduct);

        // And I click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();

        // When I click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[contains(text(), 'New Address')]"));
        newAddressButton.click();

        // And I enter address details
        WebElement streetField = driver.findElement(By.id("shipping:street1"));
        streetField.sendKeys("4 South Street");
        WebElement cityField = driver.findElement(By.id("shipping:city"));
        cityField.sendKeys("Texas");
        WebElement stateDropdown = driver.findElement(By.id("shipping:region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");
        WebElement postalCodeField = driver.findElement(By.id("shipping:postcode"));
        postalCodeField.sendKeys("77567");
        WebElement phoneNumberField = driver.findElement(By.id("shipping:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // And I click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("shipping-save"));
        shipHereButton.click();

        // And I select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // And I click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(), 'Next')]"));
        nextButton.click();

        // And I select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing-address-same-as-shipping-1"));
        sameAddressCheckbox.click();

        // And I click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[contains(text(), 'Place Order')]"));
        placeOrderButton.click();

        // And I verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.className("success-msg"));
        String expectedMessage = "Thank you for your purchase!";
        String actualMessage = successMessage.getText();
        Assert.assertEquals(actualMessage, expectedMessage);

        // When I click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[contains(text(), 'Change')]"));
        changeButton.click();

        // And I click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }
}
```

Note: Remember to replace "path-to-chrome-driver" with the actual path to your Chrome driver.