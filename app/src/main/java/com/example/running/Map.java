package com.example.running;

import android.graphics.Rect;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Map extends DrawbleObject {
    double speed = -12;

    ArrayList<Hurdle> hurdles = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();

    final static int GROUND = 850;

    public Map(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        velX = speed;
    }

    public void makeHurdle(int h, int width){
        hurdles.add(new Hurdle(1920, GROUND - h, width, h, speed));
    }

    public void makeItem(int h, int type){
        items.add(new Item(1920, GROUND - h, 150, 150, speed, type));
    }

    public void moving(){
        for (int i = 0; i < hurdles.size(); i++){
            hurdles.get(i).moving();
            if (hurdles.get(i).posX < -hurdles.get(i).width - 150){
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
    public MyObject checkGetItem(MyObject o1){
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
    }
}
