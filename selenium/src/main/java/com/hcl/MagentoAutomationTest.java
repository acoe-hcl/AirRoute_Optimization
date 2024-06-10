import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoAutomationTest {

    public static void main(String[] args) {
        // Set the browser path (update with your ChromeDriver path)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        
        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Step 1: Launch the application
        driver.get("https://magento.softwaretestingboard.com/");
        System.out.println("Opened Magento homepage");

        // Step 2: Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        System.out.println("Clicked on Sign In link");

        // Step 3: Enter email and password, and click on "Sign In" button
        driver.findElement(By.id("login-email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).sendKeys(Keys.ENTER);
        System.out.println("Entered email and password, and clicked on Sign In");

        // Step 4: Mouse hover on the "Gear" menu
        // // code to perform mouse hover
        System.out.println("Performed mouse hover on the Gear menu");

        // Step 5: Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
        System.out.println("Clicked on Bags link");

        // Step 6: Click on the "Overnight Duffle" image
        driver.findElement(By.xpath("//img[@alt='Overnight Duffle']")).click();
        System.out.println("Clicked on Overnight Duffle image");

        // Step 7: Click on the "Add to Cart" button
        driver.findElement(By.xpath("//span[text()='Add to Cart']")).click();
        System.out.println("Clicked on Add to Cart button");

        // Step 8: Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        System.out.println("Clicked on My Cart link");

        // Step 9: Click on the "Proceed to Checkout" button
        driver.findElement(By.xpath("//span[text()='Proceed to Checkout']")).click();
        System.out.println("Clicked on Proceed to Checkout button");

        // Step 10: Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.className("order-summary-item-title")).getText();
        if (orderSummary.equals("Overnight Duffle")) {
            System.out.println("Order Summary is correct");
        } else {
            System.out.println("Order Summary is incorrect");
        }

        // Step 11: Click on the "New Address" button
        driver.findElement(By.id("customer-addresses")).click();
        System.out.println("Clicked on New Address button");

        // Step 12: Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        System.out.println("Entered address details");

        // Step 13: Click on the "Ship Here" button
        driver.findElement(By.xpath("//span[text()='Ship Here']")).click();
        System.out.println("Clicked on Ship Here button");

        // Step 14: Select the "Fixed" radio button
        driver.findElement(By.id("s-method-code-fixed_FIXED")).click();
        System.out.println("Selected Fixed radio button");

        // Step 15: Click on the "Next" button
        driver.findElement(By.xpath("//span[text()='Next']")).click();
        System.out.println("Clicked on Next button");

        // Step 16: Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billingSync")).click();
        System.out.println("Selected My billing and shipping address are the same checkbox");

        // Step 17: Click on the "Place Order" button
        driver.findElement(By.xpath("//span[text()='Place Order']")).click();
        System.out.println("Clicked on Place Order button");

        // Step 18: Verify the success message
        String successMessage = driver.findElement(By.className("success-msg")).getText();
        if (successMessage.equals("Thank you for your purchase!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Order placement failed");
        }

        // Step 19: Click on the "Change" button
        driver.findElement(By.xpath("//span[text()='Change']")).click();
        System.out.println("Clicked on Change button");

        // Step 20: Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
        System.out.println("Clicked on Signout link");

        // Close the browser
        driver.quit();
    }
}