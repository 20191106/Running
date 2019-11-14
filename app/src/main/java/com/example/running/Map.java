package com.example.running;

import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

public class Map extends DrawbleObject {
    int speed = -5;
    ArrayList<Hurdle> hurdles = new ArrayList<>();

    final static int GROUND = 850;

    public Map(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        velX = speed;
    }

    public void makeHurdle(int h){
        hurdles.add(new Hurdle(width, GROUND - h, 100, h, speed));
    }

    public void moving(){
        for (int i = 0; i < hurdles.size(); i++){
            Log.d("ah",hurdles.get(i).velX+"");
            hurdles.get(i).moving();
        }
        super.moving();
    }
}
