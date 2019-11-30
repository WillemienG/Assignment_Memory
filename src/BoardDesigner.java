import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardDesigner {

    /**
     * This method first reads all tile values (such as types of fruit) that are present in the tileValues-file.
     * Then as much tile values as needed for the size of the play board are added to a List<String> which is later on used to construct the board.
     * @return a list with all possible tile-values
     */
    public List<String> readTileValues(int nbTiles) {
        List<String> allTileValuesFromFile = new ArrayList<>();
        try {
            allTileValuesFromFile = Files.readAllLines(Paths.get("Tile downsideValues"), Charset.defaultCharset());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        List<String> tileValues = new ArrayList<>();
        int i = 0;
        while (tileValues.size() < nbTiles / 2) {
                String tileValue = allTileValuesFromFile.get(i);
                tileValues.add(tileValue);
                i = i + 1;
        }
        return tileValues;
    }

    /**
     * This method creates tile-objects with the given tile-values from previous method. It always creates two identical tiles to form pairs for the matching game.
     * @return a list with tile-objects.
     */
    public List<Tile> createTileObjects(int nbTiles) {
        List<Tile> tilesForBoard = new ArrayList<>();
        List<String> tileValues = readTileValues(nbTiles);
        int i = 0;
        while (tilesForBoard.size() < tileValues.size() * 2) {
            Tile tileA = new Tile(false,null,tileValues.get(i));
            tilesForBoard.add(tileA);
            Tile tileB = new Tile(false,null,tileValues.get(i));
            tilesForBoard.add(tileB);
            i = i + 1;
        }
        return tilesForBoard;
    }

    /**
     * This method creates a board-matrix with tile-objects. Depending on whether a tile has already been turned, the upside value ("Turn me!") or downside value is printed.
     * @return a board-matrix with tile-objects.
     */
    public Board createBoard(int height, int width) {
        int nbTiles = height*width;
        List<Tile> shuffledTiles = createTileObjects(nbTiles);
        Collections.shuffle(shuffledTiles);
        Tile[][] playBoard = new Tile[height][width];
        int k = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                playBoard[i][j] = shuffledTiles.get(k);
                k = k + 1;
            }
        }
        Board board = new Board(playBoard);
        return board;
    }
}
