## 1. `pom.xml` (Maven Configuration)
**Location:** `pom.xml`
```xml
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
        <encoding>UTF-8</encoding>
    </properties>
    <dependencies>
        <!-- Selenium Java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.11.0</version>
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
            <version>5.7.0</version>
        </dependency>
        <!-- ExtentReports -->
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>5.1.1</version>
        </dependency>
        <!-- Log4j (SLF4J binding) -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.19.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.19.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
            <version>2.19.0</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <!-- Surefire Plugin for TestNG -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
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
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="OrangeHRM Automation Suite" parallel="tests" thread-count="3">
    <listeners>
        <listener class-name="com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter"/>
    </listeners>
    <test name="EmployeeManagement">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="com.test.tests.EmployeeManagementTest"/>
        </classes>
    </test>
</suite>
```

## 3. `config.properties` (Test Configuration)
**Location:** `config.properties`
```properties
# Application URLs
base.url=https://opensource-demo.orangehrmlive.com

# Admin credentials
admin.username=admin_user
admin.password=admin_pass

# Employee details for creation
employee.firstName=John
employee.lastName=Doe
employee.empId=12345
employee.username=johndoe
employee.password=Secure#Pwd2024!!

# Invalid credentials
invalid.username=invalid_user
invalid.password=invalid_pass

# Default browser
browser=chrome

# Wait timeouts (in seconds)
explicit.wait=20

# Paths for reporting/screenshots
report.path=./reports/
screenshot.path=./screenshots/
```

## 4. `BasePage.java` (Base Page Object)
**Location:** `src/test/java/com/test/pages/BasePage.java`
```java
package com.test.pages;

import com.test.utils.ConfigReader;
import com.test.utils.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BasePage class provides common Selenium WebDriver actions and waits for all page objects.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    /**
     * Constructor for BasePage.
     * @param driver The WebDriver instance to use.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ConfigReader.getInt("explicit.wait"));
        this.logger = LogManager.getLogger(this.getClass());
    }

    /**
     * Wait for visibility of the provided element.
     * @param element The WebElement to wait for.
     */
    protected void waitForElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element to be visible: {}", element, e);
            throw e;
        }
    }

    /**
     * Wait for element to be clickable.
     * @param element The WebElement to wait for.
     */
    protected void waitForElementClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element to be clickable: {}", element, e);
            throw e;
        }
    }

    /**
     * Send keys safely to a field after waiting for it to be visible.
     * @param element WebElement to send keys to
     * @param text Text to send
     */
    protected void sendKeys(WebElement element, String text) {
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text '{}' into element {}", text, element);
    }

    /**
     * Clicks the given element after waiting for it to be clickable.
     * @param element The WebElement to click
     */
    protected void click(WebElement element) {
        waitForElementClickable(element);
        element.click();
        logger.info("Clicked element {}", element);
    }

    /**
     * Checks if a WebElement is displayed
     * @param element The WebElement to check
     * @return true if displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitForElementVisible(element);
            boolean displayed = element.isDisplayed();
            logger.info("Element displayed status: {}", displayed);
            return displayed;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Element not displayed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Gets the text from a WebElement after waiting for it to be visible.
     * @param element The WebElement to get text from.
     * @return The extracted text.
     */
    protected String getElementText(WebElement element) {
        waitForElementVisible(element);
        String text = element.getText();
        logger.info("Fetched element text: {}", text);
        return text;
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
 * Page Object Model for the OrangeHRM Login Page.
 */
public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginBtn;

    @FindBy(css = ".oxd-alert-content-text")
    private WebElement loginErrorMessage;

    /**
     * Constructor initializes locators.
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Logs in using the specified username and password.
     * @param username The username to enter
     * @param password The password to enter
     */
    public void login(String username, String password) {
        logger.info("Attempting login with username '{}'", username);
        sendKeys(usernameInput, username);
        sendKeys(passwordInput, password);
        click(loginBtn);
    }

    /**
     * Returns true if login error message is displayed.
     * @return boolean is error displayed
     */
    public boolean isLoginErrorDisplayed() {
        return isElementDisplayed(loginErrorMessage);
    }

    /**
     * Gets the displayed login error message text.
     * @return String error message
     */
    public String getLoginErrorMessage() {
        return getElementText(loginErrorMessage);
    }
}
```

---

## 6. `DashboardPage.java` (Dashboard Page Object)
**Location:** `src/test/java/com/test/pages/DashboardPage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for the Dashboard Page after successful login.
 */
public class DashboardPage extends BasePage {

    @FindBy(xpath = "//span/h6[contains(text(),'Dashboard')]")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(css = ".oxd-userdropdown-name")
    private WebElement userDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutOption;

    /**
     * Initializes locators for DashboardPage.
     * @param driver WebDriver instance
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for dashboard header and verify if present.
     * @return true if Dashboard header is displayed.
     */
    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardHeader);
    }

    /**
     * Navigates to the PIM menu.
     */
    public void goToPIM() {
        click(pimMenu);
    }

    /**
     * Performs logout operation from dashboard.
     */
    public void logout() {
        click(userDropdown);
        click(logoutOption);
    }
}
```

---

## 7. `PIMPage.java` (PIM Page Object)
**Location:** `src/test/java/com/test/pages/PIMPage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * POM for OrangeHRM PIM page: Add/Search/Delete Employee.
 */
public class PIMPage extends BasePage {

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeTab;

    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeListTab;

    // Add Employee form
    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement empIdField;

    @FindBy(xpath = "//span[text()='Create Login Details']/parent::label//input[@type='checkbox']")
    private WebElement createLoginToggle;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement empUsernameField;

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    private WebElement empPasswordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
    private WebElement empConfirmPwdField;

    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveBtn;

    // Employee List form
    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement searchEmpIdField;

    @FindBy(xpath = "//button[contains(.,'Search')]")
    private WebElement searchBtn;

    @FindBy(xpath = "//span[text()='No Records Found']")
    private WebElement noRecordsFoundLabel;

    /**
     * Constructor initializes elements for PIMPage.
     * @param driver WebDriver instance
     */
    public PIMPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigate to Add Employee tab.
     */
    public void goToAddEmployee() {
        click(addEmployeeTab);
    }

    /**
     * Fill Add Employee form and save.
     */
    public void addEmployee(String firstName, String lastName, String empId, String username, String password) {
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(empIdField, empId);
        if (!createLoginToggle.isSelected()) {
            click(createLoginToggle);
        }
        sendKeys(empUsernameField, username);
        sendKeys(empPasswordField, password);
        sendKeys(empConfirmPwdField, password);
        click(saveBtn);
    }

    /**
     * Navigate to the Employee List tab.
     */
    public void goToEmployeeList() {
        click(employeeListTab);
    }

    /**
     * Search by Employee Id in Employee List tab.
     */
    public void searchEmployeeById(String empId) {
        sendKeys(searchEmpIdField, empId);
        click(searchBtn);
    }

    /**
     * Returns whether No Records Found is displayed after search.
     */
    public boolean isNoRecordsFound() {
        return isElementDisplayed(noRecordsFoundLabel);
    }
}
```

---

## 8. `EmployeeDetailsPage.java` (Employee Details Page Object)
**Location:** `src/test/java/com/test/pages/EmployeeDetailsPage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Employee Personal Details Page Object
 */
public class EmployeeDetailsPage extends BasePage {

    // Header with employee information
    @FindBy(xpath = "//h6[contains(text(),'Personal Details')]")
    private WebElement personalDetailsHeader;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement empIdInput;

    /**
     * Constructor initializing driver and elements.
     * @param driver WebDriver instance.
     */
    public EmployeeDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify Personal Details page header is displayed.
     */
    public boolean isPersonalDetailsDisplayed() {
        return isElementDisplayed(personalDetailsHeader);
    }

    /**
     * Get current First Name value.
     */
    public String getFirstName() {
        return firstNameInput.getAttribute("value");
    }

    /**
     * Get current Last Name value.
     */
    public String getLastName() {
        return lastNameInput.getAttribute("value");
    }

    /**
     * Get current Employee ID value.
     */
    public String getEmployeeId() {
        return empIdInput.getAttribute("value");
    }
}
```

---

## 9. `EmployeeListPage.java` (Employee List Page Object)
**Location:** `src/test/java/com/test/pages/EmployeeListPage.java`
```java
package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object for Employee List grid/table, with search/delete actions.
 */
public class EmployeeListPage extends BasePage {

    // Table rows and cells
    private By rowSelector = By.xpath("//div[@class='orangehrm-container']//div[@role='row' and .//div[contains(@class,'oxd-table-cell')]]");

    /**
     * Constructor for EmployeeListPage.
     * @param driver WebDriver instance
     */
    public EmployeeListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Check if a specific employee row (by name and emp id) is present.
     * @param fullName Employee's full name
     * @param empId Employee ID
     * @return row WebElement if found, else null
     */
    public WebElement getEmployeeRow(String fullName, String empId) {
        List<WebElement> rows = driver.findElements(rowSelector);
        for (WebElement row : rows) {
            if (row.getText().contains(fullName) && row.getText().contains(empId)) {
                return row;
            }
        }
        return null;
    }

    /**
     * Delete an employee by locating their row.
     * @param fullName Employee full name
     * @param empId Employee ID
     */
    public void deleteEmployee(String fullName, String empId) {
        WebElement row = getEmployeeRow(fullName, empId);
        if (row != null) {
            WebElement deleteBtn = row.findElement(By.xpath(".//button[contains(@class,'oxd-table-cell-action-space')]/i[contains(@class,'bi-trash')]"));
            click(deleteBtn);
        } else {
            logger.error("Employee row not found for deletion: {} / {}", fullName, empId);
            throw new RuntimeException("Employee not found in list for deletion");
        }
    }

    /**
     * Confirm dialog for deletion appears and confirm action.
     */
    public void confirmDeletePopup() {
        By dialogDeleteBtn = By.xpath("//button[text()=' Yes, Delete ']");
        WebElement confirmBtn = driver.findElement(dialogDeleteBtn);
        click(confirmBtn);
    }
}
```

---

## 10. `ConfigReader.java` (Configuration Utility)
**Location:** `src/test/java/com/test/utils/ConfigReader.java`
```java
package com.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to load configuration from properties file.
 */
public class ConfigReader {
    private static final Properties prop = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties could not be loaded");
        }
    }

    /**
     * Retrieves a property value as String.
     * @param key Key to search
     * @return String property value
     */
    public static String get(String key) {
        return prop.getProperty(key);
    }

    /**
     * Retrieves a property value as Integer.
     * @param key Key to search
     * @return int property value
     */
    public static Integer getInt(String key) {
        return Integer.parseInt(get(key));
    }
}
```

---

## 11. `WebDriverManager.java` (Thread-Safe WebDriver Utility)
**Location:** `src/test/java/com/test/utils/WebDriverManager.java`
```java
package com.test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

/**
 * Thread-safe WebDriver utility for parallel test execution.
 */
public class WebDriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    /**
     * Gets current instance of WebDriver.
     */
    public static WebDriver getDriver() {
        return driverThread.get();
    }

    /**
     * Initializes and returns a WebDriver instance based on browser.
     */
    public static void initDriver() {
        String browser = ConfigReader.get("browser").toLowerCase();
        WebDriver driver;
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions opts = new ChromeOptions();
                opts.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                driver = new ChromeDriver(opts);
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
                throw new RuntimeException("Unsupported browser: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // NO implicit waits
        driver.manage().window().maximize();
        driverThread.set(driver);
    }

    /**
     * Quits the current WebDriver instance.
     */
    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}
```

---

## 12. `EmployeeManagementTest.java` (Test Class with All Test Methods)
**Location:** `src/test/java/com/test/tests/EmployeeManagementTest.java`
```java
package com.test.tests;

import com.test.pages.*;
import com.test.utils.ConfigReader;
import com.test.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Test class for comprehensive employee management scenarios in OrangeHRM.
 */
public class EmployeeManagementTest extends BaseTest {

    private WebDriver driver;
    private Logger logger;

    private String adminUsername;
    private String adminPassword;
    private String empFirstName;
    private String empLastName;
    private String empId;
    private String empUsername;
    private String empPassword;
    private String invalidUsername;
    private String invalidPassword;

    @BeforeClass
    public void setupTestData() {
        adminUsername = ConfigReader.get("admin.username");
        adminPassword = ConfigReader.get("admin.password");
        empFirstName = ConfigReader.get("employee.firstName");
        empLastName = ConfigReader.get("employee.lastName");
        empId = ConfigReader.get("employee.empId");
        empUsername = ConfigReader.get("employee.username");
        empPassword = ConfigReader.get("employee.password");
        invalidUsername = ConfigReader.get("invalid.username");
        invalidPassword = ConfigReader.get("invalid.password");

        logger = LogManager.getLogger(this.getClass());
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.initDriver();
        driver = WebDriverManager.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.quitDriver();
    }

    /**
     * 1. Login to OrangeHRM and verify successful navigation to dashboard.
     */
    @Test(priority = 1, description = "Login to OrangeHRM as admin and verify dashboard")
    public void testValidLogin() {
        LoginPage login = new LoginPage(driver);
        login.login(adminUsername, adminPassword);

        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardDisplayed(), "Dashboard should be displayed after admin login");
    }

    /**
     * 2. Add new employee and verify personal details page loaded.
     */
    @Test(priority = 2, description = "Add new employee with login details and verify employee is created")
    public void testAddNewEmployee() {
        // Login
        LoginPage login = new LoginPage(driver);
        login.login(adminUsername, adminPassword);
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardDisplayed(), "Dashboard should be displayed after login.");

        // PIM - Add Employee
        dashboard.goToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToAddEmployee();
        pimPage.addEmployee(empFirstName, empLastName, empId, empUsername, empPassword);

        // Verify redirected to personal details page
        EmployeeDetailsPage empDetails = new EmployeeDetailsPage(driver);
        Assert.assertTrue(empDetails.isPersonalDetailsDisplayed(), "Personal Details page should be displayed.");
        Assert.assertEquals(empDetails.getFirstName(), empFirstName, "First name matches");
        Assert.assertEquals(empDetails.getLastName(), empLastName, "Last name matches");
        Assert.assertEquals(empDetails.getEmployeeId(), empId, "Employee ID matches");
    }

    /**
     * 3. Search for new employee in Employee List and verify presence.
     */
    @Test(priority = 3, description = "Verify new employee appears in the employee list")
    public void testVerifyEmployeeCreation() {
        // Login
        LoginPage login = new LoginPage(driver);
        login.login(adminUsername, adminPassword);
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardDisplayed(), "Dashboard displayed after login");

        // Employee List
        dashboard.goToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();
        pimPage.searchEmployeeById(empId);

        EmployeeListPage empList = new EmployeeListPage(driver);
        Assert.assertNotNull(empList.getEmployeeRow(empFirstName + " " + empLastName, empId),
                "Employee with ID " + empId + " and Name " + empFirstName + " " + empLastName + " should be present in the list");
    }

    /**
     * 4. Delete employee by employee ID and verify no longer in list.
     */
    @Test(priority = 4, description = "Delete employee record and verify removal from list")
    public void testDeleteEmployee() {
        // Login
        LoginPage login = new LoginPage(driver);
        login.login(adminUsername, adminPassword);
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardDisplayed(), "Dashboard displayed after login");

        // Employee List
        dashboard.goToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();
        pimPage.searchEmployeeById(empId);

        EmployeeListPage empList = new EmployeeListPage(driver);
        empList.deleteEmployee(empFirstName + " " + empLastName, empId);
        empList.confirmDeletePopup();

        // Confirm employee is gone
        pimPage.searchEmployeeById(empId);
        Assert.assertTrue(pimPage.isNoRecordsFound(), "No Records Found message should be shown after delete.");
    }

    /**
     * 5. Verify employee not present after deletion (fresh login).
     */
    @Test(priority = 5, description = "Verify deleted employee cannot be found after deletion")
    public void testVerifyEmployeeDeletion() {
        // Login
        LoginPage login = new LoginPage(driver);
        login.login(adminUsername, adminPassword);
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isDashboardDisplayed(), "Dashboard displayed after login");

        // Employee List
        dashboard.goToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();
        pimPage.searchEmployeeById(empId);

        Assert.assertTrue(pimPage.isNoRecordsFound(), "No Records Found should be displayed after employee deletion");
    }

    /**
     * 6. Invalid login attempt shows error message.
     */
    @Test(priority = 6, description = "Verify invalid login displays error message")
    public void testInvalidLoginAttempt() {
        LoginPage login = new LoginPage(driver);
        login.login(invalidUsername, invalidPassword);

        Assert.assertTrue(login.isLoginErrorDisplayed(), "Login error message should be displayed for invalid credentials.");
        String errorMsg = login.getLoginErrorMessage();
        Assert.assertTrue(errorMsg.toLowerCase().contains("invalid"), "Error message contains 'invalid': " + errorMsg);
    }
}
```

---

## 13. `BaseTest.java` (Base Test Setup)
**Location:** `src/test/java/com/test/tests/BaseTest.java`
```java
package com.test.tests;

import com.test.utils.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest: Handles setup and teardown for tests, ensures thread safety.
 */
public abstract class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setUpBase() {
        // Intentionally left blank, each concrete test will do its own navigation
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBase() {
        WebDriverManager.quitDriver();
    }
}
```

---

## 14. `README.md` (Execution & Setup Guide)
**Location:** `README.md`
```markdown
# OrangeHRM Selenium Test Automation Framework

## Overview

This is a scalable and robust test automation framework for the OrangeHRM application using Selenium WebDriver 4.x, TestNG, and the Page Object Model. Test data and configurations are managed via properties files, supporting cross-browser and parallel execution.

## Project Structure

```
project-root/
??? pom.xml
??? testng.xml
??? config.properties
??? src/
?   ??? test/
?       ??? java/
?           ??? com.test.pages/   # Page Objects
?           ??? com.test.tests/   # Test classes
?           ??? com.test.utils/   # Utilities
```

## Prerequisites

- Java 17 or higher
- Maven 3.6.x or higher
- Chrome/Firefox/Edge browsers installed locally
- Internet access for Maven dependencies

## Setup Instructions

1. **Clone the repository**  
   Clone or download the project to your local machine.

2. **Install dependencies**  
   Navigate to the project root. Run  
   ```
   mvn clean compile
   ```

3. **Edit config.properties as needed**  
   Update credentials, base URL, browser, and timeouts to fit your environment.

4. **Run Test Suite**  
   ```
   mvn test
   ```
   or
   ```
   mvn clean test
   ```

5. **View Test Reports**  
   ExtentReports and screenshots (on failure) will be available under `./reports/` and `./screenshots/`.

## Browser Selection

Set `browser=chrome`, `firefox`, or `edge` in `config.properties` for cross-browser testing.

## Parallel Execution

Parallel test execution is configured via `testng.xml`.

## Support

For issues or enhancements, please log a ticket in the repository.

---
**End of README**
```