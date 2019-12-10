package com.example.piskvorky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    GameView gameView;

    long startTime = 0;
    int seconds = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerHandler.postDelayed(this, 500);
        }
    };



    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences mPrefs = getSharedPreferences("Settings", 0);
        gameView = new GameView(this);
        gameView.setBackgroundColor(Color.WHITE);
        gameView.first = mPrefs.getBoolean("cross", true);
        setContentView(gameView);


        gameView.sound = mPrefs.getBoolean("sound", true);
    }

    public int getSecs()
    {
        timerHandler.removeCallbacks(timerRunnable);
        return seconds;
    }

    public void Finish() {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
