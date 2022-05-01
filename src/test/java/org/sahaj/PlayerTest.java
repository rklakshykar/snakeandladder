package org.sahaj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sahaj.helper.Constants;
import org.sahaj.player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    Player player;

    @BeforeEach
    void init() {
        player = new Player(Constants.PLAYER1.value);
    }

    @Test
    @DisplayName("MoveSuccess")
    void MoveSuccess() {
        player.setCurrentPosition(10);
        player.move(5);
        assertEquals(15, player.getCurrentPosition());
    }

    @Test
    @DisplayName("ClimbSuccess")
    void ClimbSuccess() {
        player.setCurrentPosition(10);
        player.climb(15);
        player.climb(30);
        player.climb(45);
        assertEquals(3, player.getClimbs().size());
    }

    @Test
    @DisplayName("SlideSuccess")
    void SlideSuccess() {
        player.setCurrentPosition(10);
        player.slide(50);
        player.slide(45);
        player.slide(15);
        assertEquals(3, player.getSlides().size());
    }

}
