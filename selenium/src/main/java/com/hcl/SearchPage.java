```java
// SearchPage.java
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//select[@id='searchType']")
    private WebElement searchTypeDropdown;

    @FindBy(xpath = "//input[@id='accountNumber']")
    private WebElement accountNumberField;

    @FindBy(xpath = "//button[@id='searchButton']")
    private WebElement searchButton;

    @FindBy(xpath = "//table[@id='searchResults']")
    private WebElement searchResultsTable;

    @FindBy(xpath = "//div[@class='no-records-message']")
    private WebElement noRecordsMessage;

    @FindBy(xpath = "//div[@class='record-count']")
    private WebElement recordCountElement;

    @FindBy(xpath = "//div[@class='pagination']")
    private WebElement paginationElement;

    @FindBy(xpath = "//th[text()='Client Name']")
    private WebElement clientNameColumn;

    @FindBy(xpath = "//th[text()='Client ID']")
    private WebElement clientIdColumn;

    @FindBy(xpath = "//th[text()='CIF Key#']")
    private WebElement cifKeyColumn;

    @FindBy(xpath = "//th[text()='Obligor#']")
    private WebElement obligorColumn;

    @FindBy(xpath = "//th[text()='SSN/TaxId']")
    private WebElement ssnTaxIdColumn;

    @FindBy(xpath = "//th[text()='Address']")
    private WebElement addressColumn;

    @FindBy(xpath = "//span[@class='datascan-column']")
    private WebElement datascanColumn;

    @FindBy(xpath = "//span[@class='leasewave-column']")
    private WebElement leasewaveColumn;

    @FindBy(xpath = "//span[@class='tsys-column']")
    private WebElement tsysColumn;

    @FindBy(xpath = "//a[@class='client-name-link']")
    private WebElement clientNameHyperlink;

    @FindBy(xpath = "//th[@class='sortable-cif']")
    private WebElement sortableCifColumn;

    @FindBy(xpath = "//th[@class='sortable-obligor']")
    private WebElement sortableObligorColumn;

    @FindBy(xpath = "//tbody/tr")
    private WebElement firstResultRow;

    @FindBy(xpath = "//td[@class='account-number-cell']")
    private WebElement accountNumberCell;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean navigateToSearchPage() {
        try {
            if (searchTypeDropdown.isDisplayed()) {
                reportLog("pass", "Successfully navigated to search page", true);
                return true;
            } else {
                reportLog("fail", "Failed to navigate to search page", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while navigating to search page: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean selectSearchType(String searchType) {
        try {
            jsExecutorClick(searchTypeDropdown);
            searchTypeDropdown.sendKeys(searchType);
            if (searchTypeDropdown.getAttribute("value").equals(searchType)) {
                reportLog("pass", "Successfully selected search type: " + searchType, true);
                return true;
            } else {
                reportLog("fail", "Failed to select search type: " + searchType, true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while selecting search type: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean enterAccountNumber(String accountNumber) {
        try {
            accountNumberField.clear();
            accountNumberField.sendKeys(accountNumber);
            if (accountNumberField.getAttribute("value").equals(accountNumber)) {
                reportLog("pass", "Successfully entered account number: " + accountNumber, true);
                return true;
            } else {
                reportLog("fail", "Failed to enter account number: " + accountNumber, true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while entering account number: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean clickSearchButton() {
        try {
            jsExecutorClick(searchButton);
            Thread.sleep(2000);
            reportLog("pass", "Successfully clicked search button", true);
            return true;
        } catch (Exception e) {
            reportLog("fail", "Exception while clicking search button: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifySearchResultsDisplayed() {
        try {
            if (searchResultsTable.isDisplayed()) {
                reportLog("pass", "Search results are displayed", true);
                return true;
            } else {
                reportLog("fail", "Search results are not displayed", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying search results: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifyAccountNumberInResults(String accountNumber) {
        try {
            String resultAccountNumber = accountNumberCell.getText();
            if (resultAccountNumber.contains(accountNumber)) {
                reportLog("pass", "Account number " + accountNumber + " found in search results", true);
                return true;
            } else {
                reportLog("fail", "Account number " + accountNumber + " not found in search results", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying account number in results: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifyDisplayedColumns() {
        try {
            if (clientNameColumn.isDisplayed() && clientIdColumn.isDisplayed() && 
                cifKeyColumn.isDisplayed() && obligorColumn.isDisplayed() && 
                ssnTaxIdColumn.isDisplayed() && addressColumn.isDisplayed()) {
                reportLog("pass", "All required columns are displayed", true);
                return true;
            } else {
                reportLog("fail", "Not all required columns are displayed", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying displayed columns: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifyAdditionalColumns() {
        try {
            if (datascanColumn.isDisplayed() && leasewaveColumn.isDisplayed() && tsysColumn.isDisplayed()) {
                reportLog("pass", "Additional columns (DataScan, Lease Wave, Tsys) are displayed", true);
                return true;
            } else {
                reportLog("fail", "Additional columns are not displayed", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying additional columns: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifyClientNameHyperlink() {
        try {
            if (clientNameHyperlink.isDisplayed() && clientNameHyperlink.getTagName().equals("a")) {
                reportLog("pass", "Client Name is displayed as hyperlink", true);
                return true;
            } else {
                reportLog("fail", "Client Name is not displayed as hyperlink", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying client name hyperlink: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifySortableColumns() {
        try {
            if (sor