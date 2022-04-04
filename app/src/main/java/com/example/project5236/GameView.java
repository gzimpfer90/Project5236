package com.example.project5236;

import static java.lang.String.valueOf;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private String TAG = "Touch";
    private MainThread thread;
    private CharacterSprite characterSprite;
    private boolean touching = true;
    private float touchX = 150;
    private float touchY = 150;
    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.smileyresize));
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
            characterSprite.draw(canvas);
        }
    }
}
