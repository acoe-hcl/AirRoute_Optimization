import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHRMEmployeeManagementTest {
    WebDriver driver;
    String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    String adminUsername = "Admin";
    String adminPassword = "admin123";
    String empFirstName = "John";
    String empLastName = "Doe";
    String empID = "";
    String empUsername = "john.doe" + System.currentTimeMillis();
    String empPassword = "Password@123";
    
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void navigateToBaseUrl() {
        driver.get(baseUrl);
    }

    @Test(priority = 1)
    public void testValidAdminLogin() {
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        username.sendKeys(adminUsername);
        password.sendKeys(adminPassword);
        loginBtn.click();
        
        WebElement dashboardHeader = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard is not displayed after login.");
    }
    
    @Test(priority = 2, dependsOnMethods = "testValidAdminLogin")
    public void testAddNewEmployee() {
        // Go to PIM > Add Employee
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[contains(@href,'pim/addEmployee')]")).click();

        driver.findElement(By.name("firstName")).sendKeys(empFirstName);
        driver.findElement(By.name("lastName")).sendKeys(empLastName);
        empID = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input")).getAttribute("value");

        driver.findElement(By.xpath("//span[contains(text(),'Create Login Details')]")).click(); // Enable the toggle
        driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div/input")).sendKeys(empUsername);
        driver.findElement(By.xpath("//label[text()='Password']/../following-sibling::div/input")).sendKeys(empPassword);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input")).sendKeys(empPassword);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        // Verify employee was created (redirect to personal details page)
        WebElement personalDetailsHeader = driver.findElement(By.xpath("//h6[text()='Personal Details']"));
        Assert.assertTrue(personalDetailsHeader.isDisplayed(), "Employee Personal Details page not displayed.");
    }
    
    @Test(priority = 3, dependsOnMethods = "testAddNewEmployee")
    public void testVerifyEmployeeCreation() {
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[contains(@href,'pim/viewEmployeeList')]")).click();
        
        WebElement empIdSearch = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
        empIdSearch.clear();
        empIdSearch.sendKeys(empID);
        
        driver.findElement(By.xpath("//button[@type='submit']")).click(); // Search button
        
        WebElement empRow = driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[contains(text(),'" + empID + "')]"));
        Assert.assertTrue(empRow.isDisplayed(), "Employee row not found after creation.");
        
        WebElement nameCell = driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[contains(text(),'" + empID + "')]/../../div[3]"));
        Assert.assertTrue(nameCell.getText().contains(empFirstName + " " + empLastName), "Employee name not matching.");
    }
    
    @Test(priority = 4, dependsOnMethods = "testVerifyEmployeeCreation")
    public void testDeleteEmployee() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[contains(@href,'pim/viewEmployeeList')]")).click();
        
        WebElement empIdSearch = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
        empIdSearch.clear();
        empIdSearch.sendKeys(empID);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        // Click delete icon
        WebElement deleteIcon = driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[contains(text(),'" + empID + "')]/../../div[last()]//button[1]"));
        deleteIcon.click();
        
        // Confirm deletion
        WebElement confirmBtn = driver.findElement(By.xpath("//button[text()='Yes, Delete']"));
        confirmBtn.click();
        Thread.sleep(1000); // Wait for deletion to complete

        // Assert notification
        WebElement successToast = driver.findElement(By.xpath("//p[contains(text(),'Successfully Deleted')]"));
        Assert.assertTrue(successToast.isDisplayed(), "Employee deletion confirm message not shown.");
    }
    
    @Test(priority = 5, dependsOnMethods = "testDeleteEmployee")
    public void testVerifyEmployeeDeletion() {
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        username.sendKeys(adminUsername);
        password.sendKeys(adminPassword);
        loginBtn.click();

        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[contains(@href,'pim/viewEmployeeList')]")).click();
        
        WebElement empIdSearch = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
        empIdSearch.clear();
        empIdSearch.sendKeys(empID);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Verify no records found
        WebElement noRecords = driver.findElement(By.xpath("//span[text()='No Records Found']"));
        Assert.assertTrue(noRecords.isDisplayed(), "Employee record still found after deletion.");
    }
    
    @Test(priority = 6)
    public void testInvalidLoginAttempt() {
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        username.sendKeys("invalidUser");
        password.sendKeys("invalidPass");
        loginBtn.click();

        WebElement errorMessage = driver.findElement(By.xpath("//p[contains(@class,'oxd-alert-content-text')]"));
        Assert.assertTrue(errorMessage.isDisplayed(), "No error message for invalid login.");
        Assert.assertEquals(errorMessage.getText(), "Invalid credentials", "Error message does not match expected.");
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
