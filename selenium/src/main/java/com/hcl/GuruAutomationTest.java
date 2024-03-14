import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GuruAutomationTest {

    public static void main(String[] args) throws InterruptedException {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

        // Create a new instance of ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);

        // Given I have set the browser name as "Home Page"
        String browserName = "Home Page";
        // And I have set the page name as "Home Page"
        String pageName = "Home Page";
        
        // When I launch the application "[https://magento.softwaretestingboard.com/|https://magento.softwaretestingboard.com/]"
        driver.get("https://magento.softwaretestingboard.com/");
        
        // And I click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Then I should set the browser name as "Customer Login"
        browserName = "Customer Login";
        // And I should set the page name as "Customer Login"
        pageName = "Customer Login";
        
        // When I enter "[autotest567@gmail.com|mailto:autotest567@gmail.com]" in the "Email" field
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        
        // And I enter "Tester@123" in the "Password" field as secure text
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        
        // And I click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
        
        // Then I should set the browser name as "Home Page"
        browserName = "Home Page";
        // And I should set the page name as "Home Page"
        pageName = "Home Page";
        
        // When I mouse hover on the "Gear" menu
        // And I click on the "Bags" link
        driver.findElement(By.xpath("//span[text()='Gear']")).click();
        driver.findElement(By.xpath("//a[text()='Bags']")).click();
        
        // Then I should set the browser name as "Bags - Gear"
        browserName = "Bags - Gear";
        // And I should set the page name as "Bags - Gear"
        pageName = "Bags - Gear";
        
        // When I click on the "Overnight Duffle" image
        driver.findElement(By.xpath("//a[contains(text(),'Overnight Duffle')]")).click();
        
        // Then I should set the browser name as "Overnight Duffle"
        browserName = "Overnight Duffle";
        // And I should set the page name as "Overnight Duffle"
        pageName = "Overnight Duffle";
        
        // When I click on the "Add to Cart" button
        driver.findElement(By.xpath("//span[text()='Add to Cart']")).click();
        
        // And I click on the "My Cart" link
        driver.findElement(By.xpath("//a[text()='My Cart']")).click();
        
        // And I click on the "Proceed to Checkout" button
        driver.findElement(By.xpath("//button[text()='Proceed to Checkout']")).click();
        
        // Then I should set the browser name as "Checkout"
        browserName = "Checkout";
        // And I should set the page name as "Checkout"
        pageName = "Checkout";
        
        // And I verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.xpath("//td[text()='Overnight Duffle']")).getText();
        if (orderSummary.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }
        
        // When I click on the "New Address" button
        driver.findElement(By.xpath("//a[text()='New Address']")).click();
        
        // And I enter "4 South Street" in the "Street" field
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        
        // And I enter "Texas" in the "City" field
        driver.findElement(By.id("city")).sendKeys("Texas");
        
        // And I select "Texas" from the "State/Province" dropdown
        driver.findElement(By.id("region")).sendKeys("Texas");
        
        // And I enter "77567" in the "Zip/Postal Code" field
        driver.findElement(By.id("postcode")).sendKeys("77567");
        
        // And I enter "3456788765" in the "Phone Number" field
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        
        // And I click on the "Ship Here" button
        driver.findElement(By.xpath("//button[text()='Ship Here']")).click();
        
        // And I select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        
        // And I click on the "Next" button
        driver.findElement(By.xpath("//button[text()='Next']")).click();
        
        // And I select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing-address-same-as-shipping")).click();
        
        // And I click on the "Place Order" button
        driver.findElement(By.xpath("//span[text()='Place Order']")).click();
        
        // Then I should set the browser name as "Success Page"
        browserName = "Success Page";
        // And I should set the page name as "Success Page"
        pageName = "Success Page";
        
        // And I verify the message "Thank you for your purchase!"
        String successMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']")).getText();
        if (successMessage.equals("Thank you for your purchase!")) {
            System.out.println("Success message is displayed");
        } else {
            System.out.println("Success message is not displayed");
        }
        
        // When I click on the "Change" button
        driver.findElement(By.xpath("//a[text()='Change']")).click();
        
        // And I click on the "Signout" link
        driver.findElement(By.xpath("//a[text()='Signout']")).click();
        
        // Close the browser
        driver.quit();
    }

}