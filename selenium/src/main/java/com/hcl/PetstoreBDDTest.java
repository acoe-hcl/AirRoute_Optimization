import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetstoreBDDTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://petstore.swagger.io/v3";
    }

    // Positive Test: Create a pet with valid input
    @Test
    public void createPet_Positive() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\":123,\"name\":\"Fluffy\",\"tag\":\"cat\"}")
        .when()
            .post("/pets")
        .then()
            .statusCode(201);
    }

    // Negative Test: Create a pet with missing required field "name"
    @Test
    public void createPet_MissingName_Negative() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\":124}")
        .when()
            .post("/pets")
        .then()
            .statusCode(not(201))
            .body("code", notNullValue())
            .body("message", notNullValue());
    }

    // Negative Test: Create a pet with missing required field "id"
    @Test
    public void createPet_MissingId_Negative() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\"Doggy\"}")
        .when()
            .post("/pets")
        .then()
            .statusCode(not(201))
            .body("code", notNullValue())
            .body("message", notNullValue());
    }

    // Edge Case: Create a pet with extremely large id
    @Test
    public void createPet_LargeId_EdgeCase() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\":9223372036854775807,\"name\":\"BigIdPet\"}")
        .when()
            .post("/pets")
        .then()
            .statusCode(201);
    }

    // Edge Case: Create a pet with empty tag
    @Test
    public void createPet_EmptyTag_EdgeCase() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\":125,\"name\":\"NoTagPet\",\"tag\":\"\"}")
        .when()
            .post("/pets")
        .then()
            .statusCode(201);
    }

    // Positive Test: Retrieve pet by valid id
    @Test
    public void getPetById_Positive() {
        // First create the pet so that it exists
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\":126,\"name\":\"Retriever\",\"tag\":\"dog\"}")
        .when()
            .post("/pets")
        .then()
            .statusCode(201);

        // Then retrieve it
        given()
        .when()
            .get("/pets/{petId}", 126)
        .then()
            .statusCode(200)
            .body("id", equalTo(126))
            .body("name", equalTo("Retriever"))
            .body("tag", equalTo("dog"));
    }

    // Negative Test: Retrieve a pet with non-existent id
    @Test
    public void getPetById_NonExistent_Negative() {
        given()
        .when()
            .get("/pets/{petId}", "999999")
        .then()
            .statusCode(not(200))
            .body("code", notNullValue())
            .body("message", containsString("not found"));
    }

    // Edge Case: Retrieve a pet with invalid id format (string instead of integer)
    @Test
    public void getPetById_InvalidFormat_EdgeCase() {
        given()
        .when()
            .get("/pets/{petId}", "invalid_id")
        .then()
            .statusCode(not(200))
            .body("code", notNullValue())
            .body("message", containsString("invalid"));
    }

    // Edge Case: Retrieve a pet with minimum int64 value
    @Test
    public void getPetById_MinInt64_EdgeCase() {
        given()
        .when()
            .get("/pets/{petId}", "-9223372036854775808")
        .then()
            .statusCode(not(200))
            .body("code", notNullValue());
    }

}
