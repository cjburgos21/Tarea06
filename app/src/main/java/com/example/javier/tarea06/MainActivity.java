package com.example.javier.tarea06;


import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RestaurantListFragment.onRestaurantSelected{
    Toolbar tools;
    TabLayout tabLayout;
    ViewPager vpager;
    RestaurantPagerAdapter pagerAdapter;
    List<Restaurante> restaurantList;
    RestaurantListFragment restaurantFragment,favoriteFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("restaurantlist", (ArrayList<? extends Parcelable>) restaurantList);

        if(restaurantFragment.isAdded())
            getSupportFragmentManager().putFragment(outState, "restaurantfrag",restaurantFragment);

        if(favoriteFragment.isAdded())
            getSupportFragmentManager().putFragment(outState, "favoritefrag", favoriteFragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null || !savedInstanceState.containsKey("instance")){


            restaurantList = fillRestaurants();

            restaurantFragment = new RestaurantListFragment();
            favoriteFragment = new RestaurantListFragment();

            restaurantFragment.setList(restaurantList);
            favoriteFragment.setList(restaurantList);
        }
        else{
            restaurantList = savedInstanceState.getParcelableArrayList("instance");
            restaurantFragment = (RestaurantListFragment) getSupportFragmentManager().getFragment(savedInstanceState,"restaurantfrag");
            favoriteFragment = (RestaurantListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "favoritefrag")
        }

        tools = findViewById(R.id.herramientas);
        tools.setTitle(R.string.app_name);
        setSupportActionBar(tools);

        pagerAdapter = new RestaurantAdapter(getSupportFragmentManager(),this);
        pagerAdapter.addItem("Restaurantes",restaurantFragment);
        pagerAdapter.addItem("Favoritos",favoriteFragment);

        vpager = findViewById(R.id.vpager);
        vpager.setAdapter(pagerAdapter);
        vpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                           ;
                                           @Override
                                           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                               List<Restaurante> newList = position == 0 ? restaurantList : favRestaurants(restaurantList);
                                               RestaurantListFragment listFragment = ((RestaurantListFragment) pageAdapter.getItem(position));
                                               if (listFragment != null)
                                                   listFragment.updateList(newList);

                                           }

                                           @Override
                                           public void onPageSelected(int position) {

                                           }

                                           @Override
                                           public void onPageScrollstatechange(int state) {

                                           }

                                       });


                tabLayout = findViewById(R.id.tablayout);
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                tabLayout.setupWithViewPager(vpager,true);


    }

    private ArrayList<Restaurante> fillRestaurants(){
        ArrayList<Restaurante> l = new ArrayList<>();
        l.add(new Restaurante(1, "Pollo Campero", 2,false));
        l.add(new Restante(1, "Dinasty", 4, false));
        l.add(new Restante(1, "Wendy's", 3, false));
        l.add(new Restante(1, "Tipicos Margoth", 3, false));
        l.add(new Restante(1, "Dominos Pizza", 2, false));

        return l;
    }

    private ArrayList<Restaurante> favRestaurants(List<Restaurante> restaurantes){
        ArrayList<Restaurante> favs = new ArrayList<>();
        for (Restaurante restaurante : restaurantes){
            if(restaurante.isFavorite()) favs.add(restaurante);
        }

        return favs;

    }

}
