import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;

public class InternationalShippingQuoteE2ETest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://your-shipping-homepage.com/";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void testInternationalShippingQuoteEndToEnd() {
        driver.get(baseUrl);

        // Step 1: Homepage is loaded, Click "Express Book & Pay"
        WebElement expressBookPayBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Express Book & Pay')]")));
        expressBookPayBtn.click();

        // Step 2: Verify prominent “Get a quote” CTA
        WebElement getAQuoteCta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Get a quote')]")));
        Assert.assertTrue(getAQuoteCta.isDisplayed(), "'Get a quote' CTA is not visible.");

        // Step 3: Click “Get a quote” button
        String mainWindow = driver.getWindowHandle();
        getAQuoteCta.click();

        // Step 4: New Tab is opened for quote
        waitForNewTabAndSwitch(driver, mainWindow);

        // Step 5: Validate page title
        wait.until(ExpectedConditions.titleContains("Your Package Quote Estimate – Express Book & Pay – MyTeamGE"));
        Assert.assertTrue(driver.getTitle().contains("Your Package Quote Estimate"), "Incorrect Quote Estimate page");

        // Step 6: Locate "Send from" field, type, and use autocomplete
        WebElement sendFromInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("from-location")));
        sendFromInput.sendKeys("London");

        WebElement fromAutoComplete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'autocomplete')]//li[contains(text(),'London, United Kingdom')]")));
        fromAutoComplete.click();

        // Step 7: Delivery type “Business” for from-location
        WebElement fromDeliveryBusiness = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='from-delivery-type' and @value='Business']/following-sibling::label")));
        fromDeliveryBusiness.click();

        // Step 8: Locate "Deliver to" field, type, and use autocomplete
        WebElement deliverToInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("to-location")));
        deliverToInput.sendKeys("Sydney");

        WebElement toAutoComplete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'autocomplete')]//li[contains(text(),'Sydney, Australia')]")));
        toAutoComplete.click();

        // Step 9: Delivery type “Residential” for to-location
        WebElement toDeliveryResidential = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='to-delivery-type' and @value='Residential']/following-sibling::label")));
        toDeliveryResidential.click();

        // Step 10: Click “Continue” button
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
        continueBtn.click();

        // Step 11: Click on “Use your own satchel”
        WebElement ownSatchelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'satchel-options')]//button[contains(text(),'Use your own satchel')]")));
        ownSatchelBtn.click();

        // Step 12: Review prohibited items list, check confirmation box
        WebElement prohibitedCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("prohibited-confirm")));
        prohibitedCheckbox.click();

        // Assertion: Proceed is blocked if not checked - test. (Negative scenario)
        prohibitedCheckbox.click(); // Uncheck
        continueBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueBtn.click();
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'You must confirm that you are not sending prohibited items')]")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message for prohibited items missing.");

        prohibitedCheckbox.click(); // Now check
        continueBtn.click();

        // Step 13: Concierge pickup, click continue
        WebElement conciergePickupBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='pickup-choice' and @value='concierge']/following-sibling::label")));
        conciergePickupBtn.click();
        continueBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueBtn.click();

        // Step 14: Click "Save The Quote"
        WebElement saveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save The Quote')]")));
        saveQuoteBtn.click();

        // Step 15: Get reference number
        WebElement refNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'reference-number') or contains(text(),'Reference Number')]")));
        String quoteReference = refNumber.getText().replaceAll("[^A-Z0-9]", "");

        Assert.assertNotNull(quoteReference, "Reference number is missing.");

        // Step 16: Get Priority amount
        WebElement priorityAmountElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'priority-amount') or .//label[contains(text(),'Priority')]]//span[contains(@class,'amount')]")));
        String priorityAmount = priorityAmountElem.getText();
        Assert.assertTrue(priorityAmount != null && !priorityAmount.isEmpty(), "Priority amount missing.");

        // Step 17: Go back to homepage
        driver.close(); // closes quote tab
        driver.switchTo().window(mainWindow);
        driver.navigate().refresh();

        // Step 18: Click or locate "Retrieve A Quote", enter reference, submit
        WebElement retrieveQuoteLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(translate(text(),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'RETRIEVE A QUOTE')]")));
        retrieveQuoteLink.click();

        WebElement quoteReferenceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("retrieve-quote-reference")));
        quoteReferenceInput.sendKeys(quoteReference);

        WebElement retrieveBtn = driver.findElement(By.xpath("//button[contains(text(),'Retrieve')]"));
        retrieveBtn.click();

        // Step 19: Assertion - Check shown quote reference and amount
        WebElement retrievedRef = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'reference-number') or contains(text(),'Reference Number')][contains(text(),'" + quoteReference + "')]")));
        Assert.assertTrue(retrievedRef.getText().contains(quoteReference), "Retrieved quote reference mismatch.");

        WebElement retrievedPriorityAmountElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'priority-amount') or .//label[contains(text(),'Priority')]]//span[contains(@class,'amount')]")));
        String retrievedPriorityAmount = retrievedPriorityAmountElem.getText();
        Assert.assertEquals(retrievedPriorityAmount, priorityAmount, "Priority amount does not match on retrieval.");

        // Other assertions: Summary details match (e.g., locations, delivery types can be validated similarly as above)
    }

    private void waitForNewTabAndSwitch(WebDriver driver, String currentTab) {
        wait.until(d -> driver.getWindowHandles().size() > 1);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) driver.quit();
    }
}
