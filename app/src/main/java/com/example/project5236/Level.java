package com.example.project5236;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Level extends View {
    private Player p;
    private Lightbutton[] bList;
    private Paint lineColor;

    public Level(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        lineColor.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
    }
}
