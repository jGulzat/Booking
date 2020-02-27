package com.manas.booking.Fragments;


import android.os.Bundle;

import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.manas.booking.Api;
import com.manas.booking.Model.Stations;
import com.manas.booking.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGooglemap;
    MapView mapView;
    View mView;
    ArrayList<Stations> stations = new ArrayList<>();
    AutoCompleteTextView fromAutoCompleteTextView, toAutoCompleteTextView;
    Button routeBtn;
    Api gsonApi;
    String token;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://avtobeket.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        gsonApi = retrofit.create(Api.class);
        token = "6b9b9c17f710630ff74db8c9ad73428bb6ad51b4";
      //  initial();
        return mView;
    }

  /*  public void initial(){
        Call<List<Stations>> call = gsonApi.getStations("Token " + token);
        call.enqueue(new Callback<List<Stations>>() {
            @Override
            public void onResponse(Call<List<Stations>> call, Response<List<Stations>> response) {
                if(response.isSuccessful()){

                    stations = new ArrayList<>(response.body());

                    Log.d(Constraints.TAG, "onResponse:  succes\n");
                    String stationName[] = new String[stations.size()];
                    for (int i = 0; i < stations.size(); i++)
                    { stationName[i] = stations.get(i).getName();
                        Log.d(Constraints.TAG, "onResponse: " + stations.get(i).getName());
                    }


                    fromAutoCompleteTextView = mView.findViewById(R.id.fromACTV);
                    fromAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, stationName));
                    toAutoCompleteTextView = mView.findViewById(R.id.toACTV);
                    toAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, stationName));
                }
                else  Log.d(Constraints.TAG, "onResponse:  error" );

            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {
                Log.d(Constraints.TAG, "Failure:  "  + t.getMessage());
            }
        });

    }*/

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView =  mView.findViewById(R.id.map);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        stations.add(new Stations(1,"Bishkek",41.21968725,74.97738709));
        stations.add(new Stations(1,"Bishkek",42.8799039,74.58589061));
        stations.add(new Stations(1,"Bishkek",42.5829746,71.8293567));
        stations.add(new Stations(1,"Bishkek", 42.4916808,78.3901729));


        MapsInitializer.initialize(getContext());
        mGooglemap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //GoogleMap.setMaxZoomPreference(14.0f);


        for(int i = 0 ; i < stations.size() ; i++) {


            Log.d(TAG, "onMapReady: " + stations.get(i).getName() + "\n" + stations.get(i).getLat());
            googleMap.addMarker(new MarkerOptions().position(new LatLng(stations.get(i).getLat(),stations.get(i).getLng()))
                    .title(stations.get(i).getName()).snippet("1"));
        }

        CameraPosition first = CameraPosition.builder().target(new LatLng(41.21968725,74.97738709))
                .zoom(6).bearing(2).tilt(30).build();
        float zoomLevel = 6.0f; //This goes up to 21
        LatLng latLng = new LatLng(41.21968725, 74.97738709);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));

        //googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(first));

        



    }
}
