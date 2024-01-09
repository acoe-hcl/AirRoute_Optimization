//Importing required packages
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AirCanadaContactUsTest {

    public static void main(String[] args) {
        
        //Setting the path of the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        
        //Creating an instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        //Maximizing the window
        driver.manage().window().maximize();
        
        //Navigating to the Air Canada Contact Us page
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");
        
        //Selecting the "General Concerns" tile
        WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@class='tile tile-general-concerns']"));
        generalConcernsTile.click();
        
        //Selecting "At the Airport" in the "Regarding" dropdown
        WebElement regardingDropdown = driver.findElement(By.xpath("//select[@id='regarding']"));
        Select regardingSelect = new Select(regardingDropdown);
        regardingSelect.selectByVisibleText("At the Airport");
        
        //Selecting "Check-in" in the "Issue" dropdown
        WebElement issueDropdown = driver.findElement(By.xpath("//select[@id='issue']"));
        Select issueSelect = new Select(issueDropdown);
        issueSelect.selectByVisibleText("Check-in");
        
        //Clicking on the "Next" button
        WebElement nextButton1 = driver.findElement(By.xpath("//input[@value='Next']"));
        nextButton1.click();
        
        //Entering email address in the "Email Address" field
        WebElement emailAddressField = driver.findElement(By.xpath("//input[@id='emailaddress']"));
        emailAddressField.sendKeys("bharathkumar-n@hcl.com");
        
        //Entering confirm email address in the "Confirm Email Address" field
        WebElement confirmEmailAddressField = driver.findElement(By.xpath("//input[@id='confirmemailaddress']"));
        confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");
        
        //Selecting "Mr." in the "Title" dropdown
        WebElement titleDropdown = driver.findElement(By.xpath("//select[@id='title']"));
        Select titleSelect = new Select(titleDropdown);
        titleSelect.selectByVisibleText("Mr.");
        
        //Entering first name in the "First Name" field
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstname']"));
        firstNameField.sendKeys("Bharath");
        
        //Entering last name in the "Last Name" field
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastname']"));
        lastNameField.sendKeys("Ice");
        
        //Entering permanent mailing address in the "Permanent Mailing Address" field
        WebElement permanentMailingAddressField = driver.findElement(By.xpath("//input[@id='address']"));
        permanentMailingAddressField.sendKeys("Harvest");
        
        //Entering city in the "City" field
        WebElement cityField = driver.findElement(By.xpath("//input[@id='city']"));
        cityField.sendKeys("Ontario");
        
        //Entering province/state in the "Province/State Field" field
        WebElement provinceStateField = driver.findElement(By.xpath("//input[@id='provincestate']"));
        provinceStateField.sendKeys("ON");
        
        //Selecting "ON Ontario" in the "Province/State" dropdown
        WebElement provinceStateDropdown = driver.findElement(By.xpath("//select[@id='provincestate_dropdown']"));
        Select provinceStateSelect = new Select(provinceStateDropdown);
        provinceStateSelect.selectByVisibleText("ON Ontario");
        
        //Entering postal/zipcode in the "Postal/Zipcode Field" field
        WebElement postalZipcodeField = driver.findElement(By.xpath("//input[@id='postalcodezipcode']"));
        postalZipcodeField.sendKeys("M9C 4Y1");
        
        //Selecting "Canada" in the "Country/Region" dropdown
        WebElement countryRegionDropdown = driver.findElement(By.xpath("//select[@id='countryregion']"));
        Select countryRegionSelect = new Select(countryRegionDropdown);
        countryRegionSelect.selectByVisibleText("Canada");
        
        //Entering primary phone number in the "Primary Phone No Field" field
        WebElement primaryPhoneNoField = driver.findElement(By.xpath("//input[@id='primaryphoneno']"));
        primaryPhoneNoField.sendKeys("8801070616");
        
        //Clicking on the "Next" button
        WebElement nextButton2 = driver.findElement(By.xpath("//input[@value='Next']"));
        nextButton2.click();
        
        //Selecting "Air Canada" in the "Airline" dropdown
        WebElement airlineDropdown = driver.findElement(By.xpath("//select[@id='airline']"));
        Select airlineSelect = new Select(airlineDropdown);
        airlineSelect.selectByVisibleText("Air Canada");
        
        //Entering flight number in the "Flight Number" field
        WebElement flightNumberField = driver.findElement(By.xpath("//input[@id='flightnumber']"));
        flightNumberField.sendKeys("122");
        
        //Entering flight date in the "Flight Date" field
        WebElement flightDateField = driver.findElement(By.xpath("//input[@id='flightdate']"));
        flightDateField.sendKeys("2023-08-17");
        
        //Entering departure airport in the "Departure Airport Field" field
        WebElement departureAirportField = driver.findElement(By.xpath("//input[@id='departureairport']"));
        departureAirportField.sendKeys("YVR");
        
        //Selecting "YVR Vancouver Canada (Vancouver Intl)" in the "Departure Airport" dropdown
        WebElement departureAirportDropdown = driver.findElement(By.xpath("//select[@id='departureairport_dropdown']"));
        Select departureAirportSelect = new Select(departureAirportDropdown);
        departureAirportSelect.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");
        
        //Entering arrival airport in the "Arrival Airport Field" field
        WebElement arrivalAirportField = driver.findElement(By.xpath("//input[@id='arrivalairport']"));
        arrivalAirportField.sendKeys("YYZ");
        
        //Selecting "YYZ Toronto Canada (Lester B. Pearson Intl)" in the "Arrival Airport" dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.xpath("//select[@id='arrivalairport_dropdown']"));
        Select arrivalAirportSelect = new Select(arrivalAirportDropdown);
        arrivalAirportSelect.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");
        
        //Entering booking reference in the "Booking Reference" field
        WebElement bookingReferenceField = driver.findElement(By.xpath("//input[@id='bookingreference']"));
        bookingReferenceField.sendKeys("3ED8RH");
        
        //Entering ticket number in the "Ticket Number" field
        WebElement ticketNumberField = driver.findElement(By.xpath("//input[@id='ticketnumber']"));
        ticketNumberField.sendKeys("0142173322307");
        
        //Clicking on the "Next" button
        WebElement nextButton3 = driver.findElement(By.xpath("//input[@value='Next']"));
        nextButton3.click();
        
        //Verifying that "Do not add any payment information in the comment field" is displayed below the message field
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[contains(text(),'Do not add any payment information in the comment field')]"));
        String paymentInfoMessageText = paymentInfoMessage.getText();
        if(paymentInfoMessageText.equals("Do not add any payment information in the comment field")) {
            System.out.println("Payment information message is displayed correctly");
        } else {
            System.out.println("Payment information message is not displayed correctly");
        }
        
        //Entering subject in the "Subject" field
        WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
        subjectField.sendKeys("Air Canada Booking");
        
        //Clicking on the "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
        submitButton.click();
        
        //Verifying the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[contains(text(),'Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.')]"));
        String confirmationMessageText = confirmationMessage.getText();
        if(confirmationMessageText.contains("Thank you for sharing your travel experience")) {
            System.out.println("Confirmation message is displayed correctly");
        } else {
            System.out.println("Confirmation message is not displayed correctly");
        }
        
        //Closing the browser
        driver.quit();

    }

}