import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class InternationalShippingQuoteTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://your-shipping-service.com"; // Replace with actual URL
    String fromLocation = "Sydney, Australia";
    String toLocation = "Paris, France";
    String quoteReferenceNumber;
    String priorityAmount;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver"); // Update path
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void testInternationalShippingQuoteProcess() {
        // 1. Access homepage
        driver.get(baseUrl);

        // 2. Click "Express Book & Pay"
        WebElement expressBookPayBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("expressBookPayBtn")));
        expressBookPayBtn.click();

        // 3. Verify "Get a quote" CTA is present
        WebElement getQuoteCTA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("getQuoteBtn")));
        Assert.assertTrue(getQuoteCTA.isDisplayed(), "\"Get a quote\" CTA not displayed.");

        // 4. Click the "Get a quote" button
        String originalWindow = driver.getWindowHandle();
        getQuoteCTA.click();

        // 5. Ensure new tab and navigate
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        Assert.assertTrue(driver.getTitle().contains("Your Package Quote Estimate"));

        // 6. Verify "Send from" and "Deliver to" fields exist
        WebElement sendFromField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sendFromField")));
        WebElement deliverToField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deliverToField")));
        Assert.assertTrue(sendFromField.isDisplayed());
        Assert.assertTrue(deliverToField.isDisplayed());

        // 7. Type "From" location and select via auto-complete
        sendFromField.sendKeys(fromLocation.substring(0, 5));
        By autoCompleteFromList = By.cssSelector(".autocomplete-from-list li");
        wait.until(ExpectedConditions.visibilityOfElementLocated(autoCompleteFromList));
        List<WebElement> fromOptions = driver.findElements(autoCompleteFromList);
        Assert.assertTrue(fromOptions.size() > 0, "No auto-complete options found for 'From' location.");
        for (WebElement option : fromOptions) {
            if (option.getText().contains("Sydney")) {
                option.click();
                break;
            }
        }

        // 8. Select "Business" delivery type for From
        WebElement fromBusinessRadio = wait.until(ExpectedConditions.elementToBeClickable(By.id("fromBusinessRadio")));
        fromBusinessRadio.click();
        Assert.assertTrue(fromBusinessRadio.isSelected(), "Business radio not selectable for From.");

        // 9. Type "To" location and select via auto-complete
        deliverToField.sendKeys(toLocation.substring(0, 5));
        By autoCompleteToList = By.cssSelector(".autocomplete-to-list li");
        wait.until(ExpectedConditions.visibilityOfElementLocated(autoCompleteToList));
        List<WebElement> toOptions = driver.findElements(autoCompleteToList);
        Assert.assertTrue(toOptions.size() > 0, "No auto-complete options found for 'To' location.");
        for (WebElement option : toOptions) {
            if (option.getText().contains("Paris")) {
                option.click();
                break;
            }
        }

        // 10. Select "Residential" delivery type for To
        WebElement toResidentialRadio = wait.until(ExpectedConditions.elementToBeClickable(By.id("toResidentialRadio")));
        toResidentialRadio.click();
        Assert.assertTrue(toResidentialRadio.isSelected(), "Residential radio not selectable for To.");

        // 11. Click "Continue" button
        WebElement continueBtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("continueBtn1")));
        continueBtn1.click();

        // 12. Click "Use your own satchel"
        WebElement ownSatchelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("ownSatchelBtn")));
        ownSatchelBtn.click();

        // 13. Review prohibited items, check confirmation box
        WebElement prohibitedList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prohibitedItemsList")));
        Assert.assertTrue(prohibitedList.isDisplayed(), "Prohibited items list not displayed.");
        WebElement confirmationBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("prohibitedItemsConfirmChk")));
        confirmationBox.click();
        Assert.assertTrue(confirmationBox.isSelected(), "Confirmation checkbox not selected.");

        // 14. Click "Continue" button (after prohibited confirmation)
        WebElement continueBtn2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("continueBtn2")));
        continueBtn2.click();

        // 15. Click "Concierge pickup" and continue
        WebElement conciergePickupRadio = wait.until(ExpectedConditions.elementToBeClickable(By.id("conciergePickupRadio")));
        conciergePickupRadio.click();
        Assert.assertTrue(conciergePickupRadio.isSelected());
        WebElement continueBtn3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("continueBtn3")));
        continueBtn3.click();

        // 16. Click "Save The Quote"
        WebElement saveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveQuoteBtn")));
        saveQuoteBtn.click();

        // 17. Review displayed quote reference number
        WebElement quoteRefElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quoteReferenceNumber")));
        quoteReferenceNumber = quoteRefElem.getText();
        Assert.assertTrue(quoteReferenceNumber.matches("\\w+"), "Quote reference number format invalid.");

        // 18. Copy quote reference number (already saved)
        // 19. Copy Priority amount
        WebElement priorityAmountElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("priorityAmount")));
        priorityAmount = priorityAmountElem.getText();
        Assert.assertTrue(priorityAmount.matches("\\$?\\d+(\\.\\d{2})?"), "Priority amount format invalid.");

        // 20. Navigate to home page
        driver.close(); // Close quote tab
        driver.switchTo().window(originalWindow);
        driver.navigate().refresh();

        // 21. Locate "Retrieve A Quote" and enter reference number
        WebElement retrieveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("retrieveQuoteBtn")));
        retrieveQuoteBtn.click();
        WebElement quoteRefInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quoteRefInput")));
        quoteRefInput.sendKeys(quoteReferenceNumber);
        WebElement retrieveConfirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("retrieveConfirmBtn")));
        retrieveConfirmBtn.click();

        // 22. Confirm retrieved quote displays same details
        WebElement retrievedSummary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrievedQuoteSummary")));
        WebElement retrievedFrom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrievedFromLocation")));
        WebElement retrievedTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrievedToLocation")));
        WebElement retrievedRef = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrievedQuoteReference")));
        WebElement retrievedPriorityAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrievedPriorityAmount")));

        Assert.assertTrue(retrievedSummary.isDisplayed(), "Retrieved quote summary not displayed.");
        Assert.assertEquals(retrievedFrom.getText(), "Sydney, Australia", "Retrieved 'From' location does not match.");
        Assert.assertEquals(retrievedTo.getText(), "Paris, France", "Retrieved 'To' location does not match.");
        Assert.assertEquals(retrievedRef.getText(), quoteReferenceNumber, "Quote reference mismatch.");
        Assert.assertEquals(retrievedPriorityAmount.getText(), priorityAmount, "Priority amount mismatch in retrieved quote.");
    }

    @Test(priority = 2)
    public void testProhibitedItemsCheckboxValidation() {
        driver.get(baseUrl);
        WebElement getQuoteCTA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("getQuoteBtn")));
        getQuoteCTA.click();

        // Switch to new tab
        String window = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(window);

        // Fill locations and continue (similar steps as before)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sendFromField"))).sendKeys(fromLocation.substring(0, 5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".autocomplete-from-list li"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("fromBusinessRadio"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("deliverToField"))).sendKeys(toLocation.substring(0, 5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".autocomplete-to-list li"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("toResidentialRadio"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("continueBtn1"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ownSatchelBtn"))).click();

        // DO NOT check prohibited items confirmation
        WebElement continueBtn2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("continueBtn2")));
        continueBtn2.click();

        // Verify error message for not checking
        WebElement errorProhibited = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prohibitedItemsError")));
        Assert.assertTrue(errorProhibited.isDisplayed(), "Error for unconfirmed prohibited items not displayed.");
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
    }

    @Test(priority = 3)
    public void testAutoCompleteLocationField() {
        driver.get(baseUrl);
        WebElement getQuoteCTA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("getQuoteBtn")));
        getQuoteCTA.click();

        // Switch to new tab
        String window = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(window);

        WebElement sendFromField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sendFromField")));
        sendFromField.sendKeys("Syd");
        By autoCompleteFromList = By.cssSelector(".autocomplete-from-list li");
        wait.until(ExpectedConditions.visibilityOfElementLocated(autoCompleteFromList));
        List<WebElement> options = driver.findElements(autoCompleteFromList);

        boolean sydneyFound = false;
        for (WebElement option : options) {
            if (option.getText().contains("Sydney")) {
                sydneyFound = true;
                break;
            }
        }
        Assert.assertTrue(sydneyFound, "Sydney not found in auto-complete suggestions.");
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
