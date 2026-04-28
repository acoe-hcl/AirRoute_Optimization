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

import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import cucumber.api.java.en.And

class UserSiteFieldsSteps {

    @Given('User login to D365 for {string}')
    def userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL)
        WebUI.setText(findTestObject('Page_Login/txt_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/txt_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/btn_Login'))
        // Optionally, verify login success
        WebUI.verifyElementPresent(findTestObject('Page_Home/icon_Home'), 10)
    }

    @And('User click to select {string}')
    def userClickToSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Home/dropdown_LegalEntity'))
        WebUI.setText(findTestObject('Page_Home/input_LegalEntity'), legalentity)
        WebUI.sendKeys(findTestObject('Page_Home/input_LegalEntity'), Keys.chord(Keys.ENTER))
    }

    @When("user navigates to the 'User sites' page")
    def navigateToUserSitesPage() {
        WebUI.click(findTestObject('Page_Home/menu_UserSites'))
        WebUI.verifyElementPresent(findTestObject('Page_UserSites/title_UserSites'), 10)
    }

    @And("user removes all the user sites with user ID 'Sachin'")
    def removeAllUserSitesForSachin() {
        // Search for user 'Sachin'
        WebUI.setText(findTestObject('Page_UserSites/input_UserID'), 'Sachin')
        WebUI.click(findTestObject('Page_UserSites/btn_Search'))
        // Remove all entries (loop if multiple)
        while (WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_Sachin'), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('Page_UserSites/row_UserSite_Sachin/btn_Delete'))
            WebUI.click(findTestObject('Page_UserSites/btn_ConfirmDelete'))
        }
    }

    @And("add a user site with LE {string}, user 'Sachin', Site '7100' and isDefault 'true' if not already added")
    def addUserSite7100(String legalentity) {
        addUserSiteIfNotExists(legalentity, 'Sachin', '7100', true)
    }

    @And("add a user site with LE {string}, user 'Sachin', Site '3434' and isDefault 'true' if not already added")
    def addUserSite3434(String legalentity) {
        addUserSiteIfNotExists(legalentity, 'Sachin', '3434', true)
    }

    @And("test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page")
    def verifyValidationError() {
        WebUI.click(findTestObject('Page_SalesOrder/btn_Save'))
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_SalesOrder/lbl_ErrorMessage')),
            'User cannot have multiple default sites in same legal entity', false)
    }

    // Helper method
    private void addUserSiteIfNotExists(String legalentity, String user, String site, boolean isDefault) {
        // Check if user site already exists
        WebUI.setText(findTestObject('Page_UserSites/input_UserID'), user)
        WebUI.click(findTestObject('Page_UserSites/btn_Search'))
        boolean exists = WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_Sachin'), 2, FailureHandling.OPTIONAL)
        if (!exists) {
            WebUI.click(findTestObject('Page_UserSites/btn_AddUserSite'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), user)
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), site)
            if (isDefault) {
                WebUI.click(findTestObject('Page_UserSites/chk_IsDefault'))
            }
            WebUI.click(findTestObject('Page_UserSites/btn_SaveUserSite'))
            // Optionally, verify addition
            WebUI.verifyElementPresent(findTestObject('Page_UserSites/row_UserSite_Sachin'), 5)
        }
    }
}

// Test Suite Example (TestNG style):
import org.testng.annotations.BeforeTest
import org.testng.annotations.AfterTest

class UserSiteFieldsTestSuite {
    @BeforeTest
    def setup() {
        // Set global variables or test data as needed
        GlobalVariable.D365_URL = 'https://d365.example.com'
        GlobalVariable.D365_Username = 'your_username'
        GlobalVariable.D365_Password = 'your_password'
    }

    @AfterTest
    def teardown() {
        WebUI.closeBrowser()
    }
}