import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    String playerName = askPlayerName();

    public HumanPlayer(int playerScore, String playerName) {
        super(playerScore);
        this.playerName = playerName;
    }

    public String askPlayerName() {
        Scanner scan = new Scanner(System.in);
        String playerName;
        System.out.println("Name of player:");
        playerName = scan.nextLine();
        return playerName;
    }

    /**
     * This method lets a human player pick the two tiles he or she wants to turn. Player has to start over if given coordinates are out-of-bounds or negative or zero.
     * @return a 1-by-2 array with coordinates of tile that has to be turned.
     */
    public int[] pickTiles () {
        Scanner scan = new Scanner(System.in);
        boolean isValidCo = false;
        int[] pickedTilesCo = new int[2];
        while (!isValidCo) {
            try {
                System.out.println("Enter coordinates of first tile you want to turn, e.g. 2 <ENTER> 5");
                int heightCo = scan.nextInt();
                int widthCo = scan.nextInt();
                if (heightCo <= 0 || widthCo <= 0) {
                    throw new IllegalArgumentException();
                } else if (heightCo > Board.board1.getBoardSize()[0] || widthCo > Board.board1.getBoardSize()[1]) {
                    throw new IllegalArgumentException();
                } else {
                    pickedTilesCo[0] = heightCo;
                    pickedTilesCo[1] = widthCo;
                    isValidCo = true;
                }
            } catch (InputMismatchException ime) {
                scan.next();
                System.out.println("These are not valid coordinates. Please try again.");
            } catch (IllegalArgumentException iae) {
                System.out.println("Please enter non-zero positive coordinates within the range of your play board.");
            }
        }
        return pickedTilesCo;
    }
}
