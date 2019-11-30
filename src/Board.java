public class Board {

    final private Tile[][] tiles;

    public Board(Tile[][] tiles) {
    this.tiles = tiles;
    }

    public int getHeight() {
        int height = tiles.length;
        return height;
    }

    public int getWidth() {
        int width = tiles[0].length;
        return width;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * This method prints the created board to the screen. In every turn, when a player turns a tile, this method is called to show the result.
     * Changes in printed board are made by setting boolean isTurned from false to true.
     */
    public void printBoard() {
        System.out.print("\n");
        for (int i = 0; i < this.getHeight(); i++) {
            System.out.print("\n");
            System.out.println(" ");
            for (int j = 0; j < this.getWidth(); j++) {
                if (tiles[i][j].isTurned()) {
                    System.out.print(tiles[i][j].getDownsideValue() + " ");
                } else {
                    System.out.print(tiles[i][j].getUpsideValue() + " ");
                }
            }
        }
        System.out.print("\n");
    }
}