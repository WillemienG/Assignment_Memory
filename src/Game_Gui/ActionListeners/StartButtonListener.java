package Game_Gui.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game_Gui.GUIStuffFactory;
import Game_Gui.GameFrameMaker;
import Main.Game;

import javax.swing.*;

public class StartButtonListener implements ActionListener {

    GUIStuffFactory guiStuffFactory = new GUIStuffFactory();

    Game game;
    JSpinner rowSpinner;
    JSpinner columnSpinner;
    ButtonGroup diffLevelGroup;
    ButtonGroup playerModeGroup;
    JTextField player1Name;
    JTextField player2Name;

    public StartButtonListener(Game game,JSpinner rowSpinner,JSpinner columnSpinner,ButtonGroup diffLevelGroup,ButtonGroup playerModeGroup,JTextField player1Name,JTextField player2Name) {
        this.game = game;
        this.rowSpinner = rowSpinner;
        this.columnSpinner = columnSpinner;
        this.diffLevelGroup = diffLevelGroup;
        this.playerModeGroup = playerModeGroup;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int nbRows = (Integer) rowSpinner.getValue();
        int nbColumns = (Integer) columnSpinner.getValue();
        int nbTiles = nbRows*nbColumns;
        String diffLevel = diffLevelGroup.getSelection().getActionCommand();
        String playerMode = playerModeGroup.getSelection().getActionCommand();
        String player1ChosenName = player1Name.getText();
        String player2ChosenName = player2Name.getText();

        if (nbTiles % 2 == 0) {
            game.prepareGame(nbRows, nbColumns, diffLevel, playerMode, player1ChosenName, player2ChosenName);
            GameFrameMaker gameFrameMaker = new GameFrameMaker();
            gameFrameMaker.makeGameFrame(game);
        } else {
            guiStuffFactory.makeDimErrorFrame();
        }
    }
}
