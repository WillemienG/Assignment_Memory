import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    final int[] boardSize = BoardDimensioner.determineWidthHeight();
    Tile[][] playBoard;

    public Board() {
    }
    public Board(Tile[][] playBoard) {
        this.playBoard = playBoard;
    }

    public int[] getBoardSize() {
        return boardSize;
    }

    public Tile[][] getPlayBoard() {
        return playBoard;
    }

    static Board board1 = new Board();

    /**
     * This method reads as much tile values (such as types of fruit) as needed for the size of the play board.
     * @return a list with all possible tile-values
     */
    public static List<String> readTileValues() {
        List<String> tileValues = new ArrayList<>();
        int i = 0;
        while (tileValues.size() < board1.getBoardSize()[0]*board1.getBoardSize()[1] / 2) {
            try {
                String tileValue = Files.readAllLines(Paths.get("Tile downsideValues"), Charset.defaultCharset()).get(i);
                tileValues.add(tileValue);
                i = i + 1;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return tileValues;
    }

    /**
     * This method creates tile-objects with the given tile-values from previous method. It always creates two identical tiles to form pairs for the matching game.
     * @return a list with tile-objects.
     */
    public List<Tile> createTileObjects() {
        List<Tile> tilesForBoard = new ArrayList<>();
        List<String> tileValues = readTileValues();
        int i = 0;
        while (tilesForBoard.size() < tileValues.size() * 2) {
            Tile tileA = new Tile(false,null,tileValues.get(i));
            tilesForBoard.add(tileA);
            tilesForBoard.add(tileA);
            i = i + 1;
        }
        return tilesForBoard;
    }

    /**
     * This method creates a board-matrix with tile-objects. Depending on whether a tile has already been turned, the upside value ("Turn me!") or downside value is printed.
     * @return a board-matrix with tile-objects.
     */
    public Tile[][] createBoard() {
        List<Tile> shuffledTiles = createTileObjects();
        Collections.shuffle(shuffledTiles);
        Tile[][] playBoard = new Tile[board1.getBoardSize()[0]][board1.getBoardSize()[1]];
        int k = 0;
        for (int i = 0; i < board1.getBoardSize()[0]; i++) {
            System.out.println(" ");
            for (int j = 0; j < board1.getBoardSize()[1]; j++) {
                playBoard[i][j] = shuffledTiles.get(k);
                k = k + 1;
                if (playBoard[i][j].isTurned()) {
                    System.out.print(playBoard[i][j].getDownsideValue() + " ");
                } else {
                    System.out.print(playBoard[i][j].getUpsideValue() + " ");
                }
            }
        }
        return playBoard;
    }

}