package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class splash extends ActionBarActivity {

    MediaPlayer SplashSong;
    @Override
    protected void onCreate(Bundle MilesStudios) {
        // TODO Auto-generated method stub
        super.onCreate(MilesStudios);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.splash2);
        SplashSong = MediaPlayer.create(splash.this, R.raw.splash_sound);
        SplashSong.start();
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openMain_Screen = new Intent (splash.this, main.class);
                    startActivity(openMain_Screen);
                    finish();
                    // Finished already with the finish not from on pause
                }
            }
        };
        timer.start();


    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        SplashSong.release();
        finish();
        //Already Finished Splash but not song
    }


}