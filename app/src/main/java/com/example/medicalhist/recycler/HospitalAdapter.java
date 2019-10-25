package com.example.medicalhist.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalhist.R;
import com.example.medicalhist.model.Hospital;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder> {
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
    public void onBindViewHolder(@NonNull HospitalAdapter.MyViewHolder holder, int position) {
        Hospital hospital = hospitalList.get(position);
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

    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView hospitalLogo;
        TextView mName,mLocation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalLogo = itemView.findViewById(R.id.hospital_image);
            mName = itemView.findViewById(R.id.hospital_name);
            mLocation = itemView.findViewById(R.id.hospital_location);
        }
    }
}
