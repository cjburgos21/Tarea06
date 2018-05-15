package com.example.javier.tarea06;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RestauranteListFrag {
    RecyclerView recyclerView;
    Adaptador restaurantsAdapter;
    List<Restaurante>  restaurants_list;
    onRestaurantSelected mcall;

    public RestauranteListFrag(){

    }



    public interface onRestaurantSelected{

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            mcall = (onRestaurantSelected) context;
        }catch (ClassCastException e){
            throw new ClassCastException((context.toString() + "must implement"));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            restaurantList = savedInstanceState.getParcelableArrayList("restaurantList");
            restaurantsAdapter = savedInstanceState.getParcelable("adapter");
            restaurantsAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            restaurantList = savedInstanceState.getParcelableArrayList("restaurantList");
            restaurantsAdapter = savedInstanceState.getParcelable("adapter");
            restaurantsAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        recyclerView.onSaveInstanceStat`e();

        outState.putParcelableArrayList("restaurantList", (ArrayList<? extends Parcelable>) restaurants_list);
        outState.putParcelable("adapter", restaurantsAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.restaurant_list, container, false);

        if (savedInstanceState != null){
            restaurantsAdapter = savedInstanceState.getParcelable("adapter");
        }else{
            restaurantsAdapter = new Adaptador(getContext(), restaurants_list);
        }

        recyclerView = v.findViewById(R.id.recyclerv);
        recyclerView.setAdapter(restaurantsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        return v;
    }

    public void setList(List<Restaurante> l){
        this.restaurants_list = l;
    }

    public void updateList(List<Restaurante> l){
        if (restaurantsAdapter != null)
            restaurantsAdapter.updateList(l);
    }
}

