import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Wait for page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign In")));

        // Click on the Sign In link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Wait for Customer Login page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        // Enter email and password
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Tester@123");

        // Click on Sign In button
        WebElement signInButton = driver.findElement(By.cssSelector("button[title='Sign In']"));
        signInButton.click();

        // Wait for Home page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Gear']")));

        // Mouse hover on Gear menu
        WebElement gearMenu = driver.findElement(By.cssSelector("a[title='Gear']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on Bags link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Wait for Bags - Gear page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Overnight Duffle")));

        // Click on Overnight Duffle image
        WebElement overnightDuffleImage = driver.findElement(By.linkText("Overnight Duffle"));
        overnightDuffleImage.click();

        // Wait for Overnight Duffle page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Add to Cart']")));

        // Click on Add to Cart button
        WebElement addToCartButton = driver.findElement(By.cssSelector("button[title='Add to Cart']"));
        addToCartButton.click();

        // Click on My Cart link
        WebElement myCartLink = driver.findElement(By.cssSelector("a[title='My Cart']"));
        myCartLink.click();

        // Wait for Checkout page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Proceed to Checkout']")));

        // Click on Proceed to Checkout button
        WebElement proceedToCheckoutButton = driver.findElement(By.cssSelector("button[title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Wait for Checkout page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Order Summary']/following-sibling::table//td[text()='Overnight Duffle']")));

        // Verify that the Order Summary has Overnight Duffle product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[text()='Order Summary']/following-sibling::table//td[text()='Overnight Duffle']"));
        if (orderSummary.isDisplayed()) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }

        // Click on New Address button
        WebElement newAddressButton = driver.findElement(By.cssSelector("button[title='New Address']"));
        newAddressButton.click();

        // Wait for New Address form to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("street[0]")));

        // Enter address details
        WebElement streetField = driver.findElement(By.name("street[0]"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.name("city"));
        cityField.sendKeys("Texas");

        WebElement stateDropDown = driver.findElement(By.name("region_id"));
        stateDropDown.sendKeys("Texas");

        WebElement postalCodeField = driver.findElement(By.name("postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.name("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on Ship Here button
        WebElement shipHereButton = driver.findElement(By.cssSelector("button[title='Ship Here']"));
        shipHereButton.click();

        // Select Fixed radio button
        WebElement fixedRadioButton = driver.findElement(By.cssSelector("input[value='fixed']"));
        fixedRadioButton.click();

        // Click on Next button
        WebElement nextButton = driver.findElement(By.cssSelector("button[title='Next']"));
        nextButton.click();

        // Select My billing and shipping address are the same checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.cssSelector("input[name='billing_address_id'][value='same']"));
        sameAddressCheckbox.click();

        // Click on Place Order button
        WebElement placeOrderButton = driver.findElement(By.cssSelector("button[title='Place Order']"));
        placeOrderButton.click();

        // Wait for Success page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Success Page']")));

        // Verify the success message
        WebElement successMessage = driver.findElement(By.xpath("//h1[text()='Success Page']/following-sibling::p[text()='Thank you for your purchase!']"));
        if (successMessage.isDisplayed()) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Order placement failed");
        }

        // Click on Change button
        WebElement changeButton = driver.findElement(By.cssSelector("button[title='Change']"));
        changeButton.click();

        // Click on Signout link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Close the browser
        driver.quit();
    }
}