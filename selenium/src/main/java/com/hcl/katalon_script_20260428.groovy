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

class UserSiteFieldsStepDefs {

    @Given("User login to D365 for {string}")
    def userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL)
        WebUI.setText(findTestObject('Page_Login/input_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/input_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/button_SignIn'))
        // Optionally verify login
        WebUI.verifyElementPresent(findTestObject('Page_Home/icon_UserProfile'), 10)
    }

    @Given("User click to select {string}")
    def userClickLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/dropdown_LegalEntity'))
        WebUI.setText(findTestObject('Page_Home/input_LegalEntitySearch'), legalentity)
        WebUI.sendKeys(findTestObject('Page_Home/input_LegalEntitySearch'), Keys.ENTER)
    }

    @When("user navigates to the 'User sites' page")
    def navigateToUserSites() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/title_UserSites'), 10)
    }

    @When("user removes all the user sites with user ID 'Lomas.Gupta'")
    def removeAllUserSites() {
        // Assume a search and delete mechanism exists
        WebUI.setText(findTestObject('Page_UserSites/input_UserSearch'), 'Lomas.Gupta')
        WebUI.click(findTestObject('Page_UserSites/button_Search'))
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/button_Delete'))
            WebUI.click(findTestObject('Page_UserSites/button_ConfirmDelete'))
            WebUI.delay(1)
        }
    }

    @When("add a user site with LE '{string}', user 'Lomas.gupta', Site '7100' and isDefault 'true' if not already added")
    def addUserSite7100(String legalentity) {
        addUserSiteIfNotExists(legalentity, 'Lomas.gupta', '7100', true)
    }

    @When("add a user site with LE '{string}', user 'Lomas.gupta', Site '3434' and isDefault 'true' if not already added")
    def addUserSite3434(String legalentity) {
        addUserSiteIfNotExists(legalentity, 'Lomas.gupta', '3434', true)
    }

    @When("test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page")
    def verifyValidationError() {
        WebUI.click(findTestObject('Page_UserSites/button_Save'))
        WebUI.verifyMatch(
            WebUI.getText(findTestObject('Page_UserSites/label_ErrorMessage')),
            'User cannot have multiple default sites in same legal entity',
            false
        )
    }

    private void addUserSiteIfNotExists(String legalentity, String user, String site, boolean isDefault) {
        WebUI.click(findTestObject('Page_UserSites/button_AddUserSite'))
        WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
        WebUI.setText(findTestObject('Page_UserSites/input_User'), user)
        WebUI.setText(findTestObject('Page_UserSites/input_Site'), site)
        if (isDefault) {
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
        }
        WebUI.click(findTestObject('Page_UserSites/button_SaveUserSite'))
        WebUI.delay(1)
    }
}
