package Game_Gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import Main.Game;
import Board.Board;
import Board.Tile;
import Game_Gui.ActionListeners.TileButtonListener;
import Players.Player;

public class GameFrameMaker {

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

    public void makeGameFrame(Board board, Player[] players, Game game) {
        JFrame gameFrame = new JFrame("Memory - the game");
        JLabel gameName = new JLabel("Memory - the game");
        gameName.setFont(new Font("Tahoma",Font.BOLD,20));
        gameName.setBorder(BorderFactory.createEmptyBorder(10,30,0,30));



        JPanel scorePanel = new JPanel();
        JLabel scoreBoard = new JLabel("Score board");
        scoreBoard.setFont(new Font("Tahoma",Font.BOLD,15));
        JLabel score1Label = new JLabel(players[0].getPlayerName() + "   " + players[0].getPlayerScore());
        JLabel score2Label = new JLabel(players[1].getPlayerName() + "   " + players[1].getPlayerScore());

        scorePanel.setLayout(new BorderLayout());
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10,30,200,10));
        scorePanel.add(scoreBoard,BorderLayout.NORTH);
        scorePanel.add(score1Label,BorderLayout.CENTER);
        scorePanel.add(score2Label,BorderLayout.SOUTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout());
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10,30,0,30));
        JLabel indicateTurn = new JLabel(players[0].getPlayerName() + " can now play.");

        JButton[][] tileButtons = new JButton[board.getHeight()][board.getWidth()];
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout(new GridLayout(board.getHeight(),board.getWidth()));
        Tile[][] tilesForButtons = board.getTiles();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Tile clickedTile = tilesForButtons[i][j];
                tileButtons[i][j]= createTileButton(clickedTile);
                final JButton buttonForTile = tileButtons[i][j]; //has to be final or can't be called in anonymous actionPerformed-innerclass
                tilePanel.add(tileButtons[i][j]);
                tileButtons[i][j].setActionCommand(tilesForButtons[i][j].getDownsideValue());
                tileButtons[i][j].addActionListener(new TileButtonListener(clickedTile) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (game.lastTurnedTile == null) {
                            game.turnFirstTile(players, clickedTile);
                            writeButtonText(clickedTile,buttonForTile);
                            game.setLastTurnedTile(clickedTile);
                            game.setLastClickedButton(buttonForTile);
                        } else {
                            game.turnSecondTile(players,clickedTile);
                            writeButtonText(clickedTile,buttonForTile);

                            game.checkTileMatch(game.lastTurnedTile,clickedTile,players,game);
                            writeButtonText(game.lastTurnedTile,game.lastClickedButton);
                            writeButtonText(clickedTile,buttonForTile);
                            game.setLastTurnedTile(null); //erase lastTurnedTile so a new turn can start
                            game.setLastClickedButton(null);
                            indicateTurn.setText(players[0].getPlayerName() + " can now play.");
                            score1Label.setText(players[0].getPlayerName() + "   " + players[0].getPlayerScore());
                            score2Label.setText(players[1].getPlayerName() + "   " + players[1].getPlayerScore());
                        }
                    }
                });
            }
        }
        boardPanel.add(indicateTurn, BorderLayout.NORTH);
        boardPanel.add(tilePanel, BorderLayout.CENTER);

        gameFrame.setLayout(new BorderLayout());
        gameFrame.getContentPane().add(gameName, BorderLayout.NORTH);
        gameFrame.getContentPane().add(boardPanel, BorderLayout.WEST);
        gameFrame.getContentPane().add(scorePanel, BorderLayout.EAST);
        HomeFrameMaker homeFrameMaker = new HomeFrameMaker();
        gameFrame.getContentPane().add(homeFrameMaker.makeOptionsPanel(), BorderLayout.SOUTH);

        gameFrame.pack();
        gameFrame.setVisible(true);
    }

}
