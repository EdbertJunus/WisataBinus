package com.example.wisata_binus.model;

public class Campus{
    private String CampusID;
    private String CampusName;
    private String CampusLocation;
    private String CampusAddress;
    private String CampusImage;

    public Campus(String campusID, String campusName, String campusLocation, String campusAddress, String campusImage) {
        this.CampusID = campusID;
        this.CampusName = campusName;
        this.CampusLocation = campusLocation;
        this.CampusAddress = campusAddress;
        this.CampusImage = campusImage;
    }

    public String getCampusID() {
        return CampusID;
    }

    public void setCampusID(String campusID) {
        CampusID = campusID;
    }

    public String getCampusName() {
        return CampusName;
    }

    public void setCampusName(String campusName) {
        CampusName = campusName;
    }

    public String getCampusLocation() {
        return CampusLocation;
    }

    public void setCampusLocation(String campusLocation) {
        CampusLocation = campusLocation;
    }

    public String getCampusAddress() {
        return CampusAddress;
    }

    public void setCampusAddress(String campusAddress) {
        CampusAddress = campusAddress;
    }

    public String getCampusImage() {
        return CampusImage;
    }

    public void setCampusImage(String campusImage) {
        CampusImage = campusImage;
    }
}
