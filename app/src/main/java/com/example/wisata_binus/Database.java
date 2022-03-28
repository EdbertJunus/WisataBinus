package com.example.wisata_binus;

import java.util.ArrayList;

public class Database {
    private static Database instance = null;
    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<Campus> campuses = new ArrayList<Campus>();
    public ArrayList<Favorite> favorites = new ArrayList<Favorite>();
    public boolean logInStatus = false;

    public boolean isLogInStatus() {
        return logInStatus;
    }

    public void setLogInStatus(boolean logInStatus) {
        this.logInStatus = logInStatus;
    }

    public Database() {
        User dummyUser1 = new User("BNS001", "edbertGanteng88@gmail.com","081260258388","makanEnak88");
        User dummyUser2 = new User("BNS002", "edbertyoung88@gmail.com","087775568201","minumEnak88");

        users.add(dummyUser1);
        users.add(dummyUser2);
    }

    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public void addUsers(String email, String phone, String password){
        Database database = Database.getInstance();
        int size = database.getUsers().size();
        String newUserId = "BNS00" + (size+1);
        User newUser = new User(newUserId, email, phone, password);
        users.add(newUser);
    }

    public ArrayList<User> getUsers() {
        return users;
    }


}
