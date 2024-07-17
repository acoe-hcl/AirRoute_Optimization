import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set Chrome browser path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create ChromeOptions object to disable browser notifications
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        // Create WebDriver object
        WebDriver driver = new ChromeDriver(options);

        // Step 1: Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Step 2: Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Step 3: Verify browser name and page name
        String browserName = driver.getCapabilities().getBrowserName();
        String pageTitle = driver.getTitle();
        if (browserName.equals("chrome") && pageTitle.equals("Customer Login")) {
            System.out.println("Browser name and page name validation passed.");
        } else {
            System.out.println("Browser name and page name validation failed.");
        }

        // Step 4: Enter email address and password
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));
        emailField.sendKeys("testermail@gmail.com");
        passwordField.sendKeys("Tester@123");

        // Step 5: Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Step 6: Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.xpath("//a[@id='gear']/span"));
        WebElement bagsLink = driver.findElement(By.xpath("//a[text()='Bags']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        bagsLink.click();

        // Step 7: Click on the image of the "Driven Backpack"
        WebElement backpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        backpackImage.click();

        // Step 8: Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();

        // Step 9: Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[@class='showcart']"));
        myCartLink.click();

        // Step 10: Verify if the "Driven Backpack" is included in the order summary
        WebElement orderSummary = driver.findElement(By.xpath("//h2[text()='Order Summary']"));
        WebElement drivenBackpack = driver.findElement(By.xpath("//a[text()='Driven Backpack']"));
        if (orderSummary.isDisplayed() && drivenBackpack.isDisplayed()) {
            System.out.println("Order summary validation passed.");
        } else {
            System.out.println("Order summary validation failed.");
        }

        // Step 11: Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Step 12: Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing:customer_address_id_0"));
        newAddressButton.click();

        // Step 13: Enter address details
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        WebElement cityField = driver.findElement(By.id("billing:city"));
        WebElement stateField = driver.findElement(By.id("billing:region_id"));
        WebElement zipField = driver.findElement(By.id("billing:postcode"));
        WebElement phoneField = driver.findElement(By.id("billing:telephone"));
        streetField.sendKeys("123 Test Street");
        cityField.sendKeys("Test City");
        stateField.sendKeys("Test State");
        zipField.sendKeys("12345");
        phoneField.sendKeys("1234567890");

        // Step 14: Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Step 15: Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@value='fixed']"));
        fixedRadioButton.click();

        // Step 16: Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Step 17: Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Step 18: Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Step 19: Verify confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//span[contains(text(),'Thank you for your purchase!')]"));
        if (confirmationMessage.isDisplayed()) {
            System.out.println("Confirmation message validation passed.");
        } else {
            System.out.println("Confirmation message validation failed.");
        }

        // Step 20: Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[text()='Change']"));
        changeButton.click();

        // Step 21: Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}