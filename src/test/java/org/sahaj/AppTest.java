package org.sahaj;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for simple App.
 */
class AppTest {
    App app;

    @BeforeEach
    void init() {
        app = new App();
    }

    @Test
    @DisplayName("extractArrayDataSuccess")
    void extractArrayDataSuccessTest() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input.json");
        int[][] ladders = app.extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = app.extractArrayData(jsonObject, "snakes", "head", "tail");
        assertEquals(8, app.extractArrayData(jsonObject, "ladders", "top", "bottom").length);
        assertEquals(8, app.extractArrayData(jsonObject, "snakes", "head", "tail").length);
    }

    @Test
    @DisplayName("Negative Value Test")
    void extractArrayDataFailedNegativeValueTest() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input1.json");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> app.extractArrayData(jsonObject, "snakes", "head", "tail"));
        assertEquals("Snake and Ladder values should positive integers greater than 0", exception.getMessage());
    }

    @Test
    @DisplayName("Negative Blank Test")
    void extractArrayDataFailedBlankValueTest() throws IOException, ParseException {
        JSONObject jsonObject = app.getJsonObject("input1.json");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> app.extractArrayData(jsonObject, "ladders", "top", "bottom"));
        assertEquals("Snake and Ladder values can't be null or blank", exception.getMessage());
    }
}
