package Board;

import DifficultyLevel.DifficultyLevel;
import DifficultyLevel.CustomDifficultyLevel;

public class BoardDimensioner {

    /**This method initializes an interface-object with an object of the DifficultyLevel-enum and this way, gives it the dimensions and nbPairs that are
     * enclosed in this object. The characteristics are then 'extracted' from the interface and put in an array.
     * @param difficultyLevel , the DifficultyLevel-object that gives its characteristics to the output
     * @return boardCharacteristics, an int[]-array that can later on be easily used to get information from.
     */
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

    /**This method determines boardCharacteristics, depending on the difficultyLevel.
     * @param difficultyLevel , the main parameter to know which enum-item OR customDifficultyLevel should be called upon.
     * @param nbRows , the number of rows that is used when a customized board is asked for.
     * @param nbColumns , the number of columns that is used when a customized board is asked for.
     * @return int[] boardCharacteristics, an array that contains the height (nbRows), width (nbColumns) and the number of pairs for the playing board.
     */
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
                characteristics = new CustomDifficultyLevel(nbRows, nbColumns, nbRows * nbColumns / 2);
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
}
