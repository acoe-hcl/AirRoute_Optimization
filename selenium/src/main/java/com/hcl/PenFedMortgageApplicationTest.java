import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class PenFedMortgageApplicationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testPositiveMortgageApplicationFlow() {
        // Step 1: Open PenFed.org
        driver.get("https://www.penfed.org");
        Assert.assertEquals(driver.getTitle(), "PenFed Credit Union | Checking, Savings, Mortgage, Loans", "Homepage title mismatch");

        // Step 2: Browse mortgage products
        WebElement mortgageMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//a[contains(text(),'Mortgages')]")));
        mortgageMenu.click();

        // Step 3: Identify and select "Conventional" mortgage product
        WebElement conventionalMortgage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Conventional')]")));
        conventionalMortgage.click();

        // Step 4: Click "Apply Now"
        WebElement applyNowBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Apply Now')]")));
        applyNowBtn.click();

        // Step 5: Wait for guest form
        WebElement guestForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("guestApplicationForm")));
        Assert.assertTrue(guestForm.isDisplayed(), "Guest Application Form not displayed");

        // Step 6: Enter mandatory fields
        driver.findElement(By.name("firstName")).sendKeys("John");
        driver.findElement(By.name("lastName")).sendKeys("Doe");
        driver.findElement(By.name("email")).sendKeys("john.doe@email.com");
        driver.findElement(By.name("phone")).sendKeys("123-456-7890");
        driver.findElement(By.name("street")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("Alexandria");
        driver.findElement(By.name("state")).sendKeys("VA");
        driver.findElement(By.name("zip")).sendKeys("22301");
        driver.findElement(By.name("dob")).sendKeys("01/01/1990");

        // Step 7: Select "Greater than 6 months"
        WebElement purchasePlanGt6Months = driver.findElement(By.xpath("//input[@type='radio' and @value='gt6months']"));
        purchasePlanGt6Months.click();

        // Step 8: Confirm Terms and Conditions
        WebElement termsCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("terms")));
        Assert.assertTrue(driver.findElement(By.id("termsSection")).isDisplayed(), "Terms & Conditions not displayed");
        termsCheckbox.click();

        // Step 9: Submit the application form
        driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();

        // Step 10: Lead Verification (Simulate backend)
        LeadRecord lead = BackendApi.getLeadRecordByEmail("john.doe@email.com");
        Assert.assertNotNull(lead, "Lead not created");
        Assert.assertEquals(lead.status, "New", "Lead status incorrect");
        Assert.assertEquals(lead.firstName, "John");
        Assert.assertEquals(lead.lastName, "Doe");
        Assert.assertEquals(lead.loanOfficerAssigned, BackendApi.roundRobinAssignment(), "Loan officer assignment failed");

        // Step 11: Experian API Call Verification (Simulate positive response)
        ExperianResponse experianResp = BackendApi.getExperianResponseForLead(lead.id);
        Assert.assertTrue(experianResp.isNonFraudulent(), "Experian fraud check failed");
        Assert.assertEquals(experianResp.email, "john.doe@email.com");
        Assert.assertEquals(experianResp.phone, "123-456-7890");

        // Step 12: Redirect to Origination Interface
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains("origination")), "Not redirected to origination system");

        // Step 13: Display "Create Account" option
        WebElement createAccountBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Create Account')]")));
        Assert.assertTrue(createAccountBtn.isDisplayed(), "Create Account button not displayed");

        // Step 14: Enter credentials & create account
        createAccountBtn.click();
        driver.findElement(By.name("newUsername")).sendKeys("john.doe90");
        driver.findElement(By.name("newPassword")).sendKeys("SecurePass123!");
        driver.findElement(By.xpath("//button[contains(.,'Create')]")).click();

        // Step 15: Ensure loan application screens displayed
        WebElement loanEntryScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loanEntryForm")));
        Assert.assertTrue(loanEntryScreen.isDisplayed(), "Loan application entry screen not displayed");

        // Step 16: Complete required application data
        driver.findElement(By.name("annualIncome")).sendKeys("95000");
        driver.findElement(By.name("employmentStatus")).sendKeys("Employed");
        driver.findElement(By.name("propertyValue")).sendKeys("400000");
        driver.findElement(By.xpath("//button[contains(.,'Next')]")).click();

        // Final Submission
        driver.findElement(By.xpath("//button[contains(.,'Submit Application')]")).click();

        // Step 17: Verify Thank You message
        WebElement thankYouMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Thank You')]")));
        Assert.assertTrue(thankYouMsg.isDisplayed(), "Thank You message not displayed");

        // Step 18: Confirm email received (Simulate)
        boolean emailReceived = EmailService.hasConfirmationEmail("john.doe@email.com");
        Assert.assertTrue(emailReceived, "Confirmation email not received");

        // Step 19: Origination System - API call to Encompass
        EncompassApiCall encompassCall = BackendApi.getEncompassCallForLead(lead.id);
        Assert.assertNotNull(encompassCall, "Encompass API call not triggered");
        Assert.assertEquals(encompassCall.applicationId, lead.applicationId);

        // Step 20: Lead updated with application ID
        LeadRecord updatedLead = BackendApi.getLeadRecordById(lead.id);
        Assert.assertNotNull(updatedLead.applicationId, "Lead not updated with application ID");
    }

    @Test(priority = 2)
    public void testNegativeExperianFraudMortgageApplicationFlow() {
        // Step 1-9: Same steps as positive scenario - filling form
        driver.get("https://www.penfed.org");
        WebElement mortgageMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//a[contains(text(),'Mortgages')]")));
        mortgageMenu.click();
        WebElement conventionalMortgage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Conventional')]")));
        conventionalMortgage.click();
        WebElement applyNowBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Apply Now')]")));
        applyNowBtn.click();
        WebElement guestForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("guestApplicationForm")));

        driver.findElement(By.name("firstName")).sendKeys("John");
        driver.findElement(By.name("lastName")).sendKeys("Doe");
        driver.findElement(By.name("email")).sendKeys("john.doe@email.com");
        driver.findElement(By.name("phone")).sendKeys("123-456-7890");
        driver.findElement(By.name("street")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("Alexandria");
        driver.findElement(By.name("state")).sendKeys("VA");
        driver.findElement(By.name("zip")).sendKeys("22301");
        driver.findElement(By.name("dob")).sendKeys("01/01/1990");
        driver.findElement(By.xpath("//input[@type='radio' and @value='gt6months']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("terms"))).click();

        driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();

        // Step 10: Lead Verification (Simulate backend)
        LeadRecord lead = BackendApi.getLeadRecordByEmail("john.doe@email.com");
        Assert.assertNotNull(lead);

        // Step 11: Experian API Call Verification (Simulated negative response)
        ExperianResponse experianResp = BackendApi.getExperianResponseForLeadNegative(lead.id); // negative simulation
        Assert.assertFalse(experianResp.isNonFraudulent(), "Experian fraud check should fail");

        // Step 12: Confirm error displayed, no redirection
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Your details could not be verified.')]")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Fraud error message not displayed");
        Assert.assertTrue(errorMsg.getText().contains("1-800-123-4567"));

        // Step 13: No origination nor account creation allowed
        Assert.assertTrue(driver.findElements(By.xpath("//button[contains(.,'Create Account')]")).size() == 0, "Should not allow Create Account");
        Assert.assertFalse(driver.getCurrentUrl().contains("origination"), "Should not redirect to origination");
    }
}

// Stubbed API simulator classes for backend integration checks
class BackendApi {
    static LeadRecord getLeadRecordByEmail(String email) { /*simulate*/ return new LeadRecord(email); }
    static LeadRecord getLeadRecordById(String id) { /*simulate*/ return new LeadRecord(id); }
    static String roundRobinAssignment() { return "Loan Officer A"; }
    static ExperianResponse getExperianResponseForLead(String leadId) { return new ExperianResponse(true); }
    static ExperianResponse getExperianResponseForLeadNegative(String leadId) { return new ExperianResponse(false); }
    static EncompassApiCall getEncompassCallForLead(String leadId) { return new EncompassApiCall("app123"); }
}

class LeadRecord {
    String id, firstName, lastName, email, status, loanOfficerAssigned, applicationId;
    LeadRecord(String email) { this.email = email; status = "New"; firstName = "John"; lastName = "Doe"; loanOfficerAssigned = "Loan Officer A"; applicationId = "app123"; }
    LeadRecord(String id) { this.id = id; applicationId = "app123"; }
}

class ExperianResponse {
    boolean nonFraudulent;
    String email="john.doe@email.com", phone="123-456-7890";
    ExperianResponse(boolean result) { nonFraudulent = result; }
    boolean isNonFraudulent() { return nonFraudulent; }
}

class EncompassApiCall {
    String applicationId;
    EncompassApiCall(String appId) { applicationId = appId; }
}

class EmailService {
    static boolean hasConfirmationEmail(String email) { return true; }
}
