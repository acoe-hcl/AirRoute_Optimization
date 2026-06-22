
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

public class HomeDashboardPipelineOrderLogoutTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://your-app-url.com";
    private String username = "testuser";
    private String password = "P@ssw0rd123";

    @BeforeClass
    public void setUp() {
        // Set path for chromedriver if necessary
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void testEndToEndDashboardPipelineOrderLogout() {
        // Launch application URL
        driver.get(baseUrl);
        Assert.assertEquals(driver.getTitle(), "Login", "Application did not launch correctly.");

        // Enter username and password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();

        // Verify Home Dashboard is displayed with correct username
        WebElement dashboardTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Dashboard')]")));
        Assert.assertTrue(dashboardTitle.isDisplayed(), "Dashboard is not displayed.");

        WebElement userNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNameLabel")));
        Assert.assertEquals(userNameLabel.getText().trim(), username, "Displayed username is incorrect.");

        // Pipeline statuses to validate
        String[] pipelineStatuses = {"Submitted", "Processing", "Fulfillment", "In Transit", "Delivered"};

        for (String status : pipelineStatuses) {
            // Click pipeline status tile/section
            WebElement tile = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='pipeline-status-tile' and .='" + status + "']")
            ));
            tile.click();

            // Verify list of orders is displayed
            List<WebElement> orderRows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@class='order-list']/div[@class='order-row']")
            ));
            Assert.assertTrue(orderRows.size() > 0, "No orders found for status: " + status);

            // Validate key order information in the pipeline view
            for (WebElement order : orderRows) {
                WebElement orderIdElem = order.findElement(By.className("order-id"));
                WebElement orderDateElem = order.findElement(By.className("order-date"));
                WebElement orderStatusElem = order.findElement(By.className("order-status"));

                Assert.assertNotNull(orderIdElem.getText(), "Order ID is missing.");
                Assert.assertNotNull(orderDateElem.getText(), "Order Date is missing.");
                Assert.assertEquals(orderStatusElem.getText().trim(), status, "Order Status mismatch.");
            }

            // Click Dashboard to return
            wait.until(ExpectedConditions.elementToBeClickable(By.id("dashboardButton"))).click();
            // Verify dashboard again
            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Dashboard')]"))).isDisplayed());
        }

        // Click Pipeline option from side panel
        WebElement pipelineSidePanel = wait.until(ExpectedConditions.elementToBeClickable(By.id("sidePanelPipeline")));
        pipelineSidePanel.click();

        // Verify Pipeline page loaded and list of orders visible
        WebElement pipelinePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Pipeline')]")));
        Assert.assertTrue(pipelinePageTitle.isDisplayed(), "Pipeline page not loaded.");

        List<WebElement> pipelineOrders = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//div[@class='pipeline-orders-list']/div[@class='order-row']")
        ));
        Assert.assertTrue(pipelineOrders.size() > 0, "No orders on Pipeline page.");

        // Select any order and open order details page
        WebElement anyOrder = pipelineOrders.get(0);
        anyOrder.click();

        // Verify order details
        WebElement orderDetailsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h3[contains(text(),'Order Details')]")
        ));
        Assert.assertTrue(orderDetailsTitle.isDisplayed(), "Order Details page not displayed.");

        WebElement orderIdDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderDetailsOrderId")));
        WebElement customerNameDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderDetailsCustomerName")));
        WebElement itemsDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderDetailsItems")));
        WebElement statusDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderDetailsStatus")));
        WebElement timelineDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderDetailsTimeline")));

        Assert.assertNotNull(orderIdDetail.getText(), "Order ID missing in details.");
        Assert.assertNotNull(customerNameDetail.getText(), "Customer Name missing in details.");
        Assert.assertTrue(itemsDetail.getText().length() > 0, "Items missing in details.");
        Assert.assertTrue(statusDetail.getText().length() > 0, "Status missing in details.");
        Assert.assertTrue(timelineDetail.isDisplayed(), "Timeline missing in details.");

        // Click Logout/Sign Out
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutButton"))).click();

        // Verify redirect to login page
        WebElement loginPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Login')]")));
        Assert.assertTrue(loginPageTitle.isDisplayed(), "User not redirected to login page after logout.");
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
