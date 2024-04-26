package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ApiTestList {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://hr-challenge.dev.tapyou.com";
    }

    @Test
     public void testUserListApi() {
        String gender = "male";
        Response response = RestAssured.get("https://hr-challenge.dev.tapyou.com/api/test/users?gender=" + gender);

        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.printf("Status code : %s\n" , response.getStatusCode());

        System.out.printf("Response : %s\n" , response.asString());

    }

    @Test
    public void getListOfGender() {

                given()
                        .queryParam("gender", "female")
                .when()
                .get("api/test/users")
                .then()
                        .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void testUserInfoApi() {
            RestAssured.baseURI = "https://hr-challenge.dev.tapyou.com/api/test";

            given()
                    .pathParam("id", 10)
                    .when()
                    .get("/user/{id}")
                    .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200)
                    .body("isSuccess", is(true))
                    .body("user.id", equalTo(10))
                    .body("user.name", equalTo("Peter"))
                    .body("user.gender", equalTo("male"))
                    .body("user.age", equalTo(26))
                    .body("user.city", equalTo("Omsk"))
                    .body("user.registrationDate", equalTo("2016-12-01T00:30:00"));
        }

}
