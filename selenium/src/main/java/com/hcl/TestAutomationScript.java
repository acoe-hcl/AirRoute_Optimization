import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class TestAutomationScript {
    public static void main(String[] args) {
        // Set Browser name as Home Page
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String browserName = "Home Page";
        String pageName = "Home Page";

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on Sign In link
        driver.findElement(By.linkText("Sign In")).click();
        browserName = "Customer Login";
        pageName = "Customer Login";

        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on Sign In Button
        driver.findElement(By.id("send2")).click();
        browserName = "Home Page";
        pageName = "Home Page";

        // Mouse hover on Gear Menu
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).build().perform();
        driver.findElement(By.linkText("Bags")).click();
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        // Click on Overnight Duffle Image
        driver.findElement(By.xpath("//a[contains(text(),'Overnight Duffle')]")).click();
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";

        // Click on Add to cart button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on Shopping Cart link
        driver.findElement(By.linkText("Shopping Cart")).click();

        // Verify that Shopping cart is having Overnight Duffle product
        WebElement product = driver.findElement(By.xpath("//td[@class='product-name']//a[contains(text(),'Overnight Duffle')]"));
        if(product.isDisplayed()) {
            System.out.println("Product is added to the shopping cart");
        } else {
            System.out.println("Product is not added to the shopping cart");
        }

        // Click on Proceed to Checkout button
        driver.findElement(By.id("top-cart-btn-checkout")).click();
        browserName = "Checkout";
        pageName = "Checkout";

        // Click on New Address Button
        driver.findElement(By.id("billing-new-address-form")).click();

        // Enter address details
        driver.findElement(By.id("billing:street1")).sendKeys("4 South Street");
        driver.findElement(By.id("billing:city")).sendKeys("Texas");
        driver.findElement(By.id("billing:region_id")).sendKeys("Texas");
        driver.findElement(By.id("billing:postcode")).sendKeys("77567");
        driver.findElement(By.id("billing:telephone")).sendKeys("3456788765");

        // Click on Ship here button
        driver.findElement(By.id("billing-buttons-container")).click();

        // Select Fixed Radio button
        driver.findElement(By.id("s_method_freeshipping_freeshipping")).click();

        // Click on Next button
        driver.findElement(By.id("shipping-method-buttons-container")).click();

        // Select My billing and shipping address are the same Checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // Click on Place Order button
        driver.findElement(By.id("review-buttons-container")).click();
        browserName = "Success Page";
        pageName = "Success Page";

        // Verify the message Thank you for your purchase!
        WebElement successMessage = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]"));
        if(successMessage.isDisplayed()) {
            System.out.println("Purchase success message is displayed");
        } else {
            System.out.println("Purchase success message is not displayed");
        }

        // Click on Change Button
        driver.findElement(By.id("change_btn")).click();

        // Click on Signout Link
        driver.findElement(By.linkText("Signout")).click();
    }
}