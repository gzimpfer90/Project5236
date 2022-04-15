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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private Star star1;
    private Star star2;
    private Star star3;
    private boolean buttonPressing = false;
    private boolean completed = false;
    FirebaseDatabase rootNode;
    DatabaseReference levelsReference;
    DatabaseReference nextReference;

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
        int lightY = level.topLeftY + (int) ((level.bottomRightY - level.topLeftY) * 0.2);
        int lButtonY = level.bottomRightY - (int) ((level.bottomRightX - level.topLeftY) * 0.4);
        int wButtonX = level.topLeftX + (int) ((level.bottomRightX - level.topLeftX) * 0.55);
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(),
                R.drawable.smileyresize), level.playerStartX, level.playerStartY);
        light = new Light(BitmapFactory.decodeResource(getResources(), R.drawable.redwide),
                BitmapFactory.decodeResource(getResources(), R.drawable.blackbox),
                level.topLeftX, lightY);
        lButton = new Lightbutton(BitmapFactory.decodeResource(getResources(), R.drawable.buttonon),
                BitmapFactory.decodeResource(getResources(), R.drawable.buttonoff), level.topLeftX,
                lButtonY, light);
        wButton = new Winbutton(BitmapFactory.decodeResource(getResources(), R.drawable.buttonwin),
                wButtonX, level.topLeftY);
        star1 = new Star(level.topLeftX + 100, level.topLeftY + 100);
        star2 = new Star(level.bottomRightX - 100, level.topLeftY + 50);
        star3 = new Star(level.bottomRightX - 100, level.topLeftY + 500);
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
            rootNode = FirebaseDatabase.getInstance();
            levelsReference = rootNode.getReference("Levels");
            nextReference = rootNode.getReference("NextLevel");

            nextReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String nextName = "Level" + snapshot.getValue().toString();
                    int nextLevelVal = Integer.parseInt(snapshot.getValue().toString());
                    String currentName = "Level" + (nextLevelVal - 1);
                    LevelHelperClass completeUpdate = new LevelHelperClass(1,0 );
                    LevelHelperClass nextUpdate = new LevelHelperClass(0, 0);
                    nextReference.setValue(nextLevelVal + 1);
                    levelsReference.child(currentName).setValue(completeUpdate);
                    levelsReference.child(nextName).setValue(nextUpdate);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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

    private void updateStars() {

        if (Rect.intersects(characterSprite.getDetectCollision(), star1.getDetectVisibility())) {
            if (!star1.getVisible() && !star1.getTouched()) {
                star1.setVisible(true);
            }
        } else {
            star1.setVisible(false);
        }
        if (Rect.intersects(characterSprite.getDetectCollision(), star2.getDetectVisibility())) {
            if (!star2.getVisible() && !star2.getTouched()) {
                star2.setVisible(true);
            }
        } else {
            star2.setVisible(false);
        }
        if (Rect.intersects(characterSprite.getDetectCollision(), star3.getDetectVisibility())) {
            if (!star3.getVisible() && !star3.getTouched()) {
                star3.setVisible(true);
            }
        } else {
            star3.setVisible(false);
        }
        if (Rect.intersects(characterSprite.getDetectCollision(), star1.getDetectCollision())) {
            if (!star1.getTouched()) {
                star1.touched();
            }
        }
        if (Rect.intersects(characterSprite.getDetectCollision(), star2.getDetectCollision())) {
            if (!star2.getTouched()) {
                star2.touched();
            }
        }
        if (Rect.intersects(characterSprite.getDetectCollision(), star3.getDetectCollision())) {
            if (!star3.getTouched()) {
                star3.touched();
            }
        }
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
            updateStars();
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
            star1.draw(canvas);
            star2.draw(canvas);
            star3.draw(canvas);
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
                star1.reset();
                star2.reset();
                star3.reset();
                touching = false;
                popupWindow.dismiss();
            }
        });
    }
}
