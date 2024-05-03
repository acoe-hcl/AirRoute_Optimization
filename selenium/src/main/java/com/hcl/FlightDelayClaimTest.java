
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FlightDelayClaimTest {
    public static void main(String[] args) {
        // Set the path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        
        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Navigate to the website
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");
        
        // Step 1: Select "Flight Delay or Cancellation Claim" tile
        WebElement flightDelayClaimTile = driver.findElement(By.linkText("Flight Delay or Cancellation Claim"));
        flightDelayClaimTile.click();
        
        // Step 2: Select "Flight Delay or Cancellation" option in the Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.id("Regarding"));
        Select regardingSelect = new Select(regardingDropdown);
        regardingSelect.selectByVisibleText("Flight Delay or Cancellation");
        
        // Step 3: Click on the "Submit expense/comment" button
        WebElement submitButton = driver.findElement(By.id("submitButton"));
        submitButton.click();
        
        // Step 4: Verify if on "PASSENGER INFORMATION" page
        WebElement passengerInfoHeader = driver.findElement(By.xpath("//h2[text()='PASSENGER INFORMATION']"));
        if (passengerInfoHeader.isDisplayed()) {
            System.out.println("On the PASSENGER INFORMATION page");
        } else {
            System.out.println("Not on the PASSENGER INFORMATION page");
        }
        
        // Step 5: Enter email address in the Email Address field
        WebElement emailAddressField = driver.findElement(By.id("EmailAddress"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");
        
        // Step 6: Enter email address in the Confirm Email Address field
        WebElement confirmEmailAddressField = driver.findElement(By.id("ConfirmEmailAddress"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");
        
        // Step 7: Select "Mr." option in the Title dropdown
        WebElement titleDropdown = driver.findElement(By.id("Title"));
        Select titleSelect = new Select(titleDropdown);
        titleSelect.selectByVisibleText("Mr.");
        
        // Step 8: Enter first name in the First Name field
        WebElement firstNameField = driver.findElement(By.id("FirstName"));
        firstNameField.sendKeys("Bharath");
        
        // Step 9: Enter last name in the Last Name field
        WebElement lastNameField = driver.findElement(By.id("LastName"));
        lastNameField.sendKeys("Ice");
        
        // Step 10: Enter permanent mailing address in the Permanent Mailing Address field
        WebElement mailingAddressField = driver.findElement(By.id("MailingAddress"));
        mailingAddressField.sendKeys("Harvest");
        
        // Step 11: Enter city in the City field
        WebElement cityField = driver.findElement(By.id("City"));
        cityField.sendKeys("Ontario");
        
        // Step 12: Enter province/state in the Province/State Field
        WebElement provinceField = driver.findElement(By.id("Province"));
        provinceField.sendKeys("ON");
        
        // Step 13: Select "ON Ontario" option in the Province/State dropdown
        WebElement provinceDropdown = driver.findElement(By.id("ProvinceDropdown"));
        Select provinceSelect = new Select(provinceDropdown);
        provinceSelect.selectByVisibleText("ON Ontario");
        
        // Step 14: Enter postal/zipcode in the Postal/Zipcode Field
        WebElement postalCodeField = driver.findElement(By.id("PostalCode"));
        postalCodeField.sendKeys("M9C 4Y1");
        
        // Step 15: Select "Canada" option in the Country/Region dropdown
        WebElement countryDropdown = driver.findElement(By.id("Country"));
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("Canada");
        
        // Step 16: Enter primary phone number in the Primary Phone No Field
        WebElement phoneField = driver.findElement(By.id("Phone"));
        phoneField.sendKeys("8801070616");
        
        // Step 17: Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("NextButton"));
        nextButton.click();
        
        // Step 18: Verify if on "FLIGHT DETAILS" page
        WebElement flightDetailsHeader = driver.findElement(By.xpath("//h2[text()='FLIGHT DETAILS']"));
        if (flightDetailsHeader.isDisplayed()) {
            System.out.println("On the FLIGHT DETAILS page");
        } else {
            System.out.println("Not on the FLIGHT DETAILS page");
        }
        
        // Step 19: Select "Air Canada" option in the Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.id("Airline"));
        Select airlineSelect = new Select(airlineDropdown);
        airlineSelect.selectByVisibleText("Air Canada");
        
        // Step 20: Enter flight number in the Flight Number field
        WebElement flightNumberField = driver.findElement(By.id("FlightNumber"));
        flightNumberField.sendKeys("122");
        
        // Step 21: Enter flight date in the Flight Date field
        WebElement flightDateField = driver.findElement(By.id("FlightDate"));
        flightDateField.sendKeys("2023-08-17");
        
        // Step 22: Enter departure airport in the Departure Airport Field
        WebElement departureAirportField = driver.findElement(By.id("DepartureAirport"));
        departureAirportField.sendKeys("YVR");
        
        // Step 23: Select "YVR Vancouver Canada (Vancouver Intl)" option in the Departure Airport dropdown
        WebElement departureAirportDropdown = driver.findElement(By.id("DepartureAirportDropdown"));
        Select departureAirportSelect = new Select(departureAirportDropdown);
        departureAirportSelect.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");
        
        // Step 24: Enter arrival airport in the Arrival Airport Field
        WebElement arrivalAirportField = driver.findElement(By.id("ArrivalAirport"));
        arrivalAirportField.sendKeys("YYZ");
        
        // Step 25: Select "YYZ Toronto Canada (Lester B. Pearson Intl)" option in the Arrival Airport dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.id("ArrivalAirportDropdown"));
        Select arrivalAirportSelect = new Select(arrivalAirportDropdown);
        arrivalAirportSelect.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");
        
        // Step 26: Enter booking reference in the Booking Reference field
        WebElement bookingReferenceField = driver.findElement(By.id("BookingReference"));
        bookingReferenceField.sendKeys("3ED8RH");
        
        // Step 27: Enter ticket number in the Ticket Number field
        WebElement ticketNumberField = driver.findElement(By.id("TicketNumber"));
        ticketNumberField.sendKeys("0142173322307");
        
        // Step 28: Click on the "Next" button
        nextButton.click();
        
        // Step 29: Verify if on "COMMENT" page
        WebElement commentHeader = driver.findElement(By.xpath("//h2[text()='COMMENT']"));
        if (commentHeader.isDisplayed()) {
            System.out.println("On the COMMENT page");
        } else {
            System.out.println("Not on the COMMENT page");
        }
        
        // Step 30: Verify the message "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[contains(text(),'Do not add any payment information in the comment field')]"));
        if (paymentInfoMessage.isDisplayed()) {
            System.out.println("Payment info message displayed");
        } else {
            System.out.println("Payment info message not displayed");
        }
        
        // Step 31: Enter subject in the Subject field
        WebElement subjectField = driver.findElement(By.id("Subject"));
        subjectField.sendKeys("Expenses");
        
        // Step 32: Click on the "Submit" button
        WebElement submitCommentButton = driver.findElement(By.id("SubmitCommentButton"));
        submitCommentButton.click();
        
        // Step 33: Verify if the message "Submission completed successfully." is displayed
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(),'Submission completed successfully.')]"));
        if (successMessage.isDisplayed()) {
            System.out.println("Submission completed successfully");
        } else {
            System.out.println("Submission not completed successfully");
        }
        
        // Close the browser
        driver.quit();
    }
}
