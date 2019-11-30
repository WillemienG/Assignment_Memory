import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer extends Player{

    public ComputerPlayer(int playerScore, String playerName) {
        super(playerScore, playerName);
    }

    /**This method lets the computer as opponent pick a tile, by just generating two random coordinates within the board ranges.
     *
     * @return a 1-by-2 integer-array with coordinates of tile that has to be turned.
     */
    public int[] pickTiles(int height, int width) {
        System.out.print("\n");
        System.out.println("Now the computer will pick a tile.");
        int[] pickedTileCo = new int[2];
        int heightCo = ThreadLocalRandom.current().nextInt(0, height);
        int widthCo = ThreadLocalRandom.current().nextInt(0, width);
        pickedTileCo[0] = heightCo;
        pickedTileCo[1] = widthCo;

        return pickedTileCo;
    }

    //TODO: nadenken over hoe het scoresysteem voor de computer aangepast kan worden. Voorlopig willekeurig op 2 gezet, om te compenseren voor randomness in spel vd computer
    public void addScore() {
        int oldScore = this.getPlayerScore();
        int newScore = oldScore + 1;
        this.setPlayerScore(newScore);
    }

}
