import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PlaceOrderTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void placeOrderDrivenBackpack() {
        driver.get("https://magento.softwaretestingboard.com/");
        driver.findElement(By.linkText("Sign In")).click();

        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();

        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.id("ui-id-6"));
        actions.moveToElement(gearMenu).perform();

        driver.findElement(By.linkText("Bags")).click();
        driver.findElement(By.xpath("//a[@title='Driven Backpack']")).click();
        driver.findElement(By.id("product-addtocart-button")).click();
        driver.findElement(By.linkText("shopping cart")).click();

        WebElement orderSummary = driver.findElement(By.cssSelector(".minicart-items .product-item-name"));
        Assert.assertTrue(orderSummary.getText().contains("Driven Backpack"));

        driver.findElement(By.id("top-cart-btn-checkout")).click();
        driver.findElement(By.cssSelector(".new-address-popup-link")).click();
        driver.findElement(By.name("street[0]")).sendKeys("4 South Street");
        driver.findElement(By.name("city")).sendKeys("Texas");

        Select stateDropdown = new Select(driver.findElement(By.name("region_id")));
        stateDropdown.selectByVisibleText("Texas");

        driver.findElement(By.name("postcode")).sendKeys("77567");
        driver.findElement(By.name("telephone")).sendKeys("3456788765");
        driver.findElement(By.cssSelector(".form-create-address .action-save")).click();
        
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        driver.findElement(By.cssSelector(".button.continue")).click();
        driver.findElement(By.id("billing-address-same-as-shipping-create")).click();
        driver.findElement(By.cssSelector(".button-checkout span span")).click();

        WebElement confirmationMessage = driver.findElement(By.cssSelector(".checkout-success"));
        Assert.assertTrue(confirmationMessage.getText().contains("Thank you for your purchase!"));

        driver.findElement(By.linkText("Change")).click();
        driver.findElement(By.linkText("Sign Out")).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
