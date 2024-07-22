Certainly! Here is a sample Selenium Java automation test script that covers all the steps and test validations mentioned in the given feature file:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        driver.manage().window().build().setName("Customer Login");

        // Set the page name as "Customer Login"
        driver.getTitle().thenAccept(title -> driver.manage().window().build().setName(title));

        // Enter email and password in the respective fields
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.className("gear"));
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[@class='action showcart']"));
        myCartLink.click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//tbody[@id='checkout-cart-summary-tbody']"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Driven Backpack")) {
            System.out.println("Order Summary is having Driven Backpack product.");
        } else {
            System.out.println("Order Summary is not having Driven Backpack product.");
        }

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();

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

        WebElement postalCodeField = driver.findElement(By.id("postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneField = driver.findElement(By.id("telephone"));
        phoneField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.cssSelector(".field.address.shipping-method-item input[type='radio'][value='flatrate_Flatrate']"));
        shipHereButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.xpath("//input[@id='billing:use_for_shipping_yes']"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verify the message "Thank you for your purchase!"
        WebElement confirmationMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
        String confirmationMessageText = confirmationMessage.getText();
        if (confirmationMessageText.equals("Thank you for your purchase!")) {
            System.out.println("Order placed successfully.");
        } else {
            System.out.println("Order placement failed.");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.xpath("//a[text()='Sign Out']"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Please make sure to replace "path/to/chromedriver" with the actual path to the ChromeDriver executable in your local machine.

Hope this helps! Let me know if you need any further assistance.