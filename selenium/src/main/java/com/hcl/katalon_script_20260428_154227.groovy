import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.response.ResponseObject
groovy.json.JsonSlurper

String baseUrl = 'https://team-1583910763216.atlassian.net/api/australianbusinessregister/abndetails'
String abn = '12345678901' // Example ABN, replace with valid test data
String authToken = 'Bearer <OAuth2_Token>' // Replace with actual OAuth 2.0 token

TestObject apiRequest = new TestObject('GetBusinessDetailsByABN')
apiRequest.addProperty('RestUrl', ConditionType.EQUALS, "${baseUrl}/${abn}")
apiRequest.setRestRequestMethod('GET')

List<TestObjectProperty> headers = [
    new TestObjectProperty('Authorization', ConditionType.EQUALS, authToken),
    new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
]
apiRequest.setHttpHeaderProperties(headers)

ResponseObject response = WS.sendRequest(apiRequest)

JsonSlurper slurper = new JsonSlurper()
def jsonResponse = slurper.parseText(response.getResponseText())

assert response.getStatusCode() == 200 : "Expected HTTP 200, got ${response.getStatusCode()}"
assert jsonResponse.ABN == abn : "ABN in response does not match requested ABN"
assert jsonResponse.AuthenticationGUID != null : "Authentication GUID is missing"
assert jsonResponse.BusinessName != null : "Business Name is missing"

String invalidAbn = '00000000000'
apiRequest.addProperty('RestUrl', ConditionType.EQUALS, "${baseUrl}/${invalidAbn}")
ResponseObject invalidResponse = WS.sendRequest(apiRequest)
assert invalidResponse.getStatusCode() == 400 : "Expected HTTP 400 for invalid ABN, got ${invalidResponse.getStatusCode()}"

try {
    apiRequest.addProperty('RestUrl', ConditionType.EQUALS, "${baseUrl}/") // Missing ABN
    ResponseObject errorResponse = WS.sendRequest(apiRequest)
    assert errorResponse.getStatusCode() == 400 : "Expected HTTP 400 for missing ABN, got ${errorResponse.getStatusCode()}"
} catch (Exception e) {
    println "Unexpected error occurred: ${e.message}"
}

println "Response: ${response.getResponseText()}"

if (response.getStatusCode() != 200) {
    WS.comment("Alert: API returned non-success status code ${response.getStatusCode()}")
}