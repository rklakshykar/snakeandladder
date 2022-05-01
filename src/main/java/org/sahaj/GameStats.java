package org.sahaj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sahaj.player.Player;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class GameStats {

    private static final Logger logger = LogManager.getLogger(GameStats.class);
    private static final DecimalFormat df = new DecimalFormat("0.00");
    List<Player> simulations;
    int minRollsToWin;
    int maxRollsToWin;
    double avgRollsToWin;
    int minClimb;
    int maxClimb;
    double avgClimb;
    int minSlide;
    double avgSlide;
    int maxSlide;
    int longestSum;
    List<Integer> longestTurn;
    int minUnluckyRolls;
    double avgUnluckyRolls;
    int maxUnluckyRolls;
    int minLuckyRolls;
    double avgLuckyRolls;
    int maxLuckyRolls;

    public GameStats(List<Player> simulations) {
        this.simulations = simulations;
    }

    public int getMinRollsToWin() {
        this.minRollsToWin = simulations.stream().mapToInt(Player::getNumberOfRolls).min().orElse(0);
        return minRollsToWin;
    }

    public int getMaxRollsToWin() {
        this.maxRollsToWin = simulations.stream().mapToInt(Player::getNumberOfRolls).max().orElse(0);
        return maxRollsToWin;
    }

    public double getAvgRollsToWin() {
        this.avgRollsToWin = simulations.stream().mapToInt(Player::getNumberOfRolls).average().orElse(0);
        return avgRollsToWin;
    }

    public int getMinClimb() {
        this.minClimb = simulations.stream().map(Player::getClimbs).filter(climbs -> !climbs.isEmpty()).mapToInt(climb -> climb.stream().min(Integer::compareTo).get()).min().orElse(0);
        return minClimb;
    }

    public int getMaxClimb() {
        this.maxClimb = simulations.stream().map(Player::getClimbs).filter(climbs -> !climbs.isEmpty()).mapToInt(climb -> climb.stream().max(Integer::compareTo).get()).max().orElse(0);
        return maxClimb;
    }

    public double getAvgClimb() {
        this.avgClimb = simulations.stream().map(Player::getClimbs).filter(climbs -> !climbs.isEmpty()).mapToDouble(climb -> climb.stream().mapToInt(i -> i).average().orElse(0)).average().orElse(0);
        return avgClimb;
    }

    public int getMinSlide() {
        this.minSlide = simulations.stream().map(Player::getSlides).filter(slides -> !slides.isEmpty()).mapToInt(climb -> climb.stream().min(Integer::compareTo).get()).min().orElse(0);
        return minSlide;
    }

    public double getAvgSlide() {
        this.avgSlide = simulations.stream().map(Player::getSlides).filter(slides -> !slides.isEmpty()).mapToDouble(climb -> climb.stream().mapToInt(i -> i).average().orElse(0)).average().orElse(0);
        return avgSlide;
    }

    public int getMaxSlide() {
        this.maxSlide = simulations.stream().map(Player::getSlides).filter(slides -> !slides.isEmpty()).mapToInt(climb -> climb.stream().max(Integer::compareTo).get()).max().orElse(0);
        return maxSlide;
    }

    public int getLongestSum() {
        this.longestSum = simulations.stream().map(player -> player.getLongestTurn().stream().mapToInt(i -> i).sum()).mapToInt(i -> i).max().orElseGet(() -> 0);
        return longestSum;
    }

    public List<Integer> getLongestTurn() {
        this.longestTurn = simulations.stream().filter(player -> player.getLongestTurn().stream().mapToInt(i -> i).sum() == getLongestSum()).collect(Collectors.toList()).get(0).getLongestTurn();
        return longestTurn;
    }

    public int getMinUnluckyRolls() {
        this.minUnluckyRolls = simulations.stream().mapToInt(Player::getUnluckyRolls).min().orElse(0);
        return minUnluckyRolls;
    }

    public double getAvgUnluckyRolls() {
        this.avgUnluckyRolls = simulations.stream().mapToInt(Player::getUnluckyRolls).average().orElse(0);
        return avgUnluckyRolls;
    }

    public int getMaxUnluckyRolls() {
        this.maxUnluckyRolls = simulations.stream().mapToInt(Player::getUnluckyRolls).max().orElse(0);
        return maxUnluckyRolls;
    }

    public int getMinLuckyRolls() {
        this.minLuckyRolls = simulations.stream().mapToInt(Player::getLuckyRolls).min().orElse(0);
        return minLuckyRolls;
    }

    public double getAvgLuckyRolls() {
        this.avgLuckyRolls = simulations.stream().mapToInt(Player::getLuckyRolls).average().orElse(0);
        return avgLuckyRolls;
    }

    public int getMaxLuckyRolls() {
        this.maxLuckyRolls = simulations.stream().mapToInt(Player::getLuckyRolls).max().orElse(0);
        return maxLuckyRolls;
    }

    public String getAvgRollsToWinFormatted() {
        return df.format(getAvgRollsToWin());
    }

    public String getAvgClimbFormatted() {
        return df.format(getAvgClimb());
    }

    public String getAvgSlideFormatted() {
        return df.format(getAvgSlide());
    }

    public String getAvgUnluckyRollsFormatted() {
        return df.format(getAvgUnluckyRolls());
    }

    public String getAvgLuckyRollsFormatted() {
        return df.format(getAvgLuckyRolls());
    }

    public void printStats() {
        logger.info("1. Minimum:{}, Average:{}, Maximum:{} number of rolls needed to win\n", getMinRollsToWin(), getAvgRollsToWinFormatted(), getMaxRollsToWin());
        logger.info("2. Minimum:{}, Average:{}, Maximum:{} amount of climbs during the game\n", getMinClimb(), getAvgClimbFormatted(), getMaxClimb());
        logger.info("3. Minimum:{}, Average:{}, Maximum:{} amount of slides during the game\n", getMinSlide(), getAvgSlideFormatted(), getMaxSlide());
        logger.info("4. The biggest climb in a single turn: {}\n", getMaxClimb());
        logger.info("5. The biggest slide in a single turn: {}\n", getMaxSlide());
        logger.info("6. Longest turn. The longest turn is the highest streak of consecutive rolls due to rolling 6s: {}\n", getLongestTurn());
        logger.info("7. Minimum:{}, Average:{}, Maximum:{} unlucky rolls during the game\n", getMinUnluckyRolls(), getAvgUnluckyRollsFormatted(), getMaxUnluckyRolls());
        logger.info("8. Minimum:{}, Average:{}, Maximum:{} lucky rolls during the game\n", getMinLuckyRolls(), getAvgLuckyRollsFormatted(), getMaxLuckyRolls());
    }

}
