Here is the selenium script based on the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class MagentoOrder {
  public static void main(String[] args) {
    System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

    WebDriver driver = new ChromeDriver();
    Actions action = new Actions(driver);
    driver.get("https://magento.softwaretestingboard.com/");

    WebElement signInLink = driver.findElement(By.linkText("Sign In"));
    signInLink.click();

    WebElement emailInput = driver.findElement(By.id("email"));
    emailInput.sendKeys("autotest567@gmail.com");

    WebElement passwordInput = driver.findElement(By.id("pass"));
    passwordInput.sendKeys("Tester@123");

    WebElement signInBtn = driver.findElement(By.id("send2"));
    signInBtn.click();

    WebElement gearMenu = driver.findElement(By.linkText("Gear"));
    action.moveToElement(gearMenu).perform();

    WebElement bagsLink = driver.findElement(By.linkText("Bags"));
    bagsLink.click();

    WebElement productImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
    productImage.click();

    WebElement addToCartBtn = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
    addToCartBtn.click();

    WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
    myCartLink.click();

    WebElement checkoutBtn = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
    checkoutBtn.click();

    WebElement newAddressBtn = driver.findElement(By.id("addNewAddress"));
    newAddressBtn.click();

    WebElement streetInput = driver.findElement(By.id("street"));
    streetInput.sendKeys("4 South Street");

    WebElement cityInput = driver.findElement(By.id("city"));
    cityInput.sendKeys("Texas");

    Select stateDropdown = new Select(driver.findElement(By.id("region_id")));
    stateDropdown.selectByVisibleText("Texas");

    WebElement zipInput = driver.findElement(By.id("postcode"));
    zipInput.sendKeys("77567");

    WebElement phoneInput = driver.findElement(By.id("telephone"));
    phoneInput.sendKeys("3456788765");

    WebElement shipHereBtn = driver.findElement(By.id("shipHere"));
    shipHereBtn.click();

    WebElement fixedRadioBtn = driver.findElement(By.id("s_method_flatrate_flatrate"));
    fixedRadioBtn.click();

    WebElement nextBtn = driver.findElement(By.xpath("//button[@title='Next']"));
    nextBtn.click();

    WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
    sameAddressCheckbox.click();

    WebElement placeOrderBtn = driver.findElement(By.xpath("//button[@title='Place Order']"));
    placeOrderBtn.click();

    String successMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']")).getText();
    Assert.assertEquals(successMessage, "Thank you for your purchase!");

    WebElement changeBtn = driver.findElement(By.xpath("//a[@title='Change']"));
    changeBtn.click();

    WebElement signOutLink = driver.findElement(By.linkText("Signout"));
    signOutLink.click();

    driver.quit();
  }
}
```
Make sure to replace `"/path/to/chromedriver"` with the actual path to your `chromedriver` executable.

Please note that the IDs, names, and XPaths used here are assumed based on common practices and may not work directly with your website. You need to inspect the elements on your webpage and replace them with actual values.