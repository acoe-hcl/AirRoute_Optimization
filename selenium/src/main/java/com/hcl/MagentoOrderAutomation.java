
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            // Step 1: Launch the application
            driver.get("https://magento.softwaretestingboard.com/");

            // Step 2: Click on the "Sign In" link
            WebElement signInLink = driver.findElement(By.linkText("Sign In"));
            signInLink.click();

            // Step 3: Enter email and password
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login[username]")));
            emailField.sendKeys("autotest567@gmail.com");

            WebElement passwordField = driver.findElement(By.name("login[password]"));
            passwordField.sendKeys("Tester@123");

            // Step 4: Click on the "Sign In" button
            WebElement signInButton = driver.findElement(By.id("send2"));
            signInButton.click();

            // Step 5: Mouse hover on the "Gear" menu
            WebElement gearMenu = driver.findElement(By.linkText("Gear"));
            Actions actions = new Actions(driver);
            actions.moveToElement(gearMenu).perform();

            // Step 6: Click on the "Bags" link
            WebElement bagsLink = driver.findElement(By.linkText("Bags"));
            bagsLink.click();

            // Step 7: Click on the "Overnight Duffle" image
            WebElement duffleImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='Overnight Duffle']")));
            duffleImage.click();

            // Step 8: Click on the "Add to Cart" button
            WebElement addToCartButton = driver.findElement(By.cssSelector("button[title='Add to Cart']"));
            addToCartButton.click();

            // Step 9: Click on the "My Cart" link
            WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
            myCartLink.click();

            // Step 10: Click on the "Proceed to Checkout" button
            WebElement checkoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
            checkoutButton.click();

            // Step 11: Verify the "Order Summary" has the "Overnight Duffle" product
            WebElement orderSummary = driver.findElement(By.xpath("//table[@id='checkout-review-table']//a[contains(text(),'Overnight Duffle')]"));
            if (orderSummary.isDisplayed()) {
                System.out.println("Order Summary contains Overnight Duffle product - Test Passed");
            } else {
                System.out.println("Order Summary does not contain Overnight Duffle product - Test Failed");
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
            stateDropdown.sendKeys("Texas");

            WebElement postalCodeField = driver.findElement(By.id("billing:postcode"));
            postalCodeField.sendKeys("77567");

            WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
            phoneNumberField.sendKeys("3456788765");

            // Step 14: Click on the "Ship Here" button
            WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
            shipHereButton.click();

            // Step 15: Select the "Fixed" radio button
            WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
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
            WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you for your purchase!')]"));
            if (successMessage.isDisplayed()) {
                System.out.println("Order placed successfully - Test Passed");
            } else {
                System.out.println("Order placement failed - Test Failed");
            }

            // Step 20: Click on the "Change" button
            WebElement changeButton = driver.findElement(By.linkText("Change"));
            changeButton.click();

            // Step 21: Click on the "Signout" link
            WebElement signoutLink = driver.findElement(By.linkText("Signout"));
            signoutLink.click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
``