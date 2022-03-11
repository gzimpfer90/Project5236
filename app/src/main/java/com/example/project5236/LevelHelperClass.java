package com.example.project5236;

public class LevelHelperClass {
    int complete, stars;

    public LevelHelperClass() {
    }

    public LevelHelperClass(int complete, int stars) {
        this.complete = complete;
        this.stars = stars;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
