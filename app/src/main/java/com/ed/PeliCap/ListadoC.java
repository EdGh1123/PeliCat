package com.ed.PeliCap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListadoC extends AppCompatActivity {
    RecyclerView RePeliculas;
    ArrayList<Pelicula> ListaP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

                       ListaP = new ArrayList<>();
                       RePeliculas = findViewById(R.id.idRcPeliculas);
                                     RePeliculas.setLayoutManager(new LinearLayoutManager(this));
                                       Intent Int = getIntent();
                                       ListaP = Int.getParcelableArrayListExtra("pelis");
        PeliculaA peliculaAdapter = new PeliculaA(ListaP);
        RePeliculas.setAdapter(peliculaAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        PeliculaA peliculaAdapter;

        switch (id) {
            case R.id.action_ordenar_genero:
                Collections.sort(ListaP, new Comparator<Pelicula>() {
                    @Override
                    public int compare(Pelicula o1, Pelicula o2) {
                        if (o1.getGenero().compareTo(o2.getGenero()) < 0) {
                            return -1;
                        }
                        if (o1.getGenero().compareTo(o2.getGenero()) > 0) {
                            return 1;
                        }
                        return 0;
                    }
                });
                peliculaAdapter = new PeliculaA(ListaP);
                RePeliculas.setAdapter(peliculaAdapter);
                break;
            case R.id.action_ordenar_nombre:
                Collections.sort(ListaP, new Comparator<Pelicula>() {
                    @Override
                    public int compare(Pelicula o1, Pelicula o2) {
                        if (o1.getNombre().compareTo(o2.getNombre()) < 0) {
                            return -1;
                        }
                        if (o1.getNombre().compareTo(o2.getNombre()) > 0) {
                            return 1;
                        }
                        return 0;
                    }
                });
                peliculaAdapter = new PeliculaA(ListaP);
                RePeliculas.setAdapter(peliculaAdapter);
                break;
            case R.id.action_invertir:
                Collections.reverse(ListaP);
                peliculaAdapter = new PeliculaA(ListaP);
                RePeliculas.setAdapter(peliculaAdapter);
                break;
            case R.id.action_eliminar_primero:
                AlertDialog.Builder builder = new AlertDialog.Builder(ListadoC.this);
                builder.setMessage("Desea eliminar La Primera Pelicula?")
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ListaP.remove(0);
                                PeliculaA peliculaAdapter = new PeliculaA(ListaP);
                                RePeliculas.setAdapter(peliculaAdapter);
                                Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alerta = builder.create();
                alerta.show();
                break;
            case android.R.id.home:
                Intent i = new Intent(this, MainActivity.class);
                i.putParcelableArrayListExtra("pelis", ListaP);
                setResult(Activity.RESULT_OK, i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }













}
