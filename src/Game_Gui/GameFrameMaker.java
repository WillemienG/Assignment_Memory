package Game_Gui;

import javax.swing.*;
import java.awt.*;

import Board.Tile;
import Game_Gui.ActionListeners.NextButtonListener;
import Game_Gui.ActionListeners.TileButtonListener;
import Main.Game;

public class GameFrameMaker {

    ComponentsContainersFactory componentsContainersFactory = new ComponentsContainersFactory();

    public void makeGameFrame(Game game) {
        JFrame gameFrame = new JFrame("Memory - the game");
        JLabel gameName = new JLabel("Memory - the game");
        gameName.setFont(new Font("Tahoma",Font.BOLD,20));
        gameName.setBorder(BorderFactory.createEmptyBorder(10,30,0,30));

        JPanel scorePanel = new JPanel();
        JLabel scoreBoard = new JLabel("Score board");
        scoreBoard.setFont(new Font("Tahoma",Font.BOLD,15));
        JLabel score1Label = new JLabel(game.getPlayers()[0].getPlayerName() + "   " + game.getPlayers()[0].getPlayerScore());
        JLabel score2Label = new JLabel(game.getPlayers()[1].getPlayerName() + "   " + game.getPlayers()[1].getPlayerScore());

        scorePanel.setLayout(new BorderLayout());
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10,30,200,10));
        scorePanel.add(scoreBoard,BorderLayout.NORTH);
        scorePanel.add(score1Label,BorderLayout.CENTER);
        scorePanel.add(score2Label,BorderLayout.SOUTH);

        JPanel boardPanel = makeBoardPanel(game,score1Label,score2Label);

        gameFrame.setLayout(new BorderLayout());
        gameFrame.getContentPane().add(gameName, BorderLayout.NORTH);
        gameFrame.getContentPane().add(boardPanel, BorderLayout.WEST);
        gameFrame.getContentPane().add(scorePanel, BorderLayout.EAST);
        gameFrame.getContentPane().add(componentsContainersFactory.makeOptionsPanel(), BorderLayout.SOUTH);

        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    /**This method makes a panel with the following components:
     * the buttons tileButtons which represent the memory tiles, put in a grid of height x width
     * the label IndicateTurn which tells which player is on
     * the button nextButton which needs to be clicked when a turn is over. This triggers the nextButtonActionListener.
     * @param game, the game-object which contains all of the relevant information for the game
     * @param score1Label , the label that shows the score of the player who is on, needs to be updated
     * @param score2Label , the label that shows the score of the other player, needs to be updated
     * @return a JPanel, boardPanel, to be put in the gameFrame
     */
    public JPanel makeBoardPanel(Game game, JLabel score1Label, JLabel score2Label) {
        JPanel tilePanel = new JPanel();
        JButton[][] tileButtons = new JButton[game.getBoard().getHeight()][game.getBoard().getWidth()];
        tilePanel.setLayout(new GridLayout(game.getBoard().getHeight(),game.getBoard().getWidth()));
        Tile[][] tilesForButtons = game.getBoard().getTiles();

        JLabel indicateTurn = new JLabel(game.getPlayers()[0].getPlayerName() + " can now play.");
        JButton nextButton = new JButton("Next step");
        nextButton.setEnabled(false);
        nextButton.addActionListener(new NextButtonListener(game,score1Label,score2Label,indicateTurn,tileButtons,nextButton));
        for (int i = 0; i < game.getBoard().getHeight(); i++) {
            for (int j = 0; j < game.getBoard().getWidth(); j++) {
                Tile clickedTile = tilesForButtons[i][j];
                tileButtons[i][j]= componentsContainersFactory.createTileButton(clickedTile);
                final JButton clickedButton = tileButtons[i][j]; //has to be final or can't be called in anonymous actionPerformed-innerclass
                tilePanel.add(tileButtons[i][j]);
                tileButtons[i][j].setActionCommand(tilesForButtons[i][j].getDownsideValue());
                tileButtons[i][j].addActionListener(new TileButtonListener(i,j,game,clickedButton,nextButton));
            }
        }

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout());
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10,30,0,30));

        boardPanel.add(indicateTurn, BorderLayout.NORTH);
        boardPanel.add(tilePanel, BorderLayout.CENTER);
        boardPanel.add(nextButton, BorderLayout.SOUTH);
        return boardPanel;
    }

}
