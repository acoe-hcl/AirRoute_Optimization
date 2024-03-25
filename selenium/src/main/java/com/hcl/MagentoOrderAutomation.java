Sure, here is the generated Selenium Java automation test script for the given scenario:

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderAutomation {
  public static void main(String[] args) throws InterruptedException {
    
    // Set the browser driver path
    System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    
    // Create a new instance of ChromeDriver
    WebDriver driver = new ChromeDriver();
    
    // Launch the application
    driver.get("https://magento.softwaretestingboard.com/");
    
    // Click on the "Sign In" link
    WebElement signInLink = driver.findElement(By.linkText("Sign In"));
    signInLink.click();
    
    // Enter email and password
    WebElement emailField = driver.findElement(By.id("email"));
    emailField.sendKeys("autotest567@gmail.com");
    
    WebElement passwordField = driver.findElement(By.id("pass"));
    passwordField.sendKeys("Tester@123");
    
    // Click on the "Sign In" button
    WebElement signInButton = driver.findElement(By.id("send2"));
    signInButton.click();
    
    // Mouse hover on the "Gear" menu
    WebElement gearMenu = driver.findElement(By.xpath("//a[@class='level0' and contains(text(), 'Gear')]"));
    Actions actions = new Actions(driver);
    actions.moveToElement(gearMenu).perform();
    
    // Click on the "Bags" link
    WebElement bagsLink = driver.findElement(By.xpath("//a[@class='level1' and contains(text(), 'Bags')]"));
    bagsLink.click();
    
    // Click on the "Overnight Duffle" image
    WebElement overnightDuffleImage = driver.findElement(By.xpath("//a[contains(@href,'magetest[.]php') and contains(@title,'Overnight Duffle')]"));
    overnightDuffleImage.click();
    
    // Click on the "Add to Cart" button
    WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
    addToCartButton.click();
    
    // Click on the "My Cart" link
    WebElement myCartLink = driver.findElement(By.xpath("//a[@class='showcart' and contains(text(), 'My Cart')]"));
    myCartLink.click();
    
    // Click on the "Proceed to Checkout" button
    WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
    proceedToCheckoutButton.click();
    
    // Verify order summary contains "Overnight Duffle" product
    WebElement orderSummary = driver.findElement(By.xpath("//td[@class='product-cart-info']//a[contains(text(), 'Overnight Duffle')]"));
    String productName = orderSummary.getText();
    assert(productName.equals("Overnight Duffle"));
    
    // Click on the "New Address" button
    WebElement newAddressButton = driver.findElement(By.id("billing-address-select"));
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
    WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
    shipHereButton.click();
    
    // Select the "Fixed" radio button
    WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
    fixedRadioButton.click();
    
    // Click on the "Next" button
    WebElement nextButton = driver.findElement(By.xpath("//span[contains(text(), 'Next')]/parent::button"));
    nextButton.click();
    
    // Select the "My billing and shipping address are the same" checkbox
    WebElement billingShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
    billingShippingCheckbox.click();
    
    // Click on the "Place Order" button
    WebElement placeOrderButton = driver.findElement(By.id("review-buttons-container"));
    placeOrderButton.click();
    
    // Verify success page message
    WebElement successPageMessage = driver.findElement(By.xpath("//h1[contains(text(), 'Thank you for your purchase!')]"));
    String successMessage = successPageMessage.getText();
    assert(successMessage.equals("Thank you for your purchase!"));
    
    // Click on the "Change" button
    WebElement changeButton = driver.findElement(By.xpath("//button[contains(text(), 'Change')]"));
    changeButton.click();
    
    // Click on the "Signout" link
    WebElement signoutLink = driver.findElement(By.xpath("//a[contains(text(), 'Signout')]"));
    signoutLink.click();
    
    // Close the browser
    driver.quit();
  }
}

Please note that you need to download and configure the ChromeDriver executable file according to your system configuration and update the file path in the code.