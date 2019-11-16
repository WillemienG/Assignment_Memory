import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Tile {

    //Boolean that tells whether a tile has been turned or not.
    boolean isTurned;
    //String that is visible if tile hasn't been turned yet, eg. "Turn me!". This String is the same for every newly created tile and shouldn't be changed ==> final variable
    final String upsideValue = "Turn me!";
    //String that is visible if tile has been turned, eg. "Apple".
    String downsideValue;

    public Tile(boolean isTurned, String upsideValue, String downsideValue) {
        this.setTurned(isTurned);
        this.setDownsideValue(downsideValue);
    }

    //This method reads, for now, as much types of fruit from a file as needed to construct a play board with the wished size
    public static List<String> readTileValues() {
        List<String> tileValues = new ArrayList<>();
        //Now there need to be tile values added to the actual list, as many values as tiles needed, which is width*height/2

        int i = 0;
        while (tileValues.size() < Board.board1.getBoardSize()[0]*Board.board1.getBoardSize()[1] / 2) {
            try {
                //This command reads the ith line of the tile-file and puts it in a String-list
            String tileValue = Files.readAllLines(Paths.get("Tile downsideValues"), Charset.defaultCharset()).get(i);
            tileValues.add(tileValue);
            i = i + 1;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return tileValues;
    }

    public static List<Tile> createTileObjects() {
        List<Tile> tilesForBoard = new ArrayList<>();
        List<String> tileValues = readTileValues();
        int i = 0;
        while (tilesForBoard.size() < tileValues.size() * 2) {
            //Create two exactly the same tiles, which form a pair of matching tiles
            Tile tileA = new Tile(false,null,tileValues.get(i));
            tilesForBoard.add(tileA);
            tilesForBoard.add(tileA);
            i = i + 1;
        }
        return tilesForBoard;
    }

    public void setTurned(boolean turned) {
        isTurned = turned;
    }

    public void setDownsideValue(String downsideValue) {
        this.downsideValue = downsideValue;
    }
    public String getUpsideValue() {
        return upsideValue;
    }

    public String getDownsideValue() {
        return downsideValue;
    }

    public static void main(String[] args) {
        createTileObjects();
    }
}
