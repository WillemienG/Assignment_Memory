package DifficultyLevel;

import Board.WidthHeightDetermination;

//This class allows users to define their own board size, independent of the fixed sizes for fixed difficulty levels
public class CustomDifficultyLevel implements WidthHeightDetermination {

    private final int width;
    private final int height;

    public CustomDifficultyLevel(int height, int width) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
