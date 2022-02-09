package com.example.myapplication.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.myapplication.R;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {
    /*protected NavController controller;*/
    protected VB viewBinding;
    protected abstract VB bind();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = bind();
        /*controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);*/
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        setupObservers();
        setListeners();
        callRequests();
    }

    protected abstract void setupObservers();

    protected abstract void setListeners();


    protected abstract void callRequests();


    protected abstract void setupViews();
}
