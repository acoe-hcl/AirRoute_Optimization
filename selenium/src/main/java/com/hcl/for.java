## 1. `pom.xml` (Maven Configuration)
**Location:** `pom.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.test</groupId>
    <artifactId>OrangeHRM-Automation</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <dependencies>
        <!-- Selenium WebDriver -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.12.1</version>
        </dependency>
        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.9.0</version>
            <scope>test</scope>
        </dependency>
        <!-- WebDriverManager -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.6.3</version>
        </dependency>
        <!-- ExtentReports -->
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>5.1.1</version>
        </dependency>
        <!-- Log4j2 API -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.20.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.20.0</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <!-- Compiler Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.10.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            <!-- Surefire Plugin for TestNG -->
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>testng.xml</suiteXmlFile>
                    </suiteXmlFiles>
                    <testFailureIgnore>false</testFailureIgnore>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## 2. `testng.xml` (TestNG Suite Configuration)
**Location:** `testng.xml`
```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="OrangeHRM Automation Suite" parallel="methods" thread-count="3">
    <listeners>
        <listener class-name="com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter"/>
    </listeners>
    <test name="Employee Management Tests">
        <classes>
            <class name="com.test.tests.EmployeeManagementTests"/>
        </classes>
    </test>
</suite>
```

## 3. `config.properties` (Test Configuration)
**Location:** `config.properties`
```properties
# Application URL
base.url=https://opensource-demo.orangehrmlive.com/

# Browser type: chrome, firefox, edge
browser=chrome

# Timeouts (in seconds)
explicit.wait=15

# Admin Credentials
admin.username=admin_user
admin.password=admin_pass

# Employee Details
employee.firstName=John
employee.lastName=Doe
employee.id=12345
employee.username=johndoe
employee.password=Emp@1234

# Invalid Login Data
invalid.username=invalid_user
invalid.password=invalid_pass
```

## 4. `BasePage.java` (Base Page Object)
**Location:** `src/test/java/com/test/pages/BasePage.java`
```java
package com.test.pages;

import com.test.utils.ConfigReader;
import com.test.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * BasePage serves as the base class for all Page Objects.
 * Encapsulates common page object utilities such as wait methods.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final long DEFAULT_TIMEOUT = ConfigReader.getLongProperty("explicit.wait", 15);

    /**
     * Constructor to initialize driver and page factory elements.
     * @param driver WebDriver instance.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for an element to be visible on the page.
     * @param element WebElement to wait for.
     */
    protected void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for an element to be clickable.
     * @param element WebElement to wait for.
     */
    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for a WebElement to disappear.
     * @param element WebElement to wait for disappearance.
     */
    protected void waitForElementInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Logs navigation to a page.
     * @param pageName Name of the page navigated to.
     */
    protected void logPageNavigation(String pageName) {
        Log.info("Navigated to page: " + pageName);
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

/**
 * Page object representing the OrangeHRM Login Page.
 */
public class LoginPage extends BasePage {

    // Page locators
    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".oxd-alert-content-text")
    private WebElement loginErrorMessage;

    /**
     * Constructor for LoginPage.
     * @param driver WebDriver instance.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters the username into the username field.
     * @param username String admin or employee username.
     */
    public void enterUsername(String username) {
        waitForElementVisible(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    /**
     * Enters the password into the password field.
     * @param password String user password.
     */
    public void enterPassword(String password) {
        waitForElementVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLogin() {
        waitForElementClickable(loginButton);
        loginButton.click();
    }

    /**
     * Logs in with specified credentials.
     * @param username Username to log in.
     * @param password Password for the user.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Checks if the login error message is displayed.
     * @return true if error message is displayed.
     */
    public boolean isLoginErrorVisible() {
        try {
            waitForElementVisible(loginErrorMessage);
            return loginErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the text of the login error message.
     * @return The message string.
     */
    public String getLoginErrorMessage() {
        try {
            waitForElementVisible(loginErrorMessage);
            return loginErrorMessage.getText();
        } catch (Exception e) {
            return "";
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

/**
 * Page object representing the OrangeHRM Dashboard.
 */
public class DashboardPage extends BasePage {

    @FindBy(css = "h6.oxd-topbar-header-breadcrumb-module")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(css = ".oxd-userdropdown-tab")
    private WebElement userDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutButton;

    /**
     * Constructor for DashboardPage.
     * @param driver WebDriver instance.
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifies dashboard is loaded.
     * @return true if dashboard is loaded.
     */
    public boolean isDashboardLoaded() {
        try {
            waitForElementVisible(dashboardHeader);
            return dashboardHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks the PIM menu in the top navigation.
     */
    public void clickPimMenu() {
        waitForElementClickable(pimMenu);
        pimMenu.click();
    }

    /**
     * Performs user logout action.
     */
    public void logout() {
        waitForElementClickable(userDropdown);
        userDropdown.click();
        waitForElementClickable(logoutButton);
        logoutButton.click();
    }
}
```

## 7. `PIMPage.java` (PIM/Employee Management Page Object)
**Location:** `src/test/java/com/test/pages/PIMPage.java`
```java
package com.test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page object for the PIM module in OrangeHRM, handling Employee actions.
 */
public class PIMPage extends BasePage {

    // -- Add Employee
    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeTab;

    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeIdInput;

    @FindBy(css = ".oxd-switch-input")
    private WebElement createLoginDetailsSwitch;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement employeeUsernameInput;

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    private WebElement employeePasswordInput;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
    private WebElement employeeConfirmPasswordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // -- Add Success, Personal Details
    @FindBy(css = ".orangehrm-edit-employee-name")
    private WebElement personalDetailsHeader;

    // -- Employee List
    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeListTab;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement searchEmployeeIdInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(css = ".oxd-table-body .oxd-table-card")
    private List<WebElement> employeeRows;

    @FindBy(xpath = "//span[text()='No Records Found']")
    private WebElement noRecordsFoundLabel;

    @FindBy(css = ".oxd-table-card .oxd-table-cell-actions > button[title='Delete']")
    private WebElement firstDeleteButton;

    @FindBy(css = ".oxd-dialog")
    private WebElement confirmDeleteDialog;

    @FindBy(css = ".oxd-dialog .oxd-button--label-danger")
    private WebElement confirmDeleteButton;

    /**
     * Constructor for PIMPage.
     * @param driver WebDriver instance.
     */
    public PIMPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigates to the Add Employee tab under PIM.
     */
    public void goToAddEmployee() {
        waitForElementClickable(addEmployeeTab);
        addEmployeeTab.click();
    }

    /**
     * Fills the add employee form and enables "Create login details".
     */
    public void addNewEmployee(String firstName, String lastName, String empId, String username, String password) {
        waitForElementVisible(firstNameInput);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        employeeIdInput.clear();
        employeeIdInput.sendKeys(empId);

        if (!createLoginDetailsSwitch.isSelected()) {
            createLoginDetailsSwitch.click();
        }
        waitForElementVisible(employeeUsernameInput);
        employeeUsernameInput.clear();
        employeeUsernameInput.sendKeys(username);

        employeePasswordInput.clear();
        employeePasswordInput.sendKeys(password);

        employeeConfirmPasswordInput.clear();
        employeeConfirmPasswordInput.sendKeys(password);
    }

    /**
     * Saves the new employee.
     */
    public void saveEmployee() {
        waitForElementClickable(saveButton);
        saveButton.click();
        // Wait for personal details header to assert success
        waitForElementVisible(personalDetailsHeader);
    }

    /**
     * Confirms that the employee personal details page is loaded.
     * @return true if present.
     */
    public boolean isOnPersonalDetailsPage() {
        try {
            return personalDetailsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Navigates to Employee List.
     */
    public void goToEmployeeList() {
        waitForElementClickable(employeeListTab);
        employeeListTab.click();
    }

    /**
     * Searches for an employee by ID.
     */
    public void searchEmployeeById(String empId) {
        waitForElementVisible(searchEmployeeIdInput);
        searchEmployeeIdInput.clear();
        searchEmployeeIdInput.sendKeys(empId);

        waitForElementClickable(searchButton);
        searchButton.click();
    }

    /**
     * Checks if the employee with given details is present in the table.
     */
    public boolean isEmployeePresent(String firstName, String lastName, String empId) {
        try {
            wait.until(driver ->
                employeeRows.stream().anyMatch(row -> row.getText().contains(empId) && row.getText().contains(firstName) && row.getText().contains(lastName))
            );
            for (WebElement row : employeeRows) {
                if (row.getText().contains(empId) && row.getText().contains(firstName) && row.getText().contains(lastName)) {
                    return true;
                }
            }
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Deletes the first employee found in search result.
     */
    public void deleteFirstEmployee() {
        waitForElementClickable(firstDeleteButton);
        firstDeleteButton.click();
        waitForElementVisible(confirmDeleteDialog);
        waitForElementClickable(confirmDeleteButton);
        confirmDeleteButton.click();
        // Wait for dialog to disappear or No Records Found
        try {
            waitForElementVisible(noRecordsFoundLabel);
        } catch (Exception ignored) {
        }
    }

    /**
     * Checks if "No Records Found" is displayed.
     */
    public boolean isNoRecordsFoundDisplayed() {
        try {
            waitForElementVisible(noRecordsFoundLabel);
            return noRecordsFoundLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
```

## 8. `ConfigReader.java` (Configuration Utility)
**Location:** `src/test/java/com/test/utils/ConfigReader.java`
```java
package com.test.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * ConfigReader loads properties from config.properties for test configuration.
 */
public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "config.properties";

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }

    /**
     * Gets a property value by key.
     * @param key Property name.
     * @return Property value as String, or null.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets a property as long or returns default if not found or invalid.
     * @param key  Property name.
     * @param defaultValue Default value.
     * @return Long value.
     */
    public static long getLongProperty(String key, long defaultValue) {
        String value = getProperty(key);
        if (value != null) {
            try {
                return Long.parseLong(value.trim());
            } catch (NumberFormatException ignored) {
            }
        }
        return defaultValue;
    }
}
```

## 9. `WebDriverManager.java` (Driver Factory Utility)
**Location:** `src/test/java/com/test/utils/WebDriverManager.java`
```java
package com.test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Factory class for WebDriver creation. Thread-safe for parallel execution.
 */
public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private WebDriverManager() { }

    /**
     * Initialize the WebDriver based on browser in config.
     */
    public static void initDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        WebDriver driver;
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        driverThreadLocal.set(driver);
    }

    /**
     * Returns the current WebDriver instance.
     * @return WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Quits the WebDriver and removes from thread local.
     */
    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
```

## 10. `Log.java` (Logging Utility)
**Location:** `src/test/java/com/test/utils/Log.java`
```java
package com.test.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Centralized Log4j logger for the framework.
 */
public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}
```

## 11. `BaseTest.java` (Test Base Setup)
**Location:** `src/test/java/com/test/tests/BaseTest.java`
```java
package com.test.tests;

import com.test.utils.ConfigReader;
import com.test.utils.Log;
import com.test.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest provides test setup and teardown for all test classes.
 */
public abstract class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.initDriver();
        driver = WebDriverManager.getDriver();
        driver.manage().window().maximize();
        baseUrl = ConfigReader.getProperty("base.url");
        driver.get(baseUrl);
        Log.info("Browser session started for: " + baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            Log.info("Browser session ended.");
        }
        WebDriverManager.quitDriver();
    }
}
```

## 12. `EmployeeManagementTests.java` (Test Scenarios)
**Location:** `src/test/java/com/test/tests/EmployeeManagementTests.java`
```java
package com.test.tests;

import com.test.pages.DashboardPage;
import com.test.pages.LoginPage;
import com.test.pages.PIMPage;
import com.test.utils.ConfigReader;
import com.test.utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Automated test cases for comprehensive Employee Management in OrangeHRM.
 */
public class EmployeeManagementTests extends BaseTest {
    /**
     * 1. Test: Valid Admin Login
     * Verifies successful login to dashboard.
     */
    @Test(priority = 1)
    public void testAdminValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        String adminUser = ConfigReader.getProperty("admin.username");
        String adminPass = ConfigReader.getProperty("admin.password");

        loginPage.login(adminUser, adminPass);
        DashboardPage dashboard = new DashboardPage(driver);
        boolean isDashboardLoaded = dashboard.isDashboardLoaded();

        Log.info("Admin login: " + isDashboardLoaded);
        Assert.assertTrue(isDashboardLoaded, "Admin should successfully log in and see dashboard.");
    }

    /**
     * 2. Test: Add New Employee with Login Details.
     * Verifies employee creation and redirection to personal details page.
     */
    @Test(priority = 2)
    public void testAddNewEmployee() {
        // Login first
        LoginPage loginPage = new LoginPage(driver);
        String adminUser = ConfigReader.getProperty("admin.username");
        String adminPass = ConfigReader.getProperty("admin.password");
        loginPage.login(adminUser, adminPass);

        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardLoaded(), "Dashboard must be loaded after login.");

        dashboard.clickPimMenu();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToAddEmployee();

        String firstName = ConfigReader.getProperty("employee.firstName");
        String lastName = ConfigReader.getProperty("employee.lastName");
        String empId = ConfigReader.getProperty("employee.id");
        String empUser = ConfigReader.getProperty("employee.username");
        String empPass = ConfigReader.getProperty("employee.password");

        pimPage.addNewEmployee(firstName, lastName, empId, empUser, empPass);
        pimPage.saveEmployee();

        Assert.assertTrue(pimPage.isOnPersonalDetailsPage(), "Employee should be created and navigated to Personal Details page.");
    }

    /**
     * 3. Test: Verify Employee Creation in Employee List.
     */
    @Test(priority = 3)
    public void testVerifyEmployeeCreationInList() {
        // Login first
        LoginPage loginPage = new LoginPage(driver);
        String adminUser = ConfigReader.getProperty("admin.username");
        String adminPass = ConfigReader.getProperty("admin.password");
        loginPage.login(adminUser, adminPass);

        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardLoaded(), "Dashboard must be loaded after login.");
        dashboard.clickPimMenu();

        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();

        String empId = ConfigReader.getProperty("employee.id");
        String firstName = ConfigReader.getProperty("employee.firstName");
        String lastName = ConfigReader.getProperty("employee.lastName");

        pimPage.searchEmployeeById(empId);
        boolean isPresent = pimPage.isEmployeePresent(firstName, lastName, empId);

        Assert.assertTrue(isPresent, "Newly added employee (ID: " + empId + ") should appear in the employee list.");
    }

    /**
     * 4. Test: Delete Employee by Employee ID.
     */
    @Test(priority = 4)
    public void testDeleteEmployee() {
        // Login first
        LoginPage loginPage = new LoginPage(driver);
        String adminUser = ConfigReader.getProperty("admin.username");
        String adminPass = ConfigReader.getProperty("admin.password");
        loginPage.login(adminUser, adminPass);

        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardLoaded(), "Dashboard must be loaded after login.");
        dashboard.clickPimMenu();

        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();

        String empId = ConfigReader.getProperty("employee.id");
        String firstName = ConfigReader.getProperty("employee.firstName");
        String lastName = ConfigReader.getProperty("employee.lastName");

        pimPage.searchEmployeeById(empId);
        boolean isPresent = pimPage.isEmployeePresent(firstName, lastName, empId);
        Assert.assertTrue(isPresent, "Employee with ID " + empId + " must be present before deletion.");

        pimPage.deleteFirstEmployee();

        // Confirm deletion in UI
        boolean noRecords = pimPage.isNoRecordsFoundDisplayed();
        Assert.assertTrue(noRecords, "After deletion, 'No Records Found' should be displayed.");
    }

    /**
     * 5. Test: Verify Deleted Employee is Not Found after Re-login.
     */
    @Test(priority = 5)
    public void testDeletedEmployeeNotFoundAfterLogoutLogin() {
        // First: Login and delete employee if present
        LoginPage loginPage = new LoginPage(driver);
        String adminUser = ConfigReader.getProperty("admin.username");
        String adminPass = ConfigReader.getProperty("admin.password");
        loginPage.login(adminUser, adminPass);

        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardLoaded(), "Dashboard must be loaded after login.");

        dashboard.clickPimMenu();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();

        String empId = ConfigReader.getProperty("employee.id");
        pimPage.searchEmployeeById(empId);

        if (!pimPage.isNoRecordsFoundDisplayed()) {
            // Employee still present, delete
            pimPage.deleteFirstEmployee();
        }
        // Logout
        dashboard.logout();

        // Login again
        loginPage = new LoginPage(driver);
        loginPage.login(adminUser, adminPass);

        dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardLoaded(), "Dashboard must be loaded after re-login.");

        dashboard.clickPimMenu();
        pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();
        pimPage.searchEmployeeById(empId);

        boolean noRecords = pimPage.isNoRecordsFoundDisplayed();
        Assert.assertTrue(noRecords, "Deleted employee with ID " + empId + " should NOT be found after logout/login.");
    }

    /**
     * 6. Test: Invalid Login Scenario.
     * Verifies that login with invalid credentials fails.
     */
    @Test(priority = 6)
    public void testInvalidLoginAttempt() {
        // Logout if logged in
        LoginPage loginPage = new LoginPage(driver);

        String invalidUser = ConfigReader.getProperty("invalid.username");
        String invalidPass = ConfigReader.getProperty("invalid.password");

        loginPage.enterUsername(invalidUser);
        loginPage.enterPassword(invalidPass);
        loginPage.clickLogin();

        boolean isErrorVisible = loginPage.isLoginErrorVisible();
        String actualError = loginPage.getLoginErrorMessage();

        Assert.assertTrue(isErrorVisible, "Login error message for invalid credentials should be visible.");
        Assert.assertTrue(actualError.contains("Invalid credentials"), "Error message should indicate invalid credentials. Found: " + actualError);
    }
}
```

## 13. `README.md` (Project Documentation)
**Location:** `README.md`
```markdown
# OrangeHRM Test Automation Framework

A production-ready Selenium TestNG automation framework for comprehensive Employee Management in OrangeHRM.

## Project Structure

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
                ??? test/
                    ??? pages/
                    ??? tests/
                    ??? utils/
```

## Features

- Page Object Model (POM)
- Data-driven tests from `config.properties`
- Cross-browser support: Chrome, Firefox, Edge
- Parallel test execution
- ExtentReports for detailed reporting
- Log4j2 logging and debugging
- Proper explicit waits (WebDriverWait)
- Robust error handling and assertions

## Prerequisites

- JDK 17+
- Maven 3.6+
- Chrome/Firefox/Edge browsers installed
- Internet connection (for WebDriver binaries)

## Setup

1. **Clone the repository:**
   ```
   git clone https://github.com/your-org/orangehrm-automation.git
   cd orangehrm-automation
   ```

2. **Configure properties:**
   Edit `config.properties` (URL, credentials, browsers, test data).

3. **Run tests via Maven:**
   ```
   mvn clean test
   ```

4. **View Reports:**
   Reports generated in `/test-output` or `/reports` folder.

## Usage

- To run on a different browser, change `browser` in `config.properties`.
- Tests and data are managed independently. Add/modify data in properties for flexibility.

## Test Cases Automated

1. Login as Admin
2. Add New Employee (with login credentials)
3. Verify Employee Creation in List
4. Delete Employee by Employee ID
5. Verify Employee Deletion After Logout/Login
6. Invalid Login Attempt

## Contribution

PRs are welcome! Please follow Java conventions, POM, and TestNG best practices.

---

© 2024 YourOrg. All rights reserved.
```