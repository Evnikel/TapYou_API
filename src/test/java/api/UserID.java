package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.UserData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.example.UserData.USER_ENDPOINT;

public class UserID {
    private static final Logger logger = LogManager.getLogger(UserID.class);

    @Test(dataProvider = "idDataMultiple", dataProviderClass = UserData.class)
    public void testUserInfoByIdWithMultipleIds(int id) {
        logger.info("\u001B[34mResponses for id " + id + "\u001B[0m");
        logger.info("\u001B[34m***********************************************\u001B[0m");
        Response response = given()
                .pathParam("id", id)
                .when()
                .contentType(ContentType.JSON)
                .get(USER_ENDPOINT)
                .then()
                .log().all()
                .extract().response();

        if (response.statusCode() == 200) {
            if (response.jsonPath().getBoolean("isSuccess")) {
                if (response.jsonPath().get("user.id") != null) {
                    int userId = response.jsonPath().getInt("user.id");

                    if (userId == id) {
                        logger.info("Response body:");
                        logger.info(response.body().asString());
                    } else {
                        logger.info("\u001B[31mError: Requested id does not match the response id. Expected id: " + id + ", Actual id: " + userId + "\u001B[0m");
                    }
                } else {
                    logger.info("\u001B[31mError: Response body does not contain id\u001B[0m");
                }
            } else {
                logger.info("\u001B[31mError status: " + response.body().asString() + "\u001B[0m");
            }
        } else {
            logger.info("\u001B[31mError status: " + response.statusCode() + "\u001B[0m");
        }
    }
}
