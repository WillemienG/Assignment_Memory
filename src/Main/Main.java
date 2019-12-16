package Main;

import Game_Gui.HomeFrameMaker;

import javax.swing.*;

public class Main {

    /**This method executes the project. It calls the GUI-thread in the event loop.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HomeFrameMaker homeFrameMaker = new HomeFrameMaker();
                homeFrameMaker.makeHomeFrame();
            }
        });
    }
}
