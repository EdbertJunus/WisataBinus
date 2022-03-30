package com.example.wisata_binus;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    private TextView emptyFavoriteMessage;
    Database database = Database.getInstance();
    private ArrayList<User> userList = database.getUsers();
    private ArrayList<Campus> campusList = database.getCampuses();
    private int userIndex = database.getLogInStatus();
    private ArrayList<Favorite> favoriteList = userList.get(userIndex).getFavoriteList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init(){
        rvFavoriteList = findViewById(R.id.rvFavoriteList);
        emptyFavoriteMessage = findViewById(R.id.emptyFavoriteMessage);

        if(favoriteList.size() == 0){
            emptyFavoriteMessage.setVisibility(View.VISIBLE);
            rvFavoriteList.setVisibility(View.GONE);
        }else{
            emptyFavoriteMessage.setVisibility(View.GONE);
            rvFavoriteList.setVisibility(View.VISIBLE);
            rvFavoriteList.setAdapter(new CampusListAdapter(this, campusList, favoriteList, "favorite_list"));
            rvFavoriteList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }
    }
}
