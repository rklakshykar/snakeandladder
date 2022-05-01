package org.sahaj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sahaj.core.Board;
import org.sahaj.core.Dice;
import org.sahaj.helper.Constants;
import org.sahaj.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SnakeNLadder {
    private static final Logger logger = LogManager.getLogger(SnakeNLadder.class);

    Board board;
    Player player;
    Dice dice;

    List<Integer> continuousRolls;

    private SnakeNLadder() {
    }

    public SnakeNLadder(int[][] snakes, int[][] ladders) {
        this.board = new Board().withSnakes(snakes).withLadders(ladders);
        this.dice = new Dice();
    }
    public SnakeNLadder withPlayer(Player player){
        this.player = player;
        return this;
    }
    public void play(int sixCount) {
        if (sixCount == 0) {
            continuousRolls = new ArrayList<>();
        }
        dice.rollDice();
        logger.debug(Constants.ROLLED.value, dice.getRolledValue());
        continuousRolls.add(dice.getRolledValue());
        player.move(dice.getRolledValue());
        logger.debug(Constants.CURPOS.value, player.getCurrentPosition());
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
                || board.getSnakes().containsKey(player.getCurrentPosition() + 1) || board.getSnakes().containsKey(player.getCurrentPosition() + 2)) {
            logger.debug("Missed: Possible Snake bite missed by 1 or 2 steps");
            player.setLuckyRolls(player.getLuckyRolls() + 1);
        }
    }

    private void checkLadder() {

        if (board.getLadders().containsKey(player.getCurrentPosition())) {
            logger.debug(Constants.LADDERFOUND.value, player.getCurrentPosition());
            player.climb(board.getLadders().get(player.getCurrentPosition()));
            logger.debug(Constants.CURPOS.value, player.getCurrentPosition());
        }
    }

    private void checkSnake() {
        if (board.getSnakes().containsKey(player.getCurrentPosition())) {
            logger.debug(Constants.SNAKEBITE.value, player.getCurrentPosition());
            player.slide(board.getSnakes().get(player.getCurrentPosition()));
            logger.debug(Constants.CURPOS.value, player.getCurrentPosition());
        }
    }

    public boolean isGameOver() {
        if (player.getCurrentPosition() == 100) {
            logger.debug(".....Game over.....");
            return true;
        }
        return false;
    }

}
