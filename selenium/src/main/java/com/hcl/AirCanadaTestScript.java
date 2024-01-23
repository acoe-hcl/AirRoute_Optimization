import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AirCanadaTestScript {

    public static void main(String[] args) {

        // Setting up Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");

        // Creating Chrome driver instance
        WebDriver driver = new ChromeDriver();

        // Navigating to Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Selecting the "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@class='tile-title' and text()='General Concerns']"));
        generalConcernsTile.click();

        // Selecting "At the Airport" option in the Regarding dropdown
        Select regardingDropdown = new Select(driver.findElement(By.id("regarding")));
        regardingDropdown.selectByVisibleText("At the Airport");

        // Selecting "Check-in" option in the Issue dropdown field
        Select issueDropdown = new Select(driver.findElement(By.id("issue")));
        issueDropdown.selectByVisibleText("Check-in");

        // Clicking on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        nextButton.click();

        // Entering email address and confirming it
        WebElement emailAddressField = driver.findElement(By.id("emailAddress"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");

        WebElement confirmEmailAddressField = driver.findElement(By.id("confirmEmailAddress"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Selecting "Mr." option in the Title dropdown
        Select titleDropdown = new Select(driver.findElement(By.id("title")));
        titleDropdown.selectByVisibleText("Mr.");

        // Entering first and last name
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys("Bharath");

        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys("Ice");

        // Entering permanent mailing address, city, province/state, and postal/zipcode
        WebElement mailingAddressField = driver.findElement(By.id("mailingAddress"));
        mailingAddressField.sendKeys("Harvest");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Ontario");

        WebElement provinceStateField = driver.findElement(By.id("provinceState"));
        provinceStateField.sendKeys("ON");

        Select provinceStateDropdown = new Select(driver.findElement(By.id("provinceStateDropdown")));
        provinceStateDropdown.selectByVisibleText("ON Ontario");

        WebElement postalZipcodeField = driver.findElement(By.id("postalZipcode"));
        postalZipcodeField.sendKeys("M9C 4Y1");

        // Selecting "Canada" option in the Country/Region dropdown
        Select countryDropdown = new Select(driver.findElement(By.id("country")));
        countryDropdown.selectByVisibleText("Canada");

        // Entering primary phone number
        WebElement primaryPhoneNoField = driver.findElement(By.id("primaryPhoneNo"));
        primaryPhoneNoField.sendKeys("8801070616");

        // Clicking on the "Next" button
        nextButton.click();

        // Selecting "Air Canada" option in the Airline dropdown
        Select airlineDropdown = new Select(driver.findElement(By.id("airline")));
        airlineDropdown.selectByVisibleText("Air Canada");

        // Entering flight number, flight date, departure airport, and arrival airport
        WebElement flightNumberField = driver.findElement(By.id("flightNumber"));
        flightNumberField.sendKeys("122");

        WebElement flightDateField = driver.findElement(By.id("flightDate"));
        flightDateField.sendKeys("2023-08-17");

        WebElement departureAirportField = driver.findElement(By.id("departureAirport"));
        departureAirportField.sendKeys("YVR");

        Select departureAirportDropdown = new Select(driver.findElement(By.id("departureAirportDropdown")));
        departureAirportDropdown.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");

        WebElement arrivalAirportField = driver.findElement(By.id("arrivalAirport"));
        arrivalAirportField.sendKeys("YYZ");

        Select arrivalAirportDropdown = new Select(driver.findElement(By.id("arrivalAirportDropdown")));
        arrivalAirportDropdown.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Entering booking reference and ticket number
        WebElement bookingReferenceField = driver.findElement(By.id("bookingReference"));
        bookingReferenceField.sendKeys("3ED8RH");

        WebElement ticketNumberField = driver.findElement(By.id("ticketNumber"));
        ticketNumberField.sendKeys("0142173322307");

        // Clicking on the "Next" button
        nextButton.click();

        // Verifying that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[contains(text(),'Do not add any payment information in the comment field')]"));
        String expectedPaymentInfoMessage = "Do not add any payment information in the comment field";
        if(paymentInfoMessage.getText().equals(expectedPaymentInfoMessage)) {
            System.out.println("Payment information message is displayed correctly.");
        } else {
            System.out.println("Payment information message is not displayed correctly.");
        }

        // Entering subject and clicking on the "Submit" button
        WebElement subjectField = driver.findElement(By.id("subject"));
        subjectField.sendKeys("Air Canada Booking");

        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submitButton.click();

        // Verifying the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[contains(text(),'Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.')]"));
        String expectedConfirmationMessage = "Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.\n" +
                "    A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns.\n" +
                "    We will get back to you as soon as possible. Thank you for your patience.";
        if(confirmationMessage.getText().equals(expectedConfirmationMessage)) {
            System.out.println("Confirmation message is displayed correctly.");
        } else {
            System.out.println("Confirmation message is not displayed correctly.");
        }

        // Closing the browser
        driver.quit();
    }
}