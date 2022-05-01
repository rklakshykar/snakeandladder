package org.sahaj;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sahaj.core.Board;
import org.sahaj.helper.Constants;
import org.sahaj.player.Player;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


class SnakeNLadderTest {

    App app;

    @BeforeEach
    void init() {
        app = new App();
    }

    @Test
    @DisplayName("SnakeNLadderSuccessTest")
    void snakeNLadderSuccessTest() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input.json");
        int[][] ladders = app.extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = app.extractArrayData(jsonObject, "snakes", "head", "tail");
        SnakeNLadder snakeNLadder = new SnakeNLadder(snakes, ladders).withPlayer(new Player(Constants.PLAYER1.value));
        while (!snakeNLadder.isGameOver()) {
            snakeNLadder.play(0);
        }
        assertEquals(true, snakeNLadder.isGameOver());
    }


}
