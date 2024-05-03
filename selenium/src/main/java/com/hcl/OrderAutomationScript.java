import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderAutomationScript {
    public static void main(String[] args) {
        // Set the browser name as "Home Page"
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Set the page name as "Home Page"
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Set the browser name as "Customer Login"
        // Set the page name as "Customer Login"

        // Enter email and password
        driver.findElement(By.name("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Set the browser name as "Home Page"
        // Set the page name as "Home Page"

        // Mouse hover on the "Gear" menu and click on the "Bags" link
        // Set the browser name as "Bags - Gear"
        // Set the page name as "Bags - Gear"

        // Click on the "Overnight Duffle" image
        // Set the browser name as "Overnight Duffle"
        // Set the page name as "Overnight Duffle"

        // Click on the "Add to Cart" button
        driver.findElement(By.cssSelector("button[title='Add to Cart']")).click();

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.cssSelector("button[title='Proceed to Checkout']")).click();

        // Set the browser name as "Checkout"
        // Set the page name as "Checkout"

        // Verify that the "Order Summary" is having "Overnight Duffle" product

        // Click on the "New Address" button
        driver.findElement(By.cssSelector("button[title='New Address']")).click();

        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.cssSelector("button[title='Ship Here']")).click();

        // Select the "Fixed" radio button
        // Click on the "Next" button

        // Select the "My billing and shipping address are the same" checkbox
        // Click on the "Place Order" button
        driver.findElement(By.cssSelector("button[title='Place Order']")).click();

        // Set the browser name as "Success Page"
        // Set the page name as "Success Page"

        // Verify the message "Thank you for your purchase!"

        // Click on the "Change" button
        // Click on the "Signout" link

        // Close the browser
        driver.quit();
    }
}