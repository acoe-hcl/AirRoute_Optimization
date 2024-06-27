Here is the Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderAutomation {
    public static void main(String[] args) throws InterruptedException {
        // Set the system property for webdriver.chrome.driver to specify the ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1366, 768));

        // Set the page name as "Customer Login"
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1366, 768));

        // Enter the email in the "Email" field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        // Enter the password in the "Password" field
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the "Driven Backpack" image
        WebElement backpackImage = driver.findElement(By.xpath("//a[contains(text(),'Driven Backpack')]"));
        backpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//table[@id='shopping-cart-table']//a[contains(text(),'Driven Backpack')]"));
        String productName = orderSummary.getText();
        if (productName.equals("Driven Backpack")) {
            System.out.println("Order summary contains Driven Backpack product");
        } else {
            System.out.println("Order summary does not contain Driven Backpack product");
        }

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//a[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@id='billing-address-same-as-shipping-no']"));
        newAddressButton.click();

        // Enter the street in the "Street" field
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        // Enter the city in the "City" field
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        // Select "Texas" from the "State/Province" dropdown
        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");

        // Enter the zip/postal code in the "Zip/Postal Code" field
        WebElement zipCodeField = driver.findElement(By.id("postcode"));
        zipCodeField.sendKeys("77567");

        // Enter the phone number in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@class='button action continue primary']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.xpath("//input[@id='billing-address-same-as-shipping']"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@data-role='review-save']"));
        placeOrderButton.click();

        // Verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(),'Thank you for your purchase!')]"));
        String message = successMessage.getText();
        if (message.equals("Thank you for your purchase!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Failed to place the order");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//a[contains(text(),'Change')]"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Please note that you need to download and set up ChromeDriver in order to run this script. Adjust the path/to/chromedriver accordingly.