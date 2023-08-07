package com.example.steptofitapp;

public class TrainingModel {

    private String name;
    private int img;

    public TrainingModel(String name,int img) {

        this.name = name;
        this.img=img;
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
}
