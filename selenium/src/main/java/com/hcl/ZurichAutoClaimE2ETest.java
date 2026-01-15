import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class ZurichAutoClaimE2ETest {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://zurich-insurance-claims-portal.com"; // Sample URL

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void verifyClaimCardAndInstructionsVisible() {
        driver.get(baseUrl);
        WebElement claimCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claim-card-zurich")));
        WebElement instructions = driver.findElement(By.id("claim-instructions"));
        Assert.assertTrue(claimCard.isDisplayed(), "Claim card not displayed");
        Assert.assertTrue(instructions.isDisplayed(), "Claim instructions not displayed");
    }

    @Test(priority = 2)
    public void initiateClaimProcess_And_SelectType() {
        WebElement reportBtn = driver.findElement(By.xpath("//button[contains(text(),'Report a claim')]"));
        reportBtn.click();
        WebElement reportIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("report-claim-icon")));
        reportIcon.click();
        List<WebElement> claimTypes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".claim-type-selector")));
        Assert.assertTrue(claimTypes.size() > 0, "Claim types not listed");
        for (WebElement type : claimTypes) {
            if (type.getText().equalsIgnoreCase("Auto Insurance Claim")) {
                type.click();
                break;
            }
        }
    }

    @Test(priority = 3)
    public void verifyMandatoryFieldsAndValidation_GettingStarted() {
        // Getting Started: no input, check errors
        WebElement nextBtn = driver.findElement(By.id("next-btn-getting-started"));
        nextBtn.click();

        WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-name")));
        WebElement relationshipError = driver.findElement(By.id("error-relationship"));
        WebElement emailError = driver.findElement(By.id("error-email"));
        Assert.assertTrue(nameError.isDisplayed());
        Assert.assertTrue(relationshipError.isDisplayed());
        Assert.assertTrue(emailError.isDisplayed());

        // Enter invalid email
        driver.findElement(By.id("input-name")).sendKeys("John Doe");
        driver.findElement(By.id("input-relationship")).sendKeys("Policy Holder");
        driver.findElement(By.id("input-email")).sendKeys("invalidemail");
        nextBtn.click();
        WebElement emailFormatError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-email-format")));
        Assert.assertTrue(emailFormatError.isDisplayed());

        // Enter valid data
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("john.doe@zurich.com");
        nextBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("insured-info-page")));
    }

    @Test(priority = 4)
    public void insuredInfo_AutoPopulateAddress() {
        driver.findElement(By.id("insured-company-name")).sendKeys("Acme Inc");
        driver.findElement(By.id("policy-number")).sendKeys("XYZ123456");
        driver.findElement(By.id("company-address")).sendKeys("1600 Pennsylvania Ave NW");

        // Wait for auto-population
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("zip-code")));
        String zip = driver.findElement(By.id("zip-code")).getAttribute("value");
        String city = driver.findElement(By.id("city")).getAttribute("value");
        String state = driver.findElement(By.id("state")).getAttribute("value");
        String country = driver.findElement(By.id("country")).getAttribute("value");
        Assert.assertFalse(zip.isEmpty());
        Assert.assertFalse(city.isEmpty());
        Assert.assertFalse(state.isEmpty());
        Assert.assertFalse(country.isEmpty());

        WebElement nextBtn = driver.findElement(By.id("next-btn-insured-info"));
        nextBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accident-overview-page")));
    }

    @Test(priority = 5)
    public void accidentOverviewDetails() {
        driver.findElement(By.id("accident-date")).sendKeys("06/15/2024");
        driver.findElement(By.id("location-city")).sendKeys("Atlanta");
        driver.findElement(By.id("accident-description")).sendKeys("Insured vehicle rear-ended by other vehicle near Exit 249.");
        driver.findElement(By.id("next-btn-accident-overview")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vehicle-details-page")));
    }

    @Test(priority = 6)
    public void vehicleDetailsCoverage_andUpload() {
        driver.findElement(By.id("vehicle-covered-yes")).click();
        driver.findElement(By.id("vehicle-damaged-yes")).click();

        WebElement fileInput = driver.findElement(By.id("damage-photo-upload"));
        File damagePhoto = new File("src/test/resources/photos/damage1.jpg");
        fileInput.sendKeys(damagePhoto.getAbsolutePath());

        driver.findElement(By.id("driver-name")).sendKeys("Jane Smith");
        driver.findElement(By.id("driver-owner-yes")).click();
        driver.findElement(By.id("driver-injured-no")).click();
        driver.findElement(By.id("passenger-injured-no")).click();

        driver.findElement(By.id("next-btn-vehicle-details")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional-info-page")));
    }

    @Test(priority = 7)
    public void additionalInfoEntry() {
        driver.findElement(By.id("pedestrian-injured-no")).click();
        driver.findElement(By.id("eyewitness-yes")).click();
        driver.findElement(By.id("property-damage-no")).click();
        driver.findElement(By.id("other-details")).sendKeys("None");
        driver.findElement(By.id("next-btn-additional-info")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("documents-photos-page")));
    }

    @Test(priority = 8)
    public void uploadDocsPhotos_assertMultipleAttachment() {
        WebElement accidentReportUpload = driver.findElement(By.id("accident-report-upload"));
        WebElement vehicleDamageUpload = driver.findElement(By.id("vehicle-damage-upload"));
        WebElement otherUpload = driver.findElement(By.id("other-doc-upload"));

        accidentReportUpload.sendKeys(new File("src/test/resources/docs/accident_report.pdf").getAbsolutePath());
        vehicleDamageUpload.sendKeys(new File("src/test/resources/photos/damage2.png").getAbsolutePath());
        otherUpload.sendKeys(new File("src/test/resources/docs/extras.docx").getAbsolutePath());

        // Assert uploaded files status
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".upload-success")));
        List<WebElement> uploadSuccessIcons = driver.findElements(By.cssSelector(".upload-success"));
        Assert.assertTrue(uploadSuccessIcons.size() >= 3, "Not all files uploaded successfully");

        driver.findElement(By.id("report-my-claim-btn")).click();
    }

    @Test(priority = 9)
    public void verifyClaimSubmissionConfirmation() {
        WebElement confMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claim-confirmation-msg")));
        Assert.assertTrue(confMsg.isDisplayed(), "Confirmation message not displayed");
        Assert.assertTrue(confMsg.getText().contains("claim has been submitted"), "Incorrect confirmation text");

        // Optionally verify email confirmation (pseudo - assuming possible pop-up or toast)
        try {
            WebElement emailToast = driver.findElement(By.id("claim-email-sent-toast"));
            Assert.assertTrue(emailToast.isDisplayed(), "Confirmation email toast not displayed");
        } catch (NoSuchElementException ignored) {
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }

}
