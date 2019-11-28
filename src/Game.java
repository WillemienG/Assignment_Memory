public class Game {

    private Board board;
    private Player player1;
    private Player player2;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Game() {
    }

    public Board getBoard() {
        return board;
    }

    /**
     * This method fixes the dimensions of the memory game for this round and makes a board-object with it.
     */
    public void prepareGame() {
        BoardDimensioner boardDimensioner = new BoardDimensioner();
        final int[] dimensions = boardDimensioner.determineWidthHeight();
        final int height = dimensions[0];
        final int width = dimensions[1];
        BoardDesigner boardDesigner = new BoardDesigner();
        this.board = boardDesigner.createBoard(height, width);
        MakePlayers makePlayers = new MakePlayers();
        this.player1 = makePlayers.makePlayer1();
        this.player2 = makePlayers.makePlayer2();
        board.printBoard();
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
            int[] pickedTileCo1 = players[0].pickTiles(board.getHeight(), board.getWidth());
            Tile tileToTurn1 = board.getTiles()[pickedTileCo1[0]][pickedTileCo1[1]];
            tileToTurn1.setTurned(true);
            System.out.print("\n");
            board.printBoard();
            System.out.print("\n");

            int[] pickedTileCo2 = players[0].pickTiles(board.getHeight(), board.getWidth());
            Tile tileToTurn2 = board.getTiles()[pickedTileCo2[0]][pickedTileCo2[1]];
            tileToTurn2.setTurned(true);
            System.out.print("\n");
            board.printBoard();
            System.out.print("\n");
            if (tileToTurn1.getDownsideValue().equals(tileToTurn2.getDownsideValue())) {
                nbTilesMatched += 1;
                players[0].addScore();
            } else {
                tileToTurn1.setTurned(false);
                tileToTurn2.setTurned(false);
                System.out.print("\n");
                board.printBoard();
                System.out.print("\n");
            }
            determineNextPlayer(players);
        }
    }

    public static void main(String[] args) {
        Game mijnSpelletje = new Game();
        mijnSpelletje.prepareGame();
        Player player1 = mijnSpelletje.player1;
        Player player2 = mijnSpelletje.player2;
        Player[] players = {player1, player2};
        mijnSpelletje.playTurn(players);
    }
}
