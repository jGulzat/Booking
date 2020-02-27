package com.manas.booking.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.manas.booking.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent getIntent = getIntent();
        final String token = getIntent.getStringExtra("token");

        final Bundle b = new Bundle();
        b.putString("token", token);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.home1){
                    HomesearcFragment fragment = new HomesearcFragment();

                    fragment.setArguments(b);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.search_frame,fragment);
                    fragmentTransaction.commit();
                }

                if(id == R.id.profile){
                    ProfileFragment fragment = new ProfileFragment();

                    fragment.setArguments(b);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.search_frame,fragment);
                    fragmentTransaction.commit();
                }

                if(id == R.id.help){
                    HelpFragment fragment = new HelpFragment();

                    fragment.setArguments(b);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.search_frame,fragment);
                    fragmentTransaction.commit();
                }

                if(id == R.id.map){
                    MapFragment fragment = new MapFragment();

                    fragment.setArguments(b);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.search_frame,fragment);
                    fragmentTransaction.commit();
                }

                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.home1);
    }

}
