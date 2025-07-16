package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class VerizonE2ENewCustomerTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Set property if local chromedriver, use WebDriverManager if available
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testEndToEndNewCustomerOrder() {
        // Step 1: Open the browser and navigate to the Verizon website
        driver.get("https://www.verizon.com");
        // Step 2: Verify that the website is accessible and loads without errors.
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("verizon"), "Website title does not contain 'verizon'");

        // Step 3: Navigate Shop > Devices > Smartphones
        WebElement shopMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Shop')]")));
        shopMenu.click();
        WebElement devicesSubmenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Devices')]")));
        devicesSubmenu.click();
        WebElement smartphonesLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Smartphones')]")));
        smartphonesLink.click();

        // Step 4: Select "Apple iPhone 15"
        WebElement iphone15Card = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'iphone-15') and contains(.,'iPhone 15')]")));
        iphone15Card.click();

        // Step 5: Choose black color, 128GB, new customer, one time payment, next steps, continue location
        WebElement blackColor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@aria-label,'Black')]")));
        blackColor.click();
        WebElement storage128GB = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'128 GB')]")));
        storage128GB.click();
        WebElement newCustomerOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'New customer')]")));
        newCustomerOption.click();
        WebElement oneTimePayment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'One time payment')]")));
        oneTimePayment.click();
        WebElement nextStepButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Next steps')]")));
        nextStepButton.click();
        WebElement continueLocationButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        continueLocationButton.click();

        // Step 6: Select unlimited plan
        WebElement unlimitedPlan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Unlimited')]/ancestor::button")));
        unlimitedPlan.click();

        // Step 7: Click No, Thanks for mobile protection
        WebElement noThanksButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'No, Thanks')]")));
        noThanksButton.click();

        // Step 8: Continue on accessory interstitial
        WebElement continueAccessory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        continueAccessory.click();

        // Step 9: Begin secure checkout
        WebElement secureCheckout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Begin secure checkout')]")));
        secureCheckout.click();

        // Step 10: Enter Contact & Shipping Details
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        WebElement lastName = driver.findElement(By.id("lastName"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement contactNumber = driver.findElement(By.id("contactNumber"));
        WebElement streetAddress = driver.findElement(By.id("streetAddress"));

        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        email.sendKeys("john.doe" + System.currentTimeMillis() + "@testmail.com");
        contactNumber.sendKeys("5551234567");
        streetAddress.sendKeys("123 Test St, Test City, NY 12345");

        WebElement continueShipping = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        continueShipping.click();

        // Step 11: Credit check - Enter DOB, SSN, Account PIN
        WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dob")));
        WebElement ssn = driver.findElement(By.id("ssn"));
        WebElement accountPin = driver.findElement(By.id("accountPin"));

        dob.sendKeys("01011990");
        ssn.sendKeys("123-45-6789");
        accountPin.sendKeys("1234");

        // Step 12: Generate a new MTN (Get a new number radio button)
        WebElement newNumberRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @value='newNumber']")));
        newNumberRadio.click();

        // Step 13: Verify new MTN is generated
        WebElement mtnValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'mtn-number')]")));
        String newMTN = mtnValue.getText();
        Assert.assertTrue(newMTN.matches("\\(\\d{3}\\) \\d{3}-\\d{4}"), "New MTN generated: " + newMTN);

        // Step 14: Select a shipping method
        WebElement shippingMethod = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and contains(@value,'shipping')]")));
        shippingMethod.click();

        // Step 15: Verify chosen shipping method applied
        WebElement shippingSummary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'shipping-summary')]")));
        Assert.assertTrue(shippingSummary.getText().toLowerCase().contains("shipping"), "Shipping method applied");

        // Step 16: Enter valid payment details
        WebElement cardNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardNumber")));
        WebElement cardFirstName = driver.findElement(By.id("cardFirstName"));
        WebElement cardLastName = driver.findElement(By.id("cardLastName"));
        WebElement securityCode = driver.findElement(By.id("securityCode"));
        WebElement expDate = driver.findElement(By.id("expDate"));

        cardNumber.sendKeys("4111111111111111");
        cardFirstName.sendKeys("John");
        cardLastName.sendKeys("Doe");
        securityCode.sendKeys("123");
        expDate.sendKeys("1230");

        WebElement savePayment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save') or contains(text(),'Continue')]")));
        savePayment.click();

        // Step 17: Verify payment details accepted
        WebElement paymentSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Payment method added')]")));
        Assert.assertTrue(paymentSuccess.isDisplayed(), "Payment method was not added successfully");

        // Step 18: Accept agreements, terms, and conditions
        WebElement agreementsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and @name='acceptTerms']")));
        if (!agreementsCheckbox.isSelected()) {
            agreementsCheckbox.click();
        }
        // Step 19: Verify agreements accepted
        Assert.assertTrue(agreementsCheckbox.isSelected(), "Agreements not accepted");

        // Step 20: Verify if "Place Order" button is displayed
        WebElement placeOrderBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Place Order')]")));
        Assert.assertTrue(placeOrderBtn.isDisplayed(), "'Place Order' button is not displayed");

        // Place order for E2E flow
        placeOrderBtn.click();

        // Step 21: Order confirmation
        WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Thank you for your order') or contains(text(),'Order Confirmation')]")));
        Assert.assertTrue(confirmationMsg.isDisplayed(), "Order confirmation message is not displayed");

        // Step 22: Verify order details in confirmation (could check for order #)
        WebElement orderNumber = driver.findElement(By.xpath("//span[contains(@class,'order-number')]"));
        Assert.assertTrue(orderNumber.getText().matches("\\d+"), "Order number not found in confirmation");

        // Additional: Would check for confirmation email or order history if possible (out of scope for UI automation in general)
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
