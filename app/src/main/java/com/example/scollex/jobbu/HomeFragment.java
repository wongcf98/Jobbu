package com.example.scollex.jobbu;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<JobClass> jobList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    Dialog myDialog;
    Button favbutton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = (RecyclerView) v.findViewById(R.id.home_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), jobList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jobList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        databaseReference.child("Job").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    JobClass job = ds.getValue(JobClass.class);
                    Log.w(".HomeFragment: ", "data: " + job.getJobName());
                    jobList.add(job);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(".HomeFragment: ", "Failed to read value.", databaseError.toException());
            }
        });

    }

    /*@Override
    public void onClick(View v) {
        if(v==favbutton){
            Toast.makeText(getActivity(), "Favourite Added", Toast.LENGTH_SHORT).show();
        }
    }*/
}
