import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.junit.Assert;

public class MagentoOrderPlace {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);
        
        driver.get("https://magento.softwaretestingboard.com/");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();
        
        action.moveToElement(driver.findElement(By.linkText("Gear"))).perform();
        driver.findElement(By.linkText("Bags")).click();
        driver.findElement(By.xpath("//img[contains(@alt,'Overnight Duffle')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'My Cart')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Proceed to Checkout')]")).click();
        
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Overnight Duffle')]")).isDisplayed());

        driver.findElement(By.xpath("//span[contains(text(),'New Address')]")).click();
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        driver.findElement(By.xpath("//span[contains(text(),'Ship Here')]")).click();
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();
        
        Assert.assertEquals("Thank you for your purchase!", driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")).getText());

        driver.findElement(By.xpath("//span[contains(text(),'Change')]")).click();
        driver.findElement(By.linkText("Signout")).click();
        
        driver.quit();
    }
}