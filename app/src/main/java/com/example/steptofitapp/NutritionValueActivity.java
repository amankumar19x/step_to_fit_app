package com.example.steptofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class NutritionValueActivity extends AppCompatActivity {

    String url="https://api.api-ninjas.com/v1/nutrition?query=";

    private EditText edtSearch;
    private Button btnSearch;
    private TextView txtFoodName,txtCalorie,txtWeight,txtTotalFat,txtSatFat,txtProtein,txtSodium,txtPotassium,txtCholesterol,txtCarbohydrates,txtFiber,txtSugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_value);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        edtSearch=findViewById(R.id.edtSearch);
        btnSearch=findViewById(R.id.btnSearch);
        txtCalorie=findViewById(R.id.txtCalorie);
        txtWeight=findViewById(R.id.txtWeight);
        txtTotalFat=findViewById(R.id.txtTotalFat);
        txtSatFat=findViewById(R.id.txtSatFat);
        txtProtein=findViewById(R.id.txtProtein);
        txtSodium=findViewById(R.id.txtSodium);
        txtPotassium=findViewById(R.id.txtPotassium);
        txtCholesterol=findViewById(R.id.txtCholesterol);
        txtCarbohydrates=findViewById(R.id.txtCarbohydrates);
        txtFiber=findViewById(R.id.txtFiber);
        txtSugar=findViewById(R.id.txtSugar);
        txtFoodName=findViewById(R.id.txtFoodName);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url="https://api.api-ninjas.com/v1/nutrition?query=";
                String foodName=edtSearch.getText().toString();

                if(!foodName.equals(""))
                {
                    url+=foodName;
                    fetchData(url);
                }
                else
                {
                    Toast.makeText(NutritionValueActivity.this, "Please enter food name!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public void fetchData(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONArray array=new JSONArray(response);
                            JSONObject object=array.getJSONObject(0);

                            final String cal="Calories: ";
                            final String wt="Serving Weight(g): ";
                            final String totalFat="Total Fat(g): ";
                            final String satFat="Fat Saturated(g): ";
                            final String protein="Protein(g): ";
                            final String sodium="Sodium(mg): ";
                            final String potassium="Potassium(mg): ";
                            final String cholesterol="Cholesterol(mg): ";
                            final String carb="Total Carbohydrates(g): ";
                            final String fiber="Fiber(g): ";
                            final String sugar="Sugar(g): ";

                            txtCalorie.setText(cal+object.getString("calories"));
                            txtWeight.setText(wt+object.getString("serving_size_g"));
                            txtTotalFat.setText(totalFat+object.getString("fat_total_g"));
                            txtSatFat.setText(satFat+object.getString("fat_saturated_g"));
                            txtProtein.setText(protein+object.getString("protein_g"));
                            txtSodium.setText(sodium+object.getString("sodium_mg"));
                            txtPotassium.setText(potassium+object.getString("potassium_mg"));
                            txtCholesterol.setText(cholesterol+object.getString("cholesterol_mg"));
                            txtCarbohydrates.setText(carb+object.getString("carbohydrates_total_g"));
                            txtFiber.setText(fiber+object.getString("fiber_g"));
                            txtSugar.setText(sugar+object.getString("sugar_g"));
                            txtFoodName.setText(object.getString("name"));
                        } catch (JSONException e) {
                            Toast.makeText(NutritionValueActivity.this, "Item not found!", Toast.LENGTH_SHORT).show();
                            txtFoodName.setText("Not Found");
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(NutritionValueActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();

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