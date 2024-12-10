import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigate to the given website
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Select the "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[contains(@class, 'tile-title') and text()='General Concerns']"));
        generalConcernsTile.click();

        // Select "At the Airport" option in Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.xpath("//select[@id='regardingopportunityid']"));
        regardingDropdown.click();
        WebElement atTheAirportOption = driver.findElement(By.xpath("//option[text()='At the Airport']"));
        atTheAirportOption.click();

        // Select "Check-in" option in Issue dropdown
        WebElement issueDropdown = driver.findElement(By.xpath("//select[@id='issue']"));
        issueDropdown.click();
        WebElement checkInOption = driver.findElement(By.xpath("//option[text()='Check-in']"));
        checkInOption.click();

        // Click on "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton.click();

        // Enter email address
        WebElement emailAddressField = driver.findElement(By.xpath("//input[@id='emailaddress']"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Enter confirm email address
        WebElement confirmEmailAddressField = driver.findElement(By.xpath("//input[@id='emailaddress_confirm']"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Select "Mr." option in Title dropdown
        WebElement titleDropdown = driver.findElement(By.xpath("//select[@id='salutation']"));
        titleDropdown.click();
        WebElement mrOption = driver.findElement(By.xpath("//option[text()='Mr.']"));
        mrOption.click();

        // Enter first name
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstname']"));
        firstNameField.sendKeys("Bharath");

        // Enter last name
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastname']"));
        lastNameField.sendKeys("Ice");

        // Enter permanent mailing address
        WebElement mailingAddressField = driver.findElement(By.xpath("//textarea[@id='address1_line1']"));
        mailingAddressField.sendKeys("Harvest");

        // Enter city
        WebElement cityField = driver.findElement(By.xpath("//input[@id='address1_city']"));
        cityField.sendKeys("Ontario");

        // Enter province/state abbreviation
        WebElement provinceField = driver.findElement(By.xpath("//input[@id='address1_stateorprovince']"));
        provinceField.sendKeys("ON");

        // Select "ON Ontario" option in Province/State dropdown
        WebElement provinceDropdown = driver.findElement(By.xpath("//select[@id='address1_stateorprovince_i']"));
        provinceDropdown.click();
        WebElement ontarioOption = driver.findElement(By.xpath("//option[text()='ON Ontario']"));
        ontarioOption.click();

        // Enter postal/zipcode
        WebElement postalField = driver.findElement(By.xpath("//input[@id='address1_postalcode']"));
        postalField.sendKeys("M9C 4Y1");

        // Select "Canada" option in Country/Region dropdown
        WebElement countryDropdown = driver.findElement(By.xpath("//select[@id='address1_country_i']"));
        countryDropdown.click();
        WebElement canadaOption = driver.findElement(By.xpath("//option[text()='Canada']"));
        canadaOption.click();

        // Enter primary phone number
        WebElement phoneField = driver.findElement(By.xpath("//input[@id='telephone1']"));
        phoneField.sendKeys("8801070616");

        // Click on "Next" button
        nextButton.click();

        // Select "Air Canada" option in Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.xpath("//select[@id='AirlineId']"));
        airlineDropdown.click();
        WebElement airCanadaOption = driver.findElement(By.xpath("//option[text()='Air Canada']"));
        airCanadaOption.click();

        // Enter flight number
        WebElement flightNumberField = driver.findElement(By.xpath("//input[@id='flightnumber']"));
        flightNumberField.sendKeys("122");

        // Enter flight date
        WebElement flightDateField = driver.findElement(By.xpath("//input[@id='flightdate']"));
        flightDateField.sendKeys("2023-08-17");

        // Enter departure airport
        WebElement departureAirportField = driver.findElement(By.xpath("//input[@id='departureairport']"));
        departureAirportField.sendKeys("YVR");

        // Select "YVR Vancouver Canada (Vancouver Intl)" option in Departure Airport dropdown
        WebElement departureAirportDropdown = driver.findElement(By.xpath("//select[@id='departureairport_i']"));
        departureAirportDropdown.click();
        WebElement vancouverOption = driver.findElement(By.xpath("//option[text()='YVR Vancouver Canada (Vancouver Intl)']"));
        vancouverOption.click();

        // Enter arrival airport
        WebElement arrivalAirportField = driver.findElement(By.xpath("//input[@id='arrivalairport']"));
        arrivalAirportField.sendKeys("YYZ");

        // Select "YYZ Toronto Canada (Lester B. Pearson Intl)" option in Arrival Airport dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.xpath("//select[@id='arrivalairport_i']"));
        arrivalAirportDropdown.click();
        WebElement torontoOption = driver.findElement(By.xpath("//option[text()='YYZ Toronto Canada (Lester B. Pearson Intl)']"));
        torontoOption.click();

        // Enter booking reference
        WebElement bookingReferenceField = driver.findElement(By.xpath("//input[@id='bookingreference']"));
        bookingReferenceField.sendKeys("3ED8RH");

        // Enter ticket number
        WebElement ticketNumberField = driver.findElement(By.xpath("//input[@id='ticketnumber']"));
        ticketNumberField.sendKeys("0142173322307");

        // Click on "Next" button
        nextButton.click();

        // Verify "Do not add any payment information in the comment field" message below the message field
        WebElement commentWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-warning') and text()='Do not add any payment information in the comment field']"));
        boolean isMessageDisplayed = commentWarningMessage.isDisplayed();
        if (isMessageDisplayed) {
            System.out.println("Validation Passed: \"Do not add any payment information in the comment field\" message is displayed");
        } else {
            System.out.println("Validation Failed: \"Do not add any payment information in the comment field\" message is not displayed");
        }

        // Enter subject
        WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
        subjectField.sendKeys("Air Canada Booking");

        // Click on "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();

        // Verify "Thank you for sharing your travel experience..." message
        WebElement thankYouMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-success') and text()='Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments. A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns. We will get back to you as soon as possible. Thank you for your patience.']"));
        boolean isMessageDisplayed = thankYouMessage.isDisplayed();
        if (isMessageDisplayed) {
            System.out.println("Validation Passed: \"Thank you for sharing your travel experience...\" message is displayed");
        } else {
            System.out.println("Validation Failed: \"Thank you for sharing your travel experience...\" message is not displayed");
        }

        // Close the browser
        driver.quit();
    }
}
