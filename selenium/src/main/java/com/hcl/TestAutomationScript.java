import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Launch the application
        driver.get("http://iwmtar52064254:9080/maximo/webclient/login/login.jsp");

        // Find and enter the username field
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("maxadmin");

        // Find and enter the password field
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("maxadmin");

        // Click on Sign In button
        WebElement signInButton = driver.findElement(By.id("signInButton"));
        signInButton.click();

        // Mouse hover on Work Order Application
        Actions actions = new Actions(driver);
        WebElement workOrderApp = driver.findElement(By.linkText("Work Order"));
        actions.moveToElement(workOrderApp).build().perform();

        // Click on Work Order Tracking module
        WebElement workOrderTracking = driver.findElement(By.partialLinkText("Work Order Tracking"));
        workOrderTracking.click();

        // Click on New Work Order Image
        WebElement newWorkOrder = driver.findElement(By.cssSelector("img[src*='newworkorder']"));
        newWorkOrder.click();

        // Enter "Maintenance of Pump" in Work Order description
        WebElement workOrderDescription = driver.findElement(By.id("description"));
        workOrderDescription.sendKeys("Maintenance of Pump");

        // Enter "11210" in Asset field
        WebElement assetField = driver.findElement(By.id("assetnum"));
        assetField.sendKeys("11210");

        // Click on Select Value icon of Work Type
        WebElement selectWorkTypeIcon = driver.findElement(By.id("select2-worktype_imageSpan"));
        selectWorkTypeIcon.click();
        
        // Click on CM Label
        WebElement cmLabel = driver.findElement(By.xpath("//div[contains(text(),'CM Label')]"));
        cmLabel.click();

        // Click on Save Image
        WebElement saveButton = driver.findElement(By.id("save"));
        saveButton.click();

        // Enter "JP11430A" in Job Plan field
        WebElement jobPlanField = driver.findElement(By.id("jpnum"));
        jobPlanField.sendKeys("JP11430A");

        // Click on Save Image
        saveButton.click();

        // Click on Plan tab
        WebElement planTab = driver.findElement(By.id("tabdetails_plan"));
        planTab.click();

        // Click on Material tab
        WebElement materialTab = driver.findElement(By.id("tabdetails_material"));
        materialTab.click();

        // Click on Select Assert Spare Parts button
        WebElement selectSparePartsButton = driver.findElement(By.id("select1"));
        selectSparePartsButton.click();

        // Select the checkbox of Items
        WebElement itemsCheckbox = driver.findElement(By.xpath("//input[@name='selectrow[0]']"));
        itemsCheckbox.click();

        // Click Ok Button
        WebElement okButton = driver.findElement(By.id("OK"));
        okButton.click();

        // Click on Details icon
        WebElement detailsIcon = driver.findElement(By.id("viewDetailsImage"));
        detailsIcon.click();

        // Clear the Quantity field and Enter "2" in Quantity field
        WebElement quantityField = driver.findElement(By.id("qty1"));
        quantityField.clear();
        quantityField.sendKeys("2");

        // Clear the Unit cost field and Enter "100" in Unit cost field
        WebElement unitCostField = driver.findElement(By.id("unitcost1"));
        unitCostField.clear();
        unitCostField.sendKeys("100");

        // Enter "CENTRAL" in Storeroom field
        WebElement storeroomField = driver.findElement(By.id("storeloc1"));
        storeroomField.sendKeys("CENTRAL");

        // Click on Save Image
        saveButton.click();

        // Click on Detail Menu icon and Click on Go To option
        WebElement detailMenuIcon = driver.findElement(By.id("detailmenu"));
        detailMenuIcon.click();

        WebElement goToOption = driver.findElement(By.xpath("//a[contains(text(),'Go To...')]"));
        goToOption.click();

        // Click on Inventory
        WebElement inventoryOption = driver.findElement(By.xpath("//a[contains(text(),'Inventory')]"));
        inventoryOption.click();

        // Get the value from Current Balance field
        WebElement currentBalanceField = driver.findElement(By.id("current_balance"));
        String currentBalance = currentBalanceField.getText();

        // Click on Return button
        WebElement returnButton = driver.findElement(By.id("returnbutton"));
        returnButton.click();

        // Click on Change Status
        WebElement changeStatusButton = driver.findElement(By.id("changestatusbutton"));
        changeStatusButton.click();

        // Select value "Approved" from New Status combobox
        WebElement newStatusCombobox = driver.findElement(By.id("newstatus"));
        newStatusCombobox.sendKeys("Approved");

        // Click on OK button
        WebElement okButtonStatus = driver.findElement(By.id("status_ok"));
        okButtonStatus.click();

        // Verify that Status field has value APPR
        WebElement statusField = driver.findElement(By.id("status"));
        String statusValue = statusField.getText();
        if (statusValue.equals("APPR")) {
            System.out.println("Status field validation successful");
        } else {
            System.out.println("Status field validation failed");
        }

        // Click on Actuals tab
        WebElement actualsTab = driver.findElement(By.id("tabdetails_actuals"));
        actualsTab.click();

        // Click on Material tab
        materialTab.click();

        // Click on Select Reserved Items button
        selectSparePartsButton.click();

        // Select the checkbox of Items
        itemsCheckbox.click();

        // Click Ok Button
        okButton.click();

        // Clear the Quantity field and Enter '1' in Quantity field
        quantityField.clear();
        quantityField.sendKeys("1");

        // Click on Save Image
        saveButton.click();

        // Click on Detail Menu icon and Click on Go To option
        detailMenuIcon.click();
        goToOption.click();

        // Click on Inventory
        inventoryOption.click();

        // Get the value from Current Balance field
        currentBalance = currentBalanceField.getText();

        // Click on Return button
        returnButton.click();

        // Click on Select Reserved Items button
        selectSparePartsButton.click();

        // Select the checkbox of Items
        itemsCheckbox.click();

        // Click Ok Button
        okButton.click();

        // Click on Select Value icon of Transaction Type
        WebElement transactionTypeSelect = driver.findElement(By.id("select2-transtype_imageSpan"));
        transactionTypeSelect.click();

        // Click on RETURN option
        WebElement returnOption = driver.findElement(By.xpath("//ul[@id='select2-transtype-results']//li[contains(text(),'RETURN')]"));
        returnOption.click();

        // Click on Save Image
        saveButton.click();

        // Click on Detail Menu icon and Click on Go To option
        detailMenuIcon.click();
        goToOption.click();

        // Click on Inventory
        inventoryOption.click();

        // Get the value from Current Balance field
        currentBalance = currentBalanceField.getText();

        // Click on RETURN option
        returnOption.click();

        // Click on Change Status
        changeStatusButton.click();

        // Select value "Completed" from New Status combobox
        newStatusCombobox.sendKeys("Completed");

        // Click on OK button
        okButtonStatus.click();

        // Verify that Status field has value COMP
        statusValue = statusField.getText();
        if (statusValue.equals("COMP")) {
            System.out.println("Status field validation successful");
        } else {
            System.out.println("Status field validation failed");
        }

        // Click on Change Status
        changeStatusButton.click();

        // Select value "Closed" from New Status combobox
        newStatusCombobox.sendKeys("Closed");

        // Enter "Workorder Completed" in Memo field
        WebElement memoField = driver.findElement(By.id("memo"));
        memoField.sendKeys("Workorder Completed");

        // Click on OK button
        okButtonStatus.click();

        // Verify that Status field has value CLOSE
        statusValue = statusField.getText();
        if (statusValue.equals("CLOSE")) {
            System.out.println("Status field validation successful");
        } else {
            System.out.println("Status field validation failed");
        }

        // Close the browser
        driver.quit();
    }
}