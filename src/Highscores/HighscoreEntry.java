package Highscores;

public class HighscoreEntry {
    String playerName;
    double playerScore;
    String difficultyLevel;



    public HighscoreEntry(String playerName, double playerScore, String difficultyLevel) {
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

    public double getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(double playerScore) {
        this.playerScore = playerScore;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
