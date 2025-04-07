import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MagentoOrderTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testPlaceOrderForDrivenBackpack() throws InterruptedException {
        // Given User navigates to Magento
        driver.get("https://magento.softwaretestingboard.com/");
        
        // When I click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Assertions for Browser and Page Name can be added but not mandatory
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        // When I enter Email and Password
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        
        // And I click on the "Sign In" button
        driver.findElement(By.cssSelector(".action.login")).click();
        
        // When I mouse hover on the "Gear" menu (assuming it's a dropdown)
        WebElement gearMenu = driver.findElement(By.cssSelector(".gear-menu-selector")); // Adjust selector
        gearMenu.click();  // Simulating hover action
        
        // And I click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
        
        // When I click on the "Driven Backpack" image
        driver.findElement(By.xpath("//img[@alt='Driven Backpack']")).click();
        
        // When I click on the "Add to Cart" button
        driver.findElement(By.cssSelector(".action.tocar")).click();
        
        // And I click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        
        // And I verify that the "Order Summary" has "Driven Backpack" product
        String orderSummaryText = driver.findElement(By.cssSelector(".order-summary-selector")).getText(); // Adjust selector
        Assert.assertTrue(orderSummaryText.contains("Driven Backpack"), "Driven Backpack is not in the cart.");
        
        // And I click on the "Proceed to Checkout" button
        driver.findElement(By.cssSelector(".checkout-button-selector")).click(); // Adjust selector
        
        // When I click on the "New Address" button
        driver.findElement(By.cssSelector(".new-address-button-selector")).click(); // Adjust selector
        
        // Filling in the address fields
        driver.findElement(By.name("street[0]")).sendKeys("4 South Street");
        driver.findElement(By.name("city")).sendKeys("Texas");
        
        // And I select "Texas" from the "State/Province" dropdown
        driver.findElement(By.name("region_id")).sendKeys("Texas"); // Adjust for actual dropdown interaction

        driver.findElement(By.name("postcode")).sendKeys("77567");
        driver.findElement(By.name("telephone")).sendKeys("3456788765");
        
        // And I click on the "Ship Here" button
        driver.findElement(By.cssSelector(".ship-here-selector")).click(); // Adjust selector
        
        // And I select the "Fixed" radio button
        driver.findElement(By.cssSelector(".shipping-method-fixed")).click(); // Adjust selector
        
        // And I click on the "Next" button
        driver.findElement(By.cssSelector(".next-button-selector")).click(); // Adjust selector
        
        // And I select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.cssSelector(".billing-same-as-shipping")).click(); // Adjust selector
        
        // And I click on the "Place Order" button
        driver.findElement(By.cssSelector(".place-order-button")).click(); // Adjust selector

        // And I verify the message "Thank you for your purchase!"
        String confirmationMessage = driver.findElement(By.cssSelector(".confirmation-message-selector")).getText(); // Adjust selector
        Assert.assertEquals(confirmationMessage, "Thank you for your purchase!");
        
        // When I click on the "Change" button
        driver.findElement(By.cssSelector(".change-button-selector")).click(); // Adjust selector
        
        // And I click on the "Signout" link
        driver.findElement(By.linkText("Sign Out")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
