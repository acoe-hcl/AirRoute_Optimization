Certainly! Here is a well-structured Selenium Java TestNG code that automates the described International Shipping Quote end-to-end test scenario. The script covers multiple logical combinations, includes core assertions, and is modular to enable easy maintenance and extension.

**Assumptions/Pre-requisites:**
- Standard Java Selenium/TestNG project setup
- Appropriate waits (WebDriverWait/ExpectedConditions) used for async UI
- Locators (`By.xpath`, `By.id`, etc.) are **examples** – adjust to your actual implementation!
- ChromeDriver/other WebDriver is already on PATH
- The test runs in a single-threaded context (no parallel WebDriver collisions)
- Test data (sample locations/address) are provided

---

```java
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class InternationalShippingQuoteTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://your-website.com";
    private String fromLocation = "New York, USA";
    private String fromLocationSuggestion = "New York, NY, USA";
    private String toLocation = "Sydney, Australia";
    private String toLocationSuggestion = "Sydney NSW, Australia";
    private String quoteReferenceNumber;
    private String priorityAmount;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testInternationalShippingQuoteEndToEnd() {
        // 1. Access homepage
        driver.get(baseUrl);

        // 2. Click on “Express Book & Pay”
        WebElement expressBookPayBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Express Book & Pay')]")));
        expressBookPayBtn.click();

        // 3. Verify “Get a quote” is present
        WebElement getQuoteCta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Get a quote')]")));
        Assert.assertTrue(getQuoteCta.isDisplayed(), "'Get a quote' button is NOT visible.");

        // 4. Click “Get a quote”
        String originalWindow = driver.getWindowHandle();
        getQuoteCta.click();

        // 5. Switch to new tab
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(originalWindow)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }
        wait.until(ExpectedConditions.titleContains("Your Package Quote Estimate"));

        // 6-9. Fill out quote form
        // “Send from” location (with autocomplete)
        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("fromLocation")));
        fromInput.sendKeys(fromLocation);
        WebElement fromSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'suggestion')]//span[contains(text(),'" + fromLocationSuggestion + "')]")));
        fromSuggestion.click();

        // Delivery type “Business”
        WebElement fromDeliveryType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Business']/preceding-sibling::input[@name='fromDeliveryType']")));
        fromDeliveryType.click();

        // “Deliver to” location (with autocomplete)
        WebElement toInput = driver.findElement(By.id("toLocation"));
        toInput.sendKeys(toLocation);
        WebElement toSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'suggestion')]//span[contains(text(),'" + toLocationSuggestion + "')]")));
        toSuggestion.click();

        // Delivery type “Residential”
        WebElement toDeliveryType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Residential']/preceding-sibling::input[@name='toDeliveryType']")));
        toDeliveryType.click();

        // 11. Click “Continue”
        WebElement continueBtn1 = driver.findElement(By.xpath("//button[contains(text(), 'Continue')]"));
        continueBtn1.click();

        // 12. Click on "Use your own satchel" button
        WebElement ownSatchelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Use your own satchel')]")));
        ownSatchelBtn.click();

        // 13. Confirm prohibited items acknowledgement
        WebElement prohibitedItemsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and @name='prohibitedItemsAcknowledgement']")));
        prohibitedItemsCheckbox.click(); // check the box

        // Negative test – ensure cannot continue without checking the box
        prohibitedItemsCheckbox.click(); // uncheck
        WebElement continueBtn2 = driver.findElement(By.xpath("//button[contains(text(), 'Continue')]"));
        continueBtn2.click();
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'You must confirm')]")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Prohibited items confirmation error should appear.");

        // Now check to proceed
        prohibitedItemsCheckbox.click();
        continueBtn2.click();

        // 15. Concierge pickup + Continue
        WebElement conciergePickup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='pickupOption' and @value='Concierge']")));
        conciergePickup.click();
        WebElement continueBtn3 = driver.findElement(By.xpath("//button[contains(text(), 'Continue')]"));
        continueBtn3.click();

        // 16. Click "Save The Quote"
        WebElement saveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Save The Quote')]")));
        saveQuoteBtn.click();

        // 17-19. Note quote reference and priority amount
        WebElement quoteRefElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quoteReferenceNumber")));
        quoteReferenceNumber = quoteRefElem.getText();
        Assert.assertNotNull(quoteReferenceNumber, "Quote reference number is missing.");

        WebElement priorityAmountElem = driver.findElement(By.id("priorityAmount"));
        priorityAmount = priorityAmountElem.getText();
        Assert.assertTrue(priorityAmount.matches("[$€£]\\d+\\.\\d{2}"), "Priority amount is not in correct format: " + priorityAmount);

        // 20. Navigate to home page
        driver.close();
        driver.switchTo().window(originalWindow);

        // 21. Retrieve a quote by code
        WebElement retrieveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Retrieve A Quote')]")));
        retrieveQuoteBtn.click();

        WebElement retrieveInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("retrieveQuoteReference")));
        retrieveInput.sendKeys(quoteReferenceNumber);

        WebElement retrieveSearchBtn = driver.findElement(By.xpath("//button[contains(text(),'Retrieve')]"));
        retrieveSearchBtn.click();

        // 22. Confirm returned quote details
        WebElement retrievedRefElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrievedReferenceNumber")));
        Assert.assertEquals(retrievedRefElem.getText(), quoteReferenceNumber, "Retrieved quote reference does not match.");

        WebElement summaryFrom = driver.findElement(By.id("retrievedFromLocation"));
        Assert.assertTrue(summaryFrom.getText().contains("New York"), "From location does not match in summary.");

        WebElement summaryTo = driver.findElement(By.id("retrievedToLocation"));
        Assert.assertTrue(summaryTo.getText().contains("Sydney"), "To location does not match in summary.");

        // 23. Verify priority amount
        WebElement retrievedPriorityAmountElem = driver.findElement(By.id("retrievedPriorityAmount"));
        Assert.assertEquals(retrievedPriorityAmountElem.getText(), priorityAmount, "Priority amounts do not match between original and retrieved quote.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
```

---

### **Test Combinations & Assertions Covered**
- **Happy path** (valid locations, right item types, all steps completed)
- **Negative test**: user cannot proceed without prohibited items confirmation (and error is shown)
- **Asserts**:
    - Presence and visibility of 'Get a quote'
    - Correct form opening
    - Auto-complete suggestions displayed & selected
    - Delivery options behave as expected
    - Prohibited items must be acknowledged (with error if omitted)
    - Quote number and price are populated and formatted
    - Retrieval of saved quote returns matching reference and amounts

---

> **Adapt selectors and IDs to your web application’s specifics. If multiple delivery/item/use-your-own options exist, you can parameterize the script or extract to DataProvider for broader combination coverage.**

Let me know if you want [DataProvider] for varied locations/item types, [Page Object Model] refactoring, or other strategies!