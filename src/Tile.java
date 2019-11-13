import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Tile {

    //Boolean that tells whether a tile has been turned or not.
    private boolean isTurned;
    //String that is visible if tile hasn't been turned yet, eg. "Turn me!". This String is the same for every newly created tile and shouldn't be changed ==> final variable
    final String upsideValue = "Turn me!";
    //String that is visible if tile has been turned, eg. "Apple".
    private String downsideValue;

    public Tile(boolean isTurned, String upsideValue, String downsideValue) {
        this.setTurned(isTurned);
        this.setDownsideValue(downsideValue);
    }

    public static List<String> readTileValues() {
        List<String> tileValues = new ArrayList<>();
        try {
            tileValues = Files.readAllLines(Paths.get("Tile downsideValues"), Charset.defaultCharset());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return tileValues;
    }

    public void setTilesForBoard() {
        List<Tile> tilesForBoard = new ArrayList<>();
    }

    //Don't know yet if this setter is really needed.
    public void setTurned(boolean turned) {
        isTurned = turned;
    }

    public void setDownsideValue(String downsideValue) {
        this.downsideValue = downsideValue;
    }

}
