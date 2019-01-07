package com.example.scollex.jobbu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_editprofile extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Button editBtn, cancelBtn;
    private Spinner gender,level,jobType;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRegerence;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        initializeSpinner();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mRegerence = mDatabase.getReference();

        user = new User();
        user.setUserID(mUser.getUid());
        mRegerence.child("User");


        editBtn = findViewById(R.id.editprofile_btnEdit);
        cancelBtn = findViewById(R.id.editprofile_btnCancel);
        gender = findViewById(R.id.spinnerGender);
        level = findViewById(R.id.spinnerLevel);
        jobType = findViewById(R.id.spinnerJobType);

        editBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    private void initializeSpinner() {
        Spinner spinner = findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner1 = findViewById(R.id.spinnerJobType);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.JobType,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.spinnerLevel);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Level,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent == gender){
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(),"gender: " + text, Toast.LENGTH_SHORT).show();
        }
        if(parent == level){
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(),"level: " + text, Toast.LENGTH_SHORT).show();
        }
        if(parent == jobType){
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(),"Job Type: " + text, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(),"gender: not selected ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v == cancelBtn){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
        if(v == editBtn){
            saveIntoFireBase();
        }
    }

    private void saveIntoFireBase() {
    }
}