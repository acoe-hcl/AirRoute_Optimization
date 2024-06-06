import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderAutomation {
    public static void main(String[] args) throws InterruptedException {
        // Set the path to the chrome driver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Instantiate ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Set the browser name as "Home Page"
        String browserName = "Home Page";

        // Set the page name as "Home Page"
        String pageName = "Home Page";

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        browserName = "Customer Login";

        // Set the page name as "Customer Login"
        pageName = "Customer Login";

        // Enter email in the "Email" field
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("autotest567@gmail.com");

        // Enter password in the "Password" field
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Set the browser name as "Home Page"
        browserName = "Home Page";

        // Set the page name as "Home Page"
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
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
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Set the browser name as "Checkout"
        browserName = "Checkout";

        // Set the page name as "Checkout"
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[text()='Order Summary']"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product.");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product.");
        }

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing-new-address-form"));
        newAddressButton.click();

        // Enter "4 South Street" in the "Street" field
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.sendKeys("4 South Street");

        // Enter "Texas" in the "City" field
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");

        // Select "Texas" from the "State/Province" dropdown
        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        stateDropdown.sendKeys("Texas");

        // Enter "77567" in the "Zip/Postal Code" field
        WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
        zipCodeField.sendKeys("77567");

        // Enter "3456788765" in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadio = driver.findElement(By.id("p_method_flatrate_flatrate"));
        fixedRadio.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        if (!sameAddressCheckbox.isSelected()) {
            sameAddressCheckbox.click();
        }

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Set the browser name as "Success Page"
        browserName = "Success Page";

        // Set the page name as "Success Page"
        pageName = "Success Page";

        // Verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.xpath("//span[text()='Thank you for your purchase!']"));
        String successMessageText = successMessage.getText();
        if (successMessageText.equals("Thank you for your purchase!")) {
            System.out.println("Purchase was successful.");
        } else {
            System.out.println("Purchase was not successful.");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.linkText("Change"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signOutLink = driver.findElement(By.linkText("Signout"));
        signOutLink.click();

        // Close the browser
        driver.quit();
    }
}