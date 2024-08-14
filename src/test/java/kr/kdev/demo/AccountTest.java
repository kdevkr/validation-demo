package kr.kdev.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import kr.kdev.demo.account.PasswordUpdateRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

@DisplayName("Account Test")
class AccountTest extends ApplicationTests {

    @DisplayName("Update Password")
    @Test
    void givenNewPassword_whenUpdatePassword_thenOk() {
        String password = RandomStringUtils.random(6, true, true)
                .toLowerCase(Locale.ROOT)
                .replace("l", "1");
        String newPassword = RandomStringUtils.random(6, true, true)
                .toLowerCase(Locale.ROOT)
                .replace("l", "1");

        PasswordUpdateRequest request = PasswordUpdateRequest.builder()
                .password(password)
                .newPassword(newPassword)
                .confirmPassword(newPassword)
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)

                .when()
                .log().all()
                .put("/api/password")

                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body(Matchers.is("true"));
    }

    @DisplayName("Update Password Failed")
    @Test
    void givenNotEqualConfirmPassword_whenUpdatePassword_thenFailed() {
        PasswordUpdateRequest request = PasswordUpdateRequest.builder()
                .password(RandomStringUtils.random(6, true, true))
                .newPassword(RandomStringUtils.random(6, true, true))
                .confirmPassword(RandomStringUtils.random(6, true, true))
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)

                .when()
                .log().all()
                .put("/api/password")

                .then()
                .log().ifValidationFails()
                .statusCode(400)
                .body("exception", Matchers.is("org.springframework.web.bind.MethodArgumentNotValidException"));

        request.setPassword("");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)

                .when()
                .log().all()
                .put("/api/password")

                .then()
                .log().ifValidationFails()
                .statusCode(400)
                .body("exception", Matchers.is("org.springframework.web.bind.MethodArgumentNotValidException"));
    }
}
