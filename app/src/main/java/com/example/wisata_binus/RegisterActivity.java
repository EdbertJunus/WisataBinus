package com.example.wisata_binus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wisata_binus.model.Database;
import com.example.wisata_binus.model.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private EditText email, password, phone;
    private Button redirect, register;
    Database database = Database.getInstance();
    private MainActivity main = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private static Integer checkEmailContainsAt(String email){
        int length = email.length();
        int count = 0;
        for(int i = 0; i<length; i++){
            if(email.charAt(i) == '@'){
                count++;
            }
        }
        return count;
    }

    private boolean checkEmailStructure(String email){
        int length = email.length();
        for(int i=0; i<length; i++){
            if(email.charAt(i) == '@'){
                if(email.charAt(i+1) == '.'){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkEmailValid(String email){
        if(!email.endsWith(".com")){
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_email_com), Toast.LENGTH_SHORT).show();
            return false;
        }else if(email.startsWith("@")){
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_email_notStartAt), Toast.LENGTH_SHORT).show();
            return false;
        }else if(checkEmailContainsAt(email) > 1){
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_email_oneAt), Toast.LENGTH_SHORT).show();
            return false;
        }else if(checkEmailContainsAt(email) < 1){
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_email), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!checkEmailStructure(email)){
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            int index = main.checkEmailExist(email);

            if(index != -1){
                Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_email_unique), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private boolean checkNumericPhone(String phone){
        int length = phone.length();
        int numOfDigit = 0;
        int numOfAlpha = 0;
        for(int i=0; i<length; i++){
            char character = phone.charAt(i);
            if(Character.isLetter(character)){
                numOfAlpha++;
            }else if(Character.isDigit(character)){
                numOfDigit++;
            }
        }
        if(numOfAlpha > 0){
            return false;
        }else if(numOfDigit > 0){
            return true;
        }
        return false;
    }

    private boolean checkPhone(String phone){
        int length = phone.length();

        if(length < 10 || length > 12){
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_phone_numberDigits), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!phone.startsWith("08")){
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_phone), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!checkNumericPhone(phone)){
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_phone_mustBeNumber), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_passwordLength), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!checkAlphaNumeric(password)){
            Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_password_alphaNumeric), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void init(){
        email = findViewById(R.id.etRegisterEmail);
        password = findViewById(R.id.etRegisterPassword);
        phone = findViewById(R.id.etRegisterPhone);
        register = findViewById(R.id.registerBtn);
        redirect = findViewById(R.id.registerRedirect);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                String phoneStr = phone.getText().toString();

                if(emailStr.isEmpty() || passwordStr.isEmpty() || phoneStr.isEmpty()){
                    Toast.makeText(RegisterActivity.this, getResources().getText(R.string.error_emptyRegister), Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //Validation
                    boolean emailValidity = checkEmailValid(emailStr);
                    boolean phoneValidity = checkPhone(phoneStr);
                    boolean passwordValidity = checkPassword(passwordStr);

                    if(emailValidity && phoneValidity && passwordValidity){
                        database.addUsers(emailStr, phoneStr, passwordStr);
                        Toast.makeText(RegisterActivity.this, getResources().getText(R.string.success_register), Toast.LENGTH_SHORT).show();

                        //Go To Login Page
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
            }
        });

        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go To Login Page
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
