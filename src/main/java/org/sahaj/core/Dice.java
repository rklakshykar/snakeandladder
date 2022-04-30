package org.sahaj.core;

public class Dice {
    int rolledValue;

    public void rollDice(){
        this.rolledValue = (int) (Math.random()*6) + 1;
    }

    public int getRolledValue(){
        return this.rolledValue;
    }
}
