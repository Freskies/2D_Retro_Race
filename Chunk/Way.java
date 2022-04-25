package Chunk;

import org.jetbrains.annotations.Contract;

/**
 * A way is the side the street is on in a chunk.
 * @author Giacchini Valerio
 * @version 1.1
 * @since 22/04/2022
 * @see Chunk
 */
public enum Way {
    /**
     * The street is on the left side of the chunk.
     * @see Chunk
     */
    LEFT,
    /**
     * The street is in the center of the chunk.
     * @see Chunk
     */
    CENTER,
    /**
     * The street is on the right side of the chunk.
     * @see Chunk
     */
    RIGHT;

    /**
     * @param way the string to convert
     * @return the Way corresponding to the string
     */
    @Contract (pure = true)
    public static Way fromString (String way) {
        return switch (way) {
            case "left", "l" -> LEFT;
            case "center", "c" -> CENTER;
            case "right", "r" -> RIGHT;
            default -> throw new RuntimeException ("Unknown Way: " + way);
        };
    }
}
