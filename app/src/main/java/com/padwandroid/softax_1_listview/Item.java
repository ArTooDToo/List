package com.padwandroid.softax_1_listview;

public class Item {
    private String name;
    private int color;
    private int liczba;

    public Item(){

    }

    public Item(String n, int c, int l){
        this.name = n;
        this.color = c;
        this.liczba = l;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getLiczba() {
        return liczba;
    }

    public void setLiczba(int liczba) {
        this.liczba = liczba;
    }
}
