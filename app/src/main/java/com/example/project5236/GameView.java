package com.example.project5236;

import static java.lang.String.valueOf;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

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
    private Lightbutton lButton;
    private Winbutton wButton;
    private boolean buttonPressing = false;
    private boolean completed = false;

    //instantiate popup window
    View pwView = inflate(getContext(), R.layout.popup_win, null);
    Button backBtn = (Button) pwView.findViewById(R.id.backBtn);
    Button restartBtn = (Button) pwView.findViewById(R.id.restartBtn);
    Button nextBtn = (Button) pwView.findViewById(R.id.nextBtn);
    PopupWindow popupWindow = new PopupWindow(pwView, ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT, true);

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
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(),
                R.drawable.smileyresize), level.playerStartX, level.playerStartY);
        light = new Light(BitmapFactory.decodeResource(getResources(), R.drawable.redwide),
                BitmapFactory.decodeResource(getResources(), R.drawable.blackbox),
                level.topLeftX, level.topLeftY + 200);
        lButton = new Lightbutton(BitmapFactory.decodeResource(getResources(), R.drawable.buttonon),
                BitmapFactory.decodeResource(getResources(), R.drawable.buttonoff), 0,
                level.bottomRightY - 300, light);
        wButton = new Winbutton(BitmapFactory.decodeResource(getResources(), R.drawable.buttonwin),
                600, level.topLeftY);
        characterSprite.createBounds(level.bottomRightX, level.bottomRightY, level.topLeftX,
                level.topLeftY);
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
        if (completed) {
            addPopup();
        } else {
            int index = event.getActionIndex();
            int action = event.getActionMasked();
            int pointerId = event.getPointerId(index);

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    touchX = event.getX();
                    touchY = event.getY();
                    touching = true;
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    touching = false;
                    return false;
            }
        }
        return false;
    }

    public void update() {
        if (!completed) {
            if (touching) {
                characterSprite.update(touchX, touchY);
            }
            if (Rect.intersects(characterSprite.getDetectCollision(), light.getDetectCollision()) && light.isOn) {
                characterSprite.reset();
                light.reset();
                lButton.reset();
                touching = false;
            }
            if (Rect.intersects(characterSprite.getDetectCollision(), lButton.getDetectCollision())) {
                if (!buttonPressing) {
                    lButton.pressButton();
                    buttonPressing = true;

                }
            } else {
                buttonPressing = false;
            }

            if (Rect.intersects(characterSprite.getDetectCollision(), lButton.getDetectVisibility())) {
                if (!lButton.getVisible()) {
                    lButton.setVisible(true);
                }
            } else {
                lButton.setVisible(false);
            }

            if (Rect.intersects(characterSprite.getDetectCollision(), wButton.getDetectCollision())) {
                completed = true;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas!=null) {
            level.draw(canvas);
            light.draw(canvas);
            lButton.draw(canvas);
            wButton.draw(canvas);
            characterSprite.draw(canvas);
        }
    }

    public void addPopup(){
        //display the popup window
        popupWindow.showAtLocation(this, Gravity.CENTER, 0, 0);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completed = false;
                characterSprite.reset();
                light.reset();
                lButton.reset();
                touching = false;
                popupWindow.dismiss();
            }
        });
    }
}
