package com.example.wisata_binus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wisata_binus.model.Campus;
import com.example.wisata_binus.model.Database;
import com.example.wisata_binus.model.Favorite;
import com.example.wisata_binus.model.User;

import java.util.ArrayList;

public class CampusDetailActivity extends AppCompatActivity {

    private TextView campusName, campusLocation, campusAddress;
    private ImageView campusImage;
    private Button favoriteButton;

    Database database = Database.getInstance();
    private ArrayList<User> userList = database.getUsers();
    private ArrayList<Campus> campusList = database.getCampuses();

    private int userIndex = database.getLogInStatus();
    private ArrayList<Favorite> favoriteList = userList.get(userIndex).getFavoriteList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_detail);

        init();
    }

    private void handleFavoriteButtonTextOnLoad(String campusId){
        String buttonText = favoriteButton.getText().toString();
        int favoriteIndex = database.findCampusInFavorite(favoriteList, campusId);
        if(favoriteIndex >= 0){
            favoriteButton.setText("Remove from favorite");
        }else{
            favoriteButton.setText(R.string.campus_detail_favorite);
        }
    }

    private void init(){
        Integer campusIndex = getIntent().getIntExtra("position",0);

        campusName = findViewById(R.id.campusDetailName);
        campusLocation = findViewById(R.id.campusDetailLocation);
        campusAddress = findViewById(R.id.campusDetailAddress);
        campusImage = findViewById(R.id.campusDetailImage);
        favoriteButton = findViewById(R.id.addFavoriteBtn);

        String campusIdStr = campusList.get(campusIndex).getCampusID();
        String campusNameStr = campusList.get(campusIndex).getCampusName();
        String campusLocationStr = campusList.get(campusIndex).getCampusLocation();
        String campusAddressStr = campusList.get(campusIndex).getCampusAddress();
        String campusImageStr = campusList.get(campusIndex).getCampusImage();
        int imgId = this.getResources().getIdentifier(campusImageStr, "drawable", this.getPackageName());

        //Fill Data
        handleFavoriteButtonTextOnLoad(campusIdStr);
        campusName.setText(campusNameStr);
        campusLocation.setText(campusLocationStr);
        campusAddress.setText(campusAddressStr);
        campusImage.setImageResource(imgId);

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = favoriteButton.getText().toString();
                boolean favoriteStatus = buttonText.toLowerCase().contains("add") ? true : false;

                if(favoriteStatus){
                    database.addFavorites(campusIdStr, userIndex);

                    favoriteButton.setBackgroundColor(getResources().getColor(R.color.purple_200, getTheme()));
                    favoriteButton.setText(R.string.campus_detail_removeFavorite);
                }else{
                    database.removeFavorites(campusIdStr, userIndex);
                    favoriteButton.setBackgroundColor(getResources().getColor(R.color.purple_500, getTheme()));
                    favoriteButton.setText(R.string.campus_detail_favorite);
                }
            }
        });

    }
}
