package com.example.wisata_binus;

import java.util.ArrayList;

public class User {
    private String UserId;
    private String UserEmailAddress;
    private String UserPhoneNumber;
    private String UserPassword;

    public User(String userID, String userEmailAddress, String userPhoneNumber, String userPassword) {
        this.UserId = userID;
        this.UserEmailAddress = userEmailAddress;
        this.UserPhoneNumber = userPhoneNumber;
        this.UserPassword = userPassword;
    }

    public String getUserID() {
        return UserId;
    }

    public String getUserEmailAddress() {
        return UserEmailAddress;
    }

    public String getUserPassword() {
        return UserPassword;
    }
}
