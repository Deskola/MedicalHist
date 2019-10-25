package com.example.medicalhist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.medicalhist.model.Hospital;
import com.example.medicalhist.recycler.HospitalAdapter;

import java.util.List;

public class HospitalsVisitedActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter  adapter;

    List<Hospital> hospitals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_visited);

        recyclerView = findViewById(R.id.viewHospitals);
        //hospitals
        adapter = new HospitalAdapter(hospitals,this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
