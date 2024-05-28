Here is a Selenium Java automation test script file that covers all the steps and test validations in the given scenario:

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class MagentoOrderPlacementTest {

    public static void main(String[] args) {
        // Set up Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize Chrome driver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Step 1 - Go to home page
        driver.get("https://magento.softwaretestingboard.com/");
        System.out.println("Step 1 - Go to home page");

        // Step 2 - Click on Sign In link
        driver.findElement(By.linkText("Sign In")).click();
        System.out.println("Step 2 - Click on Sign In link");

        // Step 3 - Login as customer
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();
        System.out.println("Step 3 - Login as customer");

        // Step 4 - Go to Bags category
        driver.findElement(By.linkText("Gear")).click();
        driver.findElement(By.linkText("Bags")).click();
        System.out.println("Step 4 - Go to Bags category");

        // Step 5 - Select Overnight Duffle product
        driver.findElement(By.linkText("Overnight Duffle")).click();
        System.out.println("Step 5 - Select Overnight Duffle product");

        // Step 6 - Add product to cart
        driver.findElement(By.xpath("//button[contains(text(),'Add to Cart')]")).click();
        System.out.println("Step 6 - Add product to cart");

        // Step 7 - Go to My Cart
        driver.findElement(By.id("minicart-icon")).click();
        driver.findElement(By.xpath("//span[contains(text(),'My Cart')]")).click();
        System.out.println("Step 7 - Go to My Cart");

        // Step 8 - Proceed to Checkout
        driver.findElement(By.xpath("//span[contains(text(),'Proceed to Checkout')]")).click();
        System.out.println("Step 8 - Proceed to Checkout");

        // Step 9 - Verify Order Summary
        String orderSummary = driver.findElement(By.cssSelector(".summary-item-title > a")).getText();
        if (orderSummary.equals("Overnight Duffle")) {
            System.out.println("Step 9 - Order Summary has 'Overnight Duffle' product");
        } else {
            System.out.println("Step 9 - Order Summary does not have 'Overnight Duffle' product");
        }

        // Step 10 - Enter shipping address
        driver.findElement(By.cssSelector(".form-shipping-address button.new-address")).click();
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        driver.findElement(By.cssSelector("input[id^='s_method']")).click();
        driver.findElement(By.cssSelector(".button[name='next']")).click();
        driver.findElement(By.id("billing-address-same-as-shipping-yes")).click();
        System.out.println("Step 10 - Enter shipping address");

        // Step 11 - Place Order
        driver.findElement(By.cssSelector("button[title^='Place Order']")).click();
        System.out.println("Step 11 - Place Order");

        // Step 12 - Verify Success Page
        String successMessage = driver.findElement(By.cssSelector(".success-msg")).getText();
        if (successMessage.contains("Thank you for your purchase!")) {
            System.out.println("Step 12 - Success Page is displayed with 'Thank you for your purchase!' message");
        } else {
            System.out.println("Step 12 - Success Page is not displayed with 'Thank you for your purchase!' message");
        }

        // Step 13 - Sign out
        driver.findElement(By.cssSelector(".header [title='Change']")).click();
        driver.findElement(By.cssSelector(".header [title='Sign Out']")).click();
        System.out.println("Step 13 - Sign out");

        // Close the browser
        driver.quit();
    }
}
```

Note: Please make sure to replace "path/to/chromedriver" with the actual path to your Chrome driver executable. Also, ensure that you have the necessary Selenium WebDriver and Chrome driver dependencies added to your project.