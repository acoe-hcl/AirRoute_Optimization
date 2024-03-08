import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderOnMagentoScript {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Instantiate a new WebDriver instance
        WebDriver driver = new ChromeDriver();

        try {
            // Given I have set the browser name as "Home Page"
            // And I have set the page name as "Home Page"
            String browserName = "Home Page";
            String pageName = "Home Page";

            // When I launch the application "https://magento.softwaretestingboard.com/"
            driver.get("https://magento.softwaretestingboard.com/");

            // And I click on the "Sign In" link
            driver.findElement(By.linkText("Sign In")).click();

            // Then I should set the browser name as "Customer Login"
            // And I should set the page name as "Customer Login"
            browserName = "Customer Login";
            pageName = "Customer Login";

            // When I enter "autotest567@gmail.com" in the "Email" field
            driver.findElement(By.name("login[username]")).sendKeys("autotest567@gmail.com");

            // And I enter "Tester@123" in the "Password" field as secure text
            driver.findElement(By.name("login[password]")).sendKeys("Tester@123");

            // And I click on the "Sign In" button
            driver.findElement(By.id("send2")).click();

            // Then I should set the browser name as "Home Page"
            // And I should set the page name as "Home Page"
            browserName = "Home Page";
            pageName = "Home Page";

            // When I mouse hover on the "Gear" menu
            // And I click on the "Bags" link
            driver.findElement(By.linkText("Gear")).click();
            driver.findElement(By.linkText("Bags")).click();

            // Then I should set the browser name as "Bags - Gear"
            // And I should set the page name as "Bags - Gear"
            browserName = "Bags - Gear";
            pageName = "Bags - Gear";

            // When I click on the "Overnight Duffle" image
            driver.findElement(By.linkText("Overnight Duffle")).click();

            // Then I should set the browser name as "Overnight Duffle"
            // And I should set the page name as "Overnight Duffle"
            browserName = "Overnight Duffle";
            pageName = "Overnight Duffle";

            // When I click on the "Add to Cart" button
            driver.findElement(By.id("product-addtocart-button")).click();

            // And I click on the "My Cart" link
            driver.findElement(By.linkText("My Cart")).click();

            // And I click on the "Proceed to Checkout" button
            driver.findElement(By.cssSelector(".checkout-button.primary")).click();

            // Then I should set the browser name as "Checkout"
            // And I should set the page name as "Checkout"
            browserName = "Checkout";
            pageName = "Checkout";

            // And I verify that the "Order Summary" is having "Overnight Duffle" product
            String orderSummaryText = driver.findElement(By.cssSelector(".order-summary")).getText();
            if (orderSummaryText.contains("Overnight Duffle")) {
                System.out.println("Order Summary has Overnight Duffle product");
            } else {
                System.out.println("Order Summary does not have Overnight Duffle product");
            }

            // When I click on the "New Address" button
            driver.findElement(By.cssSelector(".action.action-show-popup")).click();

            // And I enter "4 South Street" in the "Street" field
            driver.findElement(By.name("street[0]")).sendKeys("4 South Street");

            // And I enter "Texas" in the "City" field
            driver.findElement(By.name("city")).sendKeys("Texas");

            // And I select "Texas" from the "State/Province" dropdown
            driver.findElement(By.name("region_id")).sendKeys("Texas");

            // And I enter "77567" in the "Zip/Postal Code" field
            driver.findElement(By.name("postcode")).sendKeys("77567");

            // And I enter "3456788765" in the "Phone Number" field
            driver.findElement(By.name("telephone")).sendKeys("3456788765");

            // And I click on the "Ship Here" button
            driver.findElement(By.cssSelector(".action-save-address.primary")).click();

            // And I select the "Fixed" radio button
            driver.findElement(By.cssSelector("#s_method_flatrate_flatrate")).click();

            // And I click on the "Next" button
            driver.findElement(By.cssSelector(".action.primary.checkout")).click();

            // And I select the "My billing and shipping address are the same" checkbox
            driver.findElement(By.id("billing-address-same-as-shipping")).click();

            // And I click on the "Place Order" button
            driver.findElement(By.cssSelector(".action.primary.checkout")).click();

            // Then I should set the browser name as "Success Page"
            // And I should set the page name as "Success Page"
            browserName = "Success Page";
            pageName = "Success Page";

            // And I verify the message "Thank you for your purchase!"
            String successMessage = driver.findElement(By.cssSelector(".message-success")).getText();
            if (successMessage.contains("Thank you for your purchase!")) {
                System.out.println("Success message displayed: Thank you for your purchase!");
            } else {
                System.out.println("Success message not displayed");
            }

            // When I click on the "Change" button
            // And I click on the "Signout" link
            driver.findElement(By.linkText("Change")).click();
            driver.findElement(By.linkText("Signout")).click();

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}