package com.example.scollex.jobbu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser mUser;

    private TextView profileName;
    private Button logoutButton;
    private String mUsername;
    private String mPhotoUrl;
    private View profile_topView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView mBottomNav = findViewById(R.id.bottom_nav);
        mBottomNav.setOnNavigationItemSelectedListener(navListener);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        logoutButton = findViewById(R.id.profile_logoutBtn);


        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            finish();
            startActivity(new Intent(this, activity_login.class));
            return;
        }

        //to Open the homepage fragment when apps start
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        databaseReference.child("User");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = new User();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    user.setAge(ds.child(mFirebaseUser.getUid()).getValue(User.class).getAge());
                    user.setName(ds.child(mFirebaseUser.getUid()).getValue(User.class).getName());
                }
                Log.w(".MainActivity: ", "show data: Name: "+ user.getName());
                Log.w(".MainActivity: ", "show data: Age: "+ user.getAge());
                updateUI(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(".MainActivity: ", "Failed to read value.", databaseError.toException());
            }
        });

    }

    private void updateUI(User user) {
        profileName = findViewById(R.id.profileName);
        if(profileName!=null) {
            profileName.setText(user.getName());
        }

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

    public void logout(View v) {
        mFirebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,activity_login.class));
    }


    public void edit(View v){
        finish();
        startActivity(new Intent(this,activity_editprofile.class));
    }
  
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnApply:
                Toast.makeText(this, "Applied Successfully ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnFav:
                Toast.makeText(this, "Favourite Added ",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
