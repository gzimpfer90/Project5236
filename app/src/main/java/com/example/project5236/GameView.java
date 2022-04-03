package com.example.project5236;


public class GameView {
    private boolean playing;
    private Level level = null;

    public void setGameViewComponents(Level l) {
        level = l;
    }

    public void run() {
        while (playing) {
            update();
            draw();
        }
    }

    private void update() {
        level.getPlayer().update();
    }

    private void draw() {

    }
}
