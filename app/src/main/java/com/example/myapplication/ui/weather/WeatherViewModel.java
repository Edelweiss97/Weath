package com.example.myapplication.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.App;
import com.example.myapplication.common.Resource;
import com.example.myapplication.data.models.Main;
import com.example.myapplication.data.models.MainModels;
import com.example.myapplication.data.repositories.MainRepositories;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.http.PUT;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private MainRepositories repositories;
    private String city;
    public LiveData<Resource<MainModels>> weatherLiveData;

    public void setCity(String city) {
        this.city = city;
    }

    @Inject
    public WeatherViewModel(MainRepositories repositories) {
        this.repositories = repositories;
    }

    public void getWeather() {
        repositories.setCity(city);
        weatherLiveData = repositories.getCharacters();
    }
}
