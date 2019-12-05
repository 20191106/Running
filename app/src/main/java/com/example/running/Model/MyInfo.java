package com.example.running.Model;

import android.util.Log;

import java.util.ArrayList;

public class MyInfo {
    public String name;
    public int gold;
    public int topscore;
    public ArrayList<MyItem> heads = new ArrayList<>();
    public ArrayList<MyItem> bodies = new ArrayList<>();
    public ArrayList<MyItem> weapons = new ArrayList<>();

    public MyInfo(String name, int gold, int topscore, ArrayList<MyItem> heads, ArrayList<MyItem> bodies, ArrayList<MyItem> weapons) {
        this.name = name;
        this.gold = gold;
        this.topscore = topscore;
        for (int i = 0; i < heads.size(); i++) {
            this.heads.add(new MyItem(heads.get(i).type));
        }
        for (int i = 0; i < bodies.size(); i++) {
            this.bodies.add(new MyItem(bodies.get(i).type));
        }
        for (int i = 0; i < weapons.size(); i++) {
            this.weapons.add(new MyItem(weapons.get(i).type));
        }
    }
}
