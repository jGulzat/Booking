package com.manas.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.manas.booking.Fragments.SearchActivity;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Preferences prefs = Preferences.userNodeForPackage(com.manas.booking.LoginActivity.class);

                if (prefs.get("isLogged", "") == "true") {
                    final Intent mainIntent = new Intent(MainActivity.this, SearchActivity.class);
                    MainActivity.this.startActivity(mainIntent);
                    MainActivity.this.finish();
                } else {
                    final Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(mainIntent);
                    MainActivity.this.finish();
                }
            }
        }, 1000);

    }
}
