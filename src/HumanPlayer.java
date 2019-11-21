import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private String playerName;

    public HumanPlayer(int playerScore, String playerName) {
        super(playerScore);
        this.setPlayerName(playerName);
    }

    public static String askPlayerName() {
        Scanner scan = new Scanner(System.in);
        String playerName;
        System.out.println("Name of player:");
        playerName = scan.nextLine();
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    /**
     * This method lets a human player pick the two tiles he or she wants to turn. Player has to start over if given coordinates are out-of-bounds or negative or zero.
     * @return a 1-by-2 integer-array with coordinates of tile that has to be turned.
     */
    public int[] pickTiles (int height, int width) {
        Scanner scan = new Scanner(System.in);
        boolean isValidCo = false;
        int[] pickedTileCo = new int[2];
        while (!isValidCo) {
            try {
                System.out.println("Enter coordinates of first tile you want to turn, e.g. 2 <ENTER> 5");
                int heightCo = scan.nextInt();
                int widthCo = scan.nextInt();
                if (heightCo <= 0 || widthCo <= 0) {
                    throw new IllegalArgumentException();
                } else if (heightCo > height || widthCo > width) {
                    throw new IllegalArgumentException();
                } else {
                    pickedTileCo[0] = heightCo;
                    pickedTileCo[1] = widthCo;
                    isValidCo = true;
                }
            } catch (InputMismatchException ime) {
                scan.next();
                System.out.println("These are not valid coordinates. Please try again.");
            } catch (IllegalArgumentException iae) {
                System.out.println("Please enter non-zero positive coordinates within the range of your play board.");
            }
        }
        return pickedTileCo;
    }

    public void addScore() {
        int oldScore = this.getPlayerScore();
        int newScore = oldScore + 1;
        this.setPlayerScore(newScore);
    }
}
