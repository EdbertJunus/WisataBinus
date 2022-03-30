package com.example.wisata_binus.model;

import java.util.ArrayList;

public class Database {
    private static Database instance = null;
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Campus> campuses = new ArrayList<Campus>();
    private int logInStatus = -1;

    public int getLogInStatus() {
        return logInStatus;
    }

    public void setLogInStatus(int logInStatus) {
        this.logInStatus = logInStatus;
    }

    public Database() {
        //Add dummyUser
        User dummyUser1 = new User("BNS001", "edbertGanteng88@gmail.com","081260258388","makanEnak88");
        User dummyUser2 = new User("BNS002", "edbertyoung88@gmail.com","087775568201","minumEnak88");
        users.add(dummyUser1);
        users.add(dummyUser2);

        Campus dataCampus1 = new Campus("CBN001","Binus Anggrek","Jakarta","Jalan Budi Raya","binus_anggrek");
        Campus dataCampus2 = new Campus("CBN002","Binus Syahdan","Jakarta","Jalan Syahdan","binus_syahdan");
        Campus dataCampus3 = new Campus("CBN003","Binus Kijang","Jakarta","Jalan Kijang","binus_kijang");
        campuses.add(dataCampus1);
        campuses.add(dataCampus2);
        campuses.add(dataCampus3);

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
    public ArrayList<Campus> getCampuses() {
        return campuses;
    }

    public void addFavorites(String campusId, Integer userIndex){
        ArrayList<Favorite> favoriteList = users.get(userIndex).getFavoriteList();
        favoriteList.add(new Favorite(campusId));
        users.get(userIndex).setFavoriteList(favoriteList);
    }

    public Integer findCampusInFavorite(ArrayList<Favorite> favorites, String campusId){
        int length = favorites.size();
        for (int i = 0; i < length; i++) {
            if(favorites.get(i).getCampusId().equals(campusId)){
                return i;
            }
        }
        return -1;
    }

    public Integer findCampusInCampusList(ArrayList<Campus> campuses, String campusId){
        int length = campuses.size();
        for (int i = 0; i < length; i++) {
            if(campuses.get(i).getCampusID().equals(campusId)){
                return i;
            }
        }
        return -1;
    }

    public void removeFavorites(String campusId, Integer userIndex){
        ArrayList<Favorite> favoriteList= users.get(userIndex).getFavoriteList();
        int index = findCampusInFavorite(favoriteList, campusId);
        favoriteList.remove(index);
        users.get(userIndex).setFavoriteList(favoriteList);
    }

}
