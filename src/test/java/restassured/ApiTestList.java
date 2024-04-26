package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ApiTestList {

    @Test
     public void testUserListApi() {
        String gender = "male";
        Response response = RestAssured.get("https://hr-challenge.dev.tapyou.com/api/test/users?gender=" + gender);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

}
