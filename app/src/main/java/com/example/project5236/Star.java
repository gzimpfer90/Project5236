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
    private Rect detectCollision;
    private Rect detectVisibility;
    private boolean touched = false;
    private boolean visible = false;
    private Paint p = new Paint();
    private Path st;

    public Star(int x, int y){
        xCord = x;
        yCord = y;
        st = new Path();
        buildStar();
        detectCollision = new Rect(x, y, x + 70, y + 70);
        detectVisibility = new Rect(x - 100, y - 100, x + 170, y + 170);
    }

    private void buildStar() {
        p.setColor(Color.YELLOW);
        st.moveTo(xCord+30, yCord);
        st.lineTo(xCord+21, yCord+21);
        st.lineTo(xCord, yCord+21);
        st.lineTo(xCord+18, yCord+36);
        st.lineTo(xCord+9, yCord+63);
        st.lineTo(xCord+30, yCord+48);
        st.lineTo(xCord+51, yCord+63);
        st.lineTo(xCord+42, yCord+36);
        st.lineTo(xCord+60, yCord+21);
        st.lineTo(xCord+39, yCord+21);
        st.lineTo(xCord+30, yCord);
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
