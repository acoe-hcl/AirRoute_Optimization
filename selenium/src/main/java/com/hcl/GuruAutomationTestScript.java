import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GuruAutomationTestScript {

    public static void main(String[] args) {
        // Set system property for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path_to_chrome_driver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the Magento application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Enter email and password
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("autotest567@gmail.com");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.xpath("//button[@title='Login']"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//a[@class='greet welcome']/span[2]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the "Overnight Duffle" image
        WebElement duffleImage = driver.findElement(By.xpath("//a[@title='Overnight Duffle']"));
        duffleImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Click on the "Proceed to Checkout" button
        WebElement checkoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        checkoutButton.click();

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.xpath("//td[contains(text(),'Overnight Duffle')]"));
        String product = orderSummary.getText();
        if (product.equals("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");

        WebElement zipCodeField = driver.findElement(By.id("zip"));
        zipCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.xpath("//input[@id='billing:use_for_shipping_yes']"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verify success message
        WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you for your purchase!')]"));
        String message = successMessage.getText();
        if (message.equals("Thank you for your purchase!")) {
            System.out.println("Purchase was successful");
        } else {
            System.out.println("Purchase was not successful");
        }

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signOutLink = driver.findElement(By.linkText("Signout"));
        signOutLink.click();

        // Close the browser
        driver.quit();
    }
}