package com.example.midispositivo.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midispositivo.R;
import com.example.midispositivo.entidades.Solicitud;
import com.example.midispositivo.verSolicitudActivity;

import java.util.ArrayList;

public class ListaSolicitudesAdaptador extends RecyclerView.Adapter<ListaSolicitudesAdaptador.SolicitudWiewHolder> {

    ArrayList<Solicitud> listaSolicitudes;
    public ListaSolicitudesAdaptador(ArrayList<Solicitud> listaSolicitudes){
        this.listaSolicitudes = listaSolicitudes;
    }

    @NonNull
    @Override
    public SolicitudWiewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_solicitud, null, false);
        return new SolicitudWiewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudWiewHolder holder, int position) {
        holder.viewNombreC.setText(listaSolicitudes.get(position).getNombre());
        holder.viewEquipo.setText(listaSolicitudes.get(position).getEquipo());
        holder.viewReporte.setText(listaSolicitudes.get(position).getReporte());
    }

    @Override
    public int getItemCount() {
        return listaSolicitudes.size();
    }

    public class SolicitudWiewHolder extends RecyclerView.ViewHolder {
        TextView viewNombreC, viewEquipo, viewReporte;

        public SolicitudWiewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombreC = itemView.findViewById(R.id.viewNombreC);
            viewEquipo = itemView.findViewById(R.id.viewEquipo);
            viewReporte = itemView.findViewById(R.id.viewReporte);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, verSolicitudActivity.class);
                    intent.putExtra("ID", listaSolicitudes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
