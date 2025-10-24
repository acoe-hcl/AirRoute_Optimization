import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;

public class InternationalShippingQuoteE2ETest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void endToEndInternationalShippingQuoteTest() {
        // Step 1: Access homepage
        driver.get("https://www.example-shipping-website.com"); // Replace with actual URL
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("expressBookPayBtn")));

        // Step 2: Click "Express Book & Pay"
        driver.findElement(By.id("expressBookPayBtn")).click();

        // Step 3: Verify “Get a quote” CTA is present
        By getAQuoteBtn = By.xpath("//button[contains(.,'Get a quote')]");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(getAQuoteBtn)).isDisplayed());

        // Step 4: Click “Get a quote”
        String mainTab = driver.getWindowHandle();
        driver.findElement(getAQuoteBtn).click();

        // Step 5: Switch to new quote tab
        waitForNewTabAndSwitch();

        // Step 6-9: Fill “Send from” with auto-complete, select Business, fill “Deliver to” with auto-complete, select Residential
        // “Send from” location
        WebElement fromInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fromLocation")));
        fromInput.sendKeys("Sydney");
        By autocompleteSuggestion = By.xpath("//div[contains(@class,'autocomplete')]/div[contains(.,'Sydney, Australia')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(autocompleteSuggestion)).click();

        // Select "Business"
        By businessRadio = By.xpath("//input[@name='fromType' and @value='business']/following-sibling::label");
        wait.until(ExpectedConditions.elementToBeClickable(businessRadio)).click();

        // “Deliver to” location
        WebElement toInput = driver.findElement(By.id("toLocation"));
        toInput.sendKeys("Los Angeles");
        By toAutocompleteSuggestion = By.xpath("//div[contains(@class,'autocomplete')]/div[contains(.,'Los Angeles, USA')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(toAutocompleteSuggestion)).click();

        // Select "Residential"
        By residentialRadio = By.xpath("//input[@name='toType' and @value='residential']/following-sibling::label");
        wait.until(ExpectedConditions.elementToBeClickable(residentialRadio)).click();

        // Step 11: Click "Continue"
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();

        // Step 12: Click on “Use your own satchel” button
        By ownSatchelBtn = By.xpath("//button[contains(.,'Use your own satchel')]");
        wait.until(ExpectedConditions.elementToBeClickable(ownSatchelBtn)).click();

        // Step 13: Review prohibited items and tick confirmation
        By prohibitedCheckbox = By.xpath("//input[@type='checkbox' and @name='prohibitedItems']");
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(prohibitedCheckbox));
        if (!checkbox.isSelected()) checkbox.click();

        // Step 14: Click Continue
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();

        // Step 15: Click Concierge pickup and Continue
        By conciergePickup = By.xpath("//input[@name='pickupType' and @value='concierge']/following-sibling::label");
        wait.until(ExpectedConditions.elementToBeClickable(conciergePickup)).click();
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();

        // Step 16: Click "Save The Quote"
        By saveQuoteBtn = By.xpath("//button[contains(.,'Save The Quote')]");
        wait.until(ExpectedConditions.elementToBeClickable(saveQuoteBtn)).click();

        // Step 17: Note displayed quote reference number
        By quoteRef = By.xpath("//div[@id='quoteReferenceNumber']");
        String quoteNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(quoteRef)).getText().trim();
        Assert.assertFalse(quoteNumber.isEmpty());

        // Step 18: Copy quote reference number (already stored)

        // Step 19: Copy Priority amount
        By priorityAmount = By.xpath("//span[@id='priorityAmount']");
        String originalPriorityAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(priorityAmount)).getText().trim();
        Assert.assertTrue(originalPriorityAmount.matches("[$€£]\\d+[.,]?\\d*"));

        // Step 20: Navigate to home
        driver.close();
        driver.switchTo().window(mainTab);
        driver.navigate().refresh();

        // Step 21: Retrieve a quote by reference number
        By retrieveQuoteSection = By.id("retrieveQuoteSection");
        wait.until(ExpectedConditions.visibilityOfElementLocated(retrieveQuoteSection));
        driver.findElement(By.id("retrieveQuoteInput")).sendKeys(quoteNumber);
        driver.findElement(By.id("retrieveQuoteBtn")).click();

        // Step 22: Confirm system displays the original quote summary/details
        By retrievedQuoteSummary = By.id("quoteSummarySection");
        wait.until(ExpectedConditions.visibilityOfElementLocated(retrievedQuoteSummary));
        String retrievedQuoteNumber = driver.findElement(By.id("retrievedQuoteReference")).getText().trim();
        Assert.assertEquals(retrievedQuoteNumber, quoteNumber);

        // Step 23: Verify the priority amount in the quote summary matches original
        String retrievedPriorityAmount = driver.findElement(By.id("retrievedPriorityAmount")).getText().trim();
        Assert.assertEquals(retrievedPriorityAmount, originalPriorityAmount);

        // Additional validations per Acceptance Criteria
        Assert.assertTrue(driver.findElement(By.id("fromLocationSummary")).getText().contains("Sydney"));
        Assert.assertTrue(driver.findElement(By.id("toLocationSummary")).getText().contains("Los Angeles"));
        Assert.assertTrue(driver.findElement(By.id("deliveryTypeSummary")).getText().contains("Business"));
        Assert.assertTrue(driver.findElement(By.id("deliveryTypeSummary")).getText().contains("Residential"));
        Assert.assertFalse(driver.findElement(By.id("quoteSummarySection")).getText().isEmpty());
    }

    @Test
    public void testProhibitedItemConfirmationValidation() {
        // Start from Get a Quote process up to prohibited items check
        driver.get("https://www.example-shipping-website.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("expressBookPayBtn"))).click();

        By getAQuoteBtn = By.xpath("//button[contains(.,'Get a quote')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(getAQuoteBtn)).click();
        waitForNewTabAndSwitch();

        WebElement fromInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fromLocation")));
        fromInput.sendKeys("Sydney");
        By autocompleteSuggestion = By.xpath("//div[contains(@class,'autocomplete')]/div[contains(.,'Sydney, Australia')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(autocompleteSuggestion)).click();

        By businessRadio = By.xpath("//input[@name='fromType' and @value='business']/following-sibling::label");
        wait.until(ExpectedConditions.elementToBeClickable(businessRadio)).click();

        WebElement toInput = driver.findElement(By.id("toLocation"));
        toInput.sendKeys("Los Angeles");
        By toAutocompleteSuggestion = By.xpath("//div[contains(@class,'autocomplete')]/div[contains(.,'Los Angeles, USA')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(toAutocompleteSuggestion)).click();

        By residentialRadio = By.xpath("//input[@name='toType' and @value='residential']/following-sibling::label");
        wait.until(ExpectedConditions.elementToBeClickable(residentialRadio)).click();

        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
        By ownSatchelBtn = By.xpath("//button[contains(.,'Use your own satchel')]");
        wait.until(ExpectedConditions.elementToBeClickable(ownSatchelBtn)).click();

        // Do NOT tick prohibited checkbox, try to continue
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();

        By errorMsg = By.id("prohibitedConfirmationError");
        String err = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
        Assert.assertTrue(err.contains("confirmation") || err.contains("required"));

        driver.close();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            break;
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private void waitForNewTabAndSwitch() {
        String originalWindow = driver.getWindowHandle();
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(originalWindow)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }
    }
}
