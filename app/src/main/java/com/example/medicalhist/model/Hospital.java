package com.example.medicalhist.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.medicalhist.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hospital {
    @SerializedName("national_id")
    @Expose
    private Integer national_id;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("hospital_id")
    @Expose
    private Integer hospital_id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("logo")
    @Expose
    private String logo;

    public Integer getNational_id() {
        return national_id;
    }

    public void setNational_id(Integer national_id) {
        this.national_id = national_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(Integer hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    //Loading Imgae
    @BindingAdapter({"logo"})
    public static void loadImage(ImageView imageView, String imageURL){
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions().circleCrop())
                .load(imageURL)
                .placeholder(R.drawable.loading)
                .into(imageView);
    }
}
