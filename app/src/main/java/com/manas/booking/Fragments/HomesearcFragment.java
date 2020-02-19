package com.manas.booking.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.manas.booking.Api;
import com.manas.booking.Profile.MyInfo;
import com.manas.booking.R;
import com.manas.booking.RouteActivity;
import com.manas.booking.Stations;
import com.manas.booking.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomesearcFragment extends Fragment  {


    public HomesearcFragment() {
        // Required empty public constructor
    }

    TextView dateTextView;
    AutoCompleteTextView fromAutoCompleteTextView, toAutoCompleteTextView;
    Button searchBtn;
    String fromStation,toStation;
    CalendarView calendarView;
    //final String[] Stations ={ "АкТалаа", "АкТал", "Акчий", "АкОрго",
      //      "Барскоон", "Бурана","Бухара" };
    int selectedDay, selectedMonth,selectedYear;
    View v;
    Api gsonApi;
    String token;
    ArrayList<Stations> stations= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_homesearc,container,false);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://avtobeket.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        gsonApi = retrofit.create(Api.class);
        token = "6b9b9c17f710630ff74db8c9ad73428bb6ad51b4";

        initial();


        return v;
    }


    public void searchRoute(){
        fromStation = fromAutoCompleteTextView.getText().toString();
        toStation = toAutoCompleteTextView.getText().toString();


        Log.d(TAG, "searchRoute: station name:  " + fromStation +"\ndata: " +  selectedDay
                          + "\nmonth: " + selectedMonth + "\nyear: " + selectedYear);



    }



    public void initial(){


        Call<List<Stations>> call = gsonApi.getStations("Token " + token);
        call.enqueue(new Callback<List<Stations>>() {
            @Override
            public void onResponse(Call<List<Stations>> call, Response<List<Stations>> response) {
                if(response.isSuccessful()){

                    stations = new ArrayList<>(response.body());

                    Log.d(TAG, "onResponse:  succes\n");
                    String stationName[] = new String[stations.size()];
                    for (int i = 0; i < stations.size(); i++)
                    { stationName[i] = stations.get(i).getName();
                        Log.d(TAG, "onResponse: " + stations.get(i).getName());
                    }


                    fromAutoCompleteTextView = v.findViewById(R.id.fromACTV);
                    fromAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, stationName));
                    toAutoCompleteTextView = v.findViewById(R.id.toACTV);
                    toAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, stationName));
                }
                else  Log.d(TAG, "onResponse:  error" );

            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {
                Log.d(TAG, "Failure:  "  + t.getMessage());
            }
        });

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);

        calendarView = v.findViewById(R.id.calendarView);
        dateTextView = v.findViewById(R.id.currentDate);
        searchBtn = v.findViewById(R.id.searchBtn);
        dateTextView.setText("Today\n\n" + todayString);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDay = dayOfMonth;
                selectedMonth = month + 1;
                selectedYear = year;

            }
        });

        Button searchBtn;
        searchBtn =  v.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RouteActivity.class);
                startActivity(i);
            }
        });

    }

}
