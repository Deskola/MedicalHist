package com.example.medicalhist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalhist.api.APIService;
import com.example.medicalhist.api.ApiUrl;
import com.example.medicalhist.model.Hospital;
import com.example.medicalhist.model.HospitalList;
import com.example.medicalhist.model.Result;
import com.example.medicalhist.recycler.HospitalAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenAuth extends AppCompatActivity implements View.OnClickListener {
    EditText authUser;
    Button authUserBtn;
    TextView responseTv;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_auth);

        authUser = findViewById(R.id.auth_token);
        authUserBtn = findViewById(R.id.continueAuthBtn);
        responseTv = findViewById(R.id.responseTv);
        //recyclerView = findViewById(R.id.recyclerViewHospitals);


        authUserBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == authUserBtn){
            authenticatePatiet();
            //testMethodt();
        }
    }

    private void testMethodt() {
        startActivity(new Intent(TokenAuth.this, HospitalsVisitedActivity.class));
    }


    private void authenticatePatiet() {
        //display progress dialog during authentication
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        Intent intent = getIntent();

        final int id = Integer.parseInt(authUser.getText().toString());
        final String email = intent.getExtras().getString("Email");

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<Result> call = apiService.authUer(id,email);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()){
                    Toast.makeText(TokenAuth.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
//                List<Hospital> hospitalList = response.body();
//                HospitalAdapter adapter = new HospitalAdapter(hospitalList,getApplication());
//                recyclerView.setAdapter(adapter);
                Result result = response.body();
                String msg = result.getMessage();
                Intent intent1 = new Intent(TokenAuth.this,HomeActivity.class);
                intent1.putExtra("ID",id);
                intent1.putExtra("Email",email);
                Toast.makeText(TokenAuth.this,msg,Toast.LENGTH_SHORT).show();
                getApplication().startActivity(intent1);
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TokenAuth.this,"Invalid Token", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
