package com.bedefined.alpaca_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    private BottomNavigationView bottomNavigationViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Utils.startTheme(this, new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationViewProfile = findViewById(R.id.bottom_navigation_profile);
        bottomNavigationViewProfile.setSelectedItemId(R.id.profile_page);
        bottomNavigationViewProfile.setOnNavigationItemSelectedListener( item -> {

            switch(item.getItemId()) {
                case R.id.dashboard_page:
                    Intent intentMain = new Intent(this, MainActivity.class);
                    this.startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                    return true;
                case R.id.search_page:
                    Intent intentSearch = new Intent(this, Search.class);
                    this.startActivity(intentSearch, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                    return true;
                case R.id.profile_page:
                    return true;
            }
            return false;
        });
    }
}