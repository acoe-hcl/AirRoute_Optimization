```
# src/resources/config.properties
base.url=https://www.t-mobile.com
device.name=Galaxy S24
device.color=Phantom Black
device.memory=256GB
purchase.option=Pay Monthly
add.line.option=Add new line
number.of.lines=1
plan.name=Magenta MAX
protection.opt=in
shipping.fullname=John Doe
shipping.address=123 Main St
shipping.city=Seattle
shipping.state=WA
shipping.zip=98101
shipping.phone=5551234567
shipping.email=testuser@example.com
dob=01/01/1990
ssn=123-45-6789
accept.terms=true
```

```java
// src/main/java/utilities/Util.java
package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class Util {
    WebDriver driver;
    public Util(WebDriver driver) {
        this.driver = driver;
    }
    public void enterTextInElementWhenReady(By ele, int timeOut, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
        element.clear();
        element.sendKeys(text);
    }
    public void clickElementWhenReady(By ele, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
    }
}
```

```java
// src/main/java/pages/TMobilePurchasePage.java
package pages;

import org.openqa.selenium.*;
import utilities.Util;
import java.util.List;
import org.openqa.selenium.support.ui.*;

public class TMobilePurchasePage {
    WebDriver driver;
    Util util;

    // By locators
    By offersBanner = By.xpath("//*[contains(text(),'Switch to T-Mobile in just 15 minutes')]");
    By previewDevices = By.xpath("//section[contains(@aria-label,'Devices')]//img");
    By menuBtn = By.xpath("//button[contains(@id,'menu') or contains(@aria-label,'menu') or //span[text()='Menu']]");
    By phonesDevicesMenu = By.xpath("//a[contains(text(),'Phones & Devices')]");
    By phoneListingTitle = By.xpath("//h1[contains(text(),'Phones')]");
    By deviceCard = By.xpath("//a[contains(@aria-label,'"+System.getProperty("device.name", "Galaxy S24")+"')]");
    By allDeviceCards = By.xpath("//a[contains(@aria-label,'Galaxy S24') or contains(@aria-label,'"+System.getProperty("device.name","") +"')]");
    By deviceDetailHeader = By.xpath("//h1[contains(.,'Galaxy S24')]");
    By deviceColor = By.xpath("//button[contains(@aria-label,'"+ System.getProperty("device.color","Phantom Black")+"')]");
    By deviceMemory = By.xpath("//button[contains(text(),'"+ System.getProperty("device.memory","256GB")+"')]");
    By addToCartBtn = By.xpath("//button[contains(.,'Add to cart') or contains(.,'Add to Cart')]");
    By payMonthlyOption = By.xpath("//label[contains(.,'Pay Monthly')]");
    By payFullOption = By.xpath("//label[contains(.,'Pay in Full')]");
    By addLineOption = By.xpath("//label[contains(.,'Add new line')]");
    By replaceDeviceOption = By.xpath("//label[contains(.,'Replace existing device')]");
    By numberOfLines = By.xpath("//select[contains(@id,'lines') or contains(@name,'lines')]");
    By continueBtn = By.xpath("//button[contains(.,'Continue')]");
    By planCard = By.xpath("//div[contains(@aria-label, '"+ System.getProperty("plan.name","") +"')]");
    By planPrice = By.xpath("//div[contains(@aria-label, '"+ System.getProperty("plan.name","") +"')]//span[contains(.,'$')]");
    By planFeatures = By.xpath("//div[contains(@aria-label, '"+ System.getProperty("plan.name","") +"')]//ul/li");
    By protectionIn = By.xpath("//label[contains(.,'Yes, protect my device') or contains(.,'Device Protection')]");
    By protectionOut = By.xpath("//label[contains(.,'No') and contains(.,'Device Protection')]");
    By addProtectionBtn = By.xpath("//button[contains(.,'Add Protection') or contains(.,'Continue')]");
    By cartSummary = By.xpath("//section[contains(@aria-label,'Order summary') or contains(.,'Order summary')]");
    By checkoutBtn = By.xpath("//button[contains(.,'Checkout') or contains(@aria-label,'checkout')]");
    By createAccountOption = By.xpath("//button[contains(.,'Create account') or contains(.,'Create a new account')]");
    By shippingFullName = By.xpath("//input[@name='fullName' or @id='fullName']");
    By shippingAddress = By.xpath("//input[@name='address' or @id='address']");
    By shippingCity = By.xpath("//input[@name='city' or @id='city']");
    By shippingState = By.xpath("//select[@name='state' or @id='state']");
    By shippingZip = By.xpath("//input[@name='zip' or @id='zip']");
    By shippingPhone = By.xpath("//input[@name='phone' or @id='phone']");
    By shippingEmail = By.xpath("//input[@type='email']");
    By deliveryMethod = By.xpath("//label[contains(.,'Standard') or contains(.,'Express')]");
    By personalFullName = By.xpath("//input[@name='fullName' or @id='personalFullName']");
    By personalEmail = By.xpath("//input[@name='email' or @id='personalEmail']");
    By accountPhone = By.xpath("//input[@name='phoneNumber' or @id='phoneNumber']");
    By dob = By.xpath("//input[@name='dob' or @id='dob']");
    By ssn = By.xpath("//input[@name='ssn' or @id='ssn']");
    By privacyPolicyCheckbox = By.xpath("//input[@type='checkbox' and (../label[contains(.,'privacy')])]");
    By termsCheckbox = By.xpath("//input[@type='checkbox' and (../label[contains(.,'Terms')])]");
    By submitOrderBtn = By.xpath("//button[contains(.,'Submit Order')]");
    By orderConfirmation = By.xpath("//*[contains(text(),'Thank you for your order') or contains(.,'Order Confirmation') or contains(.,'Receipt')]");
    By receiptEmail = By.xpath("//div[contains(text(),'confirmation has been sent') or contains(text(),'email receipt')]");

    public TMobilePurchasePage(WebDriver driver) {
        this.driver = driver;
        this.util = new Util(driver);
    }
    public boolean verifyOffersBannerPresent() {
        return driver.findElements(offersBanner).size() > 0;
    }
    public boolean verifyDevicePreviewsPresent() {
        return driver.findElements(previewDevices).size() > 0;
    }
    public void openMenu() {
        util.clickElementWhenReady(menuBtn, 15);
    }
    public void goToPhonesAndDevices() {
        util.clickElementWhenReady(phonesDevicesMenu, 15);
    }
    public boolean isPhoneListingPage() {
        return driver.findElements(phoneListingTitle).size() > 0;
    }
    public void selectDevice(String deviceName) {
        By device = By.xpath("//a[contains(@aria-label,'"+deviceName+"')]");
        util.clickElementWhenReady(device, 15);
    }
    public boolean isDeviceDetailDisplayed(String deviceName) {
        By deviceHeader = By.xpath("//h1[contains(.,'"+deviceName+"')]");
        return driver.findElements(deviceHeader).size() > 0;
    }
    public void selectDeviceSpec(String color, String memory) {
        util.clickElementWhenReady(By.xpath("//button[contains(@aria-label,'"+color+"')]"), 10);
        util.clickElementWhenReady(By.xpath("//button[contains(text(),'"+memory+"')]"), 10);
    }
    public void addToCart() {
        util.clickElementWhenReady(addToCartBtn, 15);
    }
    public void choosePurchaseOption(String option) {
        if (option.equalsIgnoreCase("Pay Monthly"))
            util.clickElementWhenReady(payMonthlyOption, 10);
        else
            util.clickElementWhenReady(payFullOption, 10);
    }
    public void chooseLineOption(String option) {
        if (option.contains("Add")) util.clickElementWhenReady(addLineOption, 10);
        else util.clickElementWhenReady(replaceDeviceOption, 10);
    }
    public void selectNumberOfLines(String lines) {
        Select sel = new Select(driver.findElement(numberOfLines));
        sel.selectByVisibleText(lines);
    }
    public void continueNext() {
        util.clickElementWhenReady(continueBtn, 10);
    }
    public void selectPlan(String planName) {
        util.clickElementWhenReady(By.xpath("//div[contains(@aria-label,'"+planName+"')]"), 15);
    }
    public boolean verifyPlanDetailsVisible(String planName) {
        return driver.findElements(By.xpath("//div[contains(@aria-label, '"+planName+"')]//span[contains(.,'$')]")).size() > 0;
    }
    public void chooseProtectionOption(boolean inProtection) {
        if (inProtection)
            util.clickElementWhenReady(protectionIn, 5);
        else
            util.clickElementWhenReady(protectionOut, 5);
        util.clickElementWhenReady(addProtectionBtn, 10);
    }
    public boolean reviewCartAndSummary() {
        return driver.findElements(cartSummary).size() > 0;
    }
    public void checkout() {
        util.clickElementWhenReady(checkoutBtn, 10);
    }
    public void chooseCreateAccount() {
        util.clickElementWhenReady(createAccountOption, 10);
    }
    public void enterShippingInformation(String fullname, String address, String city, String state, String zip, String phone, String email) {
        util.enterTextInElementWhenReady(shippingFullName, 10, fullname);
        util.enterTextInElementWhenReady(shippingAddress, 10, address);
        util.enterTextInElementWhenReady(shippingCity, 10, city);
        Select selState = new Select(driver.findElement(shippingState));
        selState.selectByVisibleText(state);
        util.enterTextInElementWhenReady(shippingZip, 10, zip);
        util.enterTextInElementWhenReady(shippingPhone, 10, phone);
        util.enterTextInElementWhenReady(shippingEmail, 10, email);
    }
    public void chooseDeliveryMethod() {
        util.clickElementWhenReady(deliveryMethod, 8);
    }
    public void enterPersonalAndAccountInfo(String fullname, String email, String phone, String dob, String ssn) {
        util.enterTextInElementWhenReady(personalFullName, 10, fullname);
        util.enterTextInElementWhenReady(personalEmail, 10, email);
        util.enterTextInElementWhenReady(accountPhone, 10, phone);
        util.enterTextInElementWhenReady(this.dob, 10, dob);
        util.enterTextInElementWhenReady(this.ssn, 10, ssn);
    }
    public void acceptPrivacyAndTerms() {
        if(driver.findElements(privacyPolicyCheckbox).size()>0)
            driver.findElement(privacyPolicyCheckbox).click();
        if(driver.findElements(termsCheckbox).size()>0)
            driver.findElement(termsCheckbox).click();
    }
    public void submitOrder() {
        util.clickElementWhenReady(submitOrderBtn, 10);
    }
    public boolean verifyOrderConfirmation() {
        return driver.findElements(orderConfirmation).size() > 0 || driver.findElements(receiptEmail).size()>0;
    }
}
```

```java
// src/test/java/tests/LinkedInSignUpTest.java
package tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.TMobilePurchasePage;
import java.util.Properties;
import java.io.*;

public class LinkedInSignUpTest {
    WebDriver driver;
    TMobilePurchasePage tmobile;
    Properties prop;

    @BeforeClass
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/resources/config.properties");
        prop.load(fis);
        tmobile = new TMobilePurchasePage(driver);
    }

    @Test
    public void endToEndPurchaseAndAccountCreation() {
        driver.get(prop.getProperty("base.url"));

        // Verify homepage displays offers and device previews
        assert tmobile.verifyOffersBannerPresent();
        assert tmobile.verifyDevicePreviewsPresent();

        // Navigate to Phones & Devices
        tmobile.openMenu();
        tmobile.goToPhonesAndDevices();
        assert tmobile.isPhoneListingPage();

        // Select device and verify detail page
        tmobile.selectDevice(prop.getProperty("device.name"));
        assert tmobile.isDeviceDetailDisplayed(prop.getProperty("device.name"));

        // Select device specs and add to cart
        tmobile.selectDeviceSpec(prop.getProperty("device.color"), prop.getProperty("device.memory"));
        tmobile.addToCart();

        // Choose purchase option
        tmobile.choosePurchaseOption(prop.getProperty("purchase.option"));

        // Choose Add new line and specify number
        tmobile.chooseLineOption(prop.getProperty("add.line.option"));
        tmobile.selectNumberOfLines(prop.getProperty("number.of.lines"));
        tmobile.continueNext();

        // Select plan and verify details
        tmobile.selectPlan(prop.getProperty("plan.name"));
        assert tmobile.verifyPlanDetailsVisible(prop.getProperty("plan.name"));
        tmobile.continueNext();

        // Device protection
        tmobile.chooseProtectionOption("in".equals(prop.getProperty("protection.opt")));
        tmobile.continueNext();

        // Review cart and order summary
        assert tmobile.reviewCartAndSummary();

        // Checkout
        tmobile.checkout();

        // Create new account
        tmobile.chooseCreateAccount();

        // Enter shipping info
        tmobile.enterShippingInformation(
                prop.getProperty("shipping.fullname"),
                prop.getProperty("shipping.address"),
                prop.getProperty("shipping.city"),
                prop.getProperty("shipping.state"),
                prop.getProperty("shipping.zip"),
                prop.getProperty("shipping.phone"),
                prop.getProperty("shipping.email"));
        tmobile.chooseDeliveryMethod();
        tmobile.continueNext();

        // Enter account/personal info
        tmobile.enterPersonalAndAccountInfo(
                prop.getProperty("shipping.fullname"),
                prop.getProperty("shipping.email"),
                prop.getProperty("shipping.phone"),
                prop.getProperty("dob"),
                prop.getProperty("ssn"));
        tmobile.acceptPrivacyAndTerms();
        tmobile.continueNext();

        // Verify order summary and submit
        tmobile.submitOrder();

        // Confirm order and receipt
        assert tmobile.verifyOrderConfirmation();
    }

    @AfterClass
    public void tearDown() {
        if(driver!=null) {
            driver.quit();
        }
    }
}
```