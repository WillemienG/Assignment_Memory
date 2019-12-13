package Game_Gui;

import Players.Player;
import Main.Game;

import javax.swing.*;
import java.awt.*;

public class EndFrameMaker {

    public void makeEndFrame(Player[] players,Game game) {
        JFrame endFrame = new JFrame("Memory - the end");
        JLabel endLabel = new JLabel("The end. Hope you had fun :)");

        String winner = game.determineWinner(players);
        JLabel winnerLabel = new JLabel("Congratulations, " + winner + "!");

        endFrame.getContentPane().setLayout(new BorderLayout());
        endFrame.getContentPane().add(endLabel,BorderLayout.NORTH);
        endFrame.getContentPane().add(winnerLabel,BorderLayout.CENTER);
        HomeFrameMaker homeFrameMaker = new HomeFrameMaker();
        endFrame.getContentPane().add(homeFrameMaker.makeOptionsPanel(),BorderLayout.SOUTH);
    }
}
