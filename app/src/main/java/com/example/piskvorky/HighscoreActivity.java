package com.example.piskvorky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HighscoreActivity extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_highscore);

        db = new Database(getApplicationContext());
        ListView list = (ListView)findViewById(R.id.highscoreList);
        List<Score> scores = db.select();
        final List<String> lst = new ArrayList<String>();

        for (Score score : scores) {
//                System.out.println(status.getUser().getName() + ":" +
//                        status.getText());
            lst.add(score.getID()+" - "+score.getScore());
        }

        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lst);
        list.setAdapter(ad);
    }
}
