package com.example.steptofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ExerciseTutorialActivity extends AppCompatActivity {

    private TextView txtExerciseName,txtExerciseType,txtExerciseMuscle,txtExerciseEquipment,txtExerciseDifficulty,txtExerciseTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_tutorial);

        txtExerciseName=findViewById(R.id.txtExerciseName);
        txtExerciseType=findViewById(R.id.txtExerciseType);
        txtExerciseEquipment=findViewById(R.id.txtExerciseEquipment);
        txtExerciseMuscle=findViewById(R.id.txtExerciseMuscle);
        txtExerciseDifficulty=findViewById(R.id.txtExerciseDifficulty);
        txtExerciseTutorial=findViewById(R.id.txtExerciseTutorial);

        Intent intent=getIntent();
        txtExerciseName.setText(intent.getStringExtra("name"));
        txtExerciseType.setText("Type: "+intent.getStringExtra("type"));
        txtExerciseMuscle.setText("Muscle: "+intent.getStringExtra("muscle"));
        txtExerciseEquipment.setText("Equipment: "+intent.getStringExtra("equipment"));
        txtExerciseDifficulty.setText("Difficulty: "+intent.getStringExtra("difficulty"));
        txtExerciseTutorial.setText(intent.getStringExtra("instructions"));

    }
}