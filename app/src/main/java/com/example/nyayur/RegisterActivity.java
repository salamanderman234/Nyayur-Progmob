package com.example.nyayur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    protected EditText emailField,passwordField,repeat_password;
    protected Button registerbtn;
    protected DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        emailField = (EditText) findViewById(R.id.emailRegister);
//        passwordField = (EditText) findViewById(R.id.passwordRegister);
//        repeat_password = (EditText) findViewById(R.id.repeat_password);
//        registerbtn = (Button) findViewById(R.id.registerbtn);
//        db = new DbHelper(this);
//
//        registerbtn.setOnClickListener(view -> {
//            String email = emailField.getText().toString();
//            String password = passwordField.getText().toString();
//            String repeat = repeat_password.getText().toString();
//
//            if(email.isEmpty()||password.isEmpty()||repeat.isEmpty())
//                Toast.makeText(RegisterActivity.this, "Please enter all fields !", Toast.LENGTH_SHORT).show();
//            else if (!Objects.equals(password, repeat)) {
//                Toast.makeText(RegisterActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
//            } else {
//                if(!db.checkEmailExists(email)){
//                    Boolean result = db.insertData(email,password);
//                    if(result){
//                        Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                        intent.putExtra("emailRegis",email);
//                        intent.putExtra("passwdRegis",password);
//                        startActivity(intent);
//                    }else {
//                        Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}
