package com.example.nyayur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

//import android.view.View;

public class LoginActivity extends AppCompatActivity {
    protected TextInputEditText email,password;
    protected Button loginbtn;
    protected TextView toRegister;
    protected DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (TextInputEditText) findViewById(R.id.email);
        password = (TextInputEditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        toRegister = (TextView) findViewById(R.id.toRegister);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            email.setText(extras.getString("emailRegis"));
            password.setText(extras.getString("passwdRegis"));
        }
        try (DbHelper dbHelper = db = new DbHelper(this)) {
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String credmail = email.getText().toString();
                String credpassword = password.getText().toString();

                if(credmail.isEmpty()||credpassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter all fields !", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("email",credmail);
                    startActivity(intent);
//                    if(db.checkCredentials(credmail,credpassword)){
//                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                        startActivity(intent);
//                    }else {
//                        Toast.makeText(LoginActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });

        toRegister.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
    }
}
