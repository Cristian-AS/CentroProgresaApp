package com.uniminuto.appcentroprogresa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class Preinscripcion extends AppCompatActivity {
    Spinner spinnerTipoDocumento, spinnerSede, spinnerPrograma, spinnerEstadoPRS, spinnerSalud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preinscripcion);

        spinnerTipoDocumento = findViewById(R.id.spinnerTipoDocumento);
        spinnerSede = findViewById(R.id.spinnerSede);
        spinnerPrograma = findViewById(R.id.spinnerPrograma);
        spinnerEstadoPRS = findViewById(R.id.spinnerEstadoPRS);
        spinnerSalud = findViewById(R.id.spinnerSalud);

        String[] documento = {"Documento", "T.I", "C.C"};
        ArrayList<String> arrayListDocumento = new ArrayList<>(Arrays.asList(documento));
        ArrayAdapter<String> arrayAdapterSeat = new ArrayAdapter<>(this,R.layout.style_spinner,arrayListDocumento);
        spinnerTipoDocumento.setAdapter(arrayAdapterSeat);

        String[] sede = {"Sede","Sede Bello","Centro Regional Urabá","Centro Tutorial el Bagre","Centro Regional Aburrá Sur"};
        ArrayList<String> arrayListSede = new ArrayList<>(Arrays.asList(sede));
        ArrayAdapter<String> arrayAdapterSede = new ArrayAdapter<>(this,R.layout.style_spinner,arrayListSede);
        spinnerSede.setAdapter(arrayAdapterSede);

        String[] programa = {"Programa", "Ingenieria de Software"};
        ArrayList<String> arrayListPrograma = new ArrayList<>(Arrays.asList(programa));
        ArrayAdapter<String> arrayAdapterPrograma = new ArrayAdapter<>(this,R.layout.style_spinner,arrayListPrograma);
        spinnerPrograma.setAdapter(arrayAdapterPrograma);

        String[] EstadoPRS = {"Estado de Práctica de Responsabilidad Social", "SI", "NO", "EN PROCESO"};
        ArrayList<String> arrayListEstadoPRS = new ArrayList<>(Arrays.asList(EstadoPRS));
        ArrayAdapter<String> arrayAdapterEstadoPRS = new ArrayAdapter<>(this,R.layout.style_spinner,arrayListEstadoPRS);
        spinnerEstadoPRS.setAdapter(arrayAdapterEstadoPRS);

        String[] Salud = {"Afiliación  al sistema de salud", "SI", "NO"};
        ArrayList<String> arrayListSalud = new ArrayList<>(Arrays.asList(Salud));
        ArrayAdapter<String> arrayAdapterSalud = new ArrayAdapter<>(this,R.layout.style_spinner,arrayListSalud);
        spinnerSalud.setAdapter(arrayAdapterSalud);

    }
}