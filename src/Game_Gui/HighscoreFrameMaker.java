package Game_Gui;

import Highscores.HighscoreEntry;
import Highscores.HighscoreUpdater;

import javax.swing.*;
import java.util.List;

public class HighscoreFrameMaker {

    /**This method makes the JFrame highscoreFrame and adds the JTable with the highscores.
     */
    public void makeHighscoreFrame() {
        JFrame highscoreFrame = new JFrame("Highscores");
        highscoreFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JTable highscoresTable = makeHighscoreTable();
        JScrollPane scrollPane = new JScrollPane(highscoresTable);
        highscoresTable.setFillsViewportHeight(true);

        highscoreFrame.getContentPane().add(scrollPane);
        highscoreFrame.pack();
        highscoreFrame.setVisible(true);
    }

    /**This method makes the JTable highscoreTable. It calls highscoreUpdater.readHighscores() to read all highscores from the highscores.csv file which returns a
     * List<HighscoreEntry>. Each of these entries is chopped in three separate Strings, reassembled in a String[] per HighscoreEntry and put together in a String[][].
     * This String[][] is then, line by line, read out in a JTable with appropriate column names.
     * @return highscoreTable, the JTable that has the highscores.
     */
    public JTable makeHighscoreTable() {
        HighscoreUpdater highscoreUpdater = new HighscoreUpdater();
        List<HighscoreEntry> highscores = highscoreUpdater.readHighscores();
        String[][] highscoreData = new String[highscores.size()][3];
        for (int i = 0; i < highscoreData.length;i++) {
            String playerName = highscores.get(i).getPlayerName();
            String playerScore = Double.toString(highscores.get(i).getPlayerScore());
            String diffLevel = highscores.get(i).getDifficultyLevel();
            String[] scoreData = {playerName, playerScore, diffLevel};
            for (int j = 0; j < 3; j++) {
                highscoreData[i][j] = scoreData[j];
            }
        }
        String[] columnNames = {"Player name","Score","Difficulty level"};
        JTable highscoreTable = new JTable(highscoreData,columnNames);
        return highscoreTable;
    }
}
