package com.example.wisata_binus;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wisata_binus.adapter.CampusListAdapter;
import com.example.wisata_binus.model.Campus;
import com.example.wisata_binus.model.Database;
import com.example.wisata_binus.model.Favorite;
import com.example.wisata_binus.model.User;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView rvFavoriteList;
    Database database = Database.getInstance();
    private ArrayList<User> userList = database.getUsers();
    private ArrayList<Campus> campusList = database.getCampuses();
    private int userIndex = database.getLogInStatus();
    private ArrayList<Favorite> favoriteList = userList.get(userIndex).getFavoriteList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        init();

    }
    private void init(){
        rvFavoriteList = findViewById(R.id.rvFavoriteList);
        rvFavoriteList.setAdapter(new CampusListAdapter(this, campusList, favoriteList, "favorite_list"));
        rvFavoriteList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
