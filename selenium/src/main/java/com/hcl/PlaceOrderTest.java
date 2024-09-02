Sure! Here is a sample Test Automation Script for the given scenario:

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
        
        // Set the system property for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver/chromedriver.exe");
        
        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Set the browser name as "Customer Login"
        driver.manage().window().setName("Customer Login");
        
        // Set the page name as "Customer Login"
        driver.findElement(By.tagName("body")).setAttribute("id", "Customer Login");
        
        // Enter email in the "Email" field
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        
        // Enter password in the "Password" field
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
        
        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.className("gears-menu"));
        actions.moveToElement(gearMenu).perform();
        
        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
        
        // Click on the "Driven Backpack" image
        driver.findElement(By.className("product-image")).click();
        
        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();
        
        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        
        // Verify that the "Order Summary" is having "Driven Backpack" product
        String orderSummary = driver.findElement(By.className("product-name")).getText();
        if(orderSummary.contains("Driven Backpack")) {
            System.out.println("Order Summary contains Driven Backpack product");
        } else {
            System.out.println("Order Summary does not contain Driven Backpack product");
        }
        
        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();
        
        // Click on the "New Address" button
        driver.findElement(By.id("billing-new-address-form")).findElement(By.tagName("button")).click();
        
        // Enter address details
        driver.findElement(By.id("billing:street1")).sendKeys("4 South Street");
        driver.findElement(By.id("billing:city")).sendKeys("Texas");
        driver.findElement(By.id("billing:region_id")).sendKeys("Texas");
        driver.findElement(By.id("billing:postcode")).sendKeys("77567");
        driver.findElement(By.id("billing:telephone")).sendKeys("3456788765");
        
        // Click on the "Ship Here" button
        driver.findElement(By.id("billing-buttons-container")).findElement(By.tagName("button")).click();
        
        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        if(!fixedRadioButton.isSelected()) {
            fixedRadioButton.click();
        }
        
        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-buttons-container")).findElement(By.tagName("button")).click();
        
        // Select the "My billing and shipping address are the same" checkbox
        WebElement billingShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        if(!billingShippingCheckbox.isSelected()) {
            billingShippingCheckbox.click();
        }
        
        // Click on the "Place Order" button
        driver.findElement(By.id("review-buttons-container")).findElement(By.tagName("button")).click();
        
        // Verify the message "Thank you for your purchase!"
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success-msg")));
        
        if(successMessage.getText().contains("Thank you for your purchase!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Failed to place the order");
        }
        
        // Click on the "Change" button
        driver.findElement(By.className("back-link")).click();
        
        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
        
        // Close the browser
        driver.quit();
    }

}
```

Note: Please make sure to update the `path_to_chromedriver` in the script with the actual path to the ChromeDriver executable on your system. Also, make sure to have the Selenium WebDriver and ChromeDriver libraries added to your project.