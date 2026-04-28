import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Example Data (can be parameterized in Data Files or Test Suite)
GlobalVariable.D365_URL = 'https://d365.yourcompany.com'
GlobalVariable.D365_Username = 'your_username'
GlobalVariable.D365_Password = 'your_password'

// Test Case for Scenario Outline: 65090 Pre requisite for xxx- Verify the User site fields is displaying in D365
class VerifyUserSiteFieldsInD365 {

    void userLoginToD365(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL)
        WebUI.setText(findTestObject('Page_Login/input_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/input_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/button_Login'))
        WebUI.waitForPageLoad(10)
        // Optionally verify login success
        WebUI.verifyElementPresent(findTestObject('Page_Home/label_Welcome'), 10)
    }

    void userClickToSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/dropdown_LegalEntity'))
        WebUI.setText(findTestObject('Page_Home/input_LegalEntitySearch'), legalentity)
        WebUI.sendKeys(findTestObject('Page_Home/input_LegalEntitySearch'), Keys.ENTER)
        WebUI.waitForPageLoad(5)
    }

    void userNavigatesToUserSitesPage() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.waitForPageLoad(5)
    }

    void userRemovesAllUserSitesForLomasGupta() {
        // Search for user 'Lomas.Gupta'
        WebUI.setText(findTestObject('Page_UserSites/input_UserSearch'), 'Lomas.Gupta')
        WebUI.click(findTestObject('Page_UserSites/button_Search'))
        WebUI.delay(2)
        // Remove all sites listed for this user
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/button_RemoveUserSite'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_RemoveUserSite'))
            WebUI.acceptAlert()
            WebUI.delay(1)
        }
    }

    void addUserSite7100IfNotExists(String legalentity) {
        WebUI.click(findTestObject('Page_UserSites/button_AddUserSite'))
        WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
        WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Sachin')
        WebUI.setText(findTestObject('Page_UserSites/input_Site'), '7100')
        WebUI.check(findTestObject('Page_UserSites/checkbox_IsDefault'))
        WebUI.click(findTestObject('Page_UserSites/button_Save'))
        WebUI.delay(2)
        // Optionally verify addition
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/label_Success'), 5)
    }

    void addUserSite3434IfNotExists(String legalentity) {
        WebUI.click(findTestObject('Page_UserSites/button_AddUserSite'))
        WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
        WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Sachin')
        WebUI.setText(findTestObject('Page_UserSites/input_Site'), '3434')
        WebUI.check(findTestObject('Page_UserSites/checkbox_IsDefault'))
        WebUI.click(findTestObject('Page_UserSites/button_Save'))
        WebUI.delay(2)
        // Optionally verify addition
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/label_Success'), 5)
    }

    void testValidationErrorMessage() {
        WebUI.click(findTestObject('Page_UserSites/button_GoToSalesOrder'))
        WebUI.waitForPageLoad(5)
        String actualError = WebUI.getText(findTestObject('Page_SalesOrder/label_ValidationError'))
        WebUI.verifyMatch(actualError, 'User cannot have multiple default sites in same legal entity', false)
    }

    void runTest(String testcaseName, String legalentity) {
        try {
            userLoginToD365(testcaseName)
            userClickToSelectLegalEntity(legalentity)
            userNavigatesToUserSitesPage()
            userRemovesAllUserSitesForLomasGupta()
            addUserSite7100IfNotExists(legalentity)
            addUserSite3434IfNotExists(legalentity)
            testValidationErrorMessage()
        } finally {
            WebUI.closeBrowser()
        }
    }
}

// Example usage (replace with your data source or test suite logic)
def test = new VerifyUserSiteFieldsInD365()
test.runTest('65090 Pre requisite for xxx- Verify the User site fields is displaying in D365', '101')
