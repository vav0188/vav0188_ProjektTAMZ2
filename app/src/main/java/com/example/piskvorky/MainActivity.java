package com.example.piskvorky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button novaHra = (Button)findViewById(R.id.btnNewGame);
        Button nastaveni = (Button)findViewById(R.id.btnSettings);
        Button zebricek = (Button)findViewById(R.id.btnHighscore);

        novaHra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        nastaveni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        zebricek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighscoreActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences mPrefs = getSharedPreferences("Settings", 0);
        boolean music = mPrefs.getBoolean("music", true);

        if(music == true) {
            Log.d("ano","ano");

            Intent musicServ = new Intent(this, MusicService.class);
            startService(musicServ);
        }
        if(music == false) {
            Log.d("ano","ano");

            Intent musicServ = new Intent(this, MusicService.class);
            stopService(musicServ);
        }




    }
}
