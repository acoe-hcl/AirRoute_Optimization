Here is the Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestScript {
    
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions actions = new Actions(driver);
        
        // Given I have set the browser name as "Home Page"
        // And I have set the page name as "Home Page"
        
        // When I launch the application "[https://magento.softwaretestingboard.com/|https://magento.softwaretestingboard.com/]"
        driver.get("https://magento.softwaretestingboard.com/");
        
        // And I click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Then I should set the browser name as "Customer Login"
        // And I should set the page name as "Customer Login"
        
        // When I enter "[autotest567@gmail.com|mailto:autotest567@gmail.com]" in the "Email" field
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        
        // And I enter "Tester@123" in the "Password" field as secure text
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        
        // And I click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
        
        // Then I should set the browser name as "Home Page"
        // And I should set the page name as "Home Page"
        
        // When I mouse hover on the "Gear" menu
        actions.moveToElement(driver.findElement(By.linkText("Gear"))).perform();
        
        // And I click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
        
        // Then I should set the browser name as "Bags - Gear"
        // And I should set the page name as "Bags - Gear"
        
        // When I click on the "Overnight Duffle" image
        driver.findElement(By.linkText("Overnight Duffle")).click();
        
        // Then I should set the browser name as "Overnight Duffle"
        // And I should set the page name as "Overnight Duffle"
        
        // When I click on the "Add to Cart" button
        driver.findElement(By.cssSelector(".add-to-cart-buttons .btn-cart")).click();
        
        // And I click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        
        // And I click on the "Proceed to Checkout" button
        driver.findElement(By.cssSelector(".btn-proceed-checkout>a")).click();
        
        // Then I should set the browser name as "Checkout"
        // And I should set the page name as "Checkout"
        
        // And I verify that the "Order Summary" is having "Overnight Duffle" product
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector(".checkout-cart-item .product-name")), "Overnight Duffle"));
        
        // When I click on the "New Address" button
        driver.findElement(By.id("address_new")).click();
        
        // And I enter "4 South Street" in the "Street" field
        driver.findElement(By.id("shipping:street1")).sendKeys("4 South Street");
        
        // And I enter "Texas" in the "City" field
        driver.findElement(By.id("shipping:city")).sendKeys("Texas");
        
        // And I select "Texas" from the "State/Province" dropdown
        driver.findElement(By.id("shipping:region_id")).sendKeys("Texas");
        
        // And I enter "77567" in the "Zip/Postal Code" field
        driver.findElement(By.id("shipping:postcode")).sendKeys("77567");
        
        // And I enter "3456788765" in the "Phone Number" field
        driver.findElement(By.id("shipping:telephone")).sendKeys("3456788765");
        
        // And I click on the "Ship Here" button
        driver.findElement(By.id("shipping:save_in_address_book")).click();
        
        // And I select the "Fixed" radio button
        driver.findElement(By.id("s_method_fixed_fixed")).click();
        
        // And I click on the "Next" button
        driver.findElement(By.cssSelector(".button[title='Next']")).click();
        
        // And I select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();
        
        // And I click on the "Place Order" button
        driver.findElement(By.id("submit_order_button")).click();
        
        // Then I should set the browser name as "Success Page"
        // And I should set the page name as "Success Page"
        
        // And I verify the message "Thank you for your purchase!"
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector(".checkout-success .order-title")), "Thank you for your purchase!"));
        
        // When I click on the "Change" button
        driver.findElement(By.cssSelector("#header .btn.usertoggle")).click();
        
        // And I click on the "Signout" link
        driver.findElement(By.linkText("Sign Out")).click();
        
        driver.quit();
    }
}
```

Please note that this code assumes you have set up Selenium WebDriver and ChromeDriver correctly in your testing environment.