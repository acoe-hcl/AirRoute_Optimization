
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class DashboardPipelineOrderLogoutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String appUrl = "https://your-app-url.com";
    private final String username = "testuser";
    private final String password = "P@ssw0rd123";
    private final List<String> pipelineStatuses = Arrays.asList("Submitted", "Processing", "Fulfillment", "In Transit", "Delivered");

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testLoginAndDashboardValidation() {
        // Launch Application
        driver.get(appUrl);
        Assert.assertTrue(driver.getTitle().length() > 0, "Application did not launch successfully.");
        
        // Login
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("loginBtn"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginBtn.click();

        // Verify Dashboard Display
        WebElement dashboardLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardTitle")));
        Assert.assertTrue(dashboardLabel.isDisplayed(), "Dashboard is not displayed after login.");

        WebElement userNameLabel = driver.findElement(By.id("userNameDisplay"));
        Assert.assertEquals(userNameLabel.getText(), username, "Dashboard displays incorrect username.");
    }

    @Test(priority = 2)
    public void testPipelineStatusValidation() {
        for (String status : pipelineStatuses) {
            // Click pipeline status section/tile
            WebElement statusTile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'pipeline-status') and .='" + status + "']")));
            statusTile.click();

            // Verify List of Orders for Selected Status
            WebElement ordersList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ordersList")));
            List<WebElement> orders = ordersList.findElements(By.xpath(".//div[contains(@class,'order-row')]"));
            Assert.assertTrue(orders.size() > 0, "No orders displayed in pipeline status: " + status);

            // Validate Key Order Information
            for (WebElement order : orders) {
                WebElement orderId = order.findElement(By.className("order-id"));
                WebElement orderDate = order.findElement(By.className("order-date"));
                WebElement orderStatus = order.findElement(By.className("order-status"));

                Assert.assertTrue(orderId.getText().matches("\\d+"), "Order ID is invalid");
                Assert.assertTrue(orderDate.getText().matches("\\d{4}-\\d{2}-\\d{2}"), "Order date format is invalid"); // YYYY-MM-DD
                Assert.assertEquals(orderStatus.getText(), status, "Order status does not match selected pipeline");
            }

            // Return to Dashboard
            WebElement dashboardBtn = driver.findElement(By.id("dashboardBtn"));
            dashboardBtn.click();

            // Verify Dashboard is displayed again
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardTitle")));
        }
    }

    @Test(priority = 3)
    public void testPipelinePageAndOrderDetailsValidation() {
        // Click Pipeline from side panel
        WebElement pipelineSidePanelOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("pipelineSidePanel")));
        pipelineSidePanelOption.click();

        // Verify Pipeline Page Loaded
        WebElement pipelinePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pipelinePageTitle")));
        Assert.assertTrue(pipelinePageTitle.isDisplayed(), "Pipeline page did not load.");

        // Verify orders listed
        WebElement pipelineOrderList = driver.findElement(By.id("pipelineOrdersList"));
        List<WebElement> pipelineOrders = pipelineOrderList.findElements(By.xpath(".//div[contains(@class,'pipeline-order-row')]"));
        Assert.assertTrue(pipelineOrders.size() > 0, "No orders are listed on the Pipeline page.");

        // Select any order and open details
        WebElement anyOrder = pipelineOrders.get(0);
        anyOrder.click();

        // Verify order details page displays correct information
        WebElement orderDetailsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderDetailsSection")));
        Assert.assertTrue(orderDetailsSection.isDisplayed(), "Order details page not displayed.");

        WebElement orderId = orderDetailsSection.findElement(By.id("orderDetailID"));
        WebElement customerName = orderDetailsSection.findElement(By.id("orderDetailCustomerName"));
        WebElement items = orderDetailsSection.findElement(By.id("orderDetailItems"));
        WebElement status = orderDetailsSection.findElement(By.id("orderDetailStatus"));
        WebElement timeline = orderDetailsSection.findElement(By.id("orderDetailTimeline"));

        Assert.assertTrue(orderId.getText().matches("\\d+"), "Order Detail: Invalid Order ID");
        Assert.assertTrue(customerName.getText().length() > 0, "Order Detail: Customer Name missing");
        Assert.assertTrue(items.getText().length() > 0, "Order Detail: Items missing");
        Assert.assertTrue(pipelineStatuses.contains(status.getText()), "Order Detail: Invalid status");
        Assert.assertTrue(timeline.isDisplayed(), "Order Detail: Timeline not displayed");
    }

    @Test(priority = 4)
    public void testLogoutValidation() {
        // Click Logout/Sign Out button
        WebElement logoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutBtn")));
        logoutBtn.click();

        // Verify redirected to login page
        WebElement loginPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginPageTitle")));
        Assert.assertTrue(loginPageTitle.isDisplayed(), "User not redirected to login page after logout.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
