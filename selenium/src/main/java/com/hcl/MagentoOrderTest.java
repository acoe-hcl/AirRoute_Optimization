Here is a Java Selenium test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MagentoOrderTest {
    public static void main(String[] args) {
        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Step 1: Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Step 2: Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Step 3: Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Step 4: Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Step 5: Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[@class='toggle-subnav']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Step 6: Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.xpath("//a[text()='Bags']"));
        bagsLink.click();

        // Step 7: Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
        overnightDuffleImage.click();

        // Step 8: Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();

        // Step 9: Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[@class='action showcart']"));
        myCartLink.click();

        // Step 10: Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Step 11: Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[text()='Order Summary']"));
        String productText = orderSummary.getText();
        if (productText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }

        // Step 12: Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();

        // Step 13: Enter address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        Select stateDropdown = new Select(driver.findElement(By.id("region_id")));
        stateDropdown.selectByVisibleText("Texas");

        WebElement zipField = driver.findElement(By.id("postcode"));
        zipField.sendKeys("77567");

        WebElement phoneField = driver.findElement(By.id("telephone"));
        phoneField.sendKeys("3456788765");

        // Step 14: Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Step 15: Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Step 16: Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Step 17: Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Step 18: Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Step 19: Verify the success message
        WebElement successMessage = driver.findElement(By.xpath("//span[contains(text(),'Thank you for your purchase!')]"));
        String messageText = successMessage.getText();
        if (messageText.contains("Thank you for your purchase!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Failed to place the order");
        }

        // Step 20: Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // Step 21: Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.xpath("//a[text()='Sign Out']"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Please note that you need to replace "path/to/chromedriver" with the actual path to the chromedriver executable on your machine. Also, make sure you have the necessary Selenium dependencies added to your project.