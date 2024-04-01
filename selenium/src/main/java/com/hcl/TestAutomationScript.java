import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAutomationScript {
    public static void main(String[] args) {
        // Set the browser name as "Home Page"
        String browserName = "Home Page";
        // Set the page name as "Home Page"
        String pageName = "Home Page";

        // Set the driver path for Chrome
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Launch the application
        String url = "https://magento.softwaretestingboard.com/";
        driver.get(url);

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        browserName = "Customer Login";
        // Set the page name as "Customer Login"
        pageName = "Customer Login";

        // Enter the email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Set the browser name as "Home Page"
        browserName = "Home Page";
        // Set the page name as "Home Page"
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//div[@class='gear-icon']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Set the browser name as "Bags - Gear"
        browserName = "Bags - Gear";
        // Set the page name as "Bags - Gear"
        pageName = "Bags - Gear";

        // Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
        overnightDuffleImage.click();

        // Set the browser name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        // Set the page name as "Overnight Duffle"
        pageName = "Overnight Duffle";

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();

        // Set the browser name as "Checkout"
        browserName = "Checkout";
        // Set the page name as "Checkout"
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        boolean isProductInOrderSummary = driver.getPageSource().contains("Order Summary") && driver.getPageSource().contains("Overnight Duffle");

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("shipping-address-add"));
        newAddressButton.click();

        // Enter the address details
        WebElement streetField = driver.findElement(By.id("shipping:street1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("shipping:city"));
        cityField.sendKeys("Texas");

        WebElement stateProvinceDropdown = driver.findElement(By.id("shipping:region_id"));
        stateProvinceDropdown.sendKeys("Texas");

        WebElement zipPostalCodeField = driver.findElement(By.id("shipping:postcode"));
        zipPostalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("shipping:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("shipping_address_submit"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']//button[@title='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameBillingShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameBillingShippingCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.id("review-buttons-container"));
        placeOrderButton.click();

        // Set the browser name as "Success Page"
        browserName = "Success Page";
        // Set the page name as "Success Page"
        pageName = "Success Page";

        // Verify the success message
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='page-title']")));
        boolean isSuccessful = successMessage.getText().equalsIgnoreCase("Thank you for your purchase!");

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.linkText("Change"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}