package com.example.buging.historynet;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.List;


public class BuscarLugar extends Activity implements OnMapReadyCallback{

    //private GoogleMap mapa;
    private MapFragment mapFragment;
    private ImageButton btn_buscarLugar;
    private EditText direccion;
    private ImageButton btn_verLugar;
    //private MapView mapView;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Se inicia el controlador y se procede a inicializar la vista con todos  los datos que  necesita
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // orientacion vertical de la pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.buscar_lugar);
        this.mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapa);
        this.mapFragment.getMapAsync(this);

        this.btn_buscarLugar = (ImageButton) findViewById(R.id.btn_buscarLugar);
        this.btn_verLugar = (ImageButton) findViewById(R.id.btn_verLugar);
        this.direccion = (EditText) findViewById(R.id.direccion);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Funcion encargada  posicinar de manera inicialmente el mapa  en santiago
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng santiago = new LatLng(-33.4738794, -70.6621224);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(santiago, 11));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Funcion encargada de buscar un lugar depndiendo de lo que especifique el usuario a partir de
    //una direccion para esto se usa GEOCODE que  puede transformar direcciones a coordenadas ya que
    //googleMAP solo trabaja con coordenadas
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void botonBuscarLugar(View view) throws IOException{
        String lugar = this.direccion.getText().toString();

        //transforma una direeccion en latitud y longitud
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(lugar,1);
        Address nuevaLocacion = list.get(0);
        double lat = nuevaLocacion.getLatitude();
        double lng = nuevaLocacion.getLongitude();

        //se crea la coordenada para enviar a google maps
        LatLng locacion = new LatLng(lat, lng);

        //se busca la coordenada en google maps
        GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapa)).getMap();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(locacion, 15));

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Funcion encargada de controlar  el boton de  Home el cual nos debe llevar  a la vista principal
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void botonHome(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
