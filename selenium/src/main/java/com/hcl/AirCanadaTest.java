import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AirCanadaTest {

    public static void main(String[] args) {
        // Set Chrome Driver Path
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Select the tile "General Concerns"
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@id='collapseGeneralConcerns']//button"));
        generalConcernsTile.click();

        // Select the option "At the Airport" in the Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.xpath("//select[@id='regarding']"));
        regardingDropdown.click();
        WebElement regardingOption = driver.findElement(By.xpath("//option[text()='At the Airport']"));
        regardingOption.click();

        // Select the option "Check-in" in the Issue dropdown field
        WebElement issueDropdown = driver.findElement(By.xpath("//select[@id='issue']"));
        issueDropdown.click();
        WebElement issueOption = driver.findElement(By.xpath("//option[text()='Check-in']"));
        issueOption.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@type='submit']"));
        nextButton.click();

        // Verify that we are on the PASSENGER INFORMATION page
        WebElement passengerInformationHeader = driver.findElement(By.xpath("//h2[text()='PASSENGER INFORMATION']"));
        if (passengerInformationHeader.isDisplayed()) {
            System.out.println("We are on the PASSENGER INFORMATION page");
        } else {
            System.out.println("We are not on the PASSENGER INFORMATION page");
        }

        // Enter email address
        WebElement emailAddressField = driver.findElement(By.xpath("//input[@id='emailaddress']"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Confirm email address
        WebElement confirmEmailAddressField = driver.findElement(By.xpath("//input[@id='confirmemailaddress']"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Select the option "Mr." in the Title dropdown
        WebElement titleDropdown = driver.findElement(By.xpath("//select[@id='title']"));
        titleDropdown.click();
        WebElement titleOption = driver.findElement(By.xpath("//option[text()='Mr.']"));
        titleOption.click();

        // Enter first name
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstname']"));
        firstNameField.sendKeys("Bharath");

        // Enter last name
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastname']"));
        lastNameField.sendKeys("Ice");

        // Enter permanent mailing address
        WebElement permanentMailingAddressField = driver.findElement(By.xpath("//input[@id='permanentmailingaddress']"));
        permanentMailingAddressField.sendKeys("Harvest");

        // Enter city
        WebElement cityField = driver.findElement(By.xpath("//input[@id='city']"));
        cityField.sendKeys("Ontario");

        // Enter province/state
        WebElement provinceStateField = driver.findElement(By.xpath("//input[@id='provincestate']"));
        provinceStateField.sendKeys("ON");

        // Select the option "ON Ontario" in the Province/State dropdown
        WebElement provinceStateDropdown = driver.findElement(By.xpath("//select[@id='provincestatedropdown']"));
        provinceStateDropdown.click();
        WebElement provinceStateOption = driver.findElement(By.xpath("//option[text()='ON Ontario']"));
        provinceStateOption.click();

        // Enter postal/zipcode
        WebElement postalZipcodeField = driver.findElement(By.xpath("//input[@id='postalzipcode']"));
        postalZipcodeField.sendKeys("M9C 4Y1");

        // Select the option "Canada" in the Country/Region dropdown
        WebElement countryRegionDropdown = driver.findElement(By.xpath("//select[@id='countryregion']"));
        countryRegionDropdown.click();
        WebElement countryRegionOption = driver.findElement(By.xpath("//option[text()='Canada']"));
        countryRegionOption.click();

        // Enter primary phone number
        WebElement primaryPhoneNoField = driver.findElement(By.xpath("//input[@id='primaryphoneno']"));
        primaryPhoneNoField.sendKeys("8801070616");

        // Click on the "Next" button
        nextButton.click();

        // Verify that we are on the AIRLINE INFORMATION section
        WebElement airlineInformationHeader = driver.findElement(By.xpath("//h2[text()='AIRLINE INFORMATION']"));
        if (airlineInformationHeader.isDisplayed()) {
            System.out.println("We are on the AIRLINE INFORMATION section");
        } else {
            System.out.println("We are not on the AIRLINE INFORMATION section");
        }

        // Select the option "Air Canada" in the Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.xpath("//select[@id='airline']"));
        airlineDropdown.click();
        WebElement airlineOption = driver.findElement(By.xpath("//option[text()='Air Canada']"));
        airlineOption.click();

        // Enter flight number
        WebElement flightNumberField = driver.findElement(By.xpath("//input[@id='flightnumber']"));
        flightNumberField.sendKeys("122");

        // Enter flight date
        WebElement flightDateField = driver.findElement(By.xpath("//input[@id='flightdate']"));
        flightDateField.sendKeys("2023-08-17");

        // Enter departure airport
        WebElement departureAirportField = driver.findElement(By.xpath("//input[@id='departureairport']"));
        departureAirportField.sendKeys("YVR");

        // Select the option "YVR Vancouver Canada (Vancouver Intl)" in the Departure Airport dropdown
        WebElement departureAirportDropdown = driver.findElement(By.xpath("//select[@id='departureairportdropdown']"));
        departureAirportDropdown.click();
        WebElement departureAirportOption = driver.findElement(By.xpath("//option[text()='YVR Vancouver Canada (Vancouver Intl)']"));
        departureAirportOption.click();

        // Enter arrival airport
        WebElement arrivalAirportField = driver.findElement(By.xpath("//input[@id='arrivalairport']"));
        arrivalAirportField.sendKeys("YYZ");

        // Select the option "YYZ Toronto Canada (Lester B. Pearson Intl)" in the Arrival Airport dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.xpath("//select[@id='arrivalairportdropdown']"));
        arrivalAirportDropdown.click();
        WebElement arrivalAirportOption = driver.findElement(By.xpath("//option[text()='YYZ Toronto Canada (Lester B. Pearson Intl)']"));
        arrivalAirportOption.click();

        // Enter booking reference
        WebElement bookingReferenceField = driver.findElement(By.xpath("//input[@id='bookingreference']"));
        bookingReferenceField.sendKeys("3ED8RH");

        // Enter ticket number
        WebElement ticketNumberField = driver.findElement(By.xpath("//input[@id='ticketnumber']"));
        ticketNumberField.sendKeys("0142173322307");

        // Click on the "Next" button
        nextButton.click();

        // Verify that we are on the PAYMENT INFORMATION section
        WebElement paymentInformationHeader = driver.findElement(By.xpath("//h2[text()='PAYMENT INFORMATION']"));
        if (paymentInformationHeader.isDisplayed()) {
            System.out.println("We are on the PAYMENT INFORMATION section");
        } else {
            System.out.println("We are not on the PAYMENT INFORMATION section");
        }

        // Verify that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement messageField = driver.findElement(By.xpath("//textarea[@id='message']"));
        String expectedPaymentInformationText = "Do not add any payment information in the comment field";
        String actualPaymentInformationText = messageField.getAttribute("value");
        if (expectedPaymentInformationText.equals(actualPaymentInformationText)) {
            System.out.println("The payment information text is correct");
        } else {
            System.out.println("The payment information text is incorrect");
        }

        // Enter subject
        WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
        subjectField.sendKeys("Air Canada Booking");

        // Click on the "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        // Verify the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@id='confirmation']"));
        String expectedConfirmationMessageText = "Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments. A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns. We will get back to you as soon as possible. Thank you for your patience.";
        String actualConfirmationMessageText = confirmationMessage.getText();
        if (expectedConfirmationMessageText.equals(actualConfirmationMessageText)) {
            System.out.println("The confirmation message is correct");
        } else {
            System.out.println("The confirmation message is incorrect");
        }

        // Close the browser
        driver.quit();
    }

}