package com.example.project5236;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

public class Star {
    private int xCord;
    private int yCord;
    private int squareWidth, squareHeight;
    private Rect detectCollision;
    private Rect detectVisibility;
    private boolean touched = false;
    private boolean visible = false;
    private Paint p = new Paint();
    private Path st;

    public Star(int x, int y, Level level){
        xCord = x;
        yCord = y;
        squareWidth = level.bottomRightX - level.topLeftX;
        squareHeight = level.bottomRightY - level.topLeftY;
        st = new Path();
        buildStar();
        detectCollision = new Rect(x, y, x + 70, y + 70);
        detectVisibility = new Rect(x - 100, y - 100, x + 170, y + 170);
    }

    private void buildStar() {
        int x30 = (int) (0.0278 * squareWidth);
        int x9 = (int) (0.0083 * squareWidth);
        int x21 = (int) (0.0194 * squareWidth);
        int x18 = (int) (0.0167 * squareWidth);
        int x51 = (int) (0.0472 * squareWidth);
        int x42 = (int) (0.0389 * squareWidth);
        int x60 = (int) (0.0556 * squareWidth);
        int x39 = (int) (0.0361 * squareWidth);
        int y21 = (int) (0.0212 * squareHeight);
        int y36 = (int) (0.0333 * squareHeight);
        int y63 = (int) (0.0637 * squareHeight);
        int y48 = (int) (0.0485 * squareHeight);

        p.setColor(Color.YELLOW);
        st.moveTo(xCord + x30, yCord);
        st.lineTo(xCord + x21, yCord + y21);
        st.lineTo(xCord, yCord + y21);
        st.lineTo(xCord + x18, yCord + y36);
        st.lineTo(xCord + x9, yCord + y63);
        st.lineTo(xCord + x30, yCord + y48);
        st.lineTo(xCord + x51, yCord + y63);
        st.lineTo(xCord + x42, yCord + y36);
        st.lineTo(xCord + x60, yCord + y21);
        st.lineTo(xCord + x39, yCord + y21);
        st.lineTo(xCord + x30, yCord);
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

    public boolean getTouched() { return touched; }

    public void touched(){
        touched = true;
        //increment star counter
    }

    public void reset(){
        touched = false;
        //decrement star counter
    }

    public void draw(Canvas canvas) {
        p.setColor(Color.YELLOW);
        canvas.drawPath(st, p);
        if(!visible || touched) {
            p.setColor(Color.BLACK);
            canvas.drawRect(xCord, yCord, xCord + 70, yCord + 70, p);
        }
    }


}
