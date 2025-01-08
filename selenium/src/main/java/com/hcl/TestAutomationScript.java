import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {
    
    public static void main(String[] args) {
        
        // Open the Magento website
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.magento.com");
        
        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Verify that the browser name and page name is set as "Customer Login"
        String browserName = driver.getTitle();
        String pageName = driver.findElement(By.tagName("h1")).getText();
        if(browserName.equals("Customer Login") && pageName.equals("Customer Login")) {
            System.out.println("Browser name and page name is set as 'Customer Login'");
        } else {
            System.out.println("Browser name and page name is not set as 'Customer Login'");
        }
        
        // Enter the email address and password in the designated fields
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");
        
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        
        // Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.className("gear-icon"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();
        
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // Click on the image of the "Driven Backpack" product
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();
        
        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();
        
        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        // Verify that the "Order Summary" includes the "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[text()='Order Summary']"));
        if(orderSummary.getText().contains("Driven Backpack")) {
            System.out.println("Order Summary includes 'Driven Backpack' product");
        } else {
            System.out.println("Order Summary does not include 'Driven Backpack' product");
        }
        
        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();
        
        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();
        
        // Enter the address details
        WebElement streetAddressField = driver.findElement(By.id("street_1"));
        streetAddressField.sendKeys("123 Main Street");
        
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("New York");
        
        WebElement stateField = driver.findElement(By.id("region_id"));
        stateField.sendKeys("New York");
        
        WebElement zipField = driver.findElement(By.id("postcode"));
        zipField.sendKeys("10001");
        
        WebElement phoneField = driver.findElement(By.id("telephone"));
        phoneField.sendKeys("1234567890");
        
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
        WebElement billingShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        billingShippingCheckbox.click();
        
        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();
        
        // Verify that a confirmation message saying "Thank you for your purchase!" is displayed
        WebElement confirmationMessage = driver.findElement(By.xpath("//span[text()='Thank you for your purchase!']"));
        if(confirmationMessage.isDisplayed()) {
            System.out.println("Confirmation message 'Thank you for your purchase!' is displayed");
        } else {
            System.out.println("Confirmation message 'Thank you for your purchase!' is not displayed");
        }
        
        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();
        
        // Click on the "Sign Out" link
        WebElement signOutLink = driver.findElement(By.partialLinkText("Sign Out"));
        signOutLink.click();
        
        // Close the browser
        driver.quit();
    }
}
