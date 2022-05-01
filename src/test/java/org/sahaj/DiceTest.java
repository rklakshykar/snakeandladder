package org.sahaj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.sahaj.core.Dice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiceTest {

    Dice dice;

    @BeforeEach
    void setup() {
        dice = new Dice();
    }


    @Test
    @DisplayName("Roll Dice Success")
    void rollDiceSuccess() {
        dice.rollDice();
        assertTrue(dice.getRolledValue() <= 6);
    }

    @RepeatedTest(3)
    @DisplayName("Roll Dice Repeat Success")
    void rollDiceRepeatSuccess() {
        dice.rollDice();
        assertTrue(dice.getRolledValue() <= 6);
    }

}
