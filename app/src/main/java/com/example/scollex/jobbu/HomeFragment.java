package com.example.scollex.jobbu;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<JobClass> jobClass;
    Dialog myDialog;
    Button favbutton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home,container,false);


        recyclerView = (RecyclerView) v.findViewById(R.id.home_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),jobClass);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jobClass = new ArrayList<>();
        fillJobList();

        //toast for fav popup msg
       // favbutton = (Button) v.findViewById(R.id.btnFav);

    }


    private void fillJobList() {
        jobClass.add(new JobClass("ACCOUNT EXECUTIVE", " RM 5000 ","  5 Day A Week",
                "   Full Time ", "JOB DESCRIPTION :  \n\n" +
                " * Prepares asset, liability and analyzing account information.\n" +
                " * Recommends financial actions by analyzing accounting options.\n","Kuala Lumpur","New York",R.drawable.accountant));


        jobClass.add(new JobClass("IT Consultant", " RM 5000 ","  5 Day A Week",
                "   Full Time ", "JOB DESCRIPTION :  \n\n" +
                " * Analytical Skills: IT consultants have to be able to look at complex data and computer systems and find inefficiencies, analyze weaknesses or security threats." ,
                "Kuala Lumpur","New York",R.drawable.it));

        jobClass.add(new JobClass("Graphic Designer", " RM 5000 ","  4 Day A Week",
                "   Full Time ", "JOB DESCRIPTION :  \n\n" +
                " * Excellent IT skills, especially with design and photo-editing software.\n"
                ,"Kuala Lumpur","New York",R.drawable.graphic));

        jobClass.add(new JobClass("Multimedia Designer", " Category : Office ","  4 Day A Week",
                "   Full Time ", "JOB DESCRIPTION :  \n\n" +
                " * Luminous Aquatic Systems is looking for a creative, talented individual to join its marketing department as a Multimedia Designer.\n"
                ,"Kuala Lumpur","New York",R.drawable.media));

        jobClass.add(new JobClass("Civil Engineering", " RM 5000 ","  5 Day A Week",
                "   Full Time ", "JOB DESCRIPTION :  \n\n" +
                " * Analyze survey reports, maps, and other data to plan projects. \n"
                ,"Kuala Lumpur","New York",R.drawable.engineering));


        jobClass.add(new JobClass("Pharmacist", " RM 5000 ","  3 Day A Week",
                "   Full Time ", "JOB DESCRIPTION :  \n\n" +
                " * Prepares medications by reviewing and interpreting physician orders;."
               ,"Kuala Lumpur","New York ",R.drawable.accountant));
    }

    /*@Override
    public void onClick(View v) {
        if(v==favbutton){
            Toast.makeText(getActivity(), "Favourite Added", Toast.LENGTH_SHORT).show();
        }
    }*/
}
