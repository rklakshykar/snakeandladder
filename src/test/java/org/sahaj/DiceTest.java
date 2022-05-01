package org.sahaj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.sahaj.core.Dice;

public class DiceTest {

    Dice dice;

    @BeforeEach
    public void setup(){
        dice = new Dice();
    }

    @RepeatedTest(3)
    @DisplayName("Roll Dice Success")
    void rollDiceSuccess(){
        dice.rollDice();
        assert (dice.getRolledValue()<=6);
    }

}
