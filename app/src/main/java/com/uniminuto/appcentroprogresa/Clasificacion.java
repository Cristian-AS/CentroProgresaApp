package com.uniminuto.appcentroprogresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Clasificacion extends AppCompatActivity {

    EditText completeName, document, address, city, numContact, indicateDisability;
    Button cancel, send;
    FirebaseFirestore mFirestore;
    Spinner spinnerSeat, spinnerProgram, spinnerDisability, spinnerSelectClassification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);

        mFirestore = FirebaseFirestore.getInstance();
        //mAuth = FirebaseAuth.getInstance();

        completeName = findViewById(R.id.textFieldCompleteName);
        document = findViewById(R.id.textFieldEntityDocument);
        address = findViewById(R.id.textFieldAddress);
        city = findViewById(R.id.textFieldCity);
        numContact = findViewById(R.id.textFieldNumContact);
        indicateDisability = findViewById(R.id.textFieldIndicateDisability);
        cancel = findViewById(R.id.buttonToCancel);
        send = findViewById(R.id.buttonToSend);


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

        send.setOnClickListener(view -> {
            String completeNameUser = completeName.getText().toString().trim();
            String documentUser = document.getText().toString().trim();
            String addressUser = address.getText().toString().trim();
            String cityUser =city.getText().toString().trim();
            String numContactUser = numContact.getText().toString().trim();
            String indicateDisabilityUser = indicateDisability.getText().toString().trim();

            String spinnerSeatUser = spinnerSeat.getSelectedItem().toString();
            String spinnerProgramUser = spinnerProgram.getSelectedItem().toString();
            String spinnerDisabilityUser = spinnerDisability.getSelectedItem().toString();
            String spinnerSelectClassificationUser = spinnerSelectClassification.getSelectedItem().toString();

            if (spinnerSeatUser.equals("Sede") || spinnerProgramUser.equals("programa") || spinnerDisabilityUser.equals( "¿Tiene alguna discapacidad?") || spinnerSelectClassificationUser.equals ("Investigación")){
                Toast.makeText(this, "Debes seleccionar una opción válida en la lista desplegable", Toast.LENGTH_SHORT).show();
            } else if (completeNameUser.isEmpty() || documentUser.isEmpty() || addressUser.isEmpty() || cityUser.isEmpty() || numContactUser.isEmpty() || indicateDisabilityUser.isEmpty()) {
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show();
            } else if (!TextUtils.isDigitsOnly(documentUser)) {
                Toast.makeText(this, "Ingresa solamente números en el N°Documento o un número válido", Toast.LENGTH_SHORT).show();
            } else if (!TextUtils.isDigitsOnly(numContactUser)){
                Toast.makeText(this, "Ingresa solamente números en el N°Contacto o un número válido", Toast.LENGTH_SHORT).show();
            } else {
                sendUser(spinnerSeatUser, spinnerProgramUser, spinnerDisabilityUser, spinnerSelectClassificationUser, completeNameUser, documentUser, addressUser, cityUser, numContactUser, indicateDisabilityUser);
            }
        });

        cancel.setOnClickListener(view -> {
            Intent intent = new Intent(this, Perfil.class);
            startActivity(intent);
        });
    }

    private void sendUser(String spinnerSeatUser, String spinnerProgramUser, String spinnerDisabilityUser, String spinnerSelectClassificationUser, String completeNameUser, String documentUser, String addressUser, String cityUser, String numContactUser, String indicateDisabilityrUser) {
        Map<String, Object> user = new HashMap<>();
        user.put("spinnerSeat", spinnerSeatUser);
        user.put("spinnerProgram", spinnerProgramUser);
        user.put("spinnerDisability", spinnerDisabilityUser);
        user.put("spinnerSelectClassification", spinnerSelectClassificationUser);
        user.put("completeName", completeNameUser);
        user.put("document", documentUser);
        user.put("address", addressUser);
        user.put("city", cityUser);
        user.put("numContact", numContactUser);
        user.put("indicateDisability", indicateDisabilityrUser);

        // Add a new document with a generated ID
        mFirestore.collection("clasificacion")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Clasificacion.this, "Encuesta exitosa", Toast.LENGTH_SHORT).show();
                        completeName.setText("");
                        document.setText("");
                        address.setText("");
                        city.setText("");
                        numContact.setText("");
                        indicateDisability.setText("");

                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Clasificacion.this, "Error al realizar la encuesta", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}