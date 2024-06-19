import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Guru {

    private static WebDriver driver;

    public static void main(String[] args) {
        String email = "autotest567@gmail.com";
        String password = "Tester@123";
        String street = "4 South Street";
        String city = "Texas";
        String state = "Texas";
        String zipCode = "77567";
        String phoneNumber = "3456788765";

        setup();

        // Scenario: Place an order for Overnight Duffle product
        login(email, password);
        navigateToBags();
        selectProduct();
        addToCart();
        proceedToCheckout();
        verifyProductInSummary();
        enterShippingAddress(street, city, state, zipCode, phoneNumber);
        selectShippingMethod();
        enterBillingAddress();
        placeOrder();
        verifySuccessMessage();
        changeShippingAddress();
        signOut();

        teardown();
    }

    public static void setup() {
        // Set up the browser and launch the application
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://magento.softwaretestingboard.com/");
    }

    public static void login(String email, String password) {
        // Click on the "Sign In" link on the home page
        driver.findElement(By.linkText("Sign In")).click();

        // Enter email and password in the login form
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(password);

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
    }

    public static void navigateToBags() {
        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
    }

    public static void selectProduct() {
        // Click on the "Overnight Duffle" image
        driver.findElement(By.linkText("Overnight Duffle")).click();
    }

    public static void addToCart() {
        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();
    }

    public static void proceedToCheckout() {
        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.cssSelector(".action.primary.checkout")).click();
    }

    public static void verifyProductInSummary() {
        // Verify that the "Order Summary" is having "Overnight Duffle" product
        String productName = driver.findElement(By.cssSelector(".product-item-name")).getText();
        if (productName.equals("Overnight Duffle")) {
            System.out.println("Product in Order Summary is correct.");
        } else {
            System.out.println("Product in Order Summary is incorrect.");
        }
    }

    public static void enterShippingAddress(String street, String city, String state, String zipCode, String phoneNumber) {
        // Click on the "New Address" button
        driver.findElement(By.cssSelector("[title='New Address']")).click();

        // Enter the shipping address details
        driver.findElement(By.id("street_1")).sendKeys(street);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("region_id")).sendKeys(state);
        driver.findElement(By.id("zip")).sendKeys(zipCode);
        driver.findElement(By.id("telephone")).sendKeys(phoneNumber);

        // Click on the "Ship Here" button
        driver.findElement(By.cssSelector("[data-role='opc-shipping-address-container'] .action.save.primary")).click();
    }

    public static void selectShippingMethod() {
        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Click on the "Next" button
        driver.findElement(By.cssSelector("[data-role='opc-shipping_method-container'] .action-primary")).click();
    }

    public static void enterBillingAddress() {
        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing-address-same-as-shipping")).click();

        // Click on the "Next" button
        driver.findElement(By.cssSelector("[data-role='opc-billing_method-container'] .action-primary")).click();
    }

    public static void placeOrder() {
        // Click on the "Place Order" button
        driver.findElement(By.cssSelector("[data-role='opc-cc-number-sidebar'] .action.primary.checkout")).click();
    }

    public static void verifySuccessMessage() {
        // Verify the message "Thank you for your purchase!"
        String successMessage = driver.findElement(By.cssSelector(".message-success")).getText();
        if (successMessage.equals("Thank you for your purchase!")) {
            System.out.println("Order placed successfully.");
        } else {
            System.out.println("Order placement failed.");
        }
    }

    public static void changeShippingAddress() {
        // Click on the "Change" button
        driver.findElement(By.cssSelector("[data-role='opc-shipping-address-container'] .action.edit")).click();
    }

    public static void signOut() {
        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
    }

    public static void teardown() {
        // Close the browser
        driver.quit();
    }
}