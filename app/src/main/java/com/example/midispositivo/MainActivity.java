package com.example.midispositivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.midispositivo.db.DBHelper;
import com.example.midispositivo.db.DBUsuarios;
import com.example.midispositivo.entidades.Usuario;

public class MainActivity extends AppCompatActivity {

    Usuario usuario = new Usuario();

    Button bLogin, bRegi;
    EditText ipUsuario, ipPassw;
    TextView tError;

    @Override
    protected void onRestart() {
        super.onRestart();
        limpiar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        final String[] acceso = {"",""};

        bLogin = (Button) findViewById(R.id.button_Login);
        bRegi = (Button) findViewById(R.id.button_Regi);
        ipUsuario = (EditText) findViewById(R.id.input_Usuario);
        ipPassw = (EditText) findViewById(R.id.input_Passw);
        tError = (TextView) findViewById(R.id.text_error);

        bLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                acceso[0] = ipUsuario.getText().toString();
                acceso[1] = ipPassw.getText().toString();

                if(AccesoT(acceso[0], acceso[1])){
                    tError.setText("");
                    Acceder();
                }else{
                    tError.setText("Error en los datos ingresados");
                }
            }
        });
        bRegi.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                irRegistro();
            }
        });
    }

    private boolean AccesoT(String u, String p){
        DBUsuarios dbUsuario = new DBUsuarios(MainActivity.this);
        usuario = dbUsuario.getUsusario(u,p);

        if(usuario == null) return false;
        else return true;
    }

    private void Acceder(){
        switch (usuario.getTUsuario()){
            case 0:
                Intent intentP = new Intent(this,SolicitudesActivity.class);
                startActivity(intentP);
                break;
            case 1:
                Intent intentS = new Intent(this,SolicitudesClientesActivity.class);
                intentS.putExtra("idUsuario",usuario.getIdUsuario());
                startActivity(intentS);
                break;
            default:
                tError.setText("Error en los datos ingresados");
        }
    }

    private void irRegistro(){
        Intent intent = new Intent(this, NuevoUsuarioActivity.class);
        startActivity(intent);
    }

    private void limpiar(){
        ipUsuario.setText("");
        ipPassw.setText("");
    }
}