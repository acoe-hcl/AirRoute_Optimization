import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Test Data
def testcaseName = "65090 Pre requisite for xxx- Verify the User site fields is displaying in D365"
def legalentity = "101"
def userId = "Sachin"
def site1 = "7100"
def site2 = "3434"
def errorMsg = "User cannot have multiple default sites in same legal entity"

class D365UserSiteSteps {

    void userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl('https://d365-login-url.com') // Replace with actual D365 login URL
        WebUI.setText(findTestObject('Page_Login/txt_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/txt_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/btn_Login'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), 'D365 Home', false)
    }

    void selectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/btn_LegalEntityDropdown'))
        WebUI.setText(findTestObject('Page_Home/txt_LegalEntitySearch'), legalentity)
        WebUI.click(findTestObject('Page_Home/option_LegalEntity', [('entity') : legalentity]))
    }

    void navigateToUserSites() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), 'User sites', false)
    }

    void removeUserSites(String userId) {
        WebUI.setText(findTestObject('Page_UserSites/txt_UserIdSearch'), userId)
        WebUI.click(findTestObject('Page_UserSites/btn_Search'))
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite', [('userId') : userId]), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/row_UserSite', [('userId') : userId]))
            WebUI.click(findTestObject('Page_UserSites/btn_Delete'))
            WebUI.click(findTestObject('Page_UserSites/btn_ConfirmDelete'))
            WebUI.delay(1)
        }
    }

    void addUserSiteIfNotExists(String legalentity, String userId, String site) {
        WebUI.click(findTestObject('Page_UserSites/btn_AddNew'))
        WebUI.setText(findTestObject('Page_UserSites/txt_LE'), legalentity)
        WebUI.setText(findTestObject('Page_UserSites/txt_UserId'), userId)
        WebUI.setText(findTestObject('Page_UserSites/txt_Site'), site)
        WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
        WebUI.click(findTestObject('Page_UserSites/btn_Save'))
        WebUI.delay(1)
    }

    void verifyValidationError(String errorMsg) {
        WebUI.click(findTestObject('Page_SalesOrder/btn_NewOrder'))
        WebUI.click(findTestObject('Page_SalesOrder/btn_SaveOrder'))
        WebUI.verifyElementText(findTestObject('Page_SalesOrder/lbl_ErrorMessage'), errorMsg)
    }
}

D365UserSiteSteps steps = new D365UserSiteSteps()
steps.userLogin(testcaseName)
steps.selectLegalEntity(legalentity)
steps.navigateToUserSites()
steps.removeUserSites(userId)
steps.addUserSiteIfNotExists(legalentity, userId, site1)
steps.addUserSiteIfNotExists(legalentity, userId, site2)
steps.verifyValidationError(errorMsg)
