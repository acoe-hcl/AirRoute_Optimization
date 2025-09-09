import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class InternationalShippingQuoteTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void testInternationalShippingQuoteEndToEnd() {
        // Step 1: Access homepage
        driver.get("https://shipping-service-url.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#get-quote-btn")));

        // Step 2: Click “Express Book & Pay”
        WebElement expressBookBtn = driver.findElement(By.xpath("//a[text()='Express Book & Pay']"));
        expressBookBtn.click();

        // Step 3: Verify "Get a quote" CTA presence
        WebElement getQuoteBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#get-quote-btn")));
        Assert.assertTrue(getQuoteBtn.isDisplayed(), "Get a quote button should be visible.");

        // Test Combination: Assert no multiple "Get a quote" CTA
        List<WebElement> quoteCtas = driver.findElements(By.cssSelector("button#get-quote-btn"));
        Assert.assertEquals(quoteCtas.size(), 1, "One 'Get a quote' button should be present.");

        // Step 4: Click “Get a quote”
        getQuoteBtn.click();

        // Step 5: Navigated to Quote Form (new tab)
        String homeTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(homeTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        wait.until(ExpectedConditions.titleContains("Your Package Quote Estimate – Express Book & Pay – MyTeamGE"));

        // Step 6: Locate “Send from” and “Deliver to” fields.
        WebElement fromField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("send-from-location")));
        WebElement toField = driver.findElement(By.id("deliver-to-location"));

        // Step 7: Type valid “From” location, select from auto-complete
        fromField.sendKeys("Los Angeles");
        WebElement fromAutoDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".autocomplete-dropdown")));
        List<WebElement> fromSuggestions =
                fromAutoDropdown.findElements(By.tagName("li"));
        for (WebElement suggestion : fromSuggestions) {
            if (suggestion.getText().contains("Los Angeles, CA, USA")) {
                suggestion.click();
                break;
            }
        }
        // Assert auto-complete drops down
        Assert.assertTrue(fromAutoDropdown.isDisplayed());

        // Step 8: Select delivery type “Business”
        WebElement deliveryTypeBusiness = driver.findElement(By.id("delivery-type-business"));
        deliveryTypeBusiness.click();
        Assert.assertTrue(deliveryTypeBusiness.isSelected());

        // Step 9: Type valid “To” location, select from auto-complete
        toField.sendKeys("Sydney");
        WebElement toAutoDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".autocomplete-dropdown")));
        List<WebElement> toSuggestions = toAutoDropdown.findElements(By.tagName("li"));
        for (WebElement suggestion : toSuggestions) {
            if (suggestion.getText().contains("Sydney, NSW, Australia")) {
                suggestion.click();
                break;
            }
        }
        Assert.assertTrue(toAutoDropdown.isDisplayed());

        // Step 10: Select delivery type “Residential”
        WebElement deliveryTypeResidential = driver.findElement(By.id("delivery-type-residential"));
        deliveryTypeResidential.click();
        Assert.assertTrue(deliveryTypeResidential.isSelected());

        // Step 11: Click “Continue”
        WebElement continueBtn = driver.findElement(By.cssSelector("button.continue-btn"));
        continueBtn.click();

        // Step 12: Click “Use your own satchel”
        WebElement ownSatchelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("own-satchel-btn")));
        ownSatchelBtn.click();

        // Step 13: Review prohibited items, check confirmation box
        WebElement prohibitedList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prohibited-items-list")));
        Assert.assertTrue(prohibitedList.isDisplayed());
        WebElement prohibitedConfirm = driver.findElement(By.id("prohibited-confirm"));
        prohibitedConfirm.click();
        Assert.assertTrue(prohibitedConfirm.isSelected());

        // Negative Test: Try continue without confirming prohibited items, expect error
        prohibitedConfirm.click(); // Uncheck
        continueBtn = driver.findElement(By.cssSelector("button.continue-btn"));
        continueBtn.click();
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-message")));
        Assert.assertTrue(errorMsg.getText().contains("Please confirm prohibited items acknowledgement"));
        prohibitedConfirm.click(); // Re-check for next step

        // Step 14: Click “Continue”
        continueBtn.click();

        // Step 15: Concierge pickup, click continue
        WebElement conciergePickup = wait.until(ExpectedConditions.elementToBeClickable(By.id("concierge-pickup-btn")));
        conciergePickup.click();
        driver.findElement(By.cssSelector("button.continue-btn")).click();

        // Step 16: Click “Save The Quote”
        WebElement saveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("save-quote-btn")));
        saveQuoteBtn.click();

        // Step 17: Note quote reference number
        WebElement quoteReference = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quote-reference-number")));
        String quoteRefNo = quoteReference.getText();
        Assert.assertFalse(quoteRefNo.isEmpty(), "Quote reference number should be displayed.");

        // Step 18: Copy quote reference number (using variable already)

        // Step 19: Copy Priority amount
        WebElement priorityAmount = driver.findElement(By.id("priority-amount"));
        String savedPriorityAmount = priorityAmount.getText();
        Assert.assertFalse(savedPriorityAmount.isEmpty(), "Priority amount should be displayed.");

        // Step 20: Navigate back to homepage
        driver.switchTo().window(homeTab);
        driver.navigate().refresh();

        // Step 21: Locate “Retrieve A Quote”, enter reference number
        WebElement retrieveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("retrieve-quote-btn")));
        retrieveQuoteBtn.click();
        WebElement refInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrieve-quote-input")));
        refInput.sendKeys(quoteRefNo);
        driver.findElement(By.id("submit-retrieve-quote")).click();

        // Step 22: Upon retrieval, check original quote summary
        WebElement retrievedQuoteSummary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrieved-quote-summary")));

        Assert.assertTrue(retrievedQuoteSummary.getText().contains("Los Angeles"), "Retrieved summary should contain origin.");
        Assert.assertTrue(retrievedQuoteSummary.getText().contains("Sydney"), "Retrieved summary should contain destination.");
        Assert.assertTrue(retrievedQuoteSummary.getText().contains(quoteRefNo), "Retrieved summary should show reference number.");

        // Step 23: Verify Priority amount in summary
        WebElement retrievedPriorityAmount = retrievedQuoteSummary.findElement(By.id("priority-amount"));
        Assert.assertEquals(retrievedPriorityAmount.getText(), savedPriorityAmount, "Priority amount should match.");

        // Test Combination: Try retrieval with invalid quote number, assert error
        retrieveQuoteBtn.click();
        WebElement refInputInvalid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrieve-quote-input")));
        refInputInvalid.sendKeys("INVALIDCODE123");
        driver.findElement(By.id("submit-retrieve-quote")).click();
        WebElement invalidRefMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-message")));
        Assert.assertTrue(invalidRefMsg.getText().contains("No quote found for the provided reference"));

        // Test Combination: All mandatory fields are required to proceed
        driver.switchTo().window(tabWithQuoteForm());
        fromField.clear();
        continueBtn.click();
        WebElement mandatoryErrorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-message")));
        Assert.assertTrue(mandatoryErrorMsg.getText().contains("Please enter a valid 'From' location"));

        // Test Combination: Try entering a non-supported country
        fromField.sendKeys("Atlantis");
        WebElement unsupportedDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".autocomplete-dropdown")));
        List<WebElement> unsupportedSuggestions = unsupportedDropdown.findElements(By.tagName("li"));
        Assert.assertTrue(
            unsupportedSuggestions.stream().noneMatch(el -> el.getText().contains("Atlantis")), 
            "'Atlantis' should not be selectable as destination");

    }

    private String tabWithQuoteForm() {
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
            if (driver.getTitle().contains("Express Book & Pay")) return tab;
        }
        return driver.getWindowHandle();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
