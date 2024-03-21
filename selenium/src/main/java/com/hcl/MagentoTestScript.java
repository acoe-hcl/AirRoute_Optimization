Sure, here is the Java Selenium test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoTestScript {
    public static void main(String[] args) {
        // Set the browser name as "Home Page"
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Set the page name as "Home Page"
        String homePage = "https://magento.softwaretestingboard.com/";

        // Launch the application
        driver.get(homePage);

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        String customerLoginPage = driver.getTitle();

        // Set the page name as "Customer Login"
        String customerLoginUrl = driver.getCurrentUrl();

        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Set the browser name as "Home Page"
        String homePageAfterLogin = driver.getTitle();

        // Set the page name as "Home Page"
        String homePageUrlAfterLogin = driver.getCurrentUrl();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[contains(@class, 'level0')][contains(text(), 'Gear')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.xpath("//a[contains(text(), 'Bags')]"));
        bagsLink.click();

        // Set the browser name as "Bags - Gear"
        String bagsPage = driver.getTitle();

        // Set the page name as "Bags - Gear"
        String bagsPageUrl = driver.getCurrentUrl();

        // Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[contains(@alt, 'Overnight Duffle')]"));
        overnightDuffleImage.click();

        // Set the browser name as "Overnight Duffle"
        String overnightDufflePage = driver.getTitle();

        // Set the page name as "Overnight Duffle"
        String overnightDufflePageUrl = driver.getCurrentUrl();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[contains(@class, 'action showcart')]"));
        myCartLink.click();

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[contains(@class, 'action primary checkout')]"));
        proceedToCheckoutButton.click();

        // Set the browser name as "Checkout"
        String checkoutPage = driver.getTitle();

        // Set the page name as "Checkout"
        String checkoutPageUrl = driver.getCurrentUrl();

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//strong[contains(text(), 'Order Summary')]/following-sibling::p[contains(text(), 'Overnight Duffle')]"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[contains(@class, 'action action-show-popup')]"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        stateDropdown.sendKeys("Texas");

        WebElement zipCodeField = driver.findElement(By.id("zip"));
        zipCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[contains(@class, 'action primary action-save-address')]"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']/following-sibling::label"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(@class, 'action primary checkout')]"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.xpath("//input[@id='billing-address-same-as-shipping']/following-sibling::label"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[contains(@class, 'action primary checkout')]"));
        placeOrderButton.click();

        // Set the browser name as "Success Page"
        String successPage = driver.getTitle();

        // Set the page name as "Success Page"
        String successPageUrl = driver.getCurrentUrl();

        // Verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(), 'Thank you for your purchase!')]"));
        String successMessageText = successMessage.getText();
        if (successMessageText.contains("Thank you for your purchase!")) {
            System.out.println("Success message is displayed");
        } else {
            System.out.println("Success message is not displayed");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[contains(@class, 'action primary checkout')]"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Please note that you need to have the Selenium WebDriver and ChromeDriver set up in your Java project for this script to work properly.