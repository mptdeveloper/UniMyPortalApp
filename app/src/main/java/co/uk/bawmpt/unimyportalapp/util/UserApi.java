package co.uk.bawmpt.unimyportalapp.util;

import android.app.Application;

public class UserApi extends Application {
    private String name;
    private String userId;
    private static UserApi instance;

    public static UserApi getInstance() {
        if (instance == null)
            instance = new UserApi();
        return instance;
    }

    public UserApi() {
    }

    public String getName(String name) {
        return this.name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getUserId(String userId) {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
