import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Configure webdriver path based on your system
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open the Magento application
            driver.get("https://magento.softwaretestingboard.com/");

            // Step 2: Click on the "Sign In" link
            WebElement signInLink = driver.findElement(By.linkText("Sign In"));
            signInLink.click();

            // Step 3: Enter email and password
            WebElement emailField = driver.findElement(By.name("login[username]"));
            emailField.sendKeys("autotest567@gmail.com");

            WebElement passwordField = driver.findElement(By.name("login[password]"));
            passwordField.sendKeys("Tester@123");

            // Step 4: Click on the "Sign In" button
            WebElement loginButton = driver.findElement(By.cssSelector("[title='Sign In']"));
            loginButton.click();

            // Step 5: Mouse hover on the "Gear" menu
            WebElement gearMenu = driver.findElement(By.className("greeting-link"));
            Actions action = new Actions(driver);
            action.moveToElement(gearMenu).perform();

            // Step 6: Click on the "Bags" link
            WebElement bagsLink = driver.findElement(By.linkText("Bags"));
            bagsLink.click();

            // Step 7: Click on the "Overnight Duffle" image
            WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
            overnightDuffleImage.click();

            // Step 8: Click on the "Add to Cart" button
            WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
            addToCartButton.click();

            // Step 9: Click on the "My Cart" link
            WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
            myCartLink.click();

            // Step 10: Click on the "Proceed to Checkout" button
            WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
            proceedToCheckoutButton.click();

            // Step 11: Verify "Order Summary" has "Overnight Duffle" product
            WebElement orderSummary = driver.findElement(By.className("cart-summary-item-name"));
            String productName = orderSummary.getText();
            if (productName.equals("Overnight Duffle")) {
                System.out.println("Order summary contains correct product");
            } else {
                System.out.println("Order summary does not contain correct product");
            }

            // Step 12: Click on the "New Address" button
            WebElement newAddressButton = driver.findElement(By.id("billing-new-address-form"));
            newAddressButton.click();

            // Step 13: Enter address details
            WebElement streetField = driver.findElement(By.id("billing:street1"));
            streetField.sendKeys("4 South Street");

            WebElement cityField = driver.findElement(By.id("billing:city"));
            cityField.sendKeys("Texas");

            WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
            Select stateDropdownSelect = new Select(stateDropdown);
            stateDropdownSelect.selectByVisibleText("Texas");

            WebElement postalCodeField = driver.findElement(By.id("billing:postcode"));
            postalCodeField.sendKeys("77567");

            WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
            phoneNumberField.sendKeys("3456788765");

            // Step 14: Click on the "Ship Here" button
            WebElement shipHereButton = driver.findElement(By.id("billing-save"));
            shipHereButton.click();

            // Step 15: Select the "Fixed" radio button
            WebElement fixedRadioButton = driver.findElement(By.cssSelector("[for='s_method_flatrate_flatrate']"));
            fixedRadioButton.click();

            // Step 16: Click on the "Next" button
            WebElement nextButton = driver.findElement(By.id("shipping-method-buttons-container"));
            nextButton.click();

            // Step 17: Select the "My billing and shipping address are the same" checkbox
            WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
            sameAddressCheckbox.click();

            // Step 18: Click on the "Place Order" button
            WebElement placeOrderButton = driver.findElement(By.id("review-buttons-container"));
            placeOrderButton.click();

            // Step 19: Verify the success message
            WebElement successMessage = driver.findElement(By.className("success-msg"));
            String message = successMessage.getText();
            if (message.contains("Thank you for your purchase!")) {
                System.out.println("Order placed successfully");
            } else {
                System.out.println("Order placement failed");
            }

            // Step 20: Click on the "Change" button
            WebElement changeButton = driver.findElement(By.id("changeCustomerId"));
            changeButton.click();

            // Step 21: Click on the "Signout" link
            WebElement signoutLink = driver.findElement(By.linkText("Signout"));
            signoutLink.click();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}