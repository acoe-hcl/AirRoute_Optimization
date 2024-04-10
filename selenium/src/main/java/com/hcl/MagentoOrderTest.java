Sure, here is the Java Selenium test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderTest {
    public static void main(String[] args) {
        // Set the browser property
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        
        // Launch the browser
        WebDriver driver = new ChromeDriver();
        
        // Set the browser name and page name as "Home Page"
        String browserName = "Home Page";
        String pageName = "Home Page";
        
        // Open the Magento application
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Set the browser name and page name as "Customer Login"
        browserName = "Customer Login";
        pageName = "Customer Login";
        
        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");
        
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        
        // Set the browser name and page name as "Home Page"
        browserName = "Home Page";
        pageName = "Home Page";
        
        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//span[text()='Gear']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();
        
        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // Set the browser name and page name as "Bags - Gear"
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";
        
        // Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
        overnightDuffleImage.click();
        
        // Set the browser name and page name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";
        
        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();
        
        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();
        
        // Set the browser name and page name as "Checkout"
        browserName = "Checkout";
        pageName = "Checkout";
        
        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[text()='Order Summary']"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }
        
        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing-new-address-form"));
        newAddressButton.click();
        
        // Enter address details
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.sendKeys("4 South Street");
        
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");
        
        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        stateDropdown.sendKeys("Texas");
        
        WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
        zipCodeField.sendKeys("77567");
        
        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");
        
        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();
        
        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();
        
        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();
        
        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();
        
        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();
        
        // Set the browser name and page name as "Success Page"
        browserName = "Success Page";
        pageName = "Success Page";
        
        // Verify the success message
        WebElement successMessage = driver.findElement(By.xpath("//span[text()='Thank you for your purchase!']"));
        String successMessageText = successMessage.getText();
        if (successMessageText.equals("Thank you for your purchase!")) {
            System.out.println("Success message is displayed");
        } else {
            System.out.println("Success message is not displayed");
        }
        
        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();
        
        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
        
        // Close the browser
        driver.quit();
    }
}
```

Note: Make sure to replace "path/to/chromedriver" with the actual path to your ChromeDriver executable.