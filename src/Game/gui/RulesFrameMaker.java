package Game.gui;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class RulesFrameMaker {

    public void readRules() {
        JFrame rulesFrame = new JFrame("Memory: the rules");
        rulesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea rulesArea = new JTextArea();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("Rules")));
            rulesArea.read(bf, "Getting the rules");
        } catch (IOException ioe) {
            System.out.println("Something went wrong");
        }

        rulesFrame.getContentPane().add(rulesArea, BorderLayout.CENTER);
        rulesFrame.pack();
        rulesFrame.setVisible(true);
    }
}
