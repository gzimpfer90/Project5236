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
    private boolean isOn;
    private Rect detectCollision;

    public Light(Bitmap image, int startX, int startY){
        x = startX;
        y = startY;
        isOn = true;
        currentImage = image;
        detectCollision = new Rect(startX, startY, startX + image.getWidth(), startY + image.getHeight());
        Log.d("Light", "Current " + detectCollision.left + " " + detectCollision.right);
        Log.d("Light", "Current " + detectCollision.top + " " + detectCollision.bottom);
    }

    public Rect getDetectCollision() {
        return detectCollision;
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
