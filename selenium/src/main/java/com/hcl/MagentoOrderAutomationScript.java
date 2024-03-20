package com.example.test;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class MagentoOrderAutomationScript {
 
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
 
        // Step 1: Launch the application
        driver.get("https://magento.softwaretestingboard.com/");
 
        // Step 2: Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
 
        // Step 3: Login with email and password
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));
        WebElement signInButton = driver.findElement(By.id("send2"));
 
        emailField.sendKeys("autotest567@gmail.com");
        passwordField.sendKeys("Tester@123");
        signInButton.click();
 
        // Step 4: Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
 
        // Step 5: Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
 
        // Step 6: Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//div[contains(@class, 'catalog-product-view')]//img[contains(@alt, 'Overnight Duffle')]"));
        overnightDuffleImage.click();
 
        // Step 7: Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();
 
        // Step 8: Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[contains(@class, 'skip-cart')]"));
        myCartLink.click();
 
        // Step 9: Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
        proceedToCheckoutButton.click();
 
        // Step 10: Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[text()='Order Summary']"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }
 
        // Step 11: Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//a[@title='New Address']"));
        newAddressButton.click();
 
        // Step 12: Enter address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        WebElement cityField = driver.findElement(By.id("city"));
        WebElement stateProvinceDropdown = driver.findElement(By.id("region_id"));
        WebElement zipPostalCodeField = driver.findElement(By.id("zip"));
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        WebElement shipHereButton = driver.findElement(By.id("shipping-save-address-button"));
 
        streetField.sendKeys("4 South Street");
        cityField.sendKeys("Texas");
        Select stateProvinceSelect = new Select(stateProvinceDropdown);
        stateProvinceSelect.selectByVisibleText("Texas");
        zipPostalCodeField.sendKeys("77567");
        phoneNumberField.sendKeys("3456788765");
        shipHereButton.click();
 
        // Step 13: Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_fixed_fixed"));
        fixedRadioButton.click();
 
        // Step 14: Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
        nextButton.click();
 
        // Step 15: Select the "My billing and shipping address are the same" checkbox
        WebElement billingShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        billingShippingCheckbox.click();
 
        // Step 16: Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.id("payment-buttons-container"));
        placeOrderButton.click();
 
        // Step 17: Verify the success message
        WebElement successMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
        String successMessageText = successMessage.getText();
        if (successMessageText.equals("Thank you for your purchase!")) {
            System.out.println("Success message is displayed");
        } else {
            System.out.println("Success message is not displayed");
        }
 
        // Step 18: Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//a[@title='Change']"));
        changeButton.click();
 
        // Step 19: Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
 
        driver.quit();
    }
}
