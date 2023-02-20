package com.example.foodjunkie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ipsec.ike.TunnelModeChildSessionParams;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
private EditText eName;
private EditText ePassword;
private Button eLogin;
private TextView eAttemptsInfo;
boolean isValid = false;
private int counter = 5;

private TextView eRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);
        eRegister = findViewById(R.id.tvRegister);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please ensure user and password is entered", Toast.LENGTH_SHORT).show();
                } else{

                    isValid = validate(inputName, inputPassword);

                    if(!isValid){
                        counter--;
                        Toast.makeText(MainActivity.this, "The username/password entered is incorrect", Toast.LENGTH_SHORT).show();
                        eAttemptsInfo.setText("Number of Attempts remaining: "  + counter);

                       if(counter == 0){
                            eLogin.setEnabled(false);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        //This switches the screen to homepage can be edited to open first screen of app.
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });
    }
    private boolean validate(String name, String password) {

        if (RegistrationActivity.credentials != null) {
            if (name.equals(RegistrationActivity.credentials.getUsername()) && password.equals(RegistrationActivity.credentials.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
