import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class PlaceOrderTest {
    
    WebDriver driver;
    
    @BeforeTest
    public void setupDriver() {
        // Set up driver configuration here (e.g. WebDriverManager)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
    
    @Test
    public void placeOrderTest() {
        driver.get("https://magento.softwaretestingboard.com/");
        
        // User navigates to the website
        // Given User navigates to "[https://magento.softwaretestingboard.com/|https://magento.softwaretestingboard.com/]"
        
        // User clicks on the "Sign In" link
        // When I click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Set the browser name as "Customer Login"
        // Then I should set the browser name as "Customer Login"
        driver.getTitle().equals("Customer Login");
        
        // Set the page name as "Customer Login"
        // And I should set the page name as "Customer Login"
        driver.findElement(By.cssSelector("input[name='page_name']")).sendKeys("Customer Login");
        
        // User enters email and password
        // When I enter "[testermail@gmail.com]" in the "Email" field
        // And I enter "Tester@123" in the "Password" field as secure text
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        
        // User clicks on the "Sign In" button
        // And I click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
        
        // User mouse hovers on the "Gear" menu and clicks on the "Bags" link
        // When I mouse hover on the "Gear" menu
        // And I click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-1-2.first.parent"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();
        driver.findElement(By.linkText("Bags")).click();
        
        // User clicks on the "Driven Backpack" image
        // When I click on the "Driven Backpack" image
        driver.findElement(By.linkText("Driven Backpack")).click();
        
        // User clicks on the "Add to Cart" button
        // When I click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();
        
        // User clicks on the "My Cart" link
        // And I click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        
        // Verify that the "Order Summary" is having "Driven Backpack" product
        // And I verify that the "Order Summary" is having "Driven Backpack" product
        String orderSummary = driver.findElement(By.cssSelector(".cart-totals > tbody > tr > td:nth-child(1)")).getText();
        Assert.assertEquals("Order Summary", orderSummary);
        
        // User clicks on the "Proceed to Checkout" button
        // And I click on the "Proceed to Checkout" button
        driver.findElement(By.cssSelector("button[onclick*='checkout']")).click();
        
        // User clicks on the "New Address" button
        // When I click on the "New Address" button
        driver.findElement(By.cssSelector("button[onclick*='newAddress']")).click();
        
        // User enters address information
        // And I enter "4 South Street" in the "Street" field
        // And I enter "Texas" in the "City" field
        // And I select "Texas" from the "State/Province" dropdown
        // And I enter "77567" in the "Zip/Postal Code" field
        // And I enter "3456788765" in the "Phone Number" field
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        Select selectState = new Select(stateDropdown);
        selectState.selectByVisibleText("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        
        // User clicks on the "Ship Here" button
        // And I click on the "Ship Here" button
        driver.findElement(By.cssSelector("button[onclick*='shipping[save]']")).click();
        
        // User selects the "Fixed" radio button
        // And I select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        if (!fixedRadioButton.isSelected()) {
            fixedRadioButton.click();
        }
        
        // User clicks on the "Next" button
        // And I click on the "Next" button
        driver.findElement(By.cssSelector("button[onclick*='shipping[save]']")).click();
        
        // User selects the "My billing and shipping address are the same" checkbox
        // And I select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        if (!sameAddressCheckbox.isSelected()) {
            sameAddressCheckbox.click();
        }
        
        // User clicks on the "Place Order" button
        // And I click on the "Place Order" button
        driver.findElement(By.cssSelector("button[onclick*='checkout[saveMethod]']")).click();
        
        // Verify the message "Thank you for your purchase!"
        // And I verify the message "Thank you for your purchase!"
        String successMessage = driver.findElement(By.className("success-msg")).getText();
        Assert.assertEquals("Thank you for your purchase!", successMessage);
        
        // User clicks on the "Change" button
        // When I click on the "Change" button
        driver.findElement(By.linkText("Change")).click();
        
        // User clicks on the "Signout" link
        // And I click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
    }
}
