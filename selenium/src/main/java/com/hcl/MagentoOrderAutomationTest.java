Here's an example of a Selenium Java automation test script that covers all the steps and test validations mentioned in the given feature file:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomationTest {
    
    public static WebDriver driver;
    public static WebDriverWait wait;
    
    public static void main(String[] args) {
        // Set browser name as "Home Page"
        String browserName = "Home Page";
        setBrowserName(browserName);
        
        // Set page name as "Home Page"
        String pageName = "Home Page";
        setPageName(pageName);
        
        // Launch the application
        String url = "https://magento.softwaretestingboard.com/";
        launchApplication(url);
        
        // Click on the "Sign In" link
        String signInLinkText = "Sign In";
        clickOnLink(signInLinkText);
        
        // Set browser name as "Customer Login"
        browserName = "Customer Login";
        setBrowserName(browserName);
        
        // Set page name as "Customer Login"
        pageName = "Customer Login";
        setPageName(pageName);
        
        // Enter email address in the "Email" field
        String emailAddress = "autotest567@gmail.com";
        String emailFieldId = "email";
        enterText(emailFieldId, emailAddress);
        
        // Enter password in the "Password" field
        String password = "Tester@123";
        String passwordFieldId = "pass";
        enterText(passwordFieldId, password);
        
        // Click on the "Sign In" button
        String signInButtonId = "send2";
        clickOnButton(signInButtonId);
        
        // Set browser name as "Home Page"
        browserName = "Home Page";
        setBrowserName(browserName);
        
        // Set page name as "Home Page"
        pageName = "Home Page";
        setPageName(pageName);
        
        // Mouse hover on the "Gear" menu
        String gearMenuXPath = "//a[contains(text(),'Gear') and @class='level0 has-children']";
        mouseHover(gearMenuXPath);
        
        // Click on the "Bags" link
        String bagsLinkXPath = "//a[contains(text(),'Bags') and @title='Bags']";
        clickOnLink(bagsLinkXPath);
        
        // Set browser name as "Bags - Gear"
        browserName = "Bags - Gear";
        setBrowserName(browserName);
        
        // Set page name as "Bags - Gear"
        pageName = "Bags - Gear";
        setPageName(pageName);
        
        // Click on the "Overnight Duffle" image
        String overnightDuffleImageXPath = "//a[@title='Overnight Duffle']";
        clickOnImage(overnightDuffleImageXPath);
        
        // Set browser name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        setBrowserName(browserName);
        
        // Set page name as "Overnight Duffle"
        pageName = "Overnight Duffle";
        setPageName(pageName);
        
        // Click on the "Add to Cart" button
        String addToCartButtonXPath = "//button[@title='Add to Cart']";
        clickOnButton(addToCartButtonXPath);
        
        // Click on the "My Cart" link
        String myCartLinkXPath = "//a[@class='skip-cart skip-global-cart skip-link skip-link-content']";
        clickOnLink(myCartLinkXPath);
        
        // Click on the "Proceed to Checkout" button
        String proceedToCheckoutButtonXPath = "//button[@title='Proceed to Checkout']";
        clickOnButton(proceedToCheckoutButtonXPath);
        
        // Set browser name as "Checkout"
        browserName = "Checkout";
        setBrowserName(browserName);
        
        // Set page name as "Checkout"
        pageName = "Checkout";
        setPageName(pageName);
        
        // Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummaryXPath = "//h2[contains(text(),'Order Summary')]";
        String expectedProduct = "Overnight Duffle";
        verifyOrderSummary(orderSummaryXPath, expectedProduct);
        
        // Click on the "New Address" button
        String newAddressButtonXPath = "//button[@id='billing-address-select']";
        clickOnButton(newAddressButtonXPath);
        
        // Enter "4 South Street" in the "Street" field
        String streetFieldId = "billing:street1";
        String streetAddress = "4 South Street";
        enterText(streetFieldId, streetAddress);
        
        // Enter "Texas" in the "City" field
        String cityFieldId = "billing:city";
        String city = "Texas";
        enterText(cityFieldId, city);
        
        // Select "Texas" from the "State/Province" dropdown
        String stateDropdownId = "billing:region_id";
        String stateValue = "Texas";
        selectFromDropdown(stateDropdownId, stateValue);
        
        // Enter "77567" in the "Zip/Postal Code" field
        String zipCodeFieldId = "billing:postcode";
        String zipCode = "77567";
        enterText(zipCodeFieldId, zipCode);
        
        // Enter "3456788765" in the "Phone Number" field
        String phoneNumberFieldId = "billing:telephone";
        String phoneNumber = "3456788765";
        enterText(phoneNumberFieldId, phoneNumber);
        
        // Click on the "Ship Here" button
        String shipHereButtonXPath = "//button[@title='Ship Here']";
        clickOnButton(shipHereButtonXPath);
        
        // Select the "Fixed" radio button
        String fixedRadiobuttonId = "s_method_flatrate_flatrate";
        selectRadioButton(fixedRadiobuttonId);
        
        // Click on the "Next" button
        String nextButtonXPath = "//button[@title='Next']";
        clickOnButton(nextButtonXPath);
        
        // Select the "My billing and shipping address are the same" checkbox
        String billingShippingCheckboxXPath = "//input[@id='billing:use_for_shipping_yes']";
        selectCheckbox(billingShippingCheckboxXPath);
        
        // Click on the "Place Order" button
        String placeOrderButtonXPath = "//button[@title='Place Order']";
        clickOnButton(placeOrderButtonXPath);
        
        // Set browser name as "Success Page"
        browserName = "Success Page";
        setBrowserName(browserName);
        
        // Set page name as "Success Page"
        pageName = "Success Page";
        setPageName(pageName);
        
        // Verify the message "Thank you for your purchase!"
        String successMessageXPath = "//span[contains(text(),'Thank you for your purchase!')]";
        String expectedMessage = "Thank you for your purchase!";
        verifySuccessMessage(successMessageXPath, expectedMessage);
        
        // Click on the "Change" button
        String changeButtonXPath = "//a[@id='opc-edit-address']";
        clickOnButton(changeButtonXPath);
        
        // Click on the "Signout" link
        String signoutLinkXPath = "//a[@title='Sign Out']";
        clickOnLink(signoutLinkXPath);
    }
    
    public static void setBrowserName(String browserName) {
        System.out.println("Setting browser name as: " + browserName);
    }
    
    public static void setPageName(String pageName) {
        System.out.println("Setting page name as: " + pageName);
    }
    
    public static void launchApplication(String url) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get(url);
        System.out.println("Application launched at URL: " + url);
    }
    
    public static void clickOnLink(String linkText) {
        WebElement linkElement = driver.findElement(By.linkText(linkText));
        linkElement.click();
        System.out.println("Clicked on the link: " + linkText);
    }
    
    public static void enterText(String fieldId, String text) {
        WebElement textField = driver.findElement(By.id(fieldId));
        textField.clear();
        textField.sendKeys(text);
        System.out.println("Entered text: " + text);
    }
    
    public static void clickOnButton(String buttonXPath) {
        WebElement buttonElement = driver.findElement(By.xpath(buttonXPath));
        buttonElement.click();
        System.out.println("Clicked on the button: " + buttonXPath);
    }
    
    public static void mouseHover(String menuXPath) {
        Actions actions = new Actions(driver);
        WebElement menuElement = driver.findElement(By.xpath(menuXPath));
        actions.moveToElement(menuElement).build().perform();
        System.out.println("Mouse hovered on the menu: " + menuXPath);
    }
    
    public static void clickOnImage(String imageXPath) {
        WebElement imageElement = driver.findElement(By.xpath(imageXPath));
        imageElement.click();
        System.out.println("Clicked on the image: " + imageXPath);
    }
    
    public static void verifyOrderSummary(String orderSummaryXPath, String expectedProduct) {
        WebElement orderSummaryElement = driver.findElement(By.xpath(orderSummaryXPath));
        String actualProduct = orderSummaryElement.getText();
        if (actualProduct.contains(expectedProduct)) {
            System.out.println("Order summary contains the expected product: " + expectedProduct);
        } else {
            System.out.println("Order summary does not contain the expected product");
        }
    }
    
    public static void selectFromDropdown(String dropdownId, String value) {
        WebElement dropdownElement = driver.findElement(By.id(dropdownId));
        dropdownElement.sendKeys(value);
        System.out.println("Selected value: " + value + " from dropdown");
    }
    
    public static void selectRadioButton(String radioButtonId) {
        WebElement radioButtonElement = driver.findElement(By.id(radioButtonId));
        radioButtonElement.click();
        System.out.println("Selected radio button: " + radioButtonId);
    }
    
    public static void selectCheckbox(String checkboxXPath) {
        WebElement checkboxElement = driver.findElement(By.xpath(checkboxXPath));
        if (!checkboxElement.isSelected()) {
            checkboxElement.click();
        }
        System.out.println("Selected checkbox: " + checkboxXPath);
    }
    
    public static void verifySuccessMessage(String messageXPath, String expectedMessage) {
        WebElement messageElement = driver.findElement(By.xpath(messageXPath));
        String actualMessage = messageElement.getText();
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Success message is displayed: " + expectedMessage);
        } else {
            System.out.println("Success message is not displayed");
        }
    }
}
```

You can copy this code and save it as a Java file with the name "MagentoOrderAutomationTest.java". Remember to download and set the correct path for the ChromeDriver executable in the `launchApplication` method.