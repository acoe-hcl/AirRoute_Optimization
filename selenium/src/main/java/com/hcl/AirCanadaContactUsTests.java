
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Locale;

public class AirCanadaContactUsTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    void navigateToContactUsPage() {
        driver.get("https://www.aircanada.com/contact-us");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Contact Us')]")));
    }

    void fillGeneralConcerns_CheckinFlow() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'General Concerns')]"))).click();
        new Select(driver.findElement(By.id("regarding-dropdown"))).selectByVisibleText("At the Airport");
        new Select(driver.findElement(By.id("issue-dropdown"))).selectByVisibleText("Check-in");
        driver.findElement(By.id("btn-next")).click();
    }

    void fillPassengerInfo(String email, String confirmEmail, String title, String fName, String lName,
                          String address, String city, String province, String postal, String country,
                          String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);
        driver.findElement(By.id("confirm-email")).sendKeys(confirmEmail);
        new Select(driver.findElement(By.id("title"))).selectByVisibleText(title);
        driver.findElement(By.id("first-name")).sendKeys(fName);
        driver.findElement(By.id("last-name")).sendKeys(lName);
        driver.findElement(By.id("address")).sendKeys(address);
        driver.findElement(By.id("city")).sendKeys(city);
        new Select(driver.findElement(By.id("province"))).selectByVisibleText(province);
        driver.findElement(By.id("postal")).sendKeys(postal);
        new Select(driver.findElement(By.id("country"))).selectByVisibleText(country);
        driver.findElement(By.id("phone")).sendKeys(phone);
        driver.findElement(By.id("btn-next")).click();
    }

    void fillAirlineInfo(String airline, String flightNum, String flightDate, String depAirport, String arrAirport,
                         String bookingRef, String ticketNum) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airline"))).click();
        new Select(driver.findElement(By.id("airline"))).selectByVisibleText(airline);
        driver.findElement(By.id("flight-number")).sendKeys(flightNum);
        driver.findElement(By.id("flight-date")).sendKeys(flightDate);
        driver.findElement(By.id("departure-airport-input")).sendKeys(depAirport);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='departure-airport-dropdown']//li[1]"))).click();
        driver.findElement(By.id("arrival-airport-input")).sendKeys(arrAirport);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='arrival-airport-dropdown']//li[1]"))).click();
        driver.findElement(By.id("booking-reference")).sendKeys(bookingRef);
        driver.findElement(By.id("ticket-number")).sendKeys(ticketNum);
        driver.findElement(By.id("btn-next")).click();
    }

    void fillPaymentAndSubmit(String subject, String comment) {
        WebElement paymentMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("payment-info-msg")));
        Assert.assertEquals(paymentMsg.getText().trim(), "Do not add any payment information in the comment field");
        driver.findElement(By.id("subject")).sendKeys(subject);
        if (comment != null)
            driver.findElement(By.id("comment")).sendKeys(comment);
        driver.findElement(By.id("btn-submit")).click();
    }

    void assertConfirmationMessage() {
        WebElement confMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation-msg")));
        String msg = confMsg.getText();
        Assert.assertTrue(msg.contains("travel experience has been received"));
        Assert.assertTrue(msg.matches("(?s).*file number.*will be emailed shortly.*researched and investigated.*"));
    }

    @Test
    public void TC_AC_01_Positive_FlowTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("testuser@example.com", "testuser@example.com", "Mr.", "John", "Doe",
                "123 Elm St", "Toronto", "Ontario", "M5G2C3", "Canada", "4161234567");
        fillAirlineInfo("Air Canada", "1234", "2024-12-05", "YYZ", "YVR", "ABC123", "0141234567890");
        fillPaymentAndSubmit("Check-in issue at airport", null);
        assertConfirmationMessage();
    }

    @Test
    public void TC_AC_02_Negative_BlankMandatoryFieldsTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        // Leave first name blank
        fillPassengerInfo("testuser@example.com", "testuser@example.com", "Mr.", "", "Doe",
                "123 Elm St", "Toronto", "Ontario", "M5G2C3", "Canada", "4161234567");
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name-error")));
        Assert.assertTrue(error.getText().contains("First Name field is required"));
        Assert.assertTrue(driver.findElement(By.id("passenger-info-section")).isDisplayed());
    }

    @Test
    public void TC_AC_03_Negative_InvalidEmailFormatTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("invalid-email", "invalid-email", "Mr.", "John", "Doe",
                "123 Elm St", "Toronto", "Ontario", "M5G2C3", "Canada", "4161234567");
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-error")));
        Assert.assertTrue(error.getText().toLowerCase().contains("invalid email address"));
    }

    @Test
    public void TC_AC_04_Negative_MismatchedEmailsTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("user1@test.com", "user2@test.com", "Mr.", "John", "Doe",
                "123 Elm St", "Toronto", "Ontario", "M5G2C3", "Canada", "4161234567");
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm-email-error")));
        Assert.assertTrue(error.getText().toLowerCase().contains("emails do not match"));
    }

    @Test
    public void TC_AC_05_Positive_LongInputsTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        String longStr = "A".repeat(50);
        fillPassengerInfo("maxchar@test.com", "maxchar@test.com", "Mr.", longStr, longStr,
                longStr, longStr, "Ontario", "M5G2C3", "Canada", "4161234567");
        fillAirlineInfo("Air Canada", "1234", "2024-12-05", "YYZ", "YVR", "ABC123", "0141234567890");
        fillPaymentAndSubmit(longStr, null);
        assertConfirmationMessage();
    }

    @Test
    public void TC_AC_06_Edge_SpecialCharsFieldsTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("special@test.com", "special@test.com", "Mr.", "Anne-Marie", "O'Neil",
                "123 Main St.", "St. John's", "Newfoundland and Labrador", "A1A2B2", "Canada", "7095551234");
        fillAirlineInfo("Air Canada", "1234", "2024-12-05", "YYT", "YUL", "DEF456", "0141234567890");
        fillPaymentAndSubmit("Bag issue upon arrival", null);
        assertConfirmationMessage();
    }

    @Test
    public void TC_AC_07_Negative_InvalidPostalCodeTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("user@test.com", "user@test.com", "Mr.", "John", "Doe",
                "123 Elm St", "Toronto", "Ontario", "123", "Canada", "4161234567");
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-error")));
        Assert.assertTrue(error.getText().toLowerCase().contains("postal" ) || error.getText().toLowerCase().contains("invalid"));
    }

    @Test
    public void TC_AC_08_Edge_NonNumericPhoneTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("user@test.com", "user@test.com", "Mr.", "John", "Doe",
                "123 Elm St", "Toronto", "Ontario", "M5G2C3", "Canada", "abcde12345");
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone-error")));
        Assert.assertTrue(error.getText().toLowerCase().contains("phone") && error.getText().toLowerCase().contains("invalid"));
    }

    @Test
    public void TC_AC_09_Positive_AlternativeTitlesTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("msuser@test.com", "msuser@test.com", "Ms.", "Jane", "Smith",
                "456 Oak Rd", "Montreal", "Quebec", "H2Y1C6", "Canada", "5143219876");
        fillAirlineInfo("Air Canada", "5678", "2024-12-10", "YUL", "YYZ", "JKL789", "0141234567890");
        fillPaymentAndSubmit("Title is Ms.", null);
        assertConfirmationMessage();
    }

    @Test
    public void TC_AC_10_Edge_BookingFieldsOptionalTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("user@test.com", "user@test.com", "Mr.", "John", "Doe",
                "125 Elm St", "Toronto", "Ontario", "M5G2C3", "Canada", "4161234567");
        // Leave ticket number blank
        fillAirlineInfo("Air Canada", "1234", "2024-12-23", "YYZ", "YVR", "ABC123", "");
        boolean ticketRequired = false;
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ticket-number-error")));
            Assert.assertTrue(error.getText().toLowerCase().contains("ticket number") && error.getText().toLowerCase().contains("required"));
            ticketRequired = true;
        } catch (Exception ignored) {}
        if (!ticketRequired) {
            fillPaymentAndSubmit("Booking/ticket optional path", null);
            assertConfirmationMessage();
        }
    }

    @Test
    public void TC_AC_11_Negative_MissingSubjectTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("user@test.com", "user@test.com", "Mr.", "John", "Doe",
                "123 Willow Dr", "Ottawa", "Ontario", "K1A0B1", "Canada", "6135559876");
        fillAirlineInfo("Air Canada", "2222", "2024-12-15", "YOW", "YYZ", "GHI123", "0141234567890");
        WebElement paymentMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("payment-info-msg")));
        Assert.assertTrue(paymentMsg.isDisplayed());
        // Do not enter subject
        driver.findElement(By.id("btn-submit")).click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subject-error")));
        Assert.assertTrue(error.getText().toLowerCase().contains("required"));
    }

    @Test
    public void TC_AC_12_Negative_PaymentInfoInCommentTest() {
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("paytest@test.com", "paytest@test.com", "Mr.", "Carl", "Murray",
                "222 Sunset Ave", "Vancouver", "British Columbia", "V5K1A1", "Canada", "6041230000");
        fillAirlineInfo("Air Canada", "9999", "2024-12-25", "YVR", "YYC", "XYZ456", "0141234567890");
        fillPaymentAndSubmit("My credit card is 4111 1111 1111 1111", null);
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subject-error")));
        Assert.assertTrue(error.getText().toLowerCase().contains("payment") || error.getText().toLowerCase().contains("not allowed"));
    }

    @Test
    public void TC_AC_13_Edge_LanguageAndCountryTest() {
        driver.quit();
        // Restart browser with desired language (French)
        System.setProperty("webdriver.chrome.args", "--lang=fr");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        navigateToContactUsPage();
        fillGeneralConcerns_CheckinFlow();
        fillPassengerInfo("fruser@test.com", "fruser@test.com", "M.", "Jean", "Dupont",
                "23 rue de la Paix", "Paris", "Île-de-France", "75001", "France", "123456789");
        fillAirlineInfo("Air Canada", "3456", "2024-12-30", "CDG", "YYZ", "FR9876", "0141234567890");
        fillPaymentAndSubmit("Problème d'enregistrement", null);
        WebElement confMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation-msg")));
        Assert.assertTrue(confMsg.isDisplayed());
        Assert.assertTrue(confMsg.getText().toLowerCase(Locale.FRENCH).contains("expérience de voyage") ||
                confMsg.getText().toLowerCase().contains("travel experience"));
    }
}
