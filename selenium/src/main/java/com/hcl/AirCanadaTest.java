Here is the Selenium Java code for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AirCanadaTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        driver.findElement(By.xpath("//Tile for General Concerns")).click();
        new Select(driver.findElement(By.id("Regarding dropdown"))).selectByVisibleText("At the Airport");
        new Select(driver.findElement(By.id("Issue dropdown"))).selectByVisibleText("Check-in");
        driver.findElement(By.id("Next button")).click();
        
        // Assert that we are on the PASSENGER INFORMATION page
        assert driver.getTitle().equals("PASSENGER INFORMATION");

        driver.findElement(By.id("Email Address field")).sendKeys("bharathkumar-n@hcl.com");
        driver.findElement(By.id("Confirm Email Address field")).sendKeys("bharathkumar-n@hcl.com");
        new Select(driver.findElement(By.id("Title dropdown"))).selectByVisibleText("Mr.");
        driver.findElement(By.id("First Name field")).sendKeys("Bharath");
        driver.findElement(By.id("Last Name field")).sendKeys("Ice");
        driver.findElement(By.id("Permanent Mailing Address field")).sendKeys("Harvest");
        driver.findElement(By.id("City field")).sendKeys("Ontario");
        driver.findElement(By.id("Province/State Field")).sendKeys("ON");
        new Select(driver.findElement(By.id("Province/State dropdown"))).selectByVisibleText("ON Ontario");
        driver.findElement(By.id("Postal/Zipcode Field")).sendKeys("M9C 4Y1");
        new Select(driver.findElement(By.id("Country/Region dropdown"))).selectByVisibleText("Canada");
        driver.findElement(By.id("Primary Phone No Field")).sendKeys("8801070616");
        driver.findElement(By.id("Next button")).click();

        // Assert that we are on the AIRLINE INFORMATION section
        assert driver.findElement(By.id("Airline dropdown")).isDisplayed();

        new Select(driver.findElement(By.id("Airline dropdown"))).selectByVisibleText("Air Canada");
        driver.findElement(By.id("Flight Number field")).sendKeys("122");
        driver.findElement(By.id("Flight Date field")).sendKeys("2023-08-17");
        driver.findElement(By.id("Departure Airport Field")).sendKeys("YVR");
        new Select(driver.findElement(By.id("Departure Airport dropdown"))).selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");
        driver.findElement(By.id("Arrival Airport Field")).sendKeys("YYZ");
        new Select(driver.findElement(By.id("Arrival Airport dropdown"))).selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");
        driver.findElement(By.id("Booking Reference field")).sendKeys("3ED8RH");
        driver.findElement(By.id("Ticket Number field")).sendKeys("0142173322307");
        driver.findElement(By.id("Next button")).click();

        // Assert that we are on the PAYMENT INFORMATION section
        assert driver.findElement(By.id("Payment Section")).isDisplayed();

        WebElement message = driver.findElement(By.xpath("//Message below the field"));
        assert message.getText().equals("Do not add any payment information in the comment field");

        driver.findElement(By.id("Subject field")).sendKeys("Air Canada Booking");
        driver.findElement(By.id("Submit button")).click();

        // Assert the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//Confirmation message"));
        assert confirmationMessage.getText().equals("Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments. A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns. We will get back to you as soon as possible. Thank you for your patience.");

        driver.quit();
    }
}
```

Please replace all the IDs and XPaths used in the code with the actual IDs and XPaths from your webpage. The IDs and XPaths provided in the code are just placeholders and will not work on the actual webpage.