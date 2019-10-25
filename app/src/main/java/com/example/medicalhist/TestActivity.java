package com.example.medicalhist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TestActivity extends AppCompatActivity {
    ImageView hospitalLogo;
    TextView mName,mLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        hospitalLogo = findViewById(R.id.hospital_image);
        mName = findViewById(R.id.hospital_name);
        mLocation = findViewById(R.id.hospital_location);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Name");
        String location = bundle.getString("Location");
        String imageURL = bundle.getString("Logo");

        mName.setText(name);
        mLocation.setText(location);
        Picasso.get().load(imageURL).into(hospitalLogo);

    }
}
