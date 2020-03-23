package com.ed.PeliCap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Pelicula> ListaPelisArr = new ArrayList<>();
    Button btG, btC;
    String GeneroPelicula;
    RadioGroup radioGroup;
    Spinner spinnerGeneros;
    TextInputEditText ENom, EdD;
    RadioButton OpEs, OpEn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                                  radioGroup = findViewById(R.id.radioGroup);
                                  EdD = findViewById(R.id.idEdDirector);
                                  spinnerGeneros = findViewById(R.id.idSpinnerGeneros);
                                  ENom = findViewById(R.id.idEdNombrePelicula);
                                  OpEs = findViewById(R.id.BoEs);
                                  btC = findViewById(R.id.idBtCancelar);
                                  OpEn = findViewById(R.id.BoEn);
                                  btG = findViewById(R.id.idBtGuardar);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this
                , R.array.generos_Sp, R.layout.support_simple_spinner_dropdown_item);
        spinnerGeneros.setAdapter(adapter);
        spinnerGeneros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GeneroPelicula = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btG.setOnClickListener(this);
        btC.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idBtGuardar:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Quiere Guardar esta pelicula?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String idioma;
                        switch (radioGroup.getCheckedRadioButtonId()) {
                            case R.id.BoEs:
                                idioma = getString(R.string.espanol);
                                break;
                            case R.id.BoEn:
                                idioma = getString(R.string.ingles);
                                break;
                            default:
                                idioma = getString(R.string.ingles);
                                break;
                        }
                        Pelicula pelicula = new Pelicula(ENom.getText().toString(), EdD.getText().toString(), idioma, GeneroPelicula);
                        ListaPelisArr.add(pelicula);
                        ENom.setText("");
                        EdD.setText("");
                        Toast.makeText(getApplicationContext(), "Pelicula Guardada a la lista", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



                break;
            case R.id.idBtCancelar:
                ENom.setText("");
                EdD.setText("");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.action_listado:
                Intent i = new Intent(this, ListadoC.class);
                i.putParcelableArrayListExtra("pelis", ListaPelisArr);
                startActivityForResult(i, 5);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5) {
            ListaPelisArr = data.getParcelableArrayListExtra("pelis");

        }

    }
}
