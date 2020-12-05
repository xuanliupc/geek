package com.geek.learning.eleventhweek;

public class User {
    private String usetId;
    private String password;

    public User(String userId, String password) {
        this.usetId = userId;
        this.password = password;
    }

    public String getUsetId() {
        return usetId;
    }

    public void setUsetId(String usetId) {
        this.usetId = usetId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
