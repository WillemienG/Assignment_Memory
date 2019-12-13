package Game_Gui.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Board.Tile;

public class TileButtonListener implements ActionListener {
    Tile tile1;

    public TileButtonListener(Tile tile1) {
        this.tile1 = tile1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
