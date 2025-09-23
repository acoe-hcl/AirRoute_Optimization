import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class InternationalShippingQuoteTest {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.shipping-service-homepage.example.com";
    String quoteReferenceNumber;
    String copiedPriorityAmount;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void testInternationalShippingQuoteEndToEnd() {
        // Step 1: Access the homepage
        driver.get(baseUrl);

        // Step 2: Click on "Express Book & Pay"
        WebElement expressBookPayBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Express Book & Pay')]")));
        expressBookPayBtn.click();

        // Step 3: Verify "Get a quote" call to action is present
        WebElement getAQuoteCta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Get a quote')]")));
        Assert.assertTrue(getAQuoteCta.isDisplayed(), "Get a quote call to action should be visible on homepage.");

        // Step 4: Click the "Get a quote" button
        String parentWindow = driver.getWindowHandle();
        getAQuoteCta.click();

        // Step 5: Switch to new tab and validate the form is displayed
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        wait.until(ExpectedConditions.titleContains("Your Package Quote Estimate – Express Book & Pay – MyTeamGE"));
        Assert.assertTrue(driver.getTitle().contains("Express Book & Pay"), "Quote Estimate Page title mismatch.");

        // Step 6: Locate "Send from" field and enter value with autocomplete
        WebElement sendFromInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("send-from-location")));
        sendFromInput.sendKeys("Sydney");
        WebElement fromSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'autocomplete')]/li[contains(text(),'Sydney,')]")));
        fromSuggestion.click();

        // Step 7: Select Delivery Type: Business
        WebElement businessRadio = driver.findElement(By.xpath("//label[contains(text(),'Business')]/preceding-sibling::input[@type='radio']"));
        if (!businessRadio.isSelected()) {
            businessRadio.click();
        }
        Assert.assertTrue(businessRadio.isSelected(), "Business delivery type should be selected.");

        // Step 8: "Deliver to" field with autocomplete
        WebElement deliverToInput = driver.findElement(By.id("deliver-to-location"));
        deliverToInput.sendKeys("Auckland");
        WebElement toSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'autocomplete')]/li[contains(text(),'Auckland,')]")));
        toSuggestion.click();

        // Step 9: Select Delivery Type: Residential
        WebElement residentialRadio = driver.findElement(By.xpath("//label[contains(text(),'Residential')]/preceding-sibling::input[@type='radio']"));
        if (!residentialRadio.isSelected()) {
            residentialRadio.click();
        }
        Assert.assertTrue(residentialRadio.isSelected(), "Residential delivery type should be selected.");

        // Step 10: Click Continue button
        WebElement continueBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueBtn.click();

        // Step 11: Click Use your own satchel
        WebElement ownSatchelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Use your own satchel')]")));
        ownSatchelBtn.click();

        // Step 12: Review prohibited items and check confirmation
        WebElement prohibitedList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prohibited-items-list")));
        Assert.assertTrue(prohibitedList.isDisplayed(), "Prohibited items list should display.");
        WebElement acknowledgeTick = driver.findElement(By.cssSelector("input[type='checkbox'][name='prohibited-ack']"));
        if (!acknowledgeTick.isSelected()) {
            acknowledgeTick.click();
        }
        Assert.assertTrue(acknowledgeTick.isSelected(), "Acknowledge checkbox should be selected.");

        // Step 13: Continue
        continueBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueBtn.click();

        // Step 14: Click Concierge Pickup and Continue
        WebElement conciergePickupOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Concierge pickup')]")));
        conciergePickupOption.click();
        continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        continueBtn.click();

        // Step 15: Click "Save The Quote"
        WebElement saveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save The Quote')]")));
        saveQuoteBtn.click();

        // Step 16: Note and review quote reference number
        WebElement quoteRefElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quote-reference-number")));
        quoteReferenceNumber = quoteRefElement.getText();
        Assert.assertNotNull(quoteReferenceNumber, "Quote Reference Number should be displayed.");

        // Step 17: Copy Priority amount
        WebElement priorityAmountElement = driver.findElement(By.id("priority-amount"));
        copiedPriorityAmount = priorityAmountElement.getText();
        Assert.assertTrue(copiedPriorityAmount.matches("\\$[0-9,.]+"), "Priority amount should be displayed and formatted.");

        // Step 18: Go to the homepage (open in same or new tab)
        driver.switchTo().window(parentWindow);
        driver.navigate().refresh();

        // Step 19: Locate "Retrieve A Quote" and input code
        WebElement retrieveQuoteBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Retrieve A Quote')]")));
        retrieveQuoteBtn.click();

        WebElement quoteInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrieve-quote-input")));
        quoteInput.clear();
        quoteInput.sendKeys(quoteReferenceNumber);

        WebElement retrieveBtn = driver.findElement(By.xpath("//button[contains(text(),'Retrieve')]"));
        retrieveBtn.click();

        // Step 20: Validate quote details and Priority amount matches
        WebElement displayedQuoteRef = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quote-reference-number")));
        Assert.assertEquals(displayedQuoteRef.getText().trim(), quoteReferenceNumber.trim(), "Reference number should match retrieved.");

        WebElement displayedPriorityAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("priority-amount")));
        Assert.assertEquals(displayedPriorityAmount.getText().trim(), copiedPriorityAmount.trim(), "Priority amount should match original quote.");

        // End-to-end assertions for expected results
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Sydney')]")).isDisplayed(), "From location in summary should match.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Auckland')]")).isDisplayed(), "To location in summary should match.");
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Business')]")).isDisplayed(), "Delivery type 'Business' appears in summary.");
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Residential')]")).isDisplayed(), "Delivery type 'Residential' appears in summary.");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
