package com.uniminuto.appcentroprogresa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class Clasificacion extends AppCompatActivity {
    Spinner spinnerSeat, spinnerProgram, spinnerDisability, spinnerSelectClassification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);

        spinnerSeat = findViewById(R.id.spinnerSeat);
        spinnerProgram = findViewById(R.id.spinnerProgram);
        spinnerDisability = findViewById(R.id.spinnerDisability);
        spinnerSelectClassification = findViewById(R.id.spinnerSelectClassification);

        String[] seat = {"Sede","Sede Bello","Centro Regional Urabá","Centro Tutorial el Bagre","Centro Regional Aburrá Sur"};
        ArrayList<String> arrayListSeat = new ArrayList<>(Arrays.asList(seat));
        ArrayAdapter<String> arrayAdapterSeat = new ArrayAdapter<>(this,R.layout.style_spinner,arrayListSeat);
        spinnerSeat.setAdapter(arrayAdapterSeat);

        String[] program = {"Programa","Administración de Empresas","Comunicación Social y Periodismo","Psicología","Ingeniería de Software"};
        ArrayList<String> arrayListprogram = new ArrayList<>(Arrays.asList(program));
        ArrayAdapter<String> arrayAdapterprogram = new ArrayAdapter<>(this,R.layout.style_spinner,arrayListprogram);
        spinnerProgram.setAdapter(arrayAdapterprogram);

        String[] Disability = {"¿Tiene alguna discapacidad?","Si","No"};
        ArrayList<String> arrayListDisability = new ArrayList<>(Arrays.asList(Disability));
        ArrayAdapter<String> arrayAdapterDisability = new ArrayAdapter<>(this,R.layout.style_spinner,arrayListDisability);
        spinnerDisability.setAdapter(arrayAdapterDisability);

        String[] SelectClassification = {"Investigación","Emprendimiento","Selecciona la clasificación","Contrato de Aprendizaje","Contrato Laboral","Convenio o Proyecto Especial","Escenario Internacional"};
        ArrayList<String> arrayListSelectClassification = new ArrayList<>(Arrays.asList(SelectClassification));
        ArrayAdapter<String> arrayAdapterSelectClassification = new ArrayAdapter<>(this,R.layout.style_spinner,SelectClassification);
        spinnerSelectClassification.setAdapter(arrayAdapterSelectClassification);
    }

}