package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.nyayur.databinding.ActivityRegisterBinding;


import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    protected ActivityRegisterBinding binding;
    protected DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // binding
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ///////////

        //db
        db = new DbHelper(this);
        ////

        // click listerner register
        binding.registerbtn.setOnClickListener(view -> {
            String email = binding.emailRegister.getText().toString();
            String password = binding.passwordRegister.getText().toString();
            String repeat = binding.confirmPassword.getText().toString();

            if(email.isEmpty()||password.isEmpty()||repeat.isEmpty())
                Toast.makeText(RegisterActivity.this, "Please enter all fields !", Toast.LENGTH_SHORT).show();
            else if (!Objects.equals(password, repeat)) {
                Toast.makeText(RegisterActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
            } else {
                if(!db.checkEmailExists(email)){
                    Boolean result = db.insertData(email,password);
                    if(result){
                        Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        intent.putExtra("emailRegis",email);
                        intent.putExtra("passwdRegis",password);
                        startActivity(intent);
                    }else {
                        Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ////////

        // mengarahkan user ke login
        binding.toLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
        /////////////////////////////
    }
}
