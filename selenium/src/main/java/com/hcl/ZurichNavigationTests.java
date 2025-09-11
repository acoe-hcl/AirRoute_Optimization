import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.*;

public class ZurichNavigationTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // ChromeDriver setup
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    // Helper methods
    private void launchApp() {
        driver.get("https://zurich-example-app.com/");
    }

    private void goToEnergySection() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products and Services"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Commercial Insurance"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products and Claims"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Energy"))).click();
    }

    private void selectContactUsCompany() {
        WebElement companyRadio = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contact-company")));
        companyRadio.click();
    }

    private void selectFindProductsDropdown(String value) {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("find-products")));
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
    }

    private void clickVisitLocalWebsite() {
        WebElement visitLocalWebsite = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Visit local website")));
        visitLocalWebsite.click();
    }

    // TC01: Positive Scenario
    @Test
    public void test_TC01_successfulNavigationEnergyCompany() {
        launchApp();
        goToEnergySection();
        selectContactUsCompany();
        selectFindProductsDropdown("Find products");
        String mainTab = driver.getWindowHandle();
        clickVisitLocalWebsite();
        // Handle redirection
        for(String tab : driver.getWindowHandles()) {
            if(!tab.equals(mainTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("energy-local"), "Redirection to energy local website failed.");
        Assert.assertTrue(driver.getPageSource().contains("Product Details"), "Relevant product details are not shown.");
        driver.close();
        driver.switchTo().window(mainTab);
    }

    // TC02: Energy Section Not Available
    @Test
    public void test_TC02_energySectionMissing() {
        launchApp();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products and Services"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Commercial Insurance"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products and Claims"))).click();
        boolean isEnergyPresent = driver.findElements(By.linkText("Energy")).size() > 0;
        Assert.assertFalse(isEnergyPresent, "Energy section should not be available.");
    }

    // TC03: Company Radio Button Missing
    @Test
    public void test_TC03_companyRadioMissing() {
        launchApp();
        goToEnergySection();
        boolean isCompanyRadioPresent = driver.findElements(By.id("contact-company")).size() > 0;
        if (isCompanyRadioPresent) {
            WebElement companyRadio = driver.findElement(By.id("contact-company"));
            boolean isEnabled = companyRadio.isEnabled();
            companyRadio.click();
            Assert.assertFalse(isEnabled, "Company radio button should not be selectable.");
        } else {
            Assert.assertTrue(true, "Company radio button is missing as expected.");
        }
    }

    // TC04: No Dropdown Selection
    @Test
    public void test_TC04_noDropdownSelection() {
        launchApp();
        goToEnergySection();
        selectContactUsCompany();
        // Leave dropdown blank
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("find-products")));
        Select select = new Select(dropdown);
        select.selectByVisibleText(""); // Assuming blank option exists
        WebElement visitLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Visit local website")));
        boolean isDisabled = !visitLink.isEnabled();
        if (!isDisabled) {
            visitLink.click();
            boolean errorPresent = driver.getPageSource().contains("Dropdown selection required");
            Assert.assertTrue(errorPresent, "User should be presented with error for empty dropdown.");
        } else {
            Assert.assertTrue(isDisabled, "Visit local website link should be disabled.");
        }
    }

    // TC05: Non-functional Visit Link
    @Test
    public void test_TC05_nonFunctionalVisitLocalWebsite() {
        launchApp();
        goToEnergySection();
        selectContactUsCompany();
        selectFindProductsDropdown("Find products");
        WebElement visitLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Visit local website")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('href','javascript:void(0);')", visitLink); // simulate inactive
        visitLink.click();
        boolean errorPresent = driver.getPageSource().contains("Error") || driver.getCurrentUrl().equals("https://zurich-example-app.com/");
        Assert.assertTrue(errorPresent, "User should not be able to redirect, error message should be shown.");
    }

    // TC06: Rapid Clicks Handling
    @Test
    public void test_TC06_rapidClicksHandling() {
        launchApp();
        WebElement prodServ = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products and Services")));
        WebElement commIns = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Commercial Insurance")));
        for(int i=0;i<5;i++) { prodServ.click(); commIns.click(); }
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products and Claims"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Energy"))).click();
        selectContactUsCompany();
        selectFindProductsDropdown("Find products");
        WebElement visitLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Visit local website")));
        for(int i=0;i<5;i++) { visitLink.click(); }
        int tabCount = driver.getWindowHandles().size();
        Assert.assertTrue(tabCount == 2, "Only one valid redirection tab should be opened.");
    }

    // TC07: Individual Radio Button Functionality
    @Test
    public void test_TC07_individualRadioFunctionality() {
        launchApp();
        goToEnergySection();
        WebElement individualRadio = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contact-individual")));
        individualRadio.click();
        selectFindProductsDropdown("Find products");
        WebElement visitLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Visit local website")));
        visitLink.click();
        String url = driver.getCurrentUrl();
        boolean isCompanySite = url.contains("company-products");
        Assert.assertFalse(isCompanySite, "User as individual should not access company products local site.");
        Assert.assertTrue(driver.getPageSource().contains("Individual Options"), "Tailored options should be visible.");
    }

    // TC08: Maximum Input Length in Dropdown
    @Test
    public void test_TC08_maxLengthDropdownSelection() {
        launchApp();
        goToEnergySection();
        selectContactUsCompany();
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("find-products")));
        Select select = new Select(dropdown);

        String longestOption = "";
        for (WebElement option : select.getOptions()) {
            if (option.getText().length() > longestOption.length())
                longestOption = option.getText();
        }

        select.selectByVisibleText(longestOption);
        clickVisitLocalWebsite();
        for(String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("energy-local"), "Redirection failed for long-named product.");
        Assert.assertFalse(driver.getPageSource().contains("..."), "No UI/data truncation detected.");
    }

    // TC09: Unsupported Browser Handling
    @Test
    public void test_TC09_unsupportedBrowserNavigation() {
        // Simulate unsupported browser by changing user-agent
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=UnsupportedBrowser");
        WebDriver unsupportedDriver = new ChromeDriver(options);
        WebDriverWait localWait = new WebDriverWait(unsupportedDriver, 15);
        unsupportedDriver.get("https://zurich-example-app.com/");
        localWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products and Services"))).click();
        localWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Commercial Insurance"))).click();
        localWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products and Claims"))).click();
        localWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Energy"))).click();
        WebElement companyRadio = localWait.until(ExpectedConditions.presenceOfElementLocated(By.id("contact-company")));
        companyRadio.click();
        WebElement dropdown = localWait.until(ExpectedConditions.presenceOfElementLocated(By.id("find-products")));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Find products");
        WebElement visitLink = localWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Visit local website")));
        visitLink.click();

        boolean isErrorMsgPresent =
                unsupportedDriver.getPageSource().contains("unsupported browser") ||
                unsupportedDriver.getPageSource().contains("not supported") ||
                !unsupportedDriver.getCurrentUrl().contains("energy-local");

        Assert.assertTrue(isErrorMsgPresent, "Appropriate error for unsupported browser not shown.");
        unsupportedDriver.quit();
    }
}
