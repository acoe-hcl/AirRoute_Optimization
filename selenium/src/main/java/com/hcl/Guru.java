import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Guru {

    public static void main(String[] args) {
        //Setting up Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path_to_chrome_driver");

        //Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        //Setting the browser name and page name as "Home Page"
        String browserName = "Home Page";
        String pageName = "Home Page";

        //Launching the application
        driver.get("https://magento.softwaretestingboard.com/");

        //Clicking on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        //Setting the browser name and page name as "Customer Login"
        browserName = "Customer Login";
        pageName = "Customer Login";

        //Entering email in the "Email" field
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("autotest567@gmail.com");

        //Entering password in the "Password" field
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Tester@123");

        //Clicking on the "Sign In" button
        WebElement signInButton = driver.findElement(By.cssSelector("button[title='Sign In']"));
        signInButton.click();

        //Setting the browser name and page name as "Home Page"
        browserName = "Home Page";
        pageName = "Home Page";

        //Mouse hovering on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("gearIcon"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();

        //Clicking on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        //Setting the browser name and page name as "Bags - Gear"
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        //Clicking on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.xpath("//img[@title='Overnight Duffle']"));
        overnightDuffleImage.click();

        //Setting the browser name and page name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";

        //Clicking on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button/span[text()='Add to Cart']"));
        addToCartButton.click();

        //Clicking on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        //Clicking on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button/span[text()='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        //Setting the browser name and page name as "Checkout"
        browserName = "Checkout";
        pageName = "Checkout";

        //Verifying if the "Order Summary" contains "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.className("order-summary"));
        if (orderSummary.getText().contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        }

        //Clicking on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button/span[text()='New Address']"));
        newAddressButton.click();

        //Entering address in the "Street" field
        WebElement streetField = driver.findElement(By.name("street[0]"));
        streetField.sendKeys("4 South Street");

        //Entering city in the "City" field
        WebElement cityField = driver.findElement(By.name("city"));
        cityField.sendKeys("Texas");

        //Selecting state/province from the dropdown
        WebElement stateDropdown = driver.findElement(By.name("region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");

        //Entering postal code in the "Zip/Postal Code" field
        WebElement postalCodeField = driver.findElement(By.name("postcode"));
        postalCodeField.sendKeys("77567");

        //Entering phone number in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.name("telephone"));
        phoneNumberField.sendKeys("3456788765");

        //Clicking on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button/span[text()='Ship Here']"));
        shipHereButton.click();

        //Selecting the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@value='fixed']"));
        fixedRadioButton.click();

        //Clicking on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
        nextButton.click();

        //Selecting the "My billing and shipping address are the same" checkbox
        WebElement billingShippingCheckbox = driver.findElement(By.xpath("//input[@name='billing-same-as-shipping']"));
        billingShippingCheckbox.click();

        //Clicking on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button/span[text()='Place Order']"));
        placeOrderButton.click();

        //Setting the browser name and page name as "Success Page"
        browserName = "Success Page";
        pageName = "Success Page";

        //Verifying the success message
        WebElement successMessage = driver.findElement(By.xpath("//h2[text()='Thank you for your purchase!']"));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message displayed: Thank you for your purchase!");
        }

        //Clicking on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button/span[text()='Change']"));
        changeButton.click();

        //Clicking on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();

        //Closing the browser
        driver.quit();
    }
}