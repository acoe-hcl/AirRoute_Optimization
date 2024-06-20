Here is the Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MagentoOrderAutomation {

    public static void main(String[] args) {
        // Set the browser driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);

        try {
            // Launch the application
            driver.get("https://magento.softwaretestingboard.com/");

            // Click on the "Sign In" link
            WebElement signInLink = driver.findElement(By.linkText("Sign In"));
            signInLink.click();

            // Enter email and password
            WebElement emailField = driver.findElement(By.name("login[username]"));
            emailField.sendKeys("autotest567@gmail.com");

            WebElement passwordField = driver.findElement(By.name("login[password]"));
            passwordField.sendKeys("Tester@123");

            // Click on the "Sign In" button
            WebElement signInButton = driver.findElement(By.xpath("//button[@title='Sign In']"));
            signInButton.click();

            // Mouse hover on the "Gear" menu
            WebElement gearMenu = driver.findElement(By.xpath("//a[@class='ui-button ui-widget ui-state-default ui-corner-all js-menu-trigger gearopen']"));
            Actions action = new Actions(driver);
            action.moveToElement(gearMenu).perform();

            // Click on the "Bags" link
            WebElement bagsLink = driver.findElement(By.linkText("Bags"));
            bagsLink.click();

            // Click on the "Overnight Duffle" image
            WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
            overnightDuffleImage.click();

            // Click on the "Add to Cart" button
            WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
            addToCartButton.click();

            // Click on the "My Cart" link
            WebElement myCartLink = driver.findElement(By.xpath("//a[@class='action showcart']"));
            myCartLink.click();

            // Click on the "Proceed to Checkout" button
            WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//span[text()='Proceed to Checkout']"));
            proceedToCheckoutButton.click();

            // Verify the "Order Summary" has "Overnight Duffle" product
            WebElement orderSummary = driver.findElement(By.xpath("//table[@id='checkout-summary-table']//a[text()='Overnight Duffle']"));
            assert orderSummary.isDisplayed();

            // Click on the "New Address" button
            WebElement newAddressButton = driver.findElement(By.xpath("//a[text()='New Address']"));
            newAddressButton.click();

            // Enter address details
            WebElement streetField = driver.findElement(By.id("street_1"));
            streetField.sendKeys("4 South Street");

            WebElement cityField = driver.findElement(By.id("city"));
            cityField.sendKeys("Texas");

            WebElement stateDropdown = driver.findElement(By.id("region_id"));
            Select stateSelect = new Select(stateDropdown);
            stateSelect.selectByVisibleText("Texas");

            WebElement zipField = driver.findElement(By.id("postcode"));
            zipField.sendKeys("77567");

            WebElement phoneField = driver.findElement(By.id("telephone"));
            phoneField.sendKeys("3456788765");

            // Click on the "Ship Here" button
            WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
            shipHereButton.click();

            // Select the "Fixed" radio button
            WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@value='fixed']"));
            fixedRadioButton.click();

            // Click on the "Next" button
            WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
            nextButton.click();

            // Select the "My billing and shipping address are the same" checkbox
            WebElement sameAddressCheckbox = driver.findElement(By.id("shipping:same_as_billing"));
            sameAddressCheckbox.click();

            // Click on the "Place Order" button
            WebElement placeOrderButton = driver.findElement(By.id("submit-order"));
            placeOrderButton.click();

            // Verify the success message
            WebElement successMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
            assert successMessage.isDisplayed();

            // Click on the "Change" button
            WebElement changeButton = driver.findElement(By.xpath("//a[@class='change-address']"));
            changeButton.click();

            // Click on the "Signout" link
            WebElement signoutLink = driver.findElement(By.linkText("Signout"));
            signoutLink.click();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
```

Please note that you will need to replace "path/to/chromedriver" with the actual path to your ChromeDriver executable. Additionally, you may need to import the required Selenium libraries, such as `org.openqa.selenium.By`, `org.openqa.selenium.WebDriver`, `org.openqa.selenium.WebElement`, `org.openqa.selenium.chrome.ChromeDriver`, `org.openqa.selenium.chrome.ChromeOptions`, `org.openqa.selenium.interactions.Actions`, and `org.openqa.selenium.support.ui.Select`.