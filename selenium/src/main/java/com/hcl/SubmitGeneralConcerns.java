import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SubmitGeneralConcerns {

    public static void main(String[] args) {
        // Set the path of the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Select the "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@class='tile']//h3[text()='General Concerns']"));
        generalConcernsTile.click();

        // Select "At the Airport" in the Regarding dropdown
        Select regardingDropdown = new Select(driver.findElement(By.id("regarding")));
        regardingDropdown.selectByVisibleText("At the Airport");

        // Select "Check-in" in the Issue dropdown field
        Select issueDropdown = new Select(driver.findElement(By.id("issue")));
        issueDropdown.selectByVisibleText("Check-in");

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton.click();

        // Verify that we are on the PASSENGER INFORMATION page
        WebElement passengerInfoHeader = driver.findElement(By.xpath("//h2[text()='PASSENGER INFORMATION']"));
        assert(passengerInfoHeader.isDisplayed());

        // Enter email address in the Email Address field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("bharathkumar-n@hcl.com");

        // Enter email address in the Confirm Email Address field
        WebElement confirmEmailField = driver.findElement(By.id("confirmEmail"));
        confirmEmailField.sendKeys("bharathkumar-n@hcl.com");

        // Select "Mr." in the Title dropdown
        Select titleDropdown = new Select(driver.findElement(By.id("title")));
        titleDropdown.selectByVisibleText("Mr.");

        // Enter "Bharath" in the First Name field
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys("Bharath");

        // Enter "Ice" in the Last Name field
        WebElement lastNameField = driver.findElement(By.id("lastName"));
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

        // Select "ON Ontario" in the Province/State dropdown
        Select provinceDropdown = new Select(driver.findElement(By.id("provinceDropdown")));
        provinceDropdown.selectByVisibleText("ON Ontario");

        // Enter "M9C 4Y1" in the Postal/Zipcode Field
        WebElement postalField = driver.findElement(By.id("postal"));
        postalField.sendKeys("M9C 4Y1");

        // Select "Canada" in the Country/Region dropdown
        Select countryDropdown = new Select(driver.findElement(By.id("country")));
        countryDropdown.selectByVisibleText("Canada");

        // Enter "8801070616" in the Primary Phone No Field
        WebElement phoneField = driver.findElement(By.id("phone"));
        phoneField.sendKeys("8801070616");

        // Click on the "Next" button
        nextButton.click();

        // Verify that we are on the AIRLINE INFORMATION section
        WebElement airlineInfoHeader = driver.findElement(By.xpath("//h2[text()='AIRLINE INFORMATION']"));
        assert(airlineInfoHeader.isDisplayed());

        // Select "Air Canada" in the Airline dropdown
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

        // Select "YVR Vancouver Canada (Vancouver Intl)" in the Departure Airport dropdown
        Select departureAirportDropdown = new Select(driver.findElement(By.id("departureAirportDropdown")));
        departureAirportDropdown.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");

        // Enter "YYZ" in the Arrival Airport Field
        WebElement arrivalAirportField = driver.findElement(By.id("arrivalAirport"));
        arrivalAirportField.sendKeys("YYZ");

        // Select "YYZ Toronto Canada (Lester B. Pearson Intl)" in the Arrival Airport dropdown
        Select arrivalAirportDropdown = new Select(driver.findElement(By.id("arrivalAirportDropdown")));
        arrivalAirportDropdown.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Enter "3ED8RH" in the Booking Reference field
        WebElement bookingReferenceField = driver.findElement(By.id("bookingReference"));
        bookingReferenceField.sendKeys("3ED8RH");

        // Enter "0142173322307" in the Ticket Number field
        WebElement ticketNumberField = driver.findElement(By.id("ticketNumber"));
        ticketNumberField.sendKeys("0142173322307");

        // Click on the "Next" button
        nextButton.click();

        // Verify that we are on the PAYMENT INFORMATION section
        WebElement paymentInfoHeader = driver.findElement(By.xpath("//h2[text()='PAYMENT INFORMATION']"));
        assert(paymentInfoHeader.isDisplayed());

        // Verify that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[@class='message']//p[text()='Do not add any payment information in the comment field']"));
        assert(paymentInfoMessage.isDisplayed());

        // Enter "Air Canada Booking" in the Subject field
        WebElement subjectField = driver.findElement(By.id("subject"));
        subjectField.sendKeys("Air Canada Booking");

        // Click on the "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();

        // Verify the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='message']//p[text()='Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.\nA file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns.\nWe will get back to you as soon as possible. Thank you for your patience.']"));
        assert(confirmationMessage.isDisplayed());

        // Close the browser
        driver.quit();
    }

}
									