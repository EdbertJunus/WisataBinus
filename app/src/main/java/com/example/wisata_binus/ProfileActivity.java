package com.example.wisata_binus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wisata_binus.model.Database;
import com.example.wisata_binus.model.User;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private TextView email, phone;
    private EditText oldPassword, newPassword;
    private Button changePassword, logOut;

    Database database = Database.getInstance();
    private ArrayList<User> userList = database.getUsers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
    }

    private boolean checkAlphaNumeric(String password){
        int length = password.length();
        int numOfDigit = 0;
        int numOfAlpha = 0;
        for(int i=0; i<length; i++){
            char character = password.charAt(i);
            if(Character.isLetter(character)){
                numOfAlpha++;
            }else if(Character.isDigit(character)){
                numOfDigit++;
            }
        }
        if(numOfAlpha > 0 && numOfDigit > 0){
            return true;
        }
        return false;
    }

    private boolean checkPassword(String password){
        int length = password.length();

        if(length < 8){
            String text = "New " + getResources().getText(R.string.error_passwordLength);
            Toast.makeText(ProfileActivity.this, text, Toast.LENGTH_SHORT).show();
            return false;
        }else if(!checkAlphaNumeric(password)){
            String text = "New " + getResources().getText(R.string.error_password_alphaNumeric);
            Toast.makeText(ProfileActivity.this, text, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkPasswordWithDatabase(Integer index, String password){
        if(userList.get(index).getUserPassword().equals(password)){
            return true;
        }
        Toast.makeText(ProfileActivity.this, getResources().getText(R.string.error_wrongOldPassword), Toast.LENGTH_SHORT).show();
        return false;
    }

    private void init(){
        email = findViewById(R.id.userEmail);
        phone = findViewById(R.id.userPhone);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        changePassword = findViewById(R.id.changePasswordBtn);
        logOut = findViewById(R.id.logOutBtn);

        int userIndex = database.getLogInStatus();

        String emailStr = database.getUsers().get(userIndex).getUserEmailAddress();
        String phoneStr = database.getUsers().get(userIndex).getUserPhoneNumber();

        //Fill Data
        email.setText(emailStr);
        phone.setText(phoneStr);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPasswordStr = oldPassword.getText().toString();
                String newPasswordStr = newPassword.getText().toString();

                boolean oldPassValidity = checkPasswordWithDatabase(userIndex, oldPasswordStr);
                boolean newPassValidity = checkPassword(newPasswordStr);

                if(oldPasswordStr.isEmpty() || newPasswordStr.isEmpty()){
                    Toast.makeText(ProfileActivity.this, getResources().getText(R.string.error_changePasswordForm), Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(oldPassValidity && newPassValidity){
                    Toast.makeText(ProfileActivity.this, getResources().getText(R.string.success_changePassword), Toast.LENGTH_SHORT).show();
                    database.getUsers().get(userIndex).setUserPassword(newPasswordStr);

                    //Clear Form
                    oldPassword.setText("");
                    newPassword.setText("");
                }
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Change Login Status
                database.setLogInStatus(-1);

                //Redirect to Login Page
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
