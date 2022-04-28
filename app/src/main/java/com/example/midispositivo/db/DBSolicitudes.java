package com.example.midispositivo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.midispositivo.entidades.Solicitud;

import java.util.ArrayList;

public class DBSolicitudes extends DBHelper{

    Context context;

    public DBSolicitudes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaSolicitud(int idUsuario, String equipo, String reporte){
        long id = 0;
        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id_usuario", idUsuario);
            values.put("equipo", equipo);
            values.put("reporte", reporte);
            id = db.insert(TABLE_SOLICITUDES, null, values);
        }catch(Exception ex){
            ex.toString();
        }

        return id;
    }

    public boolean editarSolicitud(int id, String analisis){
        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_SOLICITUDES + " SET analisis = '" + analisis + "' WHERE id = '" + id + "'");
            correcto = true;
        }catch(Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }

        return correcto;
    }

    public ArrayList<Solicitud> getSolicitudes(){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();

        Solicitud solicitud = null;
        Cursor cursorSolicitud = null;
        String selection = "id = ?";
        String[] selectionArgs;

        cursorSolicitud = db.query(TABLE_SOLICITUDES,new String[]{"id","id_usuario","equipo", "reporte"},null,null,null, null,null);

        if(cursorSolicitud.moveToFirst()) {
            do{
                Cursor cursorUsuario = null;
                selectionArgs = new String[]{Integer.toString(cursorSolicitud.getInt(1))};
                cursorUsuario = db.query(TABLE_USUARIOS,new String[]{"nombre"},selection,selectionArgs,null, null,null);
                cursorUsuario.moveToFirst();

                solicitud = new Solicitud();
                solicitud.setId(cursorSolicitud.getInt(0));
                solicitud.setIdUsuario(cursorSolicitud.getInt(1));
                solicitud.setNombre(cursorUsuario.getString(0));
                solicitud.setEquipo(cursorSolicitud.getString(2));
                solicitud.setReporte(cursorSolicitud.getString(3));
                //solicitud.setAnalisis("");
                cursorUsuario.close();
                listaSolicitudes.add(solicitud);
            }while(cursorSolicitud.moveToNext());
        }

        cursorSolicitud.close();

        return listaSolicitudes;
    }

    public ArrayList<Solicitud> getSolicitudesByIdUsuario(int idU){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();

        Solicitud solicitud = null;
        Cursor cursorSolicitud = null;
        String selectionS = "id_usuario = ?";
        String[] selectionArgsS = new String[]{Integer.toString(idU)};
        String selection = "id = ?";
        String[] selectionArgs;

        cursorSolicitud = db.query(TABLE_SOLICITUDES,new String[]{"id","id_usuario","equipo", "reporte"},selectionS,selectionArgsS,null, null,null);

        if(cursorSolicitud.moveToFirst()) {
            do{
                Cursor cursorUsuario = null;
                selectionArgs = new String[]{Integer.toString(cursorSolicitud.getInt(1))};
                cursorUsuario = db.query(TABLE_USUARIOS,new String[]{"nombre"},selection,selectionArgs,null, null,null);
                cursorUsuario.moveToFirst();

                solicitud = new Solicitud();
                solicitud.setId(cursorSolicitud.getInt(0));
                solicitud.setIdUsuario(cursorSolicitud.getInt(1));
                solicitud.setNombre(cursorUsuario.getString(0));
                solicitud.setEquipo(cursorSolicitud.getString(2));
                solicitud.setReporte(cursorSolicitud.getString(3));
                //solicitud.setAnalisis("");
                cursorUsuario.close();
                listaSolicitudes.add(solicitud);
            }while(cursorSolicitud.moveToNext());
        }

        cursorSolicitud.close();

        return listaSolicitudes;
    }

    public Solicitud getSolicitud(int id){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Solicitud solicitud = null;
        Cursor cursorSolicitud = null;
        String selection = "id = ?";
        String[] selectionArgs = new String[]{Integer.toString(id)};

        cursorSolicitud = db.query(TABLE_SOLICITUDES,new String[]{"id","id_usuario","equipo", "reporte", "analisis"},selection,selectionArgs,null, null,null);

        if(cursorSolicitud.moveToFirst()) {
            solicitud = new Solicitud();
            solicitud.setId(cursorSolicitud.getInt(0));
            solicitud.setIdUsuario(cursorSolicitud.getInt(1));
            solicitud.setEquipo(cursorSolicitud.getString(2));
            solicitud.setReporte(cursorSolicitud.getString(3));
            solicitud.setAnalisis(cursorSolicitud.getString(4));
        }

        cursorSolicitud.close();

        return solicitud;
    }
}
