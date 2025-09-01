import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class InternationalShippingQuoteTest {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://your-shipping-homepage.com"; // Replace with actual URL
    String quoteRefNumber;
    String priorityAmount;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update path
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void endToEndInternationalShippingQuote() {
        // Step 1: Access homepage
        driver.get(baseUrl);

        // Step 2: Click "Express Book & Pay"
        WebElement expressBookPayBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Express Book & Pay')]")));
        expressBookPayBtn.click();

        // Step 3: Verify "Get a quote" CTA present
        WebElement getQuoteCTA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Get a quote') or contains(text(),'Get Quote')]")));
        Assert.assertTrue(getQuoteCTA.isDisplayed(), "'Get a quote' CTA not visible.");

        // Step 4: Click “Get a quote” button
        getQuoteCTA.click();

        // Step 5: Handle navigation to new tab for quote form
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> iterator = allWindows.iterator();
        String quoteWindow = mainWindow;
        while (iterator.hasNext()) {
            String handle = iterator.next();
            if (!handle.equals(mainWindow)) {
                quoteWindow = handle;
                break;
            }
        }
        driver.switchTo().window(quoteWindow);

        // Step 6/7: Fill "Send from" with auto-complete dropdown & choose Business
        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("fromLocation")));
        fromInput.sendKeys("New York"); // Example, update locator/logic as per actual app

        WebElement fromAutoSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class, 'autocomplete-suggestion') and contains(.,'New York')]")));
        fromAutoSuggestion.click();

        WebElement fromType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='fromDeliveryType']")));
        Select fromDeliveryType = new Select(fromType);
        fromDeliveryType.selectByVisibleText("Business");

        // Step 8/9/10: Fill “Deliver to” with auto-complete dropdown & choose Residential
        WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("toLocation")));
        toInput.sendKeys("Melbourne");

        WebElement toAutoSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class, 'autocomplete-suggestion') and contains(.,'Melbourne')]")));
        toAutoSuggestion.click();

        WebElement toType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='toDeliveryType']")));
        Select toDeliveryType = new Select(toType);
        toDeliveryType.selectByVisibleText("Residential");

        // Step 11: Click Continue
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        continueBtn.click();

        // Step 12: Click "Use your own satchel"
        WebElement ownSatchelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Use your own satchel')]")));
        ownSatchelBtn.click();

        // Step 13: Review prohibited items list & check confirmation
        WebElement prohibitedCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and contains(@id,'prohibitedItemsConfirm')]")));
        prohibitedCheckbox.click();

        // Step 14: Continue
        WebElement continueBtn2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        continueBtn2.click();

        // Step 15: Click "Concierge pickup" and Continue
        WebElement conciergePickupBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and contains(@value,'Concierge')]")));
        conciergePickupBtn.click();

        WebElement continueBtn3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        continueBtn3.click();

        // Step 16: Click "Save The Quote"
        WebElement saveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save The Quote')]")));
        saveQuoteBtn.click();

        // Step 17/18: Note/Capture quote reference number
        WebElement quoteRefElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'quote-reference')]")));
        quoteRefNumber = quoteRefElem.getText();
        Assert.assertNotNull(quoteRefNumber, "Quote Reference Number not found");

        // Step 19: Capture Priority amount
        WebElement priorityAmountElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'priority-amount')]")));
        priorityAmount = priorityAmountElem.getText();
        Assert.assertTrue(priorityAmount != null && !priorityAmount.isEmpty(), "Priority Amount not visible");

        // Step 20: Navigate to Home Page, switch to main window
        driver.close();
        driver.switchTo().window(mainWindow);
        driver.navigate().refresh();

        // Step 21: Locate and use “Retrieve a Quote” with the code
        WebElement retrieveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Retrieve A Quote')]")));
        retrieveQuoteBtn.click();

        WebElement quoteCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='quoteReference']")));
        quoteCodeInput.sendKeys(quoteRefNumber);

        WebElement retrieveSubmitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Retrieve')]")));
        retrieveSubmitBtn.click();

        // Step 22: Confirm display of original quote summary and details
        WebElement quoteSummary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'quote-summary')]")));
        Assert.assertTrue(quoteSummary.isDisplayed(), "Quote Summary not displayed");

        WebElement displayedRef = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + quoteRefNumber + "')]")));
        Assert.assertEquals(displayedRef.getText(), quoteRefNumber, "Quote reference mismatch after retrieval");

        // Step 23: Verify copied priority amount in quote details
        WebElement displayedPriorityAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'priority-amount')]")));
        Assert.assertEquals(displayedPriorityAmount.getText(), priorityAmount, "Priority Amount mismatch after retrieval");

        // --- Negative case for step 13: Try continuing without prohibited checkbox ---
        driver.navigate().back(); // To Prohibited Items page
        prohibitedCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and contains(@id,'prohibitedItemsConfirm')]")));
        prohibitedCheckbox.click(); // Uncheck
        continueBtn2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        continueBtn2.click();
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'error') and contains(text(),'must confirm')]")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed when prohibited items not acknowledged");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
