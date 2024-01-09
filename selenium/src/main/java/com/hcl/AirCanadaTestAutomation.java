import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AirCanadaTestAutomation {

    public static void main(String[] args) throws InterruptedException {
        
        //Setting the driver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32\\chromedriver.exe");
        
        //Creating ChromeDriver instance
        WebDriver driver = new ChromeDriver();
        
        //Navigating to the Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");
        
        //Selecting General Concerns tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@id='tile-general-concerns']/a"));
        generalConcernsTile.click();
        
        //Selecting At the Airport option in Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.xpath("//select[@name='regarding']/option[text()='At the Airport']"));
        regardingDropdown.click();
        
        //Selecting Check-in option in Issue dropdown
        WebElement issueDropdown = driver.findElement(By.xpath("//select[@name='issue']/option[text()='Check-in']"));
        issueDropdown.click();
        
        //Clicking on Next button
        WebElement nextButton1 = driver.findElement(By.xpath("//button[@id='nextButton1']"));
        nextButton1.click();
        
        //Entering email address
        WebElement emailAddressField = driver.findElement(By.xpath("//input[@id='emailAddress']"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");
        
        //Entering confirm email address
        WebElement confirmEmailAddressField = driver.findElement(By.xpath("//input[@id='confirmEmailAddress']"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");
        
        //Selecting Mr. option in Title dropdown
        WebElement titleDropdown = driver.findElement(By.xpath("//select[@id='title']"));
        titleDropdown.click();
        WebElement mrOption = driver.findElement(By.xpath("//option[text()='Mr.']"));
        mrOption.click();
        
        //Entering First Name
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstName']"));
        firstNameField.sendKeys("Bharath");
        
        //Entering Last Name
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastName']"));
        lastNameField.sendKeys("Ice");
        
        //Entering Permanent Mailing Address
        WebElement permanentMailingAddressField = driver.findElement(By.xpath("//textarea[@id='permanentMailingAddress']"));
        permanentMailingAddressField.sendKeys("Harvest");
        
        //Entering City
        WebElement cityField = driver.findElement(By.xpath("//input[@id='city']"));
        cityField.sendKeys("Ontario");
        
        //Entering Province/State
        WebElement provinceStateField = driver.findElement(By.xpath("//input[@id='provinceState']"));
        provinceStateField.sendKeys("ON");
        
        //Selecting ON Ontario option in Province/State dropdown
        WebElement provinceStateDropdown = driver.findElement(By.xpath("//select[@id='provinceStateDropdown']"));
        provinceStateDropdown.click();
        WebElement onOntarioOption = driver.findElement(By.xpath("//option[text()='ON Ontario']"));
        onOntarioOption.click();
        
        //Entering Postal/Zipcode
        WebElement postalZipcodeField = driver.findElement(By.xpath("//input[@id='postalZipcode']"));
        postalZipcodeField.sendKeys("M9C 4Y1");
        
        //Selecting Canada option in Country/Region dropdown
        WebElement countryRegionDropdown = driver.findElement(By.xpath("//select[@id='countryRegionDropdown']"));
        countryRegionDropdown.click();
        WebElement canadaOption = driver.findElement(By.xpath("//option[text()='Canada']"));
        canadaOption.click();
        
        //Entering Primary Phone No
        WebElement primaryPhoneNoField = driver.findElement(By.xpath("//input[@id='primaryPhoneNo']"));
        primaryPhoneNoField.sendKeys("8801070616");
        
        //Clicking on Next button
        WebElement nextButton2 = driver.findElement(By.xpath("//button[@id='nextButton2']"));
        nextButton2.click();
        
        //Selecting Air Canada option in Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.xpath("//select[@id='airline']"));
        airlineDropdown.click();
        WebElement airCanadaOption = driver.findElement(By.xpath("//option[text()='Air Canada']"));
        airCanadaOption.click();
        
        //Entering Flight Number
        WebElement flightNumberField = driver.findElement(By.xpath("//input[@id='flightNumber']"));
        flightNumberField.sendKeys("122");
        
        //Entering Flight Date
        WebElement flightDateField = driver.findElement(By.xpath("//input[@id='flightDate']"));
        flightDateField.sendKeys("2023-08-17");
        
        //Entering Departure Airport
        WebElement departureAirportField = driver.findElement(By.xpath("//input[@id='departureAirport']"));
        departureAirportField.sendKeys("YVR");
        
        //Selecting YVR Vancouver Canada (Vancouver Intl) option in Departure Airport dropdown
        WebElement departureAirportDropdown = driver.findElement(By.xpath("//select[@id='departureAirportDropdown']"));
        departureAirportDropdown.click();
        WebElement yvrVancouverCanadaOption = driver.findElement(By.xpath("//option[text()='YVR Vancouver Canada (Vancouver Intl)']"));
        yvrVancouverCanadaOption.click();
        
        //Entering Arrival Airport
        WebElement arrivalAirportField = driver.findElement(By.xpath("//input[@id='arrivalAirport']"));
        arrivalAirportField.sendKeys("YYZ");
        
        //Selecting YYZ Toronto Canada (Lester B. Pearson Intl) option in Arrival Airport dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.xpath("//select[@id='arrivalAirportDropdown']"));
        arrivalAirportDropdown.click();
        WebElement yyzTorontoCanadaOption = driver.findElement(By.xpath("//option[text()='YYZ Toronto Canada (Lester B. Pearson Intl)']"));
        yyzTorontoCanadaOption.click();
        
        //Entering Booking Reference
        WebElement bookingReferenceField = driver.findElement(By.xpath("//input[@id='bookingReference']"));
        bookingReferenceField.sendKeys("3ED8RH");
        
        //Entering Ticket Number
        WebElement ticketNumberField = driver.findElement(By.xpath("//input[@id='ticketNumber']"));
        ticketNumberField.sendKeys("0142173322307");
        
        //Clicking on Next button
        WebElement nextButton3 = driver.findElement(By.xpath("//button[@id='nextButton3']"));
        nextButton3.click();
        
        //Verifying that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInformationMessage = driver.findElement(By.xpath("//div[@id='commentContainer']/div/p"));
        String expectedMessage = "Do not add any payment information in the comment field";
        String actualMessage = paymentInformationMessage.getText();
        if(actualMessage.equals(expectedMessage)) {
            System.out.println("Payment information message is displayed correctly");
        } else {
            System.out.println("Payment information message is not displayed correctly");
        }
        
        //Entering Subject
        WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
        subjectField.sendKeys("Air Canada Booking");
        
        //Clicking on Submit button
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submitButton']"));
        submitButton.click();
        
        //Verifying confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@id='mainContent']/div[1]"));
        String expectedConfirmationMessage = "Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.\nA file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns.\nWe will get back to you as soon as possible. Thank you for your patience.";
        String actualConfirmationMessage = confirmationMessage.getText();
        if(actualConfirmationMessage.equals(expectedConfirmationMessage)) {
            System.out.println("Confirmation message is displayed correctly");
        } else {
            System.out.println("Confirmation message is not displayed correctly");
        }
        
        //Closing the browser
        driver.quit();
    }

}