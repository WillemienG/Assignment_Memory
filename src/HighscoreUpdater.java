import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class HighscoreUpdater {

    private List<HighscoreEntry> readHighscores() {
        List<HighscoreEntry> highscores = new ArrayList<>();
        try (Scanner scan = new Scanner(new FileReader("Highscores.csv"))) {
            while (scan.hasNext()) {
                String scoreData = scan.next();
                String[] scoreValues = scoreData.split(",");
                String playerName = scoreValues[0];
                int playerScore = parseInt(scoreValues[1]);
                String difficultyLevel = scoreValues[2];
                HighscoreEntry highscoreEntry = new HighscoreEntry(playerName,playerScore,difficultyLevel);
                highscores.add(highscoreEntry);
            }
        } catch (IOException ioe) {
            System.err.println("Something went wrong");
        }
        return highscores;
    }

    private List<HighscoreEntry> compareScore(Player player, String difficultyLevel, List<HighscoreEntry> highscores) {
        int score = player.getPlayerScore();
        boolean isHighscore = false;
        if (!player.getPlayerName().equals("the computer")) {
            for (int i = highscores.size() - 1; i >= 0; i--) {
                if (score > highscores.get(i).getPlayerScore() && score < highscores.get(i - 1).getPlayerScore()) {
                    HighscoreEntry highscoreEntry1 = new HighscoreEntry(player.getPlayerName(), score, difficultyLevel);
                    highscores.add(i, highscoreEntry1);
                    isHighscore = true;
                }
            }
            if (!isHighscore) {
                HighscoreEntry highscoreEntry1 = new HighscoreEntry(player.getPlayerName(), score, difficultyLevel);
                highscores.add(highscoreEntry1);
            }
        }
        return highscores;
    }

    private List<HighscoreEntry> updateHighscores(Player[] players, String difficultyLevel) {
        List<HighscoreEntry> highscores0 = readHighscores();
        List<HighscoreEntry> highscores1 = compareScore(players[0], difficultyLevel, highscores0);
        List<HighscoreEntry> highscores2 = compareScore(players[1], difficultyLevel, highscores1);

        if (highscores2.size() > 100) {
            List<HighscoreEntry> highscores3 = highscores2.subList(0, 99);
            return highscores3;
        } else {
            return highscores2;
        }
    }

    public void writeHighscores(Player[] players,  String difficultyLevel) {
        List<HighscoreEntry> highscores = updateHighscores(players, difficultyLevel);
        try (FileWriter csvWriter = new FileWriter("Highscores.csv")) {
            for(int i = 0; i < highscores.size(); i++) {
                String playerName = highscores.get(i).getPlayerName();
                String playerScore = Integer.toString(highscores.get(i).getPlayerScore());
                String diffLevel = highscores.get(i).getDifficultyLevel();
                String[] scoreData = {playerName, playerScore, diffLevel};

                csvWriter.append(String.join(",", scoreData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
        } catch (IOException ioe) {
            System.err.println("Something went wrong");
        }
    }
}
