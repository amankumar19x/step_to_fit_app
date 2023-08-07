package com.example.steptofitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTraining;

    private ArrayList<TrainingModel> modelArrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        recyclerViewTraining=findViewById(R.id.recyclerViewTraining);

        addData();

        recyclerViewTraining.setLayoutManager(new LinearLayoutManager(TrainingActivity.this));
        TrainingAdapter adapter=new TrainingAdapter(TrainingActivity.this,modelArrayList);
        recyclerViewTraining.setAdapter(adapter);
    }

    private void addData()
    {
        modelArrayList.add(new TrainingModel("abdominals",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("abductors",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("adductors",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("biceps",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("calves",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("triceps",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("chest",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("forearms",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("glutes",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("hamstrings",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("lats",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("lower_back",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("middle_back",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("neck",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("quadriceps",R.drawable.dumble));
        modelArrayList.add(new TrainingModel("traps",R.drawable.dumble));
    }



}