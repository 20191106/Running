package com.example.running.Game;

public class Hurdle extends DrawbleObject {
    public boolean isCrashed;

    public Hurdle(double posX, double posY, double width, double height, double speed) {
        super(posX, posY, width, height);
        velX = speed;
        isCrashed = false;
    }
}
