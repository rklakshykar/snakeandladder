package org.sahaj.player;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player {
    private static final Logger logger = LogManager.getLogger(Player.class);

    int currentPosition;
    final String name;

    int numberOfRolls;

    int climbs;

    int slides;

    int biggestClimb;

    int biggestSlide;

    List<Integer> longestTurn;

    int luckyRolls;

    int unluckyRolls;

    public Player(String name) {
        this.name = name;
        this.currentPosition = 0;
        this.longestTurn = new ArrayList<>();
    }

    public void move(int rolledDiceValue) {
        this.numberOfRolls++;
        if (this.currentPosition == 94 && rolledDiceValue == 6) {
            logger.trace("Found 6 at 94....");
            this.luckyRolls++;
        }
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

    public void climb(int climbTo) {
        this.climbs++;
        this.luckyRolls++;
        if (this.biggestClimb < (climbTo - this.currentPosition)) {
            this.biggestClimb = (climbTo - this.currentPosition);
        }
        this.currentPosition = climbTo;
    }

    public int getSlides() {
        return slides;
    }

    public void slide(int slideTo) {
        this.slides++;
        this.unluckyRolls++;
        if (this.biggestSlide < (this.currentPosition - slideTo)) {
            this.biggestSlide = this.currentPosition - slideTo;
        }
        this.currentPosition = slideTo;
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

    public List<Integer> getLongestTurn() {
        return longestTurn;
    }

    public void setLongestTurn(List<Integer> longestTurn) {
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

    @Override
    public String toString() {
        return "Player{" +
                "currentPosition=" + currentPosition +
                ", name='" + name + '\'' +
                ", numberOfRolls=" + numberOfRolls +
                ", climbs=" + climbs +
                ", slides=" + slides +
                ", biggestClimb=" + biggestClimb +
                ", biggestSlide=" + biggestSlide +
                ", longestTurn=" + longestTurn +
                ", luckyRolls=" + luckyRolls +
                ", unluckyRolls=" + unluckyRolls +
                '}';
    }
}
