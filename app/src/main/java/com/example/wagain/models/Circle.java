package com.example.wagain.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle {

    private int id;
    private float x;
    private float y;
    private int raio;
    private Paint paint;

    public Circle(int id, float x, float y, int raio, Paint paint) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.raio = raio;
        this.paint = paint;
        System.err.println("X: "+x+" Y:"+y+" Raio: "+raio);
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(getX(), getY(), getRaio(), getPaint());
    }

    public void drawLight(Canvas canvas, int color){
        getPaint().setColor(color);
        canvas.drawCircle(getX(), getY(), getRaio(), getPaint());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }
}
