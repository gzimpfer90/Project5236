package com.example.project5236;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Winbutton {
    private Bitmap image;
    private int xCord;
    private int yCord;
    private Rect detectCollision;
    private boolean on = true;

    public Winbutton(Bitmap bitmap, int x, int y){
        xCord = x;
        yCord = y;
        int width = x / 3;
        int height = x / 4;
        image = Bitmap.createScaledBitmap(bitmap, width, height, false);
        detectCollision = new Rect(x, y, x + image.getWidth(), y + image.getHeight());
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, xCord, yCord, null);
    }


}
