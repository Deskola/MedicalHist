package com.example.medicalhist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medicalhist.api.APIService;
import com.example.medicalhist.api.ApiUrl;
import com.example.medicalhist.model.Hospital;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenAuth extends AppCompatActivity implements View.OnClickListener {
    EditText authUser;
    Button authUserBtn;
    TextView responseTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_auth);

        authUser = findViewById(R.id.auth_token);
        authUserBtn = findViewById(R.id.continueAuthBtn);
        responseTv = findViewById(R.id.responseTv);


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

        int id = Integer.parseInt(authUser.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<List<Hospital>> call = apiService.getHospitals(id);
        call.enqueue(new Callback<List<Hospital>>() {
            @Override
            public void onResponse(Call<List<Hospital>> call, Response<List<Hospital>> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()){
                    responseTv.setText("Body"+response.errorBody());
                    return;
                }

                List<Hospital> hospitals = response.body();
                Intent intent = new Intent(TokenAuth.this, HospitalsVisitedActivity.class);
                Bundle bundle = new Bundle();
                for(Hospital hospital : hospitals){
//                    String content = "";
//                    content +="ID :" + hospital.getHospital_id() +"\n";
//                    content +="Name :" + hospital.getName() +"\n";
//                    content +="Location :" + hospital.getLocation()+"\n";
//                    content +="Logo: " + hospital.getLogo()+"\n";
//                    responseTv.append(content);

                    bundle.putInt("ID",hospital.getHospital_id());
                    bundle.putString("Name",hospital.getName());
                    bundle.putString("Location",hospital.getLocation());
                    bundle.putString("Logo",hospital.getLogo());
                    intent.putExtra("Data",bundle);
                }
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<List<Hospital>> call, Throwable t) {
                progressDialog.dismiss();
                responseTv.setText(t.getMessage());
            }
        });

    }


}
