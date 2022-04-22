package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button loginbtn;
    TextView toRegister;
    DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.emailRegisterLayout);
        password = (EditText) findViewById(R.id.passwordRegister);
        loginbtn = (Button) findViewById(R.id.registerbtn);
        toRegister = (TextView) findViewById(R.id.toRegister);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            email.setText(extras.getString("emailRegis"));
            password.setText(extras.getString("passwdRegis"));
        }
        db = new DbHelper(this);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String credmail = email.getText().toString();
                String credpassword = password.getText().toString();

                if(credmail.isEmpty()||credpassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter all fields !", Toast.LENGTH_SHORT).show();
                }else {
                    if(db.checkCredentials(credmail,credpassword)){
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        toRegister.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
            startActivity(intent);
        });
    }
}
