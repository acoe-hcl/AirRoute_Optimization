
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

public class EndToEndDashboardPipelineOrderLogoutTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String appUrl = "https://your-application-url.com"; // Replace with actual URL
    private final String username = "testuser";
    private final String password = "P@ssw0rd123";
    private final List<String> pipelineStatuses = Arrays.asList(
            "Submitted", "Processing", "Fulfillment", "In Transit", "Delivered"
    );

    @BeforeClass
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void testEndToEndDashboardPipelineOrderLogout() {
        // Step 1: Launch application
        driver.get(appUrl);
        Assert.assertEquals(driver.getCurrentUrl(), appUrl);

        // Step 2: Login with valid credentials
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("loginBtn"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginBtn.click();

        // Step 3: Verify Dashboard is displayed, validate user name
        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardHeader")));
        Assert.assertTrue(dashboardHeader.isDisplayed());
        WebElement userProfile = driver.findElement(By.id("userProfileName"));
        Assert.assertEquals(userProfile.getText().trim(), username);

        // Step 4: Validate each pipeline status
        for (String status : pipelineStatuses) {
            WebElement pipelineTile = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='pipeline-status' and text()='" + status + "']")
            ));
            pipelineTile.click();

            // Verify orders list for status is displayed
            WebElement ordersList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("ordersList")
            ));
            Assert.assertTrue(ordersList.isDisplayed());

            // Validate order info in pipeline view
            List<WebElement> orders = ordersList.findElements(By.className("orderRow"));
            Assert.assertTrue(orders.size() > 0, "No orders found for status: " + status);

            for (WebElement order : orders) {
                WebElement orderId = order.findElement(By.className("orderId"));
                WebElement orderDate = order.findElement(By.className("orderDate"));
                WebElement orderStatus = order.findElement(By.className("orderStatus"));
                Assert.assertFalse(orderId.getText().isEmpty(), "Order ID should not be empty");
                Assert.assertFalse(orderDate.getText().isEmpty(), "Order Date should not be empty");
                Assert.assertEquals(orderStatus.getText(), status, "Order status mismatch");
            }

            // Click Dashboard button to return
            WebElement dashboardBtn = driver.findElement(By.id("dashboardBtn"));
            dashboardBtn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardHeader")));
        }

        // Step 5: Access Pipeline page via side panel
        WebElement sidePanelPipeline = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("sidePanelPipeline")
        ));
        sidePanelPipeline.click();

        // Step 6: Verify Pipeline page loaded with orders
        WebElement pipelinePageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("pipelinePageHeader")
        ));
        Assert.assertTrue(pipelinePageHeader.isDisplayed());
        WebElement pipelineOrdersList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pipelineOrdersList")));
        List<WebElement> pipelineOrders = pipelineOrdersList.findElements(By.className("orderRow"));
        Assert.assertTrue(pipelineOrders.size() > 0, "No orders found in pipeline");

        // Step 7: Select any order, open details page and validate info
        WebElement selectedOrder = pipelineOrders.get(0);
        selectedOrder.click();

        WebElement orderDetailsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderDetailsSection")));
        Assert.assertTrue(orderDetailsSection.isDisplayed());

        WebElement detailsOrderId = orderDetailsSection.findElement(By.id("orderDetailId"));
        WebElement detailsCustomerName = orderDetailsSection.findElement(By.id("orderDetailCustomerName"));
        WebElement detailsOrderItems = orderDetailsSection.findElement(By.id("orderDetailItems"));
        WebElement detailsOrderStatus = orderDetailsSection.findElement(By.id("orderDetailStatus"));
        WebElement detailsOrderTimeline = orderDetailsSection.findElement(By.id("orderDetailTimeline"));

        Assert.assertFalse(detailsOrderId.getText().isEmpty(), "Order ID is empty");
        Assert.assertFalse(detailsCustomerName.getText().isEmpty(), "Customer Name is empty");
        Assert.assertFalse(detailsOrderItems.getText().isEmpty(), "Order Items is empty");
        Assert.assertTrue(pipelineStatuses.contains(detailsOrderStatus.getText()), "Order Status invalid");
        Assert.assertTrue(detailsOrderTimeline.isDisplayed());

        // Step 8: Logout and verify redirect to login page
        WebElement logoutBtn = driver.findElement(By.id("logoutBtn"));
        logoutBtn.click();

        WebElement loginPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginPageHeader")));
        Assert.assertTrue(loginPage.isDisplayed());
        Assert.assertEquals(loginPage.getText(), "Login");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
