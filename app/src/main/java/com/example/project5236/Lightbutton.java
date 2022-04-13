package com.example.project5236;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Lightbutton {
    public int x;
    public int y;
    private Bitmap image;

    public Lightbutton(Bitmap bitmap){
        x = 0;
        y = 500;
        image = bitmap;
    }

    public void draw(Canvas canvas) {
        image = Bitmap.createScaledBitmap(image, 100, 100, false);
        canvas.drawBitmap(image, x, y, null);
    }

    public void setImage(Bitmap bitmap) {
        image = bitmap;
    }

}
