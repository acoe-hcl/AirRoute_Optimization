import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Guru {
    public static void main(String[] args) {
        // Set the browser name as "Home Page"
        String browserName = "Home Page";
        // Set the page name as "Home Page"
        String pageName = "Home Page";

        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
        // Initialize ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Change browser name and page name to "Customer Login"
        browserName = "Customer Login";
        pageName = "Customer Login";

        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Change browser name and page name back to "Home Page"
        browserName = "Home Page";
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.id("gear"));
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Change browser name and page name to "Bags - Gear"
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        // Click on the "Overnight Duffle" image
        driver.findElement(By.linkText("Overnight Duffle")).click();

        // Change browser name and page name to "Overnight Duffle"
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";

        // Click on the "Add to Cart" button
        driver.findElement(By.id("add-to-cart-button")).click();

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.linkText("Proceed to Checkout")).click();

        // Change browser name and page name to "Checkout"
        browserName = "Checkout";
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.id("order-summary")).getText();
        if (orderSummary.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }
        

        // Click on the "New Address" button
        driver.findElement(By.id("new-address")).click();

        // Enter address details
        driver.findElement(By.id("street")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("state")).sendKeys("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("phone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.id("ship-here")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.id("fixed")).click();

        // Click on the "Next" button
        driver.findElement(By.id("next")).click();

        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("same-address")).click();

        // Click on the "Place Order" button
        driver.findElement(By.id("place-order")).click();

        // Change browser name and page name to "Success Page"
        browserName = "Success Page";
        pageName = "Success Page";

        // Verify the success message
        String successMessage = driver.findElement(By.id("success-message")).getText();
        if (successMessage.contains("Thank you for your purchase!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Failed to place the order");
        }

        // Click on the "Change" button
        driver.findElement(By.id("change")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();
    }
}