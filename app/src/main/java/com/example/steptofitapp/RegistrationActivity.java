package com.example.steptofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText edtRegName,edtRegEmail,edtRegPhone,edtRegPass,edtRegConfirmPass;
    private Button btnRegister,btnRegLogin;

    private String strongPassword="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtRegName=findViewById(R.id.edtRegName);
        edtRegEmail=findViewById(R.id.edtRegEmail);
        edtRegPhone=findViewById(R.id.edtRegPhone);
        edtRegPass=findViewById(R.id.edtRegPass);
        edtRegConfirmPass=findViewById(R.id.edtRegConfirmPass);

        btnRegister=findViewById(R.id.btnRegister);
        btnRegLogin=findViewById(R.id.btnRegLogin);

        btnRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=edtRegName.getText().toString();
                String email=edtRegEmail.getText().toString();
                String phone=edtRegPhone.getText().toString();
                String pass=edtRegPass.getText().toString();
                String confirmPass=edtRegConfirmPass.getText().toString();

                if(!(name.equals("")||email.equals("")||phone.equals("")||pass.equals("")||confirmPass.equals("")))
                {

                    if(pass.compareTo(confirmPass)==0)
                    {
                        if(pass.matches(strongPassword))
                        {
                            Database database=new Database(RegistrationActivity.this,"FitnessDB",null,1);
                            if(database.fetchData(email,pass)==0)
                            {
                                database.addUser(name,email,phone,pass);
                                Toast.makeText(RegistrationActivity.this, "Registration Successful..", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "User Already Exists..", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this, "Please Fill Strong Password..", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this, "Password Not Matched..", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                    Toast.makeText(RegistrationActivity.this, "Please Fill All Details..", Toast.LENGTH_SHORT).show();
            }
        });


    }
}