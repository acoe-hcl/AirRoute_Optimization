import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class InternationalShippingQuoteTest {

    WebDriver driver;
    String baseUrl = "https://www.shippingservice.com"; // Replace with actual URL

    String quoteReferenceNumber;
    String priorityAmount;
    String fromLocation = "Sydney, Australia";
    String toLocation = "New York, USA";

    @BeforeClass
    public void setup() {
        // System.setProperty("webdriver.chrome.driver", "path_to/chromedriver"); // set path accordingly
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testHomepageFlow() {
        driver.get(baseUrl);

        // Verify 'Express Book & Pay' is visible and clickable
        WebElement bookAndPayBtn = driver.findElement(By.xpath("//a[contains(text(),'Express Book & Pay')]"));
        Assert.assertTrue(bookAndPayBtn.isDisplayed());
        bookAndPayBtn.click();

        // Verify 'Get a quote' CTA is present
        WebElement getQuoteCTA = driver.findElement(By.xpath("//button[contains(text(),'Get a quote')]"));
        Assert.assertTrue(getQuoteCTA.isDisplayed());

        // Click 'Get a quote'; should open new tab
        String originalWindow = driver.getWindowHandle();
        getQuoteCTA.click();

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String newTab = originalWindow;
        while(it.hasNext()) {
            String handle = it.next();
            if(!handle.equals(originalWindow)) {
                newTab = handle;
            }
        }
        driver.switchTo().window(newTab);

        // Verify user is on the correct page
        Assert.assertTrue(driver.getTitle().contains("Your Package Quote Estimate"));
    }

    @Test(priority = 2)
    public void testQuoteFormLocationAutoCompleteAndDeliveryType() {
        // Type and select 'send from' location with auto-complete
        WebElement fromField = driver.findElement(By.id("fromLocation")); // Adjust locator
        fromField.sendKeys(fromLocation.substring(0,3));
        WebElement autoSuggest = driver.findElement(By.xpath("//li[contains(text(),'" + fromLocation + "')]"));
        Assert.assertTrue(autoSuggest.isDisplayed());
        autoSuggest.click();
        WebElement deliveryTypeBusiness = driver.findElement(By.xpath("//label[contains(text(),'Business')]//input[@type='radio']"));
        deliveryTypeBusiness.click();

        // Type and select 'deliver to' location with auto-complete
        WebElement toField = driver.findElement(By.id("toLocation")); // Adjust locator
        toField.sendKeys(toLocation.substring(0,3));
        WebElement autoSuggestTo = driver.findElement(By.xpath("//li[contains(text(),'" + toLocation + "')]"));
        Assert.assertTrue(autoSuggestTo.isDisplayed());
        autoSuggestTo.click();
        WebElement deliveryTypeResidential = driver.findElement(By.xpath("//label[contains(text(),'Residential')]//input[@type='radio']"));
        deliveryTypeResidential.click();

        // Verify only valid options are selectable
        Assert.assertTrue(deliveryTypeBusiness.isEnabled());
        Assert.assertTrue(deliveryTypeResidential.isEnabled());

        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
    }

    @Test(priority = 3)
    public void testProhibitedItemsValidation() {
        driver.findElement(By.xpath("//button[contains(text(),'Use your own satchel')]")).click();

        // Try clicking Continue without acknowledging prohibited items
        WebElement continueBtn = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueBtn.click();

        // Verify error message appears
        WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'error') and contains(text(),'must acknowledge')]"));
        Assert.assertTrue(errorMsg.isDisplayed());

        // Check the confirmation box, now proceed
        WebElement itemsConfirmCheck = driver.findElement(By.id("prohibitedItemsConfirm")); // Adjust locator
        itemsConfirmCheck.click();

        continueBtn.click();

        // Concierge pickup
        WebElement conciergePickup = driver.findElement(By.xpath("//button[contains(text(),'Concierge pickup')]"));
        conciergePickup.click();
        driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
    }

    @Test(priority = 4)
    public void testSaveAndRetrieveQuoteFlow() {
        // Save the quote
        WebElement saveQuoteBtn = driver.findElement(By.xpath("//button[contains(text(),'Save The Quote')]"));
        Assert.assertTrue(saveQuoteBtn.isDisplayed());
        saveQuoteBtn.click();

        // Note quote reference number
        WebElement quoteRefElem = driver.findElement(By.xpath("//span[@id='quoteReferenceNumber']"));
        quoteReferenceNumber = quoteRefElem.getText();
        Assert.assertTrue(quoteReferenceNumber.matches("[A-Z0-9]+"));

        // Copy priority amount
        WebElement priorityAmountElem = driver.findElement(By.xpath("//span[@class='priority-amount']"));
        priorityAmount = priorityAmountElem.getText();
        Assert.assertTrue(priorityAmount.matches("\\$[0-9,.]+"));

        // Go back to homepage
        driver.close(); // Close current tab
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            break;
        }

        driver.navigate().refresh();

        // Locate 'Retrieve A Quote'
        WebElement retrieveQuoteBtn = driver.findElement(By.xpath("//button[contains(text(),'Retrieve A Quote')]"));
        retrieveQuoteBtn.click();

        // Enter reference number
        WebElement refNumInput = driver.findElement(By.id("retrieveReferenceNumber"));
        refNumInput.sendKeys(quoteReferenceNumber);

        driver.findElement(By.xpath("//button[contains(text(),'Retrieve')]")).click();

        // Assert that the retrieved quote details match original
        WebElement retrievedRefNumElem = driver.findElement(By.xpath("//span[@id='quoteReferenceNumber']"));
        String retrievedRefNum = retrievedRefNumElem.getText();
        Assert.assertEquals(retrievedRefNum, quoteReferenceNumber);

        WebElement retrievedPriorityAmountElem = driver.findElement(By.xpath("//span[@class='priority-amount']"));
        String retrievedPriorityAmount = retrievedPriorityAmountElem.getText();
        Assert.assertEquals(retrievedPriorityAmount, priorityAmount);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
