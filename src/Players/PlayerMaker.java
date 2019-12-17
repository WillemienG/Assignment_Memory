package Players;

public class PlayerMaker {

    /**This method creates a HumanPlayer-object. In the memory game, the first player is always a HumanPlayer. The Player starts with a score of 0.
     * @param player1Name , this is given as input by the player him/herself in the JTextField
     * @return player1, a Player-object for the first player.
     */
    public Player makePlayer1(String player1Name) {
        HumanPlayer player1 = new HumanPlayer(0, player1Name);
        return player1;
    }

    /**This method creates the second Player-object. Depending on the selected playerMode, this second Player is a HumanPlayer or a ComputerPlayer.
     * A HumanPlayer is initialized with his/her own chosen name and score 0, a ComputerPlayer gets 'the computer' and score 0.
     * @param playerMode , the String that can be 'Multiplayer' or 'against the computer'.
     * @param player2Name , this is given as input by the player him/herself in the JTextField
     * @return player2, a Player-object for the second player.
     */
    public Player makePlayer2(String playerMode, String player2Name) {
        if (playerMode.equals("Multiplayer")) {
            HumanPlayer player2 = new HumanPlayer(0, player2Name);
            return player2;
        } else {
            ComputerPlayer player2 = new ComputerPlayer(0, "the computer");
            return player2;
        }
    }

}
