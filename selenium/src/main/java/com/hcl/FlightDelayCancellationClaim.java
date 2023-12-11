import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlightDelayCancellationClaim {

    public static void main(String[] args) {
        // Set the path of the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        // Navigate to the website
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Select the "Flight Delay or Cancellation Claim" tile
        WebElement flightDelayTile = driver.findElement(By.xpath("//div[@class='tile-title' and text()='Flight Delay or Cancellation Claim']"));
        flightDelayTile.click();

        // Select the "Flight Delay or Cancellation" option in the Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.id("regardingobjectid"));
        regardingDropdown.click();
        WebElement flightDelayOption = driver.findElement(By.xpath("//option[text()='Flight Delay or Cancellation']"));
        flightDelayOption.click();

        // Click on the "Submit expense/comment" button
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit expense/comment']"));
        submitButton.click();

        // Verify that we are on the "PASSENGER INFORMATION" page
        WebElement passengerInfoHeader = driver.findElement(By.xpath("//h1[text()='PASSENGER INFORMATION']"));
        assert(passengerInfoHeader.isDisplayed());

        // Enter email address in the Email Address field
        WebElement emailField = driver.findElement(By.id("emailaddress1"));
        emailField.sendKeys("bharathkumar-n@hcl.com");

        // Enter email address in the Confirm Email Address field
        WebElement confirmEmailField = driver.findElement(By.id("confirmemailaddress"));
        confirmEmailField.sendKeys("bharathkumar-n@hcl.com");

        // Select "Mr." in the Title dropdown
        WebElement titleDropdown = driver.findElement(By.id("salutation"));
        titleDropdown.click();
        WebElement mrOption = driver.findElement(By.xpath("//option[text()='Mr.']"));
        mrOption.click();

        // Enter first name in the First Name field
        WebElement firstNameField = driver.findElement(By.id("firstname"));
        firstNameField.sendKeys("Bharath");

        // Enter last name in the Last Name field
        WebElement lastNameField = driver.findElement(By.id("lastname"));
        lastNameField.sendKeys("Ice");

        // Enter permanent mailing address in the Permanent Mailing Address field
        WebElement mailingAddressField = driver.findElement(By.id("address1_composite"));
        mailingAddressField.sendKeys("Harvest");

        // Enter city in the City field
        WebElement cityField = driver.findElement(By.id("address1_city"));
        cityField.sendKeys("Ontario");

        // Enter province/state in the Province/State Field
        WebElement provinceField = driver.findElement(By.id("address1_stateorprovince"));
        provinceField.sendKeys("ON");

        // Select "ON Ontario" in the Province/State dropdown
        WebElement provinceDropdown = driver.findElement(By.id("address1_stateorprovince_i"));
        provinceDropdown.click();
        WebElement onOntarioOption = driver.findElement(By.xpath("//option[text()='ON Ontario']"));
        onOntarioOption.click();

        // Enter postal/zipcode in the Postal/Zipcode Field
        WebElement postalField = driver.findElement(By.id("address1_postalcode"));
        postalField.sendKeys("M9C 4Y1");

        // Select "Canada" in the Country/Region dropdown
        WebElement countryDropdown = driver.findElement(By.id("address1_country_i"));
        countryDropdown.click();
        WebElement canadaOption = driver.findElement(By.xpath("//option[text()='Canada']"));
        canadaOption.click();

        // Enter primary phone number in the Primary Phone No Field
        WebElement phoneField = driver.findElement(By.id("telephone1"));
        phoneField.sendKeys("8801070616");

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton.click();

        // Verify that we are on the "FLIGHT DETAILS" page
        WebElement flightDetailsHeader = driver.findElement(By.xpath("//h1[text()='FLIGHT DETAILS']"));
        assert(flightDetailsHeader.isDisplayed());

        // Select "Air Canada" in the Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.id("airline_i"));
        airlineDropdown.click();
        WebElement airCanadaOption = driver.findElement(By.xpath("//option[text()='Air Canada']"));
        airCanadaOption.click();

        // Enter flight number in the Flight Number field
        WebElement flightNumberField = driver.findElement(By.id("flightnumber"));
        flightNumberField.sendKeys("122");

        // Enter flight date in the Flight Date field
        WebElement flightDateField = driver.findElement(By.id("flightdate"));
        flightDateField.sendKeys("2023-08-17");

        // Enter departure airport in the Departure Airport Field
        WebElement departureAirportField = driver.findElement(By.id("departureairport"));
        departureAirportField.sendKeys("YVR");

        // Select "YVR Vancouver Canada (Vancouver Intl)" in the Departure Airport dropdown
        WebElement departureAirportDropdown = driver.findElement(By.id("departureairport_i"));
        departureAirportDropdown.click();
        WebElement yvrOption = driver.findElement(By.xpath("//option[text()='YVR Vancouver Canada (Vancouver Intl)']"));
        yvrOption.click();

        // Enter arrival airport in the Arrival Airport Field
        WebElement arrivalAirportField = driver.findElement(By.id("arrivalairport"));
        arrivalAirportField.sendKeys("YYZ");

        // Select "YYZ Toronto Canada (Lester B. Pearson Intl)" in the Arrival Airport dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.id("arrivalairport_i"));
        arrivalAirportDropdown.click();
        WebElement yyzOption = driver.findElement(By.xpath("//option[text()='YYZ Toronto Canada (Lester B. Pearson Intl)']"));
        yyzOption.click();

        // Enter booking reference in the Booking Reference field
        WebElement bookingReferenceField = driver.findElement(By.id("bookingreference"));
        bookingReferenceField.sendKeys("3ED8RH");

        // Enter ticket number in the Ticket Number field
        WebElement ticketNumberField = driver.findElement(By.id("ticketnumber"));
        ticketNumberField.sendKeys("0142173322307");

        // Click on the "Next" button
        nextButton.click();

        // Verify that we are on the "COMMENT" page
        WebElement commentHeader = driver.findElement(By.xpath("//h1[text()='COMMENT']"));
        assert(commentHeader.isDisplayed());

        // Verify that the message "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[@class='note' and text()='Do not add any payment information in the comment field']"));
        assert(paymentInfoMessage.isDisplayed());

        // Enter subject in the Subject field
        WebElement subjectField = driver.findElement(By.id("subject"));
        subjectField.sendKeys("Expenses");

        // Click on the "Submit" button
        submitButton.click();

        // Verify that the message "Submission completed successfully." is displayed
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='alert alert-success' and text()='Submission completed successfully.']"));
        assert(successMessage.isDisplayed());

        // Close the browser
        driver.quit();
    }

}