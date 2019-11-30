import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class HighscoreUpdater {

    public List<HighscoreEntry> readHighscores() {
        List<HighscoreEntry> highscores = new ArrayList<>();
        try (Scanner scan = new Scanner(new FileReader("Highscores.csv"))) {
            while (scan.hasNext()) {
                String scoreData = scan.next();
                String[] scoreValues = scoreData.split(",");
                String playerName = scoreValues[0];
                int playerScore = parseInt(scoreValues[1]);
                char difficultyLevel = scoreValues[2].charAt(0);
                HighscoreEntry highscoreEntry = new HighscoreEntry(playerName,playerScore,difficultyLevel);
                highscores.add(highscoreEntry);
            }
        } catch (IOException ioe) {
            System.err.println("Something went wrong");
        }
        return highscores;
    }

    public List<HighscoreEntry> updateHighscores(Player[] players, char difficultyLevel) {
        List<HighscoreEntry> highscores = readHighscores();
        int score1 = players[0].getPlayerScore();
        int score2 = players[1].getPlayerScore();
        for (int i = highscores.size() - 1 ; i >= 0; i--) {
            if (!players[0].getPlayerName().equals("the computer")) {
                if (score1 > highscores.get(i).getPlayerScore() && score1 < highscores.get(i-1).getPlayerScore() ) {
                    HighscoreEntry highscoreEntry1 = new HighscoreEntry(players[0].getPlayerName(),score1,difficultyLevel);
                    highscores.add(i, highscoreEntry1);
                }
            } else if (!players[1].getPlayerName().equals("the computer")) {
                if (score2 > highscores.get(i).getPlayerScore() && score2 < highscores.get(i-1).getPlayerScore() ) {
                    HighscoreEntry highscoreEntry2 = new HighscoreEntry(players[1].getPlayerName(),score2,difficultyLevel);
                    highscores.add(i, highscoreEntry2);
                }
            }
        }
        if (highscores.size() > 100) {
            List<HighscoreEntry> highscoresNew = highscores.subList(0, 99);
            return highscoresNew;
        } else {
            return highscores;
        }
    }

    public void writeHighScores(Player[] players,  char difficultyLevel) {
        List<HighscoreEntry> highscores = updateHighscores(players, difficultyLevel);
        for(int i = 0; i < highscores.size(); i++) {
            String playerName = highscores.get(i).getPlayerName();
            String playerScore = Integer.toString(highscores.get(i).getPlayerScore());
            String diffLevel = Character.toString(highscores.get(i).getDifficultyLevel());
            String[] scoreData = {playerName, playerScore, diffLevel};

            try (FileWriter csvWriter = new FileWriter("Highscores.csv")) {
                csvWriter.append(String.join(",", scoreData));
                csvWriter.append("\n");
            } catch (IOException ioe) {
                System.err.println("Something went wrong");
            }
        }
    }
}
