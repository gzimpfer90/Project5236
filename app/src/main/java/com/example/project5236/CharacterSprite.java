package com.example.project5236;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    private Bitmap image;
    public float x,y;
    public float xVelocity = 0.01f;
    public float yVelocity = 0.01f;
    public int maxX;
    public int maxY;
    public int minX;
    public int minY;

    public CharacterSprite(Bitmap bmp, int startX, int startY) {
        image = bmp;
        x = startX;
        y = startY;
    }

    /* Level 1 Specific */
    public void createBounds(int xMax, int yMax, int xMin, int yMin) {
        maxX = xMax;
        maxY = yMax;
        minX = xMin;
        minY = yMin;
        x -= image.getWidth();
        y -= image.getHeight();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update(float touchX, float touchY) {
        float newX = x + ((touchX - x) * xVelocity);
        float newY = y + ((touchY - y) * yVelocity);
        if(!(newX <= minX || newX >= (maxX - image.getWidth()) || newY <= minY || newY >= (maxY - image.getHeight()))) {
            x = newX;
            y = newY;
        }
    }
}
