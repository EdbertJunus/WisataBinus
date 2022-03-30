package com.example.wisata_binus.model;

import java.util.ArrayList;

public class Favorite {
    private String campusId;

    public Favorite(String campusId) {
        this.campusId = campusId;
    }

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }
}
