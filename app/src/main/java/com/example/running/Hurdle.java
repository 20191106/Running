package com.example.running;

public class Hurdle extends DrawbleObject {
    public Hurdle(int posX, int posY, int width, int height, int speed) {
        super(posX, posY, width, height);
        velX = speed;
    }
}
