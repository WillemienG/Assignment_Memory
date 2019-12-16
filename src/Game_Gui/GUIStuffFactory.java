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

    /**This method makes a pop-up frame with dimension-error message.
     */
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

    /**This method makes a pop-up frame with information about the computer's turn. If the first picked tile is a Shuffle or Skip,
     * the Tile-initializer Tile chosenTile2 = new Tile(false,null,"Whatever",0); isn't overwritten and would result in a message about a "Whatever"-tile.
     * To prevent this from happening, chosenTile2 is only added when chosenTile1 is a 'normal' tile.
     *
     * @param computerTile1 , the first picked tile.
     * @param computerTile2 , the second picked tile.
     * @param pickedTileCo1 , coordinates of computerTile1
     * @param pickedTileCo2 , coordinates of computerTile2
     */
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
        if (!computerTile1.getDownsideValue().equals("Shuffle") || !computerTile1.getDownsideValue().equals("Skip")) {
            computerFrame.getContentPane().add(computerText2);
        }
        computerFrame.pack();
        computerFrame.setVisible(true);
    }

    /**This method creates a new JButton tileButton, with text and enabled-status made by writeButtonText().
     *
     * @param tile , the Tile which lends his downside- or upsideValue to the buttonText
     * @return a JButton with the correct text and enabled/disabled.
     */
    public JButton createTileButton(Tile tile) {
        JButton buttonForTile;
        buttonForTile = new JButton();
        writeButtonText(tile,buttonForTile);
        return buttonForTile;
    }

    /**This method sets the text and enabled-status of a JButton depending on the isTurned-value of the corresponding Tile. Disabled when the tile is turned, enabled when not.
     *
     * @param tile , the Tile the Tile which lends his downside- or upsideValue to the buttonText and isTurned-value to setEnabled()
     * @param button , the JButton whose parameters are being set.
     */
    public void writeButtonText(Tile tile, JButton button) {
        button.setText(tile.getText());
        button.setEnabled(!tile.isTurned());
    }

    /**This method makes a pop-up frame with dimension-error message when called.
     */
    public void makeNotYourTurnFrame() {
        JFrame notYourTurnFrame = new JFrame("Not your turn now");
        JLabel notYourTurn = new JLabel("Sorry about that, but you can't play now! Click on 'Next step' to make the game continue.");
        notYourTurn.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        notYourTurnFrame.getContentPane().add(notYourTurn);
        notYourTurnFrame.pack();
        notYourTurnFrame.setVisible(true);
    }

    /**This method makes a JPanel optionsPanel in FlowLayout which is added to frames. It contains a JButton showRules that opens a JFrame with the rules read out.
     * Then a JButton showHighscores that opens a JFrame with the highscores nicely read out in a table.
     * Then a JButton quitButton that, when pressed gives the player the option to go back or quit the game.
     * @return a JPanel with all option buttons added.
     */
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

    /**This method reads the Rules.txt-file and reads it out with a BufferedReader, throws IO-exception when something goes wrong during the reading process.
     */
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

    /**This method makes a pop-up frame that contains an end message and congratulations to the winner/winners (if ex aequo).
     * @param game , contains needed info about player names and method to determine the winner.
     */
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
