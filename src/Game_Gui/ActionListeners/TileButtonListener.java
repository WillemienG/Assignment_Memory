package Game_Gui.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Board.Tile;
import Game_Gui.ComponentsContainersFactory;
import Main.Game;

public class TileButtonListener implements ActionListener {
    private int i;
    private int j;
    Game game;
    JButton clickedButton;
    JButton nextButton;
    ComponentsContainersFactory componentsContainersFactory = new ComponentsContainersFactory();

    public TileButtonListener(int i, int j,Game game, JButton clickedButton,JButton nextButton) {
        this.i = i;
        this.j = j;
        this.game = game;
        this.clickedButton = clickedButton;
        this.nextButton = nextButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!game.isSelectionPossible()) {
            componentsContainersFactory.makeNotYourTurnFrame();
        }
        Tile clickedTile = game.getBoard().getTiles()[i][j];
        if (game.getFirstTurnedTile() == null) {
            game.turnFirstTile(clickedTile);
            componentsContainersFactory.writeButtonText(clickedTile, clickedButton);
        } else {
            clickedTile.setTurned(true);
            componentsContainersFactory.writeButtonText(clickedTile, clickedButton);
            game.turnSecondTile(clickedTile);
        }
        if (game.isSelectionPossible()) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }
    }
}
