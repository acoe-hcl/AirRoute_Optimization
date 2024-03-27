import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomation {

  public static void main(String[] args) {

    // Set the path for chromedriver.exe
    System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");

    // Create a new instance of ChromeDriver
    WebDriver driver = new ChromeDriver();

    // Set the browser name and page name as "Home Page"
    String browserName = "Home Page";
    String pageName = "Home Page";

    // Launch the application
    driver.get("https://magento.softwaretestingboard.com/");

    // Click on the "Sign In" link
    WebElement signInLink = driver.findElement(By.linkText("Sign In"));
    signInLink.click();

    // Set the browser name and page name as "Customer Login"
    browserName = "Customer Login";
    pageName = "Customer Login";

    // Enter email and password
    WebElement emailField = driver.findElement(By.name("email"));
    emailField.sendKeys("autotest567@gmail.com");

    WebElement passwordField = driver.findElement(By.name("password"));
    passwordField.sendKeys("Tester@123");

    // Click on the "Sign In" button
    WebElement signInButton = driver.findElement(By.id("send2"));
    signInButton.click();

    // Set the browser name and page name as "Home Page"
    browserName = "Home Page";
    pageName = "Home Page";

    // Mouse hover on the "Gear" menu
    WebElement gearMenu = driver.findElement(By.linkText("Gear"));
    Actions action = new Actions(driver);
    action.moveToElement(gearMenu).perform();

    // Click on the "Bags" link
    WebElement bagsLink = driver.findElement(By.linkText("Bags"));
    bagsLink.click();

    // Set the browser name and page name as "Bags - Gear"
    browserName = "Bags - Gear";
    pageName = "Bags - Gear";

    // Click on the "Overnight Duffle" image
    WebElement overnightDuffleImage = driver.findElement(By.cssSelector("img[alt='Overnight Duffle']"));
    overnightDuffleImage.click();

    // Set the browser name and page name as "Overnight Duffle"
    browserName = "Overnight Duffle";
    pageName = "Overnight Duffle";

    // Click on the "Add to Cart" button
    WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
    addToCartButton.click();

    // Click on the "My Cart" link
    WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
    myCartLink.click();

    // Click on the "Proceed to Checkout" button
    WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
    proceedToCheckoutButton.click();

    // Set the browser name and page name as "Checkout"
    browserName = "Checkout";
    pageName = "Checkout";

    // Verify that the "Order Summary" is having "Overnight Duffle" product
    WebElement orderSummary = driver.findElement(By.xpath("//h2[text()='Order Summary']/following-sibling::table//a[@title='Overnight Duffle']"));
    String productName = orderSummary.getText();
    if (productName.equals("Overnight Duffle")) {
        System.out.println("Order summary contains Overnight Duffle product.");
    } else {
        System.out.println("Order summary does not contain Overnight Duffle product.");
    }

    // Click on the "New Address" button
    WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
    newAddressButton.click();

    // Enter address details
    WebElement streetField = driver.findElement(By.name("street[0]"));
    streetField.sendKeys("4 South Street");

    WebElement cityField = driver.findElement(By.name("city"));
    cityField.sendKeys("Texas");

    WebElement stateField = driver.findElement(By.name("region_id"));
    stateField.sendKeys("Texas");

    WebElement zipcodeField = driver.findElement(By.name("postcode"));
    zipcodeField.sendKeys("77567");

    WebElement phoneNumberField = driver.findElement(By.name("telephone"));
    phoneNumberField.sendKeys("3456788765");

    // Click on the "Ship Here" button
    WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
    shipHereButton.click();

    // Select the "Fixed" radio button
    WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@value='fixed']"));
    fixedRadioButton.click();

    // Click on the "Next" button
    WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
    nextButton.click();

    // Select the "My billing and shipping address are the same" checkbox
    WebElement billingCheckbox = driver.findElement(By.xpath("//input[@name='billing[use_for_shipping]']"));
    billingCheckbox.click();

    // Click on the "Place Order" button
    WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
    placeOrderButton.click();

    // Set the browser name and page name as "Success Page"
    browserName = "Success Page";
    pageName = "Success Page";

    // Verify the success message
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='checkout-success-msg']")));
    String message = successMessage.getText();
    if (message.contains("Thank you for your purchase!")) {
        System.out.println("Order placed successfully.");
    } else {
        System.out.println("Order placement failed.");
    }

    // Click on the "Change" button
    WebElement changeButton = driver.findElement(By.linkText("Change"));
    changeButton.click();

    // Click on the "Signout" link
    WebElement signoutLink = driver.findElement(By.linkText("Signout"));
    signoutLink.click();

    // Close the browser
    driver.quit();
  }
}