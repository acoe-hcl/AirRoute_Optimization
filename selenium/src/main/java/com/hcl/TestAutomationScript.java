Here is a Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestAutomationScript {
    public static void main(String[] args) {

        // Set the browser driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create an instance of the WebDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the given URL
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name and page name
        driver.executeScript("window.guruBrowser = 'Customer Login';");
        driver.executeScript("window.guruPage = 'Customer Login';");

        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.className("gear"));
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//a[@title='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[text()='Order Summary']"));
        String product = "Driven Backpack";
        if (orderSummary.getText().contains(product)) {
            System.out.println(product + " is present in the order summary");
        } else {
            System.out.println(product + " is not present in the order summary");
        }

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing-new-address-form"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        stateDropdown.sendKeys("Texas");

        WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
        zipCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_bestway"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("shipping-method-buttons-container"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.id("checkout-shipping-method-load"));
        placeOrderButton.click();

        // Verify the message "Thank you for your purchase!"
        WebElement confirmationMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
        String expectedMessage = "Thank you for your purchase!";
        String actualMessage = confirmationMessage.getText();
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Confirmation message is displayed correctly");
        } else {
            System.out.println("Confirmation message is not displayed correctly");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.id("top-link-checkout"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Please make sure to replace "path/to/chromedriver" with the actual path to your ChromeDriver.