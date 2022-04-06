package com.example.voicerecognisation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {

    private EditText txtEmail;
    private Button forgotBtn;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        txtEmail = findViewById(R.id.field_email);
        forgotBtn = findViewById(R.id.forgotpassButton);
        auth = FirebaseAuth.getInstance();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }
    private void validateData()
    {
        email = txtEmail.getText().toString();
        if (email.isEmpty()){
            txtEmail.setError("Required");
        }else{
            forgetPassword();
        }
    }

    private void forgetPassword()
    {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotActivity.this, "Check Your Email Address", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotActivity.this,LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(ForgotActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}