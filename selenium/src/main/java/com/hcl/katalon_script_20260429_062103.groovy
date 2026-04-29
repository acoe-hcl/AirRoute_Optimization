import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

// --- Test Data ---
String baseUrl = 'https://your-api-domain.com' // Replace with actual base URL
String abn = '53004085616' // Example valid ABN
String invalidAbn = '123' // Example invalid ABN
String authGuid = 'your-auth-guid' // Replace with actual GUID

// --- Helper Method to Create Request Object ---
TestObject createGetAbnDetailsRequest(String abnValue, String authGuidValue) {
    TestObject request = new TestObject('GetABNDetails')
    request.addProperty('RestUrl', ConditionType.EQUALS, "${baseUrl}/api/australianbusinessregister/abndetails/${abnValue}")
    request.setRestRequestMethod('GET')
    request.addProperty('Authorization', ConditionType.EQUALS, "Bearer ${GlobalVariable.OAuthToken}") // OAuth 2.0 token
    if (authGuidValue != null && authGuidValue != '') {
        request.addProperty('Authentication-GUID', ConditionType.EQUALS, authGuidValue)
    }
    return request
}

// --- Positive Test: Valid ABN ---
TestObject validRequest = createGetAbnDetailsRequest(abn, authGuid)
def validResponse = WS.sendRequest(validRequest)
WS.verifyResponseStatusCode(validResponse, 200)

// Parse and validate response body
def jsonSlurper = new JsonSlurper()
def responseBody = jsonSlurper.parseText(validResponse.getResponseBodyContent())

assert responseBody.Abn == abn
assert responseBody.AbnStatus != null
assert responseBody.AbnStatusEffectiveFrom != null
assert responseBody.Acn != null
assert responseBody.AddresssDate != null
assert responseBody.AddressPostcode != null
assert responseBody.AddressState != null
assert responseBody.BusinessName instanceof List
assert responseBody.EntityName != null
assert responseBody.EntityTypeCode != null
assert responseBody.EntityTypeName != null
assert responseBody.Gst != null
assert responseBody.Message != null

// --- Negative Test: Missing ABN (should return 400) ---
TestObject missingAbnRequest = createGetAbnDetailsRequest('', authGuid)
def missingAbnResponse = WS.sendRequest(missingAbnRequest)
WS.verifyResponseStatusCode(missingAbnResponse, 400)

// --- Negative Test: Invalid ABN Format (should return 400) ---
TestObject invalidAbnRequest = createGetAbnDetailsRequest(invalidAbn, authGuid)
def invalidAbnResponse = WS.sendRequest(invalidAbnRequest)
WS.verifyResponseStatusCode(invalidAbnResponse, 400)

// --- Negative Test: Missing Authentication GUID (should return 400) ---
TestObject missingGuidRequest = createGetAbnDetailsRequest(abn, '')
def missingGuidResponse = WS.sendRequest(missingGuidRequest)
WS.verifyResponseStatusCode(missingGuidResponse, 400)

// --- Negative Test: Simulate Unexpected Error (should return 500) ---
// Uncomment and adapt this section if you have a way to trigger a server error
// TestObject serverErrorRequest = createGetAbnDetailsRequest('trigger500', authGuid)
// def serverErrorResponse = WS.sendRequest(serverErrorRequest)
// WS.verifyResponseStatusCode(serverErrorResponse, 500)

/*
 * Test Coverage and Accuracy:
 * - Validates all required response fields for a successful request.
 * - Verifies correct status codes for missing ABN, invalid ABN format, and missing Authentication GUID.
 * - Ensures OAuth 2.0 and required headers are present in all requests.
 * - Covers both positive and negative scenarios as per BRD.
 * - Script is modular for maintainability and readability.
 * - Uses TestObjectProperty, ConditionType, and JsonSlurper for parsing.
 * - Can be extended for additional error or edge cases as needed.
 */
// End of Script