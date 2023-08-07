package com.example.steptofitapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin,btnLoginRegister;

    private TextView txtForgotPass;
    private EditText edtLoginEmail,edtLoginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=findViewById(R.id.btnLogin);
        btnLoginRegister=findViewById(R.id.btnLoginRegister);
        edtLoginEmail=findViewById(R.id.edtLoginEmail);
        edtLoginPass=findViewById(R.id.edtLoginPass);
        txtForgotPass=findViewById(R.id.txtForgotPass);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs",Context.MODE_PRIVATE);
        boolean flag=sharedPreferences.getBoolean("flag",false);
        if(flag)
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            onBackPressed();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email=edtLoginEmail.getText().toString();
                String pass=edtLoginPass.getText().toString();

                if(!(email.equals("")||pass.equals("")))
                {
                    Database database=new Database(LoginActivity.this,"FitnessDB",null,1);
                    if(database.fetchData(email,pass)==1)
                    {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("flag",true);
                        editor.putString("username",database.getUserData(email,pass).get(0));
                        editor.putString("password",pass);
                        editor.putString("email",email);
                        editor.apply();
                    }
                    else
                        Toast.makeText(LoginActivity.this, "No User Found..", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(LoginActivity.this, "Please Fill All Details..", Toast.LENGTH_SHORT).show();
            }
        });

        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,ForgotActivity.class);
                startActivity(intent);

            }
        });
    }
}