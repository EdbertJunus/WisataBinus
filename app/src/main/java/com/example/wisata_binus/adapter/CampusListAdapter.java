package com.example.wisata_binus.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wisata_binus.model.Campus;
import com.example.wisata_binus.CampusDetailActivity;
import com.example.wisata_binus.R;
import com.example.wisata_binus.model.Database;
import com.example.wisata_binus.model.Favorite;

import java.util.ArrayList;

public class CampusListAdapter extends RecyclerView.Adapter<CampusListAdapter.ViewHolder> {
    private Context ctx;
    private ArrayList<Campus> campusList;
    private ArrayList<Favorite> favoriteList;
    Database database = Database.getInstance();
    private String status;

    public CampusListAdapter(Context ctx, ArrayList<Campus> campusList, ArrayList<Favorite> favoriteList, String status) {
        this.ctx = ctx;
        this.campusList = campusList;
        this.favoriteList = favoriteList;
        this.status = status;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_campus, parent, false);
        return new ViewHolder(view);
    }

    private void handleCampusList(@NonNull ViewHolder holder, int position){
        Campus campus = campusList.get(position);

        //Fill Data
        holder.campusName.setText(campus.getCampusName());
        holder.campusLocation.setText(campus.getCampusLocation());

        String imgFileName = campus.getCampusImage();
        int imgId = ctx.getResources().getIdentifier(imgFileName, "drawable",ctx.getPackageName());
        holder.imageCampus.setImageResource(imgId);

        holder.cvCampus.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, CampusDetailActivity.class);
            intent.putExtra("position", position);
            ctx.startActivity(intent);
        });
        String favoriteStarName;
        int favoriteIndex = database.findCampusInFavorite(favoriteList, campus.getCampusID());
        if (favoriteIndex >= 0) {
            favoriteStarName = "star_full";
        }else{
            favoriteStarName = "star_empty";
        }

        int favStarId = ctx.getResources().getIdentifier(favoriteStarName, "drawable",ctx.getPackageName());
        holder.campusFavorite.setImageResource(favStarId);
    }

    private void handleFavoriteList(@NonNull ViewHolder holder, int position){
        Favorite favorite = favoriteList.get(position);
        String campusId = favorite.getCampusId();
        int favCampusIndex = database.findCampusInCampusList(campusList, campusId);
        Campus campus = campusList.get(favCampusIndex);

        //Fill Data
        holder.campusName.setText(campus.getCampusName());
        holder.campusLocation.setText(campus.getCampusLocation());

        String imgFileName = campus.getCampusImage();
        int imgId = ctx.getResources().getIdentifier(imgFileName, "drawable",ctx.getPackageName());
        holder.imageCampus.setImageResource(imgId);

        holder.cvCampus.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, CampusDetailActivity.class);
            intent.putExtra("position", favCampusIndex);
            ctx.startActivity(intent);
        });

        String favoriteStarName = "star_full";
        int favStarId = ctx.getResources().getIdentifier(favoriteStarName, "drawable",ctx.getPackageName());
        holder.campusFavorite.setImageResource(favStarId);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(status.equals("campus_list")){
            handleCampusList(holder, position);
        }else if(status.equals("favorite_list")){
            handleFavoriteList(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        if(status.equals("campus_list")){
            return campusList.size();
        }
        return favoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected CardView cvCampus;
        protected TextView campusName, campusLocation;
        protected ImageView imageCampus, campusFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvCampus = itemView.findViewById(R.id.cardCampus);
            campusName = itemView.findViewById(R.id.campusNameCard);
            campusLocation = itemView.findViewById(R.id.campusLocationCard);
            imageCampus = itemView.findViewById(R.id.imageCampus);
            campusFavorite = itemView.findViewById(R.id.campusFavorite);
        }
    }
}
