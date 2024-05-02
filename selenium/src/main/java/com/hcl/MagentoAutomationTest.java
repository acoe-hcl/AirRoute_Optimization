import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoAutomationTest {

    public static void main(String[] args) {

        // Set the browser name and page name
        String browserName = "Home Page";
        String pageName = "Home Page";
        
        // Set the browser path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        
        // Launch the application
        WebDriver driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name and page name
        browserName = "Customer Login";
        pageName = "Customer Login";

        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");
        
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Set the browser name and page name
        browserName = "Home Page";
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).build().perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Set the browser name and page name
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        // Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
        overnightDuffleImage.click();

        // Set the browser name and page name
        browserName = "Overnight Duffle";
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

        // Set the browser name and page name
        browserName = "Checkout";
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//div[contains(text(), 'Order Summary')]/following-sibling::div[contains(text(), 'Overnight Duffle')]"));
        if (orderSummary.isDisplayed()) {
            System.out.println("Order Summary is displaying Overnight Duffle product");
        } else {
            System.out.println("Order Summary is not displaying Overnight Duffle product");
        }

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("opc-new-address"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        Select stateSelectBox = new Select(stateDropdown);
        stateSelectBox.selectByVisibleText("Texas");

        WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
        zipCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("shipping-method-buttons-container"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement billingAndShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        billingAndShippingCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.id("review-buttons-container"));
        placeOrderButton.click();

        // Set the browser name and page name
        browserName = "Success Page";
        pageName = "Success Page";

        // Verify the success message
        WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(), 'Thank you for your purchase!')]"));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message is displayed: Thank you for your purchase!");
        } else {
            System.out.println("Success message is not displayed");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.id("change_cart_btn"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}