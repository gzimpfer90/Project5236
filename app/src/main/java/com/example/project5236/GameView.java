package com.example.project5236;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private boolean playing;
    private Thread gameThread = null;
    private Level level;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

//    public void setGameViewComponents(Level l) {
//        level = l;
//    }

    public GameView(Context context) {
        super(context);

        level = new Level(context);

        surfaceHolder = getHolder();
        paint = new Paint();
    }

    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        level.getPlayer().update();
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(level.getPlayer().getBitmap(),
                    level.getPlayer().getxCord(),
                    level.getPlayer().getyCord(),
                    paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int)motionEvent.getX();
        int y = (int)motionEvent.getY();

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                level.getPlayer().setCord(x, y);
                break;
        }
        return true;
    }
}
