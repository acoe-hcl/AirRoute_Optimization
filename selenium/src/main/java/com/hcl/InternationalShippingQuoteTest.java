import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class InternationalShippingQuoteTest {
    private WebDriver driver;
    private String baseUrl = "https://www.myteamge.com/"; // Replace with actual homepage URL
    private String fromLocation = "Sydney, Australia";
    private String toLocation = "London, United Kingdom";
    private String quoteReference;
    private String priorityAmount;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void homepageQuoteCTAIsVisible() {
        driver.get(baseUrl);
        Assert.assertTrue(driver.findElement(By.xpath("//button[contains(.,'Get a quote')]")).isDisplayed(), "Get a quote CTA is not visible.");
    }

    @Test(priority = 2)
    public void quoteFormOpensAndLocationsAutoComplete() {
        driver.findElement(By.xpath("//button[contains(.,'Express Book & Pay')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//button[contains(.,'Get a quote')]")).isDisplayed(), "Get a quote button not visible after Express Book & Pay.");

        driver.findElement(By.xpath("//button[contains(.,'Get a quote')]")).click();

        // Switch to new tab
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Assert.assertTrue(driver.getTitle().contains("Your Package Quote Estimate"), "Quote form page title mismatch.");

        WebElement fromInput = driver.findElement(By.xpath("//input[contains(@placeholder,'Send from')]"));
        fromInput.sendKeys(fromLocation.substring(0,3));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//li[contains(.,'" + fromLocation + "')]"))).click().perform();

        Assert.assertTrue(fromInput.getAttribute("value").contains("Sydney"), "From Location auto-complete failed.");

        WebElement fromTypeBusiness = driver.findElement(By.xpath("//select[@name='fromType']/option[contains(.,'Business')]"));
        fromTypeBusiness.click();

        WebElement toInput = driver.findElement(By.xpath("//input[contains(@placeholder,'Deliver to')]"));
        toInput.sendKeys(toLocation.substring(0,3));
        actions.moveToElement(driver.findElement(By.xpath("//li[contains(.,'" + toLocation + "')]"))).click().perform();

        Assert.assertTrue(toInput.getAttribute("value").contains("London"), "To Location auto-complete failed.");

        WebElement toTypeResidential = driver.findElement(By.xpath("//select[@name='toType']/option[contains(.,'Residential')]"));
        toTypeResidential.click();
    }

    @Test(priority = 3)
    public void deliveryTypeSelectionAndValidations() {
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();

        driver.findElement(By.xpath("//button[contains(.,'Use your own satchel')]")).click();

        // Prohibited items checkbox must be checked before continuing
        driver.findElement(By.xpath("//label[contains(.,'I confirm that my package does not contain any prohibited items')]//input")).click();

        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();

        driver.findElement(By.xpath("//button[contains(.,'Concierge pickup')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
    }

    @Test(priority = 4)
    public void saveQuoteAndVerifyReferenceAndAmount() {
        driver.findElement(By.xpath("//button[contains(.,'Save The Quote')]")).click();

        WebElement referenceElem = driver.findElement(By.xpath("//span[contains(@class,'quote-reference')]"));
        quoteReference = referenceElem.getText();
        Assert.assertNotNull(quoteReference, "Quote reference is missing.");

        WebElement priorityAmountElem = driver.findElement(By.xpath("//div[contains(@class,'priority-amount')]"));
        priorityAmount = priorityAmountElem.getText();
        Assert.assertTrue(priorityAmount.matches("\\$\\d+\\.\\d{2}"), "Priority amount format invalid.");
    }

    @Test(priority = 5)
    public void retrieveAndValidateSavedQuote() {
        driver.get(baseUrl);
        WebElement retrieveQuoteBtn = driver.findElement(By.xpath("//button[contains(.,'Retrieve A Quote')]"));
        retrieveQuoteBtn.click();
        WebElement quoteInput = driver.findElement(By.xpath("//input[@name='quoteReference']"));
        quoteInput.sendKeys(quoteReference);
        driver.findElement(By.xpath("//button[contains(.,'Retrieve')]")).click();

        WebElement retrievedReferenceElem = driver.findElement(By.xpath("//span[contains(@class,'quote-reference')]"));
        String retrievedReference = retrievedReferenceElem.getText();
        Assert.assertEquals(retrievedReference, quoteReference, "Retrieved quote reference does not match.");

        WebElement retrievedPriorityElem = driver.findElement(By.xpath("//div[contains(@class,'priority-amount')]"));
        String retrievedAmount = retrievedPriorityElem.getText();
        Assert.assertEquals(retrievedAmount, priorityAmount, "Retrieved priority amount does not match original.");

        WebElement summaryLocationFrom = driver.findElement(By.xpath("//div[contains(@class,'summary-from') and contains(.,'Sydney')]"));
        WebElement summaryLocationTo = driver.findElement(By.xpath("//div[contains(@class,'summary-to') and contains(.,'London')]"));
        Assert.assertTrue(summaryLocationFrom.isDisplayed(), "Origin location not present in summary.");
        Assert.assertTrue(summaryLocationTo.isDisplayed(), "Destination location not present in summary.");
    }

    @Test(priority = 6)
    public void mandatoryProhibitedItemsCheckboxValidation() {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//button[contains(.,'Express Book & Pay')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'Get a quote')]")).click();

        // Switch to new tab again
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        // Repeat a quick minimal form fill
        WebElement fromInput = driver.findElement(By.xpath("//input[contains(@placeholder,'Send from')]"));
        fromInput.sendKeys(fromLocation.substring(0,3));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//li[contains(.,'" + fromLocation + "')]"))).click().perform();

        WebElement fromTypeBusiness = driver.findElement(By.xpath("//select[@name='fromType']/option[contains(.,'Business')]"));
        fromTypeBusiness.click();

        WebElement toInput = driver.findElement(By.xpath("//input[contains(@placeholder,'Deliver to')]"));
        toInput.sendKeys(toLocation.substring(0,3));
        actions.moveToElement(driver.findElement(By.xpath("//li[contains(.,'" + toLocation + "')]"))).click().perform();

        WebElement toTypeResidential = driver.findElement(By.xpath("//select[@name='toType']/option[contains(.,'Residential')]"));
        toTypeResidential.click();

        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'Use your own satchel')]")).click();

        // Without checking prohibited items checkbox
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
        WebElement errorElem = driver.findElement(By.xpath("//div[contains(@class,'error') and contains(.,'Please confirm your package does not contain prohibited items')]"));
        Assert.assertTrue(errorElem.isDisplayed(), "Error message for prohibited items not displayed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
