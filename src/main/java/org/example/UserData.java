package org.example;

public class UserData {
    public static final String BASE_URL = "https://hr-challenge.dev.tapyou.com/api/test";
    public static final String[] GENDERS = {"male", "female", "any"};
    public static final int[] IDS = {10, 15, 33, 94, 501, 911, 5, 16, 300, 502, 503, 0, 212};

    private final String gender;
    private final int id;

    public UserData(String gender, int id) {
        this.gender = gender;
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String[] getGenders() {
        return GENDERS;
    }


    public static int[] getIds() {
        return IDS;
    }
}
