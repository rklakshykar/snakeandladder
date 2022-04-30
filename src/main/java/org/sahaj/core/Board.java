package org.sahaj.core;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Board {

    Map<Integer, Integer> snakes;
    Map<Integer, Integer> ladders;
    int[] tiles;

    public Board() {
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.tiles = new int[101];
        IntStream.range(0, 101).forEach(i -> tiles[i] = i);
    }

    public Board withSnakes(int[][] headTail) {
        for (int i = 0; i < headTail.length; i++) {
            this.snakes.put(headTail[i][0], headTail[i][1]);
        }
        return this;
    }

    public Board withLadders(int[][] topBottom) {
        for (int i = 0; i < topBottom.length; i++) {
            this.ladders.put(topBottom[i][1],topBottom[i][0]);
        }
        return this;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }
}
