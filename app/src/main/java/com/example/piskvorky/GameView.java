package com.example.piskvorky;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;


import android.view.View;

import androidx.core.view.MotionEventCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameView extends View {

    private int cellSize = 100;
    private int rows;
    private int cols;
    String FIRST  = "X";
    String SECOND = "O";

    boolean sound = true;
    boolean first = true;

    Database db;

    Paint pnt;
    Canvas canvas;
    Context context;
    int canvasHeight;
    Logic logic;
    int[][] board;
    GameActivity ga = new GameActivity();

    public GameView(Context context) {
        super(context);
        this.pnt = new Paint();
        this.context = this.getContext();

        this.db = new Database(context);


    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        cols = canvas.getWidth() / cellSize;
        rows = canvas.getHeight() / cellSize;
        canvasHeight = canvas.getHeight();

        if(first == false)
        {
            FIRST = "O";
            SECOND = "X";
        }
        else
        {
            FIRST = "X";
            SECOND = "O";
        }
        if(this.logic == null) {
            this.logic = new Logic(rows, cols);
            this.board = this.logic.getBoard();
        }


        this.pnt.setColor(Color.TRANSPARENT);
        canvas.drawRect(0, 0, canvas.getWidth(), canvasHeight, this.pnt);
        this.pnt.setStrokeWidth(2);
        this.pnt.setColor(Color.BLACK);

        int centerX = (canvasHeight % this.cellSize) / 2;
        int centerY = (canvas.getWidth() % this.cellSize) / 2;



        for (int i = 0; i < canvas.getWidth(); i += this.cellSize) {
            canvas.drawLine(i + centerY, centerX, i + centerY, (canvasHeight - (canvasHeight % this.cellSize)) + centerX, this.pnt);
        }

        for (int i = 0; i < canvas.getHeight(); i += this.cellSize) {
            canvas.drawLine(centerY, i + centerX, (canvas.getWidth() - (canvas.getWidth() % this.cellSize)) + centerY, i + centerX, this.pnt);
        }


        if(board!=null){
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                int x = c * this.cellSize;
                int y = r * this.cellSize + 100 + centerX;

                int player = this.logic.getCurrentState(r, c);
                if (player != 0) {Log.d("hra","centerx");

                    this.pnt.setTypeface(Typeface.create("Calibri", Typeface.NORMAL));
                    this.pnt.setTextSize(Float.valueOf(String.valueOf(120.0)));

                    if(player == 1) {
                        this.pnt.setColor(Color.RED);
                        this.pnt.setStrokeWidth(5);
                        this.pnt.setStyle(Paint.Style.FILL);
                        this.canvas.drawText(FIRST, x + 15, y -5, this.pnt);
                    } else {
                        this.pnt.setColor(Color.BLUE);
                        this.pnt.setStrokeWidth(5);
                        this.pnt.setStyle(Paint.Style.FILL);
                        this.canvas.drawText(SECOND, x + 15, y -5, this.pnt);
                    }
                }
            }

        }}




    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int action = MotionEventCompat.getActionMasked(e);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                int centerX = (canvasHeight % this.cellSize) / 2;
                int centerY = (this.canvas.getWidth() % this.cellSize) / 2;

                int col = (int)((int)e.getX() - 50 + centerX) / (int)this.cellSize;
                int row = (int)((int)e.getY() - 70 + centerY) / (int)this.cellSize;

                if(logic.checkWinner() == 0)
                {
                if(board[row][col] == 0){
                this.logic.move(row, col);

                board = this.logic.getBoard();
                if(sound == true){
                    ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_RING, 50);
                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_NETWORK_LITE, 200);}
                this.invalidate();

                return true;}}
                else if(logic.checkWinner() == 1)
                {
                    AlertDialog alert;
                    alert = new AlertDialog.Builder(this.context).create();
                    alert.setTitle("Konec hry!");
                    alert.setMessage("Vyhrál hráč s "+FIRST+" za " + this.logic.getMoves() + " tahů! ");
                    alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                            db.insert(date,"Jmeno",logic.getMoves());

                        }
                    });

                    alert.show();
                    return false;
                }
                else if(logic.checkWinner() == 2)
                {
                    AlertDialog alert;
                    alert = new AlertDialog.Builder(this.context).create();
                    alert.setTitle("Konec hry!");
                    alert.setMessage("Vyhrál hráč s "+SECOND+" za " + this.logic.getMoves() + " tahů! ");
                    alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                            db.insert(date,"Jmeno",logic.getMoves());

                        }
                    });

                    alert.show();
                    return false;
                }

                return false;

            default:
                return super.onTouchEvent(e);

        }
    }



}
