package com.example.daggerdemo.foody.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerdemo.R;
import com.example.daggerdemo.foody.FoodyApplication;
import com.example.daggerdemo.foody.adapter.RestaurantAdapter;
import com.example.daggerdemo.foody.data.model.RestaurantsItem;
import com.example.daggerdemo.foody.di.component.DaggerHomeFragmentComponent;
import com.example.daggerdemo.foody.di.component.HomeFragmentComponent;
import com.example.daggerdemo.foody.di.module.HomeFragmentModule;
import com.example.daggerdemo.foody.di.module.RestaurantRepository;
import com.example.daggerdemo.foody.ui.main.RestaurantViewModel;
import com.example.daggerdemo.foody.util.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Rajat Sangrame on 30/5/20.
 * http://github.com/rajatsangrame
 */
public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    @Inject
    RestaurantAdapter adapter;
    @Inject
    RestaurantRepository restaurantRepository;
    @Inject
    ViewModelFactory factory;
    private RestaurantViewModel restaurantViewModel;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getDependencies();
        super.onCreate(savedInstanceState);
    }

    private void getDependencies() {
        HomeFragmentComponent component = DaggerHomeFragmentComponent
                .builder()
                .foodyComponent(FoodyApplication.get(getActivity()).getFoodyComponent())
                .homeFragmentModule(new HomeFragmentModule(this))
                .build();
        component.Fragment(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        restaurantViewModel = new ViewModelProvider(this, factory).get(RestaurantViewModel.class);
        restaurantViewModel.getAllRestaurants().observe(getViewLifecycleOwner(), new Observer<List<RestaurantsItem>>() {
            @Override
            public void onChanged(List<RestaurantsItem> restaurantsItems) {
                adapter.setRestaurants(restaurantsItems);
            }
        });
        return view;
    }
}
