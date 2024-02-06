// Importing required packages and classes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Guru {
    public static void main(String[] args) {
        // Setting system properties for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Creating an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigating to Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Selecting the "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[contains(@class, 'tile-grid-item') and contains(text(), 'General Concerns')]"));
        generalConcernsTile.click();

        // Selecting "At the Airport" in the Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.id("regarding"));
        Select regardingSelect = new Select(regardingDropdown);
        regardingSelect.selectByVisibleText("At the Airport");

        // Selecting "Check-in" in the Issue dropdown field
        WebElement issueDropdown = driver.findElement(By.id("issue"));
        Select issueSelect = new Select(issueDropdown);
        issueSelect.selectByVisibleText("Check-in");

        // Clicking on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(), 'Next')]"));
        nextButton.click();

        // Verifying that we are on the PASSENGER INFORMATION page
        String expectedUrl = "https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/paxinfo";
        String actualUrl = driver.getCurrentUrl();
        if (!actualUrl.equals(expectedUrl)) {
            System.out.println("Error: Not on PASSENGER INFORMATION page");
            driver.quit();
            return;
        }

        // Entering email address in the Email Address and Confirm Email Address fields
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("bharathkumar-n@hcl.com");

        WebElement confirmEmailField = driver.findElement(By.id("confirmemail"));
        confirmEmailField.sendKeys("bharathkumar-n@hcl.com");

        // Selecting "Mr." in the Title dropdown
        WebElement titleDropdown = driver.findElement(By.id("title"));
        Select titleSelect = new Select(titleDropdown);
        titleSelect.selectByVisibleText("Mr.");

        // Entering first name and last name in the respective fields
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys("Bharath");

        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys("Ice");

        // Entering permanent mailing address, city, province/state, and postal/zipcode in the respective fields
        WebElement addressField = driver.findElement(By.id("address"));
        addressField.sendKeys("Harvest");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Ontario");

        WebElement provinceStateField = driver.findElement(By.id("province"));
        provinceStateField.sendKeys("ON");

        WebElement provinceStateDropdown = driver.findElement(By.id("province_dropdown"));
        Select provinceStateSelect = new Select(provinceStateDropdown);
        provinceStateSelect.selectByVisibleText("ON Ontario");

        WebElement postalZipcodeField = driver.findElement(By.id("postalCode"));
        postalZipcodeField.sendKeys("M9C 4Y1");

        // Selecting "Canada" in the Country/Region dropdown
        WebElement countryDropdown = driver.findElement(By.id("country"));
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("Canada");

        // Entering primary phone number in the Primary Phone No field
        WebElement phoneField = driver.findElement(By.id("primaryPhoneNumber"));
        phoneField.sendKeys("8801070616");

        // Clicking on the "Next" button
        nextButton.click();

        // Verifying that we are on the AIRLINE INFORMATION section
        expectedUrl = "https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/airlineinfo";
        actualUrl = driver.getCurrentUrl();
        if (!actualUrl.equals(expectedUrl)) {
            System.out.println("Error: Not on AIRLINE INFORMATION section");
            driver.quit();
            return;
        }

        // Selecting "Air Canada" in the Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.id("airline"));
        Select airlineSelect = new Select(airlineDropdown);
        airlineSelect.selectByVisibleText("Air Canada");

        // Entering flight number, flight date, departure airport, and arrival airport in the respective fields
        WebElement flightNumberField = driver.findElement(By.id("flightNumber"));
        flightNumberField.sendKeys("122");

        WebElement flightDateField = driver.findElement(By.id("flightDate"));
        flightDateField.sendKeys("2023-08-17");

        WebElement departureAirportField = driver.findElement(By.id("departureAirport"));
        departureAirportField.sendKeys("YVR");

        WebElement departureAirportDropdown = driver.findElement(By.id("departureAirport_dropdown"));
        Select departureAirportSelect = new Select(departureAirportDropdown);
        departureAirportSelect.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");

        WebElement arrivalAirportField = driver.findElement(By.id("arrivalAirport"));
        arrivalAirportField.sendKeys("YYZ");

        WebElement arrivalAirportDropdown = driver.findElement(By.id("arrivalAirport_dropdown"));
        Select arrivalAirportSelect = new Select(arrivalAirportDropdown);
        arrivalAirportSelect.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Entering booking reference and ticket number in the respective fields
        WebElement bookingReferenceField = driver.findElement(By.id("bookingReference"));
        bookingReferenceField.sendKeys("3ED8RH");

        WebElement ticketNumberField = driver.findElement(By.id("ticketNumber"));
        ticketNumberField.sendKeys("0142173322307");

        // Clicking on the "Next" button
        nextButton.click();

        // Verifying that we are on the PAYMENT INFORMATION section
        expectedUrl = "https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/paymentinfo";
        actualUrl = driver.getCurrentUrl();
        if (!actualUrl.equals(expectedUrl)) {
            System.out.println("Error: Not on PAYMENT INFORMATION section");
            driver.quit();
            return;
        }

        // Verifying that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInfoText = driver.findElement(By.xpath("//div[contains(@class, 'field--instructions') and contains(text(), 'Do not add any payment information in the comment field')]"));
        if (paymentInfoText == null) {
            System.out.println("Error: Payment information text not found");
            driver.quit();
            return;
        }

        // Entering "Air Canada Booking" in the Subject field
        WebElement subjectField = driver.findElement(By.id("subject"));
        subjectField.sendKeys("Air Canada Booking");

        // Clicking on the "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(), 'Submit')]"));
        submitButton.click();

        // Verifying that the confirmation message is displayed
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert') and contains(text(), 'Thank you for sharing your travel experience.')]"));
        if (confirmationMessage == null) {
            System.out.println("Error: Confirmation message not found");
            driver.quit();
            return;
        }

        // Closing the browser
        driver.quit();
    }
}