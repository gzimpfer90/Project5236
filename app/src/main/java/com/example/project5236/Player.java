package com.example.project5236;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {
    private Bitmap bitmap;

    private int xCord;
    private int yCord;

    private final int minX = 0;
    private final int maxX = 1000;
    private final int minY = 0;
    private final int maxY = 1000;

    public Player(Context context){
        xCord = 100;
        yCord = 100;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        bitmap = Bitmap.createScaledBitmap(bitmap, 120, 150, false);
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

    public Bitmap getBitmap() { return bitmap; }

    public void setCord(int x, int y) {
        xCord = x;
        yCord = y;
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
