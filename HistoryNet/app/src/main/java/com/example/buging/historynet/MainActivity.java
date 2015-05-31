package com.example.buging.historynet;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    private ImageButton btn_home;
    private ImageButton btn_ajustes;
    private Button btn_buscarLugar;
    private Button btn_usarGps;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Se inicia el controlador y se procede a inicializar la vista con todos  los datos que  necesita
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // orientacion vertical de la pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        //Se inicializan los botones
        this.btn_buscarLugar = (Button) findViewById(R.id.boton_buscarLugar);
        this.btn_usarGps = (Button) findViewById(R.id.boton_UsarGps);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Funcion encargada de controlar  el boton buscar lugar el cual nos  lanzara  a la vista
    //buscar lugar
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void buscarLugar(View view){
        Intent bl = new Intent(this, BuscarLugar.class);
        startActivity(bl);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Funcion encargada de controlar  el boton lugar actual el cual nos  lanzara  a la vista
    //Lugar Actual
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void lugarActual(View view){
        Intent la = new Intent(this, LugarActual.class);
        startActivity(la);
    }
}
