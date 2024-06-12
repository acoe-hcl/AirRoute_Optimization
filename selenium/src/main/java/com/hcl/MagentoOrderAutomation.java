Here is the Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Set the browser property
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        
        // Create an instance of WebDriver
        WebDriver driver = new ChromeDriver();

        // Set the browser name and page name as "Home Page"
        String browserName = "Home Page";
        String pageName = "Home Page";
        
        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Set the browser name and page name as "Customer Login"
        browserName = "Customer Login";
        pageName = "Customer Login";
        
        // Enter email and password in the fields and click on the "Sign In" button
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("autotest567@gmail.com");
        
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Tester@123");
        
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        
        // Set the browser name and page name as "Home Page"
        browserName = "Home Page";
        pageName = "Home Page";
        
        // Mouse hover on the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // Set the browser name and page name as "Bags - Gear"
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";
        
        // Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.className("product-image-photo"));
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
        WebElement orderSummary = driver.findElement(By.className("subtotal-amount"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }
        
        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing-new-address"));
        newAddressButton.click();
        
        // Enter address details and click on the "Ship Here" button
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.sendKeys("4 South Street");
        
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");
        
        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");
        
        WebElement postalCodeField = driver.findElement(By.id("billing:postcode"));
        postalCodeField.sendKeys("77567");
        
        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");
        
        WebElement shipHereButton = driver.findElement(By.id("billing:use_for_shipping_yes"));
        shipHereButton.click();
        
        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("billing-buttons-container"));
        nextButton.click();
        
        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("shipping:same_as_billing"));
        sameAddressCheckbox.click();
        
        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.cssSelector("button[title='Place Order']"));
        placeOrderButton.click();
        
        // Set the browser name and page name as "Success Page"
        browserName = "Success Page";
        pageName = "Success Page";
        
        // Verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.className("success-msg"));
        String successMessageText = successMessage.getText();
        if (successMessageText.contains("Thank you for your purchase!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Order placement failed");
        }
        
        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.cssSelector("button[title='Change']"));
        changeButton.click();
        
        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
        
        // Close the browser
        driver.quit();
        
    }
}
```

Make sure to replace "path/to/chromedriver.exe" with the actual path to your ChromeDriver executable.