package Players;

import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer extends Player{

    public ComputerPlayer(int playerScore, String playerName) {
        super(playerScore, playerName);
    }

    /**This method lets the computer as opponent pick a tile, by just generating two random coordinates within the board ranges.
     * @return a 1-by-2 integer-array with coordinates of tile that has to be turned.
     */
    public int[] pickTiles(int height, int width) {
        int[] pickedTileCo = new int[2];
        int heightCo = ThreadLocalRandom.current().nextInt(0, height);
        int widthCo = ThreadLocalRandom.current().nextInt(0, width);
        pickedTileCo[0] = heightCo;
        pickedTileCo[1] = widthCo;

        return pickedTileCo;
    }

    /**This method makes the score of a ComputerPlayer increase. This score is weighed by the ratio of total nbPairs and nbTilesMatched, because
     * the picking is easier when more pairs have been found.
     * @param nbTimesTurned1
     * @param nbTimesTurned2
     * @param nbPairs
     * @param nbTilesMatched
     */
    public void addScore(int nbTimesTurned1, int nbTimesTurned2,int nbPairs, int nbTilesMatched) {
        double oldScore = this.getPlayerScore();
        double newScore = oldScore + nbPairs/(nbTilesMatched+1);
        this.setPlayerScore(newScore);
    }

}
