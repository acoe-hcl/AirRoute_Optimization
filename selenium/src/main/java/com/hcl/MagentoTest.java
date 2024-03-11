
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoTest {

    @Test
    public void orderOvernightDuffle() {
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        driver.manage().window().maximize();

        driver.get("https://magento.softwaretestingboard.com/");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();
        
        WebElement gearMenu = driver.findElement(By.xpath("//a[@title='Gear']"));
        actions.moveToElement(gearMenu).perform();
        driver.findElement(By.linkText("Bags")).click();
        driver.findElement(By.xpath("//img[@alt='Overnight Duffle']")).click();
        driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
        driver.findElement(By.linkText("My Cart")).click();
        driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
        
        WebElement orderSummary = driver.findElement(By.id("order-summary"));
        Assert.assertTrue(orderSummary.getText().contains("Overnight Duffle"));
        
        driver.findElement(By.xpath("//button[@title='New Address']")).click();
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        new Select(driver.findElement(By.id("region_id"))).selectByVisibleText("Texas");
        driver.findElement(By.id("postcode")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        driver.findElement(By.xpath("//button[@title='Ship Here']")).click();
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        driver.findElement(By.xpath("//button[@title='Next']")).click();
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();
        driver.findElement(By.xpath("//button[@title='Place Order']")).click();
        
        WebElement successMsg = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
        Assert.assertTrue(successMsg.isDisplayed());
        
        driver.findElement(By.xpath("//a[@title='Change']")).click();
        driver.findElement(By.linkText("Signout")).click();
        
        driver.quit();
    }
}
```

Please ensure to replace the XPath/ID/Name with the actual values. This script is based on assumptions about the identifiers of the elements. You need to inspect the elements in the actual application to find the correct identifiers. Also, make sure to include the necessary libraries in your project.