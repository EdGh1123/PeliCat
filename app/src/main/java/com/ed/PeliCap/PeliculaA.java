package com.ed.PeliCap;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PeliculaA extends RecyclerView.Adapter<PeliculaA.ViewHolderPelicula> {

    private ArrayList<Pelicula> listaPeliculas;

    public PeliculaA(ArrayList<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    @NonNull
    @Override
    public PeliculaA.ViewHolderPelicula onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pelicula_layout, null, false);
        return new ViewHolderPelicula(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaA.ViewHolderPelicula holder, int position) {
        holder.txtNombre.setText(listaPeliculas.get(position).getNombre());
        holder.txtDirector.setText(listaPeliculas.get(position).getDirector());
        holder.txtGenero.setText(listaPeliculas.get(position).getGenero());
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public class ViewHolderPelicula extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView txtNombre, txtDirector, txtGenero;

        public ViewHolderPelicula(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.idtxtNombre);
            txtDirector = itemView.findViewById(R.id.idtxtDirector);
            txtGenero = itemView.findViewById(R.id.idtxtGenero);
            itemView.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
