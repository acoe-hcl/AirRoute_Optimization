Here's a sample Java Selenium test script for the given scenario.

Please note that the identifiers (id, class, xpath, etc.) for the web elements are assumed based on the name provided in the scenario. You would need to replace them with the actual identifiers from the application's HTML code.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    WebDriver driver;

    @Test
    public void placeOrder() {
        System.setProperty("webdriver.chrome.driver", "path to chromedriver");
        driver = new ChromeDriver();

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Sign in
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();

        // Navigate to Bags
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.id("gear"));
        actions.moveToElement(gearMenu).perform();
        driver.findElement(By.linkText("Bags")).click();

        // Select product
        driver.findElement(By.id("duffle")).click();
        driver.findElement(By.id("add-to-cart")).click();

        // Checkout
        driver.findElement(By.linkText("My Cart")).click();
        driver.findElement(By.id("proceed-to-checkout")).click();
        Assert.assertTrue(driver.findElement(By.id("order-summary")).getText().contains("Overnight Duffle"));

        // Enter address and place order
        driver.findElement(By.id("new-address")).click();
        driver.findElement(By.id("street")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        new Select(driver.findElement(By.id("state"))).selectByVisibleText("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("phone")).sendKeys("3456788765");
        driver.findElement(By.id("ship-here")).click();
        driver.findElement(By.id("fixed")).click();
        driver.findElement(By.id("next")).click();
        driver.findElement(By.id("billing-shipping-same")).click();
        driver.findElement(By.id("place-order")).click();

        // Verify success message
        Assert.assertEquals(driver.findElement(By.id("success-message")).getText(), "Thank you for your purchase!");

        // Sign out
        driver.findElement(By.linkText("Change")).click();
        driver.findElement(By.linkText("Signout")).click();

        driver.quit();
    }
}
```
This script uses TestNG for assertions, so make sure to include TestNG library if you want to run this script.