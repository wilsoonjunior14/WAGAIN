package com.example.wagain.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class Game {

    // score of the player
    private int score;

    // length of device
    private int width;
    private int heigth;

    // level of the game
    private int level;

    // circles of the game
    private ArrayList<Circle> circleGames;

    // circles touched by player
    private ArrayList<Circle> circlePlayer;

    // number of 'piscadas'
    private int numberPiscadas;

    // indicate if game is of the player
    private boolean player;

    // indicate if game is beginning
    private boolean playing;


    // constructor of the class
    public Game(int width, int height, int level) {
        this.width = width;
        this.heigth = width;
        this.level = level;
        this.player = false;
        this.playing = false;
    }

    // method for initialize the game
    public void initGame(Canvas canvas){
        this.score = 0;
        this.player = false;
        this.circleGames = new ArrayList<Circle>();
        this.circlePlayer = new ArrayList<Circle>();
        this.numberPiscadas = 1;

        // creating circles based on level of game
        for (int i=0; i<this.level; i++){
            Paint p = new Paint();
            p.setColor(Color.GRAY);

            float f = Float.valueOf(String.valueOf(this.heigth - (this.heigth - 90*(i+1))));

            this.circleGames.add(new Circle(i+1, this.width/2, f, 40, p));

            this.circleGames.get(i).draw(canvas);
        }

        drawTextInit(canvas);

    }

    // draw text of init game
    public void drawTextInit(Canvas canvas){
        Paint p = new Paint();
        p.setTextAlign(Paint.Align.CENTER);
        p.setTextSize(24);
        p.setColor(Color.WHITE);
        canvas.drawText("> Aperte na tela para iniciar o jogo", this.width/2, this.heigth+100,p);
    }

    // click on display for the player
    public void drawChanges(Canvas canvas){

    }
}
