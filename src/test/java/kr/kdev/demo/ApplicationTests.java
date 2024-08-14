package kr.kdev.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@DisplayName("Application Tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @DisplayName("Health Check")
    @Test
    void givenProxyHeader_whenHealthCheck_thenOk() {
        RestAssured.given()
                .header(new Header("X-Forwarded-For", "173.194.0.0"))

                .when()
                .log().all()
                .get("/health")

                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body(Matchers.is("200"));
    }

}
