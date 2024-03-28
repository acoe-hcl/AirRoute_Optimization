import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestAutomationScript {
    
    public static void main(String[] args) {
        // Set the webdriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        
        // Launch the browser
        WebDriver driver = new ChromeDriver();
        
        // Launch the application
        driver.get("http://iwmtar52064254:9080/maximo/webclient/login/login.jsp");
        
        // Enter "maxadmin" username field
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("maxadmin");
        
        // Enter "maxadmin" password field
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("maxadmin");
        
        // Click on Sign In button
        WebElement signInButton = driver.findElement(By.xpath("//input[@value='Sign In']"));
        signInButton.click();
        
        // Mouse hover on Work Order Application
        WebElement workOrderApp = driver.findElement(By.id("workOrderApp"));
        Actions actions = new Actions(driver);
        actions.moveToElement(workOrderApp).perform();
        
        // Click on Work Order Tracking module
        WebElement workOrderTrackingModule = driver.findElement(By.xpath("//a[contains(text(), 'Work Order Tracking')]"));
        workOrderTrackingModule.click();
        
        // Click on New Work Order Image
        WebElement newWorkOrderImage = driver.findElement(By.xpath("//img[@alt='Add New Work Order']"));
        newWorkOrderImage.click();
        
        // Enter "Maintenance of Pump" in Work Order description
        WebElement workOrderDescription = driver.findElement(By.id("description"));
        workOrderDescription.sendKeys("Maintenance of Pump");
        
        // Enter "11210" in Asset field
        WebElement assetField = driver.findElement(By.id("assetnum"));
        assetField.sendKeys("11210");
        
        // Click on Select Value icon of Work Type
        WebElement workTypeSelectValue = driver.findElement(By.xpath("//img[@alt='Select Value']"));
        workTypeSelectValue.click();
        
        // Click on CM Label
        WebElement cmLabel = driver.findElement(By.xpath("//td[@id='lookup_page1_tdrow_[C:0]_txt-tb[R:0]']/span/a[contains(text(), 'CM')]"));
        cmLabel.click();
        
        // Click on Save Image
        WebElement saveImage = driver.findElement(By.xpath("//img[@alt='Save Record']"));
        saveImage.click();
        
        // Enter "JP11430A" in Job Plan field
        WebElement jobPlanField = driver.findElement(By.id("jpnum"));
        jobPlanField.sendKeys("JP11430A");
        
        // Click on Save Image
        saveImage.click();
        
        // Click on Plan tab
        WebElement planTab = driver.findElement(By.xpath("//a[@title='Plan']"));
        planTab.click();
        
        // Click on Material tab
        WebElement materialTab = driver.findElement(By.xpath("//a[@title='Material']"));
        materialTab.click();
        
        // Click on Select Assert Spare Parts button
        WebElement selectAssertSparePartsButton = driver.findElement(By.xpath("//button[contains(text(), 'Select Assert Spare Parts')]"));
        selectAssertSparePartsButton.click();
        
        // Select the checkbox of Items
        WebElement itemsCheckbox = driver.findElement(By.xpath("//table[@id='mx33__mt33_._66151']/tbody/tr[2]/td[3]/input[@type='checkbox']"));
        itemsCheckbox.click();
        
        // Click Ok Button
        WebElement okButton = driver.findElement(By.xpath("//button[@id='select_items_bottom']"));
        okButton.click();
        
        // Click on Details icon, Clear the Quantity field.
        WebElement detailsIcon = driver.findElement(By.xpath("//img[@alt='Show Details']/.."));
        detailsIcon.click();
        
        WebElement quantityField = driver.findElement(By.id("quantity"));
        quantityField.clear();
        
        // Enter "2" in Quantity field
        quantityField.sendKeys("2");
        
        // Clear the Unit cost field and Enter "100" in Unit cost field.
        WebElement unitCostField = driver.findElement(By.id("unitcost"));
        unitCostField.clear();
        unitCostField.sendKeys("100");
        
        // Enter "CENTRAL" in Storeroom field.
        WebElement storeroomField = driver.findElement(By.id("storeloc"));
        storeroomField.sendKeys("CENTRAL");
        
        // Click on Save Image
        saveImage.click();
        
        // Click on Detail Menu icon and Click on Go To option
        WebElement detailMenuIcon = driver.findElement(By.xpath("//img[@alt='Detail Menu']"));
        detailMenuIcon.click();
        
        WebElement goToOption = driver.findElement(By.xpath("//a[contains(text(), 'Go To...')]"));
        goToOption.click();
        
        // Click on Inventory
        WebElement inventoryOption = driver.findElement(By.xpath("//a[contains(text(), 'Inventory')]"));
        inventoryOption.click();
        
        // Get the value from Current Balance field
        WebElement currentBalanceField = driver.findElement(By.id("currentbalance"));
        String currentBalanceValue = currentBalanceField.getText();
        
        // Click on Return button
        WebElement returnButton = driver.findElement(By.xpath("//button[contains(text(), 'Return')]"));
        returnButton.click();
        
        // Click on Change Status
        WebElement changeStatusButton = driver.findElement(By.xpath("//button[contains(text(), 'Change Status')]"));
        changeStatusButton.click();
        
        // Select value "Approved" from New Status combobox
        WebElement newStatusCombobox = driver.findElement(By.xpath("//select[@id='new_status']"));
        newStatusCombobox.sendKeys("Approved");
        
        // Click on OK button
        WebElement okButton2 = driver.findElement(By.xpath("//button[@id='ok_status_change']"));
        okButton2.click();
        
        // Verify that Status field has value APPR
        WebElement statusField = driver.findElement(By.id("status"));
        String statusValue = statusField.getText();
        if (statusValue.equals("APPR")) {
            System.out.println("Status field has value APPR");
        } else {
            System.out.println("Status field does not have value APPR");
        }
        
        // Click on Actuals tab
        WebElement actualsTab = driver.findElement(By.xpath("//a[@title='Actuals']"));
        actualsTab.click();
        
        // Click on Material tab
        materialTab.click();
        
        // Click on Select Reserved Items button
        WebElement selectReservedItemsButton = driver.findElement(By.xpath("//button[contains(text(), 'Select Reserved Items')]"));
        selectReservedItemsButton.click();
        
        // Select the checkbox of Items
        itemsCheckbox.click();
        
        // Click on OK Button
        okButton.click();
        
        // Clear the Quantity field and Enter '1' in Quantity field
        quantityField.clear();
        quantityField.sendKeys("1");
        
        // Click on Save Image
        saveImage.click();
        
        // Click on Detail Menu icon and Click on Go To option
        detailMenuIcon.click();
        goToOption.click();
        
        // Click on Inventory
        inventoryOption.click();
        
        // Get the value from Current Balance field
        String currentBalanceValue2 = currentBalanceField.getText();
        
        // Click on Return button
        returnButton.click();
        
        // Click on Select Reserved Items button
        selectReservedItemsButton.click();
        
        // Select the checkbox of Items
        itemsCheckbox.click();
        
        // Click Ok Button
        okButton.click();
        
        // Click on Select Value icon of Transaction Type
        WebElement transactionTypeSelectValue = driver.findElement(By.xpath("//img[@alt='Select Value']"));
        transactionTypeSelectValue .click();
        
        // Click on RETURN option
        WebElement returnOption = driver.findElement(By.xpath("//td[@id='lookup_page2_tdrow_[C:1]_txt-tb[R:7]']/span/a[contains(text(), 'RETURN')]"));
        returnOption.click();
        
        // Click on Save Image
        saveImage.click();
        
        // Click on Detail Menu icon and Click on Go To option
        detailMenuIcon.click();
        goToOption.click();
        
        // Click on Inventory
        inventoryOption.click();
        
        // Get the value from Current Balance field
        String currentBalanceValue3 = currentBalanceField.getText();
        
        // Click on RETURN option
        returnOption.click();
        
        // Click on Change Status
        changeStatusButton.click();
        
        // Select value "Completed" from New Status combobox
        newStatusCombobox.sendKeys("Completed");
        
        // Click on OK button
        okButton2.click();
        
        // Verify that Status field has value COMP
        String statusValue2 = statusField.getText();
        if (statusValue2.equals("COMP")) {
            System.out.println("Status field has value COMP");
        } else {
            System.out.println("Status field does not have value COMP");
        }
        
        // Click on Change Status
        changeStatusButton.click();
        
        // Select value "Closed" from New Status combobox
        newStatusCombobox.sendKeys("Closed");
        
        // Enter "Workorder Completed" in Memo field
        WebElement memoField = driver.findElement(By.id("memo"));
        memoField.sendKeys("Workorder Completed");
        
        // Click on OK button
        okButton2.click();
        
        // Verify that Status field has value CLOSE
        String statusValue3 = statusField.getText();
        if (statusValue3.equals("CLOSE")) {
            System.out.println("Status field has value CLOSE");
        } else {
            System.out.println("Status field does not have value CLOSE");
        }
        
        // Close the browser
        driver.quit();
    }
}