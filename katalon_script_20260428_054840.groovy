import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords.findWindowsObject

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

    @Given("User login to D365 for (.*)")
    def userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL)
        WebUI.setText(findTestObject('Page_Login/input_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/input_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/button_Login'))
        // Optionally verify login success
        WebUI.verifyElementPresent(findTestObject('Page_Home/icon_Home'), 10)
    }

    @Given("User click to select (.*)")
    def selectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/dropdown_LegalEntity'))
        WebUI.setText(findTestObject('Page_Home/input_LegalEntitySearch'), legalentity)
        WebUI.sendKeys(findTestObject('Page_Home/input_LegalEntitySearch'), Keys.ENTER)
        // Optionally verify selection
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_Home/label_SelectedLegalEntity')), legalentity, false)
    }

    @When("user navigates to the 'User sites' page")
    def navigateToUserSites() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/title_UserSites'), 10)
    }

    @When("user removes all the user sites with user ID 'Lomas.Gupta'")
    def removeAllUserSites() {
        // This is a placeholder for the actual removal logic
        // Loop through user sites and remove those with user ID 'Lomas.Gupta'
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_LomasGupta'), 3, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/row_LomasGupta/button_Delete'))
            WebUI.click(findTestObject('Page_UserSites/button_ConfirmDelete'))
        }
    }

    @When("add a user site with LE '(.*)', user 'Lomas.gupta', Site '7100' and isDefault 'true' if not already added")
    def addUserSite7100(String legalentity) {
        // Check if site 7100 exists for Lomas.Gupta and legalentity, add if not
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_Site7100'), 3, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_AddUserSite'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    @When("add a user site with LE '(.*)', user 'Lomas.gupta', Site '3434' and isDefault 'true' if not already added")
    def addUserSite3434(String legalentity) {
        // Check if site 3434 exists for Lomas.Gupta and legalentity, add if not
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_Site3434'), 3, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_AddUserSite'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    @When("test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page")
    def verifyValidationError() {
        WebUI.click(findTestObject('Page_SalesOrder/menu_SalesOrder'))
        WebUI.verifyElementPresent(findTestObject('Page_SalesOrder/label_ValidationError'), 10)
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_SalesOrder/label_ValidationError'),
            'User cannot have multiple default sites in same legal entity', false))
    }
}
