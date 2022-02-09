package com.example.myapplication.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.common.Resource;
import com.example.myapplication.data.models.MainModels;
import com.example.myapplication.data.remode.WeatherApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositories {
    private WeatherApi api;
    private String city;

    public void setCity(String city) {
        this.city = city;
    }

    @Inject
    public MainRepositories(WeatherApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<MainModels>> getCharacters() {
        MutableLiveData<Resource<MainModels>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getApi(city,"d740eee78a96b3a9edde5c83d5d0f337","metric").enqueue(new Callback<MainModels>() {
            @Override
            public void onResponse(Call<MainModels> call, Response<MainModels> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<MainModels> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }
}
