Here's a simplified version of how the Java Selenium test script might look like. Please note that this script assumes that all the necessary WebDriver setup and tear down is already done.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MagentoOrderTest {

    private WebDriver driver;

    public void placeOrderTest() {
        driver.get("https://magento.softwaretestingboard.com/");

        driver.findElement(By.linkText("Sign In")).click();

        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();

        Actions action = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.id("gear"));
        action.moveToElement(gearMenu).perform();

        driver.findElement(By.linkText("Bags")).click();
        driver.findElement(By.linkText("Overnight Duffle")).click();

        driver.findElement(By.id("product-addtocart-button")).click();
        driver.findElement(By.linkText("My Cart")).click();
        driver.findElement(By.id("proceed-to-checkout")).click();

        driver.findElement(By.id("new-address")).click();
        driver.findElement(By.id("street")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");

        Select stateDropdown = new Select(driver.findElement(By.id("region_id")));
        stateDropdown.selectByVisibleText("Texas");

        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        driver.findElement(By.id("ship-here")).click();
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        driver.findElement(By.id("next")).click();
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        driver.findElement(By.id("place-order")).click();

        String thankYouMessage = driver.findElement(By.id("order-success")).getText();
        assert(thankYouMessage.contains("Thank you for your purchase!"));

        driver.findElement(By.id("change")).click();
        driver.findElement(By.linkText("Signout")).click();
    }
}
```

Note: Actual id or class names of the web elements may vary. You may need to inspect the web page to get the correct id or class names. Also, it's always a good practice to add wait statements or assertions to verify that the page or elements have loaded before interacting with them.