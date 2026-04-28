import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import org.openqa.selenium.Keys

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then

class UserSiteFieldsSteps {

    @Given("User login to D365 for (.*)")
    def userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL)
        WebUI.setText(findTestObject('Page_Login/input_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/input_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/button_SignIn'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), '.*D365.*', true)
    }

    @Given("User click to select (.*)")
    def selectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/dropdown_LegalEntity'))
        WebUI.setText(findTestObject('Page_Home/input_LegalEntity'), legalentity)
        WebUI.sendKeys(findTestObject('Page_Home/input_LegalEntity'), Keys.chord(Keys.ENTER))
    }

    @When("user navigates to the 'User sites' page")
    def navigateToUserSites() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/title_UserSites'), 10)
    }

    @When("user removes all the user sites with user ID 'Lomas.Gupta'")
    def removeAllUserSites() {
        // Custom logic to select and remove all user sites for 'Lomas.Gupta'
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_User', ['username': 'Lomas.Gupta']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/row_User_Delete', ['username': 'Lomas.Gupta']))
            WebUI.acceptAlert()
        }
    }

    @When("add a user site with LE '(.*)', user 'Lomas.gupta', Site '7100' and isDefault 'true' if not already added")
    def addUserSite7100(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite', ['username': 'Lomas.Gupta', 'site': '7100']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_Add'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.Gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    @When("add a user site with LE '(.*)', user 'Lomas.gupta', Site '3434' and isDefault 'true' if not already added")
    def addUserSite3434(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite', ['username': 'Lomas.Gupta', 'site': '3434']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_Add'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.Gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    @When("test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page")
    def verifyValidationError() {
        WebUI.click(findTestObject('Page_UserSites/button_NavigateToSalesOrder'))
        WebUI.verifyElementPresent(findTestObject('Page_SalesOrder/label_ValidationError'), 10)
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_SalesOrder/label_ValidationError'), 'User cannot have multiple default sites in same legal entity', false))
        WebUI.closeBrowser()
    }
}
