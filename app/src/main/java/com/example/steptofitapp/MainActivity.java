package com.example.steptofitapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CardView cardNutrition,cardCardio,cardTraining,cardStretching,cardLogout,cardProfile,cardTracker;
    private TextView txtUserName;

    private boolean flagGoal=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtUserName=findViewById(R.id.txtUserName);
        cardNutrition=findViewById(R.id.cardNutrition);
        cardCardio=findViewById(R.id.cardCardio);
        cardTraining=findViewById(R.id.cardTraining);
        cardStretching=findViewById(R.id.cardStretching);
        cardLogout=findViewById(R.id.cardLogout);
        cardProfile=findViewById(R.id.cardProfile);
        cardTracker=findViewById(R.id.cardTracker);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","");
        txtUserName.setText("Hello "+username+" !");


        cardNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,NutritionValueActivity.class));
            }
        });

        cardCardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CardioActivity.class));
            }
        });

        cardTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,TrainingActivity.class));

            }
        });

        cardStretching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,StretchingActivity.class));
            }
        });

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Logout");
                builder.setIcon(R.drawable.baseline_logout_24);
                builder.setMessage("Are you sure to logout?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("flag",false);
                        editor.apply();
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();


            }
        });


        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database=new Database(MainActivity.this,"FitnessDB",null,1);

                String email=sharedPreferences.getString("email","");
                String pass=sharedPreferences.getString("password","");
               ArrayList<String> usersData=database.getUserData(email,pass);

               Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
               intent.putStringArrayListExtra("data",usersData);
               startActivity(intent);

            }
        });

        cardTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,TrackerActivity.class));
            }
        });

    }
}