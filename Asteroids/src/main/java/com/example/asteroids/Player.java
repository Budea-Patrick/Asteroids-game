package com.example.asteroids;

public class Player {

    private String name;
    private Integer score;


    public Player() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score.toString();
    }

    public void setScore(long score) {
        this.score = Math.toIntExact(score);
    }
}
