package com.example.wisata_binus.model;

import com.example.wisata_binus.model.Favorite;

import java.util.ArrayList;

public class User {
    private String UserId;
    private String UserEmailAddress;

    private String UserPhoneNumber;
    private String UserPassword;
    private ArrayList<Favorite> favoriteList = new ArrayList<Favorite>();

    public User(String userID, String userEmailAddress, String userPhoneNumber, String userPassword) {
        this.UserId = userID;
        this.UserEmailAddress = userEmailAddress;
        this.UserPhoneNumber = userPhoneNumber;
        this.UserPassword = userPassword;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public String getUserEmailAddress() {
        return UserEmailAddress;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public ArrayList<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(ArrayList<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }
}
