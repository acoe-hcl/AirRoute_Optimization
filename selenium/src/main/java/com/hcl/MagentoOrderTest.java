import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        
        // Step 1: Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Step 2: Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Step 3: Verify browser name and page name
        String browserName = driver.getTitle();
        String expectedPageName = "Customer Login";
        if (browserName.equals(expectedPageName)) {
            System.out.println("Browser name and page name are set correctly");
        } else {
            System.out.println("Browser name and page name are not set correctly");
        }
        
        // Step 4: Enter email address and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");
        
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");
        
        // Step 5: Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        
        // Step 6: Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        actions.moveToElement(gearMenu).perform();
        
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // Step 7: Click on the image of the "Driven Backpack"
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();
        
        // Step 8: Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();
        
        // Step 9: Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        // Step 10: Verify "Order Summary" includes the "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//strong[text()='Driven Backpack']"));
        if (orderSummary.isDisplayed()) {
            System.out.println("Order Summary includes the Driven Backpack product");
        } else {
            System.out.println("Order Summary does not include the Driven Backpack product");
        }
        
        // Step 11: Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();
        
        // Step 12: Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing:street1"));
        newAddressButton.click();
        
        // Step 13: Enter address details
        WebElement streetAddressField = driver.findElement(By.id("billing:street1"));
        streetAddressField.sendKeys("123 Main Street");
        
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("New York");
        
        WebElement stateField = driver.findElement(By.id("billing:region_id"));
        stateField.sendKeys("New York");
        
        WebElement postalCodeField = driver.findElement(By.id("billing:postcode"));
        postalCodeField.sendKeys("10001");
        
        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("1234567890");
        
        // Step 14: Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
        shipHereButton.click();
        
        // Step 15: Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();
        
        // Step 16: Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']/button"));
        nextButton.click();
        
        // Step 17: Select the "My billing and shipping address are the same" checkbox
        WebElement sameBillingShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameBillingShippingCheckbox.click();
        
        // Step 18: Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//div[@id='payment-buttons-container']/button"));
        placeOrderButton.click();
        
        // Step 19: Verify the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//h2[@class='sub-title']"));
        String expectedConfirmationMessage = "Thank you for your purchase!";
        if (confirmationMessage.getText().equals(expectedConfirmationMessage)) {
            System.out.println("Confirmation message is displayed correctly");
        } else {
            System.out.println("Confirmation message is not displayed correctly");
        }
        
        // Step 20: Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//li[@class='customer-welcome']/a"));
        changeButton.click();
        
        // Step 21: Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
        
        // Close the browser
        driver.quit();
    }
}
