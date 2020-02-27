package com.manas.booking.Fragments;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.manas.booking.Api;
import com.manas.booking.Model.Stations;
import com.manas.booking.R;
import com.manas.booking.RouteActivity;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomesearcFragment extends Fragment {


    public HomesearcFragment() {
        // Required empty public constructor
    }
    Date today;
    Calendar nextYear;
    EditText date1Edittext, date2Edittext;
    TextView dateTextView;
    AutoCompleteTextView fromAutoCompleteTextView, toAutoCompleteTextView;
    Button searchBtn;
    String fromStation,toStation;
    int selectedDay, selectedMonth,selectedYear;
    View v;
    Api gsonApi;
    String token;
    CalendarPickerView calendar_view;
    ArrayList<Stations> stations= new ArrayList<>();
    RadioGroup rg;
    RelativeLayout relativeLayoutcpv;
    RadioButton radioButton1, radioButton2;
    List<Date>selecteddates;
    CalendarPickerView.SelectionMode selectionMode;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_homesearc,container,false);

        Bundle bundle = getArguments();
        token = bundle.getString("token").trim();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://avtobeket.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        gsonApi = retrofit.create(Api.class);

        initial();
        fillListStation();

        return v;
    }

    public void initial(){

        rg = v.findViewById(R.id.radiogroup);
        relativeLayoutcpv = v.findViewById(R.id.relativelayoutCPV);
        radioButton1 = v.findViewById(R.id.radiobtn_1);
        radioButton2 = v.findViewById(R.id.radiobtn_2);
        date1Edittext = v.findViewById(R.id.date1EdT);
        date2Edittext = v.findViewById(R.id.date2EdT);
        dateTextView = v.findViewById(R.id.currentDate);
        fromAutoCompleteTextView = v.findViewById(R.id.fromACTV);
        toAutoCompleteTextView = v.findViewById(R.id.toACTV);
        searchBtn = v.findViewById(R.id.searchBtn);
        dateTextView = v.findViewById(R.id.currentDate);
        calendar_view = v.findViewById(R.id.calendar_view1);

        nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        today = new Date();
        calendar_view.init(today, nextYear.getTime());
        selecteddates = calendar_view.getSelectedDates();




        Date todayDate = Calendar.getInstance().getTime();
        String todayString = formatter.format(todayDate);
        dateTextView.setText("Today\n" + todayString);

        if(radioButton1.isChecked()) OnRadioButton1Clicked();
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButton1.isChecked()) OnRadioButton1Clicked();
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButton2.isChecked()) OnRadioButton2Clicked();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RouteActivity.class);
                startActivity(i);
            }
        });
    }

    public void fillListStation(){
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
                    fromAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, stationName));
                    toAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, stationName));
                }
                else  Log.d(TAG, "onResponse:  error" );

            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {
                Log.d(TAG, "Failure: homeSearch:   "  + t.getMessage());
            }
        });
    }

    public void OnRadioButton1Clicked(){
        Log.d(TAG, "OnRadioButton1Clicked: " + "worked");
        date2Edittext.setVisibility(View.GONE);
        date1Edittext.getText().clear();
        calendar_view.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.SINGLE);

        calendar_view.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                String date1 = formatter.format(calendar_view.getSelectedDate());
                date1Edittext.setText(date1);
                Log.d(TAG, "onDateSelected: " + date1);
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }

    public void OnRadioButton2Clicked(){
        Log.d(TAG, "OnRadioButton2Clicked: " + "worked");
        date1Edittext.getText().clear();
        date2Edittext.getText().clear();
        date2Edittext.setVisibility(View.VISIBLE);
        calendar_view.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE);


        calendar_view.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                selecteddates = calendar_view.getSelectedDates();
                if(selecteddates.size() >= 2){
                    String date1 = formatter.format(selecteddates.get(0));
                    String date2 = formatter.format(selecteddates.get(selecteddates.size()-1));
                    Log.d(TAG, "onDateSelected: ssss" + selecteddates + "\ns0:   " + date2);
                    date1Edittext.setText(date1);
                    date2Edittext.setText(date2);
                }

            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });


    }

    public void searchRoute(){
        fromStation = fromAutoCompleteTextView.getText().toString();
        toStation = toAutoCompleteTextView.getText().toString();

        Log.d(TAG, "searchRoute: station name:  " + fromStation +"\ndata: " +  selectedDay
                + "\nmonth: " + selectedMonth + "\nyear: " + selectedYear);
    }
}

/*

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
                Log.d(TAG, "Failure: homeSearch:   "  + t.getMessage());
            }
        });

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);
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

 */