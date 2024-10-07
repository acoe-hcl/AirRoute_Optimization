import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    private WebDriver driver;
    
    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void placeOrderForDrivenBackpackProduct() {
        // Given User navigates to "https://magento.softwaretestingboard.com/"
        driver.get("https://magento.softwaretestingboard.com/");
        
        // When I click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Then I should set the browser name as "Customer Login"
        String expectedBrowserName = "Customer Login";
        String actualBrowserName = driver.getTitle();
        Assert.assertEquals(actualBrowserName, expectedBrowserName);
        
        // And I should set the page name as "Customer Login"
        String expectedPageName = "Customer Login";
        String actualPageName = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(actualPageName, expectedPageName);
        
        // When I enter "testermail@gmail.com" in the "Email" field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys("testermail@gmail.com");
        
        // And I enter "Tester@123" in the "Password" field as secure text
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.clear();
        passwordField.sendKeys("Tester@123");
        
        // And I click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
        
        // When I mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("gear"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();
        
        // And I click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
        
        // When I click on the "Driven Backpack" image
        driver.findElement(By.linkText("Driven Backpack")).click();
        
        // When I click on the "Add to Cart" button
        driver.findElement(By.id("add-to-cart-button")).click();
        
        // And I click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        
        // And I verify that the "Order Summary" is having "Driven Backpack" product
        String expectedOrderSummary = "Driven Backpack";
        String actualOrderSummary = driver.findElement(By.className("product-cart-name")).getText();
        Assert.assertEquals(actualOrderSummary, expectedOrderSummary);
        
        // And I click on the "Proceed to Checkout" button
        driver.findElement(By.id("onepage-checkout")).click();
        
        // When I click on the "New Address" button
        driver.findElement(By.id("billing:use_for_shipping_no")).click();
        
        // And I enter "4 South Street" in the "Street" field
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.clear();
        streetField.sendKeys("4 South Street");
        
        // And I enter "Texas" in the "City" field
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.clear();
        cityField.sendKeys("Texas");
        
        // And I select "Texas" from the "State/Province" dropdown
        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");
        
        // And I enter "77567" in the "Zip/Postal Code" field
        WebElement zipField = driver.findElement(By.id("billing:postcode"));
        zipField.clear();
        zipField.sendKeys("77567");
        
        // And I enter "3456788765" in the "Phone Number" field
        WebElement phoneField = driver.findElement(By.id("billing:telephone"));
        phoneField.clear();
        phoneField.sendKeys("3456788765");
        
        // And I click on the "Ship Here" button
        driver.findElement(By.id("billing-buttons-container")).click();
        
        // And I select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        
        // And I click on the "Next" button
        driver.findElement(By.id("shipping-method-buttons-container")).click();
        
        // And I select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();
        
        // And I click on the "Place Order" button
        driver.findElement(By.id("payment-buttons-container")).click();
        
        // And I verify the message "Thank you for your purchase!"
        String expectedThankYouMsg = "Thank you for your purchase!";
        String actualThankYouMsg = driver.findElement(By.cssSelector(".col-right > p")).getText();
        Assert.assertEquals(actualThankYouMsg, expectedThankYouMsg);
        
        // When I click on the "Change" button
        driver.findElement(By.className("change")).click();
        
        // And I click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
        
        // Assertions for other test combinations can be added here
    }
}
