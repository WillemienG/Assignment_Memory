public abstract class Player {

    private int playerScore;
    private String playerName;

    public Player(int playerScore, String playerName) {
        setPlayerScore(playerScore);
        setPlayerName(playerName);
    }

    public abstract int[] pickTiles(int height, int width);

    public int getPlayerScore() {
        return playerScore;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public abstract void addScore();

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
