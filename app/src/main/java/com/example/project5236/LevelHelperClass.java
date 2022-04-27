package com.example.project5236;

public class LevelHelperClass {
    int complete, stars, score;

    public LevelHelperClass() {
    }

    public LevelHelperClass(int complete, int stars, int score) {
        this.complete = complete;
        this.stars = stars;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
