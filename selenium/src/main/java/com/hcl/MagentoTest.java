package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class MagentoTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testSignIn() {
        // Open the Magento website
        driver.get("https://www.example.com");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Verify that the browser name and page name is set as "Customer Login"
        String browserName = driver.getCapabilities().getBrowserName();
        String pageName = driver.getTitle();
        Assert.assertEquals(browserName, "Chrome");
        Assert.assertEquals(pageName, "Customer Login");
    }

    @Test(priority = 2)
    public void testAddToCart() {
        // Enter the email address and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        // Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.xpath("//span[contains(text(),'Gear')]"));
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        
        Actions hover = new Actions(driver);
        hover.moveToElement(gearMenu).build().perform();
        bagsLink.click();

        // Click on the image of the "Driven Backpack" product
        WebElement backpackImage = driver.findElement(By.xpath("//img[contains(@alt,'Driven Backpack')]"));
        backpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[contains(text(),'Add to Cart')]"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify that the "Order Summary" includes the "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.id("order-summary"));
        String orderSummaryText = orderSummary.getText();
        Assert.assertTrue(orderSummaryText.contains("Driven Backpack"));
    }

    @Test(priority = 3)
    public void testCheckout() {
        // Click on the "Proceed to Checkout" button
        WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Proceed to Checkout')]"));
        checkoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.xpath("//button[contains(text(),'New Address')]"));
        newAddressButton.click();

        // Enter the address details
        WebElement streetField = driver.findElement(By.id("street"));
        streetField.sendKeys("123 Main Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("New York");

        WebElement stateField = driver.findElement(By.id("state"));
        stateField.sendKeys("NY");

        WebElement zipField = driver.findElement(By.id("zip"));
        zipField.sendKeys("10001");

        WebElement phoneField = driver.findElement(By.id("phone"));
        phoneField.sendKeys("123-456-7890");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//button[contains(text(),'Ship Here')]"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("fixed"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("same-address"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[contains(text(),'Place Order')]"));
        placeOrderButton.click();

        // Verify that a confirmation message saying "Thank you for your purchase!" is displayed
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[contains(text(),'Thank you for your purchase!')]"));
        Assert.assertTrue(confirmationMessage.isDisplayed());
    }

    @Test(priority = 4)
    public void testSignOut() {
        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[contains(text(),'Change')]"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signOutLink = driver.findElement(By.linkText("Signout"));
        signOutLink.click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
