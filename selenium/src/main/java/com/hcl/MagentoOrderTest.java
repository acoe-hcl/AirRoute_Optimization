import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoOrderTest {
  
    public static void main(String[] args) {

        // Setup Chrome Driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Launch the application
            driver.get("https://magento.softwaretestingboard.com/");

            // Step 2: Click on the "Sign In" link
            WebElement signInLink = driver.findElement(By.linkText("Sign In"));
            signInLink.click();

            // Step 3: Enter email and password and click "Sign In" button
            WebElement emailField = driver.findElement(By.name("email"));
            emailField.sendKeys("autotest567@gmail.com");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("Tester@123");
            WebElement signInButton = driver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
            signInButton.click();

            // Step 4: Mouse hover on the "Gear" menu and click on "Bags" link
            WebElement gearMenu = driver.findElement(By.linkText("Gear"));
            WebElement bagsLink = driver.findElement(By.linkText("Bags"));
            Actions actions = new Actions(driver);
            actions.moveToElement(gearMenu).perform();
            actions.click(bagsLink).perform();

            // Step 5: Click on the "Overnight Duffle" image
            WebElement duffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
            duffleImage.click();

            // Step 6: Click on the "Add to Cart" button
            WebElement addToCartButton = driver.findElement(By.xpath("//button[contains(text(),'Add to Cart')]"));
            addToCartButton.click();

            // Step 7: Click on the "My Cart" link
            WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
            myCartLink.click();

            // Step 8: Click on the "Proceed to Checkout" button
            WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[contains(text(),'Proceed to Checkout')]"));
            proceedToCheckoutButton.click();

            // Step 9: Verify that the "Order Summary" is having "Overnight Duffle" product
            WebElement orderSummary = driver.findElement(By.xpath("//*[contains(text(),'Order Summary')]"));
            String orderSummaryText = orderSummary.getText();
            if (orderSummaryText.contains("Overnight Duffle")) {
                System.out.println("Order Summary is correct.");
            } else {
                System.out.println("Order Summary is incorrect.");
            }

            // Step 10: Click on the "New Address" button
            WebElement newAddressButton = driver.findElement(By.linkText("New Address"));
            newAddressButton.click();

            // Step 11: Enter address details
            WebElement streetField = driver.findElement(By.name("street[0]"));
            streetField.sendKeys("4 South Street");
            WebElement cityField = driver.findElement(By.name("city"));
            cityField.sendKeys("Texas");
            WebElement stateDropdown = driver.findElement(By.name("region_id"));
            Select stateSelect = new Select(stateDropdown);
            stateSelect.selectByVisibleText("Texas");
            WebElement postalCodeField = driver.findElement(By.name("postcode"));
            postalCodeField.sendKeys("77567");
            WebElement phoneNumberField = driver.findElement(By.name("telephone"));
            phoneNumberField.sendKeys("3456788765");
            WebElement shipHereButton = driver.findElement(By.xpath("//button[contains(text(),'Ship Here')]"));
            shipHereButton.click();

            // Step 12: Select shipping method and click on "Next" button
            WebElement fixedRadioBtn = driver.findElement(By.xpath("//input[@value='fixed']"));
            fixedRadioBtn.click();
            WebElement nextBtn = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
            nextBtn.click();

            // Step 13: Select the checkbox for same billing and shipping address
            WebElement sameAddressCheckbox = driver.findElement(By.name("shipping[billing_same_address]"));
            sameAddressCheckbox.click();

            // Step 14: Click on the "Place Order" button
            WebElement placeOrderButton = driver.findElement(By.xpath("//button[contains(text(),'Place Order')]"));
            placeOrderButton.click();

            // Step 15: Verify the success message
            WebElement successMessage = driver.findElement(By.xpath("//*[contains(text(),'Thank you for your purchase!')]"));
            String successMessageText = successMessage.getText();
            if (successMessageText.contains("Thank you for your purchase!")) {
                System.out.println("Order placed successfully.");
            } else {
                System.out.println("Failed to place order.");
            }

            // Step 16: Click on the "Change" button and then "Signout" link
            WebElement changeButton = driver.findElement(By.linkText("Change"));
            changeButton.click();
            WebElement signoutLink = driver.findElement(By.linkText("Signout"));
            signoutLink.click();
        } finally {
            // Quit the browser
            driver.quit();
        }
    }
}