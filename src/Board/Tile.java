package Board;

public class Tile {

    //Boolean that tells whether a tile has been turned or not.
    private boolean isTurned;
    //String that is visible if tile hasn't been turned yet, eg. "Turn me!". This String is the same for every newly created tile and shouldn't be changed ==> final variable
    private final String upsideValue = "Turn me!";
    //String that is visible if tile has been turned, eg. "Apple".
    private String downsideValue;
    //Value that keeps track how many times a tile has been turned. Will be used for the scoring system
    private int nbTimesTurned;

    public Tile(boolean isTurned, String upsideValue, String downsideValue,int nbTimesTurned) {
        this.isTurned = isTurned;
        this.downsideValue = downsideValue;
        this.setNbTimesTurned(nbTimesTurned);
    }

    public void setTurned(boolean turned) {
        isTurned = turned;
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

    public int getNbTimesTurned() {
        return nbTimesTurned;
    }

    public void setNbTimesTurned(int nbTimesTurned) {
        this.nbTimesTurned = nbTimesTurned;
    }
}
