## 1. `pom.xml` (Maven Configuration)
**Location:** `pom.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.orangehrm</groupId>
    <artifactId>employee-management-automation</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <dependencies>
        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.14.0</version>
        </dependency>
        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.8.0</version>
            <scope>test</scope>
        </dependency>
        <!-- WebDriverManager -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.6.2</version>
        </dependency>
        <!-- Log4j (Logging) -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.23.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.23.0</version>
        </dependency>
        <!-- ExtentReports (Reporting) -->
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>5.1.1</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <!-- Maven Surefire Plugin for TestNG -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## 2. `testng.xml` (TestNG Suite Configuration)
**Location:** `testng.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="OrangeHRM Employee Management Suite" parallel="tests" thread-count="3">
    <listeners>
        <listener class-name="com.test.utils.ExtentReportListener"/>
    </listeners>
    <test name="EmployeeManagementTests">
        <classes>
            <class name="com.test.tests.LoginTests"/>
            <class name="com.test.tests.EmployeeManagementTests"/>
        </classes>
    </test>
</suite>
```

## 3. `config.properties` (Test Configuration)
**Location:** `config.properties`
```properties
# OrangeHRM Application Configuration
base.url=https://opensource-demo.orangehrmlive.com/
browser=chrome

# Administrator Credentials
admin.username=Admin
admin.password=admin123
admin.invalid.username=InvalidAdmin
admin.invalid.password=wrongPass
admin.incorrect.password=wrongPassword

# Employee Test Data
employee.firstName=John
employee.lastName=Doe
employee.middleName=R
employee.employeeId=EMP12345
employee.search.name=John Doe
employee.search.invalidName=NonExisting Employee
employee.existing.employeeId=EMP10000  # Example existing employee

# Timeout Settings
explicit.wait=15

# Delete Employee Test Data
employee.deleteId=EMP12345

# Other configurations
employee.search.criteria=EMP10000
employee.delete.confirmText=Yes, Delete

# Unauthorized Access URLs
employee.add.url=https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee
employee.search.url=https://opensource-demo.orangehrmlive.com/index.php/pim/viewEmployeeList
employee.delete.url=https://opensource-demo.orangehrmlive.com/index.php/pim/deleteEmployee
admin.dashboard.url=https://opensource-demo.orangehrmlive.com/index.php/dashboard
```

## 4. `BasePage.java` (Base Page Object)
**Location:** `src/test/java/com/test/pages/BasePage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

/**
 * BasePage - common functionality for all page objects.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    /**
     * Constructor to initialize WebDriver and WebDriverWait.
     * @param driver WebDriver instance passed from test.
     * @param timeoutSeconds Timeout value in seconds from config.
     */
    public BasePage(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        this.logger = LogManager.getLogger(this.getClass());
    }

    /**
     * Wait for an element to be visible on page.
     * @param element WebElement to wait for.
     */
    public void waitForElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Element not visible: " + element.toString(), e);
            throw e;
        }
    }

    /**
     * Wait for element to be clickable.
     * @param element WebElement.
     */
    public void waitForElementClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            logger.error("Element not clickable: " + element.toString(), e);
            throw e;
        }
    }
}
```

## 5. `LoginPage.java` (Login Page Object)
**Location:** `src/test/java/com/test/pages/LoginPage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPage - Page Object for OrangeHRM Login page.
 */
public class LoginPage extends BasePage {

    // Locators - using @FindBy for PageFactory
    @FindBy(id = "txtUsername")
    private WebElement usernameInput;

    @FindBy(id = "txtPassword")
    private WebElement passwordInput;

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    @FindBy(id = "spanMessage")
    private WebElement loginErrorMessage;

    /**
     * Constructor initializes page elements using PageFactory.
     * @param driver WebDriver instance.
     * @param timeoutSeconds Timeout for explicit wait.
     */
    public LoginPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        PageFactory.initElements(driver, this);
    }

    /**
     * Enter username into login form.
     * @param username Username from test data.
     */
    public void enterUsername(String username) {
        waitForElementVisible(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    /**
     * Enter password into login form.
     * @param password Password from test data.
     */
    public void enterPassword(String password) {
        waitForElementVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /**
     * Click Login button.
     */
    public void clickLogin() {
        waitForElementClickable(loginButton);
        loginButton.click();
    }

    /**
     * Return error message text after failed login.
     * @return Error message text.
     */
    public String getLoginErrorMessage() {
        try {
            waitForElementVisible(loginErrorMessage);
            return loginErrorMessage.getText();
        } catch (Exception e) {
            logger.warn("Login error message not found.");
            return "";
        }
    }

    /**
     * Login action using username and password.
     * @param username Administrator username.
     * @param password Administrator password.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
```

## 6. `ConfigReader.java` (Configuration Utility)
**Location:** `src/test/java/com/test/utils/ConfigReader.java`
```java
package com.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader utility for reading config.properties file.
 */
public class ConfigReader {
    private static Properties properties;
    private static final String configPath = "config.properties";

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(configPath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    /**
     * Get string property value for given key.
     * @param key Property key.
     * @return Property value.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
```

## 7. `WebDriverManager.java` (WebDriver Utility)
**Location:** `src/test/java/com/test/utils/WebDriverManager.java`
```java
package com.test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * WebDriverManager utility for thread-safe WebDriver instantiation.
 */
public class WebDriverManagerUtil {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Returns the WebDriver instance for this thread.
     * @return WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Initializes WebDriver based on provided browser type.
     * @param browser Browser name ("chrome", "firefox", "edge").
     */
    public static void initDriver(String browser) {
        WebDriver wd;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                wd = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                wd = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                wd = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser specified: " + browser);
        }
        wd.manage().window().maximize();
        driver.set(wd);
    }

    /**
     * Close and cleanup WebDriver instance.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
```

## 8. `BaseTest.java` (Base Test Class)
**Location:** `src/test/java/com/test/tests/BaseTest.java`
```java
package com.test.tests;

import com.test.utils.ConfigReader;
import com.test.utils.WebDriverManagerUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BaseTest class for setup and teardown.
 */
public abstract class BaseTest {
    protected WebDriver driver;
    protected Logger logger;
    protected int explicitWaitSeconds;

    /**
     * BeforeMethod to initialize browser and setup driver.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        explicitWaitSeconds = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WebDriverManagerUtil.initDriver(ConfigReader.getProperty("browser"));
        driver = WebDriverManagerUtil.getDriver();
        logger = LogManager.getLogger(this.getClass());
        logger.info("Browser initialized: " + ConfigReader.getProperty("browser"));
    }

    /**
     * AfterMethod to quit driver.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Quitting browser.");
        WebDriverManagerUtil.quitDriver();
    }
}
```

## 9. `EmployeePage.java` (Employee Management Page Object)
**Location:** `src/test/java/com/test/pages/EmployeePage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * EmployeePage - page objects and actions for employee management.
 */
public class EmployeePage extends BasePage {

    // Locators for Employee Management
    @FindBy(id = "menu_pim_viewPimModule")
    private WebElement pimMenuLink;

    @FindBy(id = "menu_pim_addEmployee")
    private WebElement addEmployeeLink;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "middleName")
    private WebElement middleNameInput;

    @FindBy(id = "employeeId")
    private WebElement employeeIdInput;

    @FindBy(id = "btnSave")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@class='message success fadable']")
    private WebElement successMessage;

    @FindBy(id = "menu_pim_viewEmployeeList")
    private WebElement employeeListLink;

    @FindBy(id = "empsearch_employee_name_empName")
    private WebElement employeeSearchNameInput;

    @FindBy(id = "empsearch_id")
    private WebElement employeeSearchIdInput;

    @FindBy(id = "searchBtn")
    private WebElement searchButton;

    @FindBy(xpath = "//table[@id='resultTable']//tbody/tr[1]/td[2]/a")
    private WebElement firstResultEmployeeLink;

    @FindBy(xpath = "//table[@id='resultTable']//tbody/tr")
    private WebElement noRecordsFoundRow;

    @FindBy(xpath = "//input[@id='btnDelete']")
    private WebElement deleteButton;

    @FindBy(id = "dialogDeleteBtn")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//div[@class='message success fadable']")
    private WebElement deleteSuccessMessage;

    /**
     * Constructor to initialize EmployeePage elements.
     * @param driver WebDriver instance.
     * @param timeoutSeconds Timeout value.
     */
    public EmployeePage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigate to Add Employee page.
     */
    public void goToAddEmployee() {
        waitForElementClickable(pimMenuLink);
        pimMenuLink.click();
        waitForElementClickable(addEmployeeLink);
        addEmployeeLink.click();
    }

    /**
     * Fill employee details in form.
     * @param firstName First name.
     * @param middleName Middle name.
     * @param lastName Last name.
     * @param employeeId Employee ID.
     */
    public void fillEmployeeDetails(String firstName, String middleName, String lastName, String employeeId) {
        waitForElementVisible(firstNameInput);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        waitForElementVisible(middleNameInput);
        middleNameInput.clear();
        middleNameInput.sendKeys(middleName);

        waitForElementVisible(lastNameInput);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        waitForElementVisible(employeeIdInput);
        employeeIdInput.clear();
        employeeIdInput.sendKeys(employeeId);
    }

    /**
     * Save employee record.
     */
    public void saveEmployee() {
        waitForElementClickable(saveButton);
        saveButton.click();
    }

    /**
     * Check for success message after employee creation.
     * @return Success message text.
     */
    public String getSuccessMessage() {
        try {
            waitForElementVisible(successMessage);
            return successMessage.getText();
        } catch (Exception e) {
            logger.warn("Success message not displayed.");
            return "";
        }
    }

    /**
     * Search for employee by name.
     * @param employeeName Employee name.
     */
    public void searchEmployeeByName(String employeeName) {
        waitForElementClickable(employeeListLink);
        employeeListLink.click();
        waitForElementVisible(employeeSearchNameInput);
        employeeSearchNameInput.clear();
        employeeSearchNameInput.sendKeys(employeeName);
        waitForElementClickable(searchButton);
        searchButton.click();
    }

    /**
     * Search for employee by employee ID.
     * @param employeeId Employee ID.
     */
    public void searchEmployeeById(String employeeId) {
        waitForElementClickable(employeeListLink);
        employeeListLink.click();
        waitForElementVisible(employeeSearchIdInput);
        employeeSearchIdInput.clear();
        employeeSearchIdInput.sendKeys(employeeId);
        waitForElementClickable(searchButton);
        searchButton.click();
    }

    /**
     * Return whether first employee link result is visible.
     * @return true if found, false otherwise.
     */
    public boolean isSearchResultDisplayed() {
        try {
            waitForElementVisible(firstResultEmployeeLink);
            return firstResultEmployeeLink.isDisplayed();
        } catch (Exception e) {
            logger.info("No employee search result displayed.");
            return false;
        }
    }

    /**
     * Return whether "No Records Found" is displayed in result table.
     * @return true if not found, false otherwise.
     */
    public boolean isNoRecordsFound() {
        try {
            waitForElementVisible(noRecordsFoundRow);
            String rowText = noRecordsFoundRow.getText();
            return rowText.contains("No Records Found");
        } catch (Exception e) {
            logger.info("No Records Found row not present.");
            return false;
        }
    }

    /**
     * Delete first employee in result list.
     */
    public void deleteFirstEmployee() {
        waitForElementClickable(deleteButton);
        deleteButton.click();
        waitForElementClickable(confirmDeleteButton);
        confirmDeleteButton.click();
    }

    /**
     * Get delete success message.
     * @return delete success message string.
     */
    public String getDeleteSuccessMessage() {
        try {
            waitForElementVisible(deleteSuccessMessage);
            return deleteSuccessMessage.getText();
        } catch (Exception e) {
            logger.warn("Delete success message not displayed.");
            return "";
        }
    }
}
```

## 10. `LoginTests.java` (Login Test Cases)
**Location:** `src/test/java/com/test/tests/LoginTests.java`
```java
package com.test.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.pages.LoginPage;
import com.test.utils.ConfigReader;

/**
 * LoginTests - OrangeHRM login scenarios (OH-6-TC-001 - OH-6-TC-004, OH-6-TC-012).
 */
public class LoginTests extends BaseTest {

    /**
     * OH-6-TC-001: Verify successful administrator login with valid credentials.
     */
    @Test(description = "Verify admin login with valid credentials")
    public void testValidAdminLogin() {
        driver.get(ConfigReader.getProperty("base.url"));
        LoginPage loginPage = new LoginPage(driver, explicitWaitSeconds);
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));

        // Assertion: redirected to dashboard (dashboard URL check)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"),
                "Administrator should be redirected to dashboard after successful login.");

        // Assert: no invalid login message
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "",
                "No invalid login error message should be displayed for valid login.");
    }

    /**
     * OH-6-TC-002: Verify system response for invalid login with incorrect password.
     */
    @Test(description = "Verify invalid login with correct username and incorrect password")
    public void testInvalidLoginIncorrectPassword() {
        driver.get(ConfigReader.getProperty("base.url"));
        LoginPage loginPage = new LoginPage(driver, explicitWaitSeconds);
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.incorrect.password"));

        String errorMsg = loginPage.getLoginErrorMessage();
        Assert.assertTrue(errorMsg.toLowerCase().contains("invalid credentials"),
                "Login failure message should be displayed for invalid password.");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(!currentUrl.contains("dashboard"),
                "User should NOT be redirected to dashboard after invalid login.");
    }

    /**
     * OH-6-TC-003: Verify system response for invalid login with incorrect username.
     */
    @Test(description = "Verify invalid login with incorrect username")
    public void testInvalidLoginIncorrectUsername() {
        driver.get(ConfigReader.getProperty("base.url"));
        LoginPage loginPage = new LoginPage(driver, explicitWaitSeconds);
        loginPage.login(ConfigReader.getProperty("admin.invalid.username"), ConfigReader.getProperty("admin.password"));

        String errorMsg = loginPage.getLoginErrorMessage();
        Assert.assertTrue(errorMsg.toLowerCase().contains("invalid credentials"),
                "Login failure message should be displayed for invalid username.");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"),
                "User should remain on login page after invalid login.");
    }

    /**
     * OH-6-TC-004: Verify system response for invalid login with both fields invalid.
     */
    @Test(description = "Verify invalid login with both username and password invalid")
    public void testInvalidLoginBothFields() {
        driver.get(ConfigReader.getProperty("base.url"));
        LoginPage loginPage = new LoginPage(driver, explicitWaitSeconds);
        loginPage.login(ConfigReader.getProperty("admin.invalid.username"), ConfigReader.getProperty("admin.invalid.password"));

        String errorMsg = loginPage.getLoginErrorMessage();
        Assert.assertTrue(errorMsg.toLowerCase().contains("invalid credentials"),
                "Invalid login response should be displayed for invalid username and password.");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"),
                "User should not be granted access to any protected page after invalid login.");
    }

    /**
     * OH-6-TC-012: Verify unauthorized access is prevented after failed login.
     */
    @Test(description = "Verify access is blocked after failed login attempt")
    public void testAccessAfterFailedLogin() {
        driver.get(ConfigReader.getProperty("base.url"));
        LoginPage loginPage = new LoginPage(driver, explicitWaitSeconds);

        // Attempt invalid login
        loginPage.login(ConfigReader.getProperty("admin.invalid.username"), ConfigReader.getProperty("admin.invalid.password"));

        String errorMsg = loginPage.getLoginErrorMessage();
        Assert.assertTrue(errorMsg.toLowerCase().contains("invalid credentials"),
                "Login attempt should fail for invalid credentials.");

        // Attempt to access protected admin dashboard URL directly
        driver.get(ConfigReader.getProperty("admin.dashboard.url"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"),
                "User should be redirected to login or shown access restriction for protected admin functionality.");
    }
}
```

## 11. `EmployeeManagementTests.java` (Employee Management Test Cases)
**Location:** `src/test/java/com/test/tests/EmployeeManagementTests.java`
```java
package com.test.tests;

import com.test.pages.EmployeePage;
import com.test.pages.LoginPage;
import com.test.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * EmployeeManagementTests - employee CRUD and access scenarios (OH-6-TC-005 to OH-6-TC-011, OH-6-TC-013 to OH-6-TC-015).
 */
public class EmployeeManagementTests extends BaseTest {
    private void loginAsAdmin() {
        driver.get(ConfigReader.getProperty("base.url"));
        LoginPage loginPage = new LoginPage(driver, explicitWaitSeconds);
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
    }

    /**
     * OH-6-TC-005: Verify employee creation by admin with valid details.
     */
    @Test(description = "Verify employee creation by administrator")
    public void testEmployeeCreation() {
        loginAsAdmin();
        EmployeePage employeePage = new EmployeePage(driver, explicitWaitSeconds);

        employeePage.goToAddEmployee();
        employeePage.fillEmployeeDetails(
                ConfigReader.getProperty("employee.firstName"),
                ConfigReader.getProperty("employee.middleName"),
                ConfigReader.getProperty("employee.lastName"),
                ConfigReader.getProperty("employee.employeeId")
        );
        employeePage.saveEmployee();

        String successMsg = employeePage.getSuccessMessage();
        Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                "Success message should be displayed after employee creation.");

        // Assert: new employee profile or data is shown
        Assert.assertTrue(driver.getCurrentUrl().contains("empNumber"),
                "After creation, employee profile/record page should be shown.");
    }

    /**
     * OH-6-TC-006: Verify newly created employee can be found through search.
     */
    @Test(description = "Verify employee search for newly created employee")
    public void testSearchNewEmployee() {
        loginAsAdmin();
        EmployeePage employeePage = new EmployeePage(driver, explicitWaitSeconds);

        employeePage.searchEmployeeById(ConfigReader.getProperty("employee.employeeId"));

        Assert.assertTrue(employeePage.isSearchResultDisplayed(),
                "Newly created employee should be found in search results.");

        // Optionally verify the result details
        Assert.assertFalse(employeePage.isNoRecordsFound(),
                "The search result should NOT indicate 'No Records Found'");
    }

    /**
     * OH-6-TC-007: Verify employee search returns correct result for existing employee.
     */
    @Test(description = "Verify employee search for existing employee")
    public void testSearchExistingEmployee() {
        loginAsAdmin();
        EmployeePage employeePage = new EmployeePage(driver, explicitWaitSeconds);

        employeePage.searchEmployeeById(ConfigReader.getProperty("employee.existing.employeeId"));

        Assert.assertTrue(employeePage.isSearchResultDisplayed(),
                "Existing employee should be found in search results.");
    }

    /**
     * OH-6-TC-008: Verify employee search when no matching employee exists.
     */
    @Test(description = "Verify employee search returns no results when employee not found")
    public void testSearchNoEmployeeFound() {
        loginAsAdmin();
        EmployeePage employeePage = new EmployeePage(driver, explicitWaitSeconds);

        employeePage.searchEmployeeByName(ConfigReader.getProperty("employee.search.invalidName"));

        Assert.assertTrue(employeePage.isNoRecordsFound(),
                "No employee record should be returned and 'No Records Found' indicated.");
    }

    /**
     * OH-6-TC-009: Verify admin can delete an existing employee record.
     */
    @Test(description = "Verify administrator can delete employee record")
    public void testDeleteEmployee() {
        loginAsAdmin();
        EmployeePage employeePage = new EmployeePage(driver, explicitWaitSeconds);

        // Search for employee
        employeePage.searchEmployeeById(ConfigReader.getProperty("employee.employeeId"));
        Assert.assertTrue(employeePage.isSearchResultDisplayed(),
                "Employee should be present before deletion.");

        // Delete employee
        employeePage.deleteFirstEmployee();

        String deleteMsg = employeePage.getDeleteSuccessMessage();
        Assert.assertTrue(deleteMsg.toLowerCase().contains("success"),
                "Delete success message should be displayed after employee deletion.");
    }

    /**
     * OH-6-TC-010: Verify deleted employee cannot be found in search results.
     */
    @Test(description = "Verify deleted employee cannot be found in search")
    public void testSearchDeletedEmployee() {
        loginAsAdmin();
        EmployeePage employeePage = new EmployeePage(driver, explicitWaitSeconds);

        employeePage.searchEmployeeById(ConfigReader.getProperty("employee.employeeId"));

        Assert.assertTrue(employeePage.isNoRecordsFound(),
                "Deleted employee record should not be returned in search results.");
    }

    /**
     * OH-6-TC-011: End-to-end employee lifecycle: login, create, search, delete.
     */
    @Test(description = "End-to-end employee lifecycle: login, create, search, delete")
    public void testEndToEndEmployeeLifecycle() {
        loginAsAdmin();
        EmployeePage employeePage = new EmployeePage(driver, explicitWaitSeconds);

        // Create employee
        employeePage.goToAddEmployee();
        employeePage.fillEmployeeDetails(
                ConfigReader.getProperty("employee.firstName"),
                ConfigReader.getProperty("employee.middleName"),
                ConfigReader.getProperty("employee.lastName"),
                ConfigReader.getProperty("employee.employeeId")
        );
        employeePage.saveEmployee();

        String successMsg = employeePage.getSuccessMessage();
        Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                "Employee creation success message should appear.");

        // Search for employee
        employeePage.searchEmployeeById(ConfigReader.getProperty("employee.employeeId"));
        Assert.assertTrue(employeePage.isSearchResultDisplayed(),
                "Newly created employee should be found in search results.");

        // Delete employee
        employeePage.deleteFirstEmployee();
        String deleteMsg = employeePage.getDeleteSuccessMessage();
        Assert.assertTrue(deleteMsg.toLowerCase().contains("success"),
                "Employee deletion success message should appear after deletion.");

        // Search for deleted employee
        employeePage.searchEmployeeById(ConfigReader.getProperty("employee.employeeId"));
        Assert.assertTrue(employeePage.isNoRecordsFound(),
                "Deleted employee should not be found in search results.");
    }

    /**
     * OH-6-TC-013: Verify employee creation requires admin authentication.
     */
    @Test(description = "Verify unauthenticated access to employee creation is blocked")
    public void testEmployeeCreationRequiresAuthentication() {
        // Navigate directly to employee creation page without login
        driver.get(ConfigReader.getProperty("employee.add.url"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"),
                "User should be redirected to login or shown access restriction when unauthenticated.");
    }

    /**
     * OH-6-TC-014: Verify employee search requires admin authentication.
     */
    @Test(description = "Verify unauthenticated access to employee search is blocked")
    public void testEmployeeSearchRequiresAuthentication() {
        driver.get(ConfigReader.getProperty("employee.search.url"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"),
                "User should be redirected to login or shown access restriction when unauthenticated.");
    }

    /**
     * OH-6-TC-015: Verify employee deletion requires admin authentication.
     */
    @Test(description = "Verify unauthenticated access to employee deletion is blocked")
    public void testEmployeeDeletionRequiresAuthentication() {
        driver.get(ConfigReader.getProperty("employee.delete.url"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"),
                "User should be redirected to login or shown access restriction when unauthenticated.");
    }
}
```

## 12. `ExtentReportListener.java` (TestNG Listener for Reporting)
**Location:** `src/test/java/com/test/utils/ExtentReportListener.java`
```java
package com.test.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * ExtentReportListener for TestNG reporting.
 */
public class ExtentReportListener implements ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/OrangeHRM-ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
```

## 13. `README.md` (Project Documentation)
**Location:** `README.md`
```markdown
# OrangeHRM Employee Management Automation

## Overview

This project automates comprehensive employee management scenarios for the OrangeHRM application using Java, Selenium WebDriver, TestNG, and Maven, applying Page Object Model with explicit waits, configuration-driven test data, cross-browser support, advanced reporting, and thread-safe design.

## Prerequisites

- Java 17+
- Maven 3.8+
- Chrome / Firefox / Edge browsers
- Access to OrangeHRM (default: [https://opensource-demo.orangehrmlive.com/](https://opensource-demo.orangehrmlive.com/))

## Setup Instructions

1. **Clone the Repository**  
   ```
   git clone <repo-url>
   cd employee-management-automation
   ```

2. **Update `config.properties`**  
   - Set credentials, employee test data, browser, and base URL.

3. **Run Maven Tests**  
   ```
   mvn clean test
   ```

4. **Test Reports**  
   - Find HTML reports under `test-output/OrangeHRM-ExtentReport.html`.

## Project Structure

```
project-root/
??? pom.xml
??? testng.xml
??? config.properties
??? src/
?   ??? test/
?       ??? java/
?           ??? com/test/pages/      (Page Objects)
?           ??? com/test/tests/      (TestNG Test Classes)
?           ??? com/test/utils/      (Utilities, Listeners)
```

## Test Scenarios

- Administrator Login (valid/invalid)
- Unauthorized access prevention
- Employee creation, search, deletion
- End-to-end employee lifecycle
- Security: authentication required for CRUD actions

## Maintenance & Enhancement

- Update `config.properties` for new data/test cases.
- Add new Page Object classes for further modules.
- Extend reporting or add advanced validations as needed.

