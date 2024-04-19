import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderTestScript {
    public static void main(String[] args) {
        // Set the path for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Set browser name and page name to "Home Page"
        String browserName = "Home Page";
        String pageName = "Home Page";

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Find and click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set browser name and page name to "Customer Login"
        browserName = "Customer Login";
        pageName = "Customer Login";

        // Find and fill in the email field
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("autotest567@gmail.com");

        // Find and fill in the password field
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Tester@123");

        // Find and click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Set browser name and page name to "Home Page"
        browserName = "Home Page";
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[@class='fa-user gears']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Find and click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.xpath("//a[text()='Bags']"));
        bagsLink.click();

        // Set browser name and page name to "Bags - Gear"
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        // Find and click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//a[@title='Overnight Duffle']"));
        overnightDuffleImage.click();

        // Set browser name and page name to "Overnight Duffle"
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";

        // Find and click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[text()='Add to Cart']"));
        addToCartButton.click();

        // Find and click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[text()='My Cart']"));
        myCartLink.click();

        // Find and click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[contains(text(),'Proceed to Checkout')]"));
        proceedToCheckoutButton.click();

        // Set browser name and page name to "Checkout"
        browserName = "Checkout";
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//div[@class='cart-item__name' and text()='Overnight Duffle']"));
        if (orderSummary.isDisplayed()) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }

        // Find and click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[text()='New Address']"));
        newAddressButton.click();

        // Find and fill in the street field
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        // Find and fill in the city field
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        // Find and select "Texas" from the "State/Province" dropdown
        WebElement stateProvinceDropdown = driver.findElement(By.id("region_id"));
        stateProvinceDropdown.sendKeys("Texas");

        // Find and fill in the zip/postal code field
        WebElement zipPostalCodeField = driver.findElement(By.id("zip"));
        zipPostalCodeField.sendKeys("77567");

        // Find and fill in the phone number field
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Find and click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[text()='Ship Here']"));
        shipHereButton.click();

        // Find and select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@value='fixed']"));
        fixedRadioButton.click();

        // Find and click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Find and click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@data-ui-id='checkout-cart-submit-button']"));
        placeOrderButton.click();

        // Set browser name and page name to "Success Page"
        browserName = "Success Page";
        pageName = "Success Page";

        // Verify the message "Thank you for your purchase!"
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Thank you for your purchase!']")));
        if (successMessage.isDisplayed()) {
            System.out.println("Success: Thank you message displayed");
        } else {
            System.out.println("Failure: Thank you message not displayed");
        }

        // Find and click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[text()='Change']"));
        changeButton.click();

        // Find and click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}