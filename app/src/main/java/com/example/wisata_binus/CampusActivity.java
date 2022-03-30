package com.example.wisata_binus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wisata_binus.adapter.CampusListAdapter;
import com.example.wisata_binus.model.Campus;
import com.example.wisata_binus.model.Database;
import com.example.wisata_binus.model.Favorite;
import com.example.wisata_binus.model.User;

import java.util.ArrayList;

public class CampusActivity extends AppCompatActivity {
    private RecyclerView rvCampusList;
    Database database = Database.getInstance();
    private ArrayList<User> userList = database.getUsers();
    private ArrayList<Campus> campusList = database.getCampuses();
    private int userIndex = database.getLogInStatus();
    private ArrayList<Favorite> favoriteList = userList.get(userIndex).getFavoriteList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);
        init();
//        ArrayList<Favorite> favoriteTempList = userList.get(0).getFavoriteList();
//        for (int i=0; i<favoriteTempList.size(); i++){
//            System.out.println("Favorite " + i + favoriteTempList.get(i).getCampusId());
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_campus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.profilePage){
            Intent intent = new Intent(CampusActivity.this, ProfileActivity.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId() == R.id.favoritePage){
            Intent intent = new Intent(CampusActivity.this, FavoriteActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    private void init(){
        rvCampusList = findViewById(R.id.rvCampusList);
        rvCampusList.setAdapter(new CampusListAdapter(this, campusList, favoriteList,"campus_list" ));
        rvCampusList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

}
