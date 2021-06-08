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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompokempat.githubapi.R;
import com.kelompokempat.githubapi.adapter.SearchAdapter;
import com.kelompokempat.githubapi.viewmodel.UserViewModel;


public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btnSignOut;
    SearchAdapter searchAdapter;
    UserViewModel searchViewModel;
    ProgressDialog progressDialog;
    RecyclerView rvListUser;
    EditText searchUser;
    ImageView imageClear, imageFavorite;
    ConstraintLayout layoutEmpty;

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

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        imageClear.setOnClickListener(view -> {
            searchUser.getText().clear();
            imageClear.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
            rvListUser.setVisibility(View.GONE);
        });

        imageFavorite.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });

        //method action search
        searchUser.setOnEditorActionListener((v, actionId, event) -> {
            String strUsername = searchUser.getText().toString();
            if (strUsername.isEmpty()) {
                Toast.makeText(MainActivity.this, "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            } else {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    progressDialog.show();
                    searchViewModel.setSearchUser(strUsername);
                    InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    imageClear.setVisibility(View.VISIBLE);
                    layoutEmpty.setVisibility(View.GONE);
                    return true;
                }
            }
            return false;
        });

        searchAdapter = new SearchAdapter(this);
        rvListUser.setLayoutManager(new LinearLayoutManager(this));
        rvListUser.setAdapter(searchAdapter);
        rvListUser.setHasFixedSize(true);

        //method set viewmodel
        searchViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        searchViewModel.getResultList().observe(this, modelSearchData -> {
            progressDialog.dismiss();
            if (modelSearchData.size() != 0) {
                searchAdapter.setSearchUserList(modelSearchData);
            } else {
                Toast.makeText(MainActivity.this, "Pengguna Tidak Ditemukan!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}