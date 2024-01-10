// Importing necessary packages
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AirCanadaContactUs {

	public static void main(String[] args) throws InterruptedException {
		
		// Setting system property for chrome driver
		System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
		
		// Creating a new instance of the Chrome driver
		WebDriver driver = new ChromeDriver();
		
		// Navigating to the Air Canada Contact Us page
		driver.get("https://accc-uatmaster.microsoftcrmportals.com/en-CA/air-canada-contact-us/");
		
		// Maximizing the browser window
		driver.manage().window().maximize();
		
		// Selecting the "General Concerns" tile
		WebElement generalConcernsTile = driver.findElement(By.xpath("//div[@data-title='General Concerns']"));
		generalConcernsTile.click();
		
		// Selecting "At the Airport" option in the Regarding dropdown
		WebElement regardingDropdown = driver.findElement(By.id("regarding"));
		Select regardingSelect = new Select(regardingDropdown);
		regardingSelect.selectByVisibleText("At the Airport");
		
		// Selecting "Check-in" option in the Issue dropdown field
		WebElement issueDropdown = driver.findElement(By.id("issue"));
		Select issueSelect = new Select(issueDropdown);
		issueSelect.selectByVisibleText("Check-in");
		
		// Clicking on the "Next" button
		WebElement nextButton1 = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
		nextButton1.click();
		
		// Entering email address in the Email Address field and Confirm Email Address field
		WebElement emailAddressField = driver.findElement(By.id("emailaddress"));
		emailAddressField.sendKeys("bharathkumar-n@hcl.com");
		
		WebElement confirmEmailAddressField = driver.findElement(By.id("confirmemailaddress"));
		confirmEmailAddressField.sendKeys("bharathkumar-n@hcl.com");
		
		// Selecting "Mr." option in the Title dropdown
		WebElement titleDropdown = driver.findElement(By.id("title"));
		Select titleSelect = new Select(titleDropdown);
		titleSelect.selectByVisibleText("Mr.");
		
		// Entering First Name and Last Name
		WebElement firstNameField = driver.findElement(By.id("firstname"));
		firstNameField.sendKeys("Bharath");
		
		WebElement lastNameField = driver.findElement(By.id("lastname"));
		lastNameField.sendKeys("Ice");
		
		// Entering Permanent Mailing Address, City, Province/State, Postal/Zipcode
		WebElement permanentMailingAddressField = driver.findElement(By.id("permanentmailingaddress"));
		permanentMailingAddressField.sendKeys("Harvest");
		
		WebElement cityField = driver.findElement(By.id("city"));
		cityField.sendKeys("Ontario");
		
		WebElement provinceStateField = driver.findElement(By.id("provincestate"));
		provinceStateField.sendKeys("ON");
		
		WebElement provinceStateDropdown = driver.findElement(By.id("provincestatedropdown"));
		Select provinceStateSelect = new Select(provinceStateDropdown);
		provinceStateSelect.selectByVisibleText("ON Ontario");
		
		WebElement postalZipcodeField = driver.findElement(By.id("postalzipcode"));
		postalZipcodeField.sendKeys("M9C 4Y1");
		
		// Selecting "Canada" option in the Country/Region dropdown
		WebElement countryRegionDropdown = driver.findElement(By.id("countryregion"));
		Select countryRegionSelect = new Select(countryRegionDropdown);
		countryRegionSelect.selectByVisibleText("Canada");
		
		// Entering Primary Phone No
		WebElement primaryPhoneNoField = driver.findElement(By.id("primaryphoneno"));
		primaryPhoneNoField.sendKeys("8801070616");
		
		// Clicking on the "Next" button
		WebElement nextButton2 = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
		nextButton2.click();
		
		// Selecting "Air Canada" option in the Airline dropdown
		WebElement airlineDropdown = driver.findElement(By.id("airline"));
		Select airlineSelect = new Select(airlineDropdown);
		airlineSelect.selectByVisibleText("Air Canada");
		
		// Entering Flight Number, Flight Date, Departure Airport, Arrival Airport, Booking Reference, and Ticket Number
		WebElement flightNumberField = driver.findElement(By.id("flightnumber"));
		flightNumberField.sendKeys("122");
		
		WebElement flightDateField = driver.findElement(By.id("flightdate"));
		flightDateField.sendKeys("2023-08-17");
		
		WebElement departureAirportField = driver.findElement(By.id("departureairport"));
		departureAirportField.sendKeys("YVR");
		
		WebElement departureAirportDropdown = driver.findElement(By.id("departureairportdropdown"));
		Select departureAirportSelect = new Select(departureAirportDropdown);
		departureAirportSelect.selectByVisibleText("YVR Vancouver Canada (Vancouver Intl)");
		
		WebElement arrivalAirportField = driver.findElement(By.id("arrivalairport"));
		arrivalAirportField.sendKeys("YYZ");
		
		WebElement arrivalAirportDropdown = driver.findElement(By.id("arrivalairportdropdown"));
		Select arrivalAirportSelect = new Select(arrivalAirportDropdown);
		arrivalAirportSelect.selectByVisibleText("YYZ Toronto Canada (Lester B. Pearson Intl)");
		
		WebElement bookingReferenceField = driver.findElement(By.id("bookingreference"));
		bookingReferenceField.sendKeys("3ED8RH");
		
		WebElement ticketNumberField = driver.findElement(By.id("ticketnumber"));
		ticketNumberField.sendKeys("0142173322307");
		
		// Clicking on the "Next" button
		WebElement nextButton3 = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
		nextButton3.click();
		
		// Verifying that "Do not add any payment information in the comment field" is displayed below the message field
		WebElement messageField = driver.findElement(By.id("message"));
		WebElement paymentInfoMessage = driver.findElement(By.xpath("//div[contains(text(),'Do not add any payment information in the comment field')]"));
		assert paymentInfoMessage.isDisplayed() : "Payment information message is not displayed.";
		
		// Entering "Air Canada Booking" in the Subject field
		WebElement subjectField = driver.findElement(By.id("subject"));
		subjectField.sendKeys("Air Canada Booking");
		
		// Clicking on the "Submit" button
		WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		submitButton.click();
		
		// Verifying the confirmation message
		WebElement confirmationMessage = driver.findElement(By.xpath("//div[contains(text(),'Thank you for sharing your travel experience. This is an automated response to let you know that we received your comments.')]"));
		assert confirmationMessage.isDisplayed() : "Confirmation message is not displayed.";
		
		// Closing the browser
		driver.close();
	}
}