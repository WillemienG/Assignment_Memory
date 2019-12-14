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

public class ComponentsContainersFactory {

    public void makeComputerFrame(Tile computerTile1, Tile computerTile2,int[] pickedTileCo1,int[] pickedTileCo2) {
        JFrame computerFrame = new JFrame("The computer has played");
        computerFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JTextArea computerText = new JTextArea("The computer picked these tiles:");
        JTextArea computerText1 = new JTextArea(computerTile1.getDownsideValue() + " at row "+(pickedTileCo1[0]+1)+", column "+(pickedTileCo1[1]+1));
        JTextArea computerText2 = new JTextArea(computerTile2.getDownsideValue() + " at row "+(pickedTileCo2[0]+1)+", column "+(pickedTileCo2[1]+1));

        computerFrame.getContentPane().setLayout(new GridLayout(3,1));
        computerFrame.getContentPane().add(computerText);
        computerFrame.getContentPane().add(computerText1);
        computerFrame.getContentPane().add(computerText2);

        computerFrame.pack();
        computerFrame.setVisible(true);
    }

    public JButton createTileButton(Tile tile) {
        JButton buttonForTile;
        if (tile.isTurned()) {
            buttonForTile = new JButton(tile.getDownsideValue());
            buttonForTile.setEnabled(false);
        } else {
            buttonForTile = new JButton(tile.getUpsideValue());
            buttonForTile.setEnabled(true);
        }
        return buttonForTile;
    }

    public void writeButtonText(Tile tile, JButton button) {
        if (tile.isTurned()) {
            button.setText(tile.getDownsideValue());
            button.setEnabled(false);
        } else {
            button.setText(tile.getUpsideValue());
            button.setEnabled(true);
        }
    }

    public void makeNotYourTurnFrame() {
        JFrame notYourTurnFrame = new JFrame("Not your turn now");
        JLabel notYourTurn = new JLabel("Sorry about that, but you can't play now!");
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
                quitFrame.getContentPane().setLayout(new BoxLayout(quitFrame.getContentPane(),BoxLayout.Y_AXIS));
                quitFrame.getContentPane().add(new JLabel("Are you sure you want to quit?"));
                JButton quitButtonYes = new JButton("Yes, quit game");
                quitButtonYes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                JButton quitButtonNo = new JButton("No, go back.");
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
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("Rules")));
            rulesArea.read(bf, "Getting the rules");
        } catch (IOException ioe) {
            System.out.println("Something went wrong");
        }

        rulesFrame.getContentPane().add(rulesArea, BorderLayout.CENTER);
        rulesFrame.pack();
        rulesFrame.setVisible(true);
    }

    public void makeEndFrame(Game game) {
        JFrame endFrame = new JFrame("Memory - the end");
        JLabel endLabel = new JLabel("The end. Hope you had fun :)");

        String winner = game.determineWinner();
        JLabel winnerLabel = new JLabel("Congratulations, " + winner + "!");

        endFrame.getContentPane().setLayout(new BorderLayout());
        endFrame.getContentPane().add(endLabel,BorderLayout.NORTH);
        endFrame.getContentPane().add(winnerLabel,BorderLayout.CENTER);
        endFrame.getContentPane().add(makeOptionsPanel(),BorderLayout.SOUTH);

        endFrame.pack();
        endFrame.setVisible(true);
    }

}
