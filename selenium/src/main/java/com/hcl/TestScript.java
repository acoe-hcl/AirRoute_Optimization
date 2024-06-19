Sure! Here's the Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestScript {
    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Set browser name and page name variables
        String browserName = "";
        String pageName = "";

        // Step 1: Given I have set the browser name as "Home Page"
        browserName = "Home Page";

        // Step 2: Given I have set the page name as "Home Page"
        pageName = "Home Page";

        // Step 3: When I launch the application "https://magento.softwaretestingboard.com/"
        driver.get("https://magento.softwaretestingboard.com/");

        // Step 4: And I click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Step 5: Then I should set the browser name as "Customer Login"
        browserName = "Customer Login";

        // Step 6: And I should set the page name as "Customer Login"
        pageName = "Customer Login";

        // Step 7: When I enter "autotest567@gmail.com" in the "Email" field
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");

        // Step 8: And I enter "Tester@123" in the "Password" field as secure text
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Step 9: And I click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Step 10: Then I should set the browser name as "Home Page"
        browserName = "Home Page";

        // Step 11: And I should set the page name as "Home Page"
        pageName = "Home Page";

        // Step 12: When I mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Gear']"))).perform();

        // Step 13: And I click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Step 14: Then I should set the browser name as "Bags - Gear"
        browserName = "Bags - Gear";

        // Step 15: And I should set the page name as "Bags - Gear"
        pageName = "Bags - Gear";

        // Step 16: When I click on the "Overnight Duffle" image
        driver.findElement(By.xpath("//img[@alt='Overnight Duffle']")).click();

        // Step 17: Then I should set the browser name as "Overnight Duffle"
        browserName = "Overnight Duffle";

        // Step 18: And I should set the page name as "Overnight Duffle"
        pageName = "Overnight Duffle";

        // Step 19: When I click on the "Add to Cart" button
        driver.findElement(By.xpath("//button[contains(text(),'Add to Cart')]")).click();

        // Step 20: And I click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Step 21: And I click on the "Proceed to Checkout" button
        driver.findElement(By.xpath("//span[text()='Proceed to Checkout']")).click();

        // Step 22: Then I should set the browser name as "Checkout"
        browserName = "Checkout";

        // Step 23: And I should set the page name as "Checkout"
        pageName = "Checkout";

        // Step 24: And I verify that the "Order Summary" is having "Overnight Duffle" product
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h2[contains(text(),'Order Summary')]/following-sibling::table//a[contains(text(),'Overnight Duffle')]"), "Overnight Duffle"));

        // Step 25: When I click on the "New Address" button
        driver.findElement(By.xpath("//button[text()='New Address']")).click();

        // Step 26: And I enter "4 South Street" in the "Street" field
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");

        // Step 27: And I enter "Texas" in the "City" field
        driver.findElement(By.id("city")).sendKeys("Texas");

        // Step 28: And I select "Texas" from the "State/Province" dropdown
        driver.findElement(By.id("region_id")).sendKeys("Texas");

        // Step 29: And I enter "77567" in the "Zip/Postal Code" field
        driver.findElement(By.id("postcode")).sendKeys("77567");

        // Step 30: And I enter "3456788765" in the "Phone Number" field
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Step 31: And I click on the "Ship Here" button
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // Step 32: And I select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Step 33: And I click on the "Next" button
        driver.findElement(By.xpath("//span[text()='Next']")).click();

        // Step 34: And I select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // Step 35: And I click on the "Place Order" button
        driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();

        // Step 36: Then I should set the browser name as "Success Page"
        browserName = "Success Page";

        // Step 37: And I should set the page name as "Success Page"
        pageName = "Success Page";

        // Step 38: And I verify the message "Thank you for your purchase!"
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1[contains(text(),'Thank you for your purchase!')]"), "Thank you for your purchase!"));

        // Step 39: When I click on the "Change" button
        driver.findElement(By.xpath("//span[contains(text(),'Change')]")).click();

        // Step 40: And I click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();
    }
}
```

Make sure to replace `"path/to/chromedriver"` with the actual path to your ChromeDriver executable file.

Hope this helps! Let me know if you have any further questions.