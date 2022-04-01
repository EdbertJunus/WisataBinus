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
        User dummyUser1 = new User("BNS001", "edbert@gmail.com","081262153587","makanEnak88");
        User dummyUser2 = new User("BNS002", "edbertjunus@gmail.com","08126215385","minumEnak88");
        users.add(dummyUser1);
        users.add(dummyUser2);

        //Add dataCampus
        Campus dataCampus1 = new Campus("CBN001","Binus Anggrek","Jakarta","Jalan Budi Raya","binus_anggrek");
        Campus dataCampus2 = new Campus("CBN002","Binus Syahdan","Jakarta","Jalan Syahdan","binus_syahdan");
        Campus dataCampus3 = new Campus("CBN003","Binus Kijang","Jakarta","Jalan Kijang","binus_kijang");
        Campus dataCampus4 = new Campus("CBN004", "Binus Alam Sutera", "Tangerang", "Jalan Jalur Sutera Barat Panunggangan Timur", "binus_alam_sutera");
        Campus dataCampus5 = new Campus("CBN005", "Binus Bekasi", "Bekasi", "Jalan Lingkar Boulevar", "binus_bekasi");
        Campus dataCampus6 = new Campus("CBN006", "Binus JWC", "Jakarta Pusat", "Jalan Hang Lekir I", "binus_jwc");
        Campus dataCampus7 = new Campus("CBN007", "Binus Bandung", "Bandung", "Paskal Hyper Square, Jalan Pasir Kaliki", "binus_bandung");
        Campus dataCampus8 = new Campus("CBN008", "Binus Malang", "Malang", "Araya Mansion No. 8 - 22, Genitri", "binus_malang");

        campuses.add(dataCampus1);
        campuses.add(dataCampus2);
        campuses.add(dataCampus3);
        campuses.add(dataCampus4);
        campuses.add(dataCampus5);
        campuses.add(dataCampus6);
        campuses.add(dataCampus7);
        campuses.add(dataCampus8);

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
