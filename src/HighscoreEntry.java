public class HighscoreEntry {
    String playerName;
    int playerScore;
    String difficultyLevel;



    public HighscoreEntry(String playerName, int playerScore, String difficultyLevel) {
        this.setPlayerName(playerName);
        this.setPlayerScore(playerScore);
        this.setDifficultyLevel(difficultyLevel);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
