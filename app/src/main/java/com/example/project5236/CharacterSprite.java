package com.example.project5236;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    private Bitmap image;
    public float x,y;
    public float xVelocity = 0.01f;
    public float yVelocity = 0.01f;

    public CharacterSprite(Bitmap bmp) {
        image = bmp;
        x = 150;
        y = 150;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x - 50, y - 50, null);
    }

    public void update(float touchX, float touchY) {
        x += (touchX - x) * xVelocity;
        y += (touchY - y) * yVelocity;
    }
}
