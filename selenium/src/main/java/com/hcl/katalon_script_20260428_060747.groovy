import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import org.openqa.selenium.Keys

import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.api.java.en.Then

class UserSiteFieldsSteps {

    @Given('User login to D365 for {string}')
    def userLoginToD365(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL)
        WebUI.setText(findTestObject('Page_Login/txt_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/txt_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/btn_Login'))
        WebUI.verifyMatch(WebUI.getUrl(), GlobalVariable.D365_Home_URL, false)
    }

    @Given('User click to select {string}')
    def userClickToSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/btn_LegalEntityDropdown'))
        WebUI.setText(findTestObject('Page_Home/txt_LegalEntitySearch'), legalentity)
        WebUI.click(findTestObject('Page_Home/option_LegalEntity', [('entity') : legalentity]))
    }

    @When("user navigates to the 'User sites' page")
    def userNavigatesToUserSitesPage() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyMatch(WebUI.getUrl(), GlobalVariable.D365_UserSites_URL, false)
    }

    @When("user removes all the user sites with user ID 'Lomas.Gupta'")
    def userRemovesAllUserSites() {
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite', [('userId') : 'Lomas.Gupta']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/row_UserSite_Delete', [('userId') : 'Lomas.Gupta']))
            WebUI.acceptAlert()
        }
    }

    @When("add a user site with LE '{string}', user 'Lomas.gupta', Site '7100' and isDefault 'true' if not already added")
    def addUserSite7100IfNotExists(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_Site', [
                ('userId') : 'Lomas.Gupta', 
                ('site') : '7100', 
                ('isDefault') : 'true', 
                ('legalentity') : legalentity
            ]), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/btn_AddUserSite'))
            WebUI.setText(findTestObject('Page_UserSites/txt_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/txt_User'), 'Lomas.Gupta')
            WebUI.setText(findTestObject('Page_UserSites/txt_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/btn_SaveUserSite'))
        }
    }

    @When("add a user site with LE '{string}', user 'Lomas.gupta', Site '3434' and isDefault 'true' if not already added")
    def addUserSite3434IfNotExists(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_Site', [
                ('userId') : 'Lomas.Gupta', 
                ('site') : '3434', 
                ('isDefault') : 'true', 
                ('legalentity') : legalentity
            ]), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/btn_AddUserSite'))
            WebUI.setText(findTestObject('Page_UserSites/txt_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/txt_User'), 'Lomas.Gupta')
            WebUI.setText(findTestObject('Page_UserSites/txt_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/btn_SaveUserSite'))
        }
    }

    @When("test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page")
    def testValidationErrorMessage() {
        WebUI.click(findTestObject('Page_Home/menu_SalesOrder'))
        WebUI.verifyElementPresent(findTestObject('Page_SalesOrder/lbl_ValidationError'), 5)
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_SalesOrder/lbl_ValidationError')), 
            'User cannot have multiple default sites in same legal entity', false)
        WebUI.closeBrowser()
    }
}