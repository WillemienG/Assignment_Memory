public class HighscoreEntry {
    String playerName;
    int playerScore;
    char difficultyLevel;



    public HighscoreEntry(String playerName, int playerScore, char difficultyLevel) {
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

    public char getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(char difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
