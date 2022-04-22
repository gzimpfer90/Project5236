package com.example.project5236;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
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
    public int drawPointx;
    public int drawPointy;
    private int scale;
    private Rect detectCollision;

    public CharacterSprite(Bitmap bmp, int startX, int startY, Level level) {
        scale = (int) ((level.bottomRightX - level.topLeftX) * 0.1);
        image = Bitmap.createScaledBitmap(bmp, scale, scale, false);
        x = startX;
        y = startY;
        spawnX = startX - image.getWidth();
        spawnY = startY - image.getHeight();
        drawPointx = startX - (scale / 2);
        drawPointy = startY - (scale / 2);
        detectCollision = new Rect(drawPointx, drawPointy, image.getWidth(), image.getHeight());
    }

    public int getImageWidth() { return image.getWidth(); }
    public int getImageHeight() { return image.getHeight(); }

    /* Level 1 Specific */
    public void createBounds(int xMax, int yMax, int xMin, int yMin) {
        maxX = xMax;
        maxY = yMax;
        minX = xMin;
        minY = yMin;
        x -= image.getWidth();
        y -= image.getHeight();
    }

    public void draw(Canvas canvas) { canvas.drawBitmap(image, drawPointx, drawPointy, null); }

    public void update(float touchX, float touchY) {
        float newX = x + ((touchX - x) * velocity);
        float newY = y + ((touchY - y) * velocity);
        float xCorner = newX - 65;
        float yCorner = newY - 65;
        if(!(xCorner <= minX || xCorner >= (maxX - image.getWidth()) || yCorner <= minY || yCorner >= (maxY - image.getHeight()))) {
            x = newX;
            y = newY;
        }
        drawPointx = (int) x - 65;
        drawPointy = (int) y - 65;
        detectCollision.left = drawPointx;
        detectCollision.top = drawPointy;
        detectCollision.right = detectCollision.left + image.getWidth();
        detectCollision.bottom = detectCollision.top + image.getHeight();
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void reset() {
        x = spawnX;
        y = spawnY;
        drawPointx = (int) spawnX - (scale / 2);
        drawPointy = (int) spawnY - (scale / 2);
        detectCollision = new Rect(drawPointx, drawPointy, image.getWidth(), image.getHeight());
    }
}
