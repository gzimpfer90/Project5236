package com.example.project5236;

public class Player {
    private int xCord;
    private int yCord;

    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    public Player(){
        xCord = 0;
        yCord = 0;

    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public void moveUp(int d){
        yCord += d;
    }

    public void moveDown(int d){
        yCord -= d;
    }

    public void moveLeft(int d){
        xCord -= d;
    }

    public void moveRight(int d){
        xCord += d;
    }

    public void update() {
        if (xCord < minX) {
            xCord = minX;
        }
        if (xCord > maxX) {
            xCord = maxX;
        }
        if (yCord < minY) {
            yCord = minY;
        }
        if (yCord > maxY) {
            yCord = maxY;
        }
    }
}
