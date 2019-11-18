package com.example.running;

import android.util.Log;

import java.util.ArrayList;

public class MyObject extends DrawbleObject{
    int life;
    int gold;

    int state, k;
    int jumpStack;
    final int NORMAL = 0;
    final int RUN = 1;
    final int JUMP = 2;

    public MyObject(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        life = 5;
        gold = 0;
        state = 0;
        k = 0;
        jumpStack = 2;
    }

    public void jumping(){
        if (jumpStack > 0){
            velY = -40;
            accY = 1.4;
            state = JUMP;
            jumpStack--;
        }
    }

    public void moving(){

        // when landing on ground
        if (state == JUMP) {
            if (posY > Map.GROUND - height){
                velY = 0;
                accY = 0;
                k = 0;
                posY = Map.GROUND - height;
                jumpStack = 2;
                state = NORMAL;
            }
        }

        // when running
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
}
