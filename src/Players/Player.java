package Players;

public abstract class Player {

    private double playerScore;     //double that keeps track of the score a player has
    private String playerName;      //String that saves the name of the player

    public Player(double playerScore, String playerName) {
        setPlayerScore(playerScore);
        setPlayerName(playerName);
    }

    public abstract int[] pickTiles(int height, int width);

    public double getPlayerScore() {
        return playerScore;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public abstract void addScore(int nbTimesTurned1, int nbTimesTurned2, int nbPairs, int nbTilesMatched);

    public void setPlayerScore(double playerScore) {
        this.playerScore = playerScore;
    }
}
