package Game_Gui.ActionListeners;

import Board.Tile;
import Game_Gui.ComponentsContainersFactory;
import Highscores.HighscoreUpdater;
import Main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextButtonListener implements ActionListener {

    ComponentsContainersFactory componentsContainersFactory = new ComponentsContainersFactory();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.isGameStillGoing()) {
            updateScoreBoard(score1Label, score2Label);
            game.setFirstTurnedTile(null);
            game.setLastTurnedTile(null);
            refreshAllButtons(tileButtons);
            indicateTurn.setText(game.getPlayers()[0].getPlayerName() + " can now play.");
            game.setSelectionPossible(true);
            if (game.getPlayers()[0].getPlayerName().equals("the computer")) {
                clickComputerTile(nextButton);
                if (game.getPlayers()[0].getPlayerName().equals("the computer")) {
                    clickComputerTile(nextButton);
                }
            }
            refreshAllButtons(tileButtons);
        } else {
            HighscoreUpdater highscoreUpdater = new HighscoreUpdater();
            highscoreUpdater.writeHighscores(game);
            componentsContainersFactory.makeEndFrame(game);
        }
    }

    //TODO: als de computer een paar vindt, blijft de 2e daarvan niet omgedraaid waardoor het spel in de war is :(
    /** This method makes the computer pick a random tile from the board-tiles and makes it click.
     * After the click, the normal TileActionListener is triggered.
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
            game.turnSecondTile(chosenTile2);
        }
        componentsContainersFactory.makeComputerFrame(chosenTile1,chosenTile2,pickedTileCo1,pickedTileCo2);
        nextButton.setEnabled(true);
        nextButton.doClick();
    }

    /** This method loops over all the tileButtons and associates them with the corresponding Tile-object.
     * It checks whether this Tile-object is turned or not and updates the button text according to this turned state.
     * @param tileButtons , the buttons which are checked.
     */
    private void refreshAllButtons(JButton[][] tileButtons) {
        for (int i = 0; i < game.getBoard().getHeight(); i++) {
            for (int j = 0; j < game.getBoard().getWidth(); j++) {
                componentsContainersFactory.writeButtonText(game.getBoard().getTiles()[i][j],tileButtons[i][j]);
            }
        }
    }

    /** This method updates the scoreboard to the newest score values.
     * It gives a green border to the score of the winning player.
     * @param score1Label , the first label that needs to be updated.
     * @param score2Label , the second label that needs to be updated.
     */
    private void updateScoreBoard(JLabel score1Label, JLabel score2Label) {
        score1Label.setText(game.getPlayers()[0].getPlayerName() + "   " + game.getPlayers()[0].getPlayerScore());
        score2Label.setText(game.getPlayers()[1].getPlayerName() + "   " + game.getPlayers()[1].getPlayerScore());
        if (game.getPlayers()[0].getPlayerScore() > game.getPlayers()[1].getPlayerScore()) {
            score2Label.setBorder(null);
            score1Label.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        } else if (game.getPlayers()[0].getPlayerScore() < game.getPlayers()[1].getPlayerScore()) {
            score1Label.setBorder(null);
            score2Label.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }
    }
}
