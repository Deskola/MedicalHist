package com.example.medicalhist.recycler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalhist.HospitalsVisitedActivity;
import com.example.medicalhist.R;
import com.example.medicalhist.api.APIService;
import com.example.medicalhist.api.ApiUrl;
import com.example.medicalhist.model.Hospital;
import com.example.medicalhist.model.Result;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder> implements View.OnClickListener {
    List<Hospital> hospitalList;
    Context context;

    public HospitalAdapter(List<Hospital> hospitalList, Context context) {
        this.hospitalList = hospitalList;
        this.context = context;
    }

    @NonNull
    @Override
    public HospitalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospitals_card, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HospitalAdapter.MyViewHolder holder, int position) {
        final Hospital hospital = hospitalList.get(position);
        Picasso.get().load(hospital.getLogo()).into(holder.hospitalLogo, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.mName.setText(hospital.getName());
        holder.mLocation.setText(hospital.getLocation());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nationalID = hospital.getNational_id();
                String password = hospital.getPassword();
                int hospitalID = hospital.getHospital_id();

                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiUrl.BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                APIService apiService = retrofit.create(APIService.class);

                Call<Result> call = apiService.userLogin(nationalID,password,hospitalID);
                call.enqueue(new retrofit2.Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (!response.isSuccessful()){
                            holder.errorField.setText("Here: "+response.errorBody().toString());
                        }
                        Result result = response.body();
                        holder.errorField.setText(result.getMessage());
                        //Log.e("Res: ",response.body().toString());
                        Intent intent = new Intent(context, HospitalsVisitedActivity.class);
                        intent.putExtra("HospID",hospital.getHospital_id().toString());
                        intent.putExtra("ID",hospital.getNational_id().toString());
                        intent.putExtra("Password",hospital.getPassword());
                        intent.putExtra("Logo",hospital.getLogo());
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        holder.errorField.setText("Error"+t.getMessage());

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView hospitalLogo;
        TextView mName,mLocation,errorField;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalLogo = itemView.findViewById(R.id.hospital_image);
            mName = itemView.findViewById(R.id.hospital_name);
            mLocation = itemView.findViewById(R.id.hospital_location);
            cardView = itemView.findViewById(R.id.hospital_cardview_id);
            errorField = itemView.findViewById(R.id.errorField);
        }
    }
}
