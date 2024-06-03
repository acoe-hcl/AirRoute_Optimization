import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Guru {

    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the Card Management Screen
        driver.get("http://52.214.46.0/");

        // Enter username and password
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameInput.sendKeys("priya");
        passwordInput.sendKeys("Hcltech@123");
        submitButton.click();

        // Verify user lands on Card Management Application home page
        WebElement homePageHeaderText = driver.findElement(By.xpath("//h1[contains(text(),'Card Management Application')]"));
        if(homePageHeaderText.getText().equals("Card Management Application")) {
            System.out.println("User landed on Card Management Application home page");
        } else {
            System.out.println("User did not land on Card Management Application home page");
        }

        // Navigate to ACCOUNT UPDATE page
        WebElement accountUpdateLink = driver.findElement(By.linkText("ACCOUNT UPDATE"));
        accountUpdateLink.click();

        // Enter valid account ID and click Search button
        WebElement accountIDInput = driver.findElement(By.id("accountID"));
        WebElement searchButton = driver.findElement(By.id("search"));

        accountIDInput.sendKeys("2");
        searchButton.click();

        // Verify account information is retrieved
        WebElement accountInfo = driver.findElement(By.id("accountInfo"));
        if(!accountInfo.getText().isEmpty()) {
            System.out.println("Account information retrieved");
        } else {
            System.out.println("Failed to retrieve account information");
        }

        // Enter Credit Limit and click Save button
        WebElement creditLimitInput = driver.findElement(By.id("creditLimit"));
        WebElement saveButton = driver.findElement(By.id("save"));

        creditLimitInput.sendKeys("6150");
        saveButton.click();

        // Verify account information is updated in the database
        WebElement successMessage = driver.findElement(By.id("successMessage"));
        if(successMessage.getText().equals("Changes committed to database")) {
            System.out.println("Account information updated in the database");
        } else {
            System.out.println("Failed to update account information in the database");
        }

        // Verify system pop up displays success message
        // Assuming the success message is displayed in an alert box
        if(driver.switchTo().alert().getText().equals("Changes committed to database")) {
            System.out.println("Success message displayed in system pop up");
        } else {
            System.out.println("Failed to display success message in system pop up");
        }

        // Close the browser
        driver.quit();
    }
}