Here's the generated Selenium java automation test script file for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderTest {
    public static void main(String[] args) {
        // Set the browser driver path before running the script
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the given URL
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        String browserName = "Customer Login";

        // Set the page name as "Customer Login"
        String pageName = "Customer Login";

        // Enter email
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        // Enter password
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
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//strong[text()='Driven Backpack']"));
        String productName = orderSummary.getText();
        if (productName.equals("Driven Backpack")) {
            System.out.println("Order Summary is having Driven Backpack product");
        } else {
            System.out.println("Order Summary is not having Driven Backpack product");
        }

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();

        // Enter street
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        // Enter city
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        // Select state/province
        WebElement stateProvinceDropdown = driver.findElement(By.id("region_id"));
        stateProvinceDropdown.sendKeys("Texas");

        // Enter zip/postal code
        WebElement zipPostalCodeField = driver.findElement(By.id("postcode"));
        zipPostalCodeField.sendKeys("77567");

        // Enter phone number
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
        String message = successMessage.getText();
        if (message.equals("Thank you for your purchase!")) {
            System.out.println("Message is displayed: " + message);
        } else {
            System.out.println("Message is not displayed");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

You can run this script with the Selenium WebDriver and ChromeDriver to automate the given scenario in Java.