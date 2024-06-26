package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.UserData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.example.UserData.USERS_ENDPOINT;
import static org.example.UserData.USER_ENDPOINT;
import static org.hamcrest.Matchers.*;

public class UserGenderList {

    private static final Logger logger = LogManager.getLogger(UserGenderList.class);
    @Test(dataProvider = "genderData", dataProviderClass = UserData.class)
    public void testUserListByGender(String gender) {
        RestAssured.baseURI = UserData.BASE_URL;
         {
             logger.info("\u001B[34mResponses for gender " + gender + ":\u001B[0m");
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
}