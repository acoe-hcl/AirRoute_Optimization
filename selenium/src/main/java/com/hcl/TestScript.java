Sure! Here's the Selenium Java automation test script file for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScript {
    public static void main(String[] args) {
        // Set browser name as "Home Page"
        String browserName = "Home Page";
        
        // Set page name as "Home Page"
        String pageName = "Home Page";
        
        // Set WebDriver path for Chrome
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        
        // Launch the application
        WebDriver driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Set browser name as "Customer Login"
        browserName = "Customer Login";
        
        // Set page name as "Customer Login"
        pageName = "Customer Login";
        
        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
        
        // Set browser name as "Home Page"
        browserName = "Home Page";
        
        // Set page name as "Home Page"
        pageName = "Home Page";
        
        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        actions.moveToElement(gearMenu).build().perform();
        
        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
        
        // Set browser name as "Bags - Gear"
        browserName = "Bags - Gear";
        
        // Set page name as "Bags - Gear"
        pageName = "Bags - Gear";
        
        // Click on the "Overnight Duffle" image
        driver.findElement(By.linkText("Overnight Duffle")).click();
        
        // Set browser name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        
        // Set page name as "Overnight Duffle"
        pageName = "Overnight Duffle";
        
        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();
        
        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        
        // Click on the "Proceed to Checkout" button
        driver.findElement(By.className("button-checkout")).click();
        
        // Set browser name as "Checkout"
        browserName = "Checkout";
        
        // Set page name as "Checkout"
        pageName = "Checkout";
        
        // Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.className("product-name")).getText();
        if (orderSummary.contains("Overnight Duffle")) {
            System.out.println("Order Summary has Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not have Overnight Duffle product");
        }
        
        // Click on the "New Address" button
        driver.findElement(By.id("billing-new-address-form")).click();
        
        // Enter address details
        driver.findElement(By.id("billing:street1")).sendKeys("4 South Street");
        driver.findElement(By.id("billing:city")).sendKeys("Texas");
        driver.findElement(By.id("billing:region")).sendKeys("Texas");
        driver.findElement(By.id("billing:postcode")).sendKeys("77567");
        driver.findElement(By.id("billing:telephone")).sendKeys("3456788765");
        
        // Click on the "Ship Here" button
        driver.findElement(By.id("billing-save")).click();
        
        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        
        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-buttons-container")).click();
        
        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();
        
        // Click on the "Place Order" button
        driver.findElement(By.id("review-buttons-container")).click();
        
        // Set browser name as "Success Page"
        browserName = "Success Page";
        
        // Set page name as "Success Page"
        pageName = "Success Page";
        
        // Verify the message "Thank you for your purchase!"
        String successMessage = driver.findElement(By.className("success-msg")).getText();
        if (successMessage.equals("Thank you for your purchase!")) {
            System.out.println("Success message is displayed");
        } else {
            System.out.println("Success message is not displayed");
        }
        
        // Click on the "Change" button
        driver.findElement(By.linkText("Change")).click();
        
        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
        
        // Quit the browser
        driver.quit();
    }
}
```

Make sure to replace `path/to/chromedriver` with the actual path to your ChromeDriver executable.