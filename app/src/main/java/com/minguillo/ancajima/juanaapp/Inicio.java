package com.minguillo.ancajima.juanaapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher.ViewFactory;

import java.util.Timer;
import java.util.TimerTask;

public class Inicio extends Activity {
    private ImageSwitcher imageSwitcher;
    private int[] galeria = {R.drawable.p_foto, R.drawable.p_foto1, R.drawable.p_foto2};

    private int posicion;
    private static final Integer DURACION = 3000;
    private Timer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewFactory() {

            public View makeView() {
                //return new ImageView(Inicio.this);
                ImageView i = new ImageView(Inicio.this);
                i.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return i;

            }
        });


        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);

        imageSwitcher.postDelayed(new Runnable() {
            int i = 0;
            public void run() {
                imageSwitcher.setImageResource(galeria[posicion]);
                //relative.setBackgroundResource(galeria[posicion]);
                posicion++;
                if (posicion == galeria.length) {
                    posicion = 0;
                }
                imageSwitcher.postDelayed(this, 3000);
            }
        }, 1000);


    }

    public void Iniciar(){
        if (timer != null) {
            timer.cancel();
        }
        posicion = 0;
        startSlider();
    }


    public void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception:
                // "Only the original thread that created a view hierarchy can touch its views"
                runOnUiThread(new Runnable() {
                    public void run() {
                        //rlv.setBackgroundResource(galeria[posicion]);
                        imageSwitcher.setImageResource(galeria[posicion]);
                        posicion++;
                        if (posicion == galeria.length) {
                            posicion = 0;
                        }
                    }
                });
            }

        }, 0, DURACION);
    }

    // Stops the slider when the Activity is going into the background
    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }
    }

    }
