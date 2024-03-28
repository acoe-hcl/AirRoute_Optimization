import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestAutomationScript {
    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Launch the application
        driver.get("http://iwmtar52064254:9080/maximo/webclient/login/login.jsp");

        // Enter "maxadmin" username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("maxadmin");

        // Enter "maxadmin" password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("maxadmin");

        // Click on Sign In button
        WebElement signInBtn = driver.findElement(By.id("signInBtn"));
        signInBtn.click();

        // Mouse hover on Assets Application
        WebElement assetsApp = driver.findElement(By.linkText("Assets"));
        Actions actions = new Actions(driver);
        actions.moveToElement(assetsApp).build().perform();

        // Click on Assets module
        WebElement assetsModule = driver.findElement(By.xpath("//a[contains(text(), 'Assets')]"));
        assetsModule.click();

        // Click on New Assets Image
        WebElement newAssetImage = driver.findElement(By.id("newAssetImage"));
        newAssetImage.click();

        // Enter "ASSETTEST1" in Asset field
        WebElement assetField = driver.findElement(By.id("asset"));
        assetField.sendKeys("ASSETTEST1");

        // Enter "Circulation Fan- Centrifugal/ 20/000 CFM" in Asset description
        WebElement assetDescriptionField = driver.findElement(By.id("description"));
        assetDescriptionField.sendKeys("Circulation Fan- Centrifugal/ 20/000 CFM");

        // Click on Select Value icon of Type
        WebElement typeSelectValueIcon = driver.findElement(By.id("type_select_arrow"));
        typeSelectValueIcon.click();

        // Click on FACILITIES Label
        WebElement facilitiesLabel = driver.findElement(By.xpath("//label[contains(text(), 'FACILITIES')]"));
        facilitiesLabel.click();

        // Enter "11200" in Parent field
        WebElement parentField = driver.findElement(By.id("parent"));
        parentField.sendKeys("11200");

        // Enter "Company1" in Calendar field
        WebElement calendarField = driver.findElement(By.id("calendar"));
        calendarField.sendKeys("Company1");

        // Enter "4" in Priority field
        WebElement priorityField = driver.findElement(By.id("priority"));
        priorityField.sendKeys("4");

        // Enter "3749-9" in Serial # field
        WebElement serialNumberField = driver.findElement(By.id("serialnumber"));
        serialNumberField.sendKeys("3749-9");

        // Enter "1002" in Service Address field
        WebElement serviceAddressField = driver.findElement(By.id("serviceaddress"));
        serviceAddressField.sendKeys("1002");

        // Enter "TRN" in Vendor field
        WebElement vendorField = driver.findElement(By.id("vendor"));
        vendorField.sendKeys("TRN");

        // Enter "TRN" in Manufacturer field
        WebElement manufacturerField = driver.findElement(By.id("manufacturer"));
        manufacturerField.sendKeys("TRN");

        // Enter "5/1/2022" in Installation Date field
        WebElement installationDateField = driver.findElement(By.id("installationdate"));
        installationDateField.sendKeys("5/1/2022");

        // Clear the Budgeted field and Enter '1500' in Budgeted field
        WebElement budgetedField = driver.findElement(By.id("budgeted"));
        budgetedField.clear();
        budgetedField.sendKeys("1500");

        // Clear the Purchase Price field and Enter '8500' in Purchase Price field
        WebElement purchasePriceField = driver.findElement(By.id("purchaseprice"));
        purchasePriceField.clear();
        purchasePriceField.sendKeys("8500");

        // Clear the Replacement Cost field and Enter '1500' in Replacement Cost field
        WebElement replacementCostField = driver.findElement(By.id("replacementcost"));
        replacementCostField.clear();
        replacementCostField.sendKeys("1500");

        // Click on Save Image
        WebElement saveImage = driver.findElement(By.id("img_save"));
        saveImage.click();

        // Click on Change Status
        WebElement changeStatusBtn = driver.findElement(By.id("changeStatusBtn"));
        changeStatusBtn.click();

        // Select value "Operating" from New Status combobox
        WebElement newStatusComboBox = driver.findElement(By.id("newStatus"));
        newStatusComboBox.sendKeys("Operating");

        // Click on OK button
        WebElement okBtn = driver.findElement(By.id("okButton"));
        okBtn.click();

        // Click on "List View"
        WebElement listView = driver.findElement(By.xpath("//a[contains(text(), 'List View')]"));
        listView.click();

        // Enter "ASSETTEST1" in search field
        WebElement searchField = driver.findElement(By.id("search"));
        searchField.sendKeys("ASSETTEST1");

        // Verify "ASSETTEST1" value is present in search results
        // Write your verification logic here

        // Close the browser
        driver.quit();
    }
}