package DifficultyLevel;

import Board.BoardCharacteristics;

public enum DifficultyLevel implements BoardCharacteristics {
    FIRSTLEVEL(2,3,3), SECONDLEVEL(4,3,5), THIRDLEVEL(5,6,13), FOURTHLEVEL(8,8,29);

    private int width;
    private int height;
    private int nbPairs;


    //Constructor that forces every Enum-level to have a width and height
    DifficultyLevel(int height, int width, int nbPairs) {
        this.width = width;
        this.height = height;
        this.nbPairs = nbPairs;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNbPairs() {
        return nbPairs;
    }
}

