package com.example.scollex.jobbu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private SharedPreferences mPreferences;
    private String sharedPrefile = "com.example.scollex.jobbu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = getSharedPreferences(sharedPrefile,MODE_PRIVATE);

        first_time_check();

        setContentView(R.layout.activity_main);
        firebaseAuth.getInstance();

        BottomNavigationView mBottomNav = findViewById(R.id.bottom_nav);
        mBottomNav.setOnNavigationItemSelectedListener(navListener);

        //to Open the homepage fragment when apps start
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

    private boolean first_time_check() {

        String first = mPreferences.getString("first", null);
        if((first == null)){
            Intent i = new Intent(MainActivity.this, activity_login.class);
            finish();
            startActivity(i);
        }
        return false;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment selectedFragment = null;

                    //selecting different icon at the bottom bar will navigate to different fragment
                    switch(menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_fav:
                            selectedFragment = new FavoritesFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    public void logout(View view) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        preferencesEditor.putString("first",null);

        preferencesEditor.apply();

        first_time_check();
    }
}
