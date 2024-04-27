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
                {"any", 0},
                {"any", 5},
                {"any", 10},
                {"any", 15},
                {"any", 16},
                {"any", 33},
                {"any", 94},
                {"any", 212},
                {"any", 300},
                {"any", 501},
                {"any", 502},
                {"any", 503},
                {"any", 911},
                {"magic", -1},
                {"McCloud", -1}
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
                .body("user.registrationDate", notNullValue())
                .body("user.gender", anyOf(equalTo(gender), equalTo("magic"), equalTo("McCloud")));
    }
}
