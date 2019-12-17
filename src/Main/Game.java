package Main;

import Board.Board;
import Board.BoardDimensioner;
import Board.BoardDesigner;
import Board.Tile;
import Players.PlayerMaker;
import Players.Player;

public class Game {

    private Board board;                        //the board-object that contains all Tile for this game.
    private Player[] players = new Player[2];   //the Player-array that contains the 2 players, thus their names and scores, for this game.
    private int nbPairs;                        //the int that sets how many pairs of Tile there are that needs to be turned = (nbTiles-nbSpecialTiles)/2.
    private int nbTilesMatched = 0;             //the int that keeps track how many pairs of Tile already have been matched, is frequently updated.
    private Tile lastTurnedTile;                //the Tile-object that saves the last turned Tile in a turn. Is null'ed and re-assigned each turn.
    private Tile firstTurnedTile;               //the Tile-object that saves the first turned Tile in a turn. Is null'ed and re-assigned each turn.
    private boolean isSelectionPossible = true; //the boolean that indicates whether a player can play or not. If false, the nextButton needs to be clicked.
    private String difficultyLevel;             //the string that sets the difficultyLevel on which this game is played.

    public Game() {                             //a constructor to make an object of this class on which all Game-methods can be called
    }

    /**
     * This method assembles all methods that are needed to set up the game: board dimensioning, board designing, player making.
     * It assigns the output of these methods to the instance variables explained above.
     */
    public void prepareGame(int nbRows, int nbColumns, String difficultyLevel, String playerMode, String player1Name, String player2Name) {
        BoardDimensioner boardDimensioner = new BoardDimensioner();
        final int[] characteristics = boardDimensioner.determineCharacteristics(difficultyLevel, nbRows, nbColumns);
        final int height = characteristics[0];
        final int width = characteristics[1];
        this.nbPairs = characteristics[2];
        this.difficultyLevel = difficultyLevel;
        BoardDesigner boardDesigner = new BoardDesigner();
        this.board = boardDesigner.finishBoard(height, width, difficultyLevel);
        PlayerMaker playerMaker = new PlayerMaker();
        this.players[0] = playerMaker.makePlayer1(player1Name);
        this.players[1] = playerMaker.makePlayer2(playerMode, player2Name);
    }

    /**This method processes the first turned tile. It sets isTurned on true, so the downsideValue will appear on the play board. The nbTimesTurned is increased by 1,
     * to keep track of this for the scoring method. Then, depending on the downsideValue of this tile:
     * - Skip: turn is over, so setSelectionPossible is put on false (player has to click the nextButton now)
     * - Shuffle: tiles are shuffled randomly and turn is over.
     * - Normal tile: nothing happens, the player can go on and turn a second tile
     *
     * @param tileToTurn1 , the tile that was turned.
     */
    public void turnFirstTile(Tile tileToTurn1) {
        this.setFirstTurnedTile(tileToTurn1);
        tileToTurn1.setTurned(true);
        tileToTurn1.setNbTimesTurned(tileToTurn1.getNbTimesTurned()+1);
        switch (tileToTurn1.getDownsideValue()) {
            case "Skip":
                this.setSelectionPossible(false);
                break;
            case "Shuffle":
                BoardDesigner boardDesigner = new BoardDesigner();
                this.board = boardDesigner.shuffleBoard(board);
                this.setSelectionPossible(false);
                break;
            default:
                this.setSelectionPossible(true);
        }
    }

    /**This method processes the second turned tile, if existing. Everything goes the same, except for when a normal tile is picked:
     * then the method passes on to checkTileMatch(), which is not needed nor called when a special tile was chosen.
     * @param tileToTurn2 , the tile that was turned.
     */
    public void turnSecondTile(Tile tileToTurn2) {
        this.setLastTurnedTile(tileToTurn2);
        this.setSelectionPossible(false);
        tileToTurn2.setNbTimesTurned(tileToTurn2.getNbTimesTurned()+1);
        switch (tileToTurn2.getDownsideValue()) {
            case "Skip":
                firstTurnedTile.setTurned(false);
                break;
            case "Shuffle":
                BoardDesigner boardDesigner = new BoardDesigner();
                this.board = boardDesigner.shuffleBoard(board);
                firstTurnedTile.setTurned(false);
                break;
            default:
                checkTileMatch();
        }
    }

    /**This method checks whether the two turned tiles form a pair, i.e. whether their downsideValues are identical.
     * If yes, nbTilesMatched is increased as well, to keep track of the progress of the game and the addScore() of a computer player.
     * Then the score of the lucky player is increased with the addScore(), a method that is declared differently for human and computer players.
     * If not, the two tiles are set on isTurned = false again and a new turn starts.
     */
    public void checkTileMatch() {
        if (firstTurnedTile.getDownsideValue().equals(lastTurnedTile.getDownsideValue())) {
            this.setNbTilesMatched(getNbTilesMatched() + 1);
            players[0].addScore(firstTurnedTile.getNbTimesTurned(), lastTurnedTile.getNbTimesTurned(), nbPairs, nbTilesMatched);
        } else {
            firstTurnedTile.setTurned(false);
            lastTurnedTile.setTurned(false);
        }
    }

    /**
     * This method swaps the two players of the game, so the turn is passed from one player to the other.
     * Both players are temporarily stored in temp-objects and then inserted in a Players.Player-array again, but in a swapped order.
     *
     */
    public void determineNextPlayer() {
        Player temp1 = players[0];
        Player temp2 = players[1];
        players[0] = temp2;
        players[1] = temp1;
    }

    /** This method indicates whether the game is finished.
     * @return true when the number of tiles matched is not yet equal to the total number of pairs. False when they're equal.
     */
    public boolean isGameStillGoing() {
        return (nbTilesMatched < nbPairs);
    }

    /**This method determines who won this game by looking at their scores.
     *
     * @return the name of the winning player, or a message about the ex aequo.
     */
    public String determineWinner() {
        String winner = "whatever";
        double score1 = players[0].getPlayerScore();
        double score2 = players[1].getPlayerScore();
        System.out.print("\n");
        if (score1 > score2) {
            winner = players[0].getPlayerName();
        } else if (score1 == score2) {
            winner = "you both did well in this game and finished with an ex aequo of " + score1;
        } else if (score1 < score2) {
            winner = players[1].getPlayerName();
        }
        return winner;
    }

    public Board getBoard() {
        return board;
    }

    public void setLastTurnedTile(Tile lastTurnedTile) {
        this.lastTurnedTile = lastTurnedTile;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getNbTilesMatched() {
        return nbTilesMatched;
    }

    public Tile getFirstTurnedTile() {
        return firstTurnedTile;
    }

    public boolean isSelectionPossible() {
        return isSelectionPossible;
    }

    public void setNbTilesMatched(int nbTilesMatched) {
        this.nbTilesMatched = nbTilesMatched;
    }

    public void setFirstTurnedTile(Tile firstTurnedTile) {
        this.firstTurnedTile = firstTurnedTile;
    }

    public void setSelectionPossible(boolean isSelectionPossible) {
        this.isSelectionPossible = isSelectionPossible;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }
}

