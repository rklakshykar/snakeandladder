package org.sahaj;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sahaj.player.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws IOException, ParseException {
        InputStream is = App.class.getClassLoader().getResourceAsStream("input.json");
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(
                new InputStreamReader(is, StandardCharsets.UTF_8));
        int totalSimulation = Integer.parseInt(jsonObject.get("simulations").toString());
        int[][] ladders = extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = extractArrayData(jsonObject, "snakes", "head", "tail");
        List<Player> simulations = new ArrayList<>();

        while (totalSimulation > 0) {
            logger.trace("Simulation..{}", totalSimulation);
            SnakeNLadder snakeNLadder = new SnakeNLadder(snakes, ladders);
            while (!snakeNLadder.isGameOver()) {
                snakeNLadder.play(0);
            }
            simulations.add(snakeNLadder.player);
            totalSimulation--;
        }
        simulations.forEach(player -> logger.info(player.toString()));

    }


    private static int[][] extractArrayData(JSONObject jsonObject, String parameter, String data1, String data2) {
        int[][] res;
        JSONArray jsonArray = (JSONArray) jsonObject.get(parameter);
        res = new int[jsonArray.size()][2];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = (JSONObject) jsonArray.get(i);
            res[i][0] = Integer.parseInt(data.get(data1).toString());
            res[i][1] = Integer.parseInt(data.get(data2).toString());
        }
        return res;
    }
}
