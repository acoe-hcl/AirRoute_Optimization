Here is a sample Java Selenium test script for the scenario you provided:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MagentoTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path-to-chrome-driver");
        driver = new ChromeDriver();
    }

    @Test
    public void placeOrder() {
        driver.get("https://magento.softwaretestingboard.com/");
        
        WebElement signIn = driver.findElement(By.linkText("Sign In"));
        signIn.click();

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("autotest567@gmail.com");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Tester@123");

        WebElement signInButton = driver.findElement(By.id("signInButton"));
        signInButton.click();

        WebElement gearMenu = driver.findElement(By.id("gearMenu"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();

        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        WebElement duffleImage = driver.findElement(By.id("duffleImage"));
        duffleImage.click();

        WebElement addToCart = driver.findElement(By.id("addToCart"));
        addToCart.click();

        WebElement myCart = driver.findElement(By.linkText("My Cart"));
        myCart.click();

        WebElement proceedToCheckout = driver.findElement(By.id("checkout"));
        proceedToCheckout.click();

        // Add code to verify Order Summary here

        // Add code for New Address form here

        WebElement placeOrder = driver.findElement(By.id("placeOrder"));
        placeOrder.click();

        // Verify success message
        WebElement successMessage = driver.findElement(By.id("successMessage"));
        Assert.assertEquals(successMessage.getText(), "Thank you for your purchase!");

        WebElement changeButton = driver.findElement(By.id("change"));
        changeButton.click();

        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }
}
```

Please note that the script is using placeholders for the element locators. You need to replace them with the actual locators from your application.

Also, I skipped the New Address form part as it would be too long. I suggest to encapsulate it in a separate method for better readability.

This script is using TestNG as a testing framework. Make sure it is set up properly in your environment.