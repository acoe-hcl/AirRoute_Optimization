## 1. `pom.xml` (Maven Configuration)
**Location:** `pom.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.test</groupId>
    <artifactId>orangehrm-automation</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    <dependencies>
        <!-- Selenium WebDriver -->
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
            <version>5.4.0</version>
        </dependency>
        <!-- SLF4J Logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.9</version>
        </dependency>
        <!-- SLF4J Simple Binder -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>2.0.9</version>
        </dependency>
        <!-- ExtentReports -->
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>5.1.1</version>
        </dependency>
    </dependencies>
</project>
```
## 2. `testng.xml` (TestNG Suite Configuration)
**Location:** `testng.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="OrangeHRM Automation Suite" parallel="methods" thread-count="3">
    <test name="EmployeeManagementTests">
        <classes>
            <class name="com.test.tests.EmployeeManagementTest"/>
        </classes>
    </test>
</suite>
```
## 3. `config.properties` (Test Configuration)
**Location:** `config.properties`
```properties
# OrangeHRM Application Configuration
base.url=https://opensource-demo.orangehrmlive.com

# Browser configuration: chrome, firefox, edge
browser=chrome

# Administrator Credentials
admin.username=admin_user
admin.password=admin_pass

# Employee Test Data
employee.firstname=John
employee.lastname=Doe
employee.id=12345
employee.username=johndoe
employee.password=Emp@1234

# Invalid Login Credentials
invalid.username=invalid_user
invalid.password=invalid_pass

# Timeout Settings (in seconds)
explicit.wait.timeout=20

# Verification Messages
login.error.message=Invalid credentials
employee.no.records.found=No Records Found
```
## 4. `BasePage.java` (Base Page Object)
**Location:** `src/test/java/com/test/pages/BasePage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Abstract base class for all page objects.
 * Provides common WebDriver methods and explicit waits.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor for BasePage.
     * @param driver WebDriver instance.
     * @param timeoutSeconds Timeout in seconds for explicit waits.
     */
    public BasePage(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    /**
     * Waits for the given element to be visible.
     * @param element WebElement to wait for.
     * @return Visible WebElement.
     */
    protected WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the given element to be clickable.
     * @param element WebElement to wait for.
     * @return Clickable WebElement.
     */
    protected WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for the page title to match the expected value.
     * @param expectedTitle Expected page title.
     * @return True if title matches, false otherwise.
     */
    protected boolean waitForPageTitle(String expectedTitle) {
        return wait.until(ExpectedConditions.titleIs(expectedTitle));
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
 * Page Object for the OrangeHRM Login Page.
 */
public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(@class,'oxd-text') and contains(text(),'Invalid')]")
    private WebElement invalidCredsMessage;

    /**
     * Initializes the LoginPage elements.
     * @param driver WebDriver instance.
     * @param timeoutSeconds Explicit wait timeout in seconds.
     */
    public LoginPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        PageFactory.initElements(driver, this);
    }

    /**
     * Performs login action with specified username and password.
     * @param username Username input.
     * @param password Password input.
     */
    public void login(String username, String password) {
        waitForElementVisible(usernameField).clear();
        usernameField.sendKeys(username);
        waitForElementVisible(passwordField).clear();
        passwordField.sendKeys(password);
        waitForElementClickable(loginButton).click();
    }

    /**
     * Gets invalid credentials error message.
     * @return The invalid credentials error message string.
     */
    public String getInvalidCredentialsMessage() {
        return waitForElementVisible(invalidCredsMessage).getText().trim();
    }

    /**
     * Checks if invalid credentials message is displayed.
     * @return True if invalid credentials message is visible.
     */
    public boolean isLoginErrorDisplayed() {
        try {
            return invalidCredsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
```
## 6. `DashboardPage.java` (Dashboard Page Object)
**Location:** `src/test/java/com/test/pages/DashboardPage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for the OrangeHRM Dashboard after login.
 */
public class DashboardPage extends BasePage {

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(xpath = "//header//span[contains(text(),'Dashboard')]")
    private WebElement dashboardTitle;

    @FindBy(xpath = "//i[@class='oxd-icon bi-chevron-down']")
    private WebElement userDropdownIcon;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutOption;

    /**
     * Initializes the DashboardPage elements.
     * @param driver WebDriver instance.
     * @param timeoutSeconds Explicit wait timeout in seconds.
     */
    public DashboardPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        PageFactory.initElements(driver, this);
    }

    /**
     * Checks if Dashboard page is displayed.
     * @return True if dashboard title is present.
     */
    public boolean isDashboardDisplayed() {
        try {
            return waitForElementVisible(dashboardTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Navigates to the PIM section.
     */
    public void navigateToPIM() {
        waitForElementClickable(pimMenu).click();
    }

    /**
     * Logs out from the application.
     */
    public void logout() {
        waitForElementClickable(userDropdownIcon).click();
        waitForElementClickable(logoutOption).click();
    }
}
```
## 7. `PIMPage.java` (PIM/Main Employee Page Object)
**Location:** `src/test/java/com/test/pages/PIMPage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for PIM navigation and Employee List.
 */
public class PIMPage extends BasePage {

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addEmployeeButton;

    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeListTab;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeIdSearchField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='oxd-table-card']//span[contains(text(),'No Records Found')]")
    private WebElement noRecordsFoundLabel;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[contains(@class,'oxd-table-cell')][2]")
    private WebElement employeeNameResultCell;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[contains(@class,'oxd-table-cell')][3]")
    private WebElement employeeIdResultCell;

    @FindBy(xpath = "//div[@class='oxd-table-card']//button[1]")
    private WebElement employeeDeleteIcon;

    @FindBy(xpath = "//button[text()='Yes, Delete']")
    private WebElement confirmDeleteButton;

    /**
     * Initializes the PIMPage elements.
     * @param driver WebDriver instance.
     * @param timeoutSeconds Explicit wait timeout in seconds.
     */
    public PIMPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to Add Employee page.
     */
    public void clickAddEmployee() {
        waitForElementClickable(addEmployeeButton).click();
    }

    /**
     * Navigates to Employee List tab.
     */
    public void openEmployeeListTab() {
        waitForElementClickable(employeeListTab).click();
    }

    /**
     * Searches for an employee by ID.
     * @param employeeId Employee ID string.
     */
    public void searchEmployeeById(String employeeId) {
        waitForElementVisible(employeeIdSearchField).clear();
        employeeIdSearchField.sendKeys(employeeId);
        waitForElementClickable(searchButton).click();
    }

    /**
     * Returns true if employee search result is "No Records Found".
     * @return True if no records found.
     */
    public boolean isNoRecordsFoundDisplayed() {
        try {
            return waitForElementVisible(noRecordsFoundLabel).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns employee name in search result.
     * @return Employee name string.
     */
    public String getResultEmployeeName() {
        try {
            return waitForElementVisible(employeeNameResultCell).getText().trim();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns employee ID in search result.
     * @return Employee ID string.
     */
    public String getResultEmployeeId() {
        try {
            return waitForElementVisible(employeeIdResultCell).getText().trim();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Deletes employee in search result.
     */
    public void deleteEmployee() {
        waitForElementClickable(employeeDeleteIcon).click();
        waitForElementClickable(confirmDeleteButton).click();
    }
}
```
## 8. `AddEmployeePage.java` (Add Employee Page Object)
**Location:** `src/test/java/com/test/pages/AddEmployeePage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for Add Employee functionality.
 */
public class AddEmployeePage extends BasePage {

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeIdField;

    @FindBy(xpath = "//span[@class='oxd-switch-input']")
    private WebElement createLoginDetailsToggle;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement employeeUsernameField;

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    private WebElement employeePasswordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
    private WebElement employeeConfirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    /**
     * Initializes AddEmployeePage elements.
     * @param driver WebDriver instance.
     * @param timeoutSeconds Explicit wait timeout in seconds.
     */
    public AddEmployeePage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters new employee information & creates login details.
     * @param firstName Employee first name.
     * @param lastName Employee last name.
     * @param empId Employee ID.
     * @param username Login username.
     * @param password Login password.
     */
    public void fillEmployeeDetails(String firstName, String lastName, String empId,
                                    String username, String password) {
        waitForElementVisible(firstNameField).clear();
        firstNameField.sendKeys(firstName);
        waitForElementVisible(lastNameField).clear();
        lastNameField.sendKeys(lastName);
        waitForElementVisible(employeeIdField).clear();
        employeeIdField.sendKeys(empId);

        waitForElementClickable(createLoginDetailsToggle).click();
        waitForElementVisible(employeeUsernameField).clear();
        employeeUsernameField.sendKeys(username);
        waitForElementVisible(employeePasswordField).clear();
        employeePasswordField.sendKeys(password);
        waitForElementVisible(employeeConfirmPasswordField).clear();
        employeeConfirmPasswordField.sendKeys(password);
    }

    /**
     * Saves the new employee.
     */
    public void saveEmployee() {
        waitForElementClickable(saveButton).click();
    }
}
```
## 9. `PersonalDetailsPage.java` (Personal Details Page Object)
**Location:** `src/test/java/com/test/pages/PersonalDetailsPage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for Employee Personal Details page after creation.
 */
public class PersonalDetailsPage extends BasePage {

    @FindBy(xpath = "//h6[contains(text(),'Personal Details')]")
    private WebElement personalDetailsHeader;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeIdField;

    /**
     * Initializes PersonalDetailsPage elements.
     * @param driver WebDriver instance.
     * @param timeoutSeconds Explicit wait timeout in seconds.
     */
    public PersonalDetailsPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifies that personal details page is displayed.
     * @return True if personal details header is displayed.
     */
    public boolean isPersonalDetailsPageDisplayed() {
        try {
            return waitForElementVisible(personalDetailsHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns employee first name displayed.
     * @return First name string.
     */
    public String getFirstName() {
        return waitForElementVisible(firstNameField).getAttribute("value");
    }

    /**
     * Returns employee last name displayed.
     * @return Last name string.
     */
    public String getLastName() {
        return waitForElementVisible(lastNameField).getAttribute("value");
    }

    /**
     * Returns employee ID displayed.
     * @return Employee ID string.
     */
    public String getEmployeeId() {
        return waitForElementVisible(employeeIdField).getAttribute("value");
    }
}
```
## 10. `ConfigReader.java` (Configuration Utility)
**Location:** `src/test/java/com/test/utils/ConfigReader.java`
```java
package com.test.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for reading configuration data from properties file.
 */
public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    /**
     * Gets property value by key.
     * @param key Property key.
     * @return Property value.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets explicit wait timeout from config.
     * @return Timeout in seconds as integer.
     */
    public static int getExplicitWaitTimeout() {
        String timeout = properties.getProperty("explicit.wait.timeout");
        return Integer.parseInt(timeout);
    }
}
```
## 11. `WebDriverManager.java` (WebDriver Factory Utility)
**Location:** `src/test/java/com/test/utils/WebDriverManager.java`
```java
package com.test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * Utility class for WebDriver setup based on config.
 */
public class WebDriverManagerUtil {

    /**
     * Initializes WebDriver based on config 'browser' property.
     * @return WebDriver instance.
     */
    public static WebDriver getDriver() {
        String browser = ConfigReader.getProperty("browser");
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
```
## 12. `BaseTest.java` (Test Base Class)
**Location:** `src/test/java/com/test/tests/BaseTest.java`
```java
package com.test.tests;

import com.test.utils.ConfigReader;
import com.test.utils.WebDriverManagerUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all OrangeHRM tests.
 * Handles WebDriver setup/teardown.
 */
public abstract class BaseTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        logger.info("Initializing WebDriver for test thread.");
        driver.set(WebDriverManagerUtil.getDriver());
        driver.get().get(ConfigReader.getProperty("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        logger.info("Quitting WebDriver for test thread.");
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    /**
     * Returns the driver instance (thread-safe).
     * @return WebDriver instance.
     */
    public WebDriver getDriver() {
        return driver.get();
    }
}
```
## 13. `EmployeeManagementTest.java` (Employee Management Test Cases)
**Location:** `src/test/java/com/test/tests/EmployeeManagementTest.java`
```java
package com.test.tests;

import com.test.pages.*;
import com.test.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Comprehensive Employee Management tests for OrangeHRM.
 */
public class EmployeeManagementTest extends BaseTest {

    /**
     * Test: Administrator login with valid credentials.
     */
    @Test(description = "Administrator successfully logs in with valid credentials.")
    public void testValidAdminLogin() {
        LoginPage loginPage = new LoginPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        DashboardPage dashboardPage = new DashboardPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be visible after valid login.");
    }

    /**
     * Test: Administrator adds a new employee with login details.
     */
    @Test(description = "Administrator can add new employee and login details.")
    public void testAddNewEmployee() {
        LoginPage loginPage = new LoginPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        DashboardPage dashboardPage = new DashboardPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be visible after valid login.");
        dashboardPage.navigateToPIM();

        PIMPage pimPage = new PIMPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        pimPage.clickAddEmployee();

        AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        addEmployeePage.fillEmployeeDetails(
                ConfigReader.getProperty("employee.firstname"),
                ConfigReader.getProperty("employee.lastname"),
                ConfigReader.getProperty("employee.id"),
                ConfigReader.getProperty("employee.username"),
                ConfigReader.getProperty("employee.password")
        );
        addEmployeePage.saveEmployee();

        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        Assert.assertTrue(personalDetailsPage.isPersonalDetailsPageDisplayed(),
                "Should be redirected to Personal Details page after adding employee.");
        Assert.assertEquals(personalDetailsPage.getFirstName(), ConfigReader.getProperty("employee.firstname"),
                "First name should match input data.");
        Assert.assertEquals(personalDetailsPage.getLastName(), ConfigReader.getProperty("employee.lastname"),
                "Last name should match input data.");
        Assert.assertEquals(personalDetailsPage.getEmployeeId(), ConfigReader.getProperty("employee.id"),
                "Employee ID should match input data.");
    }

    /**
     * Test: Verifies that newly added employee appears in the employee list.
     */
    @Test(description = "Newly added employee appears in the employee list.")
    public void testVerifyEmployeeCreation() {
        LoginPage loginPage = new LoginPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        DashboardPage dashboardPage = new DashboardPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        dashboardPage.navigateToPIM();

        PIMPage pimPage = new PIMPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        pimPage.openEmployeeListTab();
        pimPage.searchEmployeeById(ConfigReader.getProperty("employee.id"));

        String actualName = pimPage.getResultEmployeeName();
        String expectedName = ConfigReader.getProperty("employee.firstname") + " " + ConfigReader.getProperty("employee.lastname");
        String actualId = pimPage.getResultEmployeeId();
        Assert.assertEquals(actualName, expectedName, "Employee name should match newly created employee.");
        Assert.assertEquals(actualId, ConfigReader.getProperty("employee.id"), "Employee ID should match search criteria.");
    }

    /**
     * Test: Deletes selected employee and verifies removal.
     */
    @Test(description = "Selected employee can be deleted and is removed from the system.")
    public void testDeleteEmployee() {
        LoginPage loginPage = new LoginPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        DashboardPage dashboardPage = new DashboardPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        dashboardPage.navigateToPIM();

        PIMPage pimPage = new PIMPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        pimPage.openEmployeeListTab();
        pimPage.searchEmployeeById(ConfigReader.getProperty("employee.id"));

        String actualId = pimPage.getResultEmployeeId();
        Assert.assertEquals(actualId, ConfigReader.getProperty("employee.id"), "Employee ID to delete should match.");

        pimPage.deleteEmployee();

        // Re-search to confirm deletion
        pimPage.searchEmployeeById(ConfigReader.getProperty("employee.id"));
        Assert.assertTrue(pimPage.isNoRecordsFoundDisplayed(),
                "After deletion, 'No Records Found' should be shown for employee search.");
    }

    /**
     * Test: Deleted employee record is not found upon search post-deletion.
     */
    @Test(description = "Deleted employee record is not found upon search post-deletion.")
    public void testVerifyEmployeeDeletion() {
        LoginPage loginPage = new LoginPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        DashboardPage dashboardPage = new DashboardPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        dashboardPage.navigateToPIM();

        PIMPage pimPage = new PIMPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        pimPage.openEmployeeListTab();
        pimPage.searchEmployeeById(ConfigReader.getProperty("employee.id"));

        Assert.assertTrue(pimPage.isNoRecordsFoundDisplayed(),
                "Deleted employee should not be found; 'No Records Found' expected.");
    }

    /**
     * Test: Invalid login attempts are blocked with appropriate error message.
     */
    @Test(description = "Invalid login attempts are blocked with appropriate error message.")
    public void testInvalidLoginAttempt() {
        LoginPage loginPage = new LoginPage(getDriver(), ConfigReader.getExplicitWaitTimeout());
        loginPage.login(ConfigReader.getProperty("invalid.username"), ConfigReader.getProperty("invalid.password"));

        Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Login error should be displayed for invalid credentials.");
        Assert.assertEquals(loginPage.getInvalidCredentialsMessage(),
                ConfigReader.getProperty("login.error.message"),
                "Error message should match expected invalid credentials message.");
    }
}
```
## 14. `README.md` (Project Documentation)
**Location:** `README.md`
```markdown
# OrangeHRM Employee Management Automation

## Overview
This project automates comprehensive employee management scenarios in the OrangeHRM web application using Java, Selenium WebDriver, and TestNG. The framework follows industry-standard Page Object Model (POM) design principles for robust, scalable test automation.

## Features
- Advanced browser automation (multi-browser support)
- Thread-safe parallel execution
- Explicit waits for synchronization (no Thread.sleep)
- Test data managed via external config.properties
- Detailed logging and reporting (SLF4J, ExtentReports)
- Page Object Model (POM) structure
- Complete coverage of login, employee creation, deletion, negative scenarios

## Prerequisites
- Java (JDK 17+)
- Maven (for dependency management and execution)
- Chrome, Firefox, or Edge browser installed
- OrangeHRM web application accessible

## Setup Instructions
1. **Clone the Repository**
   ```
   git clone <your-repo-url>
   cd orangehrm-automation
   ```

2. **Configure Test Data**
   - Update `config.properties` as per your environment:
     ```
     base.url=https://opensource-demo.orangehrmlive.com
     browser=chrome
     admin.username=admin_user
     admin.password=admin_pass
     ...
     ```
3. **Run Tests**
   - Use Maven to execute the suite:
     ```
     mvn clean test
     ```
   - Test results will be available in the Maven console and ExtentReports output (if enabled).

4. **Parallel Execution**
   - Tests run in parallel as configured in `testng.xml`. Adjust `thread-count` as needed.

## Structure

```
project-root/
??? pom.xml
??? testng.xml
??? config.properties
??? README.md
??? src/
    ??? test/
        ??? java/
            ??? com/
            ?   ??? test/
            ?       ??? pages/
            ?       ?   ??? BasePage.java
            ?       ?   ??? LoginPage.java
            ?       ?   ??? DashboardPage.java
            ?       ?   ??? PIMPage.java
            ?       ?   ??? AddEmployeePage.java
            ?       ?   ??? PersonalDetailsPage.java
            ?       ??? tests/
            ?       ?   ??? BaseTest.java
            ?       ?   ??? EmployeeManagementTest.java
            ?       ??? utils/
            ?           ??? ConfigReader.java
            ?           ??? WebDriverManager.java
```

## Extending & Customization

- Add new page objects for OrangeHRM functionalities under `pages/`
- Add new test cases in `tests/` folder
- Use `ConfigReader` utility to manage all test data centrally

## License

This project is open source and free to use for educational and non-commercial purposes.
```