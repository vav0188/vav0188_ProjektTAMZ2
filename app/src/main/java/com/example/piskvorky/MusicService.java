package com.example.piskvorky;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class MusicService extends Service {

    MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.sound);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    public void onPause() {
        player.stop();
    }






}