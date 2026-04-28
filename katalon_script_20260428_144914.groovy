import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords.findWindowsObject

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

class UserSiteFieldsSteps {

    void userLoginToD365(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL)
        WebUI.setText(findTestObject('Page_Login/txt_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/txt_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/btn_Login'))
        WebUI.verifyElementPresent(findTestObject('Page_Home/icon_Home'), 10)
    }

    void userClickToSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/dropdown_LegalEntity'))
        WebUI.setText(findTestObject('Page_Home/input_LegalEntity'), legalentity)
        WebUI.sendKeys(findTestObject('Page_Home/input_LegalEntity'), Keys.ENTER)
    }

    void userNavigatesToUserSitesPage() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/title_UserSites'), 10)
    }

    void userRemovesAllUserSitesWithUserIDSachin() {
        WebUI.setText(findTestObject('Page_UserSites/input_UserID'), 'Sachin')
        WebUI.click(findTestObject('Page_UserSites/btn_Search'))
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSachin'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/row_UserSachin/btn_Delete'))
            WebUI.click(findTestObject('Page_UserSites/dialog_ConfirmDelete/btn_Yes'))
            WebUI.delay(1)
        }
    }

    void addUserSite7100IfNotExists(String legalentity) {
        addUserSiteIfNotExists(legalentity, 'Sachin', '7100', true)
    }

    void addUserSite3434IfNotExists(String legalentity) {
        addUserSiteIfNotExists(legalentity, 'Sachin', '3434', true)
    }

    void addUserSiteIfNotExists(String legalentity, String user, String site, boolean isDefault) {
        WebUI.setText(findTestObject('Page_UserSites/input_UserID'), user)
        WebUI.setText(findTestObject('Page_UserSites/input_Site'), site)
        WebUI.click(findTestObject('Page_UserSites/btn_Search'))
        boolean exists = WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSachin'), 2, FailureHandling.OPTIONAL)
        if (!exists) {
            WebUI.click(findTestObject('Page_UserSites/btn_AddNew'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), user)
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), site)
            if (isDefault) {
                WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            }
            WebUI.click(findTestObject('Page_UserSites/btn_Save'))
            WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSachin'), 5)
        }
    }

    void testValidationErrorMessage() {
        WebUI.click(findTestObject('Page_SalesOrder/menu_SalesOrder'))
        WebUI.verifyElementPresent(findTestObject('Page_SalesOrder/title_SalesOrder'), 10)
        String actualError = WebUI.getText(findTestObject('Page_SalesOrder/label_ValidationError'))
        WebUI.verifyMatch(actualError, 'User cannot have multiple default sites in same legal entity', false)
    }
}

class UserSiteFieldsTestSuite {
    static void main(String[] args) {
        UserSiteFieldsSteps steps = new UserSiteFieldsSteps()
        steps.userLoginToD365('65090 Pre requisite for xxx- Verify the User site fields is displaying in D365')
        steps.userClickToSelectLegalEntity('101')
        steps.userNavigatesToUserSitesPage()
        steps.userRemovesAllUserSitesWithUserIDSachin()
        steps.addUserSite7100IfNotExists('101')
        steps.addUserSite3434IfNotExists('101')
        steps.testValidationErrorMessage()
        WebUI.closeBrowser()
    }
}