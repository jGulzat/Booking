package com.manas.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.manas.booking.Model.Route;
import com.manas.booking.Model.Station;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RouteActivity extends AppCompatActivity {

    RecyclerView routesRecycleView;
    RoutesAdapter routesAdapter;
    Api gsonApi;
    String token;
    ArrayList<Route> routes= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://avtobeket.herokuapp.com/")
                .build();

        gsonApi = retrofit.create(Api.class);
        token = "6b9b9c17f710630ff74db8c9ad73428bb6ad51b4";

        initial();

    }

    public void initial(){
        routesRecycleView = findViewById(R.id.routeRecycleView);
        routesRecycleView.setLayoutManager(new LinearLayoutManager(this));

        Station station = new Station("2","1","2020-02-12");
        Call<List<Route>> call = gsonApi.getRoute("Token " + token, station);
        call.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                if(response.isSuccessful()){
                    routes = new ArrayList<>(response.body());

                    routesAdapter = new RoutesAdapter(RouteActivity.this,routes);
                    routesRecycleView.setAdapter(routesAdapter);
                    Log.d(TAG, "onResponse:  succes\n");

                }
                else Log.d("Station:  ", "error " + response.code());
            }

            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {
                Log.d("Station:  ", "Failure: " + t.getMessage());
            }
        });

    }
}
