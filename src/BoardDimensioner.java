import java.util.InputMismatchException;
import java.util.Scanner;

public class BoardDimensioner {

    /**
     * This method determines the dimensions of game board, chosen by player(s).
     * Player can choose between different difficulty levels or customize the board in method askBoardDimensions(). In that case, the given numbers must fulfill certain conditions, such as be strictly bigger than zero and give an even product.
     * If conditions are not fulfilled, method starts over again.
     * @return a 1by2 int-array with the width and height of the gameboard
     */
    public char askDifficultyLevel() {
        Scanner scan = new Scanner(System.in);
        boolean isValidLevel = false;
        char chosenLevel = 'Z';
        int i = 1;
        while (!isValidLevel) {
            try {
                System.out.println("At which difficulty level would you like to play? A: First level, B: Second level, C: Third level, D: Fourth level, E: Customized");
                char scannedLevel = scan.next().charAt(0);
                if (scannedLevel == 'A' || scannedLevel == 'B' || scannedLevel == 'C' || scannedLevel == 'D' || scannedLevel == 'E') {
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

    public int[] determineWidthHeight() {
        WidthHeightDetermination widthAndHeight;
        int width;
        int height;
        int[] boardDimensions = new int[2];
        char chosenLevel = askDifficultyLevel();
        switch (chosenLevel) {
            case 'A':
                widthAndHeight = DifficultyLevel.FIRSTLEVEL;
                width = widthAndHeight.getWidth();
                height = widthAndHeight.getHeight();
                boardDimensions[0] = height;
                boardDimensions[1] = width;
                return boardDimensions;
            case 'B':
                widthAndHeight = DifficultyLevel.SECONDLEVEL;
                width = widthAndHeight.getWidth();
                height = widthAndHeight.getHeight();
                boardDimensions[0] = height;
                boardDimensions[1] = width;
                return boardDimensions;
            case 'C':
                widthAndHeight = DifficultyLevel.THIRDLEVEL;
                width = widthAndHeight.getWidth();
                height = widthAndHeight.getHeight();
                boardDimensions[0] = height;
                boardDimensions[1] = width;
                return boardDimensions;
            case 'D':
                widthAndHeight = DifficultyLevel.FOURTHLEVEL;
                width = widthAndHeight.getWidth();
                height = widthAndHeight.getHeight();
                boardDimensions[0] = height;
                boardDimensions[1] = width;
                return boardDimensions;
            case 'E':
                //Only when player chooses a customized board, the askBoardDimensions() gets called. These created values then are used to set width and height
                int[] customBoardDimensions = askBoardDimensions();
                widthAndHeight = new CustomDifficultyLevel(customBoardDimensions[0], customBoardDimensions[1]);
                width = widthAndHeight.getWidth();
                height = widthAndHeight.getHeight();
                boardDimensions[0] = height;
                boardDimensions[1] = width;
                return boardDimensions;
            default:
                width = 2;
                height = 2;
                boardDimensions[0] = height;
                boardDimensions[1] = width;
                return boardDimensions;
        }
    }

    public int[] askBoardDimensions() {
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
