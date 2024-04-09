Here is the Selenium Java automation test script that covers all the steps and test validations mentioned in the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScript {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Step 1: Launch the application
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Step 2: Click on the Sign In link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Step 3: Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");
        
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");
        
        // Step 4: Click on the Sign In button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        
        // Step 5: Mouse hover on the Gear menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[@title='Gear']/span"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        
        // Step 6: Click on the Bags link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // Step 7: Click on the Overnight Duffle image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
        overnightDuffleImage.click();
        
        // Step 8: Click on the Add to Cart button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();
        
        // Step 9: Click on the My Cart link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        // Step 10: Click on the Proceed to Checkout button
        WebElement proceedToCheckoutButton = driver.findElement(By.linkText("Proceed to Checkout"));
        proceedToCheckoutButton.click();
        
        // Step 11: Verify that the Order Summary has Overnight Duffle product
        WebElement orderSummary = driver.findElement(By.xpath("//div[@class='product-item-info']//h2[contains(text(),'Overnight Duffle')]"));
        String productText = orderSummary.getText();
        if (productText.contains("Overnight Duffle")) {
            System.out.println("Order Summary is having Overnight Duffle product");
        } else {
            System.out.println("Order Summary doesn't have Overnight Duffle product");
        }
        
        // Step 12: Click on the New Address button
        WebElement newAddressButton = driver.findElement(By.id("shipping-new-address-form"));
        newAddressButton.click();
        
        // Step 13: Enter address details
        WebElement streetField = driver.findElement(By.id("shipping:street1"));
        streetField.sendKeys("4 South Street");
        
        WebElement cityField = driver.findElement(By.id("shipping:city"));
        cityField.sendKeys("Texas");
        
        WebElement stateField = driver.findElement(By.id("shipping:region_id"));
        Select stateDropdown = new Select(stateField);
        stateDropdown.selectByVisibleText("Texas");
        
        WebElement zipCodeField = driver.findElement(By.id("shipping:postcode"));
        zipCodeField.sendKeys("77567");
        
        WebElement phoneNumberField = driver.findElement(By.id("shipping:telephone"));
        phoneNumberField.sendKeys("3456788765");
        
        // Step 14: Click on the Ship Here button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();
        
        // Step 15: Select Fixed radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();
        
        // Step 16: Click on the Next button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();
        
        // Step 17: Select "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();
        
        // Step 18: Click on the Place Order button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();
        
        // Step 19: Verify successful purchase message
        WebElement successMessage = driver.findElement(By.xpath("//h1[@class='success-msg']"));
        String messageText = successMessage.getText();
        if (messageText.contains("Thank you for your purchase!")) {
            System.out.println("Successfully placed the order");
        } else {
            System.out.println("Order placement failed");
        }
        
        // Step 20: Click on the Change button
        WebElement changeButton = driver.findElement(By.linkText("Change"));
        changeButton.click();
        
        // Step 21: Click on the Signout link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
        
        // Quit the browser
        driver.quit();
    }
}
```

Note: Please make sure to include necessary imports for WebDriver, WebElement, Actions, Select, and ChromeDriver. Also, you may need to set the appropriate path for the ChromeDriver executable in the system.