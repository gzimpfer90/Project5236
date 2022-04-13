package com.example.project5236;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Lightbutton {
    private Bitmap imageOn;
    private Bitmap imageOff;
    private Bitmap currentImage;
    private int xCord;
    private int yCord;
    private Rect detectCollision;
    private Light light;
    private boolean on = true;

    public Lightbutton(Bitmap on, Bitmap off, int x, int y, Light l){
        xCord = x;
        yCord = y;
        light = l;
        imageOn = on;
        imageOff = off;
        currentImage = on;
        detectCollision = new Rect(x, y, x + on.getWidth(), y + on.getHeight());
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void pressButton(){
        light.switchLight();
        if (on) {
            currentImage = imageOff;
        } else {
            currentImage = imageOn;
        }
        on = !on;
    }

    public void reset(){
        currentImage = imageOn;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, xCord, yCord, null);
    }


}
