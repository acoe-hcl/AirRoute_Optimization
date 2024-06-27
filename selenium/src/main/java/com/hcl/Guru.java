import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Guru {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Navigate to the given URL
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Set the browser name as "Customer Login"
        String browserName = "Customer Login";
        
        // Set the page name as "Customer Login"
        String pageName = "Customer Login";
        
        // Enter the email and password
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("autotest567@gmail.com");
        
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.cssSelector("button[title='Sign In']"));
        signInButton.click();
        
        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("gear-icon"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        
        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // Click on the "Driven Backpack" image
        WebElement backpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        backpackImage.click();
        
        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();
        
        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//td[contains(text(),'Driven Backpack')]"));
        String product = orderSummary.getText();
        if (product.contains("Driven Backpack")) {
            System.out.println("Order Summary is having Driven Backpack product");
        } else {
            System.out.println("Order Summary does not have Driven Backpack product");
        }
        
        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();
        
        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();
        
        // Enter the address details
        WebElement streetField = driver.findElement(By.id("street_1"));
        streetField.sendKeys("4 South Street");
        
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Texas");
        
        WebElement stateField = driver.findElement(By.id("region_id"));
        Select stateDropdown = new Select(stateField);
        stateDropdown.selectByVisibleText("Texas");
        
        WebElement zipCodeField = driver.findElement(By.id("postcode"));
        zipCodeField.sendKeys("77567");
        
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        phoneNumberField.sendKeys("3456788765");
        
        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();
        
        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate"));
        fixedRadioButton.click();
        
        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();
        
        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();
        
        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();
        
        // Verify the message "Thank you for your purchase!"
        WebElement thankYouMessage = driver.findElement(By.xpath("//span[text()='Thank you for your purchase!']"));
        String message = thankYouMessage.getText();
        if (message.equals("Thank you for your purchase!")) {
            System.out.println("Success message: " + message);
        } else {
            System.out.println("Expected message: Thank you for your purchase!");
            System.out.println("Actual message: " + message);
        }
        
        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();
        
        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
        
        // Quit the browser
        driver.quit();
    }
}