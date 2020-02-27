package com.manas.booking.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.manas.booking.LoginActivity;
import com.manas.booking.Profile.ChangePasswordActivity;
import com.manas.booking.Profile.MyInfo;
import com.manas.booking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    String token;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  =  inflater.inflate(R.layout.fragment_profile, container, false);
        Bundle bundle = getArguments();
        token = bundle.getString("token").trim();

        Button MyInfoBtn, changePass;
                MyInfoBtn =  v.findViewById(R.id.MyInfoBtn);

        MyInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MyInfo.class);
                i.putExtra("token",token);
                startActivity(i);
            }
        });

        changePass = v.findViewById(R.id.changePassBtn);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  ChangePasswordActivity.class);
                i.putExtra("token",token);
                startActivity(i);
            }
        });
        return v;
    }
}

/*
* <?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context=".Fragments.HomesearcFragment"
    android:orientation="vertical">

    <LinearLayout
        android:id="@+id/linearLayoutStation"
        android:layout_width="370dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:layout_marginStart="20dp"
        android:layout_marginEnd="20dp"
        android:background="@drawable/shadow"
        android:elevation="30dp"
        android:orientation="horizontal"
        >
        <LinearLayout
            android:layout_width="240dp"
            android:layout_height="180dp"
            android:orientation="vertical"
            android:background="@drawable/shadow">

            <TextView
                android:layout_width="35dp"
                android:layout_height="15dp"
                android:layout_marginStart="15dp"
                android:text="From"
                android:textSize="15sp"
                android:layout_marginTop="3dp"
                android:textColor="#FF3F3D3D"
                >
            </TextView>

            <AutoCompleteTextView
                android:id="@+id/fromACTV"
                android:drawableLeft="@drawable/ic_from_to"
                android:layout_width="200dp"
                android:layout_height="65dp"
                android:layout_marginTop="3dp"
                android:background="@color/white"
                android:textSize="20sp"
                android:layout_marginStart="8dp">

            </AutoCompleteTextView>
            <View
                android:layout_width="225dp"
                android:layout_height="1dp"
                android:layout_marginTop="3dp"
                android:layout_marginStart="18dp"
                android:background="#FF3F3D3D" />

            <TextView
                android:layout_width="35dp"
                android:layout_height="15dp"
                android:layout_marginStart="18dp"
                android:text="To"
                android:textSize="15sp"
                android:layout_marginTop="5dp"
                android:textColor="#FF3F3D3D">

            </TextView>

            <AutoCompleteTextView
                android:id="@+id/toACTV"
                android:drawableLeft="@drawable/ic_from_to"
                android:layout_width="200dp"
                android:layout_height="65dp"
                android:layout_marginStart="8dp"
                android:textSize="20sp"
                android:background="@color/white">

            </AutoCompleteTextView>

        </LinearLayout>

        <LinearLayout
            android:layout_width="119dp"
            android:layout_height="180dp"
            android:layout_marginEnd="15dp">

            <ImageView
                android:layout_width="60dp"
                android:layout_height="70dp"
                android:layout_marginStart="5dp"
                android:layout_marginTop="45dp"
                android:src="@drawable/ic_arrows" />
        </LinearLayout>

    </LinearLayout>

    <LinearLayout
        android:id="@+id/linearLayoutCalendar"
        android:layout_width="370dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="40dp"
        android:layout_marginStart="20dp"
        android:layout_marginEnd="20dp"
        android:background="@drawable/shadow"
        android:elevation="30dp"
        android:layout_below="@id/linearLayoutStation"
        android:orientation="horizontal"
        >
    <LinearLayout
        android:layout_width="230dp"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:background="@drawable/shadow">

        <TextView
            android:layout_width="100dp"
            android:layout_height="20dp"
            android:text="Дата поездки"
            android:textSize="15sp"
            android:textColor="#000000"
            android:layout_marginStart="20dp"
            android:layout_marginTop="5dp">

        </TextView>

        <ScrollView
            android:layout_width="wrap_content"
            android:layout_height="180dp">

            <CalendarView
                android:id="@+id/calendarView"
                android:layout_width="200dp"
                android:layout_height="180dp">
            </CalendarView>

        </ScrollView>

    </LinearLayout>

        <LinearLayout
            android:layout_width="120dp"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            >

            <TextView
                android:id="@+id/currentDate"
                android:layout_width="120dp"
                android:layout_height="80dp"
                android:layout_marginTop="65dp"
                android:textColor="#000000"
                android:textSize="17sp">
            </TextView>
        </LinearLayout>

    </LinearLayout>


    <Button
        android:id="@+id/searchBtn"
        android:layout_width="300dp"
        android:layout_height="50dp"
        android:layout_marginTop="20dp"
        android:layout_marginStart="35dp"
        android:textSize="20sp"
        android:textStyle="bold"
        android:textAlignment="center"
        android:text="Искать"
        android:textColor="@color/white"
        android:background="@drawable/round_button"
        android:layout_below="@id/linearLayoutCalendar"
        >
    </Button>

</RelativeLayout>
*
*
*
*
*
* */