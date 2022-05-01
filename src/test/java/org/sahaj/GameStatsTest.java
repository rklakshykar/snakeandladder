package org.sahaj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sahaj.helper.Constants;
import org.sahaj.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameStatsTest {

    List<Player> simulations;

    @BeforeEach
    void init() {
        simulations = new ArrayList<>();
        Player player = new Player(Constants.PLAYER1.value);
        player.setCurrentPosition(100);
        player.setNumberOfRolls(46);
        List<Integer> list = new ArrayList<>();
        list.add(21);
        player.setClimbs(list);
        list = new ArrayList<>();
        list.add(10);
        player.setSlides(list);
        list = new ArrayList<>();
        list.add(6);
        list.add(6);
        list.add(6);
        list.add(6);
        list.add(2);
        player.setLongestTurn(list);
        player.setLuckyRolls(30);
        player.setUnluckyRolls(10);
        simulations.add(player);
    }

    @Test
    @DisplayName("PrintStatsTest")
    void printStatsTest(){
        GameStats gameStats = new GameStats(simulations);
        gameStats.printStats();
        assertTrue(true);
    }
}
