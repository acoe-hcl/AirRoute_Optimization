Here is a sample Selenium Java automation test script that covers all the steps and test validations mentioned in the given feature file:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderAutomation {

    public static void main(String[] args) {
        // Set the browser system property
        System.setProperty("webdriver.chrome.driver", "<path-to-chromedriver>");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the Magento application
        driver.get("https://magento.softwaretestingboard.com/");

        // Set the browser name and page name as "Home Page"
        String browserName = "Home Page";
        String pageName = "Home Page";

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name and page name as "Customer Login"
        browserName = "Customer Login";
        pageName = "Customer Login";

        // Enter email and password and click "Sign In" button
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Set the browser name and page name as "Home Page"
        browserName = "Home Page";
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu and click on "Bags" link
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.className("gear-icon"));
        actions.moveToElement(gearMenu).perform();

        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Set the browser name and page name as "Bags - Gear"
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        // Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.cssSelector("img[alt='Overnight Duffle']"));
        overnightDuffleImage.click();

        // Set the browser name and page name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.cssSelector("a[data-bind*='cart-sidebar']"));
        myCartLink.click();

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();

        // Set the browser name and page name as "Checkout"
        browserName = "Checkout";
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.cssSelector("#checkout .product-info span[data-bind='html: $parent.name']"));
        if (orderSummary.getText().equals("Overnight Duffle")) {
            System.out.println("Order Summary has Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not have Overnight Duffle product");
        }

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("checkout-address-add-new"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.id("checkout-address-street"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("checkout-address-city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("checkout-address-region_id"));
        stateDropdown.sendKeys("Texas");

        WebElement postalCodeField = driver.findElement(By.id("checkout-address-postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("checkout-address-telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("checkout-shipping-method-load"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("shipping-method-buttons-container"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.id("review-buttons-container"));
        placeOrderButton.click();

        // Set the browser name and page name as "Success Page"
        browserName = "Success Page";
        pageName = "Success Page";

        // Verify the success message
        WebElement successMessage = driver.findElement(By.cssSelector(".success-msg span"));
        if (successMessage.getText().equals("Thank you for your purchase!")) {
            System.out.println("Success message is displayed");
        } else {
            System.out.println("Success message is not displayed");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.id("change-addr_button"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Make sure to replace `<path-to-chromedriver>` with the actual path to your ChromeDriver executable.