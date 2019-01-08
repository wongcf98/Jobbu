package com.example.scollex.jobbu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class activity_editprofile extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    public static final int GET_FROM_GALLERY = 3;

    private CircleImageView profilePic;
    private Button editBtn, cancelBtn;
    private Spinner gender,level,jobType;
    private EditText birthday,bio,profession,skill,language,salary;

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
        birthday = findViewById(R.id.editBirthday);
        bio = findViewById(R.id.editBio);
        profession = findViewById(R.id.editProfession);
        skill = findViewById(R.id.editSkill);
        language = findViewById(R.id.editLanguage);
        salary = findViewById(R.id.editExSalary);
        profilePic = findViewById(R.id.profilePic);

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
            if(TextUtils.isEmpty(birthday.getText())){
                Toast.makeText(this,"Please fill in all the fields ", Toast.LENGTH_SHORT).show();
                return;
            }if(TextUtils.isEmpty(salary.getText())){
                Toast.makeText(this,"Please fill in all the fields ", Toast.LENGTH_SHORT).show();
                return;
            }if(TextUtils.isEmpty(profession.getText())){
                Toast.makeText(this,"Please fill in all the fields ", Toast.LENGTH_SHORT).show();
                return;
            }if(TextUtils.isEmpty(language.getText())){
                Toast.makeText(this,"Please fill in all the fields ", Toast.LENGTH_SHORT).show();
                return;
            }if(TextUtils.isEmpty(bio.getText())){
                Toast.makeText(this,"Please fill in all the fields ", Toast.LENGTH_SHORT).show();
                return;
            }if(TextUtils.isEmpty(skill.getText())){
                Toast.makeText(this,"Please fill in all the fields ", Toast.LENGTH_SHORT).show();
                return;
            }
            saveIntoFireBase();
        }
    }

    private void saveIntoFireBase() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String biodata1 = bio.getText().toString().trim();
        String birthdate = birthday.getText().toString().trim();
        int day = Integer.parseInt( birthdate.substring(0,2));
        int month = Integer.parseInt( birthdate.substring(3,5));
        int year = Integer.parseInt( birthdate.substring(6,10));
        Calendar birth = Calendar.getInstance();
        birth.set(Calendar.DAY_OF_MONTH,day);
        birth.set(Calendar.MONTH ,month+1);
        birth.set(Calendar.YEAR , year);
        String gender1 = gender.getSelectedItem().toString();
        String level1 = level.getSelectedItem().toString();
        String profession1 = profession.getText().toString().trim();
        int expectedSalary = Integer.parseInt(salary.getText().toString());
        String lang = language.getText().toString().trim();
        String skill1 = skill.getText().toString().trim();
        String type1 = jobType.getSelectedItem().toString();

        int age = calculateAge(day,month,year);
        user.setBirthday(birth);
        user.setAge(age);
        user.setBio(biodata1);
        user.setEducation(new User.Education(level1,profession1));
        user.setExpectedSalary(expectedSalary);
        user.setGender(gender1);
        user.setJobType(type1);
        user.setLanguage(lang);
        user.setSkill(skill1);
        user.setName(userName);

        reference.child("User").child(userID).setValue(user);

        Toast.makeText(this,"Profile Updated",Toast.LENGTH_SHORT).show();

        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    private int calculateAge(int day, int month, int year) {
        Calendar today = Calendar.getInstance();
        int todayYear = today.get(Calendar.YEAR);

        return todayYear - year;
    }

    public void uploadPhoto(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                profilePic.setImageBitmap(bitmap);
                user.setUserPic(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}