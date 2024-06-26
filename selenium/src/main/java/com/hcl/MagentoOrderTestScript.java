
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderTestScript {

    public static void main(String[] args) {
        // Set the path of chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Set the browser name as "Customer Login"
        driver.executeScript("document.title = 'Customer Login'");

        // Set the page name as "Customer Login"
        driver.executeScript("window.localStorage.setItem('page_name', 'Customer Login')");

        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.xpath("//a[@title='Gear']"));
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Click on the "Driven Backpack" image
        driver.findElement(By.cssSelector("a[title='Driven Backpack']")).click();

        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.cssSelector("div.block-content div.cart-totals-table"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Driven Backpack")) {
            System.out.println("Order Summary contains 'Driven Backpack' product");
        }

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        // Click on the "New Address" button
        driver.findElement(By.cssSelector("button[data-role='add-new-address']")).click();

        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("postcode")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.id("shipping-save-address")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-button")).click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        if (!sameAddressCheckbox.isSelected()) {
            sameAddressCheckbox.click();
        }

        // Click on the "Place Order" button
        driver.findElement(By.id("review-place-order")).click();

        // Verify the message "Thank you for your purchase!"
        String confirmationMessage = driver.findElement(By.cssSelector("h1")).getText();
        if (confirmationMessage.contains("Thank you for your purchase!")) {
            System.out.println("Order placed successfully");
        }

        // Click on the "Change" button
        driver.findElement(By.className("change-option")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();
    }
}
```

Note: Make sure to replace "path/to/chromedriver" with the actual path to the chromedriver executable on your machine.

I hope this helps! Let me know if you need any further assistance.