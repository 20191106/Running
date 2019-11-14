package com.example.running;

import android.util.Log;

public class DrawbleObject {
    int posX;
    int posY;
    int width;
    int height;
    int velX;
    int velY;
    int accX;
    int accY;

    public DrawbleObject(int posX, int posY, int width, int height) {
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
