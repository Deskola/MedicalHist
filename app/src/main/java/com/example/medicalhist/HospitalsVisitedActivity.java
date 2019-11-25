package com.example.medicalhist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.medicalhist.model.Hospital;
import com.example.medicalhist.model.HospitalList;
import com.example.medicalhist.model.NotesDatabase;
import com.example.medicalhist.recycler.HospitalAdapter;
import com.example.medicalhist.tabs.TabsAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class HospitalsVisitedActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabLayout;
    //This is our viewPager
    private ViewPager viewPager;
    public static NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_visited);

        notesDatabase = Room.databaseBuilder(getApplicationContext(),NotesDatabase.class,"notedb").allowMainThreadQueries().build();
        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_person_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_familyt));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_treatment_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_medication_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_idata));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

        //find item

        //getallpatientsInformation
        getResponse();


    }

    private void getResponse() {

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
