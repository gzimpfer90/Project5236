package com.example.project5236;

import static java.lang.String.valueOf;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private String TAG = "Touch";
    private MainThread thread;
    private CharacterSprite characterSprite;
    private Level level;
    private int screenHeight;
    private int screenWidth;
    private boolean touching = true;
    private float touchX;
    private float touchY;
    private Light light;
    private Lightbutton[] lButtons;
    public GameView(Context context, int height, int width) {
        super(context);
        screenHeight = height;
        screenWidth = width;
        level = new Level(screenHeight, screenWidth);
        touchX = level.playerStartX;
        touchY = level.playerStartY;
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this, level);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.smileyresize), level.playerStartX, level.playerStartY);
        light = new Light(BitmapFactory.decodeResource(getResources(), R.drawable.red10), level.topLeftX, level.topLeftY);
        characterSprite.createBounds(level.bottomRightX, level.bottomRightY, level.topLeftX, level.topLeftY);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch(action) {
            case MotionEvent.ACTION_DOWN :
            case MotionEvent.ACTION_MOVE :
                touchX = event.getX();
                touchY = event.getY();
                touching = true;
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                touching = false;
                return false;
        }
        return false;
    }

    public void update() {
        if (touching) {
            characterSprite.update(touchX, touchY);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas!=null) {
            level.draw(canvas);
            characterSprite.draw(canvas);
            light.draw(canvas);
        }
    }
}
