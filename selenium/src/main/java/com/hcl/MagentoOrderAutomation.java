Sure, here is the Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomation {

    public static void main(String[] args) {
        // Set the browser driver path
        System.setProperty("webdriver.chrome.driver", "path_to_chrome_driver");

        // Instantiate ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Define explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.manage().window().maximize();

            // Navigate to the Magento application
            driver.get("https://magento.softwaretestingboard.com/");

            // Click on the "Sign In" link
            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign In")));
            WebElement signInLink = driver.findElement(By.linkText("Sign In"));
            signInLink.click();

            // Enter email and password in the login form
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
            WebElement emailField = driver.findElement(By.id("email"));
            emailField.sendKeys("autotest567@gmail.com");

            WebElement passwordField = driver.findElement(By.id("pass"));
            passwordField.sendKeys("Tester@123");

            // Click on the "Sign In" button
            WebElement signInButton = driver.findElement(By.id("send2"));
            signInButton.click();

            // Mouse hover on the "Gear" menu
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("gear")));
            WebElement gearMenu = driver.findElement(By.className("gear"));
            Actions actions = new Actions(driver);
            actions.moveToElement(gearMenu).perform();

            // Click on the "Bags" link
            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Bags")));
            WebElement bagsLink = driver.findElement(By.linkText("Bags"));
            bagsLink.click();

            // Click on the "Overnight Duffle" image
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Overnight Duffle']")));
            WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
            overnightDuffleImage.click();

            // Click on the "Add to Cart" button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-addtocart-button")));
            WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
            addToCartButton.click();

            // Click on the "My Cart" link
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("minicart-content-wrapper")));
            WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
            myCartLink.click();

            // Click on the "Proceed to Checkout" button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("top-cart-btn-checkout")));
            WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
            proceedToCheckoutButton.click();

            // Verify that the "Order Summary" is having "Overnight Duffle" product
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Order Summary')]/following-sibling::ul/li")));
            WebElement orderSummaryProduct = driver.findElement(By.xpath("//h2[contains(text(),'Order Summary')]/following-sibling::ul/li"));
            String productName = orderSummaryProduct.getText();
            if (productName.equals("Overnight Duffle")) {
                System.out.println("Order Summary contains Overnight Duffle product");
            } else {
                System.out.println("Order Summary does not contain Overnight Duffle product");
            }

            // Click on the "New Address" button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing-new-address-form")));
            WebElement newAddressButton = driver.findElement(By.id("billing-new-address-form"));
            newAddressButton.click();

            // Enter address details
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing:street1")));
            WebElement streetField = driver.findElement(By.id("billing:street1"));
            streetField.sendKeys("4 South Street");

            WebElement cityField = driver.findElement(By.id("billing:city"));
            cityField.sendKeys("Texas");

            WebElement stateField = driver.findElement(By.id("billing:region_id"));
            stateField.sendKeys("Texas");

            WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
            zipCodeField.sendKeys("77567");

            WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
            phoneNumberField.sendKeys("3456788765");

            // Click on the "Ship Here" button
            WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
            shipHereButton.click();

            // Select the "Fixed" radio button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("s_method_flatrate_flatrate")));
            WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
            fixedRadioButton.click();

            // Click on the "Next" button
            WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
            nextButton.click();

            // Select the "My billing and shipping address are the same" checkbox
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing:use_for_shipping_yes")));
            WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
            sameAddressCheckbox.click();

            // Click on the "Place Order" button
            WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
            placeOrderButton.click();

            // Verify the success message
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Success')]")));
            WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Success')]"));
            if (successMessage.getText().equals("Thank you for your purchase!")) {
                System.out.println("Order placed successfully");
            } else {
                System.out.println("Order placement failed");
            }

            // Click on the "Change" button
            WebElement changeButton = driver.findElement(By.xpath("//a[contains(text(),'Change')]"));
            changeButton.click();

            // Click on the "Signout" link
            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Signout")));
            WebElement signoutLink = driver.findElement(By.linkText("Signout"));
            signoutLink.click();
        } finally {
            // Quit the browser
            driver.quit();
        }
    }
}
```

Please note that you need to replace "path_to_chrome_driver" with the actual path to your ChromeDriver executable.