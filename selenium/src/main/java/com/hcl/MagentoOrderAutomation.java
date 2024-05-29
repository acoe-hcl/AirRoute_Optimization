Here is the Selenium java automation test script file for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderAutomation {

    public static void main(String[] args) {
        // Set the browser driver path
        System.setProperty("webdriver.chrome.driver", "path_to_chrome_driver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Set the browser name and page name
        String browserName = "Home Page";
        String pageName = "Home Page";

        // Launch the application
        driver.get("[https://magento.softwaretestingboard.com/|https://magento.softwaretestingboard.com/]");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Set the browser name and page name
        browserName = "Customer Login";
        pageName = "Customer Login";

        // Enter email and password
        driver.findElement(By.name("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.cssSelector("button[id='send2']")).click();

        // Set the browser name and page name
        browserName = "Home Page";
        pageName = "Home Page";

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//div[@class='panel block-content']//a[text()='Gear']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();

        // Click on the "Bags" link
        driver.findElement(By.xpath("//a[text()='Bags']")).click();

        // Set the browser name and page name
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";

        // Click on the "Overnight Duffle" image
        driver.findElement(By.linkText("Overnight Duffle")).click();

        // Set the browser name and page name
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";

        // Click on the "Add to Cart" button
        driver.findElement(By.cssSelector("button[class='button btn-cart']")).click();

        // Click on the "My Cart" link
        driver.findElement(By.id("cart")).click();

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.cssSelector("button[class='button btn-proceed-checkout']")).click();

        // Set the browser name and page name
        browserName = "Checkout";
        pageName = "Checkout";

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.xpath("//table//td[text()='Overnight Duffle']")).getText();
        if(orderSummary.equals("Overnight Duffle")){
            System.out.println("Order Summary contains 'Overnight Duffle' product");
        } else {
            System.out.println("Order Summary does not contain 'Overnight Duffle' product");
        }

        // Click on the "New Address" button
        driver.findElement(By.cssSelector("button[title='New Address']")).click();

        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("postcode")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.cssSelector("button[title='Ship Here']")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.xpath("//label[text()='Fixed']")).click();

        // Click on the "Next" button
        driver.findElement(By.cssSelector("button[title='Next']")).click();

        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // Click on the "Place Order" button
        driver.findElement(By.id("checkout-place-order")).click();

        // Set the browser name and page name
        browserName = "Success Page";
        pageName = "Success Page";

        // Verify the success message
        String successMessage = driver.findElement(By.cssSelector("h1[class='page-title']")).getText();
        if(successMessage.equals("Thank you for your purchase!")){
            System.out.println("'Thank you for your purchase!' message is displayed");
        } else {
            System.out.println("'Thank you for your purchase!' message is not displayed");
        }

        // Click on the "Change" button
        driver.findElement(By.cssSelector("a[title='Change']")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();

    }

}
```

Note: Replace "path_to_chrome_driver" with the actual path to your Chrome Driver.