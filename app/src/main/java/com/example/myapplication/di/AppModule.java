package com.example.myapplication.di;

import com.example.myapplication.data.remode.RetrofitClient;
import com.example.myapplication.data.remode.WeatherApi;
import com.example.myapplication.data.repositories.MainRepositories;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

@Provides
public static WeatherApi providesApi(){
    return new RetrofitClient().provideApi();
}

    @Provides
    public static MainRepositories providesMainRepositories(WeatherApi api){
        return new MainRepositories(api);
    }
}
