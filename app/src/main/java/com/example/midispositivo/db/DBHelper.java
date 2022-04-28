package com.example.midispositivo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.midispositivo.NuevoUsuarioActivity;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "solicitudes.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_SOLICITUDES = "t_solicitudes";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipo_Usuario INTEGER NOT NULL," +
                "nombre TEXT NOT NULL," +
                "password TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SOLICITUDES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_usuario INTEGER NOT NULL," +
                "equipo TEXT NOT NULL," +
                "reporte TEXT NOT NULL," +
                "analisis TEXT," +
                "FOREIGN KEY(id_usuario) REFERENCES " + TABLE_USUARIOS + "(id)" +
                ")");
        {
            sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USUARIOS + " (tipo_Usuario, nombre, password) VALUES (0,'aaa', '0')");
            sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USUARIOS + " (tipo_Usuario, nombre, password) VALUES (1,'bbb', '1')");
            sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USUARIOS + " (tipo_Usuario, nombre, password) VALUES (1,'ccc', '2')");
            sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USUARIOS + " (tipo_Usuario, nombre, password) VALUES (1,'ddd', '3')");

            sqLiteDatabase.execSQL("INSERT INTO " + TABLE_SOLICITUDES + " (id_usuario, equipo, reporte, analisis) VALUES (1,'Celular', 'No funciona correctamente', 'No se ha reparado')");
            sqLiteDatabase.execSQL("INSERT INTO " + TABLE_SOLICITUDES + " (id_usuario, equipo, reporte, analisis) VALUES (2,'PC', 'Se fundió la placa base', 'No se ha reparado')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SOLICITUDES);
        onCreate(sqLiteDatabase);
    }
}
