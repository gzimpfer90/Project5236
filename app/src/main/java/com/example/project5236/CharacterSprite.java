package com.example.project5236;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class CharacterSprite {

    private Bitmap image;
    public float x,y;
    public float spawnX, spawnY;
    public float velocity = 0.05f;
    public int maxX;
    public int maxY;
    public int minX;
    public int minY;

    public CharacterSprite(Bitmap bmp, int startX, int startY) {
        image = bmp;
        x = startX;
        y = startY;
        spawnX = startX;
        spawnY = startY;
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

    public void draw(Canvas canvas) { canvas.drawBitmap(image, x - 65, y - 65, null); }

    public void update(float touchX, float touchY) {
        float newX = x + ((touchX - x) * velocity);
        float newY = y + ((touchY - y) * velocity);
        float xCorner = newX - 65;
        float yCorner = newY - 65;
        if(!(xCorner <= minX || xCorner >= (maxX - image.getWidth()) || yCorner <= minY || yCorner >= (maxY - image.getHeight()))) {
            x = newX;
            y = newY;
        }
    }

    public void reset() {
        x = spawnX;
        y = spawnY;
    }
}
