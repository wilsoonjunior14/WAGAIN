package com.example.wagain.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    // colors used
    private int[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};

    // score of the player
    private int score;

    // length of device
    private int width;
    private int heigth;

    // level of the game
    private int level;

    // circles selected randomly
    private ArrayList<Circle> circleSelected;

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

    // indicate end game
    public boolean end_game;

    // status of player
    public boolean status_player;


    // constructor of the class
    public Game(int width, int height, int level) {
        this.width = width;
        this.heigth = width;
        this.level = level;
        this.player = false;
        this.playing = false;
        this.score = 0;
        this.player = false;
        this.circleGames = new ArrayList<Circle>();
        this.circlePlayer = new ArrayList<Circle>();
        this.circleSelected = new ArrayList<Circle>();
        this.numberPiscadas = 1;
        this.end_game = false;
        this.status_player = false;

        // creating circles based on level of game
        Paint p = new Paint();
        p.setColor(Color.GRAY);

        // creating four circles
        this.circleGames.add(new Circle(1, this.width/2, (this.heigth/2)-100, 60, p));
        this.circleGames.add(new Circle(2, this.width/2, (this.heigth/2)+100, 60, p));
        this.circleGames.add(new Circle(3, (this.width/2)-100, (this.heigth/2), 60, p));
        this.circleGames.add(new Circle(4, (this.width/2)+100, (this.heigth/2), 60, p));

    }

    // method for initialize the game
    public void play(Canvas canvas){

        if (this.end_game){
            this.drawEndGame(canvas);
            return;
        }

        // draw circles
        this.circleGames.get(0).draw(canvas, Color.GRAY);
        this.circleGames.get(1).draw(canvas, Color.GRAY);
        this.circleGames.get(2).draw(canvas, Color.GRAY);
        this.circleGames.get(3).draw(canvas, Color.GRAY);

        drawText(canvas);

    }

    // calcule the distance
    public double distance(float xi, float yi, float xf, float yf){
        return Math.sqrt(Math.pow(xi-xf, 2) + Math.pow(yi-yf, 2));
    }

    // draw end game
    public void drawEndGame(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextAlign(Paint.Align.CENTER);
        p.setTextSize(28);

        StringBuilder sb = new StringBuilder();
        if (this.status_player){
            sb.append("Você venceu!");
        }else{
            sb.append("Você perdeu!");
        }

        canvas.drawText("> Fim de Jogo! "+sb.toString(), this.width / 2, this.heigth + 100, p);
    }

    // detect touch on screen
    public void onTouch(MotionEvent motionEvent){
        if (this.end_game){
            this.circleSelected = new ArrayList<Circle>();
            this.circlePlayer   = new ArrayList<Circle>();

            this.end_game = false;
            this.playing = false;
            return;
        }

        if (!this.playing){
            this.playing = true;
            this.player  = false;
            this.circleSelected = new ArrayList<Circle>();
            this.circlePlayer = new ArrayList<Circle>();
            return;
        }

        if (this.player){

            // get touches on circles of the game
            for (int i=0; i<this.circleGames.size(); i++){
                Circle ci = this.circleGames.get(i);

                double distance = this.distance(ci.getX(), ci.getY(), motionEvent.getX(), motionEvent.getY());
                if (distance < ci.getRaio()){
                    this.circlePlayer.add(ci);
                    break;
                }

            }

            if (this.circlePlayer.size() == this.circleSelected.size()){
                int attempts = 0;
                for (int i=0; i<this.circleSelected.size(); i++){
                    Circle circle_selected = this.circleSelected.get(i);
                    Circle circle_player   = this.circlePlayer.get(i);
                    System.out.println("cs: "+circle_selected.getId()+" cp: "+circle_player.getId());

                    if (circle_player.getId() == circle_selected.getId()){
                        attempts++;
                    }

                }

                if (attempts == this.circleSelected.size()){
                    System.err.println("VOCÊ VENCEU...");
                    this.status_player = true;
                }else{
                    this.status_player = false;
                    System.err.println("VOCÊ PERDEU...");
                }

                this.playing = false;
                this.end_game = true;
            }

            System.err.println("Circles Selected Randomly: "+this.circleSelected.size());
            System.err.println("Circles Player: "+this.circlePlayer.size());

            return;
        }

    }

    // draw text of init game
    public void drawText(Canvas canvas){
        Paint p = new Paint();
        p.setTextAlign(Paint.Align.CENTER);
        p.setTextSize(24);
        p.setColor(Color.WHITE);

        if (!this.playing) {
            canvas.drawText("> Aperte na tela para iniciar o jogo", this.width / 2, this.heigth + 100, p);
        }else{
            // play is on
            if (this.player){
                canvas.drawText("> Sua Vez de Jogar", this.width/2, this.heigth+100, p);
            }else{
                canvas.drawText("> Observe os movimentos", this.width/2, this.heigth+100, p);
            }
        }
    }

    // draw the moviments randomly
    public void move_randomly(Canvas canvas){
        if (!this.player && this.playing && this.circleSelected.size() < this.level){

            Random r = new Random();

            int position = r.nextInt(3);
            int color = r.nextInt(3);

            System.err.println("Circle selected: "+position+ "");

            Circle circle = this.circleGames.get(position);

            circle.draw(canvas, this.colors[color]);
            this.circleSelected.add(circle);

            if (this.circleSelected.size() == this.level)
                this.player = true;

        }else{

        }
    }

    // returns the delay
    public int delay(){
        if (!this.playing) return 100;
        if (this.playing && !this.player) return 800;

        return 250;
    }

}
