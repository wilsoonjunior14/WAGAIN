package com.example.wagain;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.wagain.models.Game;

import java.util.zip.CheckedOutputStream;

public class GameActivity extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    public Game game;
    public Canvas canvas;

    public GameActivity(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.game.onTouch(event);
        return false;
    }

    @Override
    public void onDraw(Canvas canvas){
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {
            this.canvas = null;

            // initialize the game
            this.game = new Game(getWidth(), getHeight(), 5);

            // infinit loop
            while (true) {

                try {
                    this.canvas = getHolder().lockCanvas();
                    this.canvas.drawColor(Color.BLACK);

                    // play the game
                    this.game.play(this.canvas);

                    Thread.sleep(this.game.delay());

                    // draw the moviments
                    this.game.move_randomly(canvas);

                    // end game
                    System.out.println("End Game ? "+this.game.end_game);

                    getHolder().unlockCanvasAndPost(this.canvas);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

    }
}