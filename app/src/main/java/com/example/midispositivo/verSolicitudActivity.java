package com.example.midispositivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midispositivo.db.DBSolicitudes;
import com.example.midispositivo.entidades.Solicitud;

public class verSolicitudActivity extends AppCompatActivity {

    TextView txtEquipo, txtReporte;
    EditText txtAnalisis;
    Button btnEnviarA;

    Solicitud solicitud;
    int id = 0;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_solicitud);


        txtEquipo = findViewById(R.id.txt_In_Equipo);
        txtReporte = findViewById(R.id.txt_In_Reporte);
        txtAnalisis = findViewById(R.id.txt_In_Analisis);
        btnEnviarA = findViewById(R.id.btn_EnviarAnalisis);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBSolicitudes dbSolicitudes = new DBSolicitudes(verSolicitudActivity.this);
        solicitud = dbSolicitudes.getSolicitud(id);

        if(solicitud != null){
            txtEquipo.setText(solicitud.getEquipo());
            txtReporte.setText(solicitud.getReporte());
            txtAnalisis.setText(solicitud.getAnalisis());
        }

        btnEnviarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correcto = dbSolicitudes.editarSolicitud(id,txtAnalisis.getText().toString());

                if(correcto) Toast.makeText(verSolicitudActivity.this, "Solicitud guardada", Toast.LENGTH_LONG).show();
                else Toast.makeText(verSolicitudActivity.this, "Solicitud no guardada", Toast.LENGTH_LONG).show();
            }
        });
    }
}