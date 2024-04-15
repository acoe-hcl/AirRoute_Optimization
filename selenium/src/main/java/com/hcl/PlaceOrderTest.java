Here is the Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlaceOrderTest {
    public static void main(String[] args) {
        // Set the browser property to Chrome
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Set the browser name and page name as "Home Page"
        String browserName = "Home Page";
        String pageName = "Home Page";

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Set the browser name and page name as "Customer Login"
        browserName = "Customer Login";
        pageName = "Customer Login";

        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Set the browser name and page name as "Home Page"
        browserName = "Home Page";
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu and click on the "Bags" link
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.xpath("//a[text()='Gear']"));
        actions.moveToElement(gearMenu).perform();
        driver.findElement(By.linkText("Bags")).click();

        // Set the browser name and page name as "Bags - Gear"
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        // Click on the "Overnight Duffle" image
        driver.findElement(By.xpath("//a[contains(text(),'Overnight Duffle')]")).click();

        // Set the browser name and page name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";

        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        // Set the browser name and page name as "Checkout"
        browserName = "Checkout";
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//h3[contains(text(),'Order Summary')]/following-sibling::p[contains(text(),'Overnight Duffle')]"));
        if(orderSummary.getText().contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }

        // Click on the "New Address" button
        driver.findElement(By.id("sn-checkout-new-address")).click();

        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.id("shipping-save-address")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-buttons-container")).click();

        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // Click on the "Place Order" button
        driver.findElement(By.id("review-buttons-container")).click();

        // Set the browser name and page name as "Success Page"
        browserName = "Success Page";
        pageName = "Success Page";

        // Verify the success message
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Thank you for your purchase!')]")));
        if(successMessage.getText().contains("Thank you for your purchase!")) {
            System.out.println("Order has been placed successfully");
        } else {
            System.out.println("Order placement failed");
        }

        // Click on the "Change" button and then click on the "Signout" link
        driver.findElement(By.id("customer-change-button")).click();
        driver.findElement(By.linkText("Signout")).click();

        // Quit the browser
        driver.quit();
    }
}
```

Make sure to replace "path/to/chromedriver" with the actual path to the ChromeDriver executable file in your system. Also, consider adding necessary Selenium dependencies to your project.