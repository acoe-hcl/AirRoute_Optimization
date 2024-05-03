
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationTestScript {
    public static void main(String[] args) {
        // Set the path of the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Open the Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/%22");
        
        // Select the "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@class='tile general-concerns-tile']"));
        generalConcernsTile.click();
        
        // Select the "At the Airport" option in the Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.id("Regarding"));
        regardingDropdown.sendKeys("At the Airport");
        
        // Select the "Check-in" option in the Issue dropdown field
        WebElement issueDropdown = driver.findElement(By.id("Issue"));
        issueDropdown.sendKeys("Check-in");
        
        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("Next"));
        nextButton.click();
        
        // Verify that the user is on the PASSENGER INFORMATION page
        WebElement passengerInformationTitle = driver.findElement(By.xpath("//h2[text()='PASSENGER INFORMATION']"));
        if (passengerInformationTitle.isDisplayed()) {
            System.out.println("PASS: User is on the PASSENGER INFORMATION page");
        } else {
            System.out.println("FAIL: User is not on the PASSENGER INFORMATION page");
        }
        
        // Enter the email address in the Email Address field
        WebElement emailAddressField = driver.findElement(By.id("EmailAddress"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");
        
        // Enter the confirm email address in the Confirm Email Address field
        WebElement confirmEmailAddressField = driver.findElement(By.id("ConfirmEmailAddress"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");
        
        // Select the "Mr." option in the Title dropdown
        WebElement titleDropdown = driver.findElement(By.id("Title"));
        titleDropdown.sendKeys("Mr.");
        
        // Enter the first name in the First Name field
        WebElement firstNameField = driver.findElement(By.id("FirstName"));
        firstNameField.sendKeys("Bharath");
        
        // Enter the last name in the Last Name field
        WebElement lastNameField = driver.findElement(By.id("LastName"));
        lastNameField.sendKeys("Ice");
        
        // Enter the permanent mailing address in the Permanent Mailing Address field
        WebElement mailingAddressField = driver.findElement(By.id("MailingAddress"));
        mailingAddressField.sendKeys("Harvest");
        
        // Enter the city in the City field
        WebElement cityField = driver.findElement(By.id("City"));
        cityField.sendKeys("Ontario");
        
        // Enter the province/state in both the Province/State Field and dropdown
        WebElement provinceStateField = driver.findElement(By.id("ProvinceState"));
        provinceStateField.sendKeys("ON");
        WebElement provinceStateDropdown = driver.findElement(By.id("ProvinceState-list"));
        provinceStateDropdown.sendKeys("ON Ontario");
        
        // Enter the postal/zipcode in the Postal/Zipcode Field
        WebElement postalZipcodeField = driver.findElement(By.id("PostalZipcode"));
        postalZipcodeField.sendKeys("M9C 4Y1");
        
        // Select the "Canada" option in the Country/Region dropdown
        WebElement countryRegionDropdown = driver.findElement(By.id("CountryRegion"));
        countryRegionDropdown.sendKeys("Canada");
        
        // Enter the primary phone number in the Primary Phone No Field
        WebElement primaryPhoneField = driver.findElement(By.id("PrimaryPhone"));
        primaryPhoneField.sendKeys("8801070616");
        
        // Click on the "Next" button
        nextButton.click();
        
        // Verify that the AIRLINE INFORMATION section is displayed
        WebElement airlineInformationSection = driver.findElement(By.xpath("//h2[text()='AIRLINE INFORMATION']"));
        if (airlineInformationSection.isDisplayed()) {
            System.out.println("PASS: AIRLINE INFORMATION section is displayed");
        } else {
            System.out.println("FAIL: AIRLINE INFORMATION section is not displayed");
        }
        
        // Select the "Air Canada" option in the Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.id("Airline"));
        airlineDropdown.sendKeys("Air Canada");
        
        // Enter the flight number in the Flight Number field
        WebElement flightNumberField = driver.findElement(By.id("FlightNumber"));
        flightNumberField.sendKeys("122");
        
        // Enter the flight date in the Flight Date field
        WebElement flightDateField = driver.findElement(By.id("FlightDate"));
        flightDateField.sendKeys("2023-08-17");
        
        // Enter the departure airport in the Departure Airport Field and dropdown
        WebElement departureAirportField = driver.findElement(By.id("DepartureAirport"));
        departureAirportField.sendKeys("YVR");
        WebElement departureAirportDropdown = driver.findElement(By.id("DepartureAirport-list"));
        departureAirportDropdown.sendKeys("YVR Vancouver Canada (Vancouver Intl)");
        
        // Enter the arrival airport in the Arrival Airport Field and dropdown
        WebElement arrivalAirportField = driver.findElement(By.id("ArrivalAirport"));
        arrivalAirportField.sendKeys("YYZ");
        WebElement arrivalAirportDropdown = driver.findElement(By.id("ArrivalAirport-list"));
        arrivalAirportDropdown.sendKeys("YYZ Toronto Canada (Lester B. Pearson Intl)");
        
        // Enter the booking reference in the Booking Reference field
        WebElement bookingReferenceField = driver.findElement(By.id("BookingReference"));
        bookingReferenceField.sendKeys("3ED8RH");
        
        // Enter the ticket number in the Ticket Number field
        WebElement ticketNumberField = driver.findElement(By.id("TicketNumber"));
        ticketNumberField.sendKeys("0142173322307");
        
        // Click on the "Next" button
        nextButton.click();
        
        // Verify that the PAYMENT INFORMATION section is displayed
        WebElement paymentInformationSection = driver.findElement(By.xpath("//h2[text()='PAYMENT INFORMATION']"));
        if (paymentInformationSection.isDisplayed()) {
            System.out.println("PASS: PAYMENT INFORMATION section is displayed");
        } else {
            System.out.println("FAIL: PAYMENT INFORMATION section is not displayed");
        }
        
        // Verify that the "Do not add any payment information in the comment field" message is displayed
        WebElement paymentInformationMessage = driver.findElement(By.xpath("//div[contains(text(),'Do not add any payment information in the comment field')]"));
        if (paymentInformationMessage.isDisplayed()) {
            System.out.println("PASS: 'Do not add any payment information in the comment field' message is displayed");
        } else {
            System.out.println("FAIL: 'Do not add any payment information in the comment field' message is not displayed");
        }
        
        // Enter the subject in the Subject field
        WebElement subjectField = driver.findElement(By.id("Subject"));
        subjectField.sendKeys("Air Canada Booking");
        
        // Click on the "Submit" button
        WebElement submitButton = driver.findElement(By.id("Submit"));
        submitButton.click();
        
        // Verify that the confirmation message is displayed
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[contains(text(),'Thank you for sharing your travel experience')]"));
        if (confirmationMessage.isDisplayed()) {
            System.out.println("PASS: Confirmation message is displayed");
        } else {
            System.out.println("FAIL: Confirmation message is not displayed");
        }
        
        // Close the browser
        driver.quit();
    }
}