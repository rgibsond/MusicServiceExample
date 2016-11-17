package com.example.rgdrys13.musicserviceexample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.io.IOException;

/**
 * Created by rgdrys13 on 11/17/2016.
 */

public class MusicService extends Service implements MediaPlayer.OnPreparedListener {

    private MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int i = super.onStartCommand(intent, flags, startId);

        player = new MediaPlayer();

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        try {
            player.setDataSource(path.getPath() + "/my_old_friend.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnPreparedListener(this);
        player.prepareAsync();

        return i;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        player.stop();
        player.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
