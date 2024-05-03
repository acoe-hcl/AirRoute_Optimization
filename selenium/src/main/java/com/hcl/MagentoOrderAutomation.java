import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Set the browser name and page name as "Home Page"
        String browserName = "Home Page";
        String pageName = "Home Page";

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name and page name as "Customer Login"
        browserName = "Customer Login";
        pageName = "Customer Login";

        // Enter email and password
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.name("send"));
        signInButton.click();

        // Set the browser name and page name as "Home Page"
        browserName = "Home Page";
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("gear-icon"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Set the browser name and page name as "Bags - Gear"
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        // Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
        overnightDuffleImage.click();

        // Set the browser name and page name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.name("addtocart"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.name("checkout"));
        proceedToCheckoutButton.click();

        // Set the browser name and page name as "Checkout"
        browserName = "Checkout";
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//div[@id='checkout_order_summary']"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.name("newaddress"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.name("street[0]"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.name("city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.name("region_id"));
        stateDropdown.sendKeys("Texas");

        WebElement postalCodeField = driver.findElement(By.name("postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.name("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.name("shipping[submit]"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.name("next_button"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Set the browser name and page name as "Success Page"
        browserName = "Success Page";
        pageName = "Success Page";

        // Verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='success-msg']"));
        String successMessageText = successMessage.getText();
        if (successMessageText.contains("Thank you for your purchase!")) {
            System.out.println("Thank you message is displayed");
        } else {
            System.out.println("Thank you message is not displayed");
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