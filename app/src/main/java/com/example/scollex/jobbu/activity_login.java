package com.example.scollex.jobbu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_login extends AppCompatActivity implements View.OnClickListener {

    ProgressBar mLoginProgressBar;
    Button mLoginButton;
    Button mRegisterButton;
    EditText mEmailText;
    EditText mPasswordText;

    private String TAG = ".activity_login";
    private SharedPreferences mPreferences;
    private String sharedPrefile = "com.example.scollex.jobbu";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().toString(),"onCreate(): ");
        setContentView(R.layout.activity_login);

        mPreferences = getSharedPreferences(sharedPrefile,MODE_PRIVATE);
        mLoginProgressBar = (ProgressBar)findViewById(R.id.login_progressBar);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mRegisterButton = (Button) findViewById(R.id.register_buton);
        mEmailText = findViewById(R.id.login_email);
        mPasswordText = findViewById(R.id.login_password);

        mLoginButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
        mLoginProgressBar.setVisibility(View.INVISIBLE);

        firebaseAuth = firebaseAuth.getInstance();



    }

    @Override
    public void onClick(View view){
        if(view == mRegisterButton){
            //will initiate login process
            Intent intent = new Intent(this, activity_register.class);

            startActivity(intent);
        }
        if(view == mLoginButton){
            loginUser();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void updateUI(){
        mLoginProgressBar.setVisibility(View.INVISIBLE);
        mRegisterButton.setVisibility(View.VISIBLE);
        mLoginButton.setVisibility(View.VISIBLE);

        //finish();
        //startActivity(new Intent(this,MainActivity.class));
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
        mLoginProgressBar.setVisibility(View.VISIBLE);
        mRegisterButton.setVisibility(View.INVISIBLE);
        mLoginButton.setVisibility(View.INVISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(activity_login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });




    }
}
