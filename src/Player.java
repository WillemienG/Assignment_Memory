public abstract class Player {

    private int playerScore;

    public Player(int playerScore) {
        setPlayerScore(playerScore);
    }

    public abstract int[] pickTiles(int height, int width);

    public int getPlayerScore() {
        return playerScore;
    }

    public abstract void addScore();

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
