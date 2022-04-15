package com.example.project5236;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Light {
    private Bitmap imageOn;
    private Bitmap imageOff;
    private Bitmap currentImage;
    public float x, y;
    public boolean isOn;
    private boolean pastSetting;
    private Rect detectCollision;

    public Light(Bitmap on, Bitmap off, int startX, int startY, Level level){
        int width = level.screenWidth;
        int height = (int) (level.screenHeight * 0.03);
        on = Bitmap.createScaledBitmap(on, width, height, false);
        off = Bitmap.createScaledBitmap(off, width, height, false);
        x = startX;
        y = startY;
        isOn = true;
        pastSetting = true;
        imageOff = off;
        imageOn = on;
        currentImage = on;
        detectCollision = new Rect(startX, startY, startX + on.getWidth(), startY + on.getHeight());
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void switchLight(){
        isOn = !isOn;
        if (isOn) {
            currentImage = imageOn;
        } else {
            currentImage = imageOff;
        }
    }

    public void reset(){
        isOn = true;
        currentImage = imageOn;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, x, y, null);
    }
}
