package Game_Gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameFrameMaker {

    public void makeGameFrame(int height, int width) {
        JFrame gameFrame = new JFrame("Memory - the game");
        JLabel gameName = new JLabel("Memory: person x VS person y");//om er wat show in te brengen
        gameName.setFont(new Font("Tahoma",Font.BOLD,20));
        gameName.setBorder(BorderFactory.createEmptyBorder(10,30,0,30));

        gameFrame.setLayout(new BorderLayout());
        gameFrame.getContentPane().add(gameName, BorderLayout.NORTH);
        gameFrame.getContentPane().add(makeBoardPanel(height, width), BorderLayout.WEST);
        gameFrame.getContentPane().add(makeScorePanel(), BorderLayout.CENTER);
        HomeFrameMaker homeFrameMaker = new HomeFrameMaker();
        gameFrame.getContentPane().add(homeFrameMaker.makeOptionsPanel(), BorderLayout.SOUTH);

        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    public JPanel makeBoardPanel(int height, int width) {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout());
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10,30,0,30));
        JLabel indicateTurn = new JLabel("Person x can now play");

        JButton[][] boardButtons = new JButton[height][width];
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout(new GridLayout(height,width));
        //iets à la:
        //Tile[][] tilesForButtons = board.getTiles();
        //for (int i = 0; i < height; i++) {
            //for (int j = 0; j < width; j++) {
                //if (tilesForButtons[i][j].isTurned) {
                    //boardButtons[i][j] = new JButton(tilesForButtons[i][j].getDownsideValue);
                //} else {
                    //boardButtons[i][j] = new JButton(tilesForButtons[i][j].getUpsideValue);
                //}
                //boardButtons[i][j].setActionCommand(tilesForButtons[i][j].getDownsideValue);
                //boardButtons[i][j].addActionListener(new ActionListener() {
                    //@Override
                    //public void actionPerformed(ActionEvent e) {
                        //tilesForButtons[i][j].setTurned(true);
                        //...
        int k = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char whatever = alphabet.charAt(k);
                boardButtons[i][j] = new JButton(Character.toString(whatever));
                boardButtons[i][j].setPreferredSize(new Dimension(50,50) );
                tilePanel.add(boardButtons[i][j]);
                k = k + 1;
            }
        }

        boardPanel.add(indicateTurn, BorderLayout.NORTH);
        boardPanel.add(tilePanel, BorderLayout.CENTER);

        return boardPanel;
    }

    public JPanel makeScorePanel() {
        JPanel scorePanel = new JPanel();
        JLabel scoreBoard = new JLabel("Score board");
        JLabel score1Label = new JLabel("Willemien: 2"); //TODO: met score1Label.setText(player1.getPlayerName() + ": " + player1.getPlayerScore()) deze tekst automatisch laten aanpassen
        JLabel score2Label = new JLabel("Brecht: 3"); //TODO: met score2Label.setText(player2.getPlayerName() + ": " + player2.getPlayerScore()) deze tekst automatisch laten aanpassen

        //if (player1.getPlayerScore > player2.getPlayerScore) {
            //score1Label.setBorder(new Border(Color.GREEN, 5);
        //} else if ( player1.getPlayerScore < player2.getPlayerScore) {
            //score2Label.setBorder(new Border(Color.GREEN, 5);
        //}

        scorePanel.setLayout(new BorderLayout());
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10,30,200,10));
        scorePanel.add(scoreBoard,BorderLayout.NORTH);
        scorePanel.add(score1Label,BorderLayout.CENTER);
        scorePanel.add(score2Label,BorderLayout.SOUTH);

        return scorePanel;
    }
}
