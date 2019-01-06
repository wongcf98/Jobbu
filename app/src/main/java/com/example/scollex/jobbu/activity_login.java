package com.example.scollex.jobbu;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity implements View.OnClickListener {

    ProgressBar mLoginProgressBar;
    Button mLoginButton;
    Button mRegisterButton;
    EditText mEmailText;
    EditText mPasswordText;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginProgressBar = (ProgressBar)findViewById(R.id.login_progressBar);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mRegisterButton = (Button) findViewById(R.id.register_buton);
        mEmailText = findViewById(R.id.login_email);
        mPasswordText = findViewById(R.id.login_password);

        mLoginButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
        mLoginProgressBar.setVisibility(View.INVISIBLE);
        firebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view){
        if(view == mRegisterButton){
            //will initiate login process

        }
        if(view == mLoginButton){
            loginUser();
            mLoginProgressBar.setVisibility(View.VISIBLE);
            mRegisterButton.setVisibility(View.INVISIBLE);
            mLoginButton.setVisibility(View.INVISIBLE);
        }
    }

    private void loginUser(){
        String email = mEmailText.getText().toString().trim();
        String password = mPasswordText.getText().toString().trim();

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
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(activity_login.this, "Register Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(activity_login.this, "Register Error. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
