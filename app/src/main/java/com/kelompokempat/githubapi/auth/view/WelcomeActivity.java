package com.kelompokempat.githubapi.auth.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kelompokempat.githubapi.MainActivity;
import com.kelompokempat.githubapi.R;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnGetStarted = findViewById(R.id.btn_getstarted);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this , LoginActivity.class);
                WelcomeActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}