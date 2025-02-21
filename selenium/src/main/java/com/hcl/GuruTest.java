import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GuruTest {
    private WebDriver driver;
    
    @BeforeClass
    public void setup() {
        // Set up the driver path and initialize the WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }
    
    @AfterClass
    public void teardown() {
        // Close the WebDriver
        driver.quit();
    }
    
    @Test
    public void testValidAccountID() {
        // Open the Card Management Screen
        driver.get("http://52.214.46.0/");
        
        // Enter username and password
        driver.findElement(By.id("<username-element-id>")).sendKeys("priya");
        driver.findElement(By.id("<password-element-id>")).sendKeys("Hcltech@123");
        
        // Click on Submit button
        driver.findElement(By.id("<submit-button-id>")).click();
        
        // Verify landing on Card Management Application home page
        String expectedHomePageTitle = "Card Management Application";
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle, "Failed to land on home page");
        
        // Navigate to ACCOUNT VIEW page
        driver.findElement(By.linkText("ACCOUNT VIEW")).click();
        
        // Enter valid account ID and click on Search button
        driver.findElement(By.id("<account-id-field-id>")).sendKeys("2");
        driver.findElement(By.id("<search-button-id>")).click();
        
        // Verify account information is retrieved
        WebElement accountInfoElement = driver.findElement(By.id("<account-info-element-id>"));
        Assert.assertTrue(accountInfoElement.isDisplayed(), "Failed to retrieve account information");
    }
    
    @Test
    public void testInvalidAccountIDBlank() {
        // Open the Card Management Screen
        driver.get("http://52.214.46.0/");
        
        // Leave the account ID field blank
        driver.findElement(By.id("<account-id-field-id>")).sendKeys("");
        
        // Verify error message is displayed
        WebElement errorMessageElement = driver.findElement(By.id("<error-message-element-id>"));
        String expectedErrorMessage = "Account Filter must be a non-zero 11 digit number";
        String actualErrorMessage = errorMessageElement.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Failed to display error message");
        
        // Verify system prompts the user to enter a valid account ID
        WebElement promptElement = driver.findElement(By.id("<prompt-element-id>"));
        Assert.assertTrue(promptElement.isDisplayed(), "Failed to prompt user to enter account ID");
    }
    
    @Test
    public void testInvalidAccountIDNotNumeric() {
        // Open the Card Management Screen
        driver.get("http://52.214.46.0/");
        
        // Enter an invalid account ID
        driver.findElement(By.id("<account-id-field-id>")).sendKeys("@");
        
        // Verify error message is displayed
        WebElement errorMessageElement = driver.findElement(By.id("<error-message-element-id>"));
        String expectedErrorMessage = "Account Filter must be a non-zero 11 digit number";
        String actualErrorMessage = errorMessageElement.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Failed to display error message");
        
        // Verify system prompts the user to enter a valid account ID
        WebElement promptElement = driver.findElement(By.id("<prompt-element-id>"));
        Assert.assertTrue(promptElement.isDisplayed(), "Failed to prompt user to enter account ID");
    }
    
    @Test
    public void testInvalidAccountIDLessThan11Digits() {
        // Open the Card Management Screen
        driver.get("http://52.214.46.0/");
        
        // Enter an invalid account ID
        driver.findElement(By.id("<account-id-field-id>")).sendKeys("1234567890");
        
        // Verify error message is displayed
        WebElement errorMessageElement = driver.findElement(By.id("<error-message-element-id>"));
        String expectedErrorMessage = "Account Filter must be a non-zero 11 digit number";
        String actualErrorMessage = errorMessageElement.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Failed to display error message");
    }
    
    @Test
    public void testInvalidAccountIDMoreThan11Digits() {
        // Open the Card Management Screen
        driver.get("http://52.214.46.0/");
        
        // Enter an invalid account ID
        driver.findElement(By.id("<account-id-field-id>")).sendKeys("123456789012");
        
        // Verify error message is displayed
        WebElement errorMessageElement = driver.findElement(By.id("<error-message-element-id>"));
        String expectedErrorMessage = "Account Filter must be a non-zero 11 digit number";
        String actualErrorMessage = errorMessageElement.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Failed to display error message");
    }
}
