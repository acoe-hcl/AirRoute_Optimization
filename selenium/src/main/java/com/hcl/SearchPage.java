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
            if (sortableCifColumn.isDisplayed() && sortableObligorColumn.isDisplayed()) {
                reportLog("pass", "CIF# and Obligor# columns are sortable", true);
                return true;
            } else {
                reportLog("fail", "CIF# and Obligor# columns are not sortable", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying sortable columns: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifyRecordCountDisplayed() {
        try {
            if (recordCountElement.isDisplayed()) {
                String recordCount = recordCountElement.getText();
                reportLog("pass", "Record count is displayed: " + recordCount, true);
                return true;
            } else {
                reportLog("fail", "Record count is not displayed", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying record count: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifyPaginationEnabled() {
        try {
            String recordCountText = recordCountElement.getText();
            int recordCount = Integer.parseInt(recordCountText.replaceAll("[^0-9]", ""));
            
            if (recordCount > 15) {
                if (paginationElement.isDisplayed()) {
                    reportLog("pass", "Pagination is enabled for record count greater than 15", true);
                    return true;
                } else {
                    reportLog("fail", "Pagination is not enabled for record count greater than 15", true);
                    return false;
                }
            } else {
                reportLog("pass", "Record count is 15 or less, pagination check not required", true);
                return true;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying pagination: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifyNoRecordsMessage() {
        try {
            if (noRecordsMessage.isDisplayed() && noRecordsMessage.getText().equals("No records found")) {
                reportLog("pass", "No records found message is displayed", true);
                return true;
            } else {
                reportLog("fail", "No records found message is not displayed", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying no records message: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verifyPaginationNotShown() {
        try {
            if (!paginationElement.isDisplayed()) {
                reportLog("pass", "Pagination is not shown", true);
                return true;
            } else {
                reportLog("fail", "Pagination is shown when it should not be", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("pass", "Pagination element not found, which is expected", true);
            return true;
        }
    }

    public boolean verifyNoSearchResults() {
        try {
            if (!searchResultsTable.isDisplayed()) {
                reportLog("pass", "No search results are displayed", true);
                return true;
            } else {
                reportLog("fail", "Search results are displayed when they should not be", true);
                return false;
            }
        } catch (Exception e) {
            reportLog("pass", "Search results table not found, which is expected", true);
            return true;
        }
    }

    public boolean verifyPartialAccountNumberResults(String partialAccountNumber) {
        try {
            String resultAccountNumber = accountNumberCell.getText();
            if (resultAccountNumber.startsWith(partialAccountNumber)) {
                reportLog("pass", "Search results contain account numbers starting with: " + partialAccountNumber, true);
                return true;
            } else {
                reportLog("fail", "Search results do not contain account numbers starting with: " + partialAccountNumber, true);
                return false;
            }
        } catch (Exception e) {
            reportLog("fail", "Exception while verifying partial account number results: " + e.getMessage(), true);
            return false;
        }
    }
}
```

```java
// AccountSearchTest.java
package tests;

import org.testng.annotations.Test;
import pages.SearchPage;
import utils.ExcelUtils;
import java.util.Map;

public class AccountSearchTest extends BaseTest {

    @Test(priority = 1, description = "TC_SEARCH_ACCOUNT_001")
    public void verifySearchResultsForValidAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_ACCOUNT_001");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("AccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifySearchResultsDisplayed()) {
                            if (searchPage.verifyAccountNumberInResults(testData.get("AccountNumber"))) {
                                if (searchPage.verifyDisplayedColumns()) {
                                    if (searchPage.verifyAdditionalColumns()) {
                                        if (searchPage.verifyClientNameHyperlink()) {
                                            if (searchPage.verifySortableColumns()) {
                                                if (searchPage.verifyRecordCountDisplayed()) {
                                                    searchPage.verifyPaginationEnabled();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 2, description = "TC_SEARCH_ACCOUNT_002")
    public void verifySearchResultsForPartialAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_ACCOUNT_002");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("PartialAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifySearchResultsDisplayed()) {
                            if (searchPage.verifyPartialAccountNumberResults(testData.get("PartialAccountNumber"))) {
                                if (searchPage.verifyDisplayedColumns()) {
                                    if (searchPage.verifyAdditionalColumns()) {
                                        if (searchPage.verifyClientNameHyperlink()) {
                                            if (searchPage.verifySortableColumns()) {
                                                if (searchPage.verifyRecordCountDisplayed()) {
                                                    searchPage.verifyPaginationEnabled();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 3, description = "TC_SEARCH_ACCOUNT_003")
    public void verifySearchResultsForInvalidAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_ACCOUNT_003");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("InvalidAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifyNoSearchResults()) {
                            if (searchPage.verifyNoRecordsMessage()) {
                                searchPage.verifyPaginationNotShown();
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 4, description = "TC_SEARCH_ACCOUNT_004")
    public void verifySearchResultsForEmptyAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_ACCOUNT_004");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("EmptyAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifyNoSearchResults()) {
                            if (searchPage.verifyNoRecordsMessage()) {
                                searchPage.verifyPaginationNotShown();
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 5, description = "TC_SEARCH_ACCOUNT_005")
    public void verifySearchResultsForSpecialCharactersInAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_ACCOUNT_005");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("SpecialCharAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifyNoSearchResults()) {
                            if (searchPage.verifyNoRecordsMessage()) {
                                searchPage.verifyPaginationNotShown();
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 6, description = "TC_SEARCH_ACCOUNT_006")
    public void verifySearchResultsForAlphanumericAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_ACCOUNT_006");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("AlphanumericAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifyNoSearchResults()) {
                            if (searchPage.verifyNoRecordsMessage()) {
                                searchPage.verifyPaginationNotShown();
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 7, description = "TC_SEARCH_001")
    public void verifySearchWithValid4DigitPrefix() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_001");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("AccountNumberPrefix"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifySearchResultsDisplayed()) {
                            if (searchPage.verifyPartialAccountNumberResults(testData.get("AccountNumberPrefix"))) {
                                if (searchPage.verifyDisplayedColumns()) {
                                    if (searchPage.verifyAdditionalColumns()) {
                                        if (searchPage.verifyClientNameHyperlink()) {
                                            if (searchPage.verifySortableColumns()) {
                                                if (searchPage.verifyRecordCountDisplayed()) {
                                                    searchPage.verifyPaginationEnabled();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 8, description = "TC_SEARCH_002")
    public void verifySearchWithInvalidPrefix() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_002");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("InvalidPrefix"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifyNoSearchResults()) {
                            if (searchPage.verifyNoRecordsMessage()) {
                                searchPage.verifyPaginationNotShown();
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 9, description = "TC_SEARCH_003")
    public void verifySearchWithValidTSYSAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_003");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("TSYSAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifySearchResultsDisplayed()) {
                            if (searchPage.verifyAccountNumberInResults(testData.get("TSYSAccountNumber"))) {
                                if (searchPage.verifyDisplayedColumns()) {
                                    if (searchPage.verifyAdditionalColumns()) {
                                        if (searchPage.verifyClientNameHyperlink()) {
                                            if (searchPage.verifySortableColumns()) {
                                                if (searchPage.verifyRecordCountDisplayed()) {
                                                    searchPage.verifyPaginationEnabled();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 10, description = "TC_SEARCH_004")
    public void verifySearchWithInvalidTSYSAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_004");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("InvalidTSYSAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifyNoSearchResults()) {
                            if (searchPage.verifyNoRecordsMessage()) {
                                searchPage.verifyPaginationNotShown();
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 11, description = "TC_SEARCH_005")
    public void verifySearchWithValidAFSAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_005");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("AFSAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifySearchResultsDisplayed()) {
                            if (searchPage.verifyAccountNumberInResults(testData.get("AFSAccountNumber"))) {
                                if (searchPage.verifyDisplayedColumns()) {
                                    if (searchPage.verifyAdditionalColumns()) {
                                        if (searchPage.verifyClientNameHyperlink()) {
                                            if (searchPage.verifySortableColumns()) {
                                                if (searchPage.verifyRecordCountDisplayed()) {
                                                    searchPage.verifyPaginationEnabled();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 12, description = "TC_SEARCH_006")
    public void verifySearchWithInvalidAFSAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_006");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("InvalidAFSAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifyNoSearchResults()) {
                            if (searchPage.verifyNoRecordsMessage()) {
                                searchPage.verifyPaginationNotShown();
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 13, description = "TC_SEARCH_007")
    public void verifySearchWithValidLeasewaveAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_007");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("LeasewaveAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifySearchResultsDisplayed()) {
                            if (searchPage.verifyAccountNumberInResults(testData.get("LeasewaveAccountNumber"))) {
                                if (searchPage.verifyDisplayedColumns()) {
                                    if (searchPage.verifyAdditionalColumns()) {
                                        if (searchPage.verifyClientNameHyperlink()) {
                                            if (searchPage.verifySortableColumns()) {
                                                if (searchPage.verifyRecordCountDisplayed()) {
                                                    searchPage.verifyPaginationEnabled();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 14, description = "TC_SEARCH_008")
    public void verifySearchWithInvalidLeasewaveAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_008");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("InvalidLeasewaveAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifyNoSearchResults()) {
                            if (searchPage.verifyNoRecordsMessage()) {
                                searchPage.verifyPaginationNotShown();
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 15, description = "TC_SEARCH_009")
    public void verifySearchWithValidDatascanAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_009");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("DatascanAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifySearchResultsDisplayed()) {
                            if (searchPage.verifyAccountNumberInResults(testData.get("DatascanAccountNumber"))) {
                                if (searchPage.verifyDisplayedColumns()) {
                                    if (searchPage.verifyAdditionalColumns()) {
                                        if (searchPage.verifyClientNameHyperlink()) {
                                            if (searchPage.verifySortableColumns()) {
                                                if (searchPage.verifyRecordCountDisplayed()) {
                                                    searchPage.verifyPaginationEnabled();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(priority = 16, description = "TC_SEARCH_010")
    public void verifySearchWithInvalidDatascanAccountNumber() {
        Map<String, String> testData = ExcelUtils.getTestData("AccountSearchTest", "TC_SEARCH_010");
        
        SearchPage searchPage = new SearchPage(driver);
        
        if (searchPage.navigateToSearchPage()) {
            if (searchPage.selectSearchType(testData.get("SearchType"))) {
                if (searchPage.enterAccountNumber(testData.get("InvalidDatascanAccountNumber"))) {
                    if (searchPage.clickSearchButton()) {
                        if (searchPage.verifyNoSearchResults()) {
                            if (searchPage.verifyNoRecordsMessage()) {
                                searchPage.verifyPaginationNotShown();
                            }
                        }
                    }
                }
            }
        }
    }
}
```