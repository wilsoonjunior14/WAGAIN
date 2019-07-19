package com.example.wagain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public GameActivity game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = new GameActivity(this);
        setContentView(game);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        game.interaction(motionEvent);
        return true;
    }
}
