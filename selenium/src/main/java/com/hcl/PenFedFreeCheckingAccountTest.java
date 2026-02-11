import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class PenFedFreeCheckingAccountTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void openPenFedSite() {
        driver.get("https://www.penfed.org");
        Assert.assertTrue(driver.getTitle().contains("PenFed"), "PenFed homepage did not load.");
    }

    @Test(priority = 2)
    public void verifyDepositProducts() {
        WebElement checkingSavingsTab = driver.findElement(By.xpath("//nav//*[text()='Checking and Savings']"));
        checkingSavingsTab.click();
        List<WebElement> depositProducts = driver.findElements(By.xpath("//div[@id='all-deposit-products']//div[contains(@class,'product')]"));
        Assert.assertTrue(depositProducts.size() > 0, "Deposit products are not displayed.");
    }

    @Test(priority = 3)
    public void navigateToFreeCheckingProduct() {
        WebElement freeChecking = driver.findElement(By.xpath("//div[@id='all-deposit-products']//*[text()='Free Checking']"));
        freeChecking.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("free-checking"), "Not navigated to Free Checking page.");
    }

    @Test(priority = 4)
    public void openNowPromptOptions() {
        WebElement openNowBtn = driver.findElement(By.xpath("//button[contains(text(),'Open Now')]"));
        openNowBtn.click();
        WebElement memberPrompt = driver.findElement(By.xpath("//div[contains(@class,'member-prompt')]"));
        Assert.assertTrue(memberPrompt.isDisplayed(), "Member prompt not displayed.");
        WebElement memberOption = driver.findElement(By.xpath("//button[contains(text(),\"I'm a member\")]"));
        WebElement notMemberOption = driver.findElement(By.xpath("//button[contains(text(),\"I'm not a member\")]"));
        Assert.assertTrue(memberOption.isDisplayed() && notMemberOption.isDisplayed(), "Both member options not present.");
    }

    @Test(priority = 5)
    public void notMemberLandingPage() {
        WebElement notMemberOption = driver.findElement(By.xpath("//button[contains(text(),\"I'm not a member\")]"));
        notMemberOption.click();
        WebElement landingPage = driver.findElement(By.xpath("//div[contains(@class,'free-checking-landing')]"));
        Assert.assertTrue(landingPage.isDisplayed(), "Free Checking landing page not shown.");
        WebElement membershipInfo = driver.findElement(By.xpath("//div[contains(@class,'membership-info')]"));
        Assert.assertTrue(membershipInfo.isDisplayed(), "PenFed membership information missing on landing page.");
    }

    @Test(priority = 6)
    public void continueToPersonalInfoPage() {
        WebElement continueBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueBtn.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("personal-information"), "Not navigated to Personal Information page.");
    }

    @Test(priority = 7)
    public void validatePersonalInfoFieldsAndErrors() {
        WebElement firstName = driver.findElement(By.xpath("//input[@name='firstName']"));
        WebElement lastName = driver.findElement(By.xpath("//input[@name='lastName']"));
        WebElement email = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement mobile = driver.findElement(By.xpath("//input[@name='mobile']"));
        WebElement consentCheckbox = driver.findElement(By.xpath("//input[@type='checkbox' and @name='consent']"));
        Assert.assertTrue(firstName.isDisplayed(), "First Name field missing.");
        Assert.assertTrue(lastName.isDisplayed(), "Last Name field missing.");
        Assert.assertTrue(email.isDisplayed(), "Email field missing.");
        Assert.assertTrue(mobile.isDisplayed(), "Mobile Number field missing.");
        Assert.assertTrue(consentCheckbox.isDisplayed(), "Consent checkbox missing.");

        // Empty fields validation
        WebElement submitBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        submitBtn.click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'required')]")).isDisplayed(), "Required field error not triggered.");

        // Invalid values, spaces, special chars
        firstName.sendKeys("!@#");
        lastName.sendKeys(" ");
        email.sendKeys("invalidemail");
        mobile.sendKeys("abc123");
        submitBtn.click();
        List<WebElement> errors = driver.findElements(By.xpath("//span[contains(@class,'error-message')]"));
        Assert.assertTrue(errors.size() > 0, "Invalid input error messages not triggered.");
    }

    @Test(priority = 8)
    public void validPersonalInformationSubmission() {
        WebElement firstName = driver.findElement(By.xpath("//input[@name='firstName']"));
        WebElement lastName = driver.findElement(By.xpath("//input[@name='lastName']"));
        WebElement email = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement mobile = driver.findElement(By.xpath("//input[@name='mobile']"));
        WebElement consentCheckbox = driver.findElement(By.xpath("//input[@type='checkbox' and @name='consent']"));
        firstName.clear();
        lastName.clear();
        email.clear();
        mobile.clear();
        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        email.sendKeys("john.doe@email.com");
        mobile.sendKeys("7035551234");
        consentCheckbox.click();
        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("primary-owner-long-form"), "Not navigated to Primary Owner Long Form page.");
    }

    @Test(priority = 9)
    public void fillPrimaryOwnerLongForm() {
        // Verify fields
        WebElement address = driver.findElement(By.xpath("//input[@name='address']"));
        WebElement city = driver.findElement(By.xpath("//input[@name='city']"));
        WebElement state = driver.findElement(By.xpath("//select[@name='state']"));
        WebElement zip = driver.findElement(By.xpath("//input[@name='zip']"));
        WebElement ssn = driver.findElement(By.xpath("//input[@name='ssn']"));
        WebElement employer = driver.findElement(By.xpath("//input[@name='employer']"));
        WebElement addtlInfo = driver.findElement(By.xpath("//textarea[@name='additionalInfo']"));
        Assert.assertTrue(address.isDisplayed(), "Address field missing.");
        Assert.assertTrue(city.isDisplayed(), "City field missing.");
        Assert.assertTrue(state.isDisplayed(), "State dropdown missing.");
        Assert.assertTrue(zip.isDisplayed(), "ZIP field missing.");
        Assert.assertTrue(ssn.isDisplayed(), "SSN field missing.");
        Assert.assertTrue(employer.isDisplayed(), "Employer field missing.");
        Assert.assertTrue(addtlInfo.isDisplayed(), "Additional Information section missing.");

        // Enter valid details
        address.sendKeys("123 Main St");
        city.sendKeys("Alexandria");
        state.sendKeys("VA");
        zip.sendKeys("22314");
        ssn.sendKeys("123-45-6789");
        employer.sendKeys("IT Solutions LLC");
        addtlInfo.sendKeys("N/A");
        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("application-review"), "Not navigated to Application Review page.");
    }

    @Test(priority = 10)
    public void reviewApplicationDetails() {
        List<WebElement> reviewFields = driver.findElements(By.xpath("//div[@class='review-details']//div[contains(@class,'field')]"));
        Assert.assertTrue(reviewFields.size() > 0, "Review details not present.");
        // (Individual fields check: sample for First Name)
        String reviewFirstName = driver.findElement(By.xpath("//span[@class='review-first-name']")).getText();
        Assert.assertEquals(reviewFirstName, "John", "First Name review incorrect.");
        driver.findElement(By.xpath("//input[@name='jointOwner'][@value='no']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("disclosure"), "Not navigated to Disclosure page.");
    }

    @Test(priority = 11)
    public void validateDisclosureLinksAndCheckboxes() {
        List<WebElement> disclosureLinks = driver.findElements(By.xpath("//a[contains(@class,'disclosure-link')]"));
        for (WebElement link : disclosureLinks) {
            String originalWindow = driver.getWindowHandle();
            link.click();
            // handle modal or new window
            if (driver.getWindowHandles().size() > 1) {
                for (String winHandle : driver.getWindowHandles()) {
                    if (!winHandle.equals(originalWindow)) {
                        driver.switchTo().window(winHandle);
                        Assert.assertTrue(driver.getTitle().length() > 0, "Disclosure link did not load new content.");
                        driver.close();
                        driver.switchTo().window(originalWindow);
                    }
                }
            } else {
                WebElement modal = driver.findElement(By.xpath("//div[contains(@class,'modal')]"));
                Assert.assertTrue(modal.isDisplayed(), "Disclosure modal not displayed.");
                driver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
            }
        }
        List<WebElement> requiredCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox' and @required]"));
        for (WebElement checkbox : requiredCheckboxes) {
            if (!checkbox.isSelected()) checkbox.click();
        }
        driver.findElement(By.xpath("//button[contains(text(),'Submit Application')]")).click();
    }

    @Test(priority = 12)
    public void verifySubmissionToFICO() {
        WebElement confirmation = driver.findElement(By.xpath("//div[contains(text(),'Application submitted successfully')]"));
        Assert.assertTrue(confirmation.isDisplayed(), "Application not submitted to FICO.");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
