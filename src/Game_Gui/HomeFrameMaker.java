package Game_Gui;

import Main.Game;
import Game_Gui.ActionListeners.StartButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrameMaker {

    public void makeHomeFrame() {
        JFrame homeFrame = new JFrame("Memory - home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel homeLabel = new JLabel("Memory - set up your game");
        homeLabel.setFont(new Font("Tahoma",Font.BOLD,20));
        homeFrame.setResizable(false);

        homeFrame.getContentPane().setLayout(new BoxLayout(homeFrame.getContentPane(),BoxLayout.Y_AXIS));
        homeLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        homeFrame.getContentPane().add(homeLabel);

        JPanel diffLevPanel = new JPanel();
        JLabel diffLevLabel = new JLabel("Choose your difficulty level. Explanation can be found in the rules.");

        JPanel dimPanel = new JPanel();
        dimPanel.setLayout(new FlowLayout());
        JLabel dimLabel = new JLabel("Option E: Choose your customized board dimensions. Make sure the product rows*columns is even.");
        SpinnerModel rowModel = new SpinnerNumberModel(4,1,8,1);
        JSpinner rowSpinner = new JSpinner(rowModel);
        rowSpinner.setEnabled(false);
        JLabel rowLabel = new JLabel("Number of rows:");
        SpinnerModel columnModel = new SpinnerNumberModel(4,1,8,1);
        JSpinner columnSpinner = new JSpinner(columnModel);
        columnSpinner.setEnabled(false);
        JLabel columnLabel = new JLabel("Number of columns:");
        dimPanel.add(dimLabel);
        dimPanel.add(rowLabel);
        dimPanel.add(rowSpinner);
        dimPanel.add(columnLabel);
        dimPanel.add(columnSpinner);

        String[] diffLevels = {"A","B","C","D","E"};
        JRadioButton[] diffLevelButtons = new JRadioButton[diffLevels.length];
        JPanel diffLevButtonPanel = new JPanel();
        diffLevButtonPanel.setLayout(new GridLayout(1,5));
        ButtonGroup diffLevelGroup = new ButtonGroup();
        for (int i = 0; i < diffLevels.length; i++) {
            diffLevelButtons[i] = new JRadioButton(diffLevels[i]);
            diffLevelButtons[i].setActionCommand(diffLevels[i]);
            diffLevelGroup.add(diffLevelButtons[i]);
            diffLevButtonPanel.add(diffLevelButtons[i]);
            diffLevelButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String difficultyLevel = diffLevelGroup.getSelection().getActionCommand();
                    if (difficultyLevel.equals("E")) {
                        rowSpinner.setEnabled(true);
                        columnSpinner.setEnabled(true);
                    }
                }
            });
        }

        diffLevPanel.setLayout(new BorderLayout());
        diffLevPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        diffLevPanel.add(diffLevLabel, BorderLayout.NORTH);
        diffLevPanel.add(diffLevButtonPanel, BorderLayout.WEST);
        diffLevPanel.add(dimPanel, BorderLayout.SOUTH);

        JPanel playerPanel = new JPanel();
        JLabel playerLabel = new JLabel("Choose your player mode.");

        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.X_AXIS));
        JTextField player1Name = new JTextField();
        player1Name.setMinimumSize(new Dimension(30,20));
        player1Name.setMaximumSize(new Dimension(50,30));
        JLabel player1Label = new JLabel("Name player 1");
        JTextField player2Name = new JTextField();
        player2Name.setMinimumSize(new Dimension(30,20));
        player2Name.setMaximumSize(new Dimension(50,30));
        player2Name.setEnabled(false);
        JLabel player2Label = new JLabel("Name player 2");
        playerNamePanel.add(player1Label);
        playerNamePanel.add(player1Name);
        playerNamePanel.add(player2Label);
        playerNamePanel.add(player2Name);

        String[] playerModes = {"Multiplayer","Against the computer"};
        JPanel playerModeButtonPanel = new JPanel();
        playerModeButtonPanel.setLayout(new FlowLayout());
        JRadioButton[] playerModeButtons = new JRadioButton[playerModes.length];
        ButtonGroup playerModeGroup = new ButtonGroup();
        for (int i = 0; i < playerModes.length; i++) {
            playerModeButtons[i] = new JRadioButton(playerModes[i]);
            playerModeButtons[i].setActionCommand(playerModes[i]);
            playerModeGroup.add(playerModeButtons[i]);
            playerModeButtonPanel.add(playerModeButtons[i]);
            playerModeButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    final String chosenMode = playerModeGroup.getSelection().getActionCommand();
                    if (chosenMode.equals("Multiplayer")) {
                        player2Name.setEnabled(true);
                    }
                }
            });
        }

        playerPanel.setLayout(new BorderLayout());
        playerPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        playerPanel.add(playerLabel,BorderLayout.NORTH);
        playerPanel.add(playerModeButtonPanel,BorderLayout.WEST);
        playerPanel.add(playerNamePanel,BorderLayout.SOUTH);

        JButton startButton = new JButton("Start game");
        Game myGame = new Game();
        startButton.addActionListener(new StartButtonListener(myGame, rowSpinner, columnSpinner, diffLevelGroup, playerModeGroup, player1Name, player2Name));

        JPanel optionsPanelNew = new JPanel();
        optionsPanelNew.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        GUIStuffFactory guiStuffFactory = new GUIStuffFactory();
        optionsPanelNew.add(guiStuffFactory.makeOptionsPanel());
        optionsPanelNew.add(startButton);

        homeFrame.getContentPane().add(diffLevPanel);
        homeFrame.getContentPane().add(playerPanel);
        homeFrame.getContentPane().add(optionsPanelNew);
        homeFrame.pack();
        homeFrame.setVisible(true);
    }

}
