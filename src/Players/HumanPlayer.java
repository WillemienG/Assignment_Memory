package Players;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(int playerScore, String playerName) {
        super(playerScore, playerName);
    }

    /**
     * This method lets a human player pick the two tiles he or she wants to turn. Players.Player has to start over if given coordinates are out-of-bounds or negative or zero.
     * @return a 1-by-2 integer-array with coordinates of tile that has to be turned.
     */
    @Deprecated //this is not used in the GUI-version of the game, but needs to be there because this class extends the Player-class.
    public int[] pickTiles (int height, int width) {
        Scanner scan = new Scanner(System.in);
        boolean isValidCo = false;
        int[] pickedTileCo = new int[2];
        while (!isValidCo) {
            try {
                System.out.print("\n");
                System.out.println("Enter the row-coordinate of the tile you want to turn");
                int heightCo = scan.nextInt() - 1;
                System.out.print("\n");
                System.out.println("Enter the column-coordinate of the tile you want to turn");
                int widthCo = scan.nextInt() - 1;
                if (heightCo < 0 || widthCo < 0) {
                    throw new ArrayIndexOutOfBoundsException();
                } else if (heightCo > height || widthCo > width) {
                    throw new ArrayIndexOutOfBoundsException();
                } else {
                    pickedTileCo[0] = heightCo;
                    pickedTileCo[1] = widthCo;
                    isValidCo = true;
                }
            } catch (InputMismatchException ime) {
                scan.next();
                System.out.println("These are not valid coordinates. Please try again.");
            } catch (ArrayIndexOutOfBoundsException aioe) {
                System.out.println("Please enter non-zero positive coordinates within the range of your play board.");
            }
        }
        return pickedTileCo;
    }

    /**This method raises the score when a match has been found. Because the 2nd player has already seen the move of the first player
     * and thus has an advantage, the added score is adjusted with the number of times the matching tiles have been turned. This way players get
     * 'punished' for mindlessly playing with trial-and-error tactics.
     * @param nbTimesTurned1 the times tile1 has been turned before it was matched to tile2
     * @param nbTimesTurned2 the times tile2 has been turned before it was matched to tile1
     */
    public void addScore(int nbTimesTurned1, int nbTimesTurned2,int nbPairs, int nbTilesMatched) {
        double oldScore = this.getPlayerScore();
        double newScore = oldScore + (1 + 3/(nbTimesTurned1*nbTimesTurned2));
        this.setPlayerScore(newScore);
    }
}
