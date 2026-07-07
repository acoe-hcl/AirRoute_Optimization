
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

public class DashboardPipelineOrderLogoutTest {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://your-application-url.com"; // Replace with actual application URL

    @BeforeClass
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "path-to-your-chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testLoginAndHomeDashboard() {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("P@ssw0rd123");
        driver.findElement(By.id("loginBtn")).click(); // Update with actual login button locator

        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardHeader")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard is not displayed.");

        WebElement userNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userDisplayName")));
        Assert.assertEquals(userNameElement.getText().trim(), "testuser", "Displayed user name mismatch on dashboard.");
    }

    @Test(priority = 2, dependsOnMethods = "testLoginAndHomeDashboard")
    public void testPipelineStatusValidations() {
        String[] statuses = {"Submitted", "Processing", "Fulfillment", "In Transit", "Delivered"};
        for (String status : statuses) {
            // Click the status tile/section
            String tileXpath = "//div[contains(@class,'pipeline-status') and .//span[text()='" + status + "']]";
            WebElement tile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tileXpath)));
            tile.click();

            // Verify order list for that status is displayed
            List<WebElement> orders = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='order-list']//div[contains(@class,'order-row')]")
            ));
            Assert.assertTrue(orders.size() > 0, "No orders found under status: " + status);

            // Validate key order info for at least one order (first one)
            WebElement firstOrder = orders.get(0);
            WebElement orderIdElem = firstOrder.findElement(By.className("order-id"));
            WebElement orderDateElem = firstOrder.findElement(By.className("order-date"));
            WebElement orderStatusElem = firstOrder.findElement(By.className("order-status"));
            Assert.assertNotNull(orderIdElem.getText(), "Order ID missing for status: " + status);
            Assert.assertTrue(orderDateElem.getText().matches("\\d{2}/\\d{2}/\\d{4}"), "Order Date format invalid");
            Assert.assertEquals(orderStatusElem.getText(), status, "Order status value mismatch");

            // Click Dashboard button to return
            WebElement dashboardBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("dashboardBtn")));
            dashboardBtn.click();

            // Verify dashboard is displayed again
            WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardHeader")));
            Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard is not displayed after returning from status: " + status);
        }
    }

    @Test(priority = 3, dependsOnMethods = "testPipelineStatusValidations")
    public void testPipelinePageOrderDetails() {
        // Open Pipeline from side panel
        WebElement pipelineMenu = wait.until(ExpectedConditions.elementToBeClickable(By.id("pipelineMenu")));
        pipelineMenu.click();

        // Verify pipeline page and orders listed
        WebElement pipelineHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pipelinePageHeader")));
        Assert.assertTrue(pipelineHeader.getText().contains("Pipeline"), "Pipeline page header mismatch");

        List<WebElement> pipelineOrders = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='pipeline-orders']//div[contains(@class,'order-row')]")
        ));
        Assert.assertTrue(pipelineOrders.size() > 0, "No orders found in Pipeline list");

        // Click first order for details
        WebElement selectedOrder = pipelineOrders.get(0);
        String selectedOrderId = selectedOrder.findElement(By.className("order-id")).getText();
        selectedOrder.click();

        // Verify order details page information
        WebElement detailsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderDetailsHeader")));
        Assert.assertTrue(detailsHeader.isDisplayed(), "Order Details header not visible");

        // Check Order ID
        WebElement detailsOrderIdElem = driver.findElement(By.id("detailOrderId"));
        Assert.assertEquals(detailsOrderIdElem.getText(), selectedOrderId, "Order ID mismatch on details page");

        // Check Customer Name
        WebElement customerNameElem = driver.findElement(By.id("detailCustomerName"));
        Assert.assertTrue(!customerNameElem.getText().trim().isEmpty(), "Customer Name missing");

        // Check Items
        List<WebElement> items = driver.findElements(By.xpath("//ul[@id='detailItemsList']/li"));
        Assert.assertTrue(items.size() > 0, "No items found in order details");

        // Check Status
        WebElement statusElem = driver.findElement(By.id("detailOrderStatus"));
        Assert.assertTrue(!statusElem.getText().trim().isEmpty(), "Order Status missing in details");

        // Check Timeline
        WebElement timelineElem = driver.findElement(By.id("detailOrderTimeline"));
        Assert.assertTrue(timelineElem.isDisplayed(), "Order Timeline section missing");
    }

    @Test(priority = 4, dependsOnMethods = "testPipelinePageOrderDetails")
    public void testLogout() {
        // Click logout
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutBtn")));
        logoutBtn.click();

        // Verify redirected to login page
        WebElement loginPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginPage")));
        Assert.assertTrue(loginPage.isDisplayed(), "Login page not displayed after logout");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
