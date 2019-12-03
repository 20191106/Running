package com.example.running.Game;

import android.util.Log;

public class DrawbleObject {
    public double posX;
    public double posY;
    public double width;
    public double height;
    public double velX;
    public double velY;
    public double accX;
    public double accY;

    public DrawbleObject(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        velX = 0;
        velY = 0;
        accX = 0;
        accY = 0;
    }

    public void moving(){
        velX += accX;
        velY += accY;
        posX += velX;
        posY += velY;
    }
}
