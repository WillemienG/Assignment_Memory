package Game_Gui.ActionListeners;

import Board.Tile;
import Game_Gui.GUIStuffFactory;
import Highscores.HighscoreUpdater;
import Main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextButtonListener implements ActionListener {

    GUIStuffFactory guiStuffFactory = new GUIStuffFactory();
    Game game;
    JLabel score1Label;
    JLabel score2Label;
    JLabel indicateTurn;
    JButton[][] tileButtons;
    JButton nextButton;

    public NextButtonListener (Game game, JLabel score1Label, JLabel score2Label, JLabel indicateTurn, JButton[][] tileButtons, JButton nextButton) {
        this.game = game;
        this.score1Label = score1Label;
        this.score2Label = score2Label;
        this.indicateTurn = indicateTurn;
        this.tileButtons = tileButtons;
        this.nextButton = nextButton;
    }

    /**This method takes care of finishing up a played turn and preparing everything for the next turn.
     * Score board is updated with the newest scores, all buttons are checked for which text they should display.
     * Then, if the game is not finished: all saved Tile are erased from the memory, the players are switched and the turn-indicator is updated.
     * isSelectionPossible is set on true so the next player can click a button again. If the next player is the computer, it here gets its own turn (see clickComputerTile).
     * After the computer, all buttons are again refreshed.
     * If the game is finished, the HumanPlayer-score(s) is/are added to the highscores and the end-of-game-message is displayed.
     * @param e , the event where the nextButton is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        updateScoreBoard(score1Label, score2Label);
        refreshAllButtons(tileButtons);
        if (game.isGameStillGoing()) {
            game.setFirstTurnedTile(null);
            game.setLastTurnedTile(null);
            game.determineNextPlayer();
            indicateTurn.setText(game.getPlayers()[0].getPlayerName() + " can now play.");
            game.setSelectionPossible(true);
            if (game.getPlayers()[0].getPlayerName().equals("the computer")) {
                clickComputerTile(nextButton);
            }
            refreshAllButtons(tileButtons);
        } else {
            HighscoreUpdater highscoreUpdater = new HighscoreUpdater();
            highscoreUpdater.writeHighscores(game);
            guiStuffFactory.makeEndFrame(game);
        }
    }

    /** This method makes the computer pick random coordinates to pick a tile from game.getBoard().getTiles(). This picking process is repeated if the randomly picked tile is already turned.
     * A normal turnTile is then played. Then, if the computer can still play, a second tile is chosen and a turnTile is played.
     * A window pops up with the values the computer found and the nextButton is clicked and its NextButtonListener triggered.
     * @param nextButton , the buttons from which the computer can choose.
     */
    private void clickComputerTile(JButton nextButton) {
        boolean isTurnable1 = false;
        Tile chosenTile1 = new Tile(false,null,"Whatever",0);
        Tile chosenTile2 = new Tile(false,null,"Whatever",0);
        int[] pickedTileCo1 = new int[2];
        int[] pickedTileCo2 = new int[2];
        while (!isTurnable1) {
            pickedTileCo1 = game.getPlayers()[0].pickTiles(game.getBoard().getHeight(), game.getBoard().getWidth());
            chosenTile1 = game.getBoard().getTiles()[pickedTileCo1[0]][pickedTileCo1[1]];
            if (!chosenTile1.isTurned()) {
                isTurnable1 = true;
            }
        }
        game.turnFirstTile(chosenTile1);
        if (game.isSelectionPossible()) {
            boolean isTurnable2 = false;
            while (!isTurnable2) {
                pickedTileCo2 = game.getPlayers()[0].pickTiles(game.getBoard().getHeight(), game.getBoard().getWidth());
                chosenTile2 = game.getBoard().getTiles()[pickedTileCo2[0]][pickedTileCo2[1]];
                if (!chosenTile2.isTurned()) {
                    isTurnable2 = true;
                }
            }
            chosenTile2.setTurned(true);
            game.turnSecondTile(chosenTile2);
        }
        guiStuffFactory.makeComputerFrame(chosenTile1,chosenTile2,pickedTileCo1,pickedTileCo2);
        nextButton.setEnabled(true);
        nextButton.doClick();
    }

    /** This method loops over JButton[][] tileButtons and Tile[][]. It updates the text on the tileButton according to the isTurned (true or false) of the Tile
     * with the same coordinates in their respective matrices.
     * @param tileButtons , the buttons which are checked.
     */
    private void refreshAllButtons(JButton[][] tileButtons) {
        for (int i = 0; i < game.getBoard().getHeight(); i++) {
            for (int j = 0; j < game.getBoard().getWidth(); j++) {
                guiStuffFactory.writeButtonText(game.getBoard().getTiles()[i][j],tileButtons[i][j]);
            }
        }
    }

    /** This method updates the scoreboard to the newest score values.
     * It gives a green border to the score of the winning player.
     * @param score1Label , the first label that needs to be updated.
     * @param score2Label , the second label that needs to be updated.
     */
    private void updateScoreBoard(JLabel score1Label, JLabel score2Label) {
        score1Label.setText(game.getPlayers()[0].getPlayerName() + "   " + String.format("%.2f",game.getPlayers()[0].getPlayerScore()));
        score2Label.setText(game.getPlayers()[1].getPlayerName() + "   " + String.format("%.2f",game.getPlayers()[1].getPlayerScore()));
        if (game.getPlayers()[0].getPlayerScore() > game.getPlayers()[1].getPlayerScore()) {
            score2Label.setBorder(null);
            score1Label.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        } else if (game.getPlayers()[0].getPlayerScore() < game.getPlayers()[1].getPlayerScore()) {
            score1Label.setBorder(null);
            score2Label.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }
    }
}
