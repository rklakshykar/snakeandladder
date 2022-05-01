package org.sahaj;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sahaj.core.Board;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class BoardTest {

    App app;

    @BeforeEach
     void init() {
        app = new App();
    }

    @Test
    @DisplayName("BoardTestSuccess")
     void boardTestSuccess() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input.json");
        int[][] ladders = app.extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = app.extractArrayData(jsonObject, "snakes", "head", "tail");
        Board board = new Board().withLadders(ladders).withSnakes(snakes);
        assertTrue(true);
    }

    @Test
    @DisplayName("BoardTestFail")
     void boardTestFail() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input3.json");
        int[][] ladders = app.extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = app.extractArrayData(jsonObject, "snakes", "head", "tail");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Board().withLadders(ladders).withSnakes(snakes));
        assertEquals("Snake and Ladder value conflicting", exception.getMessage());
    }


}
