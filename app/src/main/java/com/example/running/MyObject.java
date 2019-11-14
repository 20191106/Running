package com.example.running;

import android.util.Log;

import java.util.ArrayList;

public class MyObject extends DrawbleObject{
    int life;
    ArrayList<Item> item = new ArrayList<>();

    int state, k;
    final int NORMAL = 0;
    final int RUN = 1;
    final int JUMP = 2;

    public MyObject(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        life = 5;
        state = 0;
        k = 0;
    }

    public void jumping(){
        if (posY == Map.GROUND - height){
            velY = -30;
            accY = 1;
            state = JUMP;
        }
    }

    public void moving(){
        if (state == JUMP) {
            if (posY > Map.GROUND - height){
                velY = 0;
                accY = 0;
                k = 0;
                posY = Map.GROUND - height;
                state = NORMAL;
            }
        }

        if (state == NORMAL){
            if(k > 15){
                state = RUN;
                k = 0;
            }
            k++;
        }else if (state == RUN){
            if(k > 15){
                state = NORMAL;
                k = 0;
            }
            k++;
        }

        super.moving();
    }

    public void usingItem(int idx){
        if (item.size() > idx){
            switch (item.get(idx).type){
                case 0:
                    break;
            }

            item.remove(idx);
        }
    }
}
