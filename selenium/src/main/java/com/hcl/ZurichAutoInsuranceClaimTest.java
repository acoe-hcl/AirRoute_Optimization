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
    String baseUrl = "https://portal.zurich.com/claims"; // Placeholder

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test(priority=1)
    public void testClaimCardVisibilityAndNavigation() {
        driver.get(baseUrl);
        WebElement claimCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zurich-claim-card")));
        Assert.assertTrue(claimCard.isDisplayed(), "Claim card should be visible");

        WebElement instructions = driver.findElement(By.id("claim-instructions"));
        Assert.assertTrue(instructions.isDisplayed(), "Instructions should be visible");

        WebElement reportClaimBtn = driver.findElement(By.id("report-claim-button"));
        Assert.assertTrue(reportClaimBtn.isDisplayed(), "Report a claim button should be visible");
        reportClaimBtn.click();

        wait.until(ExpectedConditions.urlContains("/claims/start"));
        WebElement claimTypeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("report-claim-icon")));
        claimTypeIcon.click();

        List<WebElement> claimTypeOptions = driver.findElements(By.cssSelector(".claim-type-options"));
        Assert.assertTrue(claimTypeOptions.size() > 0, "Claim type options should be displayed");
        for (WebElement option : claimTypeOptions) {
            Assert.assertTrue(option.isDisplayed(), "Each claim type option should be visible");
        }
    }

    @Test(priority=2)
    public void testSelectAutoInsuranceClaimType() {
        WebElement autoClaimOption = driver.findElement(By.xpath("//div[contains(text(),'Auto Insurance Claim')]"));
        Assert.assertTrue(autoClaimOption.isDisplayed());
        autoClaimOption.click();
        wait.until(ExpectedConditions.urlContains("/claims/getting-started"));
    }

    @Test(priority=3)
    public void testGettingStartedValidationLogic() {
        WebElement injuryQuestion = driver.findElement(By.id("serious-injury-question"));
        Assert.assertTrue(injuryQuestion.isDisplayed());

        WebElement nameInput = driver.findElement(By.id("contact-name"));
        WebElement relationshipInput = driver.findElement(By.id("relationship-to-claim"));
        WebElement emailInput = driver.findElement(By.id("contact-email"));
        WebElement nextBtn = driver.findElement(By.id("next-button"));

        // Attempt to proceed with empty fields to trigger validation
        nextBtn.click();
        WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name-error")));
        WebElement relationshipError = driver.findElement(By.id("relationship-error"));
        WebElement emailError = driver.findElement(By.id("email-error"));

        Assert.assertEquals(nameError.getText(),"Name is required");
        Assert.assertEquals(relationshipError.getText(),"Relationship is required");
        Assert.assertEquals(emailError.getText(),"Valid email is required");

        // Invalid email format
        nameInput.sendKeys("John Doe");
        relationshipInput.sendKeys("Policy Holder");
        emailInput.sendKeys("invalid-email");
        nextBtn.click();
        WebElement invalidEmailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-error")));
        Assert.assertEquals(invalidEmailError.getText(),"Valid email is required");

        // Valid submission
        emailInput.clear();
        emailInput.sendKeys("john.doe@example.com");
        nextBtn.click();

        wait.until(ExpectedConditions.urlContains("/claims/insured-info"));
    }

    @Test(priority=4)
    public void testInsuredInformationAddressAutoPopulation() {
        WebElement companyNameInput = driver.findElement(By.id("insured-company-name"));
        WebElement policyNumberInput = driver.findElement(By.id("policy-number"));
        WebElement addressInput = driver.findElement(By.id("company-address"));
        WebElement nextBtn = driver.findElement(By.id("next-button"));

        companyNameInput.sendKeys("Acme Automotive Ltd.");
        policyNumberInput.sendKeys("ZUR1234567");
        addressInput.sendKeys("1600 Amphitheatre Pkwy");

        // Wait for auto-populated fields
        WebElement zipCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zip-code")));
        WebElement cityInput = driver.findElement(By.id("city"));
        WebElement stateInput = driver.findElement(By.id("state"));
        WebElement countryInput = driver.findElement(By.id("country"));

        Assert.assertFalse(zipCodeInput.getAttribute("value").isEmpty());
        Assert.assertFalse(cityInput.getAttribute("value").isEmpty());
        Assert.assertFalse(stateInput.getAttribute("value").isEmpty());
        Assert.assertEquals(countryInput.getAttribute("value"), "USA");

        nextBtn.click();
        wait.until(ExpectedConditions.urlContains("/claims/accident-overview"));
    }

    @Test(priority=5)
    public void testAccidentOverviewDataEntry() {
        WebElement accidentDatePicker = driver.findElement(By.id("accident-date"));
        WebElement accidentCityInput = driver.findElement(By.id("accident-city"));
        WebElement accidentDescriptionBox = driver.findElement(By.id("accident-detail"));

        accidentDatePicker.click();
        WebElement selectDate = driver.findElement(By.xpath("//td[@data-day='15']")); // Select date as 15th
        selectDate.click();

        accidentCityInput.sendKeys("Atlanta");
        accidentDescriptionBox.sendKeys("Insured vehicle rear-ended by other vehicle near Exit 249");
        driver.findElement(By.id("next-button")).click();

        wait.until(ExpectedConditions.urlContains("/claims/vehicle-details"));
    }

    @Test(priority=6)
    public void testVehicleDetailsAndPhotoUpload() {
        WebElement coveredYesRadio = driver.findElement(By.id("vehicle-covered-yes"));
        coveredYesRadio.click();

        WebElement damagedYesRadio = driver.findElement(By.id("vehicle-damaged-yes"));
        damagedYesRadio.click();

        WebElement photoUploadBox = driver.findElement(By.id("vehicle-damage-upload"));
        File photoFile = new File(System.getProperty("user.dir") + "/testdata/damage_photo.jpg");
        photoUploadBox.sendKeys(photoFile.getAbsolutePath());

        WebElement driverNameInput = driver.findElement(By.id("driver-name"));
        driverNameInput.sendKeys("Alice Doe");

        WebElement ownerYesRadio = driver.findElement(By.id("driver-is-owner-yes"));
        ownerYesRadio.click();

        WebElement driverInjuredNoRadio = driver.findElement(By.id("driver-injured-no"));
        driverInjuredNoRadio.click();

        WebElement injuredPassengersNoRadio = driver.findElement(By.id("passengers-injured-no"));
        injuredPassengersNoRadio.click();

        driver.findElement(By.id("next-button")).click();
        wait.until(ExpectedConditions.urlContains("/claims/additional-info"));
    }

    @Test(priority=7)
    public void testAdditionalInformation() {
        WebElement pedestriansInjuredNoRadio = driver.findElement(By.id("pedestrians-injured-no"));
        pedestriansInjuredNoRadio.click();

        WebElement eyewitnessesNoRadio = driver.findElement(By.id("eyewitnesses-no"));
        eyewitnessesNoRadio.click();

        WebElement propertyDamageNoRadio = driver.findElement(By.id("property-damage-no"));
        propertyDamageNoRadio.click();

        driver.findElement(By.id("next-button")).click();
        wait.until(ExpectedConditions.urlContains("/claims/documents-photos"));
    }

    @Test(priority=8)
    public void testDocumentAndPhotoUploads() {
        WebElement accidentReportUpload = driver.findElement(By.id("accident-report-upload"));
        WebElement damagePhotosUpload = driver.findElement(By.id("damage-photos-upload"));
        WebElement otherDocsUpload = driver.findElement(By.id("other-documents-upload"));

        accidentReportUpload.sendKeys(new File(System.getProperty("user.dir")+"/testdata/accident_report.pdf").getAbsolutePath());
        damagePhotosUpload.sendKeys(new File(System.getProperty("user.dir")+"/testdata/damage_photo1.jpg").getAbsolutePath());
        damagePhotosUpload.sendKeys(new File(System.getProperty("user.dir")+"/testdata/damage_photo2.jpg").getAbsolutePath());
        otherDocsUpload.sendKeys(new File(System.getProperty("user.dir")+"/testdata/other_doc.txt").getAbsolutePath());

        List<WebElement> uploads = driver.findElements(By.cssSelector(".uploaded-file"));
        Assert.assertEquals(uploads.size(), 4, "All files should be attached");

        driver.findElement(By.id("report-my-claim-button")).click();
    }

    @Test(priority=9)
    public void testSubmissionConfirmation() {
        WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claim-confirmation-message")));
        Assert.assertTrue(confirmationMsg.isDisplayed(), "Confirmation message should appear");
        Assert.assertTrue(confirmationMsg.getText().contains("Claim has been submitted successfully"));

        // Optionally, verify confirmation email if portal sends one
        // (Requires mocking or access to testing inbox)
    }

    @AfterClass
    public void cleanup() {
        driver.quit();
    }
}
