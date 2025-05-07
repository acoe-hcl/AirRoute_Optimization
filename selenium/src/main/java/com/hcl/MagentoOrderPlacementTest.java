package api.automation.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MagentoOrderPlacementTest {

    // Base URL for the Magento website
    private static final String BASE_URL = "https://magento.softwaretestingboard.com";

    /**
     * Positive Test: Successful order placement.
     */
    @Test
    public void testPlaceOrderSuccessfully() {
        // Initialize the RestAssured Base URI
        RestAssured.baseURI = BASE_URL;

        // Step 1: Log in to the system
        RequestSpecification loginRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"username\": \"testermail@gmail.com\", \"password\": \"Tester@123\"}");

        Response loginResponse = loginRequest.post("/rest/V1/integration/customer/token");
        Assertions.assertEquals(200, loginResponse.getStatusCode(), "Login failed!");
        String token = loginResponse.body().asString();

        // Step 2: Navigate to the product page and retrieve product info
        RequestSpecification productRequest = RestAssured.given()
                .header("Authorization", "Bearer " + token);
        Response productResponse = productRequest.get("/rest/V1/products/24-MB01");
        Assertions.assertEquals(200, productResponse.getStatusCode(), "Failed to retrieve product details");
        String productSku = productResponse.jsonPath().getString("sku");
        Assertions.assertEquals("24-MB01", productSku, "Incorrect product SKU returned");

        // Step 3: Add the product to the cart
        RequestSpecification addToCartRequest = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{\"cartItem\": {\"sku\": \"" + productSku + "\", \"qty\": 1, \"quoteId\": \"\" }}");

        Response addToCartResponse = addToCartRequest.post("/rest/V1/carts/mine/items");
        Assertions.assertEquals(200, addToCartResponse.getStatusCode(), "Failed to add the product to the cart");
        String cartItemId = addToCartResponse.jsonPath().getString("item_id");
        Assertions.assertNotNull(cartItemId, "Cart item ID should not be null");

        // Step 4: Place the order
        RequestSpecification orderRequest = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{ \"paymentMethod\": { \"method\": \"checkmo\" }}");

        Response orderResponse = orderRequest.put("/rest/V1/carts/mine/payment-information");
        Assertions.assertEquals(200, orderResponse.getStatusCode(), "Failed to place an order");
        String orderId = orderResponse.jsonPath().getString("order_id");
        Assertions.assertNotNull(orderId, "Order ID should not be null");

        // Step 5: Validate confirmation message
        System.out.println("Order placed successfully! Order ID: " + orderId);
    }

    /**
     * Negative Test: Attempt to place an order with invalid login credentials.
     */
    @Test
    public void testPlaceOrderWithInvalidLogin() {
        // Initialize the RestAssured Base URI
        RestAssured.baseURI = BASE_URL;

        // Attempt to log in with invalid credentials
        RequestSpecification loginRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"username\": \"invaliduser@gmail.com\", \"password\": \"Invalid@123\"}");

        Response loginResponse = loginRequest.post("/rest/V1/integration/customer/token");
        Assertions.assertEquals(401, loginResponse.getStatusCode(), "Login did not fail as expected!");

        String errorMessage = loginResponse.jsonPath().getString("message");
        Assertions.assertEquals("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.", errorMessage, "Unexpected error message");
    }

    /**
     * Negative Test: Attempt to add a non-existent product to the cart.
     */
    @Test
    public void testAddNonExistentProductToCart() {
        // Step 1: Log in to the system
        RestAssured.baseURI = BASE_URL;
        RequestSpecification loginRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"username\": \"testermail@gmail.com\", \"password\": \"Tester@123\"}");

        Response loginResponse = loginRequest.post("/rest/V1/integration/customer/token");
        Assertions.assertEquals(200, loginResponse.getStatusCode(), "Login failed!");
        String token = loginResponse.body().asString();

        // Step 2: Attempt to add a non-existent product to the cart
        RequestSpecification addToCartRequest = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{\"cartItem\": {\"sku\": \"NON_EXISTENT_SKU\", \"qty\": 1, \"quoteId\": \"\" }}");

        Response addToCartResponse = addToCartRequest.post("/rest/V1/carts/mine/items");
        Assertions.assertEquals(400, addToCartResponse.getStatusCode(), "Adding non-existent product did not fail as expected");

        String errorMessage = addToCartResponse.jsonPath().getString("message");
        Assertions.assertThat(errorMessage, Matchers.containsString("The requested product doesn't exist."));
    }

    /**
     * Negative Test: Attempt to place an order without adding a product to the cart.
     */
    @Test
    public void testPlaceOrderWithoutProducts() {
        // Step 1: Log in to the system
        RestAssured.baseURI = BASE_URL;
        RequestSpecification loginRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"username\": \"testermail@gmail.com\", \"password\": \"Tester@123\"}");

        Response loginResponse = loginRequest.post("/rest/V1/integration/customer/token");
        Assertions.assertEquals(200, loginResponse.getStatusCode(), "Login failed!");
        String token = loginResponse.body().asString();

        // Step 2: Attempt to place an order without adding products to the cart
        RequestSpecification orderRequest = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{ \"paymentMethod\": { \"method\": \"checkmo\" }}");

        Response orderResponse = orderRequest.put("/rest/V1/carts/mine/payment-information");
        Assertions.assertEquals(400, orderResponse.getStatusCode(), "Placing an order without products did not fail as expected");

        String errorMessage = orderResponse.jsonPath().getString("message");
        Assertions.assertThat(errorMessage, Matchers.containsString("You can't place an order with an empty cart."));
    }
}
