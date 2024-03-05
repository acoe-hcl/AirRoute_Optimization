import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Set the browser name as "Home Page"
        String browserName = "Home Page";
        // Set the page name as "Home Page"
        String pageName = "Home Page";

        // Set the path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In"))).click();

        // Set the browser name as "Customer Login"
        browserName = "Customer Login";
        // Set the page name as "Customer Login"
        pageName = "Customer Login";

        // Enter the email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Set the browser name as "Home Page"
        browserName = "Home Page";
        // Set the page name as "Home Page"
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.linkText("Gear"))).perform();

        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Set the browser name as "Bags - Gear"
        browserName = "Bags - Gear";
        // Set the page name as "Bags - Gear"
        pageName = "Bags - Gear";

        // Click on the "Overnight Duffle" image
        driver.findElement(By.cssSelector("img[alt='Overnight Duffle']")).click();

        // Set the browser name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        // Set the page name as "Overnight Duffle"
        pageName = "Overnight Duffle";

        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        // Set the browser name as "Checkout"
        browserName = "Checkout";
        // Set the page name as "Checkout"
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.cssSelector(".item-name")).getText();
        if (orderSummary.equals("Overnight Duffle")) {
            System.out.println("Order Summary is having Overnight Duffle product.");
        }

        // Click on the "New Address" button
        driver.findElement(By.id("shipping-new-address-form")).click();

        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("postcode")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.id("shipping-save-in-address-book")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.id("carrier_fixed_fixed")).click();

        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-button")).click();

        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing-address-same-as-shipping")).click();

        // Click on the "Place Order" button
        driver.findElement(By.id("review-place-order")).click();

        // Set the browser name as "Success Page"
        browserName = "Success Page";
        // Set the page name as "Success Page"
        pageName = "Success Page";

        // Verify the success message
        String successMessage = driver.findElement(By.cssSelector(".success-msg")).getText();
        if (successMessage.equals("Thank you for your purchase!")) {
            System.out.println("Success message is displayed.");
        }

        // Click on the "Change" button
        driver.findElement(By.id("change")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();
    }
}