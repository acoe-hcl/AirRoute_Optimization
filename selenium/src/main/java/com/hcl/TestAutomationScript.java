import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Given I have set the browser name as "Home Page"
        String browserName = "Home Page";

        // Given I have set the page name as "Home Page"
        String pageName = "Home Page";

        // When I launch the application "https://magento.softwaretestingboard.com/"
        driver.get("https://magento.softwaretestingboard.com/");

        // And I click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Then I should set the browser name as "Customer Login"
        browserName = "Customer Login";
        
        // And I should set the page name as "Customer Login"
        pageName = "Customer Login";

        // When I enter "autotest567@gmail.com" in the "Email" field
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");

        // And I enter "Tester@123" in the "Password" field as secure text
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // And I click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Then I should set the browser name as "Home Page"
        browserName = "Home Page";
        
        // And I should set the page name as "Home Page"
        pageName = "Home Page";
        
        // When I mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.xpath("//span[text()='Gear']"));
        actions.moveToElement(gearMenu).perform();

        // And I click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Then I should set the browser name as "Bags - Gear"
        browserName = "Bags - Gear";
        
        // And I should set the page name as "Bags - Gear"
        pageName = "Bags - Gear";
        
        // When I click on the "Overnight Duffle" image
        driver.findElement(By.xpath("//img[contains(@alt, 'Overnight Duffle')]")).click();

        // Then I should set the browser name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        
        // And I should set the page name as "Overnight Duffle"
        pageName = "Overnight Duffle";
        
        // When I click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();
        
        // And I click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        
        // And I click on the "Proceed to Checkout" button
        driver.findElement(By.cssSelector(".primary.action.checkout")).click();
        
        // Then I should set the browser name as "Checkout"
        browserName = "Checkout";
        
        // And I should set the page name as "Checkout"
        pageName = "Checkout";
        
        // And I verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.cssSelector(".checkout-title h2")).getText();
        if (orderSummary.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }
        
        // When I click on the "New Address" button
        driver.findElement(By.linkText("New Address")).click();
        
        // And I enter "4 South Street" in the "Street" field
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        
        // And I enter "Texas" in the "City" field
        driver.findElement(By.id("city")).sendKeys("Texas");
        
        // And I select "Texas" from the "State/Province" dropdown
        Select stateDropdown = new Select(driver.findElement(By.id("region_id")));
        stateDropdown.selectByVisibleText("Texas");
        
        // And I enter "77567" in the "Zip/Postal Code" field
        driver.findElement(By.id("postcode")).sendKeys("77567");
        
        // And I enter "3456788765" in the "Phone Number" field
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        
        // And I click on the "Ship Here" button
        driver.findElement(By.id("shipping-save-address")).click();
        
        // And I select the "Fixed" radio button
        driver.findElement(By.cssSelector("#s_method_flatrate_flatrate")).click();
        
        // And I click on the "Next" button
        driver.findElement(By.cssSelector(".button.continue.primary")).click();
        
        // And I select the "My billing and shipping address are the same" checkbox
        WebElement billingSameAsShipping = driver.findElement(By.id("billing:use_for_shipping_yes"));
        if (!billingSameAsShipping.isSelected()) {
            billingSameAsShipping.click();
        }
        
        // And I click on the "Place Order" button
        driver.findElement(By.cssSelector(".button.action.checkout.primary")).click();
        
        // Then I should set the browser name as "Success Page"
        browserName = "Success Page";
        
        // And I should set the page name as "Success Page"
        pageName = "Success Page";
        
        // And I verify the message "Thank you for your purchase!"
        String successMessage = driver.findElement(By.cssSelector(".message-success")).getText();
        if (successMessage.contains("Thank you for your purchase!")) {
            System.out.println("Success message displayed: Thank you for your purchase!");
        } else {
            System.out.println("Failed to display success message");
        }
        
        // When I click on the "Change" button
        driver.findElement(By.cssSelector(".primary.action.change-link span")).click();
        
        // And I click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
        
        driver.quit();
    }
}