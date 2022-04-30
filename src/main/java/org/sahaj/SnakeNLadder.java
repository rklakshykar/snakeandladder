package org.sahaj;

import org.sahaj.core.Board;
import org.sahaj.core.Dice;
import org.sahaj.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SnakeNLadder {
    public Board board;
    public Player player;
    public Dice dice;

    List<Integer> conitnuousRolls;

    private SnakeNLadder() {
    }

    public SnakeNLadder(int[][] snakes, int[][] ladders) {
        this.board = new Board().withSnakes(snakes).withLadders(ladders);
        this.player = new Player("Rohit");
        this.dice = new Dice();
    }

    public void play(int sixCount) {
        if (sixCount == 0) {
            conitnuousRolls = new ArrayList<>();
        }

        dice.rollDice();
        System.out.println("Rolled Value: " + dice.getRolledValue());
        conitnuousRolls.add(dice.getRolledValue());

        player.move(dice.getRolledValue());
        System.out.println("Current Position: " + player.getCurrentPosition());
        checkSnake();
        checkLadder();

        if (dice.getRolledValue() == 6) {
            sixCount++;
            // System.out.println("found 6: count"+sixCount);
            play(sixCount);
        }else{

        }
    }

    private void checkLadder() {

        if (board.getLadders().containsKey(player.getCurrentPosition())) {
            System.out.println("Ladder found at Position:" + player.getCurrentPosition());
            player.setCurrentPosition(board.getLadders().get(player.getCurrentPosition()));
            System.out.println("Current Position: " + player.getCurrentPosition());
        }
    }

    private void checkSnake() {
        if (board.getSnakes().containsKey(player.getCurrentPosition())) {
            System.out.println("Snake Bite at Position:" + player.getCurrentPosition());
            player.setCurrentPosition(board.getSnakes().get(player.getCurrentPosition()));
            System.out.println("Current Position: " + player.getCurrentPosition());
        }
    }

    public boolean isGameOver() {
        //  System.out.println(player.getName() + "'s Current Position:" + player.getCurrentPosition());
        if (player.getCurrentPosition() == 100) {
            System.out.println("Game over...............");
            return true;
        }
        return false;
    }

}
