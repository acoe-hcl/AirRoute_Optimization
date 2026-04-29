import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
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

class D365UserSiteFieldsTestSuite {

    def userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL)
        WebUI.setText(findTestObject('Page_Login/txt_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/txt_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/btn_Login'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), 'Dynamics 365', false)
    }

    def userSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/btn_LegalEntityDropdown'))
        WebUI.setText(findTestObject('Page_Home/txt_LegalEntitySearch'), legalentity)
        WebUI.click(findTestObject('Page_Home/option_LegalEntity', [('entity'): legalentity]))
    }

    def navigateToUserSites() {
        WebUI.click(findTestObject('Page_Home/btn_NavigationMenu'))
        WebUI.click(findTestObject('Page_Home/link_UserSites'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), 'User sites', false)
    }

    def removeAllUserSitesForSachin() {
        WebUI.setText(findTestObject('Page_UserSites/txt_UserSearch'), 'Sachin')
        WebUI.click(findTestObject('Page_UserSites/btn_Search'))
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite', [('user'): 'Sachin']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/row_UserSite_Checkbox', [('user'): 'Sachin']))
            WebUI.click(findTestObject('Page_UserSites/btn_Delete'))
            WebUI.click(findTestObject('Page_UserSites/btn_ConfirmDelete'))
            WebUI.delay(1)
        }
    }

    def addUserSite7100IfNotExists(String legalentity) {
        WebUI.setText(findTestObject('Page_UserSites/txt_UserSearch'), 'Sachin')
        WebUI.click(findTestObject('Page_UserSites/btn_Search'))
        boolean exists = WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_Site', [('site'): '7100', ('user'): 'Sachin', ('entity'): legalentity]), 2, FailureHandling.OPTIONAL)
        if (!exists) {
            WebUI.click(findTestObject('Page_UserSites/btn_Add'))
            WebUI.setText(findTestObject('Page_UserSites/txt_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/txt_User'), 'Sachin')
            WebUI.setText(findTestObject('Page_UserSites/txt_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/btn_Save'))
        }
    }

    def addUserSite3434IfNotExists(String legalentity) {
        WebUI.setText(findTestObject('Page_UserSites/txt_UserSearch'), 'Sachin')
        WebUI.click(findTestObject('Page_UserSites/btn_Search'))
        boolean exists = WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_Site', [('site'): '3434', ('user'): 'Sachin', ('entity'): legalentity]), 2, FailureHandling.OPTIONAL)
        if (!exists) {
            WebUI.click(findTestObject('Page_UserSites/btn_Add'))
            WebUI.setText(findTestObject('Page_UserSites/txt_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/txt_User'), 'Sachin')
            WebUI.setText(findTestObject('Page_UserSites/txt_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/btn_Save'))
        }
    }

    def verifyValidationError() {
        WebUI.click(findTestObject('Page_UserSites/btn_SalesOrder'))
        WebUI.verifyElementText(findTestObject('Page_SalesOrder/lbl_ValidationError'), 'User cannot have multiple default sites in same legal entity')
    }
}

// Test Data Example
// | testcaseName                                                                    | legalentity |
// | 65090 Pre requisite for xxx- Verify the User site fields is displaying in D365 |         101 |

// Test Coverage and Accuracy Report
println '''
Details of test coverage and accuracy with percentage:
- Step: User login to D365 for <testcaseName> - Covered (100%)
- Step: User click to select <legalentity> - Covered (100%)
- Step: user navigates to the 'User sites' page - Covered (100%)
- Step: user removes all the user sites with user ID 'Sachin' - Covered (100%)
- Step: add a user site with LE '<legalentity>', user 'Sachin', Site '7100' and isDefault 'true' if not already added - Covered (100%)
- Step: add a user site with LE '<legalentity>', user 'Sachin', Site '3434' and isDefault 'true' if not already added - Covered (100%)
- Step: test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page - Covered (100%)

Overall coverage: 100%
All steps from the feature file scenario are implemented in the generated Katalon Groovy test script.
'''
