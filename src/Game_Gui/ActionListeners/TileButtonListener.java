package Game_Gui.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Board.Tile;
import Game_Gui.GUIStuffFactory;
import Main.Game;

public class TileButtonListener implements ActionListener {
    private int i;
    private int j;
    Game game;
    JButton clickedButton;
    JButton nextButton;
   GUIStuffFactory guiStuffFactory = new GUIStuffFactory();

    /** This constructor creates a new version of the ActionListener for when a Tile is clicked.
     *
     * @param i , the i-coordinate of the corresponding Tile on which the actions will be performed
     * @param j , the j-coordinate of the corresponding Tile on which the actions will be performed
     * @param game , the game-object which contains all of the relevant information for the game
     * @param clickedButton , the button that was clicked
     * @param nextButton , the nextButton which needs to be enabled/disabled
     */
    public TileButtonListener(int i, int j,Game game, JButton clickedButton,JButton nextButton) {
        this.i = i;
        this.j = j;
        this.game = game;
        this.clickedButton = clickedButton;
        this.nextButton = nextButton;
    }

    /**This method overrides the general Swing-actionPerformed. If a player clicks a button of the playing field, actionPerformed first determines if the player was allowed to play at all.
     * A 'sorry, you can't play'-window is launched if it wasn't their turn. Else, the method checks whether it's the first or the second tile of the turn and handles accordingly.
     * The -first or second- tile is 'processed' in a game-method turnFirstTile/turnSecondTile and the buttontext is rewritten to make it look turned.
     * In the end, the nextButton is enabled if the turn is over and thus the player can't select a tile anymore and needs to click the nextButton.
     * @param e , the event of clicking a button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!game.isSelectionPossible()) {
            guiStuffFactory.makeNotYourTurnFrame();
        }
        Tile clickedTile = game.getBoard().getTiles()[i][j];
        if (game.getFirstTurnedTile() == null) {
            game.turnFirstTile(clickedTile);
            guiStuffFactory.writeButtonText(clickedTile, clickedButton);
        } else {
            clickedTile.setTurned(true);
            guiStuffFactory.writeButtonText(clickedTile, clickedButton);
            game.turnSecondTile(clickedTile);
        }
        nextButton.setEnabled(!game.isSelectionPossible());
    }
}
