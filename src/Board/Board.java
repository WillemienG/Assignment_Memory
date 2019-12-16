package Board;

public class Board {

    final private Tile[][] tiles;       //the class variable that stores the Tile-matrix of each board-object

    public Board(Tile[][] tiles) {
    this.tiles = tiles;
    }

    public int getHeight() {
        return tiles.length;
    }

    public int getWidth() {
        return tiles[0].length;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}