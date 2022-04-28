package com.example.midispositivo;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midispositivo.db.DBUsuarios;

public class NuevoUsuarioActivity extends AppCompatActivity {

    EditText txtUsusario, txtPass;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        txtUsusario = findViewById(R.id.text_InR_Usuario);
        txtPass = findViewById(R.id.text_InR_Pass);
        btnRegistrar = findViewById(R.id.button_R_Registrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUsuarios dbUsuarios = new DBUsuarios(NuevoUsuarioActivity.this);
                long id = dbUsuarios.insertaUsuario(txtUsusario.getText().toString(),txtPass.getText().toString());

                if(id > 0) {
                    Toast.makeText(NuevoUsuarioActivity.this, "Ususario Agregado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else Toast.makeText(NuevoUsuarioActivity.this, "Error en agregar al Usuario", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void limpiar() {
        txtUsusario.setText("");
        txtPass.setText("");
    }
}