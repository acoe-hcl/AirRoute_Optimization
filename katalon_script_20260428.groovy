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

class D365UserSiteSteps {

    @Given("User login to D365 for {string}")
    def userLoginToD365(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL)
        WebUI.setText(findTestObject('LoginPage/Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('LoginPage/Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('LoginPage/LoginButton'))
        WebUI.verifyMatch(WebUI.getWindowTitle(), 'Microsoft Dynamics 365', false)
    }

    @Given("User click to select {string}")
    def userClickToSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('HomePage/LegalEntityDropdown'))
        WebUI.setText(findTestObject('HomePage/LegalEntitySearchBox'), legalentity)
        WebUI.sendKeys(findTestObject('HomePage/LegalEntitySearchBox'), Keys.ENTER)
        WebUI.verifyMatch(WebUI.getText(findTestObject('HomePage/SelectedLegalEntity')), legalentity, false)
    }

    @When("user navigates to the 'User sites' page")
    def userNavigatesToUserSitesPage() {
        WebUI.click(findTestObject('HomePage/UserSitesMenu'))
        WebUI.verifyElementPresent(findTestObject('UserSitesPage/UserSitesHeader'), 10)
    }

    @When("user removes all the user sites with user ID 'Lomas.Gupta'")
    def userRemovesAllUserSites() {
        // Assuming a table with user sites and a delete button per row
        while (WebUI.verifyElementPresent(findTestObject('UserSitesPage/UserRow', ['userId' : 'Lomas.Gupta']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('UserSitesPage/DeleteButton', ['userId' : 'Lomas.Gupta']))
            WebUI.acceptAlert()
            WebUI.delay(1)
        }
    }

    @When("add a user site with LE '{string}', user 'Lomas.gupta', Site '7100' and isDefault 'true' if not already added")
    def addUserSite7100(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('UserSitesPage/UserSiteRow', ['userId' : 'Lomas.Gupta', 'site' : '7100', 'isDefault' : 'true']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('UserSitesPage/AddButton'))
            WebUI.setText(findTestObject('UserSitesPage/LegalEntityInput'), legalentity)
            WebUI.setText(findTestObject('UserSitesPage/UserInput'), 'Lomas.gupta')
            WebUI.setText(findTestObject('UserSitesPage/SiteInput'), '7100')
            WebUI.click(findTestObject('UserSitesPage/IsDefaultCheckbox'))
            WebUI.click(findTestObject('UserSitesPage/SaveButton'))
        }
    }

    @When("add a user site with LE '{string}', user 'Lomas.gupta', Site '3434' and isDefault 'true' if not already added")
    def addUserSite3434(String legalentity) {
        if (!WebUI.verifyElementPresent(findTestObject('UserSitesPage/UserSiteRow', ['userId' : 'Lomas.Gupta', 'site' : '3434', 'isDefault' : 'true']), 2, FailureHandling.OPTIONAL)) {
            WebUI.click(findTestObject('UserSitesPage/AddButton'))
            WebUI.setText(findTestObject('UserSitesPage/LegalEntityInput'), legalentity)
            WebUI.setText(findTestObject('UserSitesPage/UserInput'), 'Lomas.gupta')
            WebUI.setText(findTestObject('UserSitesPage/SiteInput'), '3434')
            WebUI.click(findTestObject('UserSitesPage/IsDefaultCheckbox'))
            WebUI.click(findTestObject('UserSitesPage/SaveButton'))
        }
    }

    @When("test for the validation error message 'User cannot have multiple default sites in same legal entity' on the Sales Order page")
    def verifyValidationErrorMessage() {
        WebUI.click(findTestObject('UserSitesPage/NavigateToSalesOrder'))
        WebUI.verifyElementPresent(findTestObject('SalesOrderPage/ErrorMessage'), 10)
        WebUI.verifyMatch(WebUI.getText(findTestObject('SalesOrderPage/ErrorMessage')), 
            'User cannot have multiple default sites in same legal entity', false)
    }
}