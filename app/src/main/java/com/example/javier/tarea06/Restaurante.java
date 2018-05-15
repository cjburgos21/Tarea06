package com.example.javier.tarea06;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurante implements Parcelable {
    private int id;
    private String nombre;
    private float rating;
    private boolean favorito;


    public Restaurante(int id, String nombre, float rating, boolean favorito) {
        this.id = id;
        this.nombre = nombre;
        this.rating = rating;
        this.favorito = favorito;
    }

    protected Restaurante(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        rating = in.readFloat();
        favorito = in.readByte() != 0;
    }

    public static final Creator<Restaurante> CREATOR = new Creator<Restaurante>() {
        @Override
        public Restaurante createFromParcel(Parcel in) {
            return new Restaurante(in);
        }

        @Override
        public Restaurante[] newArray(int size) {
            return new Restaurante[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isFavorite() {
        return favorito;
    }

    public void setFavorite(boolean favorite) {
        this.favorito = favorito;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeFloat(rating);
        parcel.writeByte((byte) (favorito ? 1 : 0));
    }

}
