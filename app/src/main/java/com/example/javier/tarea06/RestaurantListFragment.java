package com.example.javier.tarea06;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RestaurantListFragment extends Fragment{
    RecyclerView recyclerView;
    RestaurantsAdapter restauransAdapter;
    List<Restaurant> restaurantList;

    @Nullable
    @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.restaurant_list,container,false);

        public RestaurantListFragment(){
        }

        restauransAdapter = new RestaurantsAdapter(getContext(),restaurantList);


        recyclerView = v.findViewById(R.id.recyclerv);
        recyclerView.setAdapter(restauransAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        return v;

    }

}