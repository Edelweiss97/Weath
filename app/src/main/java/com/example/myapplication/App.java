package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.data.remode.WeatherApi;
import com.example.myapplication.data.remode.RetrofitClient;
import com.example.myapplication.data.repositories.MainRepositories;

import dagger.hilt.android.HiltAndroidApp;


@HiltAndroidApp
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
