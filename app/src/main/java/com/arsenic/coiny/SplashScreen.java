package com.arsenic.coiny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.arsenic.coiny.Activities.Authentication.Login;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        int isLogged = sp.getInt("user_logged", 0);

        if(isLogged == 1){
            Intent intent = new Intent(getApplicationContext(),
                    MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    Login.class);
            startActivity(intent);
        }

        finish();
    }
}
