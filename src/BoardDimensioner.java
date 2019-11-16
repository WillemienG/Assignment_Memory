import java.util.InputMismatchException;
import java.util.Scanner;

public class BoardDimensioner {

    public static int[] determineWidthHeight() {
        Scanner scan = new Scanner(System.in);
        boolean isValidLevel = false;
        char chosenLevel = 'Z';
        int i = 1;
        while (!isValidLevel) {
            try {
                System.out.println("At which difficulty level would you like to play? A: First level, B: Second level, C: Third level, D: Fourth level, E: Customized");
                char scannedLevel = scan.next().charAt(0);
                if (scannedLevel == 'A' || scannedLevel == 'B' || scannedLevel == 'C' || scannedLevel == 'D' || scannedLevel == 'E'){
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
        WidthHeightDetermination widthAndHeight;
        int width;
        int height;
        int[] boardDimensions = new int[2];
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

    public static int[] askBoardDimensions() {
        Scanner scan = new Scanner(System.in);
        boolean isValidNumber = false;
        boolean isEvenProduct = false;
        int i = 1;
        //Board dimensions have to be put in array, method can't return multiple values
        int[] boardDimensions;
        boardDimensions = new int[2];
        //As long as one of the conditions is not fulfilled, one of these booleans is set to false, so while loop is set to true and keeps going.
        while (!isValidNumber || !isEvenProduct) {
            try {
                System.out.println("Enter number of rows");
                int height = scan.nextInt();
                System.out.println("Enter number of columns");
                int width = scan.nextInt();
                if (width % 2 != 0 && height % 2 != 0) {
                    //Gives error when product width*height is odd (can't make tile pairs that way) and starts again.
                    System.out.println("At least one of the numbers must be even in order to make a valid play board. Please try again.");
                    isEvenProduct = false;
                } else if (width <= 0 || height <= 0) {
                    //Gives error when given value(s) <= 0 and starts again.
                    System.out.println("Make sure both values are strictly bigger than zero.");
                    isValidNumber = false;
                } else {
                    isEvenProduct = true;
                    isValidNumber = true;
                    //Sets boardDimensions to given values if all conditions are fulfilled.
                    boardDimensions[0] = height;
                    boardDimensions[1] = width;
                }
            } catch (InputMismatchException ime) {
                scan.next();
                //If scan.next() is not included in this catch-block, the scanner leaves invalid argument in scanner input stream without moving on, this creates an infinite loop.
                System.out.println("You must enter a non-zero positive integer. Please try again.");
            }
            i = i + 1;
        }
        return boardDimensions;
    }

}
