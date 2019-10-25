package com.example.medicalhist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AppRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText regEmail, regPassword, regConfPassword;
    private Button regButton,logButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_register);

        auth = FirebaseAuth.getInstance();

        regEmail = findViewById(R.id.appRegisterEmail);
        regPassword = findViewById(R.id.appRegisterPassword);
        regConfPassword = findViewById(R.id.appConfPassword);
        regButton = findViewById(R.id.appRegisterButton);
        logButton = findViewById(R.id.appLoginButton);

        regButton.setOnClickListener(this);
        logButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == regButton){
            registerUser();
        }else if(view == logButton){
            startActivity(new Intent(getApplicationContext(), AppLoginActivity.class));
        }
    }

    private void registerUser() {
        //define the progress bar to show when registering
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        String email = regEmail.getText().toString().trim();
        String password = regPassword.getText().toString().trim();
        String confPass = regConfPassword.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() && TextUtils.isEmpty(email)){
            Toast.makeText(AppRegisterActivity.this, "Email format incorrect", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(AppRegisterActivity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
        }
        if(password.length() < 8){
            Toast.makeText(AppRegisterActivity.this, "Password is too Short, enter minimum 8 Characters!", Toast.LENGTH_SHORT).show();
        }

        if (!password.equals(confPass)){
            Toast.makeText(AppRegisterActivity.this, "Password doesn't match!", Toast.LENGTH_SHORT).show();
        }

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(AppRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(AppRegisterActivity.this, "Success:onComplete"+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        //if user fails send message otherwise handled with the listener
                        if(!task.isSuccessful())
                            Toast.makeText(AppRegisterActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        else
                            startActivity(new Intent(getApplicationContext(), AppLoginActivity.class));
                    }
                });
    }
}
