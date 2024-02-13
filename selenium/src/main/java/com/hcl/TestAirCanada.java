Here is your Java Selenium test script:

```java
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAirCanada {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","<path_to_chromedriver>");
        WebDriver driver = new ChromeDriver();
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // General Concerns
        driver.findElement(By.xpath("xpath_of_General_Concerns_tile")).click();
        new Select(driver.findElement(By.xpath("xpath_of_Regarding_dropdown"))).selectByVisibleText("At the Airport");
        new Select(driver.findElement(By.xpath("xpath_of_Issue_dropdown"))).selectByVisibleText("Check-in");
        driver.findElement(By.xpath("xpath_of_Next_button")).click();

        // Passenger Information
        driver.findElement(By.xpath("xpath_of_Email_Address_field")).sendKeys("bharathkumar-n@hcl.com");
        driver.findElement(By.xpath("xpath_of_Confirm_Email_Address_field")).sendKeys("bharathkumar-n@hcl.com");
        new Select(driver.findElement(By.xpath("xpath_of_Title_dropdown"))).selectByVisibleText("Mr.");
        driver.findElement(By.xpath("xpath_of_First_Name_field")).sendKeys("Bharath");
        driver.findElement(By.xpath("xpath_of_Last_Name_field")).sendKeys("Ice");
        driver.findElement(By.xpath("xpath_of_Permanent_Mailing_Address_field")).sendKeys("Harvest");
        driver.findElement(By.xpath("xpath_of_City_field")).sendKeys("Ontario");
        driver.findElement(By.xpath("xpath_of_Province/State_Field")).sendKeys("ON");
        new Select(driver.findElement(By.xpath("xpath_of_Province/State_dropdown"))).selectByVisibleText("ON Ontario");
        driver.findElement(By.xpath("xpath_of_Postal/Zipcode_Field")).sendKeys("M9C 4Y1");
        new Select(driver.findElement(By.xpath("xpath_of_Country/Region_dropdown"))).selectByVisibleText("Canada");
        driver.findElement(By.xpath("xpath_of_Primary_Phone_No_Field")).sendKeys("8801070616");
        driver.findElement(By.xpath("xpath_of_Next_button")).click();

        // Airline Information
        new Select(driver.findElement(By.xpath("xpath_of_Airline_dropdown"))).selectByVisibleText("Air Canada");
        driver.findElement(By.xpath("xpath_of_Flight_Number_field")).sendKeys("122");
        driver.findElement(By.xpath("xpath_of_Flight_Date_field")).sendKeys("2023-08-17");
        driver.findElement(By.xpath("xpath_of_Departure_Airport_Field")).sendKeys("YVR");
        new Select(driver.findElement(By.xpath("xpath_of_Departure_Airport_dropdown"))).selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");
        driver.findElement(By.xpath("xpath_of_Arrival_Airport_Field")).sendKeys("YYZ");
        new Select(driver.findElement(By.xpath("xpath_of_Arrival_Airport_dropdown"))).selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");
        driver.findElement(By.xpath("xpath_of_Booking_Reference_field")).sendKeys("3ED8RH");
        driver.findElement(By.xpath("xpath_of_Ticket_Number_field")).sendKeys("0142173322307");
        driver.findElement(By.xpath("xpath_of_Next_button")).click();

        // Payment Information
        String message = driver.findElement(By.xpath("xpath_of_message_field")).getText();
        Assert.assertEquals("Do not add any payment information in the comment field", message);
        driver.findElement(By.xpath("xpath_of_Subject_field")).sendKeys("Air Canada Booking");
        driver.findElement(By.xpath("xpath_of_Submit_button")).click();

        // Confirmation Message
        String confirmationMessage = driver.findElement(By.xpath("xpath_of_confirmation_message")).getText();
        Assert.assertEquals("Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.\nA file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns.\nWe will get back to you as soon as possible. Thank you for your patience.", confirmationMessage);
        
        driver.quit();
    }
}
```

The xpaths of the web elements need to be replaced with the actual xpaths. This script assumes that you are using Google Chrome as your browser. If you are using a different browser, you will need to replace "webdriver.chrome.driver" with the appropriate driver for your browser and also replace "ChromeDriver()" with the appropriate driver object for your browser.