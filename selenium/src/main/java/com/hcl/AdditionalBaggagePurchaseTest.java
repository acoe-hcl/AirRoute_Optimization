import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdditionalBaggagePurchaseTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testLoginToManageBooking() {
        // Access the airline's website or mobile application
        driver.get("http://example.com");

        // Login to the manage booking section
        // Add code to enter login credentials and click on the login button

        // Verify that the manage booking section is successfully loaded
        Assert.assertTrue(driver.findElement(By.id("manageBooking")).isDisplayed());
    }

    @Test(priority = 2)
    public void testPurchaseAdditionalBaggage() {
        // Locate the specific ticket that requires additional baggage purchase
        driver.findElement(By.id("ticketNumber")).sendKeys("123456789");

        // Select the option to purchase additional baggage
        driver.findElement(By.id("purchaseButton")).click();

        // Enter the desired additional weight in kilograms
        WebElement weightInput = driver.findElement(By.id("weightInput"));
        weightInput.clear();
        weightInput.sendKeys("10");

        // Confirm the additional baggage purchase
        driver.findElement(By.id("confirmButton")).click();

        // Verify that the additional baggage purchase is successfully added to the ticket
        Assert.assertTrue(driver.findElement(By.id("successMessage")).isDisplayed());

        // Verify that the option to enter the desired additional weight is available
        Assert.assertTrue(weightInput.isDisplayed());

        // Verify that the system calculates the cost of the additional baggage based on the pricing structure
        WebElement totalPrice = driver.findElement(By.id("totalPrice"));
        double price = Double.parseDouble(totalPrice.getText().replace("$", ""));
        Assert.assertEquals(price, 10.0 * 2.5, 0.01); // Assuming pricing structure is $2.5 per kilogram

        // Verify that the total payment reflects the full price of the additional baggage
        double totalPayment = Double.parseDouble(driver.findElement(By.id("totalPayment")).getText().replace("$", ""));
        Assert.assertEquals(totalPayment, price, 0.01);

        // Verify that an email confirmation is sent to the passenger
        // Add code to check if the email confirmation is sent
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
