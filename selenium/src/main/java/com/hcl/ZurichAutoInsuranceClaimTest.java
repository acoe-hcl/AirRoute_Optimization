import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class ZurichAutoInsuranceClaimTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "claimFields")
    public Object[][] claimFields() {
        return new Object[][]{
                {"", "John Doe", "Claimant", "test@invalid", false}, // Empty "Has someone been seriously or fatally injured?", Invalid email
                {"No", "", "", "test@email.com", false}, // Empty name/relationship
                {"Yes", "Jane Smith", "Daughter", "jane.smith@domain.com", true} // All valid
        };
    }

    @Test(priority = 1)
    public void portalAccessAndClaimCardTest() {
        driver.get("https://claims.zurich.com/");
        WebElement claimCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zurich-claim-card")));
        Assert.assertTrue(claimCard.isDisplayed(), "Zurich claim card is not visible.");

        WebElement instructions = driver.findElement(By.id("claim-instructions"));
        Assert.assertTrue(instructions.isDisplayed(), "Claim instructions not visible.");
    }

    @Test(priority = 2)
    public void initiateClaimTypeSelectionTest() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("report-claim-btn"))).click();
        WebElement claimIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("report-claim-icon")));
        claimIcon.click();

        List<WebElement> claimTypes = driver.findElements(By.cssSelector(".claim-type-option"));
        boolean autoClaimTypeExists = claimTypes.stream()
                .anyMatch(type -> type.getText().contains("Auto Insurance Claim"));
        Assert.assertTrue(autoClaimTypeExists, "'Auto Insurance Claim' type not listed.");

        // Select auto insurance claim
        claimTypes.stream().filter(type -> type.getText().contains("Auto Insurance Claim")).findFirst().get().click();
    }

    @Test(priority = 3, dataProvider = "claimFields")
    public void gettingStartedFieldValidations(String injuredAnswer, String name, String relationship, String email, boolean expectPass) {
        WebElement injuredDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("injured-question")));
        injuredDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()='" + (injuredAnswer.isEmpty() ? "Select" : injuredAnswer) + "']"))).click();

        driver.findElement(By.id("claimant-name")).clear();
        driver.findElement(By.id("claimant-name")).sendKeys(name);

        driver.findElement(By.id("claimant-relationship")).clear();
        driver.findElement(By.id("claimant-relationship")).sendKeys(relationship);

        driver.findElement(By.id("claimant-email")).clear();
        driver.findElement(By.id("claimant-email")).sendKeys(email);

        driver.findElement(By.id("next-btn")).click();

        if (!expectPass) {
            List<WebElement> errors = driver.findElements(By.cssSelector(".error-message"));
            Assert.assertTrue(errors.size() > 0, "No error messages shown for invalid/missing fields.");
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("insured-info-page")));
            Assert.assertTrue(driver.findElement(By.id("insured-info-page")).isDisplayed(), "Insured info page not displayed after valid input.");
        }
    }

    @Test(priority = 4)
    public void insuredInfoAddressAutoPopulateTest() {
        driver.findElement(By.id("company-name")).sendKeys("Zurich Test Corp");
        driver.findElement(By.id("policy-number")).sendKeys("ZUR123456789");
        WebElement addressInput = driver.findElement(By.id("company-address"));
        addressInput.sendKeys("123 N Market St");
        Actions actions = new Actions(driver);
        actions.moveToElement(addressInput).click().perform();

        driver.findElement(By.id("address-auto-lookup")).click();

        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.id("zip-code")), "value"));
        Assert.assertFalse(driver.findElement(By.id("zip-code")).getAttribute("value").isEmpty(), "Zip code not auto-populated.");
        Assert.assertFalse(driver.findElement(By.id("city")).getAttribute("value").isEmpty(), "City not auto-populated.");
        Assert.assertEquals(driver.findElement(By.id("country")).getAttribute("value"), "US", "Country should be auto-set as US.");

        driver.findElement(By.id("next-btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accident-overview-page")));
    }

    @Test(priority = 5)
    public void accidentOverviewPageTest() {
        driver.findElement(By.id("accident-date")).sendKeys("04/03/2024");
        driver.findElement(By.id("accident-location-city")).sendKeys("Atlanta");
        driver.findElement(By.id("accident-detail")).sendKeys("Insured vehicle rear-ended by other vehicle near Exit 249.");

        driver.findElement(By.id("next-btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vehicle-details-page")));
    }

    @DataProvider(name = "vehicleDetails")
    public Object[][] vehicleDetails() {
        return new Object[][]{
                {"Yes", "Yes", "driver.png", "Tom Driver", "No", "No", true},
                {"No", "No", "", "Jane Roe", "Yes", "Yes", true}
        };
    }

    @Test(priority = 6, dataProvider = "vehicleDetails")
    public void detailsByVehicleTest(String covered, String damaged, String photoPath, String driverName, String isDriverOwner, String isDriverInjured, boolean expectPass) {
        Select coveredSelect = new Select(driver.findElement(By.id("vehicle-covered")));
        coveredSelect.selectByVisibleText(covered);

        Select damagedSelect = new Select(driver.findElement(By.id("vehicle-damaged")));
        damagedSelect.selectByVisibleText(damaged);

        if (!photoPath.isEmpty()) {
            driver.findElement(By.id("damage-photo-upload")).sendKeys(new File(photoPath).getAbsolutePath());
        }

        driver.findElement(By.id("driver-name")).sendKeys(driverName);

        Select ownerSelect = new Select(driver.findElement(By.id("driver-owner")));
        ownerSelect.selectByVisibleText(isDriverOwner);

        Select injuredSelect = new Select(driver.findElement(By.id("driver-injured")));
        injuredSelect.selectByVisibleText(isDriverInjured);

        Select passengersInjuredSelect = new Select(driver.findElement(By.id("passenger-injured")));
        passengersInjuredSelect.selectByVisibleText("No");

        driver.findElement(By.id("next-btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional-info-page")));
        Assert.assertTrue(driver.findElement(By.id("additional-info-page")).isDisplayed());
    }

    @Test(priority = 7)
    public void additionalInformationTest() {
        Select pedestrianInjuredSelect = new Select(driver.findElement(By.id("pedestrian-injured")));
        pedestrianInjuredSelect.selectByVisibleText("No");
        driver.findElement(By.id("eyewitness-exists")).click();
        driver.findElement(By.id("nonpersonal-property-damage")).click();
        driver.findElement(By.id("other-details")).sendKeys("No further details.");

        driver.findElement(By.id("next-btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("documents-photos-page")));
    }

    @Test(priority = 8)
    public void documentUploadTest() {
        WebElement accidentReportUpload = driver.findElement(By.id("accident-report-upload"));
        WebElement damagePhotoUpload = driver.findElement(By.id("damage-photo-upload"));
        WebElement otherFilesUpload = driver.findElement(By.id("other-files-upload"));

        accidentReportUpload.sendKeys(new File("report.pdf").getAbsolutePath());
        damagePhotoUpload.sendKeys(new File("damages.jpg").getAbsolutePath());
        otherFilesUpload.sendKeys(new File("notes.txt").getAbsolutePath());

        List<WebElement> uploadedFiles = driver.findElements(By.cssSelector(".uploaded-file-list .uploaded-file"));
        Assert.assertTrue(uploadedFiles.size() >= 3, "Not all files uploaded successfully.");

        driver.findElement(By.id("report-claim-btn")).click();
    }

    @Test(priority = 9)
    public void finalClaimSubmissionAndConfirmationTest() {
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claim-confirmation-message")));
        Assert.assertTrue(confirmation.getText().contains("claim has been submitted successfully"), "Confirmation message not displayed.");

        // Optional: Confirmation email delivery
        // (Simulated: If 'confirmation-email-msg' present)
        List<WebElement> emailMsg = driver.findElements(By.id("confirmation-email-msg"));
        if (!emailMsg.isEmpty()) {
            Assert.assertTrue(emailMsg.get(0).isDisplayed() && emailMsg.get(0).getText().contains("email sent"), "Confirmation email not found.");
        }
    }
}
