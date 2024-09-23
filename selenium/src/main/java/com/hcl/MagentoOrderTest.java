import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void navigateToMagentoWebsite() {
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @Test(priority = 2)
    public void clickSignInLink() {
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
    }

    @Test(priority = 3)
    public void verifyPageName() {
        String expectedPageName = "Customer Login";
        String actualPageName = driver.getTitle();
        Assert.assertEquals(actualPageName, expectedPageName);
    }

    @Test(priority = 4)
    public void enterCredentialsAndSignIn() {
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));

        emailField.sendKeys("testermail@gmail.com");
        passwordField.sendKeys("Tester@123");

        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
    }

    @Test(priority = 5)
    public void hoverOverGearMenuAndClickBagsLink() {
        WebElement gearMenu = driver.findElement(By.xpath("//a[@id='gear']"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();
        
        WebElement bagsLink = driver.findElement(By.xpath("//a[@href='/bags']"));
        action.click(bagsLink).perform();
    }

    @Test(priority = 6)
    public void clickDrivenBackpackImage() {
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();
    }

    @Test(priority = 7)
    public void clickAddToCartButton() {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartButton.click();
    }

    @Test(priority = 8)
    public void clickMyCartLink() {
        WebElement myCartLink = driver.findElement(By.xpath("//a[@title='My Cart']"));
        myCartLink.click();
    }

    @Test(priority = 9)
    public void verifyOrderSummary() {
        WebElement orderSummary = driver.findElement(By.xpath("//span[text()='Driven Backpack']"));
        Assert.assertTrue(orderSummary.isDisplayed());
    }

    @Test(priority = 10)
    public void clickProceedToCheckoutButton() {
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
        proceedToCheckoutButton.click();
    }

    @Test(priority = 11)
    public void clickNewAddressButton() {
        WebElement newAddressButton = driver.findElement(By.xpath("//button[@title='New Address']"));
        newAddressButton.click();
    }

    @Test(priority = 12)
    public void enterAddressDetailsAndShipHere() {
        WebElement streetAddressField = driver.findElement(By.id("street_1"));
        WebElement cityField = driver.findElement(By.id("city"));
        WebElement stateField = driver.findElement(By.id("region_id"));
        WebElement postalCodeField = driver.findElement(By.id("zip"));
        WebElement phoneField = driver.findElement(By.id("telephone"));

        streetAddressField.sendKeys("123 Example Street");
        cityField.sendKeys("Example City");
        stateField.sendKeys("Example State");
        postalCodeField.sendKeys("12345");
        phoneField.sendKeys("1234567890");

        WebElement shipHereButton = driver.findElement(By.xpath("//button[@title='Ship Here']"));
        shipHereButton.click();
    }

    @Test(priority = 13)
    public void selectFixedRadioButton() {
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']"));
        fixedRadioButton.click();
    }

    @Test(priority = 14)
    public void clickNextButton() {
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();
    }

    @Test(priority = 15)
    public void selectBillingShippingSameCheckbox() {
        WebElement sameBillingShippingCheckbox = driver.findElement(By.xpath("//input[@id='billing:use_for_shipping_yes']"));
        sameBillingShippingCheckbox.click();
    }

    @Test(priority = 16)
    public void clickPlaceOrderButton() {
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();
    }

    @Test(priority = 17)
    public void verifyConfirmationMessage() {
        WebElement confirmationMessage = driver.findElement(By.xpath("//span[text()='Thank you for your purchase!']"));
        Assert.assertTrue(confirmationMessage.isDisplayed());
    }

    @Test(priority = 18)
    public void clickChangeButton() {
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();
    }

    @Test(priority = 19)
    public void clickSignOutLink() {
        WebElement signOutLink = driver.findElement(By.linkText("Signout"));
        signOutLink.click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}