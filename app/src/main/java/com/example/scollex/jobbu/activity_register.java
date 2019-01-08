package com.example.scollex.jobbu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_register extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser mFirebaseUser;
    FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private EditText mDisplayName;
    private EditText mEmailText;
    private EditText mPasswordText;
    private EditText mConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = firebaseAuth.getInstance();
        mFirebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


        mDisplayName = findViewById(R.id.register_DisplayName);
        mEmailText = findViewById(R.id.register_email);
        mPasswordText = findViewById(R.id.register_password);
        mConfirmPass = findViewById(R.id.register_confirmPass);
    }

    public void registerUser(View view) {
        String email = mEmailText.getText().toString().trim();
        String password = mPasswordText.getText().toString().trim();
        String confirmPassword = mConfirmPass.getText().toString().trim();

        if(TextUtils.isEmpty(mDisplayName.getText())){
            //display name is empty
            Toast.makeText(this,"Please enter a display name",Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter a email",Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter a password",Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this,"Please enter the confirm password",Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.equals(password,confirmPassword)){
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                //create a user information into firebase
                                createUserInformation(firebaseAuth.getCurrentUser().getUid());


                                Toast.makeText(activity_register.this, "Register Successful", Toast.LENGTH_SHORT).show();

                                finish();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                            else{
                                Toast.makeText(activity_register.this, "Register Error. Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            return;
        }else{
            Toast.makeText(this,"Please enter the same password",Toast.LENGTH_SHORT).show();

        }

    }

    private void createUserInformation(String UID) {
        User user = new User();
        user.setUserID(firebaseAuth.getCurrentUser().getUid());
        user.setName(mDisplayName.getText().toString().trim());

        databaseReference.child("User").child(UID).setValue(user.getName());
    }

    private void updateUI() {
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}



