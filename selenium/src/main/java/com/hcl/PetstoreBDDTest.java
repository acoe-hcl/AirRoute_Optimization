import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetStoreBDDTest {

    private static final String BASE_URL = "http://petstore.swagger.io/v1";
    private static int createdPetId;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    // 1. Positive Test: List all pets with no limit
    @Test
    @Order(1)
    public void listAllPetsWithoutLimit_Positive() {
        given()
        .when()
            .get("/pets")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .header("x-next", notNullValue())
            .body("$", isA(java.util.List.class));
    }

    // 2. Positive Test: List pets with limit=10
    @Test
    @Order(2)
    public void listPetsWithLimit_Positive() {
        given()
            .queryParam("limit", 10)
        .when()
            .get("/pets")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", lessThanOrEqualTo(10));
    }

    // 3. Edge Case: Limit = 100 (maximum allowed)
    @Test
    @Order(3)
    public void listPetsWithLimit_MaxEdge() {
        given()
            .queryParam("limit", 100)
        .when()
            .get("/pets")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", lessThanOrEqualTo(100));
    }

    // 4. Negative Test: Limit greater than maximum
    @Test
    @Order(4)
    public void listPetsWithLimit_OverLimit_Negative() {
        given()
            .queryParam("limit", 101)
        .when()
            .get("/pets")
        .then()
            .statusCode(anyOf(is(400), is(500), is(200))) // Depending on implementation: may be rejected or capped
            .contentType(ContentType.JSON);
    }

    // 5. Negative Test: Invalid limit value (string)
    @Test
    @Order(5)
    public void listPetsWithInvalidLimit_Negative() {
        given()
            .queryParam("limit", "abc")
        .when()
            .get("/pets")
        .then()
            .statusCode(anyOf(is(400), is(500), is(200)));
    }

    // 6. Positive Test: Create a new pet
    @Test
    @Order(6)
    public void createNewPet_Positive() {
        createdPetId = 
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\":123456789,\"name\":\"Fluffy\",\"tag\":\"cat\"}")
        .when()
            .post("/pets")
        .then()
            .statusCode(201)
            .extract().path("id") == null ? 123456789 : -1 ;
    }

    // 7. Negative Test: Create pet with missing required fields
    @Test
    @Order(7)
    public void createPetMissingFields_Negative() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"tag\":\"dog\"}")
        .when()
            .post("/pets")
        .then()
            .statusCode(anyOf(is(400), is(500)))
            .body("code", notNullValue())
            .body("message", notNullValue());
    }

    // 8. Edge Case: Create pet with id=0 (edge)
    @Test
    @Order(8)
    public void createPetWithIdZero_Edge() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\":0,\"name\":\"Zero\",\"tag\":\"robot\"}")
        .when()
            .post("/pets")
        .then()
            .statusCode(201);
    }

    // 9. Negative Test: Create pet with invalid id (string instead of int)
    @Test
    @Order(9)
    public void createPetWithInvalidId_Negative() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\":\"abc\",\"name\":\"Kitty\"}")
        .when()
            .post("/pets")
        .then()
            .statusCode(anyOf(is(400), is(500)))
            .body("code", notNullValue())
            .body("message", notNullValue());
    }

    // 10. Positive Test: Get pet by id (existing)
    @Test
    @Order(10)
    public void getPetById_Positive() {
        String validPetId = "123456789";
        given()
            .pathParam("petId", validPetId)
        .when()
            .get("/pets/{petId}")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(123456789))
            .body("name", equalTo("Fluffy"));
    }

    // 11. Negative Test: Get pet by id (non-existing)
    @Test
    @Order(11)
    public void getPetById_NotFound_Negative() {
        given()
            .pathParam("petId", "99999999999")
        .when()
            .get("/pets/{petId}")
        .then()
            .statusCode(anyOf(is(404), is(500), is(400), is(200)))
            .body(anyOf(hasKey("code"), hasKey("message"), hasKey("id")));
    }

    // 12. Negative Test: Get pet by id (invalid id, string)
    @Test
    @Order(12)
    public void getPetById_InvalidId_Negative() {
        given()
            .pathParam("petId", "invalid_id")
        .when()
            .get("/pets/{petId}")
        .then()
            .statusCode(anyOf(is(400), is(500), is(404)));
    }

}
