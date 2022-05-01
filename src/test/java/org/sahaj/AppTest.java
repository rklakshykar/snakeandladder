package org.sahaj;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sahaj.helper.Constants;
import org.sahaj.player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    App app;

    @BeforeEach
    public void init() {
        app = new App();
    }

    @Test
    @DisplayName("extractArrayDataSuccess")
    public void extractArrayDataSuccessTest() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input.json");
        int[][] ladders = app.extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = app.extractArrayData(jsonObject, "snakes", "head", "tail");
        assertEquals(app.extractArrayData(jsonObject, "ladders", "top", "bottom").length, 8);
        assertEquals(app.extractArrayData(jsonObject, "snakes", "head", "tail").length, 8);
    }

    @Test
    @DisplayName("Negative Value Test")
    public void extractArrayDataFailedNegativeValueTest() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input1.json");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> app.extractArrayData(jsonObject, "snakes", "head", "tail"));
        assertEquals("Snake and Ladder values should positive integers greater than 0", exception.getMessage());
    }

    @Test
    @DisplayName("Negative Blank Test")
    public void extractArrayDataFailedBlankValueTest() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input1.json");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> app.extractArrayData(jsonObject, "ladders", "top", "bottom"));
        assertEquals("Snake and Ladder values can't be null or blank", exception.getMessage());
    }
}
