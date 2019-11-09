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
        recyclerView = findViewById(R.id.recyclerViewHospitals);


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

        int id = Integer.parseInt(authUser.getText().toString());
        String email = intent.getExtras().getString("Email");

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<List<Hospital>> call = apiService.getHospitals(id,email);
        call.enqueue(new Callback<List<Hospital>>() {
            @Override
            public void onResponse(Call<List<Hospital>> call, Response<List<Hospital>> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()){
                    Toast.makeText(TokenAuth.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
                List<Hospital> hospitalList = response.body();
                HospitalAdapter adapter = new HospitalAdapter(hospitalList,getApplication());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Hospital>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TokenAuth.this,"Invalid National Number", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
