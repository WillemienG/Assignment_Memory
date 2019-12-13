package Main;

import Board.Board;
import Board.BoardDimensioner;
import Board.BoardDesigner;
import Board.Tile;
import Highscores.HighscoreUpdater;
import Players.MakePlayers;
import Players.Player;

import javax.swing.*;

public class Game {

    public Board board;
    public Player player1;
    public Player player2;
    public int nbPairs;
    public int nbTilesMatched;
    public Tile lastTurnedTile;
    public JButton lastClickedButton;

    public Game() {
    }

    public Board getBoard() {
        return board;
    }

    /**
     * This method fixes the dimensions of the memory game for this round and makes a board-object with it.
     */
    public void prepareGame(int nbRows, int nbColumns, String difficultyLevel, String playerMode, String player1Name, String player2Name) {
        BoardDimensioner boardDimensioner = new BoardDimensioner();
        final int[] characteristics = boardDimensioner.determineCharacteristics(difficultyLevel, nbRows, nbColumns);
        final int height = characteristics[0];
        final int width = characteristics[1];
        this.nbPairs = characteristics[2];
        BoardDesigner boardDesigner = new BoardDesigner();
        this.board = boardDesigner.finishBoard(height, width, difficultyLevel);
        MakePlayers makePlayers = new MakePlayers();
        this.player1 = makePlayers.makePlayer1(player1Name);
        this.player2 = makePlayers.makePlayer2(playerMode, player2Name);
        board.printBoard();
    }

   public void turnFirstTile(Player[] players, Tile tileToTurn1) {
        tileToTurn1.setTurned(true);
        tileToTurn1.setNbTimesTurned(tileToTurn1.getNbTimesTurned()+1);
        switch (tileToTurn1.getDownsideValue()) {
            case "Skip":
                tileToTurn1.setTurned(true);
                determineNextPlayer(players);
                break;
            case "Shuffle":
                BoardDesigner boardDesigner = new BoardDesigner();
                tileToTurn1.setTurned(true);
                this.board = boardDesigner.shuffleBoard(board, board.getHeight(), board.getWidth());
                determineNextPlayer(players);
                break;
            default:
                tileToTurn1.setTurned(true);
        }
    }

    public void turnSecondTile(Player[] players, Tile tileToTurn2) {
        tileToTurn2.setTurned(true);
        tileToTurn2.setNbTimesTurned(tileToTurn2.getNbTimesTurned()+1);
        System.out.println(players[0].getPlayerName() + " now has a score of " + players[0].getPlayerScore() + ".");
        switch (tileToTurn2.getDownsideValue()) {
            case "Shuffle":
                BoardDesigner boardDesigner = new BoardDesigner();
                this.board = boardDesigner.shuffleBoard(board, board.getHeight(), board.getWidth());
                determineNextPlayer(players);
                tileToTurn2.setTurned(true);
                break;
            default:
                determineNextPlayer(players);
        }
    }

    public void checkTileMatch(Tile tile1, Tile tile2, Player[] players, Game game) {
        if (tile1.getDownsideValue().equals(tile2.getDownsideValue())) {
            players[0].addScore(tile1.getNbTimesTurned(),tile2.getNbTimesTurned(),game.nbPairs,game.nbTilesMatched);
            nbTilesMatched += 1;
        } else {
            tile1.setTurned(false);
            tile2.setTurned(false);
        }
    }

    /**
     * This method swaps the two players of the game, so the turn is passed from one player to the other.
     * Both players are temporarily stored in temp-objects and then inserted in a Players.Player-array again, but in a swapped order.
     *
     * @param players is a Players.Player-array with the two current Players.Player-objects.
     * @return a 1-by-2-Players.Player-array in which position[0] = player who gets to play next.
     */
    public Player[] determineNextPlayer(Player[] players) {
        Player temp1 = players[0];
        Player temp2 = players[1];
        players[0] = temp2;
        players[1] = temp1;
        return players;
    }

    public Tile getLastTurnedTile() {
        return lastTurnedTile;
    }

    public void setLastTurnedTile(Tile lastTurnedTile) {
        this.lastTurnedTile = lastTurnedTile;
    }

    public JButton getLastClickedButton() {
        return lastClickedButton;
    }

    public void setLastClickedButton(JButton lastClickedButton) {
        this.lastClickedButton = lastClickedButton;
    }

    public String determineWinner(Player[] players) {
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
}

