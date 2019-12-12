package Board;

import DifficultyLevel.DifficultyLevel;
import DifficultyLevel.CustomDifficultyLevel;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BoardDimensioner {

    /**
     * This method determines the dimensions of game board, chosen by player(s).
     * Players.Player can choose between different difficulty levels or customize the board in method askBoardDimensions(). In that case, the given numbers must fulfill certain conditions, such as be strictly bigger than zero and give an even product.
     * If conditions are not fulfilled, method starts over again.
     * @return a 1by2 int-array with the width and height of the gameboard
     */
    public String askDifficultyLevel() {
        Scanner scan = new Scanner(System.in);
        boolean isValidLevel = false;
        String chosenLevel = "Z";
        int i = 1;
        while (!isValidLevel) {
            try {
                System.out.println("At which difficulty level would you like to play? A: First level, B: Second level, C: Third level, D: Fourth level, E: Customized");
                String scannedLevel = scan.next();
                if (scannedLevel.equals("A") || scannedLevel.equals("B") || scannedLevel.equals("C") || scannedLevel.equals("D") || scannedLevel.equals("E")) {
                    chosenLevel = scannedLevel;
                    isValidLevel = true;
                } else {
                    System.out.println("Enter one of the possibilities, going from A to E.");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Enter one of the possibilities, going from A to E.");
                isValidLevel = false;
            }
            i = i + 1;
        }
        return chosenLevel;
    }

    private int[] returnCharacteristics(DifficultyLevel difficultyLevel) {
        BoardCharacteristics characteristics = difficultyLevel;
        int[] boardCharacteristics = new int[3];
        int width = characteristics.getWidth();
        int height = characteristics.getHeight();
        int nbPairs = characteristics.getNbPairs();
        boardCharacteristics[0] = height;
        boardCharacteristics[1] = width;
        boardCharacteristics[2] = nbPairs;
        return boardCharacteristics;
    }

    public int[] determineCharacteristics(String difficultyLevel, int nbRows, int nbColumns) {
        BoardCharacteristics characteristics;
        int[] boardCharacteristics = new int[3];
        switch (difficultyLevel) {
            case "A":
                boardCharacteristics = returnCharacteristics(DifficultyLevel.FIRSTLEVEL);
                return boardCharacteristics;
            case "B":
                boardCharacteristics = returnCharacteristics(DifficultyLevel.SECONDLEVEL);
                return boardCharacteristics;
            case "C":
                boardCharacteristics = returnCharacteristics(DifficultyLevel.THIRDLEVEL);
                return boardCharacteristics;
            case "D":
                boardCharacteristics = returnCharacteristics(DifficultyLevel.FOURTHLEVEL);
                return boardCharacteristics;
            case "E":
                //Only when player chooses a customized board, the askBoardDimensions() gets called. These created values then are used to set width and height
                characteristics = new CustomDifficultyLevel(nbRows,nbColumns,nbRows*nbColumns/2);
                int width = characteristics.getWidth();
                int height = characteristics.getHeight();
                int nbPairs = characteristics.getNbPairs();
                boardCharacteristics[0] = height;
                boardCharacteristics[1] = width;
                boardCharacteristics[2] = nbPairs;
                return boardCharacteristics;
            default:
                boardCharacteristics[0] = 2;
                boardCharacteristics[1] = 2;
                boardCharacteristics[2] = 2;
                return boardCharacteristics;
        }
    }

    private int[] askBoardDimensions() {
        Scanner scan = new Scanner(System.in);
        boolean isValidNumber = false;
        boolean isEvenProduct = false;
        int i = 1;
        int[] boardDimensions;
        boardDimensions = new int[2];
        while (!isValidNumber || !isEvenProduct) {
            try {
                System.out.println("Enter number of rows");
                int height = scan.nextInt();
                System.out.println("Enter number of columns");
                int width = scan.nextInt();
                if (width % 2 != 0 && height % 2 != 0) {
                    System.out.println("At least one of the numbers must be even in order to make a valid play board. Please try again.");
                    isEvenProduct = false;
                } else if (width <= 0 || height <= 0) {
                    System.out.println("Make sure both values are strictly bigger than zero.");
                    isValidNumber = false;
                } else {
                    isEvenProduct = true;
                    isValidNumber = true;
                    boardDimensions[0] = height;
                    boardDimensions[1] = width;
                }
            } catch (InputMismatchException ime) {
                scan.next();
                System.out.println("You must enter a non-zero positive integer. Please try again.");
            }
            i = i + 1;
        }
        return boardDimensions;
    }

}
