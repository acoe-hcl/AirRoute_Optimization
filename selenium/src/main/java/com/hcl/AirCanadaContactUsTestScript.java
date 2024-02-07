import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AirCanadaContactUsTestScript {

    public static void main(String[] args) {

        // Setting system properties of ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Creating an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Select the "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//a[@title='General Concerns']"));
        generalConcernsTile.click();

        // Select "At the Airport" option in Regarding dropdown
        Select regardingDropdown = new Select(driver.findElement(By.xpath("//select[@id='regarding']/option[text()='At the Airport']")));
        regardingDropdown.selectByVisibleText("At the Airport");

        // Select "Check-in" option in Issue dropdown
        Select issueDropdown = new Select(driver.findElement(By.xpath("//select[@id='issue']/option[text()='Check-in']")));
        issueDropdown.selectByVisibleText("Check-in");

        // Click on "Next" button
        WebElement nextButton1 = driver.findElement(By.xpath("//button[@id='next-button']"));
        nextButton1.click();

        // Verify that we are on "PASSENGER INFORMATION" page
        WebElement passengerInfoHeader = driver.findElement(By.xpath("//h2[text()='PASSENGER INFORMATION']"));
        assert passengerInfoHeader.isDisplayed();

        // Enter email address in Email Address and Confirm Email Address fields
        WebElement emailField = driver.findElement(By.xpath("//input[@id='email']"));
        emailField.sendKeys("bharathkumar-n@hcl.com");

        WebElement confirmEmailField = driver.findElement(By.xpath("//input[@id='confirm-email']"));
        confirmEmailField.sendKeys("bharathkumar-n@hcl.com");

        // Select "Mr." in Title dropdown
        Select titleDropdown = new Select(driver.findElement(By.xpath("//select[@id='title']/option[text()='Mr.']")));
        titleDropdown.selectByVisibleText("Mr.");

        // Enter first and last name
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='first-name']"));
        firstNameField.sendKeys("Bharath");

        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='last-name']"));
        lastNameField.sendKeys("Ice");

        // Enter permanent mailing address, city, province/state, and postal/zipcode
        WebElement mailingAddressField = driver.findElement(By.xpath("//input[@id='permanent-mailing-address']"));
        mailingAddressField.sendKeys("Harvest");

        WebElement cityField = driver.findElement(By.xpath("//input[@id='city']"));
        cityField.sendKeys("Ontario");

        WebElement provinceStateField = driver.findElement(By.xpath("//input[@id='state-province']"));
        provinceStateField.sendKeys("ON");

        Select provinceStateDropdown = new Select(driver.findElement(By.xpath("//select[@id='state-province-dropdown']/option[text()='ON Ontario']")));
        provinceStateDropdown.selectByVisibleText("ON Ontario");

        WebElement postalZipcodeField = driver.findElement(By.xpath("//input[@id='postal-zip']"));
        postalZipcodeField.sendKeys("M9C 4Y1");

        // Select "Canada" in Country/Region dropdown
        Select countryDropdown = new Select(driver.findElement(By.xpath("//select[@id='country']/option[text()='Canada']")));
        countryDropdown.selectByVisibleText("Canada");

        // Enter primary phone number
        WebElement primaryPhoneField = driver.findElement(By.xpath("//input[@id='primary-phone']"));
        primaryPhoneField.sendKeys("8801070616");

        // Click on "Next" button
        WebElement nextButton2 = driver.findElement(By.xpath("//button[@id='next-button']"));
        nextButton2.click();

        // Verify that we are on "AIRLINE INFORMATION" section
        WebElement airlineInfoHeader = driver.findElement(By.xpath("//h2[text()='AIRLINE INFORMATION']"));
        assert airlineInfoHeader.isDisplayed();

        // Select "Air Canada" in Airline dropdown
        Select airlineDropdown = new Select(driver.findElement(By.xpath("//select[@id='airline']/option[text()='Air Canada']")));
        airlineDropdown.selectByVisibleText("Air Canada");

        // Enter flight number, flight date, departure airport, and arrival airport
        WebElement flightNumberField = driver.findElement(By.xpath("//input[@id='flight-number']"));
        flightNumberField.sendKeys("122");

        WebElement flightDateField = driver.findElement(By.xpath("//input[@id='flight-date']"));
        flightDateField.sendKeys("2023-08-17");

        WebElement departureAirportField = driver.findElement(By.xpath("//input[@id='departure-airport']"));
        departureAirportField.sendKeys("YVR");

        Select departureAirportDropdown = new Select(driver.findElement(By.xpath("//select[@id='departure-airport-dropdown']/option[text()='YVR Vancouver Canada (Vancouver Intl)']")));
        departureAirportDropdown.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");

        WebElement arrivalAirportField = driver.findElement(By.xpath("//input[@id='arrival-airport']"));
        arrivalAirportField.sendKeys("YYZ");

        Select arrivalAirportDropdown = new Select(driver.findElement(By.xpath("//select[@id='arrival-airport-dropdown']/option[text()='YYZ Toronto Canada (Lester B. Pearson Intl)']")));
        arrivalAirportDropdown.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Enter booking reference and ticket number
        WebElement bookingReferenceField = driver.findElement(By.xpath("//input[@id='booking-reference']"));
        bookingReferenceField.sendKeys("3ED8RH");

        WebElement ticketNumberField = driver.findElement(By.xpath("//input[@id='ticket-number']"));
        ticketNumberField.sendKeys("0142173322307");

        // Click on "Next" button
        WebElement nextButton3 = driver.findElement(By.xpath("//button[@id='next-button']"));
        nextButton3.click();

        // Verify that we are on "PAYMENT INFORMATION" section
        WebElement paymentInfoHeader = driver.findElement(By.xpath("//h2[text()='PAYMENT INFORMATION']"));
        assert paymentInfoHeader.isDisplayed();

        // Verify that "Do not add any payment information in the comment field" message is displayed
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//p[text()='Do not add any payment information in the comment field']"));
        assert paymentInfoMessage.isDisplayed();

        // Enter subject and click on "Submit" button
        WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
        subjectField.sendKeys("Air Canada Booking");

        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit-button']"));
        submitButton.click();

        // Verify that confirmation message is displayed
        WebElement confirmationMessage = driver.findElement(By.xpath("//p[text()='Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments. A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns. We will get back to you as soon as possible. Thank you for your patience.']"));
        assert confirmationMessage.isDisplayed();

        // Close the browser
        driver.close();
    }
}