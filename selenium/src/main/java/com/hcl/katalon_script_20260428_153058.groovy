import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

// === CONFIGURATION ===
String baseUrl = 'https://your-api-domain.com'
String endpoint = '/api/australianbusinessregister/abndetails/'
String validAbn = '53004085616' // Example valid ABN
String invalidAbn = '123'       // Example invalid ABN
String oauthToken = 'Bearer <your-oauth-token>' // Replace with actual token

// === POSITIVE TEST CASE: Valid ABN ===
RequestObject request = new RequestObject()
request.setRestUrl(baseUrl + endpoint + validAbn)
request.setRestRequestMethod('GET')
request.setHttpHeaderProperties([
    new TestObjectProperty('Authorization', ConditionType.EQUALS, oauthToken),
    new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
])

def response = WS.sendRequest(request)
assert response.getStatusCode() == 200 : "Expected HTTP 200, got ${response.getStatusCode()}"

def json = new JsonSlurper().parseText(response.getResponseText())
assert json.abn == validAbn : "ABN mismatch"
assert json.abnStatus != null : "ABN Status missing"
assert json.businessName instanceof List : "BusinessName should be an array"

// === NEGATIVE TEST CASES ===

// 1. Missing ABN (empty path parameter)
RequestObject missingAbnRequest = new RequestObject()
missingAbnRequest.setRestUrl(baseUrl + endpoint)
missingAbnRequest.setRestRequestMethod('GET')
missingAbnRequest.setHttpHeaderProperties([
    new TestObjectProperty('Authorization', ConditionType.EQUALS, oauthToken),
    new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
])
def missingAbnResponse = WS.sendRequest(missingAbnRequest)
assert missingAbnResponse.getStatusCode() == 400 : "Expected HTTP 400 for missing ABN"

// 2. Invalid ABN format
RequestObject invalidAbnRequest = new RequestObject()
invalidAbnRequest.setRestUrl(baseUrl + endpoint + invalidAbn)
invalidAbnRequest.setRestRequestMethod('GET')
invalidAbnRequest.setHttpHeaderProperties([
    new TestObjectProperty('Authorization', ConditionType.EQUALS, oauthToken),
    new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
])
def invalidAbnResponse = WS.sendRequest(invalidAbnRequest)
assert invalidAbnResponse.getStatusCode() == 400 : "Expected HTTP 400 for invalid ABN format"

// 3. Missing mandatory header (Authorization)
RequestObject missingHeaderRequest = new RequestObject()
missingHeaderRequest.setRestUrl(baseUrl + endpoint + validAbn)
missingHeaderRequest.setRestRequestMethod('GET')
missingHeaderRequest.setHttpHeaderProperties([
    new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
])
def missingHeaderResponse = WS.sendRequest(missingHeaderRequest)
assert (missingHeaderResponse.getStatusCode() == 400 || missingHeaderResponse.getStatusCode() == 401) : "Expected HTTP 400/401 for missing Authorization header"

// 4. Unexpected error simulation (invalid endpoint)
RequestObject unexpectedErrorRequest = new RequestObject()
unexpectedErrorRequest.setRestUrl(baseUrl + '/api/australianbusinessregister/invalidendpoint/' + validAbn)
unexpectedErrorRequest.setRestRequestMethod('GET')
unexpectedErrorRequest.setHttpHeaderProperties([
    new TestObjectProperty('Authorization', ConditionType.EQUALS, oauthToken),
    new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')
])
def unexpectedErrorResponse = WS.sendRequest(unexpectedErrorRequest)
assert unexpectedErrorResponse.getStatusCode() == 500 : "Expected HTTP 500 for unexpected error"