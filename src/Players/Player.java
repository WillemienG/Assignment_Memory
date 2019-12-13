package Players;

public abstract class Player {

    private double playerScore;
    private String playerName;

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
