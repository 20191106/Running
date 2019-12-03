package com.example.running.Game;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class Map extends DrawbleObject {
    public double speed = -12;

    public ArrayList<Hurdle> hurdles = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();

    public final static int GROUND = 850;

    public Map(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        velX = speed;
    }

    public void makeHurdle(int h, int width){
        hurdles.add(new Hurdle(2100, GROUND - h + 50, width, h, speed));
    }

    public void makeItem(int h, int type){
        items.add(new Item(2100, GROUND - h, 150, 150, speed, type));
    }

    synchronized public void moving(){
        for (int i = 0; i < hurdles.size(); i++){
            hurdles.get(i).moving();
            if (hurdles.get(i).posX < 0 - 170){
                hurdles.remove(i);
                i--;
            }
        }

        for (int i = 0; i < items.size(); i++){
            items.get(i).moving();
            if (items.get(i).posX < -items.get(i).width - 150){
                items.remove(i);
                i--;
            }
        }

        super.moving();
        if(posX <= -2880){
            posX = -960;
        }
    }

    // when crash
    public void checkCrash(MyObject o1){
        for (int i = 0; i < hurdles.size(); i++){
            Hurdle o2 = hurdles.get(i);
            if(!o2.isCrashed){
                int paddingX = 40;
                int paddingY = 30;
                Rect r1 = new Rect((int)(o1.posX + paddingX), (int)(o1.posY + paddingY),
                        (int)(o1.posX + o1.width - paddingX), (int)(o1.posY + o1.height - paddingY));
                Rect r2 = new Rect((int)o2.posX, (int)o2.posY,
                        (int)(o2.posX + o2.width), (int)(o2.posY + o2.height));
                if(r1.intersect(r2)){
                    o1.life--;
                    o2.isCrashed = true;
                }
            }
        }
    }

    // when get item
    synchronized public MyObject checkGetItem(MyObject o1){
        for (int i = 0; i < items.size(); i++){
            Item o2 = items.get(i);
            Rect r1 = new Rect((int)o1.posX, (int)o1.posY,
                    (int)(o1.posX + o1.width), (int)(o1.posY + o1.height));
            Rect r2 = new Rect((int)o2.posX, (int)o2.posY,
                    (int)(o2.posX + o2.width), (int)(o2.posY + o2.height));
            if(r1.intersect(r2)){
                o1 = o2.used(o1);
                items.remove(i);
                i--;
            }
        }
        return o1;
    }

    public boolean checkEnd(MyObject rabbit){
        boolean isEnd = false;
        if(rabbit.life == 0) {
            isEnd = true;
        }
        return isEnd;
    }

    public void changeSpeed(double speed){
        this.speed = speed;
        velX = speed;
        for (int i = 0; i < hurdles.size(); i++){
            hurdles.get(i).velX = speed;
        }
        for (int i = 0; i < items.size(); i++){
            items.get(i).velX = speed;
        }
    }

    public void timeLine(int time){
        Random rand = new Random();

        if (time % 1000 == 0){
            makeItem(rand.nextInt(500) + 150, 1);
        }
        else if (time % 200 == 0){
            makeHurdle(rand.nextInt(300) + 300, 150);
        }
        else if (time % 50 == 0){
            makeItem(rand.nextInt(500) + 150, 2);
        }

        if (time % 300 == 0) {
            changeSpeed(speed - 2);
        }
    }
}
