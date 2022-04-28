package com.example.midispositivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.midispositivo.db.DBSolicitudes;
import com.example.midispositivo.db.DBUsuarios;

public class NuevaSolicitudActivity extends AppCompatActivity {

    EditText txtEquipo, txtReporte;
    Button crearSolicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_solicitud);

        setTitle("Crear Solicitud");

        txtEquipo = findViewById(R.id.txt_In_Equipo);
        txtReporte = findViewById(R.id.txt_In_Reporte);
        crearSolicitud = findViewById(R.id.btn_CrearSolicitud);

        Intent intent = getIntent();
        int id = intent.getIntExtra("idUsuario",0);

        crearSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBSolicitudes dbSolicitudes = new DBSolicitudes(NuevaSolicitudActivity.this);
                long idr = dbSolicitudes.insertaSolicitud(id,txtEquipo.getText().toString(),txtReporte.getText().toString());
                if(idr > 0) {
                    Toast.makeText(NuevaSolicitudActivity.this, "Solicitud Enviada", Toast.LENGTH_LONG).show();
                    limpiar();
                    finish();
                } else Toast.makeText(NuevaSolicitudActivity.this, "Error en enviar la solicitud", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void limpiar() {
        txtEquipo.setText("");
        txtReporte.setText("");
    }
}