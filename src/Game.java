import Board.Board;
import Board.BoardDimensioner;
import Board.BoardDesigner;
import Board.Tile;
import Highscores.HighscoreUpdater;
import Players.MakePlayers;
import Players.Player;

public class Game {

    private Board board;
    private Player player1;
    private Player player2;
    private String difficultyLevel;
    private int nbPairs;
    private int nbTilesMatched;

    public Game(Board board, Player player1, Player player2, String difficultyLevel, int nbPairs, int nbTilesMatched) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.difficultyLevel = difficultyLevel;
        this.nbPairs = nbPairs;
        this.nbTilesMatched = nbTilesMatched;
    }

    public Game() {
    }

    public Board getBoard() {
        return board;
    }

    /**
     * This method fixes the dimensions of the memory game for this round and makes a board-object with it.
     */
    private void prepareGame() {
        BoardDimensioner boardDimensioner = new BoardDimensioner();
        this.difficultyLevel = boardDimensioner.askDifficultyLevel();
        final int[] characteristics = boardDimensioner.determineCharacteristics(difficultyLevel);
        final int height = characteristics[0];
        final int width = characteristics[1];
        this.nbPairs = characteristics[2];
        BoardDesigner boardDesigner = new BoardDesigner();
        this.board = boardDesigner.finishBoard(height, width, difficultyLevel);
        MakePlayers makePlayers = new MakePlayers();
        this.player1 = makePlayers.makePlayer1();
        this.player2 = makePlayers.makePlayer2();
        board.printBoard();
    }

    /**
     * This method swaps the two players of the game, so the turn is passed from one player to the other.
     * Both players are temporarily stored in temp-objects and then inserted in a Players.Player-array again, but in a swapped order.
     *
     * @param players is a Players.Player-array with the two current Players.Player-objects.
     * @return a 1-by-2-Players.Player-array in which position[0] = player who gets to play next.
     */
    private Player[] determineNextPlayer(Player[] players) {
        Player temp1 = players[0];
        Player temp2 = players[1];
        players[0] = temp2;
        players[1] = temp1;
        return players;
    }

    private void turnSecondTile(Player[] players,int[] pickedTileCo1, Tile tileToTurn1) {
        int[] pickedTileCo2;
        boolean isTurnable2 = false;
        while (!isTurnable2) {
            boolean isOtherTile = false;
            while (!isOtherTile) {
                pickedTileCo2 = players[0].pickTiles(board.getHeight(), board.getWidth());
                if (pickedTileCo2[0] == pickedTileCo1[0] && pickedTileCo2[1] == pickedTileCo1[1]) {
                    System.out.println("You can't pick the same tile twice. Try again");
                } else {
                    isOtherTile = true;
                    Tile tileToTurn2 = board.getTiles()[pickedTileCo2[0]][pickedTileCo2[1]];
                    if (tileToTurn2.isTurned()) {
                        System.out.println("This tile can't be turned anymore. Try again.");
                    } else {
                        tileToTurn2.setTurned(true);
                        board.printBoard();
                        isTurnable2 = true;
                        if (tileToTurn1.getDownsideValue().equals(tileToTurn2.getDownsideValue())) {
                            nbTilesMatched += 1;
                            players[0].addScore();
                        } else {
                            tileToTurn1.setTurned(false);
                            tileToTurn2.setTurned(false);
                            board.printBoard();
                        }
                        System.out.print("\n");
                        System.out.println(players[0].getPlayerName() + " now has a score of " + players[0].getPlayerScore() + ".");
                        switch (tileToTurn2.getDownsideValue()) {
                            case "Shuffle":
                                BoardDesigner boardDesigner = new BoardDesigner();
                                board = boardDesigner.shuffleBoard(board, board.getHeight(), board.getWidth());
                                determineNextPlayer(players);
                                tileToTurn2.setTurned(true);
                                break;
                            default:
                                determineNextPlayer(players);
                        }
                    }
                }
            }
        }
    }

    private void playGame(Player[] players, int nbPairs, int nbTilesMatched) {
        int[] pickedTileCo1 = {0,0};
        Tile tileToTurn1 = new Tile(false,null,"whatever");
        while (nbTilesMatched < nbPairs) {
            boolean isTurnable1 = false;
            while (!isTurnable1) {
                pickedTileCo1 = players[0].pickTiles(board.getHeight(), board.getWidth());
                tileToTurn1 = board.getTiles()[pickedTileCo1[0]][pickedTileCo1[1]];
                if (tileToTurn1.isTurned()) {
                    System.out.println("This tile can't be turned anymore. Try again.");
                } else {
                    tileToTurn1.setTurned(true);
                    board.printBoard();
                    isTurnable1 = true;
                }
            }
            switch (tileToTurn1.getDownsideValue()) {
                case "Skip":
                    tileToTurn1.setTurned(true);
                    determineNextPlayer(players);
                    break;
                case "Shuffle":
                    BoardDesigner boardDesigner = new BoardDesigner();
                    boardDesigner.shuffleBoard(board, board.getHeight(), board.getWidth());
                    determineNextPlayer(players);
                    break;
                default:
                    turnSecondTile(players, pickedTileCo1, tileToTurn1);
            }
        }
    }

    private void determineWinner(Player[] players) {
        String winner;
        int score1 = players[0].getPlayerScore();
        int score2 = players[1].getPlayerScore();
        System.out.print("\n");
        if (score1 > score2) {
            winner = players[0].getPlayerName();
            System.out.println("The winner of this game is " + winner +", congratulations! You ended with a score of " + score1 + ".");
        } else if (score1 == score2) {
            System.out.println("You both did well in this game and finished with an ex aequo of " + score1 + ".");
        } else if (score1 < score2) {
            winner = players[1].getPlayerName();
            System.out.println("The winner of this game is " + winner +", congratulations! You ended with a score of " + score2 + ".");
        }
    }

    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.prepareGame();
        Player player1 = myGame.player1;
        Player player2 = myGame.player2;
        Player[] players = {player1, player2};
        myGame.playGame(players,myGame.nbPairs,myGame.nbTilesMatched);
        myGame.determineWinner(players);
        HighscoreUpdater highscoreUpdater = new HighscoreUpdater();
        highscoreUpdater.writeHighscores(players,myGame.difficultyLevel);
    }
}
