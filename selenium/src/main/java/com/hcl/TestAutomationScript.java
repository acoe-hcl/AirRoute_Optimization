import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {

    public static void main(String[] args) {
        
        // Set the path of ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Step 1: Open the Magento website
        driver.get("http://www.example.com");

        // Step 2: Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Step 3: Verify that the browser name and page name is set as "Customer Login"
        String browserName = driver.getCapabilities().getBrowserName();
        String pageTitle = driver.getTitle();
        if (browserName.equals("Chrome") && pageTitle.equals("Customer Login")) {
            System.out.println("Browser name and page name are set correctly.");
        } else {
            System.out.println("Browser name and page name are not set correctly.");
        }
        
        // Step 4: Enter the email address and password in the designated fields
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");
        
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Tester@123");
        
        // Step 5: Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("signin-button"));
        signInButton.click();
        
        // Step 6: Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.id("gear-menu"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();
        
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // Step 7: Click on the image of the "Driven Backpack" product
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();
        
        // Step 8: Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();
        
        // Step 9: Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        // Step 10: Verify that the "Order Summary" includes the "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.id("order-summary"));
        if (orderSummary.getText().contains("Driven Backpack")) {
            System.out.println("Order Summary includes the Driven Backpack product.");
        } else {
            System.out.println("Order Summary does not include the Driven Backpack product.");
        }
        
        // Step 11: Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("proceed-to-checkout-button"));
        proceedToCheckoutButton.click();
        
        // Step 12: Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("new-address-button"));
        newAddressButton.click();
        
        // Step 13: Enter the address details
        WebElement streetAddressField = driver.findElement(By.id("street-address"));
        streetAddressField.sendKeys("123 Main Street");
        
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("New York");
        
        WebElement stateField = driver.findElement(By.id("state"));
        stateField.sendKeys("NY");
        
        WebElement zipCodeField = driver.findElement(By.id("zip-code"));
        zipCodeField.sendKeys("10001");
        
        WebElement phoneNumberField = driver.findElement(By.id("phone-number"));
        phoneNumberField.sendKeys("123-456-7890");
        
        // Step 14: Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("ship-here-button"));
        shipHereButton.click();
        
        // Step 15: Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("fixed-radio-button"));
        fixedRadioButton.click();
        
        // Step 16: Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("next-button"));
        nextButton.click();
        
        // Step 17: Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("same-address-checkbox"));
        sameAddressCheckbox.click();
        
        // Step 18: Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.id("place-order-button"));
        placeOrderButton.click();
        
        // Step 19: Verify that a confirmation message is displayed
        WebElement confirmationMessage = driver.findElement(By.id("confirmation-message"));
        if (confirmationMessage.getText().equals("Thank you for your purchase!")) {
            System.out.println("Confirmation message is displayed.");
        } else {
            System.out.println("Confirmation message is not displayed.");
        }
        
        // Step 20: Click on the "Change" button
        WebElement changeButton = driver.findElement(By.id("change-button"));
        changeButton.click();
        
        // Step 21: Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
        
        // Close the browser
        driver.quit();
    }
}
