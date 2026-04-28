import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.annotation.Keyword
import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.api.java.en.Then

class UserSiteFieldsSteps {

    @Given('User login to D365 for {string}')
    def userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_Url)
        WebUI.setText(findTestObject('Page_Login/input_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/input_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/button_SignIn'))
        WebUI.verifyElementPresent(findTestObject('Page_Home/icon_Home'), 10)
    }

    @Given('User click to select {string}')
    def selectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/dropdown_LegalEntity'))
        WebUI.setText(findTestObject('Page_Home/input_LegalEntitySearch'), legalentity)
        WebUI.sendKeys(findTestObject('Page_Home/input_LegalEntitySearch'), Keys.chord(Keys.ENTER))
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_Home/label_SelectedLegalEntity')), legalentity, false)
    }

    @When("user navigates to the 'User sites' page")
    def navigateToUserSites() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/title_UserSites'), 10)
    }

    @When("user removes all the user sites with user ID 'Lomas.Gupta'")
    def removeAllUserSites() {
        List<TestObject> userRows = CustomKeywords.'your.package.FindUserRows'('Lomas.Gupta')
        for (TestObject row : userRows) {
            WebUI.click(findTestObject('Page_UserSites/button_Delete', [('row') : row]))
            WebUI.acceptAlert()
        }
    }

    @When("add a user site with LE {string}, user 'Lomas.gupta', Site '7100' and isDefault 'true' if not already added")
    def addUserSite7100(String legalentity) {
        boolean exists = CustomKeywords.'your.package.CheckUserSiteExists'(legalentity, 'Lomas.gupta', '7100')
        if (!exists) {
            WebUI.click(findTestObject('Page_UserSites/button_Add'))
            WebUI.setText(findTestObject('Page_UserSites/input_LegalEntity'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    @When("add a user site with LE {string}, user 'Lomas.gupta', Site '3434' and isDefault 'true' if not already added")
    def addUserSite3434(String legalentity) {
        boolean exists = CustomKeywords.'your.package.CheckUserSiteExists'(legalentity, 'Lomas.gupta', '3434')
        if (!exists) {
            WebUI.click(findTestObject('Page_UserSites/button_Add'))
            WebUI.setText(findTestObject('Page_UserSites/input_LegalEntity'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    @When("test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page")
    def verifyValidationError() {
        WebUI.click(findTestObject('Page_UserSites/button_NavigateToSalesOrder'))
        WebUI.verifyElementPresent(findTestObject('Page_SalesOrder/label_ValidationError'), 10)
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_SalesOrder/label_ValidationError')), 'User cannot have multiple default sites in same legal entity', false)
        WebUI.closeBrowser()
    }
}