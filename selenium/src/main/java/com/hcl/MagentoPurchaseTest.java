import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MagentoPurchaseTest {
    public static void main(String[] args) {

        // Set WebDriver path and initialize
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
            // 1. Open the Magento website.
            driver.get("https://magento2-demo.magebit.com/");

            // 2. Click on the "Sign In" link.
            WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In")));
            signInLink.click();

            // 3. Verify that the browser name and page name is set as "Customer Login".
            String expectedTitle = "Customer Login";
            if (!driver.getTitle().contains(expectedTitle)) {
                throw new AssertionError("Page title does not contain 'Customer Login'");
            }

            // 4. Enter valid email address and password in the designated fields.
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("user@testemail.com");

            WebElement passwordField = driver.findElement(By.id("pass"));
            passwordField.sendKeys("TestPassword123");

            // 5. Click on the "Sign In" button.
            WebElement signInButton = driver.findElement(By.id("send2"));
            signInButton.click();

            // 6. Hover over the "Gear" menu and click on the "Bags" link.
            WebElement gearMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Gear")));
            actions.moveToElement(gearMenu).perform();

            WebElement bagsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Bags")));
            bagsLink.click();

            // 7. Click on the image of the "Driven Backpack" product.
            WebElement drivenBackpack = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Driven Backpack']")));
            drivenBackpack.click();

            // 8. Click on the "Add to Cart" button.
            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
            addToCartBtn.click();

            // 9. Click on the "My Cart" link.
            WebElement myCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.showcart")));
            myCartLink.click();

            // 10. Verify that the "Order Summary" includes the "Driven Backpack" product.
            WebElement orderSummary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.minicart-items")));
            if (!orderSummary.getText().contains("Driven Backpack")) {
                throw new AssertionError("Order Summary does not contain 'Driven Backpack'");
            }

            // 11. Click on the "Proceed to Checkout" button.
            WebElement proceedToCheckoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("top-cart-btn-checkout")));
            proceedToCheckoutBtn.click();

            // 12. Click on the "New Address" button.
            WebElement newAddressBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-role='opc-new-shipping-address']")));
            newAddressBtn.click();

            // 13. Enter valid street address, city, state/province, zip/postal code, and phone number.
            WebElement streetAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("street[0]")));
            streetAddress.sendKeys("123 Test Street");

            WebElement cityField = driver.findElement(By.name("city"));
            cityField.sendKeys("Testville");

            Select stateDropdown = new Select(driver.findElement(By.name("region_id")));
            stateDropdown.selectByVisibleText("California");

            WebElement postalCode = driver.findElement(By.name("postcode"));
            postalCode.sendKeys("90001");

            WebElement phoneField = driver.findElement(By.name("telephone"));
            phoneField.sendKeys("1234567890");

            // 14. Click on the "Ship Here" button.
            WebElement shipHereBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-action='customer-address-form-submit']")));
            shipHereBtn.click();

            // 15. Select the "Fixed" radio button.
            WebElement fixedRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @value='flatrate_flatrate']")));
            fixedRadioButton.click();

            // 16. Click on the "Next" button.
            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-role='opc-continue']")));
            nextBtn.click();

            // 17. Select the "My billing and shipping address are the same" checkbox.
            WebElement billingSameCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing-address-same-as-shipping-checkmo")));
            if (!billingSameCheckbox.isSelected()) {
                billingSameCheckbox.click();
            }

            // 18. Click on the "Place Order" button.
            WebElement placeOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Place Order']")));
            placeOrderBtn.click();

            // 19. Verify that a confirmation message saying "Thank you for your purchase!" is displayed.
            WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.checkout-success")));
            if (!confirmationMsg.getText().contains("Thank you for your purchase!")) {
                throw new AssertionError("Confirmation message not found!");
            }

            // 20. Click on the "Change" button.
            WebElement changeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Change")));
            changeBtn.click();

            // 21. Click on the "Signout" link.
            WebElement signOutDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.action.switch")));
            signOutDropdown.click();
            WebElement signOutLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
            signOutLink.click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
