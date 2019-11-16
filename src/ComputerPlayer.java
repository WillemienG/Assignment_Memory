import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer extends Player{

    public ComputerPlayer(int playerScore) {
        super(playerScore);
    }
    public int[] pickTiles() {
        int[] pickedTilesCo = new int[2];
        int heightCo = ThreadLocalRandom.current().nextInt(1, Board.board1.getBoardSize()[0] + 1);
        int widthCo = ThreadLocalRandom.current().nextInt(1, Board.board1.getBoardSize()[1] + 1);
        pickedTilesCo[0] = heightCo;
        pickedTilesCo[1] = widthCo;

        return pickedTilesCo;
    }

}
