package Board;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardDesigner {

    /**This method first reads all tile values (such as types of fruit) that are present in the tileValues-file.
     * Then as much tile values as needed for the size of the play board are added to a List<String> which is later on used to construct the board.
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

    /**This method creates tile-objects with the given tile-values from previous method. It always creates two identical tiles to form pairs for the matching game.
     * Depending on the difficultyLevel, special tiles are or aren't added to the List<Tile>.
     * @return a list with tile-objects.
     */
    private List<Tile> createTileObjects(int nbTiles, String difficultyLevel) {
        List<Tile> tilesForBoard = new ArrayList<>();
        List<String> tileValues = readTileValues(nbTiles);
        Tile tileSkip1 = new Tile(false,null,"Skip",0);
        Tile tileShuffle1 = new Tile(false,null,"Shuffle",0);
        Tile tileSkip2 = new Tile(false,null,"Skip",0);
        Tile tileShuffle2 = new Tile(false,null,"Shuffle",0);
        Tile tileSkip3 = new Tile(false,null,"Skip",0);
        Tile tileShuffle3 = new Tile(false,null,"Shuffle",0);
        switch(difficultyLevel) {
            case "B":
                tilesForBoard.add(tileSkip1);
                tilesForBoard.add(tileShuffle1);
                break;
            case "C":
                tilesForBoard.add(tileSkip1);
                tilesForBoard.add(tileSkip2);
                tilesForBoard.add(tileShuffle1);
                tilesForBoard.add(tileShuffle2);
                break;
            case "D":
                tilesForBoard.add(tileSkip1);
                tilesForBoard.add(tileSkip2);
                tilesForBoard.add(tileSkip3);
                tilesForBoard.add(tileShuffle1);
                tilesForBoard.add(tileShuffle2);
                tilesForBoard.add(tileShuffle3);
                break;
        }
        int i = 0;
        while (tilesForBoard.size() < tileValues.size() * 2) {
            Tile tileA = new Tile(false,null,tileValues.get(i),0);
            tilesForBoard.add(tileA);
            Tile tileB = new Tile(false,null,tileValues.get(i),0);
            tilesForBoard.add(tileB);
            i = i + 1;
        }
        return tilesForBoard;
    }

    /**This method creates a board-object with tile-objects, dependent on the given height, width and with the shuffled tiles.
     * @return a board-object.
     */
    public Board finishBoard(int height, int width, String difficultyLevel) {
        int nbTiles = height*width;
        List<Tile> shuffledTiles = createTileObjects(nbTiles, difficultyLevel);
        Board board = new Board(createBoard(height, width, shuffledTiles));
        return board;
    }

    /**This method create the Tile[][]-matrix by adding Tile-objects one by one to the matrix.
     * @param height , the height of the matrix.
     * @param width , the width of the matrix.
     * @param tiles , the List<Tile> that is used to fill the matrix.
     * @return a Tile[][]-matrix.
     */
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

    /**This method reads all Tile from a Board-object to a list. This list is then passed to createBoard(), where the tiles are shuffled and then put in a new matrix.
     * @param board , where the tiles and the dimensions are taken from.
     * @return a shuffled Board-object.
     */
    public Board shuffleBoard(Board board) {
        List<Tile> tilesToShuffle = new ArrayList<Tile>();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                tilesToShuffle.add(board.getTiles()[i][j]);
            }
        }
        Board newBoard = new Board(createBoard(board.getHeight(), board.getWidth(), tilesToShuffle));
        return newBoard;
    }
}
