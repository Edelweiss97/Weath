package com.example.myapplication.ui.weather;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.common.Resource;
import com.example.myapplication.data.models.Main;
import com.example.myapplication.data.models.MainModels;
import com.example.myapplication.databinding.FragmentWeatherBinding;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Response;
@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {
    private WeatherViewModel viewModel;
    private WeatherFragmentArgs args;
    private NavController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            args = WeatherFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void setupObservers() {
        viewModel.weatherLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainModels>>() {
            @Override
            public void onChanged(Resource<MainModels> resource) {
                switch (resource.status) {
                    case SUCCESS:
                        setData(resource.data);
                        viewBinding.progress.setVisibility(View.GONE);
                        break;
                    case LOADING:
                        viewBinding.progress.setVisibility(View.VISIBLE);
                        break;

                    case ERROR:
                        viewBinding.progress.setVisibility(View.GONE);
                        Toast.makeText(requireActivity(), resource.msg, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

    }

    private void setData(MainModels models){
        String location = models.getName();
        String urlImg = "https://openweathermap.org/img/wn/" + models.getWeather().get(0).getIcon() + ".png";
        String maxTemp = Math.round(models.getMain().getTempMax()) + "C";
        String wind = String.valueOf((int) Math.round(models.getWind().getSpeed()));
        String minTemp = String.valueOf((int) Math.round(models.getMain().getTempMin()));
        String humidity = String.valueOf(models.getMain().getHumidity());
        String barometr = String.valueOf(models.getMain().getPressure());
        String mainWeather = models.getWeather().get(0).getMain();
        String tempNow = String.valueOf((int) Math.round(models.getMain().getTemp()));

        viewBinding.btnMap.setText(location);
        Glide.with(requireActivity()).load(urlImg).into(viewBinding.imageCloud);
        viewBinding.tempMax.setText(maxTemp);
        viewBinding.tempMin.setText(minTemp);
        viewBinding.txtWind.setText(wind);
        viewBinding.txtWindPercent.setText(humidity);
        viewBinding.bigTemp.setText(tempNow);
        viewBinding.txtPressure.setText(barometr);
        viewBinding.txtCloud.setText(mainWeather);

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void callRequests() {

    }

    @Override
    protected void setupViews() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        viewModel.setCity(args.getCity());
        viewModel.getWeather();
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        viewBinding.btnMap.setOnClickListener(v -> {
            controller.navigate(R.id.action_weatherFragment_to_searchFragment2);
        });

    }
}