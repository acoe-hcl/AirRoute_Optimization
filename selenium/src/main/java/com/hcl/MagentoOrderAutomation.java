As a professional code assistant named Guru, I can generate a Selenium Java automation test script for the given scenario. Here is the test script:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        
        // Set browser properties
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        try {
            // Step 1: Launch the application
            driver.get("https://magento.softwaretestingboard.com/");
            
            // Step 2: Click on the "Sign In" link
            driver.findElement(By.linkText("Sign In")).click();
            
            // Step 3: Enter email and password
            driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
            driver.findElement(By.id("pass")).sendKeys("Tester@123");
            
            // Step 4: Click on the "Sign In" button
            driver.findElement(By.id("send2")).click();
            
            // Step 5: Mouse hover on the "Gear" menu
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Gear')]"))).perform();
            
            // Step 6: Click on the "Bags" link
            driver.findElement(By.linkText("Bags")).click();
            
            // Step 7: Click on the "Overnight Duffle" image
            driver.findElement(By.xpath("//img[contains(@alt,'Overnight Duffle')]")).click();
            
            // Step 8: Click on the "Add to Cart" button
            driver.findElement(By.cssSelector("button[title='Add to Cart']")).click();
            
            // Step 9: Click on the "My Cart" link
            driver.findElement(By.linkText("My Cart")).click();
            
            // Step 10: Click on the "Proceed to Checkout" button
            driver.findElement(By.xpath("//span[text()='Proceed to Checkout']")).click();
            
            // Step 11: Verify "Order Summary" has "Overnight Duffle" product
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.textToBe(By.xpath("//strong[contains(text(),'Order Summary')]/following-sibling::span"), "Overnight Duffle"));
            
            // Step 12: Click on the "New Address" button
            driver.findElement(By.xpath("//span[text()='New Address']")).click();
            
            // Step 13: Enter address details
            driver.findElement(By.id("street_1")).sendKeys("4 South Street");
            driver.findElement(By.id("city")).sendKeys("Texas");
            driver.findElement(By.id("region_id")).sendKeys("Texas");
            driver.findElement(By.id("postcode")).sendKeys("77567");
            driver.findElement(By.id("telephone")).sendKeys("3456788765");
            
            // Step 14: Click on the "Ship Here" button
            driver.findElement(By.xpath("//span[text()='Ship Here']")).click();
            
            // Step 15: Select the "Fixed" radio button
            driver.findElement(By.id("s_method_flatrate_flatrate")).click();
            
            // Step 16: Click on the "Next" button
            driver.findElement(By.xpath("//span[text()='Next']")).click();
            
            // Step 17: Select the "My billing and shipping address are the same" checkbox
            driver.findElement(By.id("billing:use_for_shipping_yes")).click();
            
            // Step 18: Click on the "Place Order" button
            driver.findElement(By.xpath("//span[text()='Place Order']")).click();
            
            // Step 19: Verify success page
            wait.until(ExpectedConditions.titleContains("Success Page"));
            wait.until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Thank you for your purchase!']"), "Thank you for your purchase!"));
            
            // Step 20: Click on the "Change" button
            driver.findElement(By.xpath("//span[text()='Change']")).click();
            
            // Step 21: Click on the "Signout" link
            driver.findElement(By.linkText("Signout")).click();
            
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
```

Please note that you need to replace "path/to/chromedriver.exe" with the actual path to the ChromeDriver executable on your system. Also, make sure you have the Selenium WebDriver libraries added to your project.