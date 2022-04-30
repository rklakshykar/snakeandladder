package org.sahaj;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sahaj.player.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class App {
    public static void main(String[] args) throws  IOException, ParseException {
        InputStream is = App.class.getClassLoader().getResourceAsStream("input.json");
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(
                new InputStreamReader(is, "UTF-8"));
        int totalSimulation = Integer.parseInt(jsonObject.get("simulations").toString());
        int[][] ladders = extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = extractArrayData(jsonObject, "snakes", "head", "tail");
        List<Player> simulations = new ArrayList<>();

        while (totalSimulation > 0) {
            System.out.println("Simulation.." + totalSimulation);
            SnakeNLadder snakeNLadder = new SnakeNLadder(snakes, ladders);
            while (!snakeNLadder.isGameOver()) {
                snakeNLadder.play(0);
            }
            simulations.add(snakeNLadder.player);
            totalSimulation--;
        }
        simulations.forEach(player -> System.out.println(player.toString()));
    }

    private static void printData(int[][] ladders, int[][] snakes) {
        Arrays.stream(ladders).forEach(val -> System.out.print("[" + val[0] + "," + val[1] + "] "));
        System.out.println();
        Arrays.stream(snakes).forEach(val -> System.out.print("[" + val[0] + "," + val[1] + "] "));
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
