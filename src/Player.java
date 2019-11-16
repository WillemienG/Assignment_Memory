public abstract class Player {

    private int playerScore;

    public Player(int playerScore) {
        setPlayerScore(playerScore);
    }

    public abstract int[] pickTiles();

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
