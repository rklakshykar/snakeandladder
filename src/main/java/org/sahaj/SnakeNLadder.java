package org.sahaj;

import org.sahaj.core.Board;
import org.sahaj.core.Dice;
import org.sahaj.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SnakeNLadder {
    Board board;
    Player player;
    Dice dice;

    List<Integer> continuousRolls;

    private SnakeNLadder() {
    }

    public SnakeNLadder(int[][] snakes, int[][] ladders) {
        this.board = new Board().withSnakes(snakes).withLadders(ladders);
        this.player = new Player("Rohit");
        this.dice = new Dice();
    }

    public void play(int sixCount) {
        if (sixCount == 0) {
            continuousRolls = new ArrayList<>();
        }
        dice.rollDice();
        System.out.println("Rolled Value: " + dice.getRolledValue());
        continuousRolls.add(dice.getRolledValue());
        player.move(dice.getRolledValue());
        System.out.println("Current Position: " + player.getCurrentPosition());
        checkSnake();
        checkLadder();
        checkSnakeMiss();

        if (dice.getRolledValue() == 6) {
            sixCount++;
            play(sixCount);
        } else {
            int currentMaxSum = continuousRolls.stream().mapToInt(i -> i).sum();
            int existingSum = player.getLongestTurn().stream().mapToInt(i -> i).sum();
            if (currentMaxSum > existingSum) {
                player.setLongestTurn(this.continuousRolls);
            }
        }
    }

    private void checkSnakeMiss() {
        if (board.getSnakes().containsKey(player.getCurrentPosition() - 1) || board.getSnakes().containsKey(player.getCurrentPosition() - 2)
                || board.getSnakes().containsKey(player.getCurrentPosition() + 1) || board.getSnakes().containsKey(player.getCurrentPosition() + 1)) {
            System.out.println("Missed: Possible Snake bite missed by 1 or 2 steps");
            player.setLuckyRolls(player.getLuckyRolls() + 1);
        }
    }

    private void checkLadder() {

        if (board.getLadders().containsKey(player.getCurrentPosition())) {
            System.out.println("Ladder found at Position:" + player.getCurrentPosition());
            player.climb(board.getLadders().get(player.getCurrentPosition()) - player.getCurrentPosition());
            player.setCurrentPosition(board.getLadders().get(player.getCurrentPosition()));
            System.out.println("Current Position: " + player.getCurrentPosition());

        }
    }

    private void checkSnake() {
        if (board.getSnakes().containsKey(player.getCurrentPosition())) {
            System.out.println("Snake Bite at Position:" + player.getCurrentPosition());
            player.slide(player.getCurrentPosition() - board.getSnakes().get(player.getCurrentPosition()));
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
