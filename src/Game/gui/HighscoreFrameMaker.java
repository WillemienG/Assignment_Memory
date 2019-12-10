package Game.gui;

import Highscores.HighscoreEntry;
import Highscores.HighscoreUpdater;

import javax.swing.*;
import java.util.List;

public class HighscoreFrameMaker {

    public void makeHighscoreFrame() {
        JFrame highscoreFrame = new JFrame("Highscores");
        highscoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable highscoresTable = makeHighscoreTable();
        JScrollPane scrollPane = new JScrollPane(highscoresTable);
        highscoresTable.setFillsViewportHeight(true);

        highscoreFrame.getContentPane().add(scrollPane);
        highscoreFrame.pack();
        highscoreFrame.setVisible(true);
    }

    public JTable makeHighscoreTable() {
        HighscoreUpdater highscoreUpdater = new HighscoreUpdater();
        List<HighscoreEntry> highscores = highscoreUpdater.readHighscores();
        String[][] highscoreData = new String[highscores.size()][3];
        for (int i = 0; i < highscoreData.length;i++) {
            String playerName = highscores.get(i).getPlayerName();
            String playerScore = Integer.toString(highscores.get(i).getPlayerScore());
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
