package org.sahaj;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sahaj.helper.Constants;
import org.sahaj.player.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws IOException, ParseException {
        App app = new App();
        JSONObject jsonObject = app.getJsonObject("input.json");
        int totalSimulation = Integer.parseInt(jsonObject.get("simulations").toString());

        int[][] ladders = app.extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = app.extractArrayData(jsonObject, "snakes", "head", "tail");

        List<Player> simulations = new ArrayList<>();

        SnakeNLadder snakeNLadder = new SnakeNLadder(snakes, ladders);

        while (totalSimulation > 0) {
            logger.trace("Simulation..{}", totalSimulation);
            snakeNLadder = snakeNLadder.withPlayer(new Player(Constants.PLAYER1.value));
            while (!snakeNLadder.isGameOver()) {
                snakeNLadder.play(0);
            }
            simulations.add(snakeNLadder.player);
            totalSimulation--;
        }
        simulations.forEach(player -> logger.info("{}\n", player.toString()));
        GameStats gameStats = new GameStats(simulations);
        gameStats.printStats();

    }

    public JSONObject getJsonObject(String fileName) throws IOException, ParseException {
        InputStream is = App.class.getClassLoader().getResourceAsStream(fileName);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8));
        return jsonObject;
    }

    public int[][] extractArrayData(JSONObject jsonObject, String parameter, String data1, String data2) throws IllegalArgumentException {
        int[][] res;
        JSONArray jsonArray = (JSONArray) jsonObject.get(parameter);
        res = new int[jsonArray.size()][2];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = (JSONObject) jsonArray.get(i);
            if (data.get(data1) == null || data.get(data2) == null || "".equals(data.get(data1).toString()) || "".equals(data.get(data2).toString())) {
                throw new IllegalArgumentException("Snake and Ladder values can't be null or blank");
            } else if (Integer.parseInt(data.get(data1).toString()) <= 0 || Integer.parseInt(data.get(data2).toString()) <= 0) {
                throw new IllegalArgumentException("Snake and Ladder values should positive integers greater than 0");
            }
            res[i][0] = Integer.parseInt(data.get(data1).toString());
            res[i][1] = Integer.parseInt(data.get(data2).toString());
        }
        return res;
    }
}
