package org.sahaj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sahaj.core.Board;
import org.sahaj.helper.Constants;
import org.sahaj.player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SnakeNLadderTest {
    private static final Logger logger = LogManager.getLogger(SnakeNLadderTest.class);

    App app;

    @BeforeEach
    void init() {
        app = new App();
    }

    @Test
    @DisplayName("SnakeNLadderSuccessTest")
    void snakeNLadderSuccessTest() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input.json");
        //this test runs only 1 simulation
        int[][] ladders = app.extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = app.extractArrayData(jsonObject, "snakes", "head", "tail");
        SnakeNLadder snakeNLadder = new SnakeNLadder(snakes, ladders).withPlayer(new Player(Constants.PLAYER1.value));
        while (!snakeNLadder.isGameOver()) {
            snakeNLadder.play(0);
        }
        assertEquals(true, snakeNLadder.isGameOver());
    }

    @Test
    @DisplayName("SnakeNLadderSuccessTest")
    void snakeNLadderNSimulationTest() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input.json");
        int totalSimulation = Integer.parseInt(jsonObject.get("simulations").toString());
        int[][] ladders = app.extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = app.extractArrayData(jsonObject, "snakes", "head", "tail");
        List<Player> simulations = new ArrayList<>();
        SnakeNLadder snakeNLadder = new SnakeNLadder(snakes, ladders);
        while (totalSimulation > 0) {
            snakeNLadder = snakeNLadder.withPlayer(new Player(Constants.PLAYER1.value));
            while (!snakeNLadder.isGameOver()) {
                snakeNLadder.play(0);
            }
            simulations.add(snakeNLadder.player);
            totalSimulation--;
        }
        simulations.forEach(player -> logger.info("{}\n", player));
        GameStats gameStats = new GameStats(simulations);
        gameStats.printStats();
        assertTrue(true);
    }


}
