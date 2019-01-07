package com.example.scollex.jobbu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_search, container, false);

            String [] values =
                    {"Kuala Lumpur","Penang","Selangor","Johor","Malacca","Perak","Kelantan","Kedah",
                        "Pahang","Terengganu","Negeri Sembilan","Perlis","Sabah","Sarawak"
                    };
            Spinner spinner = (Spinner) v.findViewById(R.id.state);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner.setAdapter(adapter);

        final ToggleButton part = (ToggleButton)v.findViewById(R.id.parttimebtn);
        part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(part.isChecked())
                {
                    part.setBackgroundColor(Color.BLACK);
                    part.setTextColor(Color.WHITE);
                }
                else
                {
                    part.setBackgroundColor(Color.WHITE);
                    part.setTextColor(Color.BLACK);
                }
            }
        });

        final ToggleButton full = (ToggleButton)v.findViewById(R.id.fulltimebtn);
        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(full.isChecked())
                {
                    full.setBackgroundColor(Color.BLACK);
                    full.setTextColor(Color.WHITE);
                }
                else
                {
                    full.setBackgroundColor(Color.WHITE);
                    full.setTextColor(Color.BLACK);
                }
            }
        });














            return v;
    }
}
