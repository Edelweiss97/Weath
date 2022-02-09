package com.example.myapplication.ui.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentSearchBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    private NavController controller;

    @Override
    protected FragmentSearchBinding bind() {
        return FragmentSearchBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void callRequests() {

    }

    @Override
    protected void setupViews() {
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        viewBinding.btnPut.setOnClickListener(v -> {
            controller.navigate((NavDirections) SearchFragmentDirections
                    .actionSearchFragment2ToWeatherFragment(viewBinding.input.getText().toString()));
        });
    }
}