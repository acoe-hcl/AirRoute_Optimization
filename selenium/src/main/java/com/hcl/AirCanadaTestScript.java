import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AirCanadaTestScript {

    public static void main(String[] args) throws InterruptedException {
        //Setup Chrome Driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        //Navigate to Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        //Select General Concerns tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@class='tile'][text()='General Concerns']"));
        generalConcernsTile.click();

        //Select "At the Airport" in the Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.xpath("//select[@name='regarding']/option[text()='At the Airport']"));
        regardingDropdown.click();

        //Select "Check-in" in the Issue dropdown field
        WebElement issueDropdown = driver.findElement(By.xpath("//select[@name='issue']/option[text()='Check-in']"));
        issueDropdown.click();

        //Click on Next button
        WebElement nextButton1 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton1.click();

        //Fill in Passenger Information
        WebElement emailAddressField = driver.findElement(By.xpath("//input[@name='emailAddress']"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");

        WebElement confirmEmailAddressField = driver.findElement(By.xpath("//input[@name='emailAddressConfirm']"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");

        WebElement titleDropdown = driver.findElement(By.xpath("//select[@name='title']/option[text()='Mr.']"));
        titleDropdown.click();

        WebElement firstNameField = driver.findElement(By.xpath("//input[@name='firstName']"));
        firstNameField.sendKeys("Bharath");

        WebElement lastNameField = driver.findElement(By.xpath("//input[@name='lastName']"));
        lastNameField.sendKeys("Ice");

        WebElement mailingAddressField = driver.findElement(By.xpath("//textarea[@name='mailingAddress']"));
        mailingAddressField.sendKeys("Harvest");

        WebElement cityField = driver.findElement(By.xpath("//input[@name='city']"));
        cityField.sendKeys("Ontario");

        WebElement provinceStateField = driver.findElement(By.xpath("//input[@name='stateProvince']"));
        provinceStateField.sendKeys("ON");

        WebElement provinceStateDropdown = driver.findElement(By.xpath("//select[@name='stateProvince']/option[text()='ON Ontario']"));
        provinceStateDropdown.click();

        WebElement postalZipcodeField = driver.findElement(By.xpath("//input[@name='postalCode']"));
        postalZipcodeField.sendKeys("M9C 4Y1");

        WebElement countryRegionDropdown = driver.findElement(By.xpath("//select[@name='country']/option[text()='Canada']"));
        countryRegionDropdown.click();

        WebElement primaryPhoneNoField = driver.findElement(By.xpath("//input[@name='primaryPhoneNumber']"));
        primaryPhoneNoField.sendKeys("8801070616");

        //Click on Next button
        WebElement nextButton2 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton2.click();

        //Fill in Airline Information
        WebElement airlineDropdown = driver.findElement(By.xpath("//select[@name='airline']/option[text()='Air Canada']"));
        airlineDropdown.click();

        WebElement flightNumberField = driver.findElement(By.xpath("//input[@name='flightNumber']"));
        flightNumberField.sendKeys("122");

        WebElement flightDateField = driver.findElement(By.xpath("//input[@name='flightDate']"));
        flightDateField.sendKeys("2023-08-17");

        WebElement departureAirportField = driver.findElement(By.xpath("//input[@name='departureAirport']"));
        departureAirportField.sendKeys("YVR");

        WebElement departureAirportDropdown = driver.findElement(By.xpath("//select[@name='departureAirport']/option[text()='YVR Vancouver Canada (Vancouver Intl)']"));
        departureAirportDropdown.click();

        WebElement arrivalAirportField = driver.findElement(By.xpath("//input[@name='arrivalAirport']"));
        arrivalAirportField.sendKeys("YYZ");

        WebElement arrivalAirportDropdown = driver.findElement(By.xpath("//select[@name='arrivalAirport']/option[text()='YYZ Toronto Canada (Lester B. Pearson Intl)']"));
        arrivalAirportDropdown.click();

        WebElement bookingReferenceField = driver.findElement(By.xpath("//input[@name='bookingReference']"));
        bookingReferenceField.sendKeys("3ED8RH");

        WebElement ticketNumberField = driver.findElement(By.xpath("//input[@name='ticketNumber']"));
        ticketNumberField.sendKeys("0142173322307");

        //Click on Next button
        WebElement nextButton3 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton3.click();

        //Verify "Do not add any payment information in the comment field" message is displayed
        WebElement messageField = driver.findElement(By.xpath("//textarea[@name='message']"));
        String messageText = messageField.getText();
        if (messageText.contains("Do not add any payment information in the comment field")) {
            System.out.println("Message field contains correct message");
        } else {
            System.out.println("Message field does not contain correct message");
        }

        //Fill in Subject field and Submit form
        WebElement subjectField = driver.findElement(By.xpath("//input[@name='subject']"));
        subjectField.sendKeys("Air Canada Booking");

        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();

        //Verify confirmation message is displayed
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        String confirmationText = confirmationMessage.getText();
        if (confirmationText.contains("Thank you for sharing your travel experience")) {
            System.out.println("Confirmation message displayed successfully");
        } else {
            System.out.println("Confirmation message not displayed");
        }

        //Close browser
        driver.quit();
    }

}