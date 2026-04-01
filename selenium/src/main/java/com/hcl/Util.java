**project-root/src/main/java/utilities/Util.java**
```java
package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
    WebDriver driver;

    public Util(WebDriver driver) {
        this.driver = driver;
    }

    public void enterTextInElementWhenReady(By ele, int timeOut, String text) {
        WebElement element = new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.visibilityOfElementLocated(ele));
        element.clear();
        element.sendKeys(text);
    }

    public void clickElementWhenReady(By ele, int timeOut) {
        WebElement element = new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.elementToBeClickable(ele));
        element.click();
    }
}
```

**project-root/src/main/java/pages/TMobilePurchasePage.java**
```java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Util;

public class TMobilePurchasePage {
    WebDriver driver;
    Util util;

    public TMobilePurchasePage(WebDriver driver) {
        this.driver = driver;
        util = new Util(driver);
    }

    // Locators
    public By offersBanner = By.xpath("//span[contains(text(),'Switch to T-Mobile in just 15 minutes')]");
    public By devicesPreview = By.xpath("//section[contains(@aria-label,'Device Previews') or contains(text(),'phones')]");
    public By menuButton = By.xpath("//button[@aria-label='Open menu']");
    public By phonesDevicesMenu = By.xpath("//a[contains(text(),'Phones & devices')]");
    public By phonesListingHeader = By.xpath("//h1[contains(text(),'Phones')]");
    public By galaxyS24 = By.xpath("//div[contains(text(),'Galaxy S24')]");
    public By deviceDetailTitle = By.xpath("//h1[contains(text(),'Galaxy S24')]");
    public By colorOption = By.xpath("//button[contains(@aria-label,'Color')]"); // May need adjustment per site
    public By memoryOption = By.xpath("//button[contains(@aria-label,'Memory')]");
    public By addToCartButton = By.xpath("//button[contains(text(),'Add to cart') or contains(text(),'Add to Cart')]");
    public By payMonthlyOption = By.xpath("//label[contains(text(),'Pay Monthly')]");
    public By payInFullOption = By.xpath("//label[contains(text(),'Pay in Full')]");
    public By addNewLineRadio = By.xpath("//input[@value='addNewLine']");
    public By replaceDeviceRadio = By.xpath("//input[@value='replaceDevice']");
    public By newLineQuantityInput = By.xpath("//input[@name='newLineQuantity']");
    public By continueButton = By.xpath("//button[contains(text(),'Continue')]");
    public By plansHeader = By.xpath("//h1[contains(text(),'plans')]");
    public By planSelection = By.xpath("//div[contains(@class,'plan-card')][1]//button[contains(text(),'Select')]");
    public By planFeaturesPrices = By.xpath("//div[contains(@class,'plan-features')]");
    public By deviceProtectionOptIn = By.xpath("//button[contains(text(),'Device Protection')]");
    public By optOutDeviceProtection = By.xpath("//button[contains(text(),'No thanks')]");
    public By cartSummary = By.xpath("//div[contains(@class,'cart-summary')]");
    public By checkoutButton = By.xpath("//button[contains(text(),'Checkout')]");
    public By createAccountOption = By.xpath("//button[contains(text(),'Create new account')]");
    public By shippingName = By.xpath("//input[@name='shippingName']");
    public By shippingAddress = By.xpath("//input[@name='shippingAddress']");
    public By deliveryMethod = By.xpath("//select[@name='deliveryMethod']");
    public By accountFullName = By.xpath("//input[@name='fullName']");
    public By accountEmail = By.xpath("//input[@name='email']");
    public By accountPhone = By.xpath("//input[@name='phone']");
    public By accountDOB = By.xpath("//input[@name='dob']");
    public By accountSSN = By.xpath("//input[@name='ssn']");
    public By privacyPolicyCheckbox = By.xpath("//input[@name='privacyPolicy']");
    public By termsCheckbox = By.xpath("//input[@name='terms']");
    public By submitOrderButton = By.xpath("//button[contains(text(),'Submit Order')]");
    public By orderConfirmation = By.xpath("//h2[contains(text(),'Order Confirmation')]");
    public By accountReceipt = By.xpath("//div[contains(text(),'Account creation receipt') or contains(text(),'confirmation email')]");

    // Actions
    public void verifyHomePageOffers(int timeOut) {
        util.enterTextInElementWhenReady(offersBanner, timeOut, "");
        util.enterTextInElementWhenReady(devicesPreview, timeOut, "");
    }

    public void openMenuAndSelectPhonesDevices(int timeOut) {
        util.clickElementWhenReady(menuButton, timeOut);
        util.clickElementWhenReady(phonesDevicesMenu, timeOut);
    }

    public void verifyPhonesListingPage(int timeOut) {
        util.enterTextInElementWhenReady(phonesListingHeader, timeOut, "");
    }

    public void selectDevice(String deviceName, int timeOut) {
        util.clickElementWhenReady(By.xpath("//div[contains(text(),'" + deviceName + "')]"), timeOut);
    }

    public void verifyDeviceDetailPage(String deviceName, int timeOut) {
        util.enterTextInElementWhenReady(By.xpath("//h1[contains(text(),'" + deviceName + "')]"), timeOut, "");
    }

    public void chooseDeviceSpecs(String color, String memory, int timeOut) {
        util.clickElementWhenReady(By.xpath("//button[contains(@aria-label,'" + color + "')]"), timeOut);
        util.clickElementWhenReady(By.xpath("//button[contains(@aria-label,'" + memory + "')]"), timeOut);
    }

    public void addToCart(int timeOut) {
        util.clickElementWhenReady(addToCartButton, timeOut);
    }

    public void selectPurchaseOption(String option, int timeOut) {
        if (option.equalsIgnoreCase("Pay Monthly")) {
            util.clickElementWhenReady(payMonthlyOption, timeOut);
        } else {
            util.clickElementWhenReady(payInFullOption, timeOut);
        }
    }

    public void selectLineOption(String lineOption, int timeOut) {
        if (lineOption.equalsIgnoreCase("Add new line")) {
            util.clickElementWhenReady(addNewLineRadio, timeOut);
        } else {
            util.clickElementWhenReady(replaceDeviceRadio, timeOut);
        }
    }

    public void specifyNewLineQuantity(String quantity, int timeOut) {
        util.enterTextInElementWhenReady(newLineQuantityInput, timeOut, quantity);
        util.clickElementWhenReady(continueButton, timeOut);
    }

    public void selectPlan(int timeOut) {
        util.clickElementWhenReady(planSelection, timeOut);
    }

    public void verifyPlanFeaturesPrices(int timeOut) {
        util.enterTextInElementWhenReady(planFeaturesPrices, timeOut, "");
    }

    public void addOrDeclineDeviceProtection(boolean addDeviceProtection, int timeOut) {
        if (addDeviceProtection) {
            util.clickElementWhenReady(deviceProtectionOptIn, timeOut);
        } else {
            util.clickElementWhenReady(optOutDeviceProtection, timeOut);
        }
    }

    public void reviewCartSummary(int timeOut) {
        util.enterTextInElementWhenReady(cartSummary, timeOut, "");
    }

    public void clickCheckout(int timeOut) {
        util.clickElementWhenReady(checkoutButton, timeOut);
    }

    public void selectCreateAccount(int timeOut) {
        util.clickElementWhenReady(createAccountOption, timeOut);
    }

    public void enterShippingInformation(String name, String address, String deliveryMethodOption, int timeOut) {
        util.enterTextInElementWhenReady(shippingName, timeOut, name);
        util.enterTextInElementWhenReady(shippingAddress, timeOut, address);
        util.clickElementWhenReady(deliveryMethod, timeOut);
        util.enterTextInElementWhenReady(deliveryMethod, timeOut, deliveryMethodOption);
    }

    public void enterAccountPersonalInfo(String fullName, String email, String phone, String dob, String ssn, int timeOut) {
        util.enterTextInElementWhenReady(accountFullName, timeOut, fullName);
        util.enterTextInElementWhenReady(accountEmail, timeOut, email);
        util.enterTextInElementWhenReady(accountPhone, timeOut, phone);
        util.enterTextInElementWhenReady(accountDOB, timeOut, dob);
        util.enterTextInElementWhenReady(accountSSN, timeOut, ssn);
    }

    public void acceptPolicies(int timeOut) {
        util.clickElementWhenReady(privacyPolicyCheckbox, timeOut);
        util.clickElementWhenReady(termsCheckbox, timeOut);
    }

    public void submitOrder(int timeOut) {
        util.clickElementWhenReady(submitOrderButton, timeOut);
    }

    public void verifyOrderConfirmation(int timeOut) {
        util.enterTextInElementWhenReady(orderConfirmation, timeOut, "");
        util.enterTextInElementWhenReady(accountReceipt, timeOut, "");
    }
}
```

**project-root/src/test/java/tests/TMobilePurchaseTest.java**
```java
package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import pages.TMobilePurchasePage;

public class TMobilePurchaseTest {
    WebDriver driver;
    TMobilePurchasePage purchasePage;
    Properties config;

    @BeforeClass
    public void setUp() throws IOException {
        config = new Properties();
        config.load(new FileInputStream(System.getProperty("user.dir") + "/src/resources/config.properties"));
        System.setProperty("webdriver.chrome.driver", config.getProperty("chromeDriverPath"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getProperty("url"));
        purchasePage = new TMobilePurchasePage(driver);
    }

    @Test
    public void testEndToEndPurchaseAndAccountSetup() {
        int timeout = Integer.parseInt(config.getProperty("waitTimeout"));
        purchasePage.verifyHomePageOffers(timeout);
        purchasePage.openMenuAndSelectPhonesDevices(timeout);
        purchasePage.verifyPhonesListingPage(timeout);
        purchasePage.selectDevice(config.getProperty("deviceName"), timeout);
        purchasePage.verifyDeviceDetailPage(config.getProperty("deviceName"), timeout);
        purchasePage.chooseDeviceSpecs(config.getProperty("deviceColor"), config.getProperty("deviceMemory"), timeout);
        purchasePage.addToCart(timeout);
        purchasePage.selectPurchaseOption(config.getProperty("purchaseOption"), timeout);
        purchasePage.selectLineOption(config.getProperty("lineOption"), timeout);
        purchasePage.specifyNewLineQuantity(config.getProperty("newLineQuantity"), timeout);
        purchasePage.selectPlan(timeout);
        purchasePage.verifyPlanFeaturesPrices(timeout);
        purchasePage.addOrDeclineDeviceProtection(Boolean.parseBoolean(config.getProperty("addDeviceProtection")), timeout);
        purchasePage.reviewCartSummary(timeout);
        purchasePage.clickCheckout(timeout);
        purchasePage.selectCreateAccount(timeout);
        purchasePage.enterShippingInformation(
                config.getProperty("shippingName"),
                config.getProperty("shippingAddress"),
                config.getProperty("deliveryMethod"),
                timeout
        );
        purchasePage.enterAccountPersonalInfo(
                config.getProperty("accountFullName"),
                config.getProperty("accountEmail"),
                config.getProperty("accountPhone"),
                config.getProperty("accountDOB"),
                config.getProperty("accountSSN"),
                timeout
        );
        purchasePage.acceptPolicies(timeout);
        purchasePage.submitOrder(timeout);
        purchasePage.verifyOrderConfirmation(timeout);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

**project-root/src/resources/config.properties**
```
url=https://www.t-mobile.com
chromeDriverPath=drivers/chromedriver.exe
waitTimeout=10
deviceName=Galaxy S24
deviceColor=Black
deviceMemory=128GB
purchaseOption=Pay Monthly
lineOption=Add new line
newLineQuantity=1
shippingName=John Doe
shippingAddress=123 Main Street, New York, NY, 10001
deliveryMethod=Standard Delivery
accountFullName=John Doe
accountEmail=johndoe@email.com
accountPhone=1234567890
accountDOB=01/01/1990
accountSSN=123-45-6789
addDeviceProtection=false
```