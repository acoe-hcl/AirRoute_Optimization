import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import org.openqa.selenium.Keys

class UserSiteFieldsD365Steps {

    void userLoginToD365(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL) // Set this variable in your profile
        WebUI.setText(findTestObject('Page_Login/input_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/input_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/button_Login'))
        WebUI.verifyElementPresent(findTestObject('Page_Home/logo_D365'), 10)
    }

    void userClickToSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/dropdown_LegalEntity'))
        WebUI.click(findTestObject("Page_Home/item_LegalEntity_${legalentity}"))
    }

    void navigateToUserSitesPage() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/title_UserSites'), 10)
    }

    void removeAllUserSitesForLomasGupta() {
        WebUI.setText(findTestObject('Page_UserSites/input_SearchUser'), 'Lomas.Gupta')
        WebUI.click(findTestObject('Page_UserSites/button_Search'))
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/row_UserSite/checkbox_Select'))
            WebUI.click(findTestObject('Page_UserSites/button_Delete'))
            WebUI.acceptAlert()
            WebUI.delay(1)
        }
    }

    void addUserSite7100IfNotExists(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_7100'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_Add'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.Gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    void addUserSite3434IfNotExists(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_3434'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_Add'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.Gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    void verifyValidationErrorMessage() {
        WebUI.click(findTestObject('Page_SalesOrder/menu_SalesOrder'))
        WebUI.setText(findTestObject('Page_SalesOrder/input_User'), 'Lomas.Gupta')
        WebUI.click(findTestObject('Page_SalesOrder/button_Validate'))
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_SalesOrder/label_ValidationError')),
            'User cannot have multiple default sites in same legal entity', false)
        WebUI.closeBrowser()
    }
}

// Test Suite Example
import org.junit.Test

class UserSiteFieldsD365TestSuite {
    @Test
    void testUserSiteFieldsD365() {
        UserSiteFieldsD365Steps steps = new UserSiteFieldsD365Steps()
        steps.userLoginToD365('65090 Pre requisite for xxx- Verify the User site fields is displaying in D365')
        steps.userClickToSelectLegalEntity('101')
        steps.navigateToUserSitesPage()
        steps.removeAllUserSitesForLomasGupta()
        steps.addUserSite7100IfNotExists('101')
        steps.addUserSite3434IfNotExists('101')
        steps.verifyValidationErrorMessage()
    }
}