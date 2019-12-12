package Game_Gui.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Board.Tile;

import javax.swing.*;

public class TileButtonListener implements ActionListener {
    Tile tile1;
    Tile tile2;
    JButton buttonForTile;

    public TileButtonListener(Tile tile1, JButton buttonForTile) {
        this.tile1 = tile1;
        this.buttonForTile = buttonForTile;
    }

    public TileButtonListener(Tile tile1, Tile tile2) {
        this.tile1 = tile1;
        this.tile2 = tile2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
