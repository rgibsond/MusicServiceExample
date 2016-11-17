package com.example.rgdrys13.musicserviceexample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public static final int REQUEST_CODE = 999;
    public static final String LOG_TAG = MainActivity.class.getName();
    private Button play, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            this.requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
        else{
            init();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                init();
            } else {
                Log.e(LOG_TAG, "permission not granted to read file");
            }
        }
    }

    private void init(){
        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, MusicService.class));
            }
        });

        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, MusicService.class));
            }
        });
    }
}
