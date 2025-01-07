import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WT001_Test {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set up Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown() {
        // Close the browser after test execution
        driver.quit();
    }

    @Test
    public void verifyCheckoutButtonDisplayed() {
        // Launch the Straight Talk application
        driver.get("https://www.straighttalk.com/");

        // Navigate to the "Phones & Devices" section
        WebElement phonesDevicesSection = driver.findElement(By.linkText("Phones & Devices"));
        phonesDevicesSection.click();

        // Locate and click on the "Trade-In Program" within the "Phones & Devices" section
        WebElement tradeInProgramLink = driver.findElement(By.linkText("Trade-In Program"));
        tradeInProgramLink.click();

        // Locate and click on the "Appraise my phone" button within the Trade-In Program
        WebElement appraisePhoneButton = driver.findElement(By.linkText("Appraise my phone"));
        appraisePhoneButton.click();

        // Select the "Apple" manufacturer option
        WebElement appleManufacturerOption = driver.findElement(By.xpath("//option[contains(text(),'Apple')]"));
        appleManufacturerOption.click();

        // Click on the "Next" button after selecting the manufacturer
        WebElement nextButton1 = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        nextButton1.click();

        // Select the "iPhone11" from the Model type drop-down
        WebElement iPhone11ModelOption = driver.findElement(By.xpath("//option[contains(text(),'iPhone11')]"));
        iPhone11ModelOption.click();

        // Click on the "Next" button after selecting the phone model
        WebElement nextButton2 = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        nextButton2.click();

        // Select the "AT&T" carrier from the Carrier dropdown
        WebElement attCarrierOption = driver.findElement(By.xpath("//option[contains(text(),'AT&T')]"));
        attCarrierOption.click();

        // Click on the "Next" button after selecting the carrier
        WebElement nextButton3 = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        nextButton3.click();

        // Select the storage size "128GB" for the phone
        WebElement storageSizeOption = driver.findElement(By.xpath("//option[contains(text(),'128GB')]"));
        storageSizeOption.click();

        // Click on the "Next" button after selecting the storage size
        WebElement nextButton4 = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        nextButton4.click();

        // Select the desired mobile device for trade-in
        WebElement mobileDeviceOption = driver.findElement(By.id("desiredMobileDevice"));
        mobileDeviceOption.click();

        // Click on the "Confirm Phone" button after selecting the device
        WebElement confirmPhoneButton = driver.findElement(By.xpath("//button[contains(text(),'Confirm Phone')]"));
        confirmPhoneButton.click();

        // Click on the "YES" button when asked about device power on and off
        WebElement yesButton1 = driver.findElement(By.xpath("//button[contains(text(),'YES')]"));
        yesButton1.click();

        // Click on the "NO" button when asked about Activation Lock being disabled
        WebElement noButton1 = driver.findElement(By.xpath("//button[contains(text(),'NO')]"));
        noButton1.click();

        // Click on the "YES" button when asked about the device screen being in good condition
        WebElement yesButton2 = driver.findElement(By.xpath("//button[contains(text(),'YES')]"));
        yesButton2.click();

        // Click on the "YES" button when asked about the device housing being in good condition
        WebElement yesButton3 = driver.findElement(By.xpath("//button[contains(text(),'YES')]"));
        yesButton3.click();

        // Click on the "Next" button after confirming the device details
        WebElement nextButton5 = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        nextButton5.click();

        // Click on the "Confirm ship" checkbox
        WebElement confirmShipCheckbox = driver.findElement(By.id("confirmShipCheckbox"));
        confirmShipCheckbox.click();

        // Click on the "Shop phones" button
        WebElement shopPhonesButton = driver.findElement(By.id("shopPhonesButton"));
        shopPhonesButton.click();

        // Click on the "See details" of a specific Samsung Galaxy mobile
        WebElement seeDetailsButton = driver.findElement(By.xpath("//button[contains(text(),'See details')]"));
        seeDetailsButton.click();

        // Select the "I am a new customer" radio button
        WebElement newCustomerRadioButton = driver.findElement(By.xpath("//input[@id='newCustomer']"));
        newCustomerRadioButton.click();

        // Click on the "Add to cart" button
        WebElement addToCartButton = driver.findElement(By.id("addToCartButton"));
        addToCartButton.click();

        // Enter the desired zipcode
        WebElement zipcodeInput = driver.findElement(By.id("zipcodeInput"));
        zipcodeInput.sendKeys("12345");

        // Select the "I want a new number" radio button
        WebElement newNumberRadioButton = driver.findElement(By.xpath("//input[@id='newNumber']"));
        newNumberRadioButton.click();

        // Click on the "Continue" button
        WebElement continueButton = driver.findElement(By.id("continueButton"));
        continueButton.click();

        // Click on the "Skip for now" button
        WebElement skipForNowButton = driver.findElement(By.xpath("//button[contains(text(),'Skip for now')]"));
        skipForNowButton.click();

        // Click on the "Enroll now" button for the Mobile protect plus protection
        WebElement enrollNowButton = driver.findElement(By.xpath("//button[contains(text(),'Enroll now')]"));
        enrollNowButton.click();

        // Check the checkbox for "Terms & conditions"
        WebElement termsAndConditionsCheckbox = driver.findElement(By.name("termsAndConditions"));
        termsAndConditionsCheckbox.click();

        // Click on the "Confirm" button
        WebElement confirmButton = driver.findElement(By.xpath("//button[contains(text(),'Confirm')]"));
        confirmButton.click();

        // Verify if the checkout button is displayed
        WebElement checkoutButton = driver.findElement(By.id("checkoutButton"));
        Assert.assertTrue(checkoutButton.isDisplayed(), "Checkout button is not displayed");
    }
}
