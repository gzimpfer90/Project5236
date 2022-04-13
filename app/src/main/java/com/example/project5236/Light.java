package com.example.project5236;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Light {
    private Bitmap imageOn;
    private Bitmap imageOff;
    private Bitmap currentImage;
    public float x, y;
    private boolean isOn;

    public Light(Bitmap image, int startX, int startY){
        x = startX;
        y = startY;
        isOn = true;
        currentImage = image;
    }

    public void switchLight(){
        isOn = !isOn;
    }

    public void reset(){
        isOn = true;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, x, y, null);
    }
}
