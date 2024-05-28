Sure, I can help you with that. Here is the Selenium Java test automation script for the given scenario:

```java
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {
    public static void main(String[] args) throws InterruptedException {
        // Set the browser property
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        WebDriver driver = new ChromeDriver();

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Sign In
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on Gear menu and click on Bags link
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.id("gear"));
        actions.moveToElement(gearMenu).perform();

        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on Overnight Duffle image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
        overnightDuffleImage.click();

        // Add to cart and go to My Cart
        WebElement addToCartButton = driver.findElement(By.id("product_addtocart_form"));
        addToCartButton.submit();

        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Proceed to Checkout
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Verify Order Summary
        WebElement orderSummary = driver.findElement(By.xpath("//div[@class='checkout-step-title'][contains(text(),'Order Summary')]"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary is displaying Overnight Duffle product");
        } else {
            System.out.println("Order Summary is not displaying Overnight Duffle product");
        }

        // Enter address details
        WebElement newAddressButton = driver.findElement(By.id("billing:street1"));
        newAddressButton.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");

        WebElement postalCodeField = driver.findElement(By.id("billing:postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Select shipping method and proceed
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Select billing and shipping address
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Place order
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verify success page
        WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you for your purchase!')]"));
        System.out.println("Success message: " + successMessage.getText());

        // Click on Change button and sign out
        WebElement changeButton = driver.findElement(By.linkText("Change"));
        changeButton.click();

        WebElement signOutLink = driver.findElement(By.linkText("Signout"));
        signOutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Please make sure to replace "path_to_chromedriver" with the actual path to your ChromeDriver executable.

Hope this helps! Let me know if you have any further questions.