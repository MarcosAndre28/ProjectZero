package com.example.projectzero.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectzero.ProjectZeroApplication;
import com.example.projectzero.R;
import com.example.projectzero.db.AppDatabase;


public class SplashScreenFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        final Handler handler = new Handler();
        handler.postDelayed(() -> Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_loginFragment), 5000);
        return view;
    }
}