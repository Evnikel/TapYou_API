package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.UserData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.example.UserData.USER_ENDPOINT;

public class UserID {

    @Test(dataProvider = "idDataMultiple", dataProviderClass = UserData.class)
    public void testUserInfoByIdWithMultipleIds(int id) {
        System.out.println("\u001B[34mResponses for id " + id + "\u001B[0m");
        System.out.println("\u001B[34m***********************************************\u001B[0m");
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
                        System.out.println("Response body:");
                        System.out.println(response.body().asString());
                    } else {
                        System.out.println("\u001B[31mError: Requested id does not match the response id. Expected id: " + id + ", Actual id: " + userId + "\u001B[0m");
                    }
                } else {
                    System.out.println("\u001B[31mError: Response body does not contain id\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mError status: " + response.body().asString() + "\u001B[0m");
            }
        } else {
            System.out.println("\u001B[31mError status: " + response.statusCode() + "\u001B[0m");
        }
    }
}
