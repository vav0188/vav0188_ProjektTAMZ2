package com.example.piskvorky;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import android.view.View;

public class GameView extends View {

    private int cellSize = 100;
    private int rows;
    private int cols;

    Paint paint;
    Canvas canvas;
    Context context;
    int canvasHeight;


    public GameView(Context context) {
        super(context);

        this.context = this.getContext();
        this.paint = new Paint();
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        cols = canvas.getWidth() / cellSize;
        rows = canvas.getHeight() / cellSize;
        canvasHeight = canvas.getHeight();


        paint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_DOWN)
            this.move(e);

        invalidate();
        return true;
    }

    private void move(MotionEvent e) {


    }

    public void paint() {
        this.paint.setColor(Color.TRANSPARENT);
        this.canvas.drawRect(0, 0, this.canvas.getWidth(), canvasHeight, this.paint);
        this.paint.setStrokeWidth(2);
        this.paint.setColor(Color.BLACK);

        int centerX = (canvasHeight % this.cellSize) / 2;
        int centerY = (this.canvas.getWidth() % this.cellSize) / 2;

        for(int i = 0 ; i < canvas.getWidth() ; i += this.cellSize)
        {
            this.canvas.drawLine(i + centerY, centerX, i + centerY, (canvasHeight - (canvasHeight % this.cellSize)) + centerX, this.paint);
        }

        for(int i = 0 ; i < canvas.getHeight() ; i += this.cellSize)
        {
            this.canvas.drawLine(centerY, i + centerX, (this.canvas.getWidth() - (this.canvas.getWidth() % this.cellSize)) + centerY, i + centerX, this.paint);
        }
    }


}
