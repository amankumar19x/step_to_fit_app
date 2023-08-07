package com.example.steptofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ForgotActivity extends AppCompatActivity {

    private EditText edtForgotEmail,edtForgotPhone;
    private TextView txtPassword;
    private Button btnGetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        edtForgotEmail=findViewById(R.id.edtForgotEmail);
        edtForgotPhone=findViewById(R.id.edtForgotPhone);
        txtPassword=findViewById(R.id.txtPassword);
        btnGetPass=findViewById(R.id.btnGetPass);

        btnGetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Database database=new Database(ForgotActivity.this,"FitnessDB",null,1);

                String email=edtForgotEmail.getText().toString();
                String phone=edtForgotPhone.getText().toString();

                if(email.equals("")|| phone.equals(""))
                {
                    Toast.makeText(ForgotActivity.this, "Please Enter Email and Phone No.. ", Toast.LENGTH_SHORT).show();
                }
                else {
                    ArrayList<String> data = database.getUserPassword(email, phone);
                    if(data.size()!=0) {
                        txtPassword.setText("Password: " + data.get(3));
                    }
                    else {
                        txtPassword.setText("");
                        Toast.makeText(ForgotActivity.this, "No User Found", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}