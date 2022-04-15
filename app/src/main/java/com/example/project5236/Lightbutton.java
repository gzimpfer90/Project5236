package com.example.project5236;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Lightbutton {
    private Bitmap imageOn;
    private Bitmap imageOff;
    private Bitmap currentImage;
    private int xCord;
    private int yCord;
    private Rect detectCollision;
    private Rect detectVisibility;
    private Light light;
    private boolean on = true;
    private boolean visible = false;
    private Paint p = new Paint();

    public Lightbutton(Bitmap on, Bitmap off, int x, int y, Light l, Level level){
        int scale = (int) (level.screenHeight * 0.08);
        on = Bitmap.createScaledBitmap(on, scale, scale, false);
        off = Bitmap.createScaledBitmap(off, scale, scale, false);
        xCord = x;
        yCord = y;
        light = l;
        imageOn = on;
        imageOff = off;
        currentImage = on;
        detectCollision = new Rect(x, y, x + on.getWidth(), y + on.getHeight());
        detectVisibility = new Rect(x - 100, y - 100, x + on.getWidth() + 100, y + on.getHeight() + 100);
    }
    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Rect getDetectVisibility() {
        return detectVisibility;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean b) {
        visible = b;
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
        on = true;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, xCord, yCord, null);
        if(!visible) {
            p.setColor(Color.BLACK);
            canvas.drawRect(xCord, yCord, xCord + currentImage.getWidth(), yCord + currentImage.getWidth(), p);
        }
    }


}
