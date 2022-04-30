package org.sahaj;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Arrays;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws URISyntaxException, IOException, ParseException {
        InputStream is = App.class.getClassLoader().getResourceAsStream("input.json");
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(
                new InputStreamReader(is, "UTF-8"));

        int[][] ladders = extractArrayData(jsonObject, "ladders", "top", "bottom");
        int[][] snakes = extractArrayData(jsonObject, "snakes", "head", "tail");

       // printData(ladders,snakes);
        SnakeNLadder snakeNLadder = new SnakeNLadder(snakes, ladders);
       // System.out.println("Ladders");
        //snakeNLadder.board.getLadders().forEach((key,val) -> System.out.println(key.getIndex()+"\t"+val.getIndex()));
        //System.out.println("Snakes");
        //snakeNLadder.board.getSnakes().forEach((key,val) -> System.out.println(key.getIndex()+"\t"+val.getIndex()));
        while (!snakeNLadder.isGameOver()){
            snakeNLadder.play(0);
        }
    }

    private static void printData(int[][] ladders, int[][] snakes) {
        Arrays.stream(ladders).forEach(val -> System.out.print("["+val[0]+","+val[1]+"] "));
        System.out.println();
        Arrays.stream(snakes).forEach(val -> System.out.print("["+val[0]+","+val[1]+"] "));
    }

    private static int[][] extractArrayData(JSONObject jsonObject, String parameter, String data1, String data2) {
        int[][] res;
        JSONArray jsonArray = (JSONArray) jsonObject.get(parameter);
        res = new int[jsonArray.size()][2];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = (JSONObject) jsonArray.get(i);
            res[i][0] = Integer.valueOf(data.get(data1).toString());
            res[i][1] = Integer.valueOf(data.get(data2).toString());
        }
        return res;
    }
}
