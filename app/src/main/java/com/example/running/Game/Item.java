package com.example.running.Game;

public class Item extends DrawbleObject {
    public int type;

    public Item(double posX, double posY, double width, double height, double speed, int type) {
        super(posX, posY, width, height);
        velX = speed;
        this.type = type;
    }

    public MyObject used(MyObject rabbit){
        switch (type){
            case 1:
                if(rabbit.life < 5) rabbit.life++;
                else rabbit.gold += 500;
                break;
            case 2:
                rabbit.gold += 100;
                break;
        }

        return rabbit;
    }
}
