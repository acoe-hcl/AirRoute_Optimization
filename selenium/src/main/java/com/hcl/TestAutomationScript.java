import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set the path of ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Launch the application
        driver.get("http://iwmtar52064254:9080/maximo/webclient/login/login.jsp");

        // Step 1: User enters username and password
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement signInButton = driver.findElement(By.id("signinbutton"));

        usernameField.sendKeys("maxadmin");
        passwordField.sendKeys("maxadmin");
        signInButton.click();

        // Step 2: Verify successful login
        // Your test validation code here

        // Step 3: User hovers over Assets Application and clicks on Assets module
        WebElement assetsApplication = driver.findElement(By.id("assets-application"));
        WebElement assetsModule = driver.findElement(By.id("assets-module"));

        // Hover over Assets Application
        Actions actions = new Actions(driver);
        actions.moveToElement(assetsApplication).perform();

        // Click on Assets module
        assetsModule.click();

        // Step 4: Verify redirection to the Assets module
        // Your test validation code here

        // Step 5: User clicks on New Assets Image and enters asset details
        WebElement newAssetsImage = driver.findElement(By.id("new-assets-image"));
        newAssetsImage.click();

        WebElement assetField = driver.findElement(By.id("asset-field"));
        WebElement assetDescriptionField = driver.findElement(By.id("asset-description-field"));
        // Enter other asset details using respective locators

        assetField.sendKeys("ASSETTEST1");
        assetDescriptionField.sendKeys("Circulation Fan- Centrifugal/ 20/000 CFM");
        // Enter other asset details using respective sendKeys() method

        // Step 6: User saves the asset
        WebElement saveImage = driver.findElement(By.id("save-image"));
        saveImage.click();

        // Step 7: Verify successful asset save
        // Your test validation code here

        // Step 8: User changes asset status to "Operating"
        WebElement changeStatusButton = driver.findElement(By.id("change-status-button"));
        WebElement newStatusComboBox = driver.findElement(By.id("new-status-combobox"));
        WebElement okButton = driver.findElement(By.id("ok-button"));

        changeStatusButton.click();
        newStatusComboBox.sendKeys("Operating");
        okButton.click();

        // Step 9: Verify asset status change to "Operating"
        // Your test validation code here

        // Step 10: User clicks on "List View" button and searches for asset
        WebElement listViewButton = driver.findElement(By.id("list-view-button"));
        WebElement searchField = driver.findElement(By.id("search-field"));

        listViewButton.click();
        searchField.sendKeys("ASSETTEST1");

        // Step 11: Verify asset value in search results
        // Your test validation code here

        // Close the browser
        driver.quit();
    }
}
Hope this helps! Let me know if you have any further questions.