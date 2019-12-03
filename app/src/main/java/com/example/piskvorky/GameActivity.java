package com.example.piskvorky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mPrefs = getSharedPreferences("Settings", 0);
        gameView = new GameView(this);
        gameView.setBackgroundColor(Color.WHITE);
        gameView.first = mPrefs.getBoolean("cross", true);
        setContentView(gameView);


        gameView.sound = mPrefs.getBoolean("sound", true);


    }
}
