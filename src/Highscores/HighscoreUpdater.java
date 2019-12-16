package Highscores;

import Main.Game;
import Players.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.Double.parseDouble;

public class HighscoreUpdater {

    /**This method uses a scanner to scan the lines of Highscores.csv (where the different fields are separated by a comma). This way, each line of the file is put
     * in a String that needs to be chopped. Using ',' as the delimiter, the String of an entire line is converted in a String[] of which 2 Strings stay String and
     * a third one is parsed to Double. These values are then used to create a new HighscoreEntry-object that is added to the List<HighscoreEntry>.
     * @return the list with all HighscoreEntries.
     */
    public List<HighscoreEntry> readHighscores() {
        List<HighscoreEntry> highscores = new ArrayList<>();
        try (Scanner scan = new Scanner(new FileReader("Highscores.csv"))) {
            while (scan.hasNext()) {
                String scoreData = scan.next();
                String[] scoreValues = scoreData.split(",");
                String playerName = scoreValues[0];
                double playerScore = parseDouble(scoreValues[1]);
                String difficultyLevel = scoreValues[2];
                HighscoreEntry highscoreEntry = new HighscoreEntry(playerName,playerScore,difficultyLevel);
                highscores.add(highscoreEntry);
            }
        } catch (IOException ioe) {
            System.err.println("Something went wrong");
        }
        return highscores;
    }

    /**This method compares the playerScores at the end of the game with the highscores that are read to the List<HighscoreEntry>. Only HumanPlayers can have their
     * scores added to the list, hence the first if-statement. The for-loop starts at the bottom of the list: at each HighscoreEntry, the playerScore is compared to the highscore.
     * If it's bigger than this one ánd smaller than the next one, a new HighscoreEntry is created with the playerName, the playerScore and the difficultyLevel, and inserted
     * between the two highscores.
     * If it's equal to the highscore, the newly created HighscoreEntry is added 1 place lower than the highscore and the loop breaks (the prevent that, in the case already
     * multiple highscores with the same value exist, the new playerScore is inserted under each of these highscores);
     * If the playerScore doesn't find a perfect fit in the list (so it's smaller than every highscore), it's added at the bottom.
     *
     * @param player , the Player-object with playerName and playerScore
     * @param difficultyLevel , the difficultyLevel at which the playerScore was got
     * @param highscores , the list with HighscoreEntries
     * @return highscores, the adjusted list with HighscoreEntries
     */
    private List<HighscoreEntry> compareScore(Player player, String difficultyLevel, List<HighscoreEntry> highscores) {
        double score = player.getPlayerScore();
        boolean isHighscore = false;
        if (!player.getPlayerName().equals("the computer")) {
            for (int i = highscores.size() - 1; i >= 0; i--) {
                if (score > highscores.get(i).getPlayerScore() && score < highscores.get(i - 1).getPlayerScore()) {
                    HighscoreEntry highscoreEntry1 = new HighscoreEntry(player.getPlayerName(), score, difficultyLevel);
                    highscores.add(i, highscoreEntry1);
                    isHighscore = true;
                } else if (score == highscores.get(i).getPlayerScore()) {
                    HighscoreEntry highscoreEntry1 = new HighscoreEntry(player.getPlayerName(), score, difficultyLevel);
                    highscores.add(i + 1, highscoreEntry1);
                    isHighscore = true;
                    break;
                }
            }
            if (!isHighscore) {
                HighscoreEntry highscoreEntry1 = new HighscoreEntry(player.getPlayerName(), score, difficultyLevel);
                highscores.add(highscoreEntry1);
            }
        }
        return highscores;
    }

    /**This method uses compareHighscores() to add the 2 playerScores to the list. In this case, only the top 100 of highscores is kept. If the list has got longer than
     * 100, a new subList is made with only the first 100 HighscoreEntries.
     * @param game , has all relevant info about the players and the difficultyLevel.
     * @return a list of HighscoreEntries
     */
    private List<HighscoreEntry> updateHighscores(Game game) {
        List<HighscoreEntry> highscores0 = readHighscores();
        List<HighscoreEntry> highscores1 = compareScore(game.getPlayers()[0],game.getDifficultyLevel(), highscores0);
        List<HighscoreEntry> highscores2 = compareScore(game.getPlayers()[1],game.getDifficultyLevel(), highscores1);

        if (highscores2.size() > 100) {
            List<HighscoreEntry> highscores3 = highscores2.subList(0, 99);
            return highscores3;
        } else {
            return highscores2;
        }
    }

    /**This method does the exactly opposite of readHighscores():
     * - each HighscoreEntry of the list is converted to a String[] again (the Double is converted to String). This String[] is then reduced ('joined') to a String, where
     * the different fields are separated by a comma. A FileWriter then appends and flushes this String, and this way writes it to the Highscores.csv.
     * @param game , has all relevant info about the players and the difficultyLevel.
     */
    public void writeHighscores(Game game) {
        List<HighscoreEntry> highscores = updateHighscores(game);
        try (FileWriter csvWriter = new FileWriter("Highscores.csv")) {
            File f = new File("Highscores.csv");
            f.createNewFile();
            for(int i = 0; i < highscores.size(); i++) {
                String playerName = highscores.get(i).getPlayerName();
                String playerScore = Double.toString(highscores.get(i).getPlayerScore());
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
