package com.example.midispositivo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.midispositivo.adaptadores.ListaSolicitudesAdaptador;
import com.example.midispositivo.db.DBSolicitudes;
import com.example.midispositivo.entidades.Solicitud;

import java.util.ArrayList;

public class SolicitudesActivity extends AppCompatActivity {

    ArrayList<Solicitud> listArraySolicitudes;

    RecyclerView listaSolicitudes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes);

        listaSolicitudes = findViewById(R.id.lista_Solicitudes);
        listaSolicitudes.setLayoutManager(new LinearLayoutManager(this));

        DBSolicitudes dbSolicitudes = new DBSolicitudes(SolicitudesActivity.this);

        listArraySolicitudes = new ArrayList<>();

        ListaSolicitudesAdaptador adaptador = new ListaSolicitudesAdaptador(dbSolicitudes.getSolicitudes());
        listaSolicitudes.setAdapter(adaptador);

        if(dbSolicitudes.getSolicitudes().size() == 0) setTitle("No hay Solicitudes");
    }
}