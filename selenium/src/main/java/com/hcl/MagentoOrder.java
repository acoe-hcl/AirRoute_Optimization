```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class MagentoOrder {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path_to_your_chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        
        driver.get("https://magento.softwaretestingboard.com/");
        
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");
        
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");
        
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        actions.moveToElement(gearMenu).perform();
        
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[contains(@src, 'overnight-duffle')]"));
        overnightDuffleImage.click();
        
        WebElement addToCartButton = driver.findElement(By.xpath("//button[contains(@title, 'Add to Cart')]"));
        addToCartButton.click();
        
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(@title, 'Proceed to Checkout')]"));
        checkoutButton.click();
        
        WebElement orderSummary = driver.findElement(By.xpath("//div[@id='checkout-summary']"));
        Assert.assertTrue(orderSummary.getText().contains("Overnight Duffle"));
        
        WebElement newAddressButton = driver.findElement(By.xpath("//button[contains(@title, 'New Address')]"));
        newAddressButton.click();
        
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");
        
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");
        
        Select stateDropdown = new Select(driver.findElement(By.id("region_id")));
        stateDropdown.selectByVisibleText("Texas");
        
        WebElement zipCodeField = driver.findElement(By.id("zip"));
        zipCodeField.sendKeys("77567");
        
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");
        
        WebElement shipHereButton = driver.findElement(By.xpath("//button[contains(@title, 'Ship Here')]"));
        shipHereButton.click();
        
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();
        
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(@title, 'Next')]"));
        nextButton.click();
        
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();
        
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[contains(@title, 'Place Order')]"));
        placeOrderButton.click();
        
        WebElement successMessage = driver.findElement(By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]"));
        Assert.assertTrue(successMessage.isDisplayed());
        
        WebElement changeButton = driver.findElement(By.xpath("//button[contains(@title, 'Change')]"));
        changeButton.click();
        
        WebElement signOutLink = driver.findElement(By.linkText("Sign Out"));
        signOutLink.click();
        
        driver.quit();
    }
}
```

Note: You need to replace the "path_to_your_chromedriver.exe" with the actual path where your ChromeDriver is located. Also, the ids and xpaths used above are assumed based on the usual conventions and may not work with the actual Magento website. Please replace them with the actual ids and xpaths as per your Magento application.