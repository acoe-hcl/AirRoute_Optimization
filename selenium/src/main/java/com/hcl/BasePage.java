
package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.utils.ConfigReader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.time.Duration;

/**
 * BasePage - Common functionality for all page objects.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    /**
     * Constructor for BasePage.
     * @param driver WebDriver object
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getIntProperty("explicitTimeout")));
        this.logger = LogManager.getLogger(this.getClass());
    }

    /**
     * Waits for the element to be visible.
     * @param element WebElement to wait for
     * @return WebElement after waiting
     */
    public WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the element to be clickable.
     * @param element WebElement to wait for
     * @return WebElement after waiting
     */
    public WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Logs info message.
     * @param message Message to log
     */
    public void logInfo(String message) {
        logger.info(message);
    }

    /**
     * Logs error message.
     * @param message Message to log
     */
    public void logError(String message) {
        logger.error(message);
    }
}
