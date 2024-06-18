Here is the Selenium Java automation test script file for the given scenario:

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class MagentoOrderAutomation {

    public static void main(String[] args) {
        // Set browser property
        System.setProperty("webdriver.chrome.driver", "path/to/chrome/driver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        // Navigate to Magento website
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Click on Sign In link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");
        
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");
        
        // Click on Sign In button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        
        // Mouse hover on Gear menu and click on Bags link
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // Click on Overnight Duffle image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
        overnightDuffleImage.click();
        
        // Click on Add to Cart button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();
        
        // Click on My Cart link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        // Click on Proceed to Checkout button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();
        
        // Verify Order Summary has Overnight Duffle product
        WebElement orderSummary = driver.findElement(By.xpath("//div[@class='product-name']/a[text()='Overnight Duffle']"));
        Assert.assertEquals("Overnight Duffle", orderSummary.getText());
        
        // Click on New Address button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();
        
        // Enter address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");
        
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");
        
        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");
        
        WebElement postalCodeField = driver.findElement(By.id("postcode"));
        postalCodeField.sendKeys("77567");
        
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");
        
        // Click on Ship Here button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();
        
        // Select Fixed radio button and click on Next button
        WebElement fixedRadioButton = driver.findElement(By.id("p_method_fixed"));
        fixedRadioButton.click();
        
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();
        
        // Select My billing and shipping address are the same checkbox
        WebElement billingShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        billingShippingCheckbox.click();
        
        // Click on Place Order button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();
        
        // Verify success page message
        WebElement successMessage = driver.findElement(By.xpath("//*[contains(text(), 'Thank you for your purchase!')]"));
        Assert.assertTrue(successMessage.isDisplayed());
        
        // Click on Change button and Signout link
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();
        
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
        
        // Close the browser
        driver.quit();
    }
}
```

Note: Replace "path/to/chrome/driver" in line 14 with the actual path to the Chrome driver executable on your machine.