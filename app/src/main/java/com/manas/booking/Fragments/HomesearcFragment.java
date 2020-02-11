package com.manas.booking.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manas.booking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomesearcFragment extends Fragment {


    public HomesearcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homesearc, container, false);
    }

}
