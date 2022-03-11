package com.example.project5236;

public class Lightbutton {
    private int xCord;
    private int yCord;
    private Light light;

    public Lightbutton(int x, int y, Light l){
        xCord = x;
        yCord = y;
        light = l;
    }

    public int getxCord(){
        return xCord;
    }

    public int getyCord(){
        return yCord;
    }

    public void pressButton(){
        light.switchLight();
    }


}
