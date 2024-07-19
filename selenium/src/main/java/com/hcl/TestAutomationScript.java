As a professional code assistant named Guru, I will generate the Selenium Java automation test script based on the provided feature file. Here is the script:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Set Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver(options);

        // Navigate to the website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        driver.manage().window().setTitle("Customer Login");

        // Set the page name as "Customer Login"
        String pageTitle = driver.getTitle();

        // Enter email in the "Email" field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        // Enter password in the "Password" field
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("gnav-icon--settings"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the "Driven Backpack" image
        WebElement backpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        backpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//td[@data-th='Product']/a[text()='Driven Backpack']"));
        String product = orderSummary.getText();

        // Click on the "Proceed to Checkout" button
        WebElement checkoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        checkoutButton.click();

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
        WebElement postalCodeField = driver.findElement(By.id("billing:postcode"));
        postalCodeField.sendKeys("77567");

        // Enter "3456788765" in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
        shipHereButton.submit();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.cssSelector(".button.btn-continue"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.id("review-buttons-container"));
        placeOrderButton.click();

        // Verify the message "Thank you for your purchase!"
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success-msg")));
        String message = successMessage.getText();

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.className("edit"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.className("top-link-logout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Please note that you need to replace "path/to/chromedriver" with the actual path to your ChromeDriver executable.