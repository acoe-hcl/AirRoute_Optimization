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

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then

class UserSiteFieldsSteps {

    @Given("User login to D365 for (.*)")
    def userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl('https://d365.yourcompany.com')
        WebUI.setText(findTestObject('Page_Login/input_Username'), 'Lomas.Gupta')
        WebUI.setText(findTestObject('Page_Login/input_Password'), 'your_password')
        WebUI.click(findTestObject('Page_Login/button_Login'))
        // Optionally verify login
        WebUI.verifyElementPresent(findTestObject('Page_Home/icon_Home'), 10)
    }

    @Given("User click to select (.*)")
    def userSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/dropdown_LegalEntity'))
        WebUI.setText(findTestObject('Page_Home/input_LegalEntity'), legalentity)
        WebUI.sendKeys(findTestObject('Page_Home/input_LegalEntity'), Keys.chord(Keys.ENTER))
    }

    @When("user navigates to the 'User sites' page")
    def navigateToUserSitesPage() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/title_UserSites'), 10)
    }

    @When("user removes all the user sites with user ID 'Lomas.Gupta'")
    def removeAllUserSites() {
        WebUI.setText(findTestObject('Page_UserSites/input_UserID'), 'Lomas.Gupta')
        WebUI.click(findTestObject('Page_UserSites/button_Search'))
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_Delete'))
            WebUI.click(findTestObject('Page_UserSites/button_ConfirmDelete'))
        }
    }

    @When("add a user site with LE '(.*)', user 'Lomas.gupta', Site '7100' and isDefault 'true' if not already added")
    def addUserSite7100(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_Site7100'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_Add'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    @When("add a user site with LE '(.*)', user 'Lomas.gupta', Site '3434' and isDefault 'true' if not already added")
    def addUserSite3434(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_Site3434'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_Add'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    @When("test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page")
    def validateErrorMessage() {
        WebUI.click(findTestObject('Page_SalesOrder/menu_SalesOrder'))
        WebUI.verifyElementPresent(findTestObject('Page_SalesOrder/label_Error'), 10)
        String actualError = WebUI.getText(findTestObject('Page_SalesOrder/label_Error'))
        WebUI.verifyMatch(actualError, 'User cannot have multiple default sites in same legal entity', false)
        WebUI.closeBrowser()
    }
}

// Test Suite/Runner Example
CucumberKW.runWithCucumberRunner(UserSiteFieldsSteps)
