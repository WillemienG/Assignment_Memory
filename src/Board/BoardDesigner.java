package Board;

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
     * Depending on the chosen difficulty level, less 'normal' tiles and more 'special' tiles are added to the List<String>.
     * @return a list with all possible tile-values
     */
    private List<String> readTileValues(int nbTiles) {
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
    private List<Tile> createTileObjects(int nbTiles, String difficultyLevel) {
        List<Tile> tilesForBoard = new ArrayList<>();
        List<String> tileValues = readTileValues(nbTiles);
        Tile tileSkip = new Tile(false,null,"Skip");
        Tile tileShuffle = new Tile(false,null,"Shuffle");
        switch(difficultyLevel) {
            case "B":
                tilesForBoard.add(tileSkip);
                tilesForBoard.add(tileShuffle);
                break;
            case "C":
                tilesForBoard.add(tileSkip);
                tilesForBoard.add(tileSkip);
                tilesForBoard.add(tileShuffle);
                tilesForBoard.add(tileShuffle);
                break;
            case "D":
                tilesForBoard.add(tileSkip);
                tilesForBoard.add(tileSkip);
                tilesForBoard.add(tileSkip);
                tilesForBoard.add(tileShuffle);
                tilesForBoard.add(tileShuffle);
                tilesForBoard.add(tileShuffle);
                break;
        }
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
    public Board finishBoard(int height, int width, String difficultyLevel) {
        int nbTiles = height*width;
        List<Tile> shuffledTiles = createTileObjects(nbTiles, difficultyLevel);
        Board board = new Board(createBoard(height, width, shuffledTiles));
        return board;
    }

    public Tile[][] createBoard(int height, int width, List<Tile> tiles) {
        Collections.shuffle(tiles);
        Tile[][] playBoard = new Tile[height][width];
        int k = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                playBoard[i][j] = tiles.get(k);
                k = k + 1;
            }
        }
        return playBoard;
    }

    public Board shuffleBoard(Board board, int height, int width) {
        List<Tile> tilesToShuffle = new ArrayList<Tile>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tilesToShuffle.add(board.getTiles()[i][j]);
            }
        }
        Board newBoard = new Board(createBoard(height, width, tilesToShuffle));
        return newBoard;
    }
}
