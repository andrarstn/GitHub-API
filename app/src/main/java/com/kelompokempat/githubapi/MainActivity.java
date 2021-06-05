package com.kelompokempat.githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kelompokempat.githubapi.auth.view.LoginActivity;
import com.kelompokempat.githubapi.auth.view.RegisterActivity;
import com.kelompokempat.githubapi.auth.view.WelcomeActivity;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences getPreferences = getSharedPreferences("SAVED_LOGIN", MODE_PRIVATE);
        Boolean check = getPreferences.getBoolean("LOGGED",false);
        String username = getPreferences.getString("USERNAME", "DEFAULT");

        if (!check){
            Intent intent = new Intent(MainActivity.this , WelcomeActivity.class);
            MainActivity.this.startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv_Hello);
        tv.setText(username);

        btnSignOut = findViewById(R.id.btn_signoutmain);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getPreferences.edit();
                editor.putBoolean("LOGGED",false);
                editor.putString("USERNAME","DEFAULT");
                editor.apply();

                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}