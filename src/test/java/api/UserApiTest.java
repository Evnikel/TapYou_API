package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.UserData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.example.UserData.USERS_ENDPOINT;
import static org.example.UserData.USER_ENDPOINT;
import static org.hamcrest.Matchers.*;

public class UserApiTest {
    @Test(dataProvider = "genderData", dataProviderClass = UserData.class)
    public void testUserListByGender(String gender) {
        RestAssured.baseURI = UserData.BASE_URL;
         {
            System.out.println("\u001B[34mResponses for gender " + gender + ":\u001B[0m");
             given()
                    .param("gender", gender)
                    .when()
                    .contentType(ContentType.JSON)
                    .get(USERS_ENDPOINT)
                    .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200)
                    .body("isSuccess", is(true));


        }
    }
    @Test(dataProvider = "idData", dataProviderClass = UserData.class)
    public void testUserInfoById(String gender, int[] ids) {
        RestAssured.baseURI = UserData.BASE_URL;
        for (int id : ids) {
            try {
                System.out.println("\u001B[34mResponses for gender " + gender + " and id " + id + "\u001B[0m");
                System.out.println("\u001B[34m***********************************************\u001B[0m");
                given()
                        .pathParam("id", id)
                        .when()
                        .contentType(ContentType.JSON)
                        .get(USER_ENDPOINT)
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
            } catch (AssertionError e) {
                System.out.println("\\u001B[31mTest failed for gender " + gender + " and id " + id);
                System.err.println("Error message: " + e.getMessage() + "\u001B[0m");
            }
        }
    }

}