
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class IntlShippingQuoteTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Sample data – adjust as per test environment
    private final String BASE_URL = "https://your-shipping-site.com";
    private final String FROM_LOCATION = "Sydney, Australia";
    private final String TO_LOCATION = "London, United Kingdom";
    private final String QUOTE_PAGE_TITLE = "Your Package Quote Estimate – Express Book & Pay – MyTeamGE";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testEndToEndInternationalQuote() {
        // 1. Access homepage
        driver.get(BASE_URL);

        // 2. Click “Express Book & Pay”
        WebElement expressBookBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Express Book & Pay')]")));
        expressBookBtn.click();

        // 3. Verify “Get a quote” CTA present
        WebElement getQuoteCta = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(.,'Get a quote')]")));
        Assert.assertTrue(getQuoteCta.isDisplayed(), "Get a quote CTA not visible on page!");

        // 4. Click “Get a quote” to start process
        getQuoteCta.click();

        // 5. Wait for new tab and switch
        String originalWin = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Assert.assertTrue(driver.getTitle().contains(QUOTE_PAGE_TITLE), "Quote page did not open in new tab!");

        // 6. Locate From/To fields
        WebElement sendFromField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fromLocation")));
        WebElement deliverToField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toLocation")));

        // 7. Type FROM location, select from autocomplete
        sendFromField.sendKeys(FROM_LOCATION.substring(0, 4));
        By autoDropdown = By.cssSelector(".autocomplete-suggestion");
        wait.until(ExpectedConditions.visibilityOfElementLocated(autoDropdown));
        List<WebElement> fromOptions = driver.findElements(autoDropdown);
        boolean fromFound = fromOptions.stream().anyMatch(option -> option.getText().contains(FROM_LOCATION));
        Assert.assertTrue(fromFound, "From-location autocomplete did not produce expected suggestion");
        fromOptions.stream().filter(option -> option.getText().contains(FROM_LOCATION)).findFirst().get().click();

        // 8. Select delivery type “Business”
        WebElement businessRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'Business')]/preceding-sibling::input[@type='radio']")));
        businessRadio.click();

        // 9. Type TO location, select with autocomplete
        deliverToField.sendKeys(TO_LOCATION.substring(0, 4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(autoDropdown));
        List<WebElement> toOptions = driver.findElements(autoDropdown);
        boolean toFound = toOptions.stream().anyMatch(option -> option.getText().contains(TO_LOCATION));
        Assert.assertTrue(toFound, "To-location autocomplete did not produce expected suggestion");
        toOptions.stream().filter(option -> option.getText().contains(TO_LOCATION)).findFirst().get().click();

        // 10. Select delivery type “Residential”
        WebElement resRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'Residential')]/preceding-sibling::input[@type='radio']")));
        resRadio.click();

        // 11. Click “Continue”
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Continue')]")));
        continueBtn.click();

        // 12. Click “Use your own satchel”
        WebElement ownSatchelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Use your own satchel')]")));
        ownSatchelBtn.click();

        // 13. View prohibited list & check confirmation box
        WebElement prohibitedList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".prohibited-items-list")));
        Assert.assertTrue(prohibitedList.isDisplayed(), "Prohibited items list not displayed!");
        WebElement confirmChk = wait.until(ExpectedConditions.elementToBeClickable(By.id("prohibited-confirmation")));
        
        // ========== Negative path – omit checking, expect error ==========
        continueBtn = driver.findElement(By.xpath("//button[contains(.,'Continue')]"));
        continueBtn.click();
        WebElement prohibitedError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error-message")));
        Assert.assertTrue(prohibitedError.getText().contains("must confirm"), "Error message missing for prohibited items check");
        
        // Now check and proceed
        confirmChk.click();

        // 14. Continue
        continueBtn.click();

        // 15. Concierge pickup + continue
        WebElement conciergeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'Concierge pickup')]/preceding-sibling::input")));
        conciergeOption.click();
        continueBtn = driver.findElement(By.xpath("//button[contains(.,'Continue')]"));
        continueBtn.click();

        // 16. Click “Save The Quote”
        WebElement saveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Save The Quote')]")));
        saveQuoteBtn.click();

        // 17. Display and verify quote reference number
        WebElement referenceElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quote-reference")));
        String quoteRef = referenceElem.getText();
        Assert.assertTrue(quoteRef.matches("[A-Z0-9]{6,}"), "Reference number format appears invalid: " + quoteRef);

        // 18. Copy reference number (store to variable - as above)
        // 19. Copy Priority amount
        WebElement priorityAmtElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".priority-price")));
        String priorityAmt = priorityAmtElem.getText();

        // 20. Navigate back to homepage
        driver.close(); // Close quote tab
        driver.switchTo().window(originalWin);
        driver.navigate().refresh();

        // 21. Retrieve quote by reference number
        WebElement retrieveQuoteLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Retrieve A Quote')]")));
        retrieveQuoteLink.click();

        // Wait for dialog/input to appear
        WebElement refInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrieve-quote-reference")));
        refInput.sendKeys(quoteRef);
        WebElement retrieveBtn = driver.findElement(By.xpath("//button[contains(.,'Retrieve')]"));
        retrieveBtn.click();

        // 22. Assert quote summary matches
        WebElement summaryRef = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quote-summary .reference")));
        Assert.assertEquals(summaryRef.getText(), quoteRef, "Retrieved reference does not match!");

        // 23. Verify the copied priority amount in quote summary
        WebElement summaryPriorityAmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quote-summary .priority-price")));
        Assert.assertEquals(summaryPriorityAmt.getText(), priorityAmt, "Priority amount in summary does not match!");

        // Additional assertions for locations if available
        WebElement summaryFrom = driver.findElement(By.cssSelector(".quote-summary .from-location"));
        WebElement summaryTo = driver.findElement(By.cssSelector(".quote-summary .to-location"));
        Assert.assertTrue(summaryFrom.getText().contains(FROM_LOCATION), "FROM location does not match in summary!");
        Assert.assertTrue(summaryTo.getText().contains(TO_LOCATION), "TO location does not match in summary!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
```
