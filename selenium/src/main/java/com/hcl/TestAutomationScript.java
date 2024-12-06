import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAutomationScript {

    public static void main(String[] args) throws InterruptedException {
        // Set the path of chromedriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Maximize the browser window
        driver.manage().window().maximize();

        // Open the website
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Select "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.linkText("General Concerns"));
        generalConcernsTile.click();

        // Select "At the Airport" in Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.id("cpp-category"));
        Select regardingDropdownSelect = new Select(regardingDropdown);
        regardingDropdownSelect.selectByVisibleText("At the Airport");

        // Select "Check-in" in Issue dropdown
        WebElement issueDropdown = driver.findElement(By.id("cpp-category-detail"));
        Select issueDropdownSelect = new Select(issueDropdown);
        issueDropdownSelect.selectByVisibleText("Check-in");

        // Click on "Next" button
        WebElement nextButton1 = driver.findElement(By.xpath("//input[@value='Next']"));
        nextButton1.click();

        // Enter email address
        WebElement emailAddressField = driver.findElement(By.id("cpp-email"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Confirm email address
        WebElement confirmEmailAddressField = driver.findElement(By.id("cpp-email-confirm"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Select "Mr." in Title dropdown
        WebElement titleDropdown = driver.findElement(By.id("cpp-title"));
        Select titleDropdownSelect = new Select(titleDropdown);
        titleDropdownSelect.selectByVisibleText("Mr.");

        // Enter first name
        WebElement firstNameField = driver.findElement(By.id("cpp-first-name"));
        firstNameField.sendKeys("Bharath");

        // Enter last name
        WebElement lastNameField = driver.findElement(By.id("cpp-last-name"));
        lastNameField.sendKeys("Ice");

        // Enter permanent mailing address
        WebElement permanentMailingAddressField = driver.findElement(By.id("cpp-address"));
        permanentMailingAddressField.sendKeys("Harvest");

        // Enter city
        WebElement cityField = driver.findElement(By.id("cpp-city"));
        cityField.sendKeys("Ontario");

        // Enter province/state
        WebElement provinceStateField = driver.findElement(By.id("cpp-province"));
        provinceStateField.sendKeys("ON");

        // Select "ON Ontario" in Province/State dropdown
        WebElement provinceStateDropdown = driver.findElement(By.id("cpp-province-list"));
        Select provinceStateDropdownSelect = new Select(provinceStateDropdown);
        provinceStateDropdownSelect.selectByVisibleText("ON Ontario");

        // Enter postal/zipcode
        WebElement postalZipCodeField = driver.findElement(By.id("cpp-postal-code"));
        postalZipCodeField.sendKeys("M9C 4Y1");

        // Select "Canada" in Country/Region dropdown
        WebElement countryRegionDropdown = driver.findElement(By.id("cpp-country"));
        Select countryRegionDropdownSelect = new Select(countryRegionDropdown);
        countryRegionDropdownSelect.selectByVisibleText("Canada");

        // Enter primary phone number
        WebElement primaryPhoneNoField = driver.findElement(By.id("cpp-primary-phone-no"));
        primaryPhoneNoField.sendKeys("8801070616");

        // Click on "Next" button
        WebElement nextButton2 = driver.findElement(By.xpath("//input[@value='Next']"));
        nextButton2.click();

        // Select "Air Canada" in Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.id("cpp-airline"));
        Select airlineDropdownSelect = new Select(airlineDropdown);
        airlineDropdownSelect.selectByVisibleText("Air Canada");

        // Enter flight number
        WebElement flightNumberField = driver.findElement(By.id("cpp-flight-number"));
        flightNumberField.sendKeys("122");

        // Enter flight date
        WebElement flightDateField = driver.findElement(By.id("cpp-flight-date"));
        flightDateField.sendKeys("2023-08-17");

        // Enter departure airport
        WebElement departureAirportField = driver.findElement(By.id("cpp-departure-airport"));
        departureAirportField.sendKeys("YVR");

        // Select "YVR Vancouver Canada (Vancouver Intl)" in Departure Airport dropdown
        WebElement departureAirportDropdown = driver.findElement(By.id("cpp-departure-airport-list"));
        Select departureAirportDropdownSelect = new Select(departureAirportDropdown);
        departureAirportDropdownSelect.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");

        // Enter arrival airport
        WebElement arrivalAirportField = driver.findElement(By.id("cpp-arrival-airport"));
        arrivalAirportField.sendKeys("YYZ");

        // Select "YYZ Toronto Canada (Lester B. Pearson Intl)" in Arrival Airport dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.id("cpp-arrival-airport-list"));
        Select arrivalAirportDropdownSelect = new Select(arrivalAirportDropdown);
        arrivalAirportDropdownSelect.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Enter booking reference
        WebElement bookingReferenceField = driver.findElement(By.id("cpp-booking-reference"));
        bookingReferenceField.sendKeys("3ED8RH");

        // Enter ticket number
        WebElement ticketNumberField = driver.findElement(By.id("cpp-ticket-number"));
        ticketNumberField.sendKeys("0142173322307");

        // Click on "Next" button
        WebElement nextButton3 = driver.findElement(By.xpath("//input[@value='Next']"));
        nextButton3.click();

        // Verify "Do not add any payment information in the comment field" message
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[contains(text(),'Do not add any payment information in the comment field')]"));
        if (paymentInfoMessage.isDisplayed()) {
            System.out.println("Pass: Payment info message is displayed");
        } else {
            System.out.println("Fail: Payment info message is not displayed");
        }

        // Enter subject
        WebElement subjectField = driver.findElement(By.id("cpp-subject"));
        subjectField.sendKeys("Air Canada Booking");

        // Click on "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
        submitButton.click();
        
        // Verify success message
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(),'Thank you for sharing your travel experience')]"));
        if (successMessage.isDisplayed()) {
            System.out.println("Pass: Success message is displayed");
        } else {
            System.out.println("Fail: Success message is not displayed");
        }

        // Close the browser
        driver.quit();
    }
}
