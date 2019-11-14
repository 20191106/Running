package com.example.running;

import java.util.ArrayList;

public class MyObject extends DrawbleObject{
    int life;
    ArrayList<Item> item = new ArrayList<>();

    public MyObject(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
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
