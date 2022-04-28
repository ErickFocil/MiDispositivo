package com.example.midispositivo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.midispositivo.entidades.Usuario;

public class DBUsuarios extends DBHelper{

    Context context;

    public DBUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaUsuario(String nombre, String pass){
        long id = 0;
        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("tipo_Usuario", 1);
            values.put("nombre", nombre);
            values.put("password", pass);
            id = db.insert(TABLE_USUARIOS, null, values);
        }catch(Exception ex){
            ex.toString();
        }

        return id;
    }

    public Usuario getUsusario(String u, String p){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Usuario usuario = null;
        Cursor cursorUsuario;
        String selection = "nombre = ? AND password = ?";
        String[] selectionArgs = new String[]{u,p};

        cursorUsuario = db.query(TABLE_USUARIOS,new String[]{"id","tipo_Usuario","nombre"},selection,selectionArgs,null, null,null);

        //cursorUsuario = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE nombre = " + u + " AND password = " + p, null);

        if(cursorUsuario.moveToFirst())
            usuario = new Usuario(cursorUsuario.getInt(0),cursorUsuario.getInt(1),cursorUsuario.getString(2));

        cursorUsuario.close();

        return usuario;
    }
}
