package com.minguillo.ancajima.juanaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;


public class Ini extends Activity {
    private  int posicion;
    private Button btnEntrar, btnRegistrarte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini);

        final ScrollView scrollview = ((ScrollView) findViewById(R.id.scrollAutomatico));
        scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        btnEntrar = (Button)findViewById(R.id.btnPIEntrar);
        btnRegistrarte = (Button)findViewById(R.id.btnPIRegistrarte);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaEntrar = new Intent(getApplicationContext(), Entrar.class);
                startActivity(pantallaEntrar);
            }
        });

        btnRegistrarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaRegistrarte = new Intent(getApplicationContext(), Registrarte.class);
                startActivity(pantallaRegistrarte);
            }
        });

        /*
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                //scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                scrollview.scrollTo(0, 100);

            }
        });
        */

        scrollview.postDelayed(new Runnable() {
            @Override
            public void run() {
                //scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                posicion++;
                scrollview.scrollTo(0, posicion);
                //scrollview.scrollTo(0, posicion);
                if(posicion== scrollview.getChildAt(0).getHeight()){
                    posicion=0;
                }

                scrollview.postDelayed(this, 30);

            }
        }, 30);



    }

}
