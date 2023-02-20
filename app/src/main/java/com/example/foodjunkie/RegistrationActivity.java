package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    private EditText eRegName;
    private EditText eRegPassword;
    private Button eRegister;

   public static Credentials credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.btnRegister);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String regUserName = eRegName.getText().toString();
                String regPassword = eRegPassword.getText().toString();
                if(validate(regUserName, regPassword)){
                    credentials = new Credentials(regUserName, regPassword);
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    //redirecting on click to log in screen
                }
            }
        });
        }
        private boolean validate(String username, String password){
        if(username.isEmpty() || password.length() < 8){
            // Other factors could be added if wanted and could seperate error messages.
            Toast.makeText(this, "Please enter vaild username and password. Password might be at least 8 characters", Toast.LENGTH_SHORT).show();
            //maybe make it longer
            return false;
        }
        return true;
        }

    }

