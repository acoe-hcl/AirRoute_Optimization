Here is a Selenium Java automation test script file for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomation {

    public static void main(String[] args) {
        // Setting up the webdriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Navigating to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Clicking on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Setting the browser name and page name
        String browserName = "Customer Login";
        String pageName = "Customer Login";

        // Entering email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Clicking on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("mg-nav__item--current"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Clicking on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Clicking on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Clicking on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Clicking on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verifying the "Order Summary" has "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//span[contains(text(),'Driven Backpack')]"));
        String productText = orderSummary.getText();
        if (productText.contains("Driven Backpack")) {
            System.out.println("Order Summary contains Driven Backpack product");
        } else {
            System.out.println("Order Summary does not contain Driven Backpack product");
        }

        // Clicking on the "Proceed to Checkout" button
        WebElement checkoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        checkoutButton.click();

        // Clicking on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//span[contains(text(),'New Address')]"));
        newAddressButton.click();

        // Entering address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        stateDropdown.sendKeys("Texas");

        WebElement postalCodeField = driver.findElement(By.id("postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Clicking on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//span[contains(text(),'Ship Here')]"));
        shipHereButton.click();

        // Selecting the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@id='s_method_freeshipping_freeshipping']"));
        fixedRadioButton.click();

        // Clicking on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
        nextButton.click();

        // Selecting the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Clicking on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//span[contains(text(),'Place Order')]"));
        placeOrderButton.click();

        // Verifying the message "Thank you for your purchase!"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Thank you for your purchase!')]")));
        WebElement thankYouMessage = driver.findElement(By.xpath("//span[contains(text(),'Thank you for your purchase!')]"));
        String messageText = thankYouMessage.getText();
        if (messageText.contains("Thank you for your purchase!")) {
            System.out.println("Thank you message is displayed");
        } else {
            System.out.println("Thank you message is not displayed");
        }

        // Clicking on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//span[contains(text(),'Change')]"));
        changeButton.click();

        // Clicking on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Closing the webdriver
        driver.quit();
    }
}
```

Please make sure to replace "path/to/chromedriver" with the actual path to your ChromeDriver executable.