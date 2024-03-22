Sure, here is the Selenium Java automation test script file for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Set the browser driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Create variables for browser name and page name
        String browserName;
        String pageName;

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
        // Code to perform the mouse hover action

        // Step 13: And I click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Step 14: Then I should set the browser name as "Bags - Gear"
        browserName = "Bags - Gear";

        // Step 15: And I should set the page name as "Bags - Gear"
        pageName = "Bags - Gear";

        // Step 16: When I click on the "Overnight Duffle" image
        driver.findElement(By.linkText("Overnight Duffle")).click();

        // Step 17: Then I should set the browser name as "Overnight Duffle"
        browserName = "Overnight Duffle";

        // Step 18: And I should set the page name as "Overnight Duffle"
        pageName = "Overnight Duffle";

        // Step 19: When I click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Step 20: And I click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Step 21: And I click on the "Proceed to Checkout" button
        driver.findElement(By.linkText("Proceed to Checkout")).click();

        // Step 22: Then I should set the browser name as "Checkout"
        browserName = "Checkout";

        // Step 23: And I should set the page name as "Checkout"
        pageName = "Checkout";

        // Step 24: And I verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.cssSelector(".order-summary")).getText();
        if (orderSummary.contains("Overnight Duffle")) {
            System.out.println("Order Summary is having the correct product.");
        } else {
            System.out.println("Order Summary is not having the correct product.");
        }

        // Step 25: When I click on the "New Address" button
        driver.findElement(By.id("shipping-new-address-trigger")).click();

        // Step 26: And I enter "4 South Street" in the "Street" field
        driver.findElement(By.id("shipping:street1")).sendKeys("4 South Street");

        // Step 27: And I enter "Texas" in the "City" field
        driver.findElement(By.id("shipping:city")).sendKeys("Texas");

        // Step 28: And I select "Texas" from the "State/Province" dropdown
        // Code to select the option from the dropdown

        // Step 29: And I enter "77567" in the "Zip/Postal Code" field
        driver.findElement(By.id("shipping:postcode")).sendKeys("77567");

        // Step 30: And I enter "3456788765" in the "Phone Number" field
        driver.findElement(By.id("shipping:telephone")).sendKeys("3456788765");

        // Step 31: And I click on the "Ship Here" button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Step 32: And I select the "Fixed" radio button
        // Code to select the radio button

        // Step 33: And I click on the "Next" button
        driver.findElement(By.cssSelector(".button[title='Next']")).click();

        // Step 34: And I select the "My billing and shipping address are the same" checkbox
        // Code to select the checkbox

        // Step 35: And I click on the "Place Order" button
        driver.findElement(By.cssSelector(".button[title='Place Order']")).click();

        // Step 36: Then I should set the browser name as "Success Page"
        browserName = "Success Page";

        // Step 37: And I should set the page name as "Success Page"
        pageName = "Success Page";

        // Step 38: And I verify the message "Thank you for your purchase!"
        String successMessage = driver.findElement(By.cssSelector(".success-msg"))
                .getText();
        if (successMessage.contains("Thank you for your purchase!")) {
            System.out.println("Success message displayed correctly.");
        } else {
            System.out.println("Success message not displayed correctly.");
        }

        // Step 39: When I click on the "Change" button
        driver.findElement(By.linkText("Change")).click();

        // Step 40: And I click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();
    }
}
```

Please note that you need to replace `"path/to/chromedriver"` with the actual path to the ChromeDriver executable on your machine. Additionally, you may need to import the required Selenium libraries and set up the WebDriver environment according to your project setup.