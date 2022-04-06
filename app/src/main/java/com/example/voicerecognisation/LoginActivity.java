package com.example.voicerecognisation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    Button btnLogin;
    TextView forgotPassword, createNewAccount;
    ProgressDialog mLoadingDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        forgotPassword = findViewById(R.id.forget_btn);
        createNewAccount = findViewById(R.id.register_btn);
        mLoadingDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttemptLogn();
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgotActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void AttemptLogn() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

//        check whether the user enter the value in editbox or not

        if (email.isEmpty() || !email.contains("@gmail")) {
            showError(inputEmail, "Email is not Valid");
        } else if (password.isEmpty() || password.length() < 5) {
            showError(inputPassword, "Password must be greater than 5 letter");
        } else {
            mLoadingDialog.setTitle("Login");
            mLoadingDialog.setMessage("Please Wait, While your Credentials");
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.show();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mLoadingDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Login is Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        mLoadingDialog.dismiss();
                        Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        mLoadingDialog.dismiss();
                        finish();
                    }
                }
            });
        }
    }

    private void showError(EditText field, String text) {
        field.setError(text);
        field.requestFocus();
    }
}
