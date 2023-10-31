										import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SubmitGeneralConcerns {

    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        // Create ChromeDriver instance
        WebDriver driver = new ChromeDriver();
        // Navigate to Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");
        // Maximize browser window
        driver.manage().window().maximize();
        
        // Select General Concerns tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@class='tile-content']//h3[text()='General Concerns']"));
        generalConcernsTile.click();
        
        // Select 'At the Airport' option in Regarding dropdown
        Select regardingDropdown = new Select(driver.findElement(By.id("regarding")));
        regardingDropdown.selectByVisibleText("At the Airport");
        
        // Select 'Check-in' option in Issue dropdown
        Select issueDropdown = new Select(driver.findElement(By.id("issue")));
        issueDropdown.selectByVisibleText("Check-in");
        
        // Click on Next button
        WebElement nextButton1 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton1.click();
        
        // Enter email address
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("bharathkumar-n@hcl.com");
        
        // Confirm email address
        WebElement confirmEmailField = driver.findElement(By.id("confirmEmail"));
        confirmEmailField.sendKeys("bharathkumar-n@hcl.com");
        
        // Select 'Mr.' option in Title dropdown
        Select titleDropdown = new Select(driver.findElement(By.id("title")));
        titleDropdown.selectByVisibleText("Mr.");
        
        // Enter first name
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys("Bharath");
        
        // Enter last name
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys("Ice");
        
        // Enter permanent mailing address
        WebElement mailingAddressField = driver.findElement(By.id("address1"));
        mailingAddressField.sendKeys("Harvest");
        
        // Enter city
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Ontario");
        
        // Enter province/state
        WebElement provinceStateField = driver.findElement(By.id("state"));
        provinceStateField.sendKeys("ON");
        
        // Select 'ON Ontario' option in Province/State dropdown
        Select provinceStateDropdown = new Select(driver.findElement(By.id("stateProvince")));
        provinceStateDropdown.selectByVisibleText("ON Ontario");
        
        // Enter postal/zipcode
        WebElement postalZipcodeField = driver.findElement(By.id("zipPostalCode"));
        postalZipcodeField.sendKeys("M9C 4Y1");
        
        // Select 'Canada' option in Country/Region dropdown
        Select countryDropdown = new Select(driver.findElement(By.id("country")));
        countryDropdown.selectByVisibleText("Canada");
        
        // Enter primary phone number
        WebElement phoneField = driver.findElement(By.id("phone"));
        phoneField.sendKeys("8801070616");
        
        // Click on Next button
        WebElement nextButton2 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton2.click();
        
        // Select 'Air Canada' option in Airline dropdown
        Select airlineDropdown = new Select(driver.findElement(By.id("airline")));
        airlineDropdown.selectByVisibleText("Air Canada");
        
        // Enter flight number
        WebElement flightNumberField = driver.findElement(By.id("flightNumber"));
        flightNumberField.sendKeys("122");
        
        // Enter flight date
        WebElement flightDateField = driver.findElement(By.id("flightDate"));
        flightDateField.sendKeys("2023-08-17");
        
        // Enter departure airport
        WebElement departureAirportField = driver.findElement(By.id("departureAirport"));
        departureAirportField.sendKeys("YVR");
        
        // Select 'YVR Vancouver Canada (Vancouver Intl)' option in Departure Airport dropdown
        Select departureAirportDropdown = new Select(driver.findElement(By.id("departureAirportList")));
        departureAirportDropdown.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");
        
        // Enter arrival airport
        WebElement arrivalAirportField = driver.findElement(By.id("arrivalAirport"));
        arrivalAirportField.sendKeys("YYZ");
        
        // Select 'YYZ Toronto Canada (Lester B. Pearson Intl)' option in Arrival Airport dropdown
        Select arrivalAirportDropdown = new Select(driver.findElement(By.id("arrivalAirportList")));
        arrivalAirportDropdown.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");
        
        // Enter booking reference
        WebElement bookingReferenceField = driver.findElement(By.id("bookingReference"));
        bookingReferenceField.sendKeys("3ED8RH");
        
        // Enter ticket number
        WebElement ticketNumberField = driver.findElement(By.id("ticketNumber"));
        ticketNumberField.sendKeys("0142173322307");
        
        // Click on Next button
        WebElement nextButton3 = driver.findElement(By.xpath("//button[text()='Next']"));
        nextButton3.click();
        
        // Verify 'Do not add any payment information in the comment field' message is displayed
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[@class='comment-text']//p[text()='Do not add any payment information in the comment field']"));
        if(paymentInfoMessage.isDisplayed()) {
            System.out.println("Payment information message is displayed");
        } else {
            System.out.println("Payment information message is not displayed");
        }
        
        // Enter subject
        WebElement subjectField = driver.findElement(By.id("subject"));
        subjectField.sendKeys("Air Canada Booking");
        
        // Click on Submit button
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();
        
        // Verify confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        String expectedMessage = "Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.\n" +
                "A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns.\n" +
                "We will get back to you as soon as possible. Thank you for your patience.";
        if(confirmationMessage.getText().equals(expectedMessage)) {
            System.out.println("Confirmation message is displayed");
        } else {
            System.out.println("Confirmation message is not displayed");
        }
        
        // Close browser
        driver.quit();
    }

}
									