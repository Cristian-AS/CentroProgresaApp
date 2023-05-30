package com.uniminuto.appcentroprogresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    Button btn_login;
    TextView txtRegister;
    EditText email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.correoElectronico);
        password = findViewById(R.id.contrasena);
        btn_login = findViewById(R.id.buttonIngresar);
        txtRegister = findViewById(R.id.txtregister);

        txtRegister.setOnClickListener(view -> {
            Toast.makeText(this, "Estas en la vista de Registro", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        });

        btn_login.setOnClickListener(view -> {
            String emailUser = email.getText().toString().trim();
            String passwordUser = password.getText().toString().trim();

            if (emailUser.isEmpty() && passwordUser.isEmpty()) {
                Toast.makeText(this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(emailUser, passwordUser);
            }
        });
    }

    private void loginUser(String emailUser, String passwordUser) {
        mAuth.signInWithEmailAndPassword(emailUser, passwordUser)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        finish();
                        Toast.makeText(this, "Inicio de Sesion exitosa", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, Perfil.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> Toast.makeText(this, "Error al iniciar Sesion",Toast.LENGTH_SHORT).show());
    }
}
