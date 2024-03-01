import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class WebTestAutomation {

    public static void main(String[] args) throws InterruptedException {
        
        // Set the path of chromedriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        // Navigate to CW TV website
        driver.get("https://www.cwtv.com/");

        // Verify home page is displayed
        WebElement homePage = driver.findElement(By.xpath("//div[@class='homepage']"));
        if (homePage.isDisplayed()) {
            System.out.println("Home page is displayed");
        } else {
            System.out.println("Home page is not displayed");
        }

        // Click on "Series" in the header menu
        WebElement seriesMenu = driver.findElement(By.xpath("//a[text()='Series']"));
        seriesMenu.click();
        
        Thread.sleep(2000); // Wait for page to load
        
        // Verify user is redirected to the Series Page
        WebElement seriesPage = driver.findElement(By.xpath("//h1[text()='Series']"));
        if (seriesPage.isDisplayed()) {
            System.out.println("User is redirected to the Series Page");
        } else {
            System.out.println("User is not redirected to the Series Page");
        }

        // Scroll down to select genre as "Sci-Fi/Fantasy"
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement genre = driver.findElement(By.xpath("//span[text()='Genre']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(genre).click().perform();

        WebElement sciFiFantasy = driver.findElement(By.xpath("//label[text()='Sci-Fi/Fantasy']"));
        sciFiFantasy.click();

        // Select "Flash Gordon" from the list
        WebElement flashGordon = driver.findElement(By.xpath("//h2[text()='Flash Gordon']"));
        flashGordon.click();
        
        Thread.sleep(2000); // Wait for page to load
        
        // Click on the Play button
        WebElement playButton = driver.findElement(By.xpath("//button[@title='Play']"));
        playButton.click();
        
        Thread.sleep(12000); // Wait for video to play for 12 seconds

        // Click on Pause button
        WebElement pauseButton = driver.findElement(By.xpath("//button[@title='Pause']"));
        pauseButton.click();
        
        Thread.sleep(5000); // Wait for 5 seconds
        
        // Click on Play button to resume playing
        WebElement resumeButton = driver.findElement(By.xpath("//button[@title='Play']"));
        resumeButton.click();
        
        Thread.sleep(5000); // Wait for 5 seconds
        
        // Click on Forward 10 seconds
        WebElement forwardButton = driver.findElement(By.xpath("//button[@title='Forward 10 seconds']"));
        forwardButton.click();
        
        // Verify video is skipped forward by 10 seconds
        String currentTime = driver.findElement(By.className("video-current-time")).getText();
        if (currentTime.equals("00:22")) {
            System.out.println("Video is skipped forward by 10 seconds");
        } else {
            System.out.println("Video is not skipped forward by 10 seconds");
        }
        
        Thread.sleep(5000); // Wait for 5 seconds
        
        // Click on Pause button
        pauseButton.click();
        
        // Scroll down
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        
        // Verify "You Might also like" section is visible
        WebElement youMightAlsoLike = driver.findElement(By.xpath("//h2[text()='You Might also like']"));
        if (youMightAlsoLike.isDisplayed()) {
            System.out.println("You Might also like section is visible");
        } else {
            System.out.println("You Might also like section is not visible");
        }
        
        // Close browser
        driver.quit();
    }
}