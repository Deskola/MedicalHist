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

public class AppLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mainLoginBtn,regButton;
    private EditText appUsername,appPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_login);

        //Get firebase instance
        auth = FirebaseAuth.getInstance();

        appUsername = findViewById(R.id.appUsername);
        appPassword = findViewById(R.id.appPassword);
        mainLoginBtn = findViewById(R.id.appLoginButton);
        regButton = findViewById(R.id.appRegisterButton);
        mainLoginBtn.setOnClickListener(this);
        regButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if(view == regButton){
            startActivity(new Intent(getApplicationContext(), AppRegisterActivity.class));
        }else if(view == mainLoginBtn){
            userlogin();
        }
    }

    private void userlogin() {
        //define the progress bar to show when registering
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        String email = appUsername.getText().toString().trim();
        final String password = appPassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            Toast.makeText(AppLoginActivity.this, "Email Format is wrong", Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(email))
            Toast.makeText(AppLoginActivity.this, "Enter Email!", Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(password))
            Toast.makeText(AppLoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();

        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(AppLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if sign in fails display a message
                        if (!task.isSuccessful()){
                            //there was an error
                            if(password.length() < 8)
                                appPassword.setError(getString(R.string.auth_error));
                            else
                                Toast.makeText(AppLoginActivity.this, R.string.auth_error, Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent = new Intent(getApplicationContext(), TokenAuth.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
