package com.duanov.duasatu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    //    Button next2;
//thread = komponen
// splashTread = variable
    Thread splashTread;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //panggil function startanimation
        StartAnimations();
    }

    //buat function staranimation
    //menggunakan class private karna hanya diakses oleh class main
    private void StartAnimations() {
        //load animasi dari folder anim yang  berada didalam res
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        //deklarasi & panggil id komponen di view/layout
        RelativeLayout l = (RelativeLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        //komponen imageview dengan variable iv
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 5000) {
                        sleep(5000);
                        waited += 5000;
                    }
                    //setelah selesai animasi splash dijalankan maka akan otomatis pindah ke activity/screen Slider
                    //menggunakan intent
                    Intent intent = new Intent(MainActivity.this,
                            LoginPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    MainActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    MainActivity.this.finish();
                }
            }
        };
        splashTread.start();

    }

}