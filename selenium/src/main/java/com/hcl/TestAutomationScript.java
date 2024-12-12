import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Launch the website
        driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");

        // Select tile "General Concerns"
        driver.findElement(By.xpath("//a[text()='General Concerns']")).click();

        // Select the option "At the Airport" in Regarding dropdown
        WebElement regardingDropdown = driver.findElement(By.xpath("//select[@id='regarding']/option[text()='At the Airport']"));
        Select regardingSelect = new Select(regardingDropdown);
        regardingSelect.selectByVisibleText("At the Airport");

        // Select "Check-in" in Issue dropdown field
        WebElement issueDropdown = driver.findElement(By.xpath("//select[@id='issue']/option[text()='Check-in']"));
        Select issueSelect = new Select(issueDropdown);
        issueSelect.selectByVisibleText("Check-in");

        // Click on "Next" button
        driver.findElement(By.xpath("//button[text()='Next']")).click();

        // Enter email address
        driver.findElement(By.id("input_emailaddress")).sendKeys("bharathkumar-n@hcl.com");

        // Enter confirm email address
        driver.findElement(By.id("input_emailaddressconfirm")).sendKeys("bharathkumar-n@hcl.com");

        // Select the option "Mr." in Title dropdown
        WebElement titleDropdown = driver.findElement(By.xpath("//select[@id='title']/option[text()='Mr.']"));
        Select titleSelect = new Select(titleDropdown);
        titleSelect.selectByVisibleText("Mr.");

        // Enter first name
        driver.findElement(By.id("input_firstname")).sendKeys("Bharath");

        // Enter last name
        driver.findElement(By.id("input_lastname")).sendKeys("Ice");

        // Enter permanent mailing address
        driver.findElement(By.id("input_addressline1")).sendKeys("Harvest");

        // Enter city
        driver.findElement(By.id("input_city")).sendKeys("Ontario");

        // Enter province/state
        driver.findElement(By.id("input_state")).sendKeys("ON");

        // Select the option "ON Ontario" in province/state dropdown
        WebElement stateDropdown = driver.findElement(By.xpath("//select[@id='state_provinceterritory']/option[text()='ON Ontario']"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("ON Ontario");

        // Enter postal/zipcode
        driver.findElement(By.id("input_postalzip")).sendKeys("M9C 4Y1");

        // Select the option "Canada" in country/region dropdown
        WebElement countryDropdown = driver.findElement(By.xpath("//select[@id='country']/option[text()='Canada']"));
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("Canada");

        // Enter primary phone number
        driver.findElement(By.id("input_primaryphone")).sendKeys("8801070616");

        // Click on "Next" button
        driver.findElement(By.xpath("//button[text()='Next']")).click();

        // Select the option "Air Canada" in airline dropdown
        WebElement airlineDropdown = driver.findElement(By.xpath("//select[@id='airline']/option[text()='Air Canada']"));
        Select airlineSelect = new Select(airlineDropdown);
        airlineSelect.selectByVisibleText("Air Canada");

        // Enter flight number
        driver.findElement(By.id("input_flightnumber")).sendKeys("122");

        // Enter flight date
        driver.findElement(By.id("input_flightdate")).sendKeys("2023-08-17");

        // Enter departure airport
        driver.findElement(By.id("input_departureairport")).sendKeys("YVR");

        // Select the option "YVR Vancouver Canada (Vancouver Intl)" in departure airport dropdown
        WebElement departureAirportDropdown = driver.findElement(By.xpath("//select[@id='departureairport']/option[text()='YVR Vancouver Canada (Vancouver Intl)']"));
        Select departureAirportSelect = new Select(departureAirportDropdown);
        departureAirportSelect.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");

        // Enter arrival airport
        driver.findElement(By.id("input_arrivalairport")).sendKeys("YYZ");

        // Select the option "YYZ Toronto Canada (Lester B. Pearson Intl)" in arrival airport dropdown
        WebElement arrivalAirportDropdown = driver.findElement(By.xpath("//select[@id='arrivalairport']/option[text()='YYZ Toronto Canada (Lester B. Pearson Intl)']"));
        Select arrivalAirportSelect = new Select(arrivalAirportDropdown);
        arrivalAirportSelect.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");

        // Enter booking reference
        driver.findElement(By.id("input_bookingreference")).sendKeys("3ED8RH");

        // Enter ticket number
        driver.findElement(By.id("input_ticketnumber")).sendKeys("0142173322307");

        // Click on "Next" button
        driver.findElement(By.xpath("//button[text()='Next']")).click();

        // Verify "Do not add any payment information in the comment field" below message field in the current page
        WebElement messageField = driver.findElement(By.xpath("//textarea[@id='description']"));
        String messageFieldValue = messageField.getAttribute("value");
        if (messageFieldValue.contains("Do not add any payment information in the comment field")) {
            System.out.println("Verification successful. Message field contains the expected text.");
        } else {
            System.out.println("Verification failed. Message field does not contain the expected text.");
        }

        // Enter subject
        driver.findElement(By.id("input_subject")).sendKeys("Air Canada Booking");

        // Click on "Submit" button
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        // Verify "Thank you for sharing your travel experience..." displayed in the current page
        WebElement successMessage = driver.findElement(By.xpath("//div[@id='form_success_message']"));
        String successMessageText = successMessage.getText();
        if (successMessageText.equals("Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments. A file number has been assigned and will be emailed to you shortly. Your feedback is very important and it may take some time to research and investigate your concerns. We will get back to you as soon as possible. Thank you for your patience.")) {
            System.out.println("Verification successful. Success message is displayed correctly.");
        } else {
            System.out.println("Verification failed. Success message is not displayed correctly.");
        }

        // Close the browser
        driver.quit();
    }
}
