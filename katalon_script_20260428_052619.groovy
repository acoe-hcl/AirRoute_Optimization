import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords.findWindowsObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

class UserSiteFieldsSteps {

    void userLogin(String testcaseName) {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(GlobalVariable.D365_URL) // Set this in GlobalVariable
        WebUI.setText(findTestObject('Page_Login/input_Username'), GlobalVariable.D365_Username)
        WebUI.setText(findTestObject('Page_Login/input_Password'), GlobalVariable.D365_Password)
        WebUI.click(findTestObject('Page_Login/button_Login'))
        WebUI.verifyMatch(WebUI.getUrl(), '.*dashboard.*', true)
    }

    void userSelectLegalEntity(String legalentity) {
        WebUI.click(findTestObject('Page_Dashboard/dropdown_LegalEntity'))
        WebUI.setText(findTestObject('Page_Dashboard/input_LegalEntitySearch'), legalentity)
        WebUI.sendKeys(findTestObject('Page_Dashboard/input_LegalEntitySearch'), Keys.ENTER)
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_Dashboard/selected_LegalEntity')), legalentity, false)
    }

    void navigateToUserSites() {
        WebUI.click(findTestObject('Page_Dashboard/menu_UserSites'))
        WebUI.verifyMatch(WebUI.getText(findTestObject('Page_UserSites/header')), 'User sites', false)
    }

    void removeAllUserSites() {
        List<WebElement> userSiteRows = WebUI.findWebElements(findTestObject('Page_UserSites/rows_UserSite'), 10)
        for (WebElement row : userSiteRows) {
            String userId = row.findElement(By.xpath(".//td[@class='user-id-column']")).getText()
            if (userId.equalsIgnoreCase('Lomas.Gupta')) {
                row.findElement(By.xpath(".//button[contains(@class,'delete')]")).click()
                WebUI.acceptAlert()
            }
        }
    }

    void addUserSite7100(String legalentity) {
        if (!isUserSitePresent(legalentity, 'Lomas.gupta', '7100')) {
            WebUI.click(findTestObject('Page_UserSites/button_AddUserSite'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '7100')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    void addUserSite3434(String legalentity) {
        if (!isUserSitePresent(legalentity, 'Lomas.gupta', '3434')) {
            WebUI.click(findTestObject('Page_UserSites/button_AddUserSite'))
            WebUI.setText(findTestObject('Page_UserSites/input_LE'), legalentity)
            WebUI.setText(findTestObject('Page_UserSites/input_User'), 'Lomas.gupta')
            WebUI.setText(findTestObject('Page_UserSites/input_Site'), '3434')
            WebUI.click(findTestObject('Page_UserSites/checkbox_IsDefault'))
            WebUI.click(findTestObject('Page_UserSites/button_Save'))
        }
    }

    void verifyValidationError() {
        WebUI.click(findTestObject('Page_UserSites/button_NavigateToSalesOrder'))
        String errorMsg = WebUI.getText(findTestObject('Page_SalesOrder/label_ValidationError'))
        WebUI.verifyMatch(errorMsg, 'User cannot have multiple default sites in same legal entity', false)
        WebUI.closeBrowser()
    }

    private boolean isUserSitePresent(String legalentity, String user, String site) {
        List<WebElement> userSiteRows = WebUI.findWebElements(findTestObject('Page_UserSites/rows_UserSite'), 10)
        for (WebElement row : userSiteRows) {
            String le = row.findElement(By.xpath(".//td[@class='le-column']")).getText()
            String u = row.findElement(By.xpath(".//td[@class='user-column']")).getText()
            String s = row.findElement(By.xpath(".//td[@class='site-column']")).getText()
            if (le == legalentity && u.equalsIgnoreCase(user) && s == site) {
                return true
            }
        }
        return false
    }
}

import org.testng.annotations.Test

class UserSiteFieldsTestSuite {
    @Test
    void testUserSiteFields() {
        UserSiteFieldsSteps steps = new UserSiteFieldsSteps()
        steps.userLogin('65090 Pre requisite for xxx- Verify the User site fields is displaying in D365')
        steps.userSelectLegalEntity('101')
        steps.navigateToUserSites()
        steps.removeAllUserSites()
        steps.addUserSite7100('101')
        steps.addUserSite3434('101')
        steps.verifyValidationError()
    }
}
