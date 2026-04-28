import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil

// Base URL and OAuth token (replace with actual values or use GlobalVariables)
def baseUrl = 'https://your-api-domain.com'
def abn = '12345678901' // Valid ABN for positive test
def invalidAbn = '12345' // Invalid ABN for negative test
def oauthToken = 'Bearer <your-oauth-token>'

// Utility function to create GET TestObject for ABN endpoint
TestObject createAbnGetTestObject(String abnValue, boolean withAuth = true) {
    TestObject to = new TestObject('GetBusinessDetailsByABN_' + (abnValue ?: 'empty'))
    to.addProperty('RestUrl', ConditionType.EQUALS, "${baseUrl}/api/australianbusinessregister/abndetails/${abnValue}")
    List<TestObjectProperty> headers = []
    if (withAuth) {
        headers.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, oauthToken))
    }
    headers.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
    to.setHttpHeaderProperties(headers)
    to.setRestRequestMethod('GET')
    return to
}

// Positive Test: Valid ABN
def validAbnTestObject = createAbnGetTestObject(abn)
def validResponse = WS.sendRequest(validAbnTestObject)
WS.verifyResponseStatusCode(validResponse, 200)
def jsonSlurper = new JsonSlurper()
def validResponseBody = jsonSlurper.parseText(validResponse.getResponseBodyContent())
KeywordUtil.logInfo("Valid ABN Response: " + validResponseBody)
assert validResponseBody.abn == abn
assert validResponseBody.businessName != null

// Negative Test: Invalid ABN Format
def invalidAbnTestObject = createAbnGetTestObject(invalidAbn)
def invalidResponse = WS.sendRequest(invalidAbnTestObject)
WS.verifyResponseStatusCode(invalidResponse, 400)
def invalidResponseBody = jsonSlurper.parseText(invalidResponse.getResponseBodyContent())
KeywordUtil.logInfo("Invalid ABN Response: " + invalidResponseBody)
assert invalidResponseBody.error != null

// Negative Test: Missing ABN (empty path param)
def missingAbnTestObject = createAbnGetTestObject('')
def missingAbnResponse = WS.sendRequest(missingAbnTestObject)
WS.verifyResponseStatusCode(missingAbnResponse, 400)
def missingAbnResponseBody = jsonSlurper.parseText(missingAbnResponse.getResponseBodyContent())
KeywordUtil.logInfo("Missing ABN Response: " + missingAbnResponseBody)
assert missingAbnResponseBody.error != null

// Negative Test: Missing Authorization Header
def noAuthTestObject = createAbnGetTestObject(abn, false)
def noAuthResponse = WS.sendRequest(noAuthTestObject)
WS.verifyResponseStatusCode(noAuthResponse, 401)
def noAuthResponseBody = jsonSlurper.parseText(noAuthResponse.getResponseBodyContent())
KeywordUtil.logInfo("No Auth Response: " + noAuthResponseBody)
assert noAuthResponseBody.error != null

// Negative Test: Unexpected Error (simulate by invalid endpoint)
TestObject unexpectedErrorTestObject = new TestObject('GetBusinessDetailsByABN_InvalidEndpoint')
unexpectedErrorTestObject.addProperty('RestUrl', ConditionType.EQUALS, "${baseUrl}/api/australianbusinessregister/abndetails/invalidendpoint")
List<TestObjectProperty> headers = []
headers.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, oauthToken))
headers.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
unexpectedErrorTestObject.setHttpHeaderProperties(headers)
unexpectedErrorTestObject.setRestRequestMethod('GET')
def unexpectedErrorResponse = WS.sendRequest(unexpectedErrorTestObject)
WS.verifyResponseStatusCode(unexpectedErrorResponse, 500)
def unexpectedErrorResponseBody = jsonSlurper.parseText(unexpectedErrorResponse.getResponseBodyContent())
KeywordUtil.logInfo("Unexpected Error Response: " + unexpectedErrorResponseBody)
assert unexpectedErrorResponseBody.error != null