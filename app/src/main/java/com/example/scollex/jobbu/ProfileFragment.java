package com.example.scollex.jobbu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private TextView name,birthday,age,type,exSalary,bio,level,profession,skill,language,gender;
    private FirebaseAuth auth;
    private FirebaseUser fuser;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        gender = v.findViewById(R.id.textGender);
        age = v.findViewById(R.id.textAge);
        name = v.findViewById(R.id.profileName);
        level = v.findViewById(R.id.textLevel);
        type = v.findViewById(R.id.textJobType);
        birthday = v.findViewById(R.id.textBirthday);
        bio = v.findViewById(R.id.textBio);
        profession = v.findViewById(R.id.textProfession);
        skill = v.findViewById(R.id.textSkill);
        language = v.findViewById(R.id.textLanguage);
        exSalary = v.findViewById(R.id.textExSalary);

        auth = FirebaseAuth.getInstance();
        fuser = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        reference.child("User");

        reference.child("User").child(fuser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = new User();
                if(dataSnapshot.child("Name").getValue() != null) {
                    user.setName(dataSnapshot.child("Name").getValue().toString());
                    user.setAge(Integer.parseInt(dataSnapshot.child("Age").getValue().toString()));

                    Log.w(".MainActivity: ", "show data: Name: " + user.getName());
                    Log.w(".MainActivity: ", "show data: Age: " + user.getAge());

                    name.setText(user.getName());
                    birthday.setText(user.getBirthdate());
                    age.setText(user.getAge());
                    type.setText(user.getJobType());
                    exSalary.setText(user.getExpectedSalary());
                    bio.setText(user.getBio());
                    level.setText(user.getEducation().level);
                    profession.setText(user.getEducation().Professions);
                    skill.setText(user.getSkill());
                    language.setTag(user.getLanguage());
                    gender.setText(user.getGender());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(".MainActivity: ", "Failed to read value.", databaseError.toException());
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
