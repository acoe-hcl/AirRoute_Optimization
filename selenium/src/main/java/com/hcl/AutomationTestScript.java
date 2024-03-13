import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationTestScript {
    public static void main(String[] args) {
        // Set browser name and page name to "Home Page"
        String browserName = "Home Page";
        String pageName = "Home Page";
        
        // Launch the application
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Set browser name and page name to "Customer Login"
        browserName = "Customer Login";
        pageName = "Customer Login";
        
        // Enter email and password
        driver.findElement(By.name("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        driver.findElement(By.xpath("//button[contains(text(), 'Sign In')]")).click();
        
        // Set browser name and page name to "Home Page"
        browserName = "Home Page";
        pageName = "Home Page";
        
        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//span[contains(text(), 'Gear')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        
        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
        
        // Set browser name and page name to "Bags - Gear"
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";
        
        // Click on the "Overnight Duffle" image
        driver.findElement(By.xpath("//img[contains(@alt, 'Overnight Duffle')]")).click();
        
        // Set browser name and page name to "Overnight Duffle"
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";
        
        // Click on the "Add to Cart" button
        driver.findElement(By.xpath("//button[contains(text(), 'Add to Cart')]")).click();
        
        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        
        // Click on the "Proceed to Checkout" button
        driver.findElement(By.xpath("//button[contains(text(), 'Proceed to Checkout')]")).click();
        
        // Set browser name and page name to "Checkout"
        browserName = "Checkout";
        pageName = "Checkout";
        
        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.id("order-summary"));
        String orderSummaryText = orderSummary.getText();
        String expectedProduct = "Overnight Duffle";
        if (orderSummaryText.contains(expectedProduct)) {
            System.out.println("Order Summary contains " + expectedProduct + " product");
        } else {
            System.out.println("Order Summary does not contain " + expectedProduct + " product");
        }
        
        // Click on the "New Address" button
        driver.findElement(By.xpath("//button[contains(text(), 'New Address')]")).click();
        
        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("postcode")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        
        // Click on the "Ship Here" button
        driver.findElement(By.xpath("//button[contains(text(), 'Ship Here')]")).click();
        
        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_fixed_fixed")).click();
        
        // Click on the "Next" button
        driver.findElement(By.xpath("//button[contains(text(), 'Next')]")).click();
        
        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing-address-same-as-shipping")).click();
        
        // Click on the "Place Order" button
        driver.findElement(By.xpath("//button[contains(text(), 'Place Order')]")).click();
        
        // Set browser name and page name to "Success Page"
        browserName = "Success Page";
        pageName = "Success Page";
        
        // Verify the message "Thank you for your purchase!"
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Thank you for your purchase!')]")));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message displayed: " + successMessage.getText());
        } else {
            System.out.println("Success message not displayed");
        }
        
        // Click on the "Change" button
        driver.findElement(By.xpath("//button[contains(text(), 'Change')]")).click();
        
        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
        
        // Close the browser
        driver.quit();
    }
}
```

Please note that you need to replace "path/to/chromedriver" with the actual path to your ChromeDriver executable.

This test script covers all the steps and test validations mentioned in the feature file. Let me know if you need any further assistance!