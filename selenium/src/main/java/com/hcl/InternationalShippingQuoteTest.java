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
import java.util.Iterator;
import java.util.Set;

public class InternationalShippingQuoteTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    String baseUrl = "https://www.your-shippingservice.com";
    String fromLocation = "Sydney, Australia";
    String toLocation = "London, United Kingdom";
    String priorityAmount = "";
    String quoteReference = "";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testGetQuoteFlow() {
        driver.get(baseUrl);

        // Step 1: Homepage loaded
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Express Book & Pay"))).isDisplayed());

        // Step 2: Click "Express Book & Pay"
        driver.findElement(By.linkText("Express Book & Pay")).click();

        // Step 3: Verify "Get a quote" CTA present
        WebElement getQuoteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Get a quote')]")));
        Assert.assertTrue(getQuoteButton.isDisplayed());

        // Step 4: Click "Get a quote" (expect new tab)
        String originalWindow = driver.getWindowHandle();
        getQuoteButton.click();

        // Step 5: Switch to new tab (Quote form)
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String quoteWindow = "";
        while (it.hasNext()) {
            String win = it.next();
            if (!win.equals(originalWindow)) {
                quoteWindow = win;
                break;
            }
        }
        driver.switchTo().window(quoteWindow);

        // Check correct page title
        wait.until(ExpectedConditions.titleContains("Your Package Quote Estimate"));
        Assert.assertTrue(driver.getTitle().contains("Your Package Quote Estimate"));

        // Step 6: Locate "Send from" and "Deliver to"
        WebElement sendFrom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fromLocation")));
        WebElement deliverTo = driver.findElement(By.id("toLocation"));

        // Step 7: "From" location autocomplete
        sendFrom.clear();
        sendFrom.sendKeys(fromLocation.substring(0, 3)); // e.g. "Syd"
        WebElement autoOptionFrom = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'autocomplete')]//div[contains(text(),'" + fromLocation.split(",")[0] + "')]")));
        autoOptionFrom.click();

        // Step 8: Select "Business" delivery for "From"
        WebElement fromTypeBusiness = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'Business')]//input[@name='fromType']")));
        fromTypeBusiness.click();

        // Step 9: "To" location autocomplete
        deliverTo.clear();
        deliverTo.sendKeys(toLocation.substring(0, 3));
        WebElement autoOptionTo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'autocomplete')]//div[contains(text(),'" + toLocation.split(",")[0] + "')]")));
        autoOptionTo.click();

        // Step 10: Select "Residential" delivery for "To"
        WebElement toTypeResidential = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'Residential')]//input[@name='toType']")));
        toTypeResidential.click();

        // Step 11: Click "Continue"
        WebElement continueBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueBtn.click();

        // Step 12: Click "Use your own satchel"
        WebElement ownSatchelBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Use your own satchel')]")));
        ownSatchelBtn.click();

        // Step 13: Prohibited items - leave unchecked once to validate error
        WebElement confirmCheckBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prohibited-confirm")));
        continueBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueBtn.click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Please confirm')]")));
        Assert.assertTrue(errorMsg.isDisplayed());

        // Now check and proceed
        confirmCheckBox.click();

        // Step 14: Click "Continue" after confirmation
        continueBtn.click();

        // Step 15: Concierge pickup & continue
        WebElement conciergePickup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'Concierge pickup')]//input[@type='radio']")));
        conciergePickup.click();
        WebElement continueBtn3 = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueBtn3.click();

        // Step 16: Click "Save The Quote"
        WebElement saveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save The Quote')]")));
        saveQuoteBtn.click();

        // Step 17 & 18: Get reference number
        WebElement refElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Reference') or contains(text(),'Ref:')]/following-sibling::*")));
        quoteReference = refElement.getText();
        Assert.assertTrue(quoteReference.matches("[A-Z0-9]+"));

        // Step 19: Copy priority amount
        WebElement priorityAmountElm = driver.findElement(By.xpath("//*[contains(text(),'Priority')]/following-sibling::*[1]"));
        priorityAmount = priorityAmountElm.getText();
        Assert.assertTrue(priorityAmount.startsWith("$"));

        // Step 20: Navigate to home page
        driver.close();
        driver.switchTo().window(originalWindow);
        driver.navigate().refresh();

        // Step 21: Retrieve saved quote
        WebElement retrieveQuoteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Retrieve A Quote")));
        retrieveQuoteBtn.click();
        WebElement retrieveInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quoteReference")));
        retrieveInput.sendKeys(quoteReference);
        WebElement retrieveSubmit = driver.findElement(By.xpath("//button[contains(text(),'Retrieve')]"));
        retrieveSubmit.click();

        // Step 22: Validate retrieved quote details
        WebElement summaryRef = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + quoteReference + "')]")));
        Assert.assertTrue(summaryRef.isDisplayed());

        // Step 23: Verify priority amount matches
        WebElement retrievedPriorityAmount = driver.findElement(By.xpath("//*[contains(text(),'Priority')]/following-sibling::*[1]"));
        Assert.assertEquals(retrievedPriorityAmount.getText(), priorityAmount);
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}
