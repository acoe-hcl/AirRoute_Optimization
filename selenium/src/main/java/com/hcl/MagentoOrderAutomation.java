import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Set browser property
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Launch the browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Set the browser and page names
        String browserName = "Home Page";
        String pageName = "Home Page";

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Update the browser and page names
        browserName = "Customer Login";
        pageName = "Customer Login";

        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Update the browser and page names
        browserName = "Home Page";
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.cssSelector(".nav-3 .has-children>a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();

        // Click on the "Bags" link
        driver.findElement(By.cssSelector(".nav-3-1-1>a")).click();

        // Update the browser and page names
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        // Click on the "Overnight Duffle" image
        driver.findElement(By.cssSelector(".product-item:nth-child(1) img")).click();

        // Update the browser and page names
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";

        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on the "My Cart" link
        driver.findElement(By.cssSelector(".minicart-wrapper")).click();

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.cssSelector(".action.checkout")).click();

        // Update the browser and page names
        browserName = "Checkout";
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.cssSelector(".cart-summary-item-name>a")).getText();
        if (orderSummary.equals("Overnight Duffle")) {
            System.out.println("Order Summary validation passed.");
        } else {
            System.out.println("Order Summary validation failed.");
        }

        // Click on the "New Address" button
        driver.findElement(By.id("shipping-new-address-form-popups")).click();

        // Enter shipping address details
        driver.findElement(By.id("co-shipping-form-street1")).sendKeys("4 South Street");
        driver.findElement(By.id("co-shipping-form-city")).sendKeys("Texas");
        driver.findElement(By.id("co-shipping-form-region_id")).sendKeys("Texas");
        driver.findElement(By.id("co-shipping-form-postcode")).sendKeys("77567");
        driver.findElement(By.id("co-shipping-form-telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.name("shipping-address-item")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Click on the "Next" button
        driver.findElement(By.cssSelector(".button.action.continue")).click();

        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:same_as_shipping")).click();

        // Click on the "Place Order" button
        driver.findElement(By.cssSelector(".button.action.primary.checkout")).click();

        // Update the browser and page names
        browserName = "Success Page";
        pageName = "Success Page";

        // Verify the success message
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sub-title")));
        String message = successMessage.getText();
        if (message.equals("Thank you for your purchase!")) {
            System.out.println("Success message validation passed.");
        } else {
            System.out.println("Success message validation failed.");
        }

        // Click on the "Change" button
        driver.findElement(By.cssSelector(".order-details-ship .action")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Sign Out")).click();

        // Quit the browser
        driver.quit();
    }
}
```

Make sure to replace "path/to/chromedriver" with the actual path to your ChromeDriver executable. You can also customize the validation messages according to your requirements.