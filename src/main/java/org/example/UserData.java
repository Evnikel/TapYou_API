package org.example;

import org.testng.annotations.DataProvider;

public class UserData {
    public static final String BASE_URL = "https://hr-challenge.dev.tapyou.com/api/test";
    public static final String USERS_ENDPOINT = BASE_URL + "/users";
    public static final String USER_ENDPOINT = BASE_URL + "/user/{id}";

    @DataProvider(name = "genderData")
    public static Object[][] genderData() {
        return new Object[][]{
                {"male"},
                {"female"},
                {"any"}
        };
    }

    @DataProvider(name = "idData")
    public static Object[][] idData() {
        return new Object[][]{
                {"male", new int[]{10, 15, 33, 94, 501}},
                {"female", new int[]{5, 15, 16, 300, 502, 503}},
                {"any", new int[]{0, 5, 10, 15, 16, 33, 94, 212, 300, 501, 502, 503, 911}}
        };
    }

        @DataProvider(name = "idDataMultiple")
        public static Object[][] idDataMultiple() {
            return new Object[][]{
                    {0}, {5}, {10}, {15}, {16}, {33}, {94}, {212}, {300}, {501}, {502}, {503}, {911}
            };
        }

}
