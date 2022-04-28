package com.example.midispositivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.midispositivo.adaptadores.ListaSolicitudesAdaptador;
import com.example.midispositivo.db.DBSolicitudes;
import com.example.midispositivo.entidades.Solicitud;

import java.util.ArrayList;

public class SolicitudesClientesActivity extends AppCompatActivity {

    ArrayList<Solicitud> listArraySolicitudes;
    TextView txtAviso;
    Button bCrearSolicitud;
    ImageButton btnReload;

    RecyclerView listaSolicitudes;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes_clientes);

        listaSolicitudes = findViewById(R.id.lista_Solicitudes);
        listaSolicitudes.setLayoutManager(new LinearLayoutManager(this));

        DBSolicitudes dbSolicitudes = new DBSolicitudes(SolicitudesClientesActivity.this);

        listArraySolicitudes = new ArrayList<>();

        Intent intent = getIntent();
        id = intent.getIntExtra("idUsuario",0);
        ArrayList<Solicitud> listT = dbSolicitudes.getSolicitudesByIdUsuario(id);
        ListaSolicitudesAdaptador adaptador = new ListaSolicitudesAdaptador(listT);
        listaSolicitudes.setAdapter(adaptador);

        txtAviso = findViewById(R.id.text_AvisoListaSC);
        bCrearSolicitud = findViewById(R.id.btn_CrearSolicitud);
        btnReload = findViewById(R.id.btn_Reload);

        if(listT.size() == 0) txtAviso.setText("No tienes solicitudes");

        bCrearSolicitud.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                crearSolicitud(id);
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //listT = dbSolicitudes.getSolicitudesByIdUsuario(id);
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void crearSolicitud(int id){
        Intent intent = new Intent(this,NuevaSolicitudActivity.class);
        intent.putExtra("idUsuario", id);
        startActivity(intent);
    }
}