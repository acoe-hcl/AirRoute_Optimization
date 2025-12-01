import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

public class TransactionApiTests {

    private static final String BASE_URL = "https://your-api-url.com";
    private static final String ADD_TRANSACTION_ENDPOINT = "/api/transactions/add";
    private static final String GET_TABLE_ENDPOINT = "/api/table/get";
    private static final String ACCESS_TOKEN = "YOUR_VALID_ACCESS_TOKEN";

    // Helper to build request headers
    private Map<String, String> getAuthHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + ACCESS_TOKEN);
        return headers;
    }

    @Test
    public void TC01_CreateTransactionWithValidInput_BalancedDebitCredit() {
        Map<String, Object> body = new HashMap<>();
        body.put("valueDate", "2024-07-10");
        body.put("officeId", 12);
        body.put("userId", 1001);
        body.put("logOnId", "logon123");
        body.put("costCenterId", 900);
        body.put("referenceNumber", "REF123ABC");
        List<Map<String, Object>> details = new ArrayList<>();

        Map<String, Object> detailRow = new HashMap<>();
        detailRow.put("debitTotal", 100.00);
        detailRow.put("creditTotal", 100.00);
        detailRow.put("description", "Valid Transaction");
        details.add(detailRow);

        body.put("details", details);

        Response res =
            RestAssured.given()
                .baseUri(BASE_URL)
                .headers(getAuthHeader())
                .contentType(ContentType.JSON)
                .body(body)
                .post(ADD_TRANSACTION_ENDPOINT);

        res.then().statusCode(200)
           .contentType(ContentType.JSON)
           .body("transactionId", Matchers.notNullValue());

        String transactionId = res.jsonPath().getString("transactionId");
        Assert.assertNotNull(transactionId);
    }

    @Test
    public void TC02_CreateTransactionWithMultipleDetailRows_BalancedTotals() {
        Map<String, Object> body = new HashMap<>();
        body.put("valueDate", "2024-07-10");
        body.put("officeId", 12);
        body.put("userId", 1001);
        body.put("logOnId", "logon123");
        body.put("costCenterId", 901);
        body.put("referenceNumber", "MULTIREF987");

        List<Map<String, Object>> details = new ArrayList<>();
        Map<String, Object> row1 = new HashMap<>();
        row1.put("debitTotal", 50.00);
        row1.put("creditTotal", 0.00);
        row1.put("description", "Detail 1");
        details.add(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("debitTotal", 0.00);
        row2.put("creditTotal", 50.00);
        row2.put("description", "Detail 2");
        details.add(row2);

        body.put("details", details);

        Response res =
            RestAssured.given()
                .baseUri(BASE_URL)
                .headers(getAuthHeader())
                .contentType(ContentType.JSON)
                .body(body)
                .post(ADD_TRANSACTION_ENDPOINT);

        res.then().statusCode(200)
           .contentType(ContentType.JSON)
           .body("transactionId", Matchers.notNullValue());

        double debitTotal = details.stream().mapToDouble(d -> (Double) d.get("debitTotal")).sum();
        double creditTotal = details.stream().mapToDouble(d -> (Double) d.get("creditTotal")).sum();
        Assert.assertEquals(debitTotal, creditTotal, 0.001);
    }

    @Test
    public void TC03_SubmitWithReferenceNumber() {
        Map<String, Object> body = new HashMap<>();
        body.put("valueDate", "2024-07-10");
        body.put("officeId", 13);
        body.put("userId", 1002);
        body.put("logOnId", "logon789");
        body.put("costCenterId", 902);
        body.put("referenceNumber", "REFALPHA567");
        List<Map<String, Object>> details = new ArrayList<>();
        Map<String, Object> detailRow = new HashMap<>();
        detailRow.put("debitTotal", 60.00);
        detailRow.put("creditTotal", 60.00);
        details.add(detailRow);
        body.put("details", details);

        Response res =
            RestAssured.given()
                .baseUri(BASE_URL)
                .headers(getAuthHeader())
                .contentType(ContentType.JSON)
                .body(body)
                .post(ADD_TRANSACTION_ENDPOINT);

        res.then().statusCode(200)
           .contentType(ContentType.JSON)
           .body("transactionId", Matchers.notNullValue());
    }

    @Test
    public void TC04_ValueDateWithinAllowedDateRange() {
        Map<String, Object> body = new HashMap<>();
        body.put("valueDate", "2024-06-15");
        body.put("officeId", 14);
        body.put("userId", 1003);
        body.put("logOnId", "logon321");
        body.put("costCenterId", 903);
        body.put("referenceNumber", "DATEOK412");
        List<Map<String, Object>> details = new ArrayList<>();
        Map<String, Object> detailRow = new HashMap<>();
        detailRow.put("debitTotal", 80.00);
        detailRow.put("creditTotal", 80.00);
        details.add(detailRow);
        body.put("details", details);

        Response res =
            RestAssured.given()
                .baseUri(BASE_URL)
                .headers(getAuthHeader())
                .contentType(ContentType.JSON)
                .body(body)
                .post(ADD_TRANSACTION_ENDPOINT);

        res.then().statusCode(200)
           .contentType(ContentType.JSON)
           .body("transactionId", Matchers.notNullValue());
    }

    @Test
    public void TC19_GetTableDetailsWithValidId() {
        String tableId = "123"; // Should be a valid tableId from your test DB

        Response res =
            RestAssured.given()
                .baseUri(BASE_URL)
                .headers(getAuthHeader())
                .contentType(ContentType.JSON)
                .queryParam("tableId", tableId)
                .get(GET_TABLE_ENDPOINT);

        res.then().statusCode(200)
           .contentType(ContentType.JSON)
           .body("tableId", Matchers.equalTo(tableId))
           .body("relatedData", Matchers.notNullValue());
    }

    // NEGATIVE TEST CASES
    @Test
    public void TC_NEG01_CreateTransaction_MissingRequiredFields() {
        Map<String, Object> body = new HashMap<>();
        body.put("officeId", 15);
        // required: valueDate, userId, logOnId, costCenterId, details missing

        Response res =
            RestAssured.given()
                .baseUri(BASE_URL)
                .headers(getAuthHeader())
                .contentType(ContentType.JSON)
                .body(body)
                .post(ADD_TRANSACTION_ENDPOINT);

        res.then().statusCode(Matchers.oneOf(400, 422));
    }

    @Test
    public void TC_NEG02_CreateTransaction_DebitCreditMismatch() {
        Map<String, Object> body = new HashMap<>();
        body.put("valueDate", "2024-07-10");
        body.put("officeId", 16);
        body.put("userId", 1004);
        body.put("logOnId", "logon555");
        body.put("costCenterId", 904);
        body.put("referenceNumber", "MISMATCH001");
        List<Map<String, Object>> details = new ArrayList<>();
        Map<String, Object> detailRow1 = new HashMap<>();
        detailRow1.put("debitTotal", 90.00);
        detailRow1.put("creditTotal", 50.00);
        details.add(detailRow1);
        body.put("details", details);

        Response res =
            RestAssured.given()
                .baseUri(BASE_URL)
                .headers(getAuthHeader())
                .contentType(ContentType.JSON)
                .body(body)
                .post(ADD_TRANSACTION_ENDPOINT);

        res.then().statusCode(Matchers.oneOf(400, 422));
    }

    @Test
    public void TC_NEG03_GetTable_InvalidTableId() {
        String tableId = "999999"; // Assuming this ID doesn't exist

        Response res =
            RestAssured.given()
                .baseUri(BASE_URL)
                .headers(getAuthHeader())
                .contentType(ContentType.JSON)
                .queryParam("tableId", tableId)
                .get(GET_TABLE_ENDPOINT);

        res.then().statusCode(Matchers.oneOf(404, 400))
           .body("error", Matchers.notNullValue());
    }

    @Test
    public void TC_NEG04_UnauthorizedAccess_ToAddTransaction() {
        Map<String, Object> body = new HashMap<>();
        body.put("valueDate", "2024-07-10");
        body.put("officeId", 17);
        body.put("userId", 1005);
        body.put("logOnId", "logon888");
        body.put("costCenterId", 905);
        body.put("referenceNumber", "UNAUTH001");
        List<Map<String, Object>> details = new ArrayList<>();
        Map<String, Object> detailRow = new HashMap<>();
        detailRow.put("debitTotal", 25.00);
        detailRow.put("creditTotal", 25.00);
        details.add(detailRow);
        body.put("details", details);

        Response res =
            RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .post(ADD_TRANSACTION_ENDPOINT);

        res.then().statusCode(401);
    }
}
