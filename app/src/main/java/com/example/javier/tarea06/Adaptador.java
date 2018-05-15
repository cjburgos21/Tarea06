package com.example.javier.tarea06;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.RestaurantViewHolder> implements Parcelable {
    Context mCtx;
    List<Restaurante> restaurantList;

    public Adaptador(Context mCtx, List<Restaurante> restaurantList) {
        this.mCtx = mCtx;
        this.restaurantList = restaurantList;
    }

    protected Adaptador(Parcel in) {
        restaurantList = in.createTypedArrayList(Restaurante.CREATOR);
    }

    public static final Creator<Adaptador> CREATOR = new Creator<Adaptador>() {
        @Override
        public Adaptador createFromParcel(Parcel in) {
            return new Adaptador(in);
        }

        @Override
        public Adaptador[] newArray(int size) {
            return new Adaptador[size];
        }
    };

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mCtx).inflate(R.layout.restaurant_cardview, parent, false);
        RestaurantViewHolder vh = new RestaurantViewHolder(v);
        return vh;
    }

    public void updateList(List<Restaurante> l){
        restaurantList = l;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantViewHolder holder, final int position) {
        final Restaurante restaurant = restaurantList.get(position);

        holder.restaurantTitle.setText(restaurantList.get(position).getNombre());
        holder.favImage.setImageResource(restaurant.isFavorite() ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off);
        holder.favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (restaurant.isFavorite()){
                    holder.favImage.setImageResource(android.R.drawable.btn_star_big_off);
                    restaurantList.get(position).setFavorite(false);
                }else{
                    holder.favImage.setImageResource(android.R.drawable.btn_star_big_on);
                    restaurantList.get(position).setFavorite(true);
                }

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (restaurantList != null)
            return restaurantList.size();
        else
            return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(restaurantList);
    }

    protected class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView restaurantImage, favImage;
        TextView restaurantTitle;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurant_img);
            restaurantTitle = itemView.findViewById(R.id.restaurant_title);
        }
    }
}

