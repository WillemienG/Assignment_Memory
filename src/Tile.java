public class Tile {

    //Boolean that tells whether a tile has been turned or not.
    private boolean isTurned;
    //String that is visible if tile hasn't been turned yet, eg. "Turn me!". This String is the same for every newly created tile and shouldn't be changed ==> final variable
    private final String upsideValue = "Turn me!";
    //String that is visible if tile has been turned, eg. "Apple".
    private String downsideValue;

    public Tile(boolean isTurned, String upsideValue, String downsideValue) {
        this.isTurned = isTurned;
        this.downsideValue = downsideValue;
    }

    public boolean turnTile() {
        if (isTurned) {
            isTurned = false;
        } else {
            isTurned = true;
        }
        return isTurned;
    }

    public boolean isTurned() {
        return isTurned;
    }

    public String getUpsideValue() {
        return upsideValue;
    }

    public String getDownsideValue() {
        return downsideValue;
    }
}
