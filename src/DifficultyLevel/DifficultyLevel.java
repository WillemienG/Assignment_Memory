package DifficultyLevel;

import Board.WidthHeightDetermination;

public enum DifficultyLevel implements WidthHeightDetermination {
    FIRSTLEVEL(2, 3), SECONDLEVEL(4,3), THIRDLEVEL(5,6), FOURTHLEVEL(8,8);

    private int width;
    private int height;

    //Constructor that forces every Enum-level to have a width and height
    DifficultyLevel(int height, int width) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
