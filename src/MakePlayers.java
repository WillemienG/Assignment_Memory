import java.util.InputMismatchException;
import java.util.Scanner;

public class MakePlayers {

    public int askPlayerMode() {
        Scanner scan = new Scanner(System.in);
        boolean isValidAnswer = false;
        int playerMode = 2;
        while (!isValidAnswer) {
            try {
                System.out.println("Hit 1 if you'd like to play with 2 people or hit 2 if you'd like to play against the computer.");
                int scannedMode = scan.nextInt();
                if (scannedMode <= 0 || scannedMode > 2) {
                    System.out.println("This is not a valid option. Try again.");
                } else {
                    isValidAnswer = true;
                    playerMode = scannedMode;
                }
            } catch (InputMismatchException ime) {
                scan.next();
                System.out.println("This is not a valid option. Try again.");
            }
        }
        return playerMode;
    }

    public Player makePlayer1() {
        HumanPlayer player1 = new HumanPlayer(0, "player1Name");
        player1.setPlayerName(HumanPlayer.askPlayerName());
        return player1;
    }

    public Player makePlayer2() {
        if (askPlayerMode() == 1) {
            HumanPlayer player2 = new HumanPlayer(0, "player1Name");
            player2.setPlayerName(HumanPlayer.askPlayerName());
            return player2;
        } else {
            ComputerPlayer player2 = new ComputerPlayer(0);
            return player2;
        }
    }

}
