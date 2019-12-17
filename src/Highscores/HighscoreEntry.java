package Highscores;

public class HighscoreEntry {
    private String playerName;
    private double playerScore;
    private String difficultyLevel;

    /**This constructor makes HighscoreEntry-objects with 3 fields: name, score, difficultyLevel. These objects will be used to store the data from the Highscores.csv file.
     * @param playerName
     * @param playerScore
     * @param difficultyLevel
     */
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
