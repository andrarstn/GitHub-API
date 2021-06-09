package com.kelompokempat.githubapi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.kelompokempat.githubapi.adapter.ViewPagerAdapter;
import com.kelompokempat.githubapi.model.search.ModelSearchData;
import com.kelompokempat.githubapi.model.user.ModelUser;
import com.kelompokempat.githubapi.viewmodel.UserViewModel;
import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String DETAIL_USER = "DETAIL_USER";
    ArrayList<ModelUser> modelUserArrayList = new ArrayList<>();
    UserViewModel userViewModel;
    ModelSearchData modelSearchData;
    String strUsername;
    ImageView imageUser;
    TextView tvUsername, tvBio, tvFollowers, tvFollowing, tvRepository;
    TabLayout tabsLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    MaterialFavoriteButton imageFavorite;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        imageUser = findViewById(R.id.imageUser);
        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);
        tvFollowers = findViewById(R.id.tvFollowers);
        tvFollowing = findViewById(R.id.tvFollowing);
        tvRepository = findViewById(R.id.tvRepository);
        tabsLayout = findViewById(R.id.tabsLayout);
        viewPager = findViewById(R.id.viewPager);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);

        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        modelSearchData = getIntent().getParcelableExtra(DETAIL_USER);
        if (modelSearchData != null) {
            strUsername = modelSearchData.getLogin();
        }

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), modelSearchData));
        viewPager.setOffscreenPageLimit(2);
        tabsLayout.setupWithViewPager(viewPager);

        //method set viewmodel
        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        userViewModel.setUserDetail(strUsername);
        userViewModel.getUserList().observe(this, modelUser -> {
            Glide.with(getApplicationContext())
                    .load(modelUser.getAvatarUrl())
                    .into(imageUser);

            collapsingToolbarLayout.setTitle(modelUser.getName());
            tvUsername.setText(modelUser.getLogin() + " \u2022 " + modelUser.getLocation());
            tvBio.setText(modelUser.getBio());
            tvFollowers.setText(modelUser.getFollowers());
            tvFollowing.setText(modelUser.getFollowing());
            tvRepository.setText(modelUser.getPublicRepos());

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}