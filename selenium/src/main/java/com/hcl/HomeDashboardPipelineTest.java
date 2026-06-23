
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class HomeDashboardPipelineTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "<path_to_chromedriver>");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void endToEndDashboardPipelineOrderLogoutTest() {
        // Test Data
        String appUrl = "https://your-app-url.com";
        String username = "testuser";
        String password = "P@ssw0rd123";
        String[] pipelineStatuses = {"Submitted", "Processing", "Fulfillment", "In Transit", "Delivered"};

        // Launch the application
        driver.get(appUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginForm")));
        Assert.assertTrue(driver.getTitle().contains("Login")); // Launch Assertion

        // Login
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardPage")));
        WebElement userNameLabel = driver.findElement(By.id("dashboardUserName"));
        Assert.assertEquals(userNameLabel.getText().trim(), username, "User name display mismatch");

        // Dashboard metrics assertion (example: dashboard stats present)
        Assert.assertTrue(driver.findElements(By.className("dashboard-metric")).size() > 0, "Dashboard metrics not displayed");

        // For each pipeline status: validate orders list and order info
        for (String status : pipelineStatuses) {
            WebElement pipelineTile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pipeline-tile' and .='" + status + "']")));
            pipelineTile.click();

            WebElement ordersList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pipelineOrdersList")));
            List<WebElement> orders = ordersList.findElements(By.className("order-row"));
            Assert.assertTrue(orders.size() > 0, "No orders found for status: " + status);

            for (WebElement order : orders) {
                WebElement orderIdElement = order.findElement(By.className("order-id"));
                WebElement orderDateElement = order.findElement(By.className("order-date"));
                WebElement orderStatusElement = order.findElement(By.className("order-status"));

                Assert.assertNotNull(orderIdElement.getText(), "Order ID missing");
                Assert.assertNotNull(orderDateElement.getText(), "Order Date missing");
                Assert.assertEquals(orderStatusElement.getText(), status, "Order Status mismatch");
            }

            // Navigate back to Dashboard
            WebElement dashboardBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardBtn")));
            dashboardBtn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardPage")));
        }

        // Navigate to Pipeline page from side panel
        WebElement sidePanelPipeline = wait.until(ExpectedConditions.elementToBeClickable(By.id("sidePanelPipeline")));
        sidePanelPipeline.click();
        WebElement pipelinePageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pipelinePageHeader")));
        Assert.assertTrue(pipelinePageHeader.getText().contains("Pipeline"), "Pipeline page not loaded");

        // Verify pipeline orders listed
        WebElement pipelineOrdersTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pipelineOrdersTable")));
        List<WebElement> pipelineOrders = pipelineOrdersTable.findElements(By.className("order-row"));
        Assert.assertTrue(pipelineOrders.size() > 0, "No pipeline orders found");

        // Open an order details page
        WebElement orderToOpen = pipelineOrders.get(0);
        String orderIdText = orderToOpen.findElement(By.className("order-id")).getText();
        orderToOpen.click();

        // Order Details Validation
        WebElement orderDetailsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderDetailsPage")));
        String detailsOrderId = orderDetailsPage.findElement(By.id("detailsOrderId")).getText();
        String detailsCustomerName = orderDetailsPage.findElement(By.id("detailsCustomerName")).getText();
        String detailsItems = orderDetailsPage.findElement(By.id("detailsItems")).getText();
        String detailsStatus = orderDetailsPage.findElement(By.id("detailsOrderStatus")).getText();
        String detailsTimeline = orderDetailsPage.findElement(By.id("detailsOrderTimeline")).getText();

        Assert.assertEquals(detailsOrderId, orderIdText, "Order ID does not match details page");
        Assert.assertFalse(detailsCustomerName.isEmpty(), "Customer Name missing");
        Assert.assertFalse(detailsItems.isEmpty(), "Items missing");
        Assert.assertFalse(detailsStatus.isEmpty(), "Status missing");
        Assert.assertFalse(detailsTimeline.isEmpty(), "Timeline missing");

        // Logout and verify redirection to login
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutBtn")));
        logoutBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginForm")));
        Assert.assertEquals(driver.getCurrentUrl(), appUrl + "/login", "User not redirected to login page after logout");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
