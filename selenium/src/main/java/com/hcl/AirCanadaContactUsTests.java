
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Locale;

public class AirCanadaContactUsTests {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.aircanada.com/contactus"); // Actual URL may differ
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    void fillGeneralConcernsFlow(String regarding, String issue) {
        driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
        driver.findElement(By.id("regardingDropdown")).click();
        driver.findElement(By.xpath("//option[text()='" + regarding + "']")).click();
        driver.findElement(By.id("issueDropdown")).click();
        driver.findElement(By.xpath("//option[text()='" + issue + "']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
    }

    void fillPassengerInformation(String email, String confirmEmail, String title, String firstName, String lastName,
                                  String address, String city, String province, String zip, String country, String phone) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("confirmEmail")).sendKeys(confirmEmail);
        driver.findElement(By.id("titleDropdown")).click();
        driver.findElement(By.xpath("//option[text()='" + title + "']")).click();
        if (!firstName.isEmpty()) driver.findElement(By.id("firstName")).sendKeys(firstName);
        if (!lastName.isEmpty()) driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("address")).sendKeys(address);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("provinceDropdown")).click();
        driver.findElement(By.xpath("//option[text()='" + province + "']")).click();
        driver.findElement(By.id("zip")).sendKeys(zip);
        driver.findElement(By.id("countryDropdown")).click();
        driver.findElement(By.xpath("//option[text()='" + country + "']")).click();
        driver.findElement(By.id("phone")).sendKeys(phone);
        driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
    }

    void fillAirlineInformation(String airline, String flightNo, String flightDate,
                                String depAirport, String arrAirport, String bookingRef, String ticketNo) {
        driver.findElement(By.id("airlineDropdown")).click();
        driver.findElement(By.xpath("//option[text()='" + airline + "']")).click();
        driver.findElement(By.id("flightNumber")).sendKeys(flightNo);
        driver.findElement(By.id("flightDate")).sendKeys(flightDate);
        driver.findElement(By.id("departureAirport")).sendKeys(depAirport);
        driver.findElement(By.id("arrivalAirport")).sendKeys(arrAirport);
        if (bookingRef != null) driver.findElement(By.id("bookingReference")).sendKeys(bookingRef);
        if (ticketNo != null) driver.findElement(By.id("ticketNumber")).sendKeys(ticketNo);
        driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
    }

    void fillSubjectAndSubmit(String subject) {
        String warningMsg = driver.findElement(By.xpath("//*[contains(text(),'Do not add any payment information')]")).getText();
        Assert.assertTrue(warningMsg.contains("Do not add any payment information"));
        driver.findElement(By.id("subject")).sendKeys(subject);
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
    }

    // --- TEST CASES ---

    @Test
    public void TC_AC_01_Positive_FlowTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("valid.email@test.com", "valid.email@test.com", "Mr.", "John", "Doe",
                "123 Main St", "Toronto", "Ontario", "M4B 1B3", "Canada", "4161234567");
        fillAirlineInformation("Air Canada", "123", "2024-12-25", "YYZ", "YVR", "ABCD12", "0147859632147");
        fillSubjectAndSubmit("Issue with Check-in at the airport");
        WebElement confirmMsg = driver.findElement(By.xpath("//*[contains(text(),'travel experience has been received')]"));
        Assert.assertTrue(confirmMsg.isDisplayed());
    }

    @Test
    public void TC_AC_02_Negative_BlankMandatoryFieldsTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("valid.email@test.com", "valid.email@test.com", "Mr.", "", "Doe",
                "123 Main St", "Toronto", "Ontario", "M4B 1B3", "Canada", "4161234567");
        WebElement error = driver.findElement(By.id("firstName-error"));
        Assert.assertTrue(error.getText().contains("First Name field is required"));
        boolean nextDisabled = driver.findElement(By.xpath("//button[contains(text(),'Next')]")).isEnabled();
        Assert.assertFalse(nextDisabled);
    }

    @Test
    public void TC_AC_03_Negative_InvalidEmailFormatTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("invalid-email", "invalid-email", "Mr.", "John", "Doe",
                "123 Main St", "Toronto", "Ontario", "M4B 1B3", "Canada", "4161234567");
        WebElement error = driver.findElement(By.id("email-error"));
        Assert.assertTrue(error.getText().contains("email address is invalid"));
    }

    @Test
    public void TC_AC_04_Negative_MismatchedEmailsTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("user1@test.com", "user2@test.com", "Mr.", "John", "Doe",
                "123 Main St", "Toronto", "Ontario", "M4B 1B3", "Canada", "4161234567");
        WebElement error = driver.findElement(By.id("confirmEmail-error"));
        Assert.assertTrue(error.getText().contains("emails do not match"));
    }

    @Test
    public void TC_AC_05_Positive_LongInputsTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        // Example: assume max 50 chars for names, address, city, subject
        String longStr = "A".repeat(50);
        fillPassengerInformation("maxinput@test.com", "maxinput@test.com", "Mr.", longStr, longStr,
                longStr, longStr, "Ontario", "M4B 1B3", "Canada", "4161234567");
        fillAirlineInformation("Air Canada", "123", "2024-12-25", "YYZ", "YVR", "ABCD12", "0147859632147");
        fillSubjectAndSubmit(longStr);
        WebElement confirmMsg = driver.findElement(By.xpath("//*[contains(text(),'travel experience has been received')]"));
        Assert.assertTrue(confirmMsg.isDisplayed());
    }

    @Test
    public void TC_AC_06_Edge_SpecialCharsFieldsTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("specialchar@test.com", "specialchar@test.com", "Mr.", "Anne-Marie", "O'Neil",
                "123, King-St.", "Quebec", "Quebec", "G1K 7P6", "Canada", "5149876543");
        fillAirlineInformation("Air Canada", "321", "2024-10-01", "YUL", "YYC", "XYZ987", "0132147856987");
        fillSubjectAndSubmit("Test avec des caractères spéciaux");
        WebElement confirmMsg = driver.findElement(By.xpath("//*[contains(text(),'travel experience has been received')]"));
        Assert.assertTrue(confirmMsg.isDisplayed());
    }

    @Test
    public void TC_AC_07_Negative_InvalidPostalCodeTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("badzip@test.com", "badzip@test.com", "Mr.", "John", "Doe",
                "123 Main St", "Toronto", "Ontario", "123", "Canada", "4161234567");
        WebElement error = driver.findElement(By.id("zip-error"));
        Assert.assertTrue(error.getText().contains("postal/zipcode is invalid"));
    }

    @Test
    public void TC_AC_08_Edge_NonNumericPhoneTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("phone@test.com", "phone@test.com", "Mr.", "John", "Doe",
                "123 Main St", "Toronto", "Ontario", "M4B 1B3", "Canada", "abcde12345");
        WebElement error = driver.findElement(By.id("phone-error"));
        Assert.assertTrue(error.getText().contains("invalid phone number format"));
    }

    @Test
    public void TC_AC_09_Positive_AlternativeTitlesTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("alt.title@test.com", "alt.title@test.com", "Ms.", "Jane", "Doe",
                "123 Main St", "Montreal", "Quebec", "H2Y 1C6", "Canada", "5141234567");
        fillAirlineInformation("Air Canada", "321", "2024-11-15", "YUL", "YYC", "QRST98", "0156987456321");
        fillSubjectAndSubmit("Ms. passenger check-in issue");
        WebElement confirmMsg = driver.findElement(By.xpath("//*[contains(text(),'travel experience has been received')]"));
        Assert.assertTrue(confirmMsg.isDisplayed());
    }

    @Test
    public void TC_AC_10_Edge_BookingFieldsOptionalTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("optionalfields@test.com", "optionalfields@test.com", "Mr.", "John", "Smith",
                "123 King St", "Vancouver", "British Columbia", "V5K 0A1", "Canada", "6041234567");
        fillAirlineInformation("Air Canada", "222", "2024-10-30", "YVR", "YYZ", "", "0147859632147");
        if (isElementPresent(By.id("bookingReference-error"))) {
            Assert.assertTrue(driver.findElement(By.id("bookingReference-error")).isDisplayed());
        } else {
            fillSubjectAndSubmit("No booking reference provided");
            WebElement confirmMsg = driver.findElement(By.xpath("//*[contains(text(),'travel experience has been received')]"));
            Assert.assertTrue(confirmMsg.isDisplayed());
        }
    }

    @Test
    public void TC_AC_11_Negative_MissingSubjectTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("missing.subject@test.com", "missing.subject@test.com", "Mr.", "John", "Doe",
                "221B Baker St", "Ottawa", "Ontario", "K1A 0B1", "Canada", "3431231234");
        fillAirlineInformation("Air Canada", "456", "2024-09-20", "YOW", "YEG", "LMNO45", "0165987412365");
        // Do NOT fill subject
        String warningMsg = driver.findElement(By.xpath("//*[contains(text(),'Do not add any payment information')]")).getText();
        Assert.assertTrue(warningMsg.contains("Do not add any payment information"));
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
        WebElement error = driver.findElement(By.id("subject-error"));
        Assert.assertTrue(error.getText().contains("subject field is required"));
    }

    @Test
    public void TC_AC_12_Negative_PaymentInfoInCommentTest() {
        fillGeneralConcernsFlow("At the Airport", "Check-in");
        fillPassengerInformation("paymentinfo@test.com", "paymentinfo@test.com", "Mr.", "John", "Doe",
                "123 Payment Rd", "Moncton", "New Brunswick", "E1C 1Y6", "Canada", "5061231122");
        fillAirlineInformation("Air Canada", "789", "2024-08-10", "YQM", "YVR", "UVWX12", "0123698745123");
        String warningMsg = driver.findElement(By.xpath("//*[contains(text(),'Do not add any payment information')]")).getText();
        Assert.assertTrue(warningMsg.contains("Do not add any payment information"));
        driver.findElement(By.id("subject")).sendKeys("My credit card is 4111 1111 1111 1111");
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
        WebElement error = driver.findElement(By.id("subject-error"));
        Assert.assertTrue(error.getText().toLowerCase().contains("payment"));
    }

    @Test
    public void TC_AC_13_Edge_LanguageAndCountryTest() {
        driver.quit();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=fr");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.aircanada.com/contactus");

        fillGeneralConcernsFlow("À l'aéroport", "Enregistrement");
        fillPassengerInformation("frlang@test.com", "frlang@test.com", "M.", "Jean", "Dupont",
                "45 Rue de Rivoli", "Paris", "Île-de-France", "75001", "France", "0145123456");
        fillAirlineInformation("Air Canada", "456", "2024-09-20", "CDG", "YUL", "FR1234", "0123698745123");
        fillSubjectAndSubmit("Problème d’enregistrement à l’aéroport");
        WebElement confirmMsg = driver.findElement(By.xpath("//*[contains(text(),'expérience de voyage a été reçue')]"));
        Assert.assertTrue(confirmMsg.isDisplayed());
    }

    // Helper method
    public boolean isElementPresent(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
