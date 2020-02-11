package com.manas.booking.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.manas.booking.Api;
import com.manas.booking.Profile.ChangePasswordActivity;
import com.manas.booking.Profile.EditProfileActivity;
import com.manas.booking.Profile.MyInfo;
import com.manas.booking.R;
import com.manas.booking.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  =  inflater.inflate(R.layout.fragment_profile, container, false);


        Button MyInfoBtn, changePass;
                MyInfoBtn =  v.findViewById(R.id.MyInfoBtn);

        MyInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MyInfo.class);
                startActivity(i);
            }
        });

        changePass = v.findViewById(R.id.changePassBtn);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  ChangePasswordActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

}
/*
<LinearLayout
        android:id="@+id/linearLayout1"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:layout_marginTop="30dp"
        android:paddingStart="15dp"
        android:layout_below="@id/fullnameTextView"
        android:orientation="horizontal">

        <TextView
            android:id="@+id/RidesTextView"
            android:layout_width="250dp"
            android:layout_height="45dp"
            android:paddingTop="10dp"
            android:textSize="17sp"
            android:textStyle="normal"
            android:textColor="#000"
            android:drawableLeft="@drawable/ic_bus"
            android:text="Предыдущие поездки"
            >
        </TextView>

        <ImageView
            android:layout_width="20dp"
            android:layout_height="20dp"
            android:layout_marginTop="12dp"
            android:layout_marginStart="50dp"
            android:src="@drawable/right_chevron"
            >
        </ImageView>

    </LinearLayout>



 */