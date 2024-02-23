
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MagentoAutomation{
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();

        try{
            driver.get("https://magento.softwaretestingboard.com/");

            //Perform Sign In
            WebElement signInLink = driver.findElement(By.id("SignIn"));
            signInLink.click();

            WebElement emailField = driver.findElement(By.id("Email"));
            emailField.sendKeys("autotest567@gmail.com");

            WebElement passwordField = driver.findElement(By.id("Password"));
            passwordField.sendKeys("Tester@123");

            WebElement signInButton = driver.findElement(By.id("SignInButton"));
            signInButton.click();

            //Select Overnight Duffle product
            WebElement gearMenu = driver.findElement(By.id("Gear"));
            WebElement bagsLink = driver.findElement(By.linkText("Bags"));
            bagsLink.Click();

            WebElement overnightDuffleImage = driver.findElement(By.id("OvernightDuffleImage"));
            overnightDuffleImage.click();
            
            WebElement addToCartButton = driver.findElement(By.name("AddToCartButton"));
            addToCartButton.click();

            WebElement proceedToCheckoutButton = driver.findElement(By.name("ProceedToCheckout"));
            proceedToCheckoutButton.click();

            //New Address
            WebElement newAddressButton = driver.findElement(By.name("NewAddressButton"));
            newAddressButton.click();
            
            WebElement streetField = driver.findElement(By.name("StreetField"));
            streetField.sendKeys("4 South Street");
            
            WebElement cityField = driver.findElement(By.name("CityField"));
            cityField.sendKeys("Texas");

            Select stateDropdown = new Select(driver.findElement(By.id("StateProvinceDropdown")));
            stateDropdown.selectByVisibleText("Texas");

            WebElement zipField = driver.findElement(By.name("ZipField"));
            zipField.sendKeys("77567");

            WebElement phoneNumberField = driver.findElement(By.name("PhoneNumberField"));
            phoneNumberField.sendKeys("3456788765");

            WebElement shipHereButton = driver.findElement(By.name("ShipHereButton"));
            shipHereButton.click();

            //Place Order
            WebElement placeOrderButton = driver.findElement(By.name("PlaceOrderButton"));
            placeOrderButton.click();
            
            //Sign out
            WebElement changeButton = driver.findElement(By.name("ChangeButton"));
            changeButton.click();

            WebElement signoutLink = driver.findElement(By.linkText("Signout"));
            signoutLink.Click();
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            driver.quit();
        }
    }
}
```
Remember to replace the By.id or By.name with the actual identifier for the elements in your web page. Also, note that explicit waits and validations are not implemented in this script; you will need to add those to make the script more reliable.

Please manage the ChromeDriver executable path prior to execution by setting system property "webdriver.chrome.driver" with actual ChromeDriver location.