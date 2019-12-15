package Game_Gui;

import Board.Tile;
import Main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GUIStuffFactory {

    public void makeDimErrorFrame() {
        JFrame dimErrorFrame = new JFrame("Error");
        dimErrorFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JLabel dimErrorLabel = new JLabel("Rows*columns must be even. Please adjust your board dimensions to start playing.");
        dimErrorLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
        dimErrorLabel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        dimErrorFrame.getContentPane().add(dimErrorLabel);

        dimErrorFrame.pack();
        dimErrorFrame.setVisible(true);
    }

    public void makeComputerFrame(Tile computerTile1, Tile computerTile2,int[] pickedTileCo1,int[] pickedTileCo2) {
        JFrame computerFrame = new JFrame("The computer has played");
        computerFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JTextArea computerText = new JTextArea("The computer picked these tiles:");
        computerText.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextArea computerText1 = new JTextArea(computerTile1.getDownsideValue() + " at row "+(pickedTileCo1[0]+1)+", column "+(pickedTileCo1[1]+1));
        computerText1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextArea computerText2 = new JTextArea(computerTile2.getDownsideValue() + " at row "+(pickedTileCo2[0]+1)+", column "+(pickedTileCo2[1]+1));
        computerText2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        computerFrame.getContentPane().setLayout(new GridLayout(3,1));
        computerFrame.getContentPane().add(computerText);
        computerFrame.getContentPane().add(computerText1);
        if (!computerTile2.getDownsideValue().equals("Whatever")) {
            computerFrame.getContentPane().add(computerText2);
        }
        computerFrame.pack();
        computerFrame.setVisible(true);
    }

    public JButton createTileButton(Tile tile) {
        JButton buttonForTile;
        buttonForTile = new JButton();
        writeButtonText(tile,buttonForTile);
        return buttonForTile;
    }

    public void writeButtonText(Tile tile, JButton button) {
        button.setText(tile.getText());
        button.setEnabled(!tile.isTurned());
    }

    public void makeNotYourTurnFrame() {
        JFrame notYourTurnFrame = new JFrame("Not your turn now");
        JLabel notYourTurn = new JLabel("Sorry about that, but you can't play now!");
        notYourTurn.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        notYourTurnFrame.getContentPane().add(notYourTurn);
        notYourTurnFrame.pack();
        notYourTurnFrame.setVisible(true);
    }

    protected JPanel makeOptionsPanel() {
        JPanel optionsPanel = new JPanel();

        JButton showRules = new JButton("Rules");
        showRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               makeRulesFrame();
            }
        });

        JButton showHighscores = new JButton("Highscores");
        showHighscores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HighscoreFrameMaker highscoreFrameMaker = new HighscoreFrameMaker();
                highscoreFrameMaker.makeHighscoreFrame();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame quitFrame = new JFrame("Quit game");
                JLabel quitButtonQuestion = new JLabel("Are you sure you want to quit?");
                quitButtonQuestion.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                quitFrame.getContentPane().setLayout(new BoxLayout(quitFrame.getContentPane(),BoxLayout.Y_AXIS));
                quitFrame.getContentPane().add(quitButtonQuestion);
                JButton quitButtonYes = new JButton("Yes, quit game");
                quitButtonYes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                quitButtonYes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                JButton quitButtonNo = new JButton("No, go back.");
                quitButtonNo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                quitButtonNo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        quitFrame.setVisible(false);
                    }
                });
                quitFrame.getContentPane().add(quitButtonYes);
                quitFrame.getContentPane().add(quitButtonNo);
                quitFrame.pack();
                quitFrame.setVisible(true);
            }
        });

        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        optionsPanel.add(showRules);
        optionsPanel.add(showHighscores);
        optionsPanel.add(quitButton);
        return optionsPanel;
    }

    public void makeRulesFrame() {
        JFrame rulesFrame = new JFrame("Memory: the rules");
        rulesFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JTextArea rulesArea = new JTextArea();
        rulesArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("Rules")));
            rulesArea.read(bf, "Getting the rules");
        } catch (IOException ioe) {
            System.out.println("Something went wrong");
        }

        rulesArea.setFont(new Font("Tahoma",Font.PLAIN,15));
        rulesFrame.getContentPane().add(rulesArea, BorderLayout.CENTER);
        rulesFrame.pack();
        rulesFrame.setVisible(true);
    }

    public void makeEndFrame(Game game) {
        JFrame endFrame = new JFrame("Memory - the end");
        JLabel endLabel = new JLabel("The end. Hope you had fun :)");
        endLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        String winner = game.determineWinner();
        JLabel winnerLabel = new JLabel("Congratulations, " + winner + "!");
        winnerLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        endFrame.getContentPane().setLayout(new BorderLayout());
        endFrame.getContentPane().add(endLabel,BorderLayout.NORTH);
        endFrame.getContentPane().add(winnerLabel,BorderLayout.CENTER);
        endFrame.getContentPane().add(makeOptionsPanel(),BorderLayout.SOUTH);

        endFrame.pack();
        endFrame.setVisible(true);
    }

}
