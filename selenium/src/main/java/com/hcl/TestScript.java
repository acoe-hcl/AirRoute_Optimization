import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScript {
    public static void main(String[] args) {
        // Setting up browser
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Navigating to application
        driver.get("https://magento.softwaretestingboard.com/");

        // Clicking on Sign In link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Entering email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Clicking on Sign In button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hovering on Gear menu
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Clicking on Bags link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Clicking on Overnight Duffle image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@alt='Overnight Duffle']"));
        overnightDuffleImage.click();

        // Clicking on Add to Cart button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Clicking on My Cart link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Clicking on Proceed to Checkout button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();

        // Verifying Order Summary contains Overnight Duffle product
        WebElement orderSummary = driver.findElement(By.xpath("//div[@class='cart-totals']//strong[text()='Order Summary']"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }

        // Clicking on New Address button
        WebElement newAddressButton = driver.findElement(By.id("opc-new-address"));
        newAddressButton.click();

        // Entering address details
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");

        WebElement postalCodeField = driver.findElement(By.id("billing:postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Clicking on Ship Here button
        WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
        shipHereButton.click();

        // Selecting Fixed radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Clicking on Next button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Selecting My billing and shipping address are the same checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Clicking on Place Order button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verifying Success Page and Thank you message
        WebElement successPage = driver.findElement(By.xpath("//title[text()='Success Page']"));
        String successPageTitle = successPage.getAttribute("text");
        if (successPageTitle.equals("Success Page")) {
            System.out.println("Success Page is displayed");
        } else {
            System.out.println("Success Page is not displayed");
        }

        WebElement thankYouMessage = driver.findElement(By.xpath("//div[contains(text(), 'Thank you for your purchase!')]"));
        String thankYouMessageText = thankYouMessage.getText();
        if (thankYouMessageText.contains("Thank you for your purchase!")) {
            System.out.println("Thank you message is displayed");
        } else {
            System.out.println("Thank you message is not displayed");
        }

        // Clicking on Change button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // Clicking on Signout link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        // Closing the browser
        driver.quit();
    }
}