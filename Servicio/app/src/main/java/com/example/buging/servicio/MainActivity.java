package com.example.buging.servicio;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    EditText etResponse;
    TextView tvIsConnected;
    String respuesta;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Funcion encargada de inicializar el controlador y l vista para realizar el llamado al servicio
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se inicializan los componentes de la vista ue e usaran
        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        //Se realiza  el llamado a la funcion para la comunicación con el servicio
        conectarServicio("http://46.101.184.198:8000/consultar_lugar/?format=json");

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Se realizan algunos cambios en la vista y se realiza  un llamado asincrono
    //al servicio
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void conectarServicio(String url){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("Conectado");
            new HttpAsyncTask().execute(url);
        }else{
            return;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Clase asincrona
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            ConeccionServicio cs = new ConeccionServicio();
            return cs.GET(urls[0]);
        }

        //Se realiza el cambio etResponse luego de realizar el llamado
        @Override
        protected void onPostExecute(String result) {
            etResponse.setText(result);
        }
    }

}
