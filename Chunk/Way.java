package Chunk;

import org.jetbrains.annotations.Contract;

public enum Way {
    LEFT, CENTER, RIGHT;

    /**
     * @param s the string to convert
     * @return the Way corresponding to the string
     */
    @Contract (pure = true)
    public static Way fromString (String s) {
        return switch (s) {
            case "left", "l" -> LEFT;
            case "center", "c" -> CENTER;
            case "right", "r" -> RIGHT;
            default -> throw new RuntimeException ("Unknown Way: " + s);
        };
    }
}
