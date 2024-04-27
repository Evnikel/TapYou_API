package restassured;

import io.restassured.RestAssured;
import org.example.UserData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTest {
    @Test
    public void testUserListByGender() {
        RestAssured.baseURI = UserData.getBaseUrl();

        for (String gender : UserData.getGenders()) {
            given()
                    .param("gender", gender)
                    .when()
                    .get("/users")
                    .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200)
                    .body("isSuccess", is(true));


        }
    }

    @Test
    public void testUserInfoById(UserData userData) {
        RestAssured.baseURI = UserData.getBaseUrl();

        given()
                .pathParam("id", userData.getId())
                .when()
                .get("/user/{id}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("isSuccess", is(true))
                .body("user.id", equalTo(userData.getId()))
                .body("user.name", notNullValue())
                .body("user.gender", equalTo(userData.getGender()))
                .body("user.age", greaterThan(0))
                .body("user.city", notNullValue())
                .body("user.registrationDate", notNullValue())
                .body("user.gender", anyOf(equalTo(userData.getGender()), equalTo("magic"), equalTo("McCloud")));
    }

}