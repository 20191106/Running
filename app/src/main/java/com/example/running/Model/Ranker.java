package com.example.running.Model;

public class Ranker {
    public int rank;
    public int head;
    public int body;
    public int weapon;
    public String name;
    public int topscore;

    public Ranker(int rank, int head, int body, int weapon, String name, int topscore) {
        this.rank = rank;
        this.head = head;
        this.body = body;
        this.weapon = weapon;
        this.name = name;
        this.topscore = topscore;
    }
}
