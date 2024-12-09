import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderTest {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Step 1: Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Step 2: Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Step 3: Verify browser name and page name
        String browserName = driver.getClass().getSimpleName();
        String pageTitle = driver.getTitle();
        System.out.println("Browser Name: " + browserName);
        System.out.println("Page Title: " + pageTitle);

        // Step 4: Enter email address and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Step 5: Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Step 6: Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.cssSelector(".nav-3"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Step 7: Click on the image of the "Driven Backpack"
        WebElement drivenBackpackImage = driver.findElement(By.cssSelector(".product-image-photo"));
        drivenBackpackImage.click();

        // Step 8: Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Step 9: Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Step 10: Verify that "Driven Backpack" is in the order summary
        WebElement orderSummary = driver.findElement(By.cssSelector(".cart-item-description"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Driven Backpack")) {
            System.out.println("Order Summary includes Driven Backpack");
        } else {
            System.out.println("Order Summary does not include Driven Backpack");
        }

        // Step 11: Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();

        // Step 12: Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing-address-select-new"));
        newAddressButton.click();

        // Step 13: Enter address details
        WebElement streetAddressField = driver.findElement(By.id("billing:street1"));
        streetAddressField.sendKeys("123 Test Street");
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Test City");
        WebElement stateField = driver.findElement(By.id("billing:region_id"));
        stateField.sendKeys("Test State");
        WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
        zipCodeField.sendKeys("12345");
        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("1234567890");

        // Step 14: Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
        shipHereButton.click();

        // Step 15: Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Step 16: Click on the "Next" button
        WebElement nextButton = driver.findElement(By.cssSelector(".button-continue"));
        nextButton.click();

        // Step 17: Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:same_as_shipping"));
        sameAddressCheckbox.click();

        // Step 18: Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.cssSelector(".place-order-primary"));
        placeOrderButton.click();

        // Step 19: Verify the confirmation message
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement confirmationMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-title")));
        String confirmationMessage = confirmationMessageElement.getText();
        if (confirmationMessage.equals("Thank you for your purchase!")) {
            System.out.println("Confirmation message: " + confirmationMessage);
        } else {
            System.out.println("Confirmation message not found");
        }

        // Step 20: Click on the "Change" button
        WebElement changeButton = driver.findElement(By.cssSelector(".changeShippingMethod"));
        changeButton.click();

        // Step 21: Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
