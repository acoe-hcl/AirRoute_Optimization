import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AirCanadaTestScript {

    public static void main(String[] args) throws InterruptedException {
        // Setting system properties for Chrome driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Creating object for Chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigating to Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Maximizing browser window
        driver.manage().window().maximize();

        // Selecting General Concerns tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//h4[text()='General Concerns']"));
        generalConcernsTile.click();

        // Selecting "At the Airport" option in Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.xpath("//label[text()='Regarding']/following-sibling::select"));
        regardingDropdown.click();
        WebElement regardingOption = driver.findElement(By.xpath("//option[text()='At the Airport']"));
        regardingOption.click();

        // Selecting "Check-in" option in Issue dropdown
        WebElement issueDropdown = driver.findElement(By.xpath("//label[text()='Issue']/following-sibling::select"));
        issueDropdown.click();
        WebElement issueOption = driver.findElement(By.xpath("//option[text()='Check-in']"));
        issueOption.click();

        // Clicking on Next button
        WebElement nextButton1 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton1.click();

        // Entering email address and confirming email address
        WebElement emailField = driver.findElement(By.xpath("//label[text()='Email Address']/following-sibling::input"));
        emailField.sendKeys("bharathkumar-n@hcl.com");
        WebElement confirmEmailField = driver.findElement(By.xpath("//label[text()='Confirm Email Address']/following-sibling::input"));
        confirmEmailField.sendKeys("bharathkumar-n@hcl.com");

        // Selecting Title dropdown option
        WebElement titleDropdown = driver.findElement(By.xpath("//label[text()='Title']/following-sibling::select"));
        titleDropdown.click();
        WebElement titleOption = driver.findElement(By.xpath("//option[text()='Mr.']"));
        titleOption.click();

        // Entering first name and last name
        WebElement firstNameField = driver.findElement(By.xpath("//label[text()='First Name']/following-sibling::input"));
        firstNameField.sendKeys("Bharath");
        WebElement lastNameField = driver.findElement(By.xpath("//label[text()='Last Name']/following-sibling::input"));
        lastNameField.sendKeys("Ice");

        // Entering permanent mailing address, city, province/state, and postal/zipcode
        WebElement mailingAddressField = driver.findElement(By.xpath("//label[text()='Permanent Mailing Address']/following-sibling::input"));
        mailingAddressField.sendKeys("Harvest");
        WebElement cityField = driver.findElement(By.xpath("//label[text()='City']/following-sibling::input"));
        cityField.sendKeys("Ontario");
        WebElement provinceStateField = driver.findElement(By.xpath("//label[text()='Province/State']/following-sibling::input"));
        provinceStateField.sendKeys("ON");
        WebElement provinceStateDropdown = driver.findElement(By.xpath("//label[text()='Province/State']/following-sibling::select"));
        provinceStateDropdown.click();
        WebElement provinceStateOption = driver.findElement(By.xpath("//option[text()='ON Ontario']"));
        provinceStateOption.click();
        WebElement postalZipcodeField = driver.findElement(By.xpath("//label[text()='Postal/Zipcode']/following-sibling::input"));
        postalZipcodeField.sendKeys("M9C 4Y1");

        // Selecting "Canada" option in Country/Region dropdown
        WebElement countryDropdown = driver.findElement(By.xpath("//label[text()='Country/Region']/following-sibling::select"));
        countryDropdown.click();
        WebElement countryOption = driver.findElement(By.xpath("//option[text()='Canada']"));
        countryOption.click();

        // Entering primary phone number
        WebElement phoneField = driver.findElement(By.xpath("//label[text()='Primary Phone No']/following-sibling::input"));
        phoneField.sendKeys("8801070616");

        // Clicking on Next button
        WebElement nextButton2 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton2.click();

        // Selecting Air Canada option in Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.xpath("//label[text()='Airline']/following-sibling::select"));
        airlineDropdown.click();
        WebElement airlineOption = driver.findElement(By.xpath("//option[text()='Air Canada']"));
        airlineOption.click();

        // Entering flight number, date, departure airport, and arrival airport
        WebElement flightNumberField = driver.findElement(By.xpath("//label[text()='Flight Number']/following-sibling::input"));
        flightNumberField.sendKeys("122");
        WebElement flightDateField = driver.findElement(By.xpath("//label[text()='Flight Date']/following-sibling::input"));
        flightDateField.sendKeys("2023-08-17");
        WebElement departureAirportField = driver.findElement(By.xpath("//label[text()='Departure Airport']/following-sibling::input"));
        departureAirportField.sendKeys("YVR");
        WebElement departureAirportDropdown = driver.findElement(By.xpath("//label[text()='Departure Airport']/following-sibling::select"));
        departureAirportDropdown.click();
        WebElement departureAirportOption = driver.findElement(By.xpath("//option[text()='YVR Vancouver Canada (Vancouver Intl)']"));
        departureAirportOption.click();
        WebElement arrivalAirportField = driver.findElement(By.xpath("//label[text()='Arrival Airport']/following-sibling::input"));
        arrivalAirportField.sendKeys("YYZ");
        WebElement arrivalAirportDropdown = driver.findElement(By.xpath("//label[text()='Arrival Airport']/following-sibling::select"));
        arrivalAirportDropdown.click();
        WebElement arrivalAirportOption = driver.findElement(By.xpath("//option[text()='YYZ Toronto Canada (Lester B. Pearson Intl)']"));
        arrivalAirportOption.click();

        // Entering booking reference and ticket number
        WebElement bookingReferenceField = driver.findElement(By.xpath("//label[text()='Booking Reference']/following-sibling::input"));
        bookingReferenceField.sendKeys("3ED8RH");
        WebElement ticketNumberField = driver.findElement(By.xpath("//label[text()='Ticket Number']/following-sibling::input"));
        ticketNumberField.sendKeys("0142173322307");

        // Clicking on Next button
        WebElement nextButton3 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton3.click();

        // Verifying "Do not add any payment information in the comment field" message
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[contains(text(),'Do not add any payment information in the comment field')]"));
        String expectedMessage = "Do not add any payment information in the comment field";
        String actualMessage = paymentInfoMessage.getText();
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Payment information message is displayed correctly");
        } else {
            System.out.println("Payment information message is not displayed correctly");
        }

        // Entering subject and clicking on Submit button
        WebElement subjectField = driver.findElement(By.xpath("//label[text()='Subject']/following-sibling::input"));
        subjectField.sendKeys("Air Canada Booking");
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();

        // Verifying confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[contains(text(),'Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.')]"));
        String expectedConfirmation = "Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.\nA file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns.\nWe will get back to you as soon as possible. Thank you for your patience.";
        String actualConfirmation = confirmationMessage.getText();
        if (actualConfirmation.equals(expectedConfirmation)) {
            System.out.println("Confirmation message is displayed correctly");
        } else {
            System.out.println("Confirmation message is not displayed correctly");
        }

        // Closing browser
        driver.quit();
    }

}