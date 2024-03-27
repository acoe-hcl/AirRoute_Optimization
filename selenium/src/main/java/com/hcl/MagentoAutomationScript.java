import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MagentoAutomationScript {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        Select select;

        // Given I have set the browser name as "Home Page"
        // And I have set the page name as "Home Page"

        // When I launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // And I click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Then I should set the browser name as "Customer Login"
        // And I should set the page name as "Customer Login"

        // When I enter "[autotest567@gmail.com]" in the "Email" field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        // And I enter "Tester@123" in the "Password" field as secure text
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // And I click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Then I should set the browser name as "Home Page"
        // And I should set the page name as "Home Page"

        // When I mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//li[@class='gnav-gear']//a"));
        actions.moveToElement(gearMenu).perform();

        // And I click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.xpath("//a[text()='Bags']"));
        bagsLink.click();

        // Then I should set the browser name as "Bags - Gear"
        // And I should set the page name as "Bags - Gear"

        // When I click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//a[@class='product-image'][contains(@href,'overnight-duffle')]"));
        overnightDuffleImage.click();

        // Then I should set the browser name as "Overnight Duffle"
        // And I should set the page name as "Overnight Duffle"

        // When I click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // And I click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.cssSelector(".minicart-wrapper .showcart"));
        myCartLink.click();

        // And I click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.cssSelector(".checkout-button"));
        proceedToCheckoutButton.click();

        // Then I should set the browser name as "Checkout"
        // And I should set the page name as "Checkout"

        // And I verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummaryProduct = driver.findElement(By.xpath("//td[@class='product-name']/a[contains(text(),'Overnight Duffle')]"));
        String productName = orderSummaryProduct.getText();
        if (productName.equals("Overnight Duffle")) {
            System.out.println("Order Summary has the expected product: " + productName);
        } else {
            System.out.println("Order Summary does not have the expected product");
        }

        // When I click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.cssSelector(".action.add-new-address"));
        newAddressButton.click();

        // And I enter "4 South Street" in the "Street" field
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        // And I enter "Texas" in the "City" field
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        // And I select "Texas" from the "State/Province" dropdown
        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        select = new Select(stateDropdown);
        select.selectByVisibleText("Texas");

        // And I enter "77567" in the "Zip/Postal Code" field
        WebElement zipField = driver.findElement(By.id("postcode"));
        zipField.sendKeys("77567");

        // And I enter "3456788765" in the "Phone Number" field
        WebElement phoneField = driver.findElement(By.id("telephone"));
        phoneField.sendKeys("3456788765");

        // And I click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.cssSelector(".primary.action-save-address"));
        shipHereButton.click();

        // And I select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.cssSelector("input[name='shipping'][value='fixed']"));
        fixedRadioButton.click();

        // And I click on the "Next" button
        WebElement nextButton = driver.findElement(By.cssSelector(".primary.action-next"));
        nextButton.click();

        // And I select the "My billing and shipping address are the same" checkbox
        WebElement billingShippingCheckbox = driver.findElement(By.id("billing-same-as-shipping-checkbox"));
        if (!billingShippingCheckbox.isSelected()) {
            billingShippingCheckbox.click();
        }

        // And I click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.cssSelector(".primary.action-place-order"));
        placeOrderButton.click();

        // Then I should set the browser name as "Success Page"
        // And I should set the page name as "Success Page"

        // And I verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.xpath("//span[text()='Thank you for your purchase!']"));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message is displayed");
        } else {
            System.out.println("Success message is not displayed");
        }

        // When I click on the "Change" button
        WebElement changeButton = driver.findElement(By.cssSelector(".action-change"));
        changeButton.click();

        // And I click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        driver.quit();
    }
}