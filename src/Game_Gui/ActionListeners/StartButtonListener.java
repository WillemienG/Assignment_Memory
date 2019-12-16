package Game_Gui.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game_Gui.GUIStuffFactory;
import Game_Gui.GameFrameMaker;
import Main.Game;

import javax.swing.*;

public class StartButtonListener implements ActionListener {

    //This is needed to call upon methods in the GUIStuffFactory-class.
    GUIStuffFactory guiStuffFactory = new GUIStuffFactory();

    Game game;
    JSpinner rowSpinner;
    JSpinner columnSpinner;
    ButtonGroup diffLevelGroup;
    ButtonGroup playerModeGroup;
    JTextField player1Name;
    JTextField player2Name;

    /**This constructor takes all necessary parameters for the StartButtonActionListener.
     *
     * @param game , contains all needed info for the game
     * @param rowSpinner , passes chosen row-dimension
     * @param columnSpinner , passes chosen column-dimension
     * @param diffLevelGroup , ButtonGroup whose selection passes the chosen difficultyLevel
     * @param playerModeGroup , ButtonGroup whose selection passes the chosen playerMode
     * @param player1Name , passes chosen player1Name
     * @param player2Name , passes chosen player2Name
     */
    public StartButtonListener(Game game,JSpinner rowSpinner,JSpinner columnSpinner,ButtonGroup diffLevelGroup,ButtonGroup playerModeGroup,JTextField player1Name,JTextField player2Name) {
        this.game = game;
        this.rowSpinner = rowSpinner;
        this.columnSpinner = columnSpinner;
        this.diffLevelGroup = diffLevelGroup;
        this.playerModeGroup = playerModeGroup;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    /**This method fires up the game. It calls prepareGame() with all needed parameters, taken from the StartButtonListener-constructor.
     * When the button is clicked, the conditions are checked: for a customized game, the product of dimensions should be even to make a valid game board with pairs of Tile.
     * If not even, an error message pops up. If even, the myGame-object for this new Game is created. PrepareGame() then assigns playerNames, dimensions, difficultyLevel, ...
     * everything needed to this myGame. With these parameters saved in myGame, the new JFrame GameFrame can be made.
     * @param e , when the player clicks the button.
     */
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
