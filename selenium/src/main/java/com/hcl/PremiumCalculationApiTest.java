import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class PremiumCalculationApiTest {

    private static final String BASE_PATH = "/api/v1";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = BASE_PATH;
    }

    private String bearerToken() {
        return "Bearer dummy-token";
    }

    // Positive Test: Valid premium calculation with minimum fields
    @Test
    public void testCalculatePremium_ValidSinglePolicy() {
        String body = "{"
            + "\"correlationId\":\"corr-12345\","
            + "\"policies\":[{"
            +     "\"policyholder\":{"
            +         "\"age\":30,"
            +         "\"gender\":\"M\""
            +     "},"
            +     "\"policyType\":\"Life\","
            +     "\"coverageAmount\":50000,"
            +     "\"policyTerm\":10"
            + "}]"
            + "}";

        RestAssured.given()
            .header("Authorization", bearerToken())
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/premium/calculate")
        .then()
            .statusCode(200)
            .body("correlationId", Matchers.equalTo("corr-12345"))
            .body("results[0].policyId", Matchers.notNullValue())
            .body("results[0].calculatedPremium", Matchers.greaterThan(0f))
            .body("results[0].calculationRuleVersion", Matchers.notNullValue())
            .body("results[0].calculationTimestamp", Matchers.notNullValue());
    }

    // Positive Test: Valid batch policy, optional fields included
    @Test
    public void testCalculatePremium_ValidBatchPolicy_WithOptionalFields() {
        String body = "{"
            + "\"correlationId\":\"corr-batch\","
            + "\"policies\":["
            +   "{"
            +     "\"policyholder\":{"
            +         "\"age\":65,"
            +         "\"gender\":\"F\","
            +         "\"name\":\"Alice\","
            +         "\"medicalData\":{"
            +            "\"smoker\":false,"
            +            "\"riskScore\":2.3"
            +         "}"
            +     "},"
            +     "\"policyType\":\"Accident\","
            +     "\"coverageAmount\":10000,"
            +     "\"policyTerm\":1,"
            +     "\"externalReferences\":{"
            +          "\"extRef\":\"111-abc\""
            +     "}"
            +   "},"
            +   "{"
            +     "\"policyholder\":{"
            +         "\"age\":45,"
            +         "\"gender\":\"O\""
            +     "},"
            +     "\"policyType\":\"Life\","
            +     "\"coverageAmount\":500000,"
            +     "\"policyTerm\":30"
            +   "}"
            + "]"
            + "}";

        RestAssured.given()
            .header("Authorization", bearerToken())
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/premium/calculate")
        .then()
            .statusCode(200)
            .body("correlationId", Matchers.equalTo("corr-batch"))
            .body("results.size()", Matchers.equalTo(2))
            .body("results[0].policyId", Matchers.notNullValue())
            .body("results[1].policyId", Matchers.notNullValue());
    }

    // Edge Case: Age at minimum boundary
    @Test
    public void testCalculatePremium_AgeMinimumBoundary() {
        String body = "{"
            + "\"correlationId\":\"corr-min-age\","
            + "\"policies\":[{"
            +     "\"policyholder\":{"
            +         "\"age\":0,"
            +         "\"gender\":\"F\""
            +     "},"
            +     "\"policyType\":\"Life\","
            +     "\"coverageAmount\":1000,"
            +     "\"policyTerm\":1"
            + "}]"
            + "}";

        RestAssured.given()
            .header("Authorization", bearerToken())
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/premium/calculate")
        .then()
            .statusCode(200)
            .body("correlationId", Matchers.equalTo("corr-min-age"))
            .body("results.size()", Matchers.equalTo(1));
    }

    // Edge Case: Age at maximum boundary
    @Test
    public void testCalculatePremium_AgeMaximumBoundary() {
        String body = "{"
            + "\"correlationId\":\"corr-max-age\","
            + "\"policies\":[{"
            +     "\"policyholder\":{"
            +         "\"age\":120,"
            +         "\"gender\":\"M\""
            +     "},"
            +     "\"policyType\":\"Accident\","
            +     "\"coverageAmount\":1000,"
            +     "\"policyTerm\":1"
            + "}]"
            + "}";

        RestAssured.given()
            .header("Authorization", bearerToken())
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/premium/calculate")
        .then()
            .statusCode(200)
            .body("correlationId", Matchers.equalTo("corr-max-age"))
            .body("results.size()", Matchers.equalTo(1));
    }

    // Negative Test: Missing required field (policyTerm)
    @Test
    public void testCalculatePremium_MissingRequiredField() {
        String body = "{"
            + "\"correlationId\":\"corr-missing-policyTerm\","
            + "\"policies\":[{"
            +     "\"policyholder\":{"
            +         "\"age\":40,"
            +         "\"gender\":\"F\""
            +     "},"
            +     "\"policyType\":\"Life\","
            +     "\"coverageAmount\":20000"
            // missing "policyTerm"
            + "}]"
            + "}";

        ValidatableResponse resp = RestAssured.given()
            .header("Authorization", bearerToken())
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/premium/calculate")
        .then()
            .statusCode(400)
            .body("correlationId", Matchers.equalTo("corr-missing-policyTerm"))
            .body("errorCode", Matchers.notNullValue())
            .body("errorMessage", Matchers.notNullValue());
    }

    // Negative Test: Age less than minimum (negative)
    @Test
    public void testCalculatePremium_AgeNegative() {
        String body = "{"
            + "\"correlationId\":\"corr-age-negative\","
            + "\"policies\":[{"
            +     "\"policyholder\":{"
            +         "\"age\":-1,"
            +         "\"gender\":\"M\""
            +     "},"
            +     "\"policyType\":\"Accident\","
            +     "\"coverageAmount\":1000,"
            +     "\"policyTerm\":1"
            + "}]"
            + "}";

        RestAssured.given()
            .header("Authorization", bearerToken())
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/premium/calculate")
        .then()
            .statusCode(400)
            .body("correlationId", Matchers.equalTo("corr-age-negative"))
            .body("errorCode", Matchers.notNullValue())
            .body("errorMessage", Matchers.containsString("age"));
    }

    // Negative Test: Age greater than maximum
    @Test
    public void testCalculatePremium_AgeOverMaximum() {
        String body = "{"
            + "\"correlationId\":\"corr-age-overmax\","
            + "\"policies\":[{"
            +     "\"policyholder\":{"
            +         "\"age\":121,"
            +         "\"gender\":\"F\""
            +     "},"
            +     "\"policyType\":\"Life\","
            +     "\"coverageAmount\":1000,"
            +     "\"policyTerm\":5"
            + "}]"
            + "}";

        RestAssured.given()
            .header("Authorization", bearerToken())
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/premium/calculate")
        .then()
            .statusCode(400)
            .body("correlationId", Matchers.equalTo("corr-age-overmax"))
            .body("er