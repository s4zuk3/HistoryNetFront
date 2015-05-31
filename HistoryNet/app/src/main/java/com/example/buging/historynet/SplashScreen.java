package com.example.buging.historynet;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Buging on 22-05-2015.
 */
public class SplashScreen extends Activity {

    private static final long TiempoDeEspera = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // orientacion vertical de la pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Iniciar el layout
        setContentView(R.layout.splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                //Iniciar el Main Activity
                Intent mainIntent = new Intent().setClass(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                //Se finaliza el hilo de ejecucion
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, TiempoDeEspera);
    }
}
