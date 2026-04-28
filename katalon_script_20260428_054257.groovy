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
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then

class UserSiteFieldsSteps {

    @Given("User login to D365 for {string}")
    def userLoginToD365(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_Url) // Use a GlobalVariable for the actual URL
        WebUI.setText(findTestObject('Page_Login/txt_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/txt_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/btn_Login'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), 'D365 Home', false)
    }

    @Given("User click to select {string}")
    def userClickToSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/btn_LegalEntityDropdown'))
        WebUI.click(findTestObject('Page_Home/option_LegalEntity', [('entity') : legalentity]))
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_Home/lbl_SelectedLegalEntity')), legalentity, false)
    }

    @When("user navigates to the 'User sites' page")
    def userNavigatesToUserSitesPage() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), 'User Sites', false)
    }

    @When("user removes all the user sites with user ID 'Lomas.Gupta'")
    def userRemovesAllUserSitesWithUserId() {
        WebUI.setText(findTestObject('Page_UserSites/txt_SearchUser'), 'Lomas.Gupta')
        WebUI.click(findTestObject('Page_UserSites/btn_Search'))
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/btn_DeleteUserSite'))
            WebUI.click(findTestObject('Page_UserSites/btn_ConfirmDelete'))
        }
    }

    @When("add a user site with LE {string}, user 'Lomas.gupta', Site '7100' and isDefault 'true' if not already added")
    def addUserSite7100IfNotExists(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_Site', [('site') : '7100']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/btn_AddUserSite'))
            WebUI.setText(findTestObject('Page_UserSites/txt_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/txt_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/txt_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/btn_SaveUserSite'))
        }
    }

    @When("add a user site with LE {string}, user 'Lomas.gupta', Site '3434' and isDefault 'true' if not already added")
    def addUserSite3434IfNotExists(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_Site', [('site') : '3434']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/btn_AddUserSite'))
            WebUI.setText(findTestObject('Page_UserSites/txt_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/txt_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/txt_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/btn_SaveUserSite'))
        }
    }

    @When("test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page")
    def verifyValidationErrorMessage() {
        WebUI.click(findTestObject('Page_SalesOrder/btn_CreateSalesOrder'))
        String errorMsg = WebUI.getText(findTestObject('Page_SalesOrder/lbl_ValidationError'))
        WebUI.verifyMatch(errorMsg, 'User cannot have multiple default sites in same legal entity', false)
        WebUI.closeBrowser()
    }
}
