import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set the path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Navigate to the Magento website
            driver.get("https://magento.softwaretestingboard.com/");

            // Step 2: Click on the "Sign In" link
            driver.findElement(By.linkText("Sign In")).click();

            // Step 3: Verify the browser name and page name
            String expectedBrowserName = "Customer Login";
            String actualBrowserName = driver.getTitle();
            if (actualBrowserName.equals(expectedBrowserName)) {
                System.out.println("SUCCESS: Browser name is set as " + expectedBrowserName);
            } else {
                System.out.println("ERROR: Browser name is not set as " + expectedBrowserName);
            }

            // Step 4: Enter email address and password
            driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
            driver.findElement(By.id("pass")).sendKeys("Tester@123");

            // Step 5: Click on the "Sign In" button
            driver.findElement(By.id("send2")).click();

            // Step 6: Hover over the "Gear" menu and click on the "Bags" link
            WebElement gearMenu = driver.findElement(By.className("gear"));
            WebElement bagsLink = driver.findElement(By.linkText("Bags"));
            Actions action = new Actions(driver);
            action.moveToElement(gearMenu).build().perform();
            Thread.sleep(1000); // Wait for the menu to expand
            action.click(bagsLink).build().perform();

            // Step 7: Click on the image of the "Driven Backpack"
            driver.findElement(By.className("product-image")).click();

            // Step 8: Click on the "Add to Cart" button
            driver.findElement(By.id("product-addtocart-button")).click();

            // Step 9: Click on the "My Cart" link
            driver.findElement(By.className("my-cart")).click();

            // Step 10: Verify that "Order Summary" includes the "Driven Backpack" product
            String expectedProductName = "Driven Backpack";
            String actualProductName = driver.findElement(By.className("product-name")).getText();
            if (actualProductName.equals(expectedProductName)) {
                System.out.println("SUCCESS: Order Summary includes the product " + expectedProductName);
            } else {
                System.out.println("ERROR: Order Summary does not include the product " + expectedProductName);
            }

            // Step 11: Click on the "Proceed to Checkout" button
            driver.findElement(By.id("proceed-checkout-btn")).click();

            // Step 12: Click on the "New Address" button
            driver.findElement(By.id("new-address-btn")).click();

            // Step 13: Enter street address, city, state/province, zip/postal code, and phone number
            driver.findElement(By.id("street")).sendKeys("123 Test Street");
            driver.findElement(By.id("city")).sendKeys("Test City");
            driver.findElement(By.id("state")).sendKeys("Test State");
            driver.findElement(By.id("zip")).sendKeys("12345");
            driver.findElement(By.id("phone")).sendKeys("1234567890");

            // Step 14: Click on the "Ship Here" button
            driver.findElement(By.id("ship-here-btn")).click();

            // Step 15: Select the "Fixed" radio button
            driver.findElement(By.id("shipping-method-fixed")).click();

            // Step 16: Click on the "Next" button
            driver.findElement(By.id("next-btn")).click();

            // Step 17: Select the "My billing and shipping address are the same" checkbox
            driver.findElement(By.id("same-address")).click();

            // Step 18: Click on the "Place Order" button
            driver.findElement(By.id("place-order-btn")).click();

            // Step 19: Verify the confirmation message
            String expectedConfirmationMessage = "Thank you for your purchase!";
            String actualConfirmationMessage = driver.findElement(By.className("confirmation-message")).getText();
            if (actualConfirmationMessage.equals(expectedConfirmationMessage)) {
                System.out.println("SUCCESS: Confirmation message is displayed as \"" + expectedConfirmationMessage + "\"");
            } else {
                System.out.println("ERROR: Confirmation message is not displayed as \"" + expectedConfirmationMessage + "\"");
            }

            // Step 20: Click on the "Change" button
            driver.findElement(By.className("change-btn")).click();

            // Step 21: Click on the "Signout" link
            driver.findElement(By.linkText("Signout")).click();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
