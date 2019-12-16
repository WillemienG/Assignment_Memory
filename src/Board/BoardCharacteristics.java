package Board;

/** This interface is implemented by both DifficultyLevel-classes/enums to force them to have these 3 methods.
 */
public interface BoardCharacteristics {
    int getWidth();
    int getHeight();
    int getNbPairs();
}
