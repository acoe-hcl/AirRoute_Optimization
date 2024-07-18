```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoOrderAutomationScript {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver","path/to/chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        String browserName = "Customer Login";

        // Set the page name as "Customer Login"
        String pageName = "Customer Login";

        // Enter email address and password
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("testermail@gmail.com");

        WebElement passwordField = driver.findElement(By.name("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//*[@id='nav']//span[text()='Gear']"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.xpath("//*[@id='nav']//a[text()='Bags']"));
        bagsLink.click();

        // Click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[@title='My Cart']"));
        myCartLink.click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//div[@class='product-name']"));
        String expectedProduct = "Driven Backpack";
        String actualProduct = orderSummary.getText();
        if (actualProduct.contains(expectedProduct)) {
            System.out.println("Product verification passed.");
        } else {
            System.out.println("Product verification failed.");
        }

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//span[text()='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//span[text()='New Address']"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.name("street[0]"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.name("city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.name("region_id"));
        Select stateDropdownSelect = new Select(stateDropdown);
        stateDropdownSelect.selectByVisibleText("Texas");

        WebElement postalCodeField = driver.findElement(By.name("postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.name("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//span[text()='Ship Here']"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.name("shippingAddressUseBilling"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//span[text()='Place Order']"));
        placeOrderButton.click();

        // Verify the message "Thank you for your purchase!"
        WebElement purchaseMessage = driver.findElement(By.xpath("//span[text()='Thank you for your purchase!']"));
        String expectedMessage = "Thank you for your purchase!";
        String actualMessage = purchaseMessage.getText();
        if (expectedMessage.equals(actualMessage)) {
            System.out.println("Purchase message verification passed.");
        } else {
            System.out.println("Purchase message verification failed.");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//span[text()='Change']"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}
```

Please note that you need to replace the "path/to/chromedriver" with the actual path to the chromedriver executable on your machine. Additionally, you might need to import the necessary Selenium and Java libraries for the code to work properly.