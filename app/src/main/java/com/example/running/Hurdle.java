package com.example.running;

public class Hurdle extends DrawbleObject {
    boolean isCrashed;

    public Hurdle(double posX, double posY, double width, double height, double speed) {
        super(posX, posY, width, height);
        velX = speed;
        isCrashed = false;
    }
}
