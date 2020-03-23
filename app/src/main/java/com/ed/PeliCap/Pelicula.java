package com.ed.PeliCap;

import android.os.Parcel;
import android.os.Parcelable;

public class Pelicula implements Parcelable {
    private String nombre, director,idioma ,genero ;



    public Pelicula(Parcel in) {


        nombre = in.readString();
        idioma = in.readString();
        director = in.readString();
        genero = in.readString();
    }

    public Pelicula(String nombre, String director, String idioma, String genero) {


        this.nombre = nombre;
        this.idioma = idioma;
        this.director = director;
        this.genero = genero;
    }

    public static final Creator<Pelicula> CREATOR = new Creator<Pelicula>() {
        @Override
        public Pelicula createFromParcel(Parcel in) {
            return new Pelicula(in);
        }

        @Override
        public Pelicula[] newArray(int size) {
            return new Pelicula[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre); dest.writeString(idioma); dest.writeString(genero); dest.writeString(director);

    }


}
