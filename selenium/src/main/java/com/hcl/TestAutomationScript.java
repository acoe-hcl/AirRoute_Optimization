import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set the path of chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        
        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Navigate to the website
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");
        
        // Select tile "General Concerns"
        WebElement tile = driver.findElement(By.xpath("//div[@class='ms-portals-tile']//span[text()='General Concerns']"));
        tile.click();
        
        // Select the option "At the Airport" in Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.xpath("//select[@id='phae68b8e-7abf-eb11-bacb-000d3a8fe7a8']"));
        WebElement atAirportOption = regardingDropdown.findElement(By.xpath("//option[text()='At the Airport']"));
        atAirportOption.click();
        
        // Select "Check-in" in Issue dropdown field
        WebElement issueDropdown = driver.findElement(By.xpath("//select[@id='04ae68b8e-7abf-eb11-bacb-000d3a8fe7a8']"));
        WebElement checkInOption = issueDropdown.findElement(By.xpath("//option[text()='Check-in']"));
        checkInOption.click();
        
        // Click on "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@id='button-Next']"));
        nextButton.click();
        
        // Enter email address
        WebElement emailField = driver.findElement(By.xpath("//input[@id='ph56535b7-28e8-9ef2-df17-0ffd7fb18507']"));
        emailField.sendKeys("bharathkumar-n@hcl.com");
        
        // Enter confirm email address
        WebElement confirmEmailField = driver.findElement(By.xpath("//input[@id='phf6f8668-28e8-9ef2-df17-0ffd7fb18507']"));
        confirmEmailField.sendKeys("bharathkumar-n@hcl.com");
        
        // Select "Mr." in Title dropdown
        WebElement titleDropdown = driver.findElement(By.xpath("//select[@id='ph30ea6fc1-8ff2-4671-ef42-f974d6d80319']"));
        WebElement mrOption = titleDropdown.findElement(By.xpath("//option[text()='Mr.']"));
        mrOption.click();
        
        // Enter first name
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='ph7fa20616-8ff2-4671-ef42-f974d6d80319']"));
        firstNameField.sendKeys("Bharath");
        
        // Enter last name
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='phe2bb400-8ff2-4671-ef42-f974d6d80319']"));
        lastNameField.sendKeys("Ice");
        
        // Enter permanent mailing address
        WebElement mailingAddressField = driver.findElement(By.xpath("//input[@id='phffbe58e5-8ff2-4671-ef42-f974d6d80319']"));
        mailingAddressField.sendKeys("Harvest");
        
        // Enter city
        WebElement cityField = driver.findElement(By.xpath("//input[@id='ph00057709-8ff2-4671-ef42-f974d6d80319']"));
        cityField.sendKeys("Ontario");
        
        // Enter province/state
        WebElement provinceField = driver.findElement(By.xpath("//input[@id='ph1c0601f7-8ff2-4671-ef42-f974d6d80319']"));
        provinceField.sendKeys("ON");
        
        // Select "ON Ontario" in Province/State dropdown
        WebElement provinceDropdown = driver.findElement(By.xpath("//select[@id='ph62d67877-8ff2-4671-ef42-f974d6d80319']"));
        WebElement onOntarioOption = provinceDropdown.findElement(By.xpath("//option[text()='ON Ontario']"));
        onOntarioOption.click();
        
        // Enter postal/zipcode
        WebElement postalField = driver.findElement(By.xpath("//input[@id='ph4ac5e69f-8ff2-4671-ef42-f974d6d80319']"));
        postalField.sendKeys("M9C 4Y1");
        
        // Select "Canada" in Country/Region dropdown
        WebElement countryDropdown = driver.findElement(By.xpath("//select[@id='ph6f192a7b-0ad3-4aed-8e1e-afb59359b239']"));
        WebElement canadaOption = countryDropdown.findElement(By.xpath("//option[text()='Canada']"));
        canadaOption.click();
        
        // Enter primary phone number
        WebElement phoneField = driver.findElement(By.xpath("//input[@id='ph4a732eef-8ff2-4671-ef42-f974d6d80319']"));
        phoneField.sendKeys("8801070616");
        
        // Click on "Next" button
        nextButton.click();
        
        // Select "Air Canada" in Airline dropdown
        WebElement airlineDropdown = driver.findElement(By.xpath("//select[@id='phc1431ab4-8ff2-4671-ef42-f974d6d80319']"));
        WebElement airCanadaOption = airlineDropdown.findElement(By.xpath("//option[text()='Air Canada']"));
        airCanadaOption.click();
        
        // Enter flight number
        WebElement flightNumberField = driver.findElement(By.xpath("//input[@id='ph843302e8-8ff2-4671-ef42-f974d6d80319']"));
        flightNumberField.sendKeys("122");
        
        // Enter flight date
        WebElement flightDateField = driver.findElement(By.xpath("//input[@id='ph665e4a3e-4700-4d90-e620-acabccf15e22']"));
        flightDateField.sendKeys("2023-08-17");
        
        // Enter departure airport
        WebElement departureAirportField = driver.findElement(By.xpath("//input[@id='ph616cdf2a-8ff2-4671-ef42-f974d6d80319']"));
        departureAirportField.sendKeys("YVR");
        
        // Select "YVR Vancouver Canada (Vancouver Intl)" in Departure Airport dropdown
        WebElement departureAirportDropdown = driver.findElement(By.xpath("//select[@id='ph877aba67-8ff2-4671-ef42-f974d6d80319']"));
        WebElement yvrOption = departureAirportDropdown.findElement(By.xpath("//option[text()='YVR Vancouver Canada (Vancouver Intl)']"));
        yvrOption.click();
        
        // Enter arrival airport
        WebElement arrivalAirportField = driver.findElement(By.xpath("//input[@id='ph07840548-8ff2-4671-ef42-f974d6d80319']"));
        arrivalAirportField.sendKeys("YYZ");
        
        // Select "YYZ Toronto Canada (Lester B. Pearson Intl)" in Arrival Airport dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.xpath("//select[@id='ph7f20718c-8ff2-4671-ef42-f974d6d80319']"));
        WebElement yyzOption = arrivalAirportDropdown.findElement(By.xpath("//option[text()='YYZ Toronto Canada (Lester B. Pearson Intl)']"));
        yyzOption.click();
        
        // Enter booking reference
        WebElement bookingReferenceField = driver.findElement(By.xpath("//input[@id='ph9bfc66b2-8ff2-4671-ef42-f974d6d80319']"));
        bookingReferenceField.sendKeys("3ED8RH");
        
        // Enter ticket number
        WebElement ticketNumberField = driver.findElement(By.xpath("//input[@id='ph7f380f99-8ff2-4671-ef42-f974d6d80319']"));
        ticketNumberField.sendKeys("0142173322307");
        
        // Click on "Next" button
        nextButton.click();
        
        // Verify "Do not add any payment information in the comment field" message
        WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[text()='Do not add any payment information in the comment field']"));
        if(paymentInfoMessage.isDisplayed()) {
            System.out.println("Validation successful: 'Do not add any payment information in the comment field' message is displayed");
        } else {
            System.out.println("Validation failed: 'Do not add any payment information in the comment field' message is not displayed");
        }
        
        // Enter subject
        WebElement subjectField = driver.findElement(By.xpath("//input[@id='ph0a7ce2af-8ff2-4671-ef42-f974d6d80319']"));
        subjectField.sendKeys("Air Canada Booking");
        
        // Click on "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='button-Submit']"));
        submitButton.click();

        // Verify success message
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(),'Thank you for sharing your travel experience')]"));
        if(successMessage.isDisplayed()) {
            System.out.println("Validation successful: 'Thank you for sharing your travel experience' message is displayed");
        } else {
            System.out.println("Validation failed: 'Thank you for sharing your travel experience' message is not displayed");
        }
        
        // Close the browser
        driver.quit();
    }
}
