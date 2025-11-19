import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHRMEmployeeManagementTest {
    private WebDriver driver;
    private final String baseUrl = "https://orangehrm-demo-6x.orangehrmlive.com/"; // Change to actual URL if different

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void login(String username, String password) {
        driver.get(baseUrl);
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void logout() {
        driver.findElement(By.id("welcome")).click();
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test(priority = 1)
    public void testAdminLogin() {
        login("admin_user", "admin_pass");
        Assert.assertTrue(driver.findElement(By.id("menu_dashboard_index")).isDisplayed(), 
            "Dashboard is not displayed after login.");
    }

    @Test(priority = 2)
    public void testAddEmployeeWithLoginDetails() {
        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        driver.findElement(By.id("menu_pim_addEmployee")).click();
        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        WebElement empId = driver.findElement(By.id("employeeId"));
        firstName.clear();
        firstName.sendKeys("John");
        lastName.clear();
        lastName.sendKeys("Doe");
        empId.clear();
        empId.sendKeys("12345");
        driver.findElement(By.id("chkLogin")).click(); // Enable login details
        driver.findElement(By.id("user_name")).sendKeys("johndoe");
        driver.findElement(By.id("user_password")).sendKeys("Emp@1234");
        driver.findElement(By.id("re_password")).sendKeys("Emp@1234");
        driver.findElement(By.id("btnSave")).click();
        WebElement personalDetails = driver.findElement(By.id("personal_txtEmpFirstName"));
        Assert.assertEquals(personalDetails.getAttribute("value"), "John", "First name mismatch on personal details page.");
    }

    @Test(priority = 3)
    public void testVerifyEmployeeCreation() {
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        WebElement searchEmpId = driver.findElement(By.id("empsearch_id"));
        searchEmpId.clear();
        searchEmpId.sendKeys("12345");
        driver.findElement(By.id("searchBtn")).click();
        WebElement employeeCell = driver.findElement(By.xpath("//a[text()='12345']/../following-sibling::td/a"));
        WebElement nameCell = driver.findElement(By.xpath("//a[text()='12345']/../../td[3]/a"));
        Assert.assertEquals(employeeCell.getText(), "12345", "Employee ID not found in search results.");
        Assert.assertEquals(nameCell.getText(), "John Doe", "Employee name not found in search results.");
    }

    @Test(priority = 4)
    public void testDeleteEmployee() {
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        WebElement searchEmpId = driver.findElement(By.id("empsearch_id"));
        searchEmpId.clear();
        searchEmpId.sendKeys("12345");
        driver.findElement(By.id("searchBtn")).click();
        WebElement checkbox = driver.findElement(By.xpath("//a[text()='12345']/../preceding-sibling::td/input[@type='checkbox']"));
        checkbox.click();
        driver.findElement(By.id("btnDelete")).click();
        driver.findElement(By.id("dialogDeleteBtn")).click();
        // Wait for list update (Add explicit wait as needed)
        Thread.sleep(2000);
        WebElement noRecords = driver.findElement(By.xpath("//*[contains(text(), 'No Records Found')]"));
        Assert.assertTrue(noRecords.isDisplayed(), "Employee was not deleted successfully.");
    }

    @Test(priority = 5)
    public void testVerifyEmployeeDeletion() throws InterruptedException {
        logout();
        login("admin_user", "admin_pass");
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        WebElement searchEmpId = driver.findElement(By.id("empsearch_id"));
        searchEmpId.clear();
        searchEmpId.sendKeys("12345");
        driver.findElement(By.id("searchBtn")).click();
        // Wait for list update (Add explicit wait as needed)
        Thread.sleep(2000);
        WebElement noRecords = driver.findElement(By.xpath("//*[contains(text(), 'No Records Found')]"));
        Assert.assertTrue(noRecords.isDisplayed(), "Deleted employee record still found.");
    }

    @Test(priority = 6)
    public void testInvalidLoginAttempt() {
        logout();
        login("invalid_user", "invalid_pass");
        WebElement errorMsg = driver.findElement(By.id("spanMessage"));
        Assert.assertEquals(errorMsg.getText(), "Invalid credentials", "Error message not displayed for invalid login.");
    }
}
