										import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SubmitGeneralConcerns {

    public static void main(String[] args) {
        // Setting system properties for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Creating a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigating to the Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Selecting the "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@class='tile__title' and text()='General Concerns']"));
        generalConcernsTile.click();

        // Selecting "At the Airport" in the Regarding dropdown
        Select regardingDropdown = new Select(driver.findElement(By.id("regarding")));
        regardingDropdown.selectByVisibleText("At the Airport");

        // Selecting "Check-in" in the Issue dropdown field
        Select issueDropdown = new Select(driver.findElement(By.id("issue")));
        issueDropdown.selectByVisibleText("Check-in");

        // Clicking on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@type='submit' and text()='Next']"));
        nextButton.click();

        // Entering email address in the Email Address field
        WebElement emailAddressField = driver.findElement(By.id("emailaddress"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Entering email address in the Confirm Email Address field
        WebElement confirmEmailAddressField = driver.findElement(By.id("confirmemailaddress"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");

        // Selecting "Mr." in the Title dropdown
        Select titleDropdown = new Select(driver.findElement(By.id("title")));
        titleDropdown.selectByVisibleText("Mr.");

        // Entering "Bharath" in the First Name field
        WebElement firstNameField = driver.findElement(By.id("firstname"));
        firstNameField.sendKeys("Bharath");

        // Entering "Ice" in the Last Name field
        WebElement lastNameField = driver.findElement(By.id("lastname"));
        lastNameField.sendKeys("Ice");

        // Entering "Harvest" in the Permanent Mailing Address field
        WebElement mailingAddressField = driver.findElement(By.id("mailingaddress"));
        mailingAddressField.sendKeys("Harvest");

        // Entering "Ontario" in the City field
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Ontario");

        // Entering "ON" in the Province/State Field
        WebElement provinceStateField = driver.findElement(By.id("provincestate"));
        provinceStateField.sendKeys("ON");

        // Selecting "ON Ontario" in the Province/State dropdown
        Select provinceStateDropdown = new Select(driver.findElement(By.id("provincestatedropdown")));
        provinceStateDropdown.selectByVisibleText("ON Ontario");

        // Entering "M9C 4Y1" in the Postal/Zipcode Field
        WebElement postalZipcodeField = driver.findElement(By.id("postalcodezipcode"));
        postalZipcodeField.sendKeys("M9C 4Y1");

        // Selecting "Canada" in the Country/Region dropdown
        Select countryRegionDropdown = new Select(driver.findElement(By.id("countryregion")));
        countryRegionDropdown.selectByVisibleText("Canada");

        // Entering "8801070616" in the Primary Phone No Field
        WebElement primaryPhoneNoField = driver.findElement(By.id("primaryphoneno"));
        primaryPhoneNoField.sendKeys("8801070616");

        // Clicking on the "Next" button
        nextButton.click();

        // Selecting "Air Canada" in the Airline dropdown
        Select airlineDropdown = new Select(driver.findElement(By.id("airline")));
        airlineDropdown.selectByVisibleText("Air Canada");

        // Entering "122" in the Flight Number field
        WebElement flightNumberField = driver.findElement(By.id("flightnumber"));
        flightNumberField.sendKeys("122");

        // Entering "2023-08-17" in the Flight Date field
        WebElement flightDateField = driver.findElement(By.id("flightdate"));
        flightDateField.sendKeys("2023-08-17");

        // Entering "YVR" in the Departure Airport Field
        WebElement departureAirportField = driver.findElement(By.id("departureairport"));
        departureAirportField.sendKeys("YVR");

        // Selecting "YVR Vancouver Canada (Vancouver Intl)" in the Departure Airport dropdown
        Select departureAirportDropdown = new Select(driver.findElement(By.id("departureairportdropdown")));
        departureAirportDropdown.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");

        // Entering "YYZ" in the Arrival Airport Field
        WebElement arrivalAirportField = driver.findElement(By.id("arrivalairport"));
        arrivalAirportField.sendKeys("YYZ");

        // Selecting "YYZ Toronto Canada (Lester B. Pearson Intl)" in the Arrival Airport dropdown
        Select arrivalAirportDropdown = new Select(driver.findElement(By.id("arrivalairportdropdown")));
        arrivalAirportDropdown.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Entering "3ED8RH" in the Booking Reference field
        WebElement bookingReferenceField = driver.findElement(By.id("bookingreference"));
        bookingReferenceField.sendKeys("3ED8RH");

        // Entering "0142173322307" in the Ticket Number field
        WebElement ticketNumberField = driver.findElement(By.id("ticketnumber"));
        ticketNumberField.sendKeys("0142173322307");

        // Clicking on the "Next" button
        nextButton.click();

        // Verifying that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInformationMessage = driver.findElement(By.xpath("//div[@class='comment__message' and text()='Do not add any payment information in the comment field']"));
        assert(paymentInformationMessage.isDisplayed());

        // Entering "Air Canada Booking" in the Subject field
        WebElement subjectField = driver.findElement(By.id("subject"));
        subjectField.sendKeys("Air Canada Booking");

        // Clicking on the "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit' and text()='Submit']"));
        submitButton.click();

        // Verifying the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='alert alert-success' and contains(text(),'Thank you for sharing your travel experience')]"));
        assert(confirmationMessage.isDisplayed());

        // Closing the browser
        driver.quit();
    }

}
									