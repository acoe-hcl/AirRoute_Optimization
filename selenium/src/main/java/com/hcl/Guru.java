import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Guru {

    public static void main(String[] args) throws InterruptedException {
        
        // Set browser name and page name
        String browserName;
        String pageName;
        
        // Set up Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        
        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");
        browserName = "Home Page";
        pageName = "Home Page";
        
        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        browserName = "Customer Login";
        pageName = "Customer Login";
        
        // Enter email and password
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("autotest567@gmail.com");
        
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.tagName("button"));
        signInButton.click();
        browserName = "Home Page";
        pageName = "Home Page";
        
        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("gear"));
        actions.moveToElement(gearMenu).perform();
        
        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";
        
        // Click on the "Overnight Duffle" image
        WebElement overnightDuffleImage = driver.findElement(By.className("duffleImage"));
        overnightDuffleImage.click();
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";
        
        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.className("addToCartButton"));
        addToCartButton.click();
        
        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.className("proceedToCheckoutButton"));
        proceedToCheckoutButton.click();
        browserName = "Checkout";
        pageName = "Checkout";
        
        // Verify "Order Summary" contains "Overnight Duffle" product
        WebElement orderSummary = driver.findElement(By.className("orderSummary"));
        String orderSummaryText = orderSummary.getText();
        if (orderSummaryText.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }
        
        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.className("newAddressButton"));
        newAddressButton.click();
        
        // Enter address details
        WebElement streetField = driver.findElement(By.name("street"));
        streetField.sendKeys("4 South Street");
        
        WebElement cityField = driver.findElement(By.name("city"));
        cityField.sendKeys("Texas");
        
        WebElement stateDropdown = driver.findElement(By.name("state"));
        stateDropdown.sendKeys("Texas");
        
        WebElement postalCodeField = driver.findElement(By.name("postal"));
        postalCodeField.sendKeys("77567");
        
        WebElement phoneNumberField = driver.findElement(By.name("phone"));
        phoneNumberField.sendKeys("3456788765");
        
        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.className("shipHereButton"));
        shipHereButton.click();
        
        // Select "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("fixed"));
        fixedRadioButton.click();
        
        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.className("nextButton"));
        nextButton.click();
        
        // Select "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("sameAddress"));
        sameAddressCheckbox.click();
        
        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.className("placeOrderButton"));
        placeOrderButton.click();
        browserName = "Success Page";
        pageName = "Success Page";
        
        // Verify success message
        WebElement successMessage = driver.findElement(By.className("successMessage"));
        String successMessageText = successMessage.getText();
        if (successMessageText.equals("Thank you for your purchase!")) {
            System.out.println("Success message is displayed");
        } else {
            System.out.println("Success message is not displayed");
        }
        
        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.className("changeButton"));
        changeButton.click();
        
        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
        
        // Close the browser
        driver.quit();
    }
}