package com.example.running.Model;

import java.util.ArrayList;

public class MyInfo {
    String name;
    int gold;
    int topscore;
    int[] head = new int[10];
    int[] body = new int[10];
    int[] weapon = new int[10]; // 0 = NONE

    public MyInfo(String name, int gold, int topscore, int[] head, int[] body, int[] weapon) {
        this.name = name;
        this.gold = gold;
        this.topscore = topscore;
        this.head = head;
        this.body = body;
        this.weapon = weapon;
    }
}
