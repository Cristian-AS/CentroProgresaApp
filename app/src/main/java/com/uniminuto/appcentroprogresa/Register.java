package com.uniminuto.appcentroprogresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText name, email, password, career;
    Button register;
    TextView Ingresa;
    FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.textFieldName);
        email = findViewById(R.id.textFieldEmail);
        password = findViewById(R.id.textFieldPassword);
        career = findViewById(R.id.textFieldCareer);
        register = findViewById(R.id.buttonRegister);
        Ingresa = findViewById(R.id.txtIngresa);

        Ingresa.setOnClickListener(view -> {
            Toast.makeText(this, "Estas en la vista de Inicio de Sesion", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

        register.setOnClickListener(view -> {
            String nameUser = name.getText().toString().trim();
            String emailUser = email.getText().toString().trim();
            String passwordUser = password.getText().toString().trim();
            String careerUser = career.getText().toString().trim();

            if (nameUser.isEmpty() && emailUser.isEmpty() && passwordUser.isEmpty() && careerUser.isEmpty()) {
                Toast.makeText(Register.this, "Complete los datos", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(nameUser, emailUser, passwordUser, careerUser);
            }
        });
    }

    private void registerUser(String nameUser, String emailUser, String passwordUser, String careerUser) {
        mAuth.createUserWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(task -> {
            String id = mAuth.getCurrentUser().getUid();
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("name", nameUser);
            map.put("email", emailUser);
            map.put("password", passwordUser);
            map.put("career", careerUser);

            mFirestore.collection("user").document(id).set(map).addOnSuccessListener(unused -> {
                finish();
                startActivity(new Intent(Register.this, Perfil.class));
                Toast.makeText(Register.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Register.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                }
            });
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

