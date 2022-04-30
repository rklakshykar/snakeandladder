package org.sahaj.core;

import java.util.Random;

public class Dice {
    int rolledValue;

    public void rollDice(){
        this.rolledValue = new Random().nextInt(6)+1;
    }

    public int getRolledValue(){
        return this.rolledValue;
    }
}
