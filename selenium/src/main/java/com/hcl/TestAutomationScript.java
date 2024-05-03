
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.junit.Assert;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set Chrome WebDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Select the tile "General Concerns"
        WebElement generalConcernsTile = driver.findElement(By.xpath("//span[text()='General Concerns']"));
        generalConcernsTile.click();

        // Select the option "At the Airport" in the Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.id("regarding"));
        regardingDropdown.sendKeys("At the Airport");

        // Select the option "Check-in" in the Issue dropdown field
        WebElement issueDropdown = driver.findElement(By.id("issue"));
        issueDropdown.sendKeys("Check-in");

        // Click on the "Next" button
        WebElement nextButton1 = driver.findElement(By.id("next"));
        nextButton1.click();

        // Verify that the PASSENGER INFORMATION page is displayed
        WebElement passengerInfoPage = driver.findElement(By.xpath("//h2[text()='PASSENGER INFORMATION']"));
        Assert.assertTrue(passengerInfoPage.isDisplayed());

        // Enter email address in the Email Address field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("bharathkumar-n@hcl.com");

        // Enter email address in the Confirm Email Address field
        WebElement confirmEmailField = driver.findElement(By.id("confirm_email"));
        confirmEmailField.sendKeys("bharathkumar-n@hcl.com");

        // Select the option "Mr." in the Title dropdown
        WebElement titleDropdown = driver.findElement(By.id("title"));
        titleDropdown.sendKeys("Mr.");

        // Enter "Bharath" in the First Name field
        WebElement firstNameField = driver.findElement(By.id("first_name"));
        firstNameField.sendKeys("Bharath");

        // Enter "Ice" in the Last Name field
        WebElement lastNameField = driver.findElement(By.id("last_name"));
        lastNameField.sendKeys("Ice");

        // Enter "Harvest" in the Permanent Mailing Address field
        WebElement addressField = driver.findElement(By.id("address"));
        addressField.sendKeys("Harvest");

        // Enter "Ontario" in the City field
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Ontario");

        // Enter "ON" in the Province/State Field
        WebElement provinceField = driver.findElement(By.id("province"));
        provinceField.sendKeys("ON");

        // Select the option "ON Ontario" in the Province/State dropdown
        WebElement provinceDropdown = driver.findElement(By.id("province-dropdown"));
        provinceDropdown.sendKeys("ON Ontario");

        // Enter "M9C 4Y1" in the Postal/Zipcode Field
        WebElement postcodeField = driver.findElement(By.id("postcode"));
        postcodeField.sendKeys("M9C 4Y1");

        // Select the option "Canada" in the Country/Region dropdown
        WebElement countryDropdown = driver.findElement(By.id("country"));
        countryDropdown.sendKeys("Canada");

        // Enter "8801070616" in the Primary Phone No Field
        WebElement phoneField = driver.findElement(By.id("phone"));
        phoneField.sendKeys("8801070616");

        // Click on the "Next" button
        WebElement nextButton2 = driver.findElement(By.id("next"));
        nextButton2.click();

        // Verify that the AIRLINE INFORMATION section is displayed
        WebElement airlineInfoSection = driver.findElement(By.xpath("//h2[text()='AIRLINE INFORMATION']"));
        Assert.assertTrue(airlineInfoSection.isDisplayed());

        // Select the option "Air Canada" in the Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.id("airline"));
        airlineDropdown.sendKeys("Air Canada");

        // Enter "122" in the Flight Number field
        WebElement flightNumberField = driver.findElement(By.id("flight_number"));
        flightNumberField.sendKeys("122");

        // Enter "2023-08-17" in the Flight Date field
        WebElement flightDateField = driver.findElement(By.id("flight_date"));
        flightDateField.sendKeys("2023-08-17");

        // Enter "YVR" in the Departure Airport Field
        WebElement departureAirportField = driver.findElement(By.id("departure_airport"));
        departureAirportField.sendKeys("YVR");

        // Select the option "YVR Vancouver Canada (Vancouver Intl)" in the Departure Airport dropdown
        WebElement departureAirportDropdown = driver.findElement(By.id("departure_airport-dropdown"));
        departureAirportDropdown.sendKeys("YVR Vancouver Canada (Vancouver Intl)");

        // Enter "YYZ" in the Arrival Airport Field
        WebElement arrivalAirportField = driver.findElement(By.id("arrival_airport"));
        arrivalAirportField.sendKeys("YYZ");

        // Select the option "YYZ Toronto Canada (Lester B. Pearson Intl)" in the Arrival Airport dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.id("arrival_airport-dropdown"));
        arrivalAirportDropdown.sendKeys("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Enter "3ED8RH" in the Booking Reference field
        WebElement bookingReferenceField = driver.findElement(By.id("booking_reference"));
        bookingReferenceField.sendKeys("3ED8RH");

        // Enter "0142173322307" in the Ticket Number field
        WebElement ticketNumberField = driver.findElement(By.id("ticket_number"));
        ticketNumberField.sendKeys("0142173322307");

        // Click on the "Next" button
        WebElement nextButton3 = driver.findElement(By.id("next"));
        nextButton3.click();

        // Verify that the PAYMENT INFORMATION section is displayed
        WebElement paymentInfoSection = driver.findElement(By.xpath("//h2[text()='PAYMENT INFORMATION']"));
        Assert.assertTrue(paymentInfoSection.isDisplayed());

        // Verify that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//p[text()='Do not add any payment information in the comment field']"));
        Assert.assertTrue(paymentInfoMessage.isDisplayed());

        // Enter "Air Canada Booking" in the Subject field
        WebElement subjectField = driver.findElement(By.id("subject"));
        subjectField.sendKeys("Air Canada Booking");

        // Click on the "Submit" button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Verify the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//p[contains(text(),'Thank you for sharing your travel experience')]"));
        String expectedMessage = "Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments." +
                                "A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns." +
                                "We will get back to you as soon as possible. Thank you for your patience.";
        Assert.assertEquals(expectedMessage, confirmationMessage.getText());

        // Close the browser
        driver.quit();
    }
}

