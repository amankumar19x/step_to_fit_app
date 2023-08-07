package com.example.steptofitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrainingMuscleActivity extends AppCompatActivity {

    private TextView txtTrainingMuscleName;
    private RecyclerView recyclerViewTrainingMuscle;
    ProgressBar progressBar;

    private ArrayList<ApiModel> modelArrayList=new ArrayList<>();
    private String url="https://api.api-ninjas.com/v1/exercises?muscle=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_muscle);

        txtTrainingMuscleName=findViewById(R.id.txtTrainingMuscleName);
        recyclerViewTrainingMuscle=findViewById(R.id.recyclerViewTrainingMuscle);
        progressBar=findViewById(R.id.progressBar);

        recyclerViewTrainingMuscle.setLayoutManager(new LinearLayoutManager(TrainingMuscleActivity.this));

        Intent intent=getIntent();
        String muscleName=intent.getStringExtra("muscle_name");

        txtTrainingMuscleName.setText(muscleName);

        fetchMuscleExerciseData(muscleName);
    }


    public void fetchMuscleExerciseData(String muscleName)
    {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest getRequest = new StringRequest(Request.Method.GET, url+muscleName,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray array=new JSONArray(response);
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject object=array.getJSONObject(i);

                                String name=object.getString("name");
                                String type=object.getString("type");
                                String muscle=object.getString("muscle");
                                String equipment=object.getString("equipment");
                                String difficulty=object.getString("difficulty");
                                String instructions=object.getString("instructions");

                                ApiModel model=new ApiModel(name,type,muscle,equipment,difficulty,instructions,R.drawable.dumble);
                                modelArrayList.add(model);
                            }

                            progressBar.setVisibility(View.GONE);
                            ApiAdapter adapter=new ApiAdapter(TrainingMuscleActivity.this,modelArrayList);
                            recyclerViewTrainingMuscle.setAdapter(adapter);

                        } catch (JSONException e) {

                            Toast.makeText(TrainingMuscleActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Api-Key", "l3CB7yIzRyaX+apSTuon9A==AznszXjguj0ELYaM");

                return params;
            }
        };
        queue.add(getRequest);
    }
}