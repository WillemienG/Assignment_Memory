public class Game {

    private Board board;
    private Player player1;
    private Player player2;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * This method fixes the dimensions of the memory game for this round and makes a board-object with it.
     */
    public void prepareGame() {
        BoardDimensioner boardDimensioner = new BoardDimensioner();
        final int height = boardDimensioner.determineWidthHeight()[0];
        final int width = boardDimensioner.determineWidthHeight()[1];
        BoardDesigner boardDesigner = new BoardDesigner();
        this.board = boardDesigner.createBoard(height, width);
        MakePlayers makePlayers = new MakePlayers();
        this.player1 = makePlayers.makePlayer1();
        this.player2 = makePlayers.makePlayer2();
    }

    /**
     * This method swaps the two players of the game, so the turn is passed from one player to the other.
     * Both players are temporarily stored in temp-objects and then inserted in a Player-array again, but in a swapped order.
     *
     * @param players is a Player-array with the two current Player-objects.
     * @return a 1-by-2-Player-array in which position[0] = player who gets to play next.
     */
    public Player[] determineNextPlayer(Player[] players) {
        Player temp1 = players[0];
        Player temp2 = players[1];
        players[0] = temp2;
        players[1] = temp1;
        return players;
    }

    public void playTurn(Player[] players) {
        int nbTilesMatched = 0;
        while (nbTilesMatched < board.getHeight() * board.getWidth() / 2) {
            int[] pickedTileCo = players[0].pickTiles(board.getHeight(), board.getWidth());
            Tile tileToTurn1 = board.getTiles()[pickedTileCo[0]][pickedTileCo[1]];
            tileToTurn1.setTurned(true);
            board.printBoard();

            Tile tileToTurn2 = board.getTiles()[pickedTileCo[0]][pickedTileCo[1]];
            tileToTurn2.setTurned(true);
            board.printBoard();
            if (tileToTurn1.getDownsideValue().equals(tileToTurn2.getDownsideValue())) {
                nbTilesMatched += 1;
                players[0].addScore();
            } else {
                tileToTurn1.setTurned(false);
                tileToTurn2.setTurned(false);
                board.printBoard();
            }
            determineNextPlayer(players);
        }
    }


    public static void main(String[] args) {
        Game game = new Game(Board board, Player player1, Player player2)
    }
}
