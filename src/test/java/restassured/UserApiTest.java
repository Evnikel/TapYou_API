package restassured;

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTest {
    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][]{
                {"male", 10},
                {"male", 15},
                {"male", 33},
                {"male", 94},
                {"male", 501},
                {"male", 911},
                {"female", 5},
                {"female", 15},
                {"female", 16},
                {"female", 300},
                {"female", 502},
                {"female", 503},
                {"magic", 300},
                {"McCloud", 911}
        };
    }

    @Test(dataProvider = "userData")
    public void testUserInfoApi(String gender, int id) {
        RestAssured.baseURI = "https://hr-challenge.dev.tapyou.com/api/test";

        given()
                .pathParam("id", id)
                .when()
                .get("/user/{id}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("isSuccess", is(true))
                .body("user.id", equalTo(id))
                .body("user.name", notNullValue())
                .body("user.gender", equalTo(gender))
                .body("user.age", greaterThan(0))
                .body("user.city", notNullValue())
                .body("user.registrationDate", notNullValue());
    }
}
