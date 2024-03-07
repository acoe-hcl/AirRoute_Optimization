Sure! Here is a sample Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoOrderPlacementTest {
    public static void main(String[] args) {
        // Set up Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Given I have set the browser name as "Home Page"
            String browserName = "Home Page";
            // Given I have set the page name as "Home Page"
            String pageName = "Home Page";

            // When I launch the application
            driver.get("https://magento.softwaretestingboard.com/");

            // And I click on the "Sign In" link
            WebElement signInLink = driver.findElement(By.linkText("Sign In"));
            signInLink.click();

            // Then I should set the browser name as "Customer Login"
            browserName = "Customer Login";
            // And I should set the page name as "Customer Login"
            pageName = "Customer Login";

            // When I enter the email and password
            WebElement emailField = driver.findElement(By.id("email"));
            emailField.sendKeys("autotest567@gmail.com");

            WebElement passwordField = driver.findElement(By.id("pass"));
            passwordField.sendKeys("Tester@123");

            // And I click on the "Sign In" button
            WebElement signInButton = driver.findElement(By.id("send2"));
            signInButton.click();

            // Then I should set the browser name as "Home Page"
            browserName = "Home Page";
            // And I should set the page name as "Home Page"
            pageName = "Home Page";

            // When I mouse hover on the "Gear" menu
            WebElement gearMenu = driver.findElement(By.linkText("Gear"));
            Actions actions = new Actions(driver);
            actions.moveToElement(gearMenu).perform();

            // And I click on the "Bags" link
            WebElement bagsLink = driver.findElement(By.linkText("Bags"));
            actions.moveToElement(bagsLink).click().perform();

            // Then I should set the browser name as "Bags - Gear"
            browserName = "Bags - Gear";
            // And I should set the page name as "Bags - Gear"
            pageName = "Bags - Gear";

            // When I click on the "Overnight Duffle" image
            WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
            overnightDuffleImage.click();

            // Then I should set the browser name as "Overnight Duffle"
            browserName = "Overnight Duffle";
            // And I should set the page name as "Overnight Duffle"
            pageName = "Overnight Duffle";

            // When I click on the "Add to Cart" button
            WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
            addToCartButton.click();

            // And I click on the "My Cart" link
            WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
            myCartLink.click();

            // And I click on the "Proceed to Checkout" button
            WebElement proceedToCheckoutButton = driver.findElement(By.linkText("Proceed to Checkout"));
            proceedToCheckoutButton.click();

            // Then I should set the browser name as "Checkout"
            browserName = "Checkout";
            // And I should set the page name as "Checkout"
            pageName = "Checkout";

            // And I verify that the "Order Summary" is having "Overnight Duffle" product
            WebElement orderSummary = driver.findElement(By.xpath("//div[@class='cart-item-details']/a"));
            String product = orderSummary.getText();
            if (product.contains("Overnight Duffle")) {
                System.out.println("Order Summary contains Overnight Duffle product");
            } else {
                System.out.println("Order Summary does not contain Overnight Duffle product");
            }

            // When I click on the "New Address" button
            WebElement newAddressButton = driver.findElement(By.id("shipping-new-address-form"));
            newAddressButton.click();

            // And I enter the shipping address details
            WebElement streetField = driver.findElement(By.id("shipping:street1"));
            streetField.sendKeys("4 South Street");

            WebElement cityField = driver.findElement(By.id("shipping:city"));
            cityField.sendKeys("Texas");

            WebElement stateDropdown = driver.findElement(By.id("shipping:region_id"));
            Select stateSelect = new Select(stateDropdown);
            stateSelect.selectByVisibleText("Texas");

            WebElement postalCodeField = driver.findElement(By.id("shipping:postcode"));
            postalCodeField.sendKeys("77567");

            WebElement phoneNumberField = driver.findElement(By.id("shipping:telephone"));
            phoneNumberField.sendKeys("3456788765");

            // And I click on the "Ship Here" button
            WebElement shipHereButton = driver.findElement(By.id("shipping-buttons-container"));
            shipHereButton.click();

            // And I select the "Fixed" radio button
            WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
            fixedRadioButton.click();

            // And I click on the "Next" button
            WebElement nextButton = driver.findElement(By.id("shipping-method-buttons-container"));
            nextButton.click();

            // And I select the "My billing and shipping address are the same" checkbox
            WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
            sameAddressCheckbox.click();

            // And I click on the "Place Order" button
            WebElement placeOrderButton = driver.findElement(By.id("review-buttons-container"));
            placeOrderButton.click();

            // Then I should set the browser name as "Success Page"
            browserName = "Success Page";
            // And I should set the page name as "Success Page"
            pageName = "Success Page";

            // And I verify the message "Thank you for your purchase!"
            WebElement successMessage = driver.findElement(By.xpath("//span[@class='success-msg']"));
            String message = successMessage.getText();
            if (message.contains("Thank you for your purchase!")) {
                System.out.println("Order placed successfully");
            } else {
                System.out.println("Failed to place the order");
            }

            // When I click on the "Change" button
            WebElement changeButton = driver.findElement(By.linkText("Change"));
            changeButton.click();

            // And I click on the "Signout" link
            WebElement signoutLink = driver.findElement(By.linkText("Signout"));
            signoutLink.click();
        } finally {
            // Quit the browser
            driver.quit();
        }
    }
}
```

Note: Before running the script, make sure to set the correct path to the ChromeDriver executable on your machine. The code uses the ChromeDriver to interact with the browser.