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

    List<Integer> climbs;

    List<Integer> slides;

    List<Integer> longestTurn;

    int luckyRolls;

    int unluckyRolls;

    public Player(String name) {
        this.name = name;
        this.currentPosition = 0;
        this.longestTurn = new ArrayList<>();
        this.climbs = new ArrayList<>();
        this.slides = new ArrayList<>();
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

    public List<Integer> getClimbs() {
        return climbs;
    }

    public void setClimbs(List<Integer> climbs) {
        this.climbs = climbs;
    }

    public void setSlides(List<Integer> slides) {
        this.slides = slides;
    }

    public void climb(int climbTo) {
        this.climbs.add(climbTo - this.currentPosition);
        this.luckyRolls++;
        this.currentPosition = climbTo;
    }

    public List<Integer> getSlides() {
        return slides;
    }

    public void slide(int slideTo) {
        this.slides.add(this.currentPosition - slideTo);
        this.unluckyRolls++;
        this.currentPosition = slideTo;
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
                ", longestTurn=" + longestTurn +
                ", luckyRolls=" + luckyRolls +
                ", unluckyRolls=" + unluckyRolls +
                '}';
    }
}
