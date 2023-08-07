package com.example.steptofitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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

public class StretchingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewStretching;
    private ArrayList<ApiModel> modelArrayList=new ArrayList<>();

    private ProgressBar progressBar;
    private String url="https://api.api-ninjas.com/v1/exercises?type=stretching";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretching);
        recyclerViewStretching=findViewById(R.id.recyclerViewStretching);
        progressBar=findViewById(R.id.progressBar);

        recyclerViewStretching.setLayoutManager(new LinearLayoutManager(StretchingActivity.this));

        fetchData(recyclerViewStretching);
    }

    public void fetchData(RecyclerView recyclerView)
    {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
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

                                ApiModel model=new ApiModel(name,type,muscle,equipment,difficulty,instructions,R.drawable.streching);
                                modelArrayList.add(model);
                            }

                            progressBar.setVisibility(View.GONE);
                            ApiAdapter adapter=new ApiAdapter(StretchingActivity.this,modelArrayList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {

                            Toast.makeText(StretchingActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
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