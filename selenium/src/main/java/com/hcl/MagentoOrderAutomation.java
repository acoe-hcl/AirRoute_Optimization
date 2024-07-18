Sure! Here is the Selenium Java automation test script file for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name and page name
        String browserName = "Customer Login";
        String pageName = "Customer Login";
        driver.executeScript("window.localStorage.setItem('browserName', '" + browserName + "');");
        driver.executeScript("window.localStorage.setItem('pageName', '" + pageName + "');");

        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[@id='nav-2']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//h1[contains(text(),'Order Summary')]"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Driven Backpack")) {
            System.out.println("Order Summary contains Driven Backpack product");
        } else {
            System.out.println("Order Summary does not contain Driven Backpack product");
        }

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        WebElement stateField = driver.findElement(By.id("region_id"));
        stateField.sendKeys("Texas");

        WebElement postalCodeField = driver.findElement(By.id("postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.xpath("//span[contains(text(),'Thank you for your purchase!')]"));
        String successMessageText = successMessage.getText();
        if (successMessageText.equals("Thank you for your purchase!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Order placement failed");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Make sure to replace "path/to/chromedriver" with the actual path to your chromedriver executable.

Hope this helps! Let me know if you have any questions.