import java.util.Collections;
import java.util.List;

public class Board {

    final int[] boardSize = BoardDimensioner.determineWidthHeight();

    public Board() {
    }

    public int[] getBoardSize() {
        return boardSize;
    }

    static Board board1 = new Board();

    public static String[][] createBoard() {
        List<Tile> shuffledTiles = Tile.createTileObjects();
        Collections.shuffle(shuffledTiles);
        String[][] playBoard = new String[board1.getBoardSize()[0]][board1.getBoardSize()[1]];
        int k = 0;
        //Fill play board row by row
        for (int i = 0; i < board1.getBoardSize()[0]; i++) {
            System.out.println(" ");
            for (int j = 0; j < board1.getBoardSize()[1]; j++) {
                playBoard[i][j] = shuffledTiles.get(k).getUpsideValue();
                k = k + 1;
                System.out.print(playBoard[i][j] + " ");
            }
        }
        return playBoard;
    }

    public static void main(String[] args) {
        createBoard();
    }

}
