package org.sahaj.player;

import java.util.ArrayList;

public class Player {

    int currentPosition;
    final String name;

    int numberOfRolls;

    int climbs;

    int slides;

    int biggestClimb;

    int biggestSlide;

    ArrayList<Integer> longestTurn;

    int luckyRolls;

    int unluckyRolls;

    public Player(String name) {
        this.name = name;
        this.currentPosition = 0;
        this.longestTurn = new ArrayList<>();
    }

    public void move(int rolledDiceValue) {
        if ((currentPosition + rolledDiceValue) < 101) {
            this.currentPosition += rolledDiceValue;
        }
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getName() {
        return this.name;
    }

    public int getNumberOfRolls() {
        return numberOfRolls;
    }

    public void setNumberOfRolls(int numberOfRolls) {
        this.numberOfRolls = numberOfRolls;
    }

    public int getClimbs() {
        return climbs;
    }

    public void setClimbs(int climbs) {
        this.climbs = climbs;
    }

    public int getSlides() {
        return slides;
    }

    public void setSlides(int slides) {
        this.slides = slides;
    }

    public int getBiggestClimb() {
        return biggestClimb;
    }

    public void setBiggestClimb(int biggestClimb) {
        this.biggestClimb = biggestClimb;
    }

    public int getBiggestSlide() {
        return biggestSlide;
    }

    public void setBiggestSlide(int biggestSlide) {
        this.biggestSlide = biggestSlide;
    }

    public ArrayList<Integer> getLongestTurn() {
        return longestTurn;
    }

    public void setLongestTurn(ArrayList<Integer> longestTurn) {
        this.longestTurn = longestTurn;
    }

    public int getLuckyRolls() {
        return luckyRolls;
    }

    public void setLuckyRolls(int luckyRolls) {
        this.luckyRolls = luckyRolls;
    }

    public int getUnluckyRolls() {
        return unluckyRolls;
    }

    public void setUnluckyRolls(int unluckyRolls) {
        this.unluckyRolls = unluckyRolls;
    }
}
