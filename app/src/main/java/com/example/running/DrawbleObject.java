package com.example.running;

import android.util.Log;

public class DrawbleObject {
    double posX;
    double posY;
    double width;
    double height;
    double velX;
    double velY;
    double accX;
    double accY;

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
