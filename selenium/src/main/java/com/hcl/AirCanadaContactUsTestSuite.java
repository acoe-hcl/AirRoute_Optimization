import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Locale;

public class AirCanadaContactUsTestSuite {

    public static void main(String[] args) {

        // Set up driver (assume ChromeDriver path set elsewhere)
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // ---------- TC_AC_01_Positive_Flow ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");

            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("testuser1@example.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("testuser1@example.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys("John");
            driver.findElement(By.id("lastName")).sendKeys("Doe");
            driver.findElement(By.id("address")).sendKeys("123 Test Avenue");
            driver.findElement(By.id("city")).sendKeys("Toronto");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Ontario");
            driver.findElement(By.id("postalCode")).sendKeys("M1A 1A1");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("4165551234");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airline")));
            new Select(driver.findElement(By.id("airline"))).selectByVisibleText("Air Canada");
            driver.findElement(By.id("flightNumber")).sendKeys("AC123");
            driver.findElement(By.id("flightDate")).sendKeys("2024-06-25");
            driver.findElement(By.id("departureAirport")).sendKeys("YYZ");
            new Select(driver.findElement(By.id("departureAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("arrivalAirport")).sendKeys("YVR");
            new Select(driver.findElement(By.id("arrivalAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("bookingReference")).sendKeys("ABC123");
            driver.findElement(By.id("ticketNumber")).sendKeys("0141234567890");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentInfoMessage")));
            String paymentMsg = driver.findElement(By.id("paymentInfoMessage")).getText();
            assert paymentMsg.contains("Do not add any payment information");

            driver.findElement(By.id("subject")).sendKeys("Check-in Issue");
            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationMessage")));
            String confMessage = driver.findElement(By.id("confirmationMessage")).getText();
            assert confMessage.contains("travel experience has been received");

            // ---------- TC_AC_02_Negative_BlankMandatoryFields ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("testuser2@example.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("testuser2@example.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).clear();
            driver.findElement(By.id("lastName")).sendKeys("Blank");
            driver.findElement(By.id("address")).sendKeys("123 St");
            driver.findElement(By.id("city")).sendKeys("Calgary");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Alberta");
            driver.findElement(By.id("postalCode")).sendKeys("T1A 2B4");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("5871234567");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            WebElement fnError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName-error")));
            assert fnError.getText().toLowerCase().contains("required");

            // ---------- TC_AC_03_Negative_InvalidEmailFormat ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("invalid-email");
            driver.findElement(By.id("confirmEmail")).sendKeys("invalid-email");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys("Invalid");
            driver.findElement(By.id("lastName")).sendKeys("Email");
            driver.findElement(By.id("address")).sendKeys("789 Wind Rd");
            driver.findElement(By.id("city")).sendKeys("Windsor");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Ontario");
            driver.findElement(By.id("postalCode")).sendKeys("N8P 1M8");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("2268874592");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-error")));
            assert emailError.getText().toLowerCase().contains("invalid");

            // ---------- TC_AC_04_Negative_MismatchedEmails ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("user1@test.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("user2@test.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys("Mismatch");
            driver.findElement(By.id("lastName")).sendKeys("Email");
            driver.findElement(By.id("address")).sendKeys("456 River Rd");
            driver.findElement(By.id("city")).sendKeys("Edmonton");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Alberta");
            driver.findElement(By.id("postalCode")).sendKeys("T5A 3R6");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("7806651234");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            WebElement mismatchError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmEmail-error")));
            assert mismatchError.getText().toLowerCase().contains("match");

            // ---------- TC_AC_05_Positive_LongInputs ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            String longName = "MaxLengthFirstName".repeat(10).substring(0, 50);
            String longLastName = "MaxLengthLastName".repeat(10).substring(0, 50);
            String longAddress = "123 Main Street, Apartment 1234-B, Superlong".repeat(3).substring(0, 100);
            String longCity = "SomeVeryLongCityNameThatIsAllowedByFormField".substring(0, 40);
            String longSubject = "SubjectLineWithMaximumAllowedCharacters".repeat(4).substring(0, 100);

            driver.findElement(By.id("email")).sendKeys("testuserlong@example.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("testuserlong@example.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys(longName);
            driver.findElement(By.id("lastName")).sendKeys(longLastName);
            driver.findElement(By.id("address")).sendKeys(longAddress);
            driver.findElement(By.id("city")).sendKeys(longCity);
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Quebec");
            driver.findElement(By.id("postalCode")).sendKeys("H1A 2B3");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("5146651234");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airline")));
            new Select(driver.findElement(By.id("airline"))).selectByVisibleText("Air Canada");
            driver.findElement(By.id("flightNumber")).sendKeys("AC999");
            driver.findElement(By.id("flightDate")).sendKeys("2024-07-01");
            driver.findElement(By.id("departureAirport")).sendKeys("YUL");
            new Select(driver.findElement(By.id("departureAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("arrivalAirport")).sendKeys("YYC");
            new Select(driver.findElement(By.id("arrivalAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("bookingReference")).sendKeys("LONGIN");
            driver.findElement(By.id("ticketNumber")).sendKeys("0149876543210");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentInfoMessage")));
            driver.findElement(By.id("subject")).sendKeys(longSubject);
            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationMessage")));
            assert driver.findElement(By.id("confirmationMessage")).getText().toLowerCase().contains("confirmation");

            // ---------- TC_AC_06_Edge_SpecialCharsFields ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("specialchar@email.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("specialchar@email.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys("Anne-Marie");
            driver.findElement(By.id("lastName")).sendKeys("O'Neil");
            driver.findElement(By.id("address")).sendKeys("456 Sample St.");
            driver.findElement(By.id("city")).sendKeys("L'Assomption");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Quebec");
            driver.findElement(By.id("postalCode")).sendKeys("J5W 1A1");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("6136771234");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airline")));
            new Select(driver.findElement(By.id("airline"))).selectByVisibleText("Air Canada");
            driver.findElement(By.id("flightNumber")).sendKeys("AC321");
            driver.findElement(By.id("flightDate")).sendKeys("2024-08-12");
            driver.findElement(By.id("departureAirport")).sendKeys("YOW");
            new Select(driver.findElement(By.id("departureAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("arrivalAirport")).sendKeys("YVR");
            new Select(driver.findElement(By.id("arrivalAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("bookingReference")).sendKeys("SPCHAR");
            driver.findElement(By.id("ticketNumber")).sendKeys("0145641239876");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentInfoMessage")));
            driver.findElement(By.id("subject")).sendKeys("Check-in: Special chars - éèàçôñÜ");
            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationMessage")));
            assert driver.findElement(By.id("confirmationMessage")).getText().toLowerCase().contains("confirmation");

            // ---------- TC_AC_07_Negative_InvalidPostalCode ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("invalidpostal@test.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("invalidpostal@test.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys("Test");
            driver.findElement(By.id("lastName")).sendKeys("Postal");
            driver.findElement(By.id("address")).sendKeys("8999 Fake Ave.");
            driver.findElement(By.id("city")).sendKeys("Halifax");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Nova Scotia");
            driver.findElement(By.id("postalCode")).sendKeys("123");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("9028651234");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            WebElement postalError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postalCode-error")));
            assert postalError.getText().toLowerCase().contains("invalid");

            // ---------- TC_AC_08_Edge_NonNumericPhone ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("nonnumericphone@test.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("nonnumericphone@test.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys("Non");
            driver.findElement(By.id("lastName")).sendKeys("Numeric");
            driver.findElement(By.id("address")).sendKeys("9877 Lake Rd");
            driver.findElement(By.id("city")).sendKeys("Regina");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Saskatchewan");
            driver.findElement(By.id("postalCode")).sendKeys("S4P 1A1");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("abcde12345");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            WebElement phoneError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("primaryPhone-error")));
            assert phoneError.getText().toLowerCase().contains("invalid");

            // ---------- TC_AC_09_Positive_AlternativeTitles ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("msuser@mail.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("msuser@mail.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Ms.");
            driver.findElement(By.id("firstName")).sendKeys("Jane");
            driver.findElement(By.id("lastName")).sendKeys("Smith");
            driver.findElement(By.id("address")).sendKeys("255 Main Blvd");
            driver.findElement(By.id("city")).sendKeys("Ottawa");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Ontario");
            driver.findElement(By.id("postalCode")).sendKeys("K2P 2K2");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("6137896543");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airline")));
            new Select(driver.findElement(By.id("airline"))).selectByVisibleText("Air Canada");
            driver.findElement(By.id("flightNumber")).sendKeys("AC987");
            driver.findElement(By.id("flightDate")).sendKeys("2024-10-12");
            driver.findElement(By.id("departureAirport")).sendKeys("YOW");
            new Select(driver.findElement(By.id("departureAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("arrivalAirport")).sendKeys("YUL");
            new Select(driver.findElement(By.id("arrivalAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("bookingReference")).sendKeys("MSALT");
            driver.findElement(By.id("ticketNumber")).sendKeys("0141230984521");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentInfoMessage")));
            driver.findElement(By.id("subject")).sendKeys("Check-in Ms.");
            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationMessage")));
            assert driver.findElement(By.id("confirmationMessage")).getText().toLowerCase().contains("confirmation");

            // ---------- TC_AC_10_Edge_BookingFieldsOptional ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("optionalbooking@mail.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("optionalbooking@mail.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys("Test");
            driver.findElement(By.id("lastName")).sendKeys("NoBooking");
            driver.findElement(By.id("address")).sendKeys("880 Queen St");
            driver.findElement(By.id("city")).sendKeys("Vancouver");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("British Columbia");
            driver.findElement(By.id("postalCode")).sendKeys("V5K 0A1");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("6048019981");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airline")));
            new Select(driver.findElement(By.id("airline"))).selectByVisibleText("Air Canada");
            driver.findElement(By.id("flightNumber")).sendKeys("AC654");
            driver.findElement(By.id("flightDate")).sendKeys("2024-11-15");
            driver.findElement(By.id("departureAirport")).sendKeys("YVR");
            new Select(driver.findElement(By.id("departureAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("arrivalAirport")).sendKeys("YYZ");
            new Select(driver.findElement(By.id("arrivalAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("bookingReference")).clear();
            driver.findElement(By.id("ticketNumber")).sendKeys("0144523456130");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            boolean bookingRefErrorDisplayed = driver.findElements(By.id("bookingReference-error")).size() > 0;
            if (bookingRefErrorDisplayed) {
                assert driver.findElement(By.id("bookingReference-error")).getText().toLowerCase().contains("required");
            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentInfoMessage")));
            }

            // ---------- TC_AC_11_Negative_MissingSubject ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("nolinesubject@mail.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("nolinesubject@mail.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys("No");
            driver.findElement(By.id("lastName")).sendKeys("Subject");
            driver.findElement(By.id("address")).sendKeys("1221 King Rd");
            driver.findElement(By.id("city")).sendKeys("Quebec City");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("Quebec");
            driver.findElement(By.id("postalCode")).sendKeys("G1K 7P4");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("4189423124");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airline")));
            new Select(driver.findElement(By.id("airline"))).selectByVisibleText("Air Canada");
            driver.findElement(By.id("flightNumber")).sendKeys("AC231");
            driver.findElement(By.id("flightDate")).sendKeys("2024-12-01");
            driver.findElement(By.id("departureAirport")).sendKeys("YQB");
            new Select(driver.findElement(By.id("departureAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("arrivalAirport")).sendKeys("YYZ");
            new Select(driver.findElement(By.id("arrivalAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("bookingReference")).sendKeys("NOSUBJ");
            driver.findElement(By.id("ticketNumber")).sendKeys("0141133567892");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentInfoMessage")));
            driver.findElement(By.id("subject")).clear();
            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            WebElement subjectError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subject-error")));
            assert subjectError.getText().toLowerCase().contains("required");

            // ---------- TC_AC_12_Negative_PaymentInfoInComment ----------
            driver.get("https://www.aircanada.com/ca/en/aco/home/fly/customer-support/contact-us.html");
            driver.findElement(By.xpath("//div[contains(text(),'General Concerns')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("At the Airport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Check-in");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("paymentincomment@mail.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("paymentincomment@mail.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr.");
            driver.findElement(By.id("firstName")).sendKeys("Test");
            driver.findElement(By.id("lastName")).sendKeys("PaymentInComment");
            driver.findElement(By.id("address")).sendKeys("888 Payment Test Dr");
            driver.findElement(By.id("city")).sendKeys("Moncton");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("New Brunswick");
            driver.findElement(By.id("postalCode")).sendKeys("E1A 1A6");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
            driver.findElement(By.id("primaryPhone")).sendKeys("5063341234");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airline")));
            new Select(driver.findElement(By.id("airline"))).selectByVisibleText("Air Canada");
            driver.findElement(By.id("flightNumber")).sendKeys("AC888");
            driver.findElement(By.id("flightDate")).sendKeys("2024-09-03");
            driver.findElement(By.id("departureAirport")).sendKeys("YQM");
            new Select(driver.findElement(By.id("departureAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("arrivalAirport")).sendKeys("YYZ");
            new Select(driver.findElement(By.id("arrivalAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("bookingReference")).sendKeys("PAYCMNT");
            driver.findElement(By.id("ticketNumber")).sendKeys("0149875643210");
            driver.findElement(By.xpath("//button[text()='Next']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentInfoMessage")));
            driver.findElement(By.id("subject")).sendKeys("My credit card is 4111 1111 1111 1111");
            driver.findElement(By.xpath("//button[text()='Submit']")).click();

            WebElement paymentSubjectError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subject-error")));
            assert paymentSubjectError.getText().toLowerCase().contains("payment");

            // ---------- TC_AC_13_Edge_LanguageAndCountry ----------
            System.setProperty("lang", "fr");
            driver.get("https://www.aircanada.com/ca/fr/aco/home/fly/customer-support/contact-us.html");

            driver.findElement(By.xpath("//div[contains(text(),'Préoccupations générales')]")).click();
            new Select(driver.findElement(By.id("regarding"))).selectByVisibleText("À l'aéroport");
            new Select(driver.findElement(By.id("issue"))).selectByVisibleText("Enregistrement");
            driver.findElement(By.xpath("//button[@type='submit' or text()='Suivant']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("frenchuser@test.com");
            driver.findElement(By.id("confirmEmail")).sendKeys("frenchuser@test.com");
            new Select(driver.findElement(By.id("title"))).selectByVisibleText("Monsieur");
            driver.findElement(By.id("firstName")).sendKeys("Jean");
            driver.findElement(By.id("lastName")).sendKeys("Dupont");
            driver.findElement(By.id("address")).sendKeys("1 Rue de Paris");
            driver.findElement(By.id("city")).sendKeys("Paris");
            new Select(driver.findElement(By.id("province"))).selectByVisibleText("N/A");
            driver.findElement(By.id("postalCode")).sendKeys("75001");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("France");
            driver.findElement(By.id("primaryPhone")).sendKeys("330145123456");
            driver.findElement(By.xpath("//button[@type='submit' or text()='Suivant']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("airline")));
            new Select(driver.findElement(By.id("airline"))).selectByVisibleText("Air Canada");
            driver.findElement(By.id("flightNumber")).sendKeys("AC850");
            driver.findElement(By.id("flightDate")).sendKeys("2024-07-18");
            driver.findElement(By.id("departureAirport")).sendKeys("CDG");
            new Select(driver.findElement(By.id("departureAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("arrivalAirport")).sendKeys("YUL");
            new Select(driver.findElement(By.id("arrivalAirportDropdown"))).selectByIndex(1);
            driver.findElement(By.id("bookingReference")).sendKeys("FRENCH");
            driver.findElement(By.id("ticketNumber")).sendKeys("0148721094502");
            driver.findElement(By.xpath("//button[@type='submit' or text()='Suivant']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentInfoMessage")));
            driver.findElement(By.id("subject")).sendKeys("Souci à l'enregistrement");
            driver.findElement(By.xpath("//button[@type='submit' or text()='Soumettre']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationMessage")));
            assert driver.findElement(By.id("confirmationMessage")).getText().toLowerCase(Locale.FRANCE).contains("votre expérience de voyage a été reçue");

        } finally {
            driver.quit();
        }
    }
}
