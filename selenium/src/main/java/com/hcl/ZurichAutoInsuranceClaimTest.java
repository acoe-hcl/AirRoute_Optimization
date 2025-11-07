import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class ZurichAutoInsuranceClaimTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void verifyPortalAccessAndLandingPageElements() {
        driver.get("https://zurich-claims-portal.com"); // Replace with actual portal URL

        WebElement claimCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zurich-claim-card")));
        Assert.assertTrue(claimCard.isDisplayed());

        WebElement instructions = driver.findElement(By.id("claim-instructions"));
        Assert.assertTrue(instructions.isDisplayed());

        WebElement reportClaimBtn = driver.findElement(By.id("report-claim-btn"));
        Assert.assertTrue(reportClaimBtn.isDisplayed());
        reportClaimBtn.click();
    }

    @Test(priority = 2)
    public void selectClaimTypeAndValidateOptions() {
        WebElement reportClaimIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("report-claim-icon")));
        reportClaimIcon.click();

        List<WebElement> claimTypes = driver.findElements(By.cssSelector(".claim-type-option"));
        Assert.assertTrue(claimTypes.size() > 0);

        for (WebElement claimType : claimTypes) {
            if (claimType.getText().equalsIgnoreCase("Auto Insurance Claim")) {
                claimType.click();
                break;
            }
        }
    }

    @Test(priority = 3)
    public void gettingStartedMandatoryFieldValidations() {
        // Without entering any fields, try to proceed
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("next-btn-started")));
        nextBtn.click();

        WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name-error")));
        WebElement relationshipError = driver.findElement(By.id("relationship-error"));
        WebElement emailError = driver.findElement(By.id("email-error"));

        Assert.assertTrue(nameError.isDisplayed());
        Assert.assertTrue(relationshipError.isDisplayed());
        Assert.assertTrue(emailError.isDisplayed());

        // Enter invalid email and check validation
        driver.findElement(By.id("injury-question")).click(); // Yes/No radio button

        driver.findElement(By.id("claimant-name")).sendKeys("John Doe");
        driver.findElement(By.id("claimant-relationship")).sendKeys("Insured");
        driver.findElement(By.id("claimant-email")).sendKeys("invalid-email");
        nextBtn.click();
        Assert.assertTrue(emailError.isDisplayed());

        // Enter valid details and proceed
        driver.findElement(By.id("claimant-email")).clear();
        driver.findElement(By.id("claimant-email")).sendKeys("johndoe@example.com");
        nextBtn.click();
    }

    @Test(priority = 4)
    public void insuredInformationAddressAutoPopulate() {
        driver.findElement(By.id("insured-company-name")).sendKeys("ABC Logistics Ltd");
        driver.findElement(By.id("policy-number")).sendKeys("ZUR123456");

        driver.findElement(By.id("insured-address")).sendKeys("1600 Amphitheatre Parkway");
        // Wait for auto-population
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.id("insured-zip"), "value", "")));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.id("insured-city"), "value", "")));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.id("insured-state"), "value", "")));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.id("insured-country"), "value", "")));

        Assert.assertFalse(driver.findElement(By.id("insured-zip")).getAttribute("value").isEmpty());
        Assert.assertFalse(driver.findElement(By.id("insured-city")).getAttribute("value").isEmpty());
        Assert.assertFalse(driver.findElement(By.id("insured-state")).getAttribute("value").isEmpty());
        Assert.assertFalse(driver.findElement(By.id("insured-country")).getAttribute("value").isEmpty());

        driver.findElement(By.id("next-btn-insured-info")).click();
    }

    @Test(priority = 5)
    public void accidentOverviewSection() {
        driver.findElement(By.id("accident-date")).sendKeys("06/27/2024");
        driver.findElement(By.id("accident-city")).sendKeys("Atlanta");
        driver.findElement(By.id("accident-description")).sendKeys("Insured vehicle rear-ended by other vehicle near Exit 249");
        driver.findElement(By.id("next-btn-accident")).click();
    }

    @Test(priority = 6)
    public void detailsByVehiclePhotoUploadAndValidations() {
        driver.findElement(By.id("vehicle-covered-yes")).click();
        driver.findElement(By.id("vehicle-damaged-yes")).click();

        WebElement photoUpload = driver.findElement(By.id("damage-photo-upload"));
        File photo = new File("src/test/resources/damage1.jpg");
        photoUpload.sendKeys(photo.getAbsolutePath());

        driver.findElement(By.id("driver-name")).sendKeys("Jane Smith");
        driver.findElement(By.id("driver-owner-yes")).click();
        driver.findElement(By.id("driver-injured-no")).click();
        driver.findElement(By.id("passenger-injured-no")).click();

        driver.findElement(By.id("next-btn-vehicle-details")).click();
    }

    @Test(priority = 7)
    public void addAdditionalInformationAndProceed() {
        driver.findElement(By.id("pedestrians-injured-no")).click();
        driver.findElement(By.id("eyewitnesses-yes")).click();
        driver.findElement(By.id("nonpersonal-property-damage-no")).click();
        driver.findElement(By.id("additional-details")).sendKeys("Police was at scene, witness contact shared.");

        driver.findElement(By.id("next-btn-additional-info")).click();
    }

    @Test(priority = 8)
    public void uploadDocumentsAndFinalSubmission() {
        // Multiple attachments
        WebElement accidentReportUpload = driver.findElement(By.id("accident-report-upload"));
        accidentReportUpload.sendKeys(new File("src/test/resources/accident_report.pdf").getAbsolutePath());

        WebElement vehiclePhotoUpload = driver.findElement(By.id("vehicle-photos-upload"));
        vehiclePhotoUpload.sendKeys(new File("src/test/resources/damage2.jpg").getAbsolutePath());
        vehiclePhotoUpload.sendKeys(new File("src/test/resources/damage3.jpg").getAbsolutePath());

        List<WebElement> uploadedFiles = driver.findElements(By.cssSelector(".uploaded-files-list li"));
        Assert.assertTrue(uploadedFiles.size() >= 2);

        driver.findElement(By.id("report-my-claim-btn")).click();

        WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claim-confirmation-msg")));
        Assert.assertTrue(confirmationMsg.isDisplayed());
        Assert.assertTrue(confirmationMsg.getText().contains("successfully"));

        // Optionally verify confirmation email (depends on portal, usually can't automate, but can check UI alert)
        // WebElement emailConfirmMsg = driver.findElement(By.id("claim-email-confirm-msg"));
        // Assert.assertTrue(emailConfirmMsg.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
