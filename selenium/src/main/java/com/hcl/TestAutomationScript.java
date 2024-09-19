import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class TestAutomationScript {
    public static void main(String[] args) {
        // Set Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Select the tile "General Concerns"
        WebElement generalConcernsTile = driver.findElement(By.id("general-concerns-tile"));
        generalConcernsTile.click();

        // Select the option "At the Airport" in the Regarding dropdown
        Select regardingDropdown = new Select(driver.findElement(By.id("regarding")));
        regardingDropdown.selectByVisibleText("At the Airport");

        // Select the option "Check-in" in the Issue dropdown field
        Select issueDropdown = new Select(driver.findElement(By.id("issue")));
        issueDropdown.selectByVisibleText("Check-in");

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//input[@value='Next']"));
        nextButton.click();

        // Verify that the user is on the PASSENGER INFORMATION page
        WebElement passengerInformationTitle = driver.findElement(By.xpath("//h2[text()='PASSENGER INFORMATION']"));
        if (passengerInformationTitle.isDisplayed()) {
            System.out.println("User is on the PASSENGER INFORMATION page");
        } else {
            System.out.println("User is not on the PASSENGER INFORMATION page");
            driver.quit();
            return;
        }

        // Enter email address in the Email Address field
        WebElement emailAddressField = driver.findElement(By.id("emailAddress"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Enter email address in the Confirm Email Address field
        WebElement confirmEmailAddressField = driver.findElement(By.id("confirmEmailAddress"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Select the option "Mr." in the Title dropdown
        Select titleDropdown = new Select(driver.findElement(By.id("title")));
        titleDropdown.selectByVisibleText("Mr.");

        // Enter "Bharath" in the First Name field
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys("Bharath");

        // Enter "Ice" in the Last Name field
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys("Ice");

        // Enter "Harvest" in the Permanent Mailing Address field
        WebElement permanentMailingAddressField = driver.findElement(By.id("permanentMailingAddress"));
        permanentMailingAddressField.sendKeys("Harvest");

        // Enter "Ontario" in the City field
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Ontario");

        // Enter "ON" in the Province/State Field
        WebElement provinceStateField = driver.findElement(By.id("provinceState"));
        provinceStateField.sendKeys("ON");

        // Select the option "ON Ontario" in the Province/State dropdown
        Select provinceStateDropdown = new Select(driver.findElement(By.id("provinceStateId")));
        provinceStateDropdown.selectByVisibleText("ON Ontario");

        // Enter "M9C 4Y1" in the Postal/Zipcode Field
        WebElement postalZipCodeField = driver.findElement(By.id("postalZipCode"));
        postalZipCodeField.sendKeys("M9C 4Y1");

        // Select the option "Canada" in the Country/Region dropdown
        Select countryRegionDropdown = new Select(driver.findElement(By.id("countryRegionId")));
        countryRegionDropdown.selectByVisibleText("Canada");

        // Enter "8801070616" in the Primary Phone No Field
        WebElement primaryPhoneNoField = driver.findElement(By.id("primaryPhoneNo"));
        primaryPhoneNoField.sendKeys("8801070616");

        // Click on the "Next" button
        nextButton.click();

        // Verify that the AIRLINE INFORMATION section is displayed
        WebElement airlineInformationSection = driver.findElement(By.xpath("//h2[text()='AIRLINE INFORMATION']"));
        if (airlineInformationSection.isDisplayed()) {
            System.out.println("AIRLINE INFORMATION section is displayed");
        } else {
            System.out.println("AIRLINE INFORMATION section is not displayed");
            driver.quit();
            return;
        }

        // Select the option "Air Canada" in the Airline dropdown
        Select airlineDropdown = new Select(driver.findElement(By.id("airline")));
        airlineDropdown.selectByVisibleText("Air Canada");

        // Enter "122" in the Flight Number field
        WebElement flightNumberField = driver.findElement(By.id("flightNumber"));
        flightNumberField.sendKeys("122");

        // Enter "2023-08-17" in the Flight Date field
        WebElement flightDateField = driver.findElement(By.id("flightDate"));
        flightDateField.sendKeys("2023-08-17");

        // Enter "YVR" in the Departure Airport Field
        WebElement departureAirportField = driver.findElement(By.id("departureAirport"));
        departureAirportField.sendKeys("YVR");

        // Select the option "YVR Vancouver Canada (Vancouver Intl)" in the Departure Airport dropdown
        Select departureAirportDropdown = new Select(driver.findElement(By.id("departureAirportId")));
        departureAirportDropdown.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");

        // Enter "YYZ" in the Arrival Airport Field
        WebElement arrivalAirportField = driver.findElement(By.id("arrivalAirport"));
        arrivalAirportField.sendKeys("YYZ");

        // Select the option "YYZ Toronto Canada (Lester B. Pearson Intl)" in the Arrival Airport dropdown
        Select arrivalAirportDropdown = new Select(driver.findElement(By.id("arrivalAirportId")));
        arrivalAirportDropdown.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Enter "3ED8RH" in the Booking Reference field
        WebElement bookingReferenceField = driver.findElement(By.id("bookingReference"));
        bookingReferenceField.sendKeys("3ED8RH");

        // Enter "0142173322307" in the Ticket Number field
        WebElement ticketNumberField = driver.findElement(By.id("ticketNumber"));
        ticketNumberField.sendKeys("0142173322307");

        // Click on the "Next" button
        nextButton.click();

        // Verify that the PAYMENT INFORMATION section is displayed
        WebElement paymentInformationSection = driver.findElement(By.xpath("//h2[text()='PAYMENT INFORMATION']"));
        if (paymentInformationSection.isDisplayed()) {
            System.out.println("PAYMENT INFORMATION section is displayed");
        } else {
            System.out.println("PAYMENT INFORMATION section is not displayed");
            driver.quit();
            return;
        }

        // Verify that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInformationMessage = driver.findElement(By.xpath("//p[text()='Do not add any payment information in the comment field']"));
        if (paymentInformationMessage.isDisplayed()) {
            System.out.println("Payment information message is displayed");
        } else {
            System.out.println("Payment information message is not displayed");
        }

        // Enter "Air Canada Booking" in the Subject field
        WebElement subjectField = driver.findElement(By.id("subject"));
        subjectField.sendKeys("Air Canada Booking");

        // Click on the "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
        submitButton.click();

        // Verify the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='confirmation-message']"));
        String expectedMessage = "Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.\n" +
                "A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns.\n" +
                "We will get back to you as soon as possible. Thank you for your patience.";
        if (confirmationMessage.getText().equals(expectedMessage)) {
            System.out.println("Confirmation message is displayed");
        } else {
            System.out.println("Confirmation message is not displayed");
        }

        // Close the browser
        driver.quit();
    }
}
