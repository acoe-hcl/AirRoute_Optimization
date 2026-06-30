## 1. `pom.xml` (Maven Configuration)
**Location:** `pom.xml`
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.test</groupId>
  <artifactId>orangehrm-automation</artifactId>
  <version>1.0-SNAPSHOT</version>
  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>
  <dependencies>
    <!-- Selenium Java 4.x -->
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>4.18.1</version>
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
    <!-- SLF4J API -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>2.0.13</version>
    </dependency>
    <!-- Log4j binding for SLF4J -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>2.0.13</version>
    </dependency>
    <!-- ExtentReports -->
    <dependency>
      <groupId>com.aventstack</groupId>
      <artifactId>extentreports</artifactId>
      <version>5.1.1</version>
    </dependency>
  </dependencies>
  <build>
    <plugins>
      <!-- Maven Surefire Plugin for running TestNG tests -->
      <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.2.5</version>
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
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="OrangeHRM Automation Suite" parallel="tests" thread-count="3">
    <listeners>
        <listener class-name="com.test.utils.ExtentReportListener"/>
    </listeners>
    <test name="Employee Management Tests" parallel="methods" thread-count="3">
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
# Application URLs and credentials
base.url=https://opensource-demo.orangehrmlive.com/
admin.username=admin_user
admin.password=admin_pass

# Employee data
employee.firstName=John
employee.lastName=Doe
employee.id=12345
employee.username=johndoe
employee.password=Password123!

# Invalid login data
invalid.username=invalid_user
invalid.password=invalid_pass

# Web drivers
browser=chrome

# Timeouts (in seconds)
explicit.wait=15

# Logins
login.success.url=/dashboard
login.error.message=Invalid credentials

# Employee search
employee.no.records=No Records Found
```

## 4. `BasePage.java` (Base Page Object)
**Location:** `src/test/java/com/test/pages/BasePage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.test.utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BasePage contains common web interaction methods and explicit wait logic.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    /**
     * BasePage constructor initializes WebDriver and WebDriverWait.
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        int expWaitSec = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(expWaitSec));
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * Wait for element visibility.
     * @param element WebElement to wait for
     */
    protected void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for element to be clickable.
     * @param element WebElement to wait for
     */
    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for URL to contain a fragment.
     * @param urlFragment String fragment to check in current URL
     */
    protected void waitForUrlContains(String urlFragment) {
        wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    /**
     * Get current page title.
     * @return String page title
     */
    public String getPageTitle() {
        return driver.getTitle();
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
 * Page Object class for OrangeHRM Login Page.
 */
public class LoginPage extends BasePage {

    // Locators using @FindBy annotation
    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement errorMessageDiv;

    /**
     * Constructor for LoginPage. Initializes page factory elements.
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Enter username.
     * @param username Username value
     */
    public void enterUsername(String username) {
        waitForElementVisible(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        logger.info("Entered username: " + username);
    }

    /**
     * Enter password.
     * @param password Password value
     */
    public void enterPassword(String password) {
        waitForElementVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        logger.info("Entered password (masked).");
    }

    /**
     * Click the login button.
     */
    public void clickLogin() {
        waitForElementClickable(loginButton);
        loginButton.click();
        logger.info("Clicked Login button.");
    }

    /**
     * Perform login action.
     * @param username Username value
     * @param password Password value
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Get login error message text.
     * @return String errorMessage
     */
    public String getLoginErrorMessage() {
        waitForElementVisible(errorMessageDiv);
        String message = errorMessageDiv.getText();
        logger.info("Login error message: " + message);
        return message;
    }

    /**
     * Check if error message is displayed.
     * @return true if error message visible
     */
    public boolean isErrorMessageDisplayed() {
        try {
            waitForElementVisible(errorMessageDiv);
            boolean displayed = errorMessageDiv.isDisplayed();
            logger.info("Login error message displayed: " + displayed);
            return displayed;
        } catch (Exception e) {
            logger.error("Error message not found: " + e.getMessage());
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
 * Page Object for OrangeHRM Dashboard Page.
 */
public class DashboardPage extends BasePage {

    @FindBy(xpath = "//header//span[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//aside//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(xpath = "//span[text()='Logout']")
    private WebElement logoutMenu;

    /**
     * DashboardPage constructor. Initializes page factory elements.
     * @param driver WebDriver instance.
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Check if Dashboard header is present.
     * @return true if header visible
     */
    public boolean isAt() {
        try {
            waitForElementVisible(dashboardHeader);
            logger.info("Dashboard header is displayed.");
            return dashboardHeader.isDisplayed();
        } catch (Exception e) {
            logger.error("Dashboard header not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Navigate to PIM main menu.
     */
    public void navigateToPIM() {
        waitForElementClickable(pimMenu);
        pimMenu.click();
        logger.info("Navigated to PIM menu.");
    }

    /**
     * Logout using menu.
     */
    public void logout() {
        // Open user menu then click Logout
        WebElement userMenu = driver.findElement(
            org.openqa.selenium.By.xpath("//span[@class='oxd-userdropdown-tab']"));
        waitForElementClickable(userMenu);
        userMenu.click();
        waitForElementClickable(logoutMenu);
        logoutMenu.click();
        logger.info("Clicked logout from Dashboard.");
    }
}
```

## 7. `PIMPage.java` (PIM Management Page Object)
**Location:** `src/test/java/com/test/pages/PIMPage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * Page Object for OrangeHRM PIM Section (Add Employee, Employee List).
 */
public class PIMPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href, 'addEmployee')]")
    private WebElement addEmployeeLink;

    @FindBy(xpath = "//a[contains(@href, 'viewEmployeeList')]")
    private WebElement employeeListTab;

    // Add Employee Form
    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    @FindBy(xpath = "//label[text()='Employee Id']/../following-sibling::div/input")
    private WebElement employeeIdInput;

    @FindBy(css = "input[type='checkbox'][name='createLoginDetails']")
    private WebElement createLoginDetailsCheckbox;

    @FindBy(xpath = "//label[text()='Username']/../following-sibling::div/input")
    private WebElement newUsernameInput;

    @FindBy(xpath = "//label[text()='Password']/../following-sibling::div/input")
    private WebElement newPasswordInput;

    @FindBy(xpath = "//label[text()='Confirm Password']/../following-sibling::div/input")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Employee List Search
    @FindBy(xpath = "//input[@placeholder='Type for hints...']/../following-sibling::div//input")
    private WebElement employeeNameSearchInput;

    @FindBy(xpath = "//label[text()='Employee Id']/../following-sibling::div/input")
    private WebElement employeeIdSearchInput;
    
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@role='rowgroup']//div[@role='row']")
    private List<WebElement> employeeRows;

    @FindBy(xpath = "//span[contains(text(),'No Records Found')]")
    private WebElement noRecordsFoundMessage;

    /**
     * PIMPage constructor.
     * @param driver WebDriver instance
     */
    public PIMPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Click Add Employee link.
     */
    public void goToAddEmployee() {
        waitForElementClickable(addEmployeeLink);
        addEmployeeLink.click();
        logger.info("Clicked Add Employee");
    }

    /**
     * Add new employee with login details.
     */
    public void addEmployee(String firstName, String lastName, String employeeId,
                            String username, String password) {
        waitForElementVisible(firstNameInput);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        employeeIdInput.clear();
        employeeIdInput.sendKeys(employeeId);

        waitForElementClickable(createLoginDetailsCheckbox);
        if (!createLoginDetailsCheckbox.isSelected()) {
            createLoginDetailsCheckbox.click();
        }
        waitForElementVisible(newUsernameInput);
        newUsernameInput.clear();
        newUsernameInput.sendKeys(username);

        newPasswordInput.clear();
        newPasswordInput.sendKeys(password);

        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);

        logger.info("Filled Add Employee Form: " + firstName + " " + lastName);
        saveButton.click();
        logger.info("Clicked Save for Add Employee");
    }

    /**
     * Navigate to Employee List tab.
     */
    public void goToEmployeeList() {
        waitForElementClickable(employeeListTab);
        employeeListTab.click();
        logger.info("Navigated to Employee List");
    }

    /**
     * Search employee by given ID.
     * @param employeeId Employee ID
     */
    public void searchEmployeeById(String employeeId) {
        waitForElementVisible(employeeIdSearchInput);
        employeeIdSearchInput.clear();
        employeeIdSearchInput.sendKeys(employeeId);
        searchButton.click();
        logger.info("Searched for Employee ID: " + employeeId);
    }

    /**
     * Returns the row of employee by ID, if found.
     * @param employeeId Employee ID to locate
     * @return WebElement row if exists, else null
     */
    public WebElement getEmployeeRowById(String employeeId) {
        for (WebElement row : employeeRows) {
            if (row.getText().contains(employeeId)) {
                logger.info("Employee row located for ID: " + employeeId);
                return row;
            }
        }
        logger.info("Employee ID not found in search results: " + employeeId);
        return null;
    }

    /**
     * Get "No Records Found" message element.
     * @return true if found
     */
    public boolean isNoRecordsFoundDisplayed() {
        try {
            waitForElementVisible(noRecordsFoundMessage);
            boolean displayed = noRecordsFoundMessage.isDisplayed();
            logger.info("'No Records Found' message displayed: " + displayed);
            return displayed;
        } catch (Exception e) {
            logger.error("'No Records Found' message not visible: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete employee by ID.
     * @param employeeId Employee ID to delete
     * @return true if delete clicked, false if row not found
     */
    public boolean deleteEmployeeById(String employeeId) {
        WebElement row = getEmployeeRowById(employeeId);
        if (row != null) {
            WebElement deleteButton = row.findElement(
                org.openqa.selenium.By.xpath(".//button[contains(@class,'delete')]"));
            waitForElementClickable(deleteButton);
            deleteButton.click();
            logger.info("Clicked delete for employee ID: " + employeeId);
            return true;
        }
        return false;
    }
}
```

## 8. `PersonalDetailsPage.java` (Personal Details Page Object)
**Location:** `src/test/java/com/test/pages/PersonalDetailsPage.java`
```java
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for Personal Details page after employee creation.
 */
public class PersonalDetailsPage extends BasePage {

    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement personalDetailsHeader;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//label[text()='Employee Id']/../following-sibling::div/input")
    private WebElement employeeIdInput;

    /**
     * Constructor for PersonalDetailsPage.
     * @param driver WebDriver instance
     */
    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify you are on Personal Details page.
     * @return true if header is visible
     */
    public boolean isAt() {
        try {
            waitForElementVisible(personalDetailsHeader);
            logger.info("On Personal Details page.");
            return personalDetailsHeader.isDisplayed();
        } catch (Exception e) {
            logger.error("Personal Details page not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get the Employee ID value.
     * @return Employee ID as String
     */
    public String getEmployeeId() {
        waitForElementVisible(employeeIdInput);
        return employeeIdInput.getAttribute("value");
    }
}
```

## 9. `ConfigReader.java` (Configuration Properties Utility)
**Location:** `src/test/java/com/test/utils/ConfigReader.java`
```java
package com.test.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Utility class to read config.properties for test configuration and data.
 */
public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("config.properties");
            properties.load(file);
        } catch (Exception e) {
            throw new RuntimeException("Could not load config.properties: " + e.getMessage());
        }
    }

    /**
     * Get property string value by key.
     * @param key Property key
     * @return Property value as String
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
```

## 10. `WebDriverManager.java` (WebDriver Factory Utility)
**Location:** `src/test/java/com/test/utils/WebDriverManager.java`
```java
package com.test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

/**
 * Utility class to initialize WebDriver instances for multiple browsers.
 */
public class WebDriverManagerFactory {

    /**
     * Get WebDriver instance based on browser type.
     * @param browserName Browser name as string
     * @return WebDriver instance
     */
    public static WebDriver getDriver(String browserName) {
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--start-maximized");
                driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--width=1920");
                ffOptions.addArguments("--height=1080");
                driver = new org.openqa.selenium.firefox.FirefoxDriver(ffOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edOptions = new EdgeOptions();
                driver = new org.openqa.selenium.edge.EdgeDriver(edOptions);
                driver.manage().window().maximize();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browserName);
        }
        return driver;
    }
}
```

## 11. `ExtentReportListener.java` (ExtentReport TestNG Listener)
**Location:** `src/test/java/com/test/utils/ExtentReportListener.java`
```java
package com.test.utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.*;

import java.util.Date;

/**
 * ExtentReports listener for TestNG to generate test reports.
 */
public class ExtentReportListener implements ITestListener {
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    static {
        String path = "target/ExtentReport_" + new Date().getTime() + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
```

## 12. `BaseTest.java` (Base Test Class)
**Location:** `src/test/java/com/test/tests/BaseTest.java`
```java
package com.test.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import com.test.utils.ConfigReader;
import com.test.utils.WebDriverManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseTest provides WebDriver setup and teardown for all tests.
 */
public abstract class BaseTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Get WebDriver instance for current thread.
     * @return WebDriver
     */
    protected WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        driver.set(WebDriverManagerFactory.getDriver(browser));
        logger.info("WebDriver started for browser: " + browser);
        getDriver().manage().deleteAllCookies();
        getDriver().get(ConfigReader.getProperty("base.url"));
        logger.info("Navigated to URL: " + ConfigReader.getProperty("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            logger.info("WebDriver instance quit.");
            driver.remove();
        }
    }
}
```

## 13. `EmployeeManagementTest.java` (Employee Management Test Cases)
**Location:** `src/test/java/com/test/tests/EmployeeManagementTest.java`
```java
package com.test.tests;

import com.test.pages.*;
import com.test.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Automation Test Suite: Comprehensive Employee Management in OrangeHRM
 */
public class EmployeeManagementTest extends BaseTest {

    /**
     * Test Case 1: Login as Administrator and verify dashboard access.
     */
    @Test(description = "Verify administrator can login successfully and access dashboard.")
    public void testAdminValidLogin() {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAt(), "Dashboard should be visible after login.");
    }

    /**
     * Test Case 2: Add new employee with login details, then verify landing on Personal Details page.
     */
    @Test(description = "Verify addition of a new employee and redirect to Personal Details page.")
    public void testAddNewEmployee() {
        WebDriver driver = getDriver();
        // Login as admin
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAt(), "Should be on Dashboard after login.");

        dashboardPage.navigateToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToAddEmployee();

        pimPage.addEmployee(
            ConfigReader.getProperty("employee.firstName"),
            ConfigReader.getProperty("employee.lastName"),
            ConfigReader.getProperty("employee.id"),
            ConfigReader.getProperty("employee.username"),
            ConfigReader.getProperty("employee.password")
        );

        // Verify redirect to Personal Details
        PersonalDetailsPage personalPage = new PersonalDetailsPage(driver);
        Assert.assertTrue(personalPage.isAt(), "Should be redirected to Personal Details page.");
        String createdEmployeeId = personalPage.getEmployeeId();
        Assert.assertEquals(createdEmployeeId, ConfigReader.getProperty("employee.id"),
                "Employee ID should match the entered value.");
    }

    /**
     * Test Case 3: Verify newly added employee appears in Employee List.
     */
    @Test(description = "Verify the created employee appears in the Employee List after addition.")
    public void testVerifyEmployeeCreation() {
        WebDriver driver = getDriver();
        // Login as admin
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAt(), "Should be on Dashboard.");

        dashboardPage.navigateToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();
        pimPage.searchEmployeeById(ConfigReader.getProperty("employee.id"));

        WebElement row = pimPage.getEmployeeRowById(ConfigReader.getProperty("employee.id"));
        Assert.assertNotNull(row, "Employee row should be present in Employee List.");
        String rowText = row.getText();
        Assert.assertTrue(rowText.contains(ConfigReader.getProperty("employee.firstName"))
                && rowText.contains(ConfigReader.getProperty("employee.lastName")),
                "Employee full name should be in the row (" + rowText + ").");
    }

    /**
     * Test Case 4: Delete employee and confirm removal from Employee List.
     */
    @Test(description = "Delete the employee and verify removal from Employee List.")
    public void testDeleteEmployee() {
        WebDriver driver = getDriver();
        // Login as admin
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAt(), "Should be on Dashboard.");

        dashboardPage.navigateToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();
        pimPage.searchEmployeeById(ConfigReader.getProperty("employee.id"));

        // Attempt to delete employee row
        boolean deleteInitiated = pimPage.deleteEmployeeById(ConfigReader.getProperty("employee.id"));
        Assert.assertTrue(deleteInitiated, "Delete button should be available for the employee.");

        // Confirm deletion in modal
        WebElement confirmDeleteBtn = driver.findElement(
                org.openqa.selenium.By.xpath("//button[normalize-space()='Yes, Delete']"));
        confirmDeleteBtn.click();

        // Wait for potential removal
        pimPage.searchEmployeeById(ConfigReader.getProperty("employee.id"));
        Assert.assertTrue(pimPage.isNoRecordsFoundDisplayed(),
                "'No Records Found' should be displayed after deletion.");
    }

    /**
     * Test Case 5: Verify employee record is not found after deletion (with re-login).
     */
    @Test(description = "Login as admin and assert employee is not found after deletion.")
    public void testVerifyEmployeeDeletion() {
        WebDriver driver = getDriver();
        // Login as admin
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAt(), "Should be on Dashboard.");

        dashboardPage.navigateToPIM();
        PIMPage pimPage = new PIMPage(driver);
        pimPage.goToEmployeeList();
        pimPage.searchEmployeeById(ConfigReader.getProperty("employee.id"));
        Assert.assertTrue(pimPage.isNoRecordsFoundDisplayed(),
                "'No Records Found' should be shown if employee is deleted.");
    }

    /**
     * Test Case 6: Invalid login attempt must display error message.
     */
    @Test(description = "Attempt invalid login and verify error message is displayed.")
    public void testInvalidLoginAttempt() {
        WebDriver driver = getDriver();
        // Logout is not enforced, because each test launches the login page.

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("invalid.username"));
        loginPage.enterPassword(ConfigReader.getProperty("invalid.password"));
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid login.");
        String actualError = loginPage.getLoginErrorMessage();
        Assert.assertTrue(actualError.contains(ConfigReader.getProperty("login.error.message")),
                "Error message should indicate login failure. Actual: " + actualError);
    }
}
```

## 14. `README.md` (Project Documentation)
**Location:** `README.md`
```markdown
# OrangeHRM Test Automation Framework

#### Comprehensive Employee Management Test Suite (Java + Selenium WebDriver + TestNG + POM)

## Project Overview
This test automation framework provides a robust and scalable solution for automating the critical employee management workflows in the OrangeHRM web application using the Page Object Model design pattern, Selenium WebDriver 4.x, and TestNG.

### Features
- Page Object Model for maintainable and readable UI tests
- Complete parameterization with `config.properties`
- Parallel cross-browser testing with TestNG/Maven
- Centralized WebDriver management using WebDriverManager
- Real-time reporting with ExtentReports
- Logging with SLF4J and Log4j
- Synchronization via WebDriverWait and explicit waits (no sleep)
- Assertions with descriptive error messages
- TestNG listeners for reporting

## Prerequisites
- Java JDK 17 or higher
- Maven 3.6+
- Internet access for dependency download and driver management
- OrangeHRM application should be accessible

## Setup Instructions

1. **Clone the repository**

   ```
   git clone https://github.com/your-repo/orangehrm-automation.git
   cd orangehrm-automation
   ```

2. **Update Configuration**

   - Open `config.properties` and update the test data, URLs, and browser as needed.

3. **Build the Project**

   ```
   mvn clean install
   ```

4. **Run the Tests**

   ```
   mvn test
   ```

   or run specific browser:

   ```
   mvn test -Dbrowser=firefox
   ```

## Project Structure

```
project-root/
??? pom.xml
??? testng.xml
??? config.properties
??? src/
?   ??? test/
?       ??? java/
?           ??? com.test.pages/
?           ??? com.test.tests/
?           ??? com.test.utils/
??? README.md
```

## Results

- Detailed HTML reports generated in `target/` (ExtentReports).
- Log files (if configured for output file).

## Support

Feel free to raise issues or requests for more automation scenarios!

---
```