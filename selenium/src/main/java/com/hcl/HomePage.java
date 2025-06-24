Certainly, Guru will provide a **Selenium-Java TestNG** automation solution, structured using the **Page Object Model (POM)**, using `@FindBy` annotations for element mapping.

### **Framework Structure Overview**
```
project-root/
?
??? src/
?   ??? main/
?   ?   ??? java/
?   ?       ??? pages/
?   ?           ??? HomePage.java
?   ?           ??? LoginPage.java
?   ?           ??? ProductPage.java
?   ?           ??? CartPage.java
?   ?           ??? CheckoutPage.java
?   ?           ??? ConfirmationPage.java
?   ??? test/
?       ??? java/
?           ??? tests/
?               ??? PurchaseFlowTest.java
??? testng.xml
```

---

## **1. Page Objects**

### **HomePage.java**
```java
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(linkText = "Sign In")
    WebElement signInLink;

    @FindBy(xpath = "//span[text()='Gear']")
    WebElement gearMenu;

    @FindBy(xpath = "//span[text()='Bags']")
    WebElement bagsLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignIn() {
        signInLink.click();
    }
    
    public void hoverOverGearMenu(org.openqa.selenium.interactions.Actions actions) {
        actions.moveToElement(gearMenu).perform();
    }

    public void clickBagsLink() {
        bagsLink.click();
    }
}
```

---

### **LoginPage.java**
```java
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "pass")
    WebElement passwordField;

    @FindBy(id = "send2")
    WebElement signInButton;

    @FindBy(xpath = "//span[@class='base' and text()='Customer Login']")
    WebElement customerLoginPageTitle;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return customerLoginPageTitle.getText();
    }

    public void login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
    }
}
```

---

### **ProductPage.java**
```java
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    WebDriver driver;

    @FindBy(xpath = "//img[@alt='Driven Backpack']")
    WebElement drivenBackpackImage;

    @FindBy(xpath = "//span[text()='Add to Cart']/parent::button")
    WebElement addToCartBtn;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectDrivenBackpack() {
        drivenBackpackImage.click();
    }

    public void addToCart() {
        addToCartBtn.click();
    }
}
```

---

### **CartPage.java**
```java
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;

    @FindBy(xpath = "//a[@class='action showcart']")
    WebElement myCartLink;

    @FindBy(css = ".product-item-details .product-item-name a")
    WebElement orderSummaryProductName;

    @FindBy(id = "top-cart-btn-checkout")
    WebElement proceedToCheckoutBtn;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToCart() {
        myCartLink.click();
    }

    public String getProductFromOrderSummary() {
        return orderSummaryProductName.getText();
    }

    public void clickProceedToCheckout() {
        proceedToCheckoutBtn.click();
    }
}
```

---

### **CheckoutPage.java**
```java
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

public class CheckoutPage {
    WebDriver driver;

    @FindBy(xpath = "//span[text()='New Address']")
    WebElement newAddressBtn;

    @FindBy(name = "street[0]")
    WebElement streetField;

    @FindBy(name = "city")
    WebElement cityField;

    @FindBy(name = "region_id")
    WebElement stateDropdown;

    @FindBy(name = "postcode")
    WebElement postcodeField;

    @FindBy(name = "telephone")
    WebElement phoneField;

    @FindBy(xpath = "//button[@data-action='customer-save-address']")
    WebElement shipHereBtn;

    @FindBy(xpath = "//input[@type='radio' and contains(@value, 'flatrate')]")
    WebElement fixedShippingRadio;

    @FindBy(xpath = "//span[text()='Next']/parent::button")
    WebElement nextBtn;

    @FindBy(name = "billing-address-same-as-shipping")
    WebElement billingSameCheckbox;

    @FindBy(xpath = "//span[text()='Place Order']/parent::button")
    WebElement placeOrderBtn;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickNewAddress() {
        newAddressBtn.click();
    }

    public void enterShippingAddress(String street, String city, String state, String postcode, String phone) {
        streetField.sendKeys(street);
        cityField.sendKeys(city);
        Select select = new Select(stateDropdown);
        select.selectByVisibleText(state);
        postcodeField.sendKeys(postcode);
        phoneField.sendKeys(phone);
    }

    public void clickShipHere() {
        shipHereBtn.click();
    }

    public void selectFixedShipping() {
        fixedShippingRadio.click();
    }

    public void clickNext() {
        nextBtn.click();
    }

    public void selectBillingSameCheckbox() {
        if (!billingSameCheckbox.isSelected())
            billingSameCheckbox.click();
    }

    public void placeOrder() {
        placeOrderBtn.click();
    }
}
```

---

### **ConfirmationPage.java**
```java
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
    WebDriver driver;

    @FindBy(xpath = "//span[text()='Thank you for your purchase!']")
    WebElement thankYouMessage;

    @FindBy(xpath = "//a[text()='Change']")
    WebElement changeBtn;

    @FindBy(linkText = "Sign Out")
    WebElement signoutLink;
    
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isThankYouDisplayed() {
        return thankYouMessage.isDisplayed();
    }

    public void clickChange() {
        changeBtn.click();
    }

    public void clickSignout() {
        signoutLink.click();
    }
}
```

---

## **2. Test Class**

### **PurchaseFlowTest.java**
```java
package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import pages.*;

import java.time.Duration;

public class PurchaseFlowTest {
    WebDriver driver;
    Actions actions;
    HomePage homePage;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://magento-demo-url.com/"); // Replace with real URL

        actions = new Actions(driver);

        // Initialize Page Objects
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }

    @Test
    public void testSuccessfulPurchaseFlow() throws InterruptedException {
        // Step 1: Click "Sign In"
        homePage.clickSignIn();

        // Step 2: Assert Browser Name and Page Title
        Assert.assertEquals(loginPage.getPageTitle(), "Customer Login", "Page title mismatch after clicking Sign In");

        // Step 3: Login with Valid Credentials
        loginPage.login("testuser@example.com", "StrongPassword123");

        // Step 4: Hover Gear -> Click Bags
        homePage.hoverOverGearMenu(actions);
        homePage.clickBagsLink();

        // Step 5: Select Driven Backpack and Add to Cart
        productPage.selectDrivenBackpack();
        productPage.addToCart();

        // Step 6: Go to Cart
        cartPage.goToCart();

        // Assertion: Driven Backpack in Order Summary
        Assert.assertTrue(cartPage.getProductFromOrderSummary().contains("Driven Backpack"),
            "Driven Backpack not found in cart Order Summary!");

        // Step 7: Proceed to Checkout
        cartPage.clickProceedToCheckout();

        // Step 8: Add New Address
        checkoutPage.clickNewAddress();
        checkoutPage.enterShippingAddress("100 Main St", "New York", "New York", "12345", "1234567890");
        checkoutPage.clickShipHere();

        // Step 9: Select Fixed Shipping Method, click Next
        Thread.sleep(2000); // Wait for shipping methods to load (use explicit waits in production)
        checkoutPage.selectFixedShipping();
        checkoutPage.clickNext();
        
        // Step 10: Check Billing and Shipping Same
        checkoutPage.selectBillingSameCheckbox();

        // Step 11: Place Order
        checkoutPage.placeOrder();

        // Assertion: Thank You Message
        Assert.assertTrue(confirmationPage.isThankYouDisplayed(), 
            "\"Thank you for your purchase!\" message was not displayed!");

        // Step 12: Click Change & Sign Out
        confirmationPage.clickChange();
        confirmationPage.clickSignout();
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}
```

---

## **3. Notes & Expansion**
- **Test Data**: Replace user credentials, address, and URL as appropriate.
- **Assertions** are included for page titles, cart contents, and confirmation message.
- **Explicit Waits** can replace `Thread.sleep` for production reliability.
- All elements are mapped using `@FindBy` for maintainability.
- Extend as needed for negative/alternate test scenarios.

---

**Guru’s Tip:**  
- Integrate [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) for automatic driver binaries.
- Expand with DataProviders for multiple combinations.
- Refactor locators based on actual site structure as needed.

Let me know if you need DataProviders, parameterization, or immediate negative flows as well!