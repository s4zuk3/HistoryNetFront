package com.example.buging.historynet;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;


public class LugarActual extends Activity implements OnMapReadyCallback {

    private MapFragment mapFragment;
    private boolean estrellita;
    private ImageButton btn_favoritos;

    private LocationManager localizacion;
    private boolean gpsActivo;
    private boolean wifiActivo;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Se inicia el controlador y se procede a inicializar la vista con todos  los datos que  necesita
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // orientacion vertical de la pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.lugar_actual);

        //marcar como favorito el lugar
        this.estrellita = false;

        this.mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapa);
        this.mapFragment.getMapAsync(this);
        //cargar los botones
        this.btn_favoritos = (ImageButton) findViewById(R.id.btn_favoritos);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Funcion encargada  posicinar de manera inicialmente en el lugar actual en el que nos encontramos
    //ya sea atraves del GPS o wifi
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        Location myLocacion;
        double lat,lng;

        //Valida si se encuentra activo el wifi o el GPS
        try{
            this.localizacion = (LocationManager) getSystemService(LOCATION_SERVICE);
            this.gpsActivo = localizacion.isProviderEnabled(LocationManager.GPS_PROVIDER);
            this.wifiActivo = localizacion.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch(Exception e){}

        //Busca la diireccion atraves del gps y ubica el mapa en ella
        if(gpsActivo){
            myLocacion = localizacion.getLastKnownLocation(localizacion.GPS_PROVIDER);
            lat = myLocacion.getLatitude();
            lng = myLocacion.getLongitude();
            LatLng myLocacionCoordenadas = new LatLng(lat, lng);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocacionCoordenadas, 13));

        //Busca la direccion atraves del wifi y ubica el mapa en ella
        }else if(wifiActivo){
            myLocacion = localizacion.getLastKnownLocation(localizacion.NETWORK_PROVIDER);
            lat = myLocacion.getLatitude();
            lng = myLocacion.getLongitude();
            LatLng myLocacionCoordenadas = new LatLng(lat, lng);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocacionCoordenadas, 13));
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Funcion encargada de controlar  el boton de  favoritos donde debe enviar consultas a la  base
    //datos  para poder realizar cambios  en los lugares  favoritos de un usuario
    //actualmente no implementado al 100%
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void boton_estrella(View view){

        if(this.estrellita == false){
            this.estrellita = true;
            Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.estrella_favoritos);
            this.btn_favoritos.setImageBitmap(Bitmap.createScaledBitmap(bMap, 100, 100, false));
        }else{
            this.estrellita = false;
            Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.estrella);
            this.btn_favoritos.setImageBitmap(Bitmap.createScaledBitmap(bMap, 100, 100, false));
        }
    }
}
