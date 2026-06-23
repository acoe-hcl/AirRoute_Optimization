
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class EndToEndDashboardPipelineTest {
    WebDriver driver;
    WebDriverWait wait;

    // Test Data
    String baseUrl = "https://your-app-url.com";
    String username = "testuser";
    String password = "P@ssw0rd123";
    String[] pipelineStatuses = {"Submitted", "Processing", "Fulfillment", "In Transit", "Delivered"};

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testLaunchApplication() {
        driver.get(baseUrl);
        String title = driver.getTitle();
        Assert.assertTrue(title.length() > 0, "Application title should be visible");
        Assert.assertTrue(driver.findElement(By.id("loginUsername")).isDisplayed(), "Username field should be present");
    }

    @Test(priority = 2)
    public void testLoginAndDashboardValidation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginUsername")));
        driver.findElement(By.id("loginUsername")).sendKeys(username);
        driver.findElement(By.id("loginPassword")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardUserName")));
        String userNameDisplayed = driver.findElement(By.id("dashboardUserName")).getText();
        Assert.assertEquals(userNameDisplayed, username, "Dashboard should display correct user name");
        Assert.assertTrue(driver.findElement(By.id("dashboardMetrics")).isDisplayed(), "Dashboard metrics should be visible");
    }

    @Test(priority = 3)
    public void testPipelineStatusValidation() {
        for (String status : pipelineStatuses) {
            By statusTile = By.xpath("//div[contains(@class,'pipeline-tile') and .//span[text()='" + status + "']]");
            wait.until(ExpectedConditions.elementToBeClickable(statusTile));
            driver.findElement(statusTile).click();

            By orderListView = By.id("orderListView");
            wait.until(ExpectedConditions.visibilityOfElementLocated(orderListView));
            List<WebElement> orders = driver.findElements(By.xpath("//div[@id='orderListView']/div[contains(@class,'order-row')]"));
            Assert.assertTrue(orders.size() > 0, "Order list for status '" + status + "' should not be empty");

            for (WebElement order : orders) {
                String orderId = order.findElement(By.xpath(".//span[@class='order-id']")).getText();
                String orderDate = order.findElement(By.xpath(".//span[@class='order-date']")).getText();
                String orderStatus = order.findElement(By.xpath(".//span[@class='order-status']")).getText();
                Assert.assertNotNull(orderId, "Order ID should be present");
                Assert.assertNotNull(orderDate, "Order date should be present");
                Assert.assertEquals(orderStatus, status, "Order status should match pipeline status");
            }

            driver.findElement(By.id("dashboardButton")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardUserName")));
        }
    }

    @Test(priority = 4)
    public void testPipelinePageAccess() {
        driver.findElement(By.id("sidePanelPipeline")).click();

        By pipelinePage = By.id("pipelinePage");
        wait.until(ExpectedConditions.visibilityOfElementLocated(pipelinePage));
        List<WebElement> pipelineOrders = driver.findElements(By.xpath("//div[@id='pipelinePage']//div[contains(@class,'order-row')]"));

        Assert.assertTrue(pipelineOrders.size() > 0, "Pipeline page should list at least one order");
    }

    @Test(priority = 5)
    public void testOrderDetailsValidation() {
        List<WebElement> pipelineOrders = driver.findElements(By.xpath("//div[@id='pipelinePage']//div[contains(@class,'order-row')]"));
        WebElement orderToView = pipelineOrders.get(0); // Select first order for validation

        orderToView.click();

        By orderDetailsPage = By.id("orderDetailsPage");
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderDetailsPage));

        String displayedOrderId = driver.findElement(By.id("orderDetailsID")).getText();
        String displayedCustomerName = driver.findElement(By.id("orderDetailsCustomer")).getText();
        String displayedItems = driver.findElement(By.id("orderDetailsItems")).getText();
        String displayedStatus = driver.findElement(By.id("orderDetailsStatus")).getText();
        String displayedTimeline = driver.findElement(By.id("orderDetailsTimeline")).getText();

        Assert.assertNotNull(displayedOrderId, "Order ID is missing");
        Assert.assertFalse(displayedCustomerName.isEmpty(), "Customer name should be present");
        Assert.assertFalse(displayedItems.isEmpty(), "Order items should be present");
        Assert.assertTrue(displayedStatus.length() > 0, "Order status should be present");
        Assert.assertTrue(displayedTimeline.length() > 0, "Order timeline should be present");
    }

    @Test(priority = 6)
    public void testLogoutFunctionality() {
        driver.findElement(By.id("logoutButton")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginUsername")));
        Assert.assertTrue(driver.findElement(By.id("loginUsername")).isDisplayed(), "Should redirect to Login page after logout");
    }
}
