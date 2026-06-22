
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrangeHRMTest {
    public static void main(String[] args) throws InterruptedException {
        // Set WebDriver path and initialize
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        // 1.1 Launch OrangeHRM application
        driver.get("https://orangehrm-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // 1.2 Enter username as "admin_user"
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("admin_user");

        // 1.3 Enter password as "admin_pass"
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("admin_pass");

        // 1.4 Click on the Login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        Thread.sleep(3000);

        // 1.5 Verify successful login to the dashboard
        WebElement dashboardHeader = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        assert dashboardHeader.isDisplayed();

        // 2.1 Navigate to PIM menu
        WebElement pimMenu = driver.findElement(By.xpath("//span[text()='PIM']"));
        pimMenu.click();
        Thread.sleep(2000);

        // 2.2 Click on "Add Employee"
        WebElement addEmployeeBtn = driver.findElement(By.xpath("//a[text()='Add Employee']"));
        addEmployeeBtn.click();
        Thread.sleep(2000);

        // 2.3 Enter "John" in the First Name field
        WebElement firstName = driver.findElement(By.name("firstName"));
        firstName.sendKeys("John");

        // 2.4 Enter "Doe" in the Last Name field
        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.sendKeys("Doe");

        // 2.5 Enter "12345" in the Employee ID field
        WebElement empId = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
        empId.clear();
        empId.sendKeys("12345");

        // 2.6 Enable "Create login details" toggle
        WebElement loginDetailsToggle = driver.findElement(By.xpath("//span[text()='Create Login Details']/following-sibling::div//input[@type='checkbox']"));
        if (!loginDetailsToggle.isSelected()) {
            loginDetailsToggle.click();
        }
        Thread.sleep(1000);

        // 2.7 Enter "johndoe" as username
        WebElement newUsername = driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div/input"));
        newUsername.sendKeys("johndoe");

        // 2.8 Enter "Emp@1234" as password
        WebElement newPassword = driver.findElement(By.xpath("//label[text()='Password']/../following-sibling::div/input"));
        newPassword.sendKeys("Emp@1234");

        // 2.9 Confirm password by entering "Emp@1234"
        WebElement confirmPassword = driver.findElement(By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input"));
        confirmPassword.sendKeys("Emp@1234");

        // 2.10 Click on "Save"
        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();
        Thread.sleep(3000);

        // 2.11 Verify employee is created and redirected to personal details page
        WebElement personalDetailsHeader = driver.findElement(By.xpath("//h6[text()='Personal Details']"));
        assert personalDetailsHeader.isDisplayed();

        // 3.1 Navigate to "Employee List" tab under PIM
        WebElement employeeListTab = driver.findElement(By.xpath("//a[text()='Employee List']"));
        employeeListTab.click();
        Thread.sleep(2000);

        // 3.2 Enter "12345" in the employee ID search field
        WebElement searchEmpId = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
        searchEmpId.clear();
        searchEmpId.sendKeys("12345");

        // 3.3 Click on "Search"
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(2000);

        // 3.4 Confirm that "John Doe" and employee ID "12345" are displayed in the search results
        WebElement employeeResult = driver.findElement(By.xpath("//div[contains(text(),'John')]//following-sibling::div[contains(text(),'Doe')]//following-sibling::div[contains(text(),'12345')]"));
        assert employeeResult.isDisplayed();

        // 4.1 Locate employee with ID "12345"
        WebElement deleteButton = driver.findElement(By.xpath("//div[text()='12345']/ancestor::div[contains(@class,'employee-row')]//button[@title='Delete']"));
        deleteButton.click();
        Thread.sleep(1000);

        // 4.3 Confirm deletion in the popup/modal
        WebElement confirmDeleteBtn = driver.findElement(By.xpath("//button[text()='Yes, Delete']"));
        confirmDeleteBtn.click();
        Thread.sleep(2000);

        // 4.4 Verify the employee record is removed from the list
        searchEmpId.clear();
        searchEmpId.sendKeys("12345");
        searchButton.click();
        Thread.sleep(2000);
        WebElement noRecordsMsg = driver.findElement(By.xpath("//*[contains(text(),'No Records Found')]"));
        assert noRecordsMsg.isDisplayed();

        // 5.1 Logout from OrangeHRM
        WebElement profileDropdown = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
        profileDropdown.click();
        Thread.sleep(1000);
        WebElement logoutBtn = driver.findElement(By.xpath("//a[text()='Logout']"));
        logoutBtn.click();
        Thread.sleep(3000);

        // 5.2 Login again using credentials "admin_user" and "admin_pass"
        usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("admin_user");
        passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("admin_pass");
        loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        Thread.sleep(3000);

        // 5.3 Navigate to "Employee List" tab
        pimMenu = driver.findElement(By.xpath("//span[text()='PIM']"));
        pimMenu.click();
        Thread.sleep(1000);
        employeeListTab = driver.findElement(By.xpath("//a[text()='Employee List']"));
        employeeListTab.click();
        Thread.sleep(2000);

        // 5.4 Enter "12345" in the employee ID search field
        searchEmpId = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
        searchEmpId.clear();
        searchEmpId.sendKeys("12345");

        // 5.5 Click "Search"
        searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(2000);

        // 5.6 Verify that "No Records Found" is displayed, and employee details are not present
        noRecordsMsg = driver.findElement(By.xpath("//*[contains(text(),'No Records Found')]"));
        assert noRecordsMsg.isDisplayed();

        // 6.1 Logout from OrangeHRM
        profileDropdown = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
        profileDropdown.click();
        Thread.sleep(1000);
        logoutBtn = driver.findElement(By.xpath("//a[text()='Logout']"));
        logoutBtn.click();
        Thread.sleep(3000);

        // 6.2 Enter username as "invalid_user"
        usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("invalid_user");

        // 6.3 Enter password as "invalid_pass"
        passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("invalid_pass");

        // 6.4 Click on the Login button
        loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        Thread.sleep(2000);

        // 6.5 Verify error message for login failure
        WebElement invalidCredMsg = driver.findElement(By.xpath("//*[contains(text(),'Invalid credentials')]"));
        assert invalidCredMsg.isDisplayed();

        driver.quit();
    }
}
