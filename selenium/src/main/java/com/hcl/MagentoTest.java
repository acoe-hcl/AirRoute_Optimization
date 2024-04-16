Here is a sample Java Selenium test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoTest {
    public static void main(String[] args) {
        // Set the path to chromedriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[@title='Gear']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.xpath("//a[@title='Bags']"));
        bagsLink.click();

        // Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@title='Overnight Duffle']"));
        overnightDuffleImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Verify the "Order Summary" contains "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//td[contains(text(), 'Overnight Duffle')]"));

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        stateDropdown.sendKeys("Texas");

        WebElement zipCodeField = driver.findElement(By.id("postcode"));
        zipCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement billingShippingCheckbox = driver.findElement(By.xpath("//input[@id='billing:use_for_shipping_yes']"));
        billingShippingCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verify the browser name is "Success Page"
        // Verify the page name is "Success Page"
        // Verify the message "Thank you for your purchase!"

        // Click on the "Change" button
        // Click on the "Signout" link

        // Close the browser
        driver.quit();
    }
}
```

Please note that you will need to replace "path/to/chromedriver" with the actual path to the chromedriver executable on your machine. Also, this script assumes that you have the necessary Selenium dependencies set up in your project.