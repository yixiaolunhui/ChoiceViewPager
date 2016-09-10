package com.dalong.choiceviewpager.entity;

/**
 * Created by zhouweilong on 16/9/10.
 */

public class Card {
    private int img;
    private String name;

    public Card() {
    }

    public Card(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Card{" +
                "img=" + img +
                ", name='" + name + '\'' +
                '}';
    }
}
