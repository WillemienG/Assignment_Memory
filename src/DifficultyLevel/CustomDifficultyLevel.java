package DifficultyLevel;

import Board.BoardCharacteristics;

//This class allows users to define their own board size, independent of the fixed sizes for fixed difficulty levels
public class CustomDifficultyLevel implements BoardCharacteristics {

    private final int width;
    private final int height;
    private final int nbPairs;

    public CustomDifficultyLevel(int height, int width, int nbPairs) {
        this.width = width;
        this.height = height;
        this.nbPairs = nbPairs;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getNbPairs() {
        return nbPairs;
    }
}
