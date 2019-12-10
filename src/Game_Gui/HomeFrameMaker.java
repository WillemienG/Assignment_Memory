package Game_Gui;

import Board.Board;
import Board.BoardDimensioner;

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

        homeFrame.getContentPane().setLayout(new BoxLayout(homeFrame.getContentPane(),BoxLayout.Y_AXIS));
        homeLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        homeFrame.getContentPane().add(homeLabel);

        JPanel optionsPanelNew = new JPanel();
        optionsPanelNew.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        optionsPanelNew.add(makeOptionsPanel());
        optionsPanelNew.add(makeStartButton());

        homeFrame.getContentPane().add(makeDiffLevPanel());
        homeFrame.getContentPane().add(makePlayerPanel());
        homeFrame.getContentPane().add(optionsPanelNew);
        homeFrame.pack();
        homeFrame.setVisible(true);
    }

    protected JPanel makeDiffLevPanel() {
        JPanel diffLevPanel = new JPanel();
        JLabel diffLevLabel = new JLabel("Choose your difficulty level. Explanation can be found in the rules.");

        JPanel dimPanel = new JPanel();
        dimPanel.setLayout(new FlowLayout());
        JLabel dimLabel = new JLabel("Option E: Choose your customized board dimensions. Make sure the product rows*columns is even.");
        SpinnerModel dimModel = new SpinnerNumberModel(4,1,8,1);
        JSpinner rowSpinner = new JSpinner(dimModel);
        rowSpinner.setEnabled(false);
        JLabel rowLabel = new JLabel("Number of rows:");
        JSpinner columnSpinner = new JSpinner(dimModel);
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
                    //try {
                      //  rowSpinner.commitEdit();
                        //columnSpinner.commitEdit();
                    //} catch (java.text.ParseException pe) { }
                    //BoardDimensioner boardDimensioner = new BoardDimensioner();
                    //boardDimensioner.determineCharacteristics(diffLevelGroup.getSelection().getActionCommand(),(Integer) rowSpinner.getValue(),(Integer) columnSpinner.getValue());
                }
            });
        }

        diffLevPanel.setLayout(new BorderLayout());
        diffLevPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        diffLevPanel.add(diffLevLabel, BorderLayout.NORTH);
        diffLevPanel.add(diffLevButtonPanel, BorderLayout.WEST);
        diffLevPanel.add(dimPanel, BorderLayout.SOUTH);
        return diffLevPanel;
    }

    protected JPanel makePlayerPanel() {
        JPanel playerPanel = new JPanel();
        JLabel playerLabel = new JLabel("Choose your player mode.");

        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.X_AXIS));
        JTextField player1Name = new JTextField();
        player1Name.setMinimumSize(new Dimension(30,15));
        player1Name.setMaximumSize(new Dimension(50,15));
        JLabel player1Label = new JLabel("Name player 1");
        JTextField player2Name = new JTextField();
        player2Name.setMinimumSize(new Dimension(30,15));
        player2Name.setMaximumSize(new Dimension(50,15));
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
                    String chosenMode = playerModeGroup.getSelection().getActionCommand();
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

        return playerPanel;
    }

    protected JPanel makeOptionsPanel() {
        JPanel optionsPanel = new JPanel();

        JButton showRules = new JButton("Rules");
        showRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RulesFrameMaker rulesFrameMaker = new RulesFrameMaker();
                rulesFrameMaker.readRules();
            }
        });

        JButton showHighscores = new JButton("Highscores");
        showHighscores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HighscoreFrameMaker highscoreFrameMaker = new HighscoreFrameMaker();
                highscoreFrameMaker.makeHighscoreFrame();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame quitFrame = new JFrame("Quit game");
                quitFrame.getContentPane().setLayout(new BoxLayout(quitFrame.getContentPane(),BoxLayout.Y_AXIS));
                quitFrame.getContentPane().add(new JLabel("Are you sure you want to quit?"));
                JButton quitButtonYes = new JButton("Yes, quit game");
                quitButtonYes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                JButton quitButtonNo = new JButton("No, go back.");
                quitButtonNo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        quitFrame.setVisible(false);
                    }
                });
                quitFrame.getContentPane().add(quitButtonYes);
                quitFrame.getContentPane().add(quitButtonNo);
                quitFrame.pack();
                quitFrame.setVisible(true);
            }
        });

        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        optionsPanel.add(showRules);
        optionsPanel.add(showHighscores);
        optionsPanel.add(quitButton);
        return optionsPanel;
    }

    public JButton makeStartButton() {
        JButton startButton = new JButton("Start game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrameMaker gameFrameMaker = new GameFrameMaker();
                gameFrameMaker.makeGameFrame(5,4);
            }
        });
        return startButton;
    }
    public static void main(String[] args) {
        HomeFrameMaker homeFrameMaker = new HomeFrameMaker();
        homeFrameMaker.makeHomeFrame();
    }
}
