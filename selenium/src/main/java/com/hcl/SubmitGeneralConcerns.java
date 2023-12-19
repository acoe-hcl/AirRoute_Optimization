import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SubmitGeneralConcerns {

    public static void main(String[] args) {

        // Setting up Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Creating a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigating to the Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Selecting the "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@class='tile-content']//h3[text()='General Concerns']"));
        generalConcernsTile.click();

        // Selecting "At the Airport" in the Regarding dropdown
        Select regardingDropdown = new Select(driver.findElement(By.id("regarding")));
        regardingDropdown.selectByVisibleText("At the Airport");

        // Selecting "Check-in" in the Issue dropdown field
        Select issueDropdown = new Select(driver.findElement(By.id("issue")));
        issueDropdown.selectByVisibleText("Check-in");

        // Clicking on the "Next" button
        WebElement nextButton1 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton1.click();

        // Entering email address in the Email Address field
        WebElement emailAddressField = driver.findElement(By.id("emailaddress"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Entering email address in the Confirm Email Address field
        WebElement confirmEmailAddressField = driver.findElement(By.id("confirmemailaddress"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Selecting "Mr." in the Title dropdown
        Select titleDropdown = new Select(driver.findElement(By.id("title")));
        titleDropdown.selectByVisibleText("Mr.");

        // Entering first name in the First Name field
        WebElement firstNameField = driver.findElement(By.id("firstname"));
        firstNameField.sendKeys("Bharath");

        // Entering last name in the Last Name field
        WebElement lastNameField = driver.findElement(By.id("lastname"));
        lastNameField.sendKeys("Ice");

        // Entering permanent mailing address in the Permanent Mailing Address field
        WebElement permanentMailingAddressField = driver.findElement(By.id("address1"));
        permanentMailingAddressField.sendKeys("Harvest");

        // Entering city in the City field
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Ontario");

        // Entering province/state in the Province/State Field
        WebElement provinceStateField = driver.findElement(By.id("stateorprovince"));
        provinceStateField.sendKeys("ON");

        // Selecting "ON Ontario" in the Province/State dropdown
        Select provinceStateDropdown = new Select(driver.findElement(By.id("stateprovince")));
        provinceStateDropdown.selectByVisibleText("ON Ontario");

        // Entering postal/zipcode in the Postal/Zipcode Field
        WebElement postalZipcodeField = driver.findElement(By.id("postalcode"));
        postalZipcodeField.sendKeys("M9C 4Y1");

        // Selecting "Canada" in the Country/Region dropdown
        Select countryRegionDropdown = new Select(driver.findElement(By.id("country")));
        countryRegionDropdown.selectByVisibleText("Canada");

        // Entering primary phone number in the Primary Phone No Field
        WebElement primaryPhoneNoField = driver.findElement(By.id("primaryphonenumber"));
        primaryPhoneNoField.sendKeys("8801070616");

        // Clicking on the "Next" button
        WebElement nextButton2 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton2.click();

        // Selecting "Air Canada" in the Airline dropdown
        Select airlineDropdown = new Select(driver.findElement(By.id("airline")));
        airlineDropdown.selectByVisibleText("Air Canada");

        // Entering flight number in the Flight Number field
        WebElement flightNumberField = driver.findElement(By.id("flightnumber"));
        flightNumberField.sendKeys("122");

        // Entering flight date in the Flight Date field
        WebElement flightDateField = driver.findElement(By.id("flightdate"));
        flightDateField.sendKeys("2023-08-17");

        // Entering departure airport in the Departure Airport Field
        WebElement departureAirportField = driver.findElement(By.id("departureairport"));
        departureAirportField.sendKeys("YVR");

        // Selecting "YVR Vancouver Canada (Vancouver Intl)" in the Departure Airport dropdown
        Select departureAirportDropdown = new Select(driver.findElement(By.id("departureairportlist")));
        departureAirportDropdown.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");

        // Entering arrival airport in the Arrival Airport Field
        WebElement arrivalAirportField = driver.findElement(By.id("arrivalairport"));
        arrivalAirportField.sendKeys("YYZ");

        // Selecting "YYZ Toronto Canada (Lester B. Pearson Intl)" in the Arrival Airport dropdown
        Select arrivalAirportDropdown = new Select(driver.findElement(By.id("arrivalairportlist")));
        arrivalAirportDropdown.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Entering booking reference in the Booking Reference field
        WebElement bookingReferenceField = driver.findElement(By.id("bookingreference"));
        bookingReferenceField.sendKeys("3ED8RH");

        // Entering ticket number in the Ticket Number field
        WebElement ticketNumberField = driver.findElement(By.id("ticketnumber"));
        ticketNumberField.sendKeys("0142173322307");

        // Clicking on the "Next" button
        WebElement nextButton3 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton3.click();

        // Verifying that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInformationMessage = driver.findElement(By.xpath("//div[@class='form-group']//p[text()='Do not add any payment information in the comment field']"));
        String expectedPaymentInformationMessage = "Do not add any payment information in the comment field";
        String actualPaymentInformationMessage = paymentInformationMessage.getText();
        if (actualPaymentInformationMessage.equals(expectedPaymentInformationMessage)) {
            System.out.println("Payment information message is displayed correctly");
        } else {
            System.out.println("Payment information message is not displayed correctly");
        }

        // Entering subject in the Subject field
        WebElement subjectField = driver.findElement(By.id("subject"));
        subjectField.sendKeys("Air Canada Booking");

        // Clicking on the "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();

        // Verifying the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        String expectedConfirmationMessage = "Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.\n" +
                "A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns.\n" +
                "We will get back to you as soon as possible. Thank you for your patience.";
        String actualConfirmationMessage = confirmationMessage.getText();
        if (actualConfirmationMessage.equals(expectedConfirmationMessage)) {
            System.out.println("Confirmation message is displayed correctly");
        } else {
            System.out.println("Confirmation message is not displayed correctly");
        }

        // Closing the browser
        driver.quit();
    }
}