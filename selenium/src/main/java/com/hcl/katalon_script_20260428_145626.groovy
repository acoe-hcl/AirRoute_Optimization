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

class UserSiteFieldsSteps {

    void userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_LoginUrl)
        WebUI.setText(findTestObject('Page_Login/txt_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/txt_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/btn_Login'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), 'D365 - Home', false)
    }

    void userSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/btn_LegalEntityDropdown'))
        WebUI.setText(findTestObject('Page_Home/txt_LegalEntitySearch'), legalentity)
        WebUI.click(findTestObject('Page_Home/option_LegalEntity', [('entity') : legalentity]))
    }

    void navigateToUserSites() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), 'User Sites', false)
    }

    void removeAllUserSitesForSachin() {
        List<WebElement> userSites = WebUI.findWebElements(findTestObject('Page_UserSites/row_UserSite_Sachin'), 10)
        for (WebElement userSite : userSites) {
            WebUI.click(userSite)
            WebUI.click(findTestObject('Page_UserSites/btn_Delete'))
            WebUI.acceptAlert()
        }
    }

    void addUserSite7100(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite', [
                ('entity') : legalentity, 
                ('site') : '7100', 
                ('user') : 'Sachin', 
                ('isDefault') : 'true'
            ]), 5, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/btn_Add'))
            WebUI.setText(findTestObject('Page_UserSites/txt_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/txt_User'), 'Sachin')
            WebUI.setText(findTestObject('Page_UserSites/txt_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/btn_Save'))
        }
    }

    void addUserSite3434(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite', [
                ('entity') : legalentity, 
                ('site') : '3434', 
                ('user') : 'Sachin', 
                ('isDefault') : 'true'
            ]), 5, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/btn_Add'))
            WebUI.setText(findTestObject('Page_UserSites/txt_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/txt_User'), 'Sachin')
            WebUI.setText(findTestObject('Page_UserSites/txt_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/btn_Save'))
        }
    }

    void verifyValidationError() {
        WebUI.click(findTestObject('Page_UserSites/btn_GoToSalesOrder'))
        WebUI.verifyElementPresent(findTestObject('Page_SalesOrder/lbl_ValidationError'), 10)
        String errorMsg = WebUI.getText(findTestObject('Page_SalesOrder/lbl_ValidationError'))
        WebUI.verifyMatch(errorMsg, 'User cannot have multiple default sites in same legal entity', false)
        WebUI.closeBrowser()
    }
}

String testcaseName = '65090 Pre requisite for xxx- Verify the User site fields is displaying in D365'
String legalentity = '101'

UserSiteFieldsSteps steps = new UserSiteFieldsSteps()
steps.userLogin(testcaseName)
steps.userSelectLegalEntity(legalentity)
steps.navigateToUserSites()
steps.removeAllUserSitesForSachin()
steps.addUserSite7100(legalentity)
steps.addUserSite3434(legalentity)
steps.verifyValidationError()
