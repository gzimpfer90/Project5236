package com.example.project5236;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Level {

    public int screenHeight,screenWidth, topLeftX, topLeftY, bottomRightX, bottomRightY, sideLength;
    public float xVelocity = 0.01f;
    public float yVelocity = 0.01f;
    Paint paint = new Paint();


    /*Level 1 Specific */
    public final int playerStartX;
    public final int playerStartY;

    public Level(int height, int width) {
        screenHeight = height;
        screenWidth = width;

        calculateLevelDimensions();

        /*Level 1 Specific */
        playerStartX = bottomRightX;
        playerStartY = bottomRightY;

    }

    private void calculateLevelDimensions() {
        if(screenHeight > screenWidth) {
            topLeftX = 0;
            topLeftY = screenHeight / 4;
            bottomRightX = screenWidth;
            bottomRightY = screenHeight - (screenHeight / 4);
            sideLength = screenWidth;
        }
        else {
            topLeftX = screenWidth / 4;
            topLeftY = 0;
            bottomRightX = screenWidth - (screenWidth / 4);
            bottomRightY = screenHeight;
            sideLength = screenHeight;
        }
    }

    public void draw(Canvas canvas) {

        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(topLeftX, topLeftY, bottomRightX, bottomRightY, paint);
    }

}
