package com.kelompokempat.githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.kelompokempat.githubapi.auth.view.WelcomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences getPreferences = getSharedPreferences("SAVED_LOGIN", MODE_PRIVATE);
        Boolean check = getPreferences.getBoolean("LOGGED",false);

        if (!check){
            Intent intent = new Intent(MainActivity.this , WelcomeActivity.class);
            MainActivity.this.startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}