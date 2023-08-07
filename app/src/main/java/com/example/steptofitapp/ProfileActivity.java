package com.example.steptofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtProfileName,edtProfileEmail,edtProfilePhone;

    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        edtProfileName=findViewById(R.id.edtProfileName);
        edtProfileEmail=findViewById(R.id.edtProfileEmail);
        edtProfilePhone=findViewById(R.id.edtProfilePhone);

        btnClose=findViewById(R.id.btnClose);

        Intent intent=getIntent();

        ArrayList<String> data=intent.getStringArrayListExtra("data");

        if(data.size()!=0) {
            edtProfileName.setText(data.get(0));
            edtProfileEmail.setText(data.get(1));
            edtProfilePhone.setText(data.get(2));
        }
        else {
            Toast.makeText(this, "Not Found..", Toast.LENGTH_SHORT).show();
        }
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}