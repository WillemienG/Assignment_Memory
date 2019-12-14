package Main;

import Board.Board;
import Board.BoardDimensioner;
import Board.BoardDesigner;
import Board.Tile;
import Players.PlayerMaker;
import Players.Player;

public class Game {

    private Board board;
    private Player[] players = new Player[2];
    private int nbPairs;
    private int nbTilesMatched;
    private Tile lastTurnedTile;
    private Tile firstTurnedTile;
    private boolean isSelectionPossible = true;
    private String difficultyLevel;

    public Game() {
    }

    /**
     * This method assigns a Game-object with the needed values for a board and players. It fixes these v
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

   public void turnFirstTile(Tile tileToTurn1) {
        this.setFirstTurnedTile(tileToTurn1);
        tileToTurn1.setTurned(true);
        tileToTurn1.setNbTimesTurned(tileToTurn1.getNbTimesTurned()+1);
        switch (tileToTurn1.getDownsideValue()) {
            case "Skip":
                this.setSelectionPossible(false);
                determineNextPlayer();
                break;
            case "Shuffle":
                BoardDesigner boardDesigner = new BoardDesigner();
                this.board = boardDesigner.shuffleBoard(board, board.getHeight(), board.getWidth());
                this.setSelectionPossible(false);
                determineNextPlayer();
                break;
            default:
                this.setSelectionPossible(true);
        }
    }

    public void turnSecondTile(Tile tileToTurn2) {
        this.setLastTurnedTile(tileToTurn2);
        this.setSelectionPossible(false);
        tileToTurn2.setNbTimesTurned(tileToTurn2.getNbTimesTurned()+1);
        switch (tileToTurn2.getDownsideValue()) {
            case "Skip":
                determineNextPlayer();
                firstTurnedTile.setTurned(false);
                break;
            case "Shuffle":
                BoardDesigner boardDesigner = new BoardDesigner();
                this.board = boardDesigner.shuffleBoard(board, board.getHeight(), board.getWidth());
                determineNextPlayer();
                firstTurnedTile.setTurned(false);
                break;
            default:
                checkTileMatch();
                determineNextPlayer();
        }
    }

    public void checkTileMatch() {
        if (firstTurnedTile.getDownsideValue().equals(lastTurnedTile.getDownsideValue())) {
            players[0].addScore(firstTurnedTile.getNbTimesTurned(), lastTurnedTile.getNbTimesTurned(), nbPairs, nbTilesMatched);
            setNbTilesMatched(getNbTilesMatched() + 1);
        } else {
            firstTurnedTile.setTurned(false);
            lastTurnedTile.setTurned(false);
        }
    }

    /**
     * This method swaps the two players of the game, so the turn is passed from one player to the other.
     * Both players are temporarily stored in temp-objects and then inserted in a Players.Player-array again, but in a swapped order.
     *
     * @return a 1-by-2-Players.Player-array in which position[0] = player who gets to play next.
     */
    public Player[] determineNextPlayer() {
        Player temp1 = players[0];
        Player temp2 = players[1];
        players[0] = temp2;
        players[1] = temp1;
        return players;
    }

    /** This method indicates whether the game is finished.
     * @return true when the number of tiles matched is not yet equal to the total number of pairs. False when they're equal.
     */
    public boolean isGameStillGoing() {
        return (nbTilesMatched < nbPairs);
    }

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

    public Tile getLastTurnedTile() {
        return lastTurnedTile;
    }

    public void setLastTurnedTile(Tile lastTurnedTile) {
        this.lastTurnedTile = lastTurnedTile;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getNbPairs() {
        return nbPairs;
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

    public void setNbPairs(int nbPairs) {
        this.nbPairs = nbPairs;
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

