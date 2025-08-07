import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Main_2_8 {

    // 1. GET Request
    @Test
    void getRequestTest() {
        given()
            .baseUri("https://postman-echo.com")
            .queryParam("param1", "value1")
            .queryParam("param2", "value2")
        .when()
            .get("/get")
        .then()
            .statusCode(200)
            .body("args.param1", equalTo("value1"))
            .body("args.param2", equalTo("value2"))
            .body("headers.host", equalTo("postman-echo.com"))
            .body("url", containsString("/get?param1=value1&param2=value2"));
    }

    // 2. POST Raw Text
    @Test
    void postRawTextTest() {
        String textPayload = "Raw text payload";
        
        given()
            .baseUri("https://postman-echo.com")
            .contentType("text/plain; charset=UTF-8")
            .queryParam("queryParam", "testValue")
            .body(textPayload)
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .body("data", equalTo(textPayload))
            .body("args.queryParam", equalTo("testValue"))
            .body("headers.content-type", containsString("text/plain"));
    }

    // 3. POST raw data using variables
    @Test
    void postRawDataWithVariablesTest() {
        String jsonPayload = "{\"variable\": \"value\", \"number\": 42}";
        
        given()
            .baseUri("https://postman-echo.com")
            .contentType("application/json")
            .body(jsonPayload)
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .body("data", equalTo(jsonPayload))
            .body("json.variable", equalTo("value"))
            .body("json.number", equalTo(42));
    }

    // 4. POST Form Data
    @Test
    void postFormDataTest() {
        given()
            .baseUri("https://postman-echo.com")
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .formParam("username", "testUser")
            .formParam("email", "test@example.com")
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .body("form.username", equalTo("testUser"))
            .body("form.email", equalTo("test@example.com"));
    }

    // 5. PUT Request
    @Test
    void putRequestTest() {
        String textPayload = "PUT request payload";
        
        given()
            .baseUri("https://postman-echo.com")
            .contentType("text/plain")
            .queryParam("resourceId", "123")
            .body(textPayload)
        .when()
            .put("/put")
        .then()
            .statusCode(200)
            .body("data", equalTo(textPayload))
            .body("args.resourceId", equalTo("123"));
    }

    // 6. PATCH Request
    @Test
    void patchRequestTest() {
        String jsonPayload = "{\"status\": \"active\", \"enabled\": true}";
        
        given()
            .baseUri("https://postman-echo.com")
            .contentType("application/json")
            .body(jsonPayload)
        .when()
            .patch("/patch")
        .then()
            .statusCode(200)
            .body("data", equalTo(jsonPayload))
            .body("json.status", equalTo("active"))
            .body("json.enabled", equalTo(true));
    }

    // 7. DELETE Request
    @Test
    void deleteRequestTest() {
        given()
            .baseUri("https://postman-echo.com")
            .contentType("application/json")
            .queryParam("recordId", "789")
            .body("{\"confirmation\": \"true\"}")
        .when()
            .delete("/delete")
        .then()
            .statusCode(200)
            .body("args.recordId", equalTo("789"))
            .body("json.confirmation", equalTo("true"));
    }
}