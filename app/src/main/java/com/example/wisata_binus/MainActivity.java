package com.example.wisata_binus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wisata_binus.model.Database;
import com.example.wisata_binus.model.Favorite;
import com.example.wisata_binus.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button login, redirect;

    Database database = Database.getInstance();
    private ArrayList<User> userList = database.getUsers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Database: " + database.getUsers());
        System.out.println("Log In Status" + database.getLogInStatus());
        init();
        //testing
        System.out.println("Old Favorite List: " + userList.get(0).getFavoriteList());
        database.addFavorites("CBN001",0);
        database.addFavorites("CBN003",0);
        System.out.println("Favorite List: " + userList.get(0).getFavoriteList());
        ArrayList<Favorite> favoriteTempList = userList.get(0).getFavoriteList();
        for (int i=0; i<favoriteTempList.size(); i++){
            System.out.println("Favorite " + i + favoriteTempList.get(i).getCampusId());
        }
    }

    private int checkEmailExist(String email){
        int listSize = userList.size();
        for(int i=0; i<listSize; i++){
            if(userList.get(i).getUserEmailAddress().equals(email) ){
                System.out.println("Email sama");
                return i;
            }
        }
        return -1;
    }

    private boolean checkPassword(Integer index, String password){
        if(userList.get(index).getUserPassword().equals(password)){
            return true;
        }
        return false;
    }

    private void init(){
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.loginBtn);
        redirect = findViewById(R.id.loginRedirect);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validations for email and password
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();

                if(emailStr.isEmpty() || passwordStr.isEmpty()){
                    Toast.makeText(MainActivity.this, getResources().getText(R.string.error_emptyForm), Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Integer index = checkEmailExist(emailStr);
                    System.out.println("index: " + index);

                    if(index != -1){
                        if(checkPassword(index, passwordStr)){
                            System.out.println("index" + index);
                            database.setLogInStatus(index);
                            //Intent Go To Campus Page
                            Toast.makeText(MainActivity.this, getResources().getText(R.string.success_login), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, CampusActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, getResources().getText(R.string.error_wrongPassword), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, getResources().getText(R.string.error_wrongEmail), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go To Register Page
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}