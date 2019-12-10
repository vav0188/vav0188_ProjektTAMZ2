package com.example.piskvorky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        Switch cross = (Switch) findViewById(R.id.swKrizky);
        Switch sound = (Switch) findViewById(R.id.swZvuk);
        Switch music = (Switch) findViewById(R.id.swHudba);
        Button menu = (Button) findViewById(R.id.btnMenu);

        SharedPreferences prefs = getSharedPreferences("Settings", 0);
        boolean boolCross = prefs.getBoolean("cross", false);
        boolean boolSound = prefs.getBoolean("sound", false);
        boolean boolMusic = prefs.getBoolean("music", true);

        Log.d("checked","cross "+boolCross);

        cross.setChecked(boolCross);
        sound.setChecked(boolSound);
        music.setChecked(boolMusic);


        cross.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Settings", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                if (isChecked)
                {
                    editor.putBoolean("cross",true);
                    editor.commit();
                }
                else {
                    editor.putBoolean("cross",false);
                    editor.commit();
                }
            }
        });

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Settings", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                if (isChecked) {

                    editor.putBoolean("sound",true);
                    editor.commit();
                } else {
                    editor.putBoolean("sound",false);
                    editor.commit();
                }
            }
        });

        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Settings", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                if (isChecked) {
                    Log.d("checked","ch");
                    editor.putBoolean("music",true);
                    editor.commit();
                } else {
                    editor.putBoolean("music",false);
                    editor.commit();
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });







    }
}
