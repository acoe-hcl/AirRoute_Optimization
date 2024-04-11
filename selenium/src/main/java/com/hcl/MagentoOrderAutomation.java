import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderAutomation {
    
    public static void main(String[] args) {
        // Setting up chromedriver path
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        
        // Creating an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Step 1: Launch the application
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Step 2: Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Step 3: Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");
        
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");
        
        // Step 4: Click on the "Sign In" button
        WebElement loginButton = driver.findElement(By.id("send2"));
        loginButton.click();
        
        // Step 5: Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[contains(@class,'level0 gear')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        
        // Step 6: Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.xpath("//a[contains(text(),'Bags')]"));
        bagsLink.click();
        
        // Step 7: Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//a[contains(text(),'Overnight Duffle')]"));
        overnightDuffleImage.click();
        
        // Step 8: Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product_addtocart_form"));
        addToCartButton.submit();
        
        // Step 9: Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[@class='top-link-cart']"));
        myCartLink.click();
        
        // Step 10: Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();
        
        // Step 11: Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//h1[contains(text(),'Order Summary')]"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains 'Overnight Duffle' product");
        } else {
            System.out.println("Order Summary does not contain 'Overnight Duffle' product");
        }
        
        // Step 12: Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@id='billing-address-select']"));
        newAddressButton.click();
        
        // Step 13: Enter address details
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
        
        // Step 14: Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();
        
        // Step 15: Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();
        
        // Step 16: Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();
        
        // Step 17: Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();
        
        // Step 18: Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();
        
        // Step 19: Verify success message
        WebElement successMessage = driver.findElement(By.xpath("//span[contains(text(),'Thank you for your purchase!')]"));
        String successMessageText = successMessage.getText();
        if (successMessageText.equals("Thank you for your purchase!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Order placement failed");
        }
        
        // Step 20: Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//a[contains(text(),'Change')]"));
        changeButton.click();
        
        // Step 21: Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.xpath("//a[contains(text(),'Signout')]"));
        signoutLink.click();
        
        // Closing the browser
        driver.quit();
    }
}