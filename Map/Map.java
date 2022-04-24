package Map;

import Chunk.Chunk;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Map {
    /**
     * List of the all chunks
     */
    protected static Chunk[] CHUNKS = Chunk.getChunks ();

    public static final int MIN_MAP_LENGTH = 5;
    public static final int MAX_MAP_LENGTH = 8;
    public static final int MIN_MAP_PADDING = 100;

    @Override
    public String toString () {
        return "Map{" +
                "map=" + Arrays.toString (map) +
                '}';
    }

    public static final int MAX_MAP_PADDING = 999;

    protected Chunk[] map;

    public Map () {
        this.map = this.generateFromSeed (Map.generateSeed ());
    }

    public Map (String seed) {
        this.map = this.generateFromSeed (seed);
    }

    /**
     * seed must be: "length_padding"
     * length must be between MIN_MAP_LENGTH and MAX_MAP_LENGTH
     * padding must be between MIN_MAP_PADDING and MAX_MAP_PADDING
     * @return a random seed
     */
    public static String generateSeed () {
        int length = new Random ().nextInt (MIN_MAP_LENGTH, MAX_MAP_LENGTH);
        int padding = new Random ().nextInt (MIN_MAP_PADDING, MAX_MAP_PADDING);
        return "%d_%d".formatted (length, padding);
    }

    /**
     * @param seed the seed to check
     * @return true if the seed is legal
     */
    public static boolean legalSeed (String seed) {
        try {
            boolean legal_length = Integer.parseInt (seed.split ("_")[0]) >= MIN_MAP_LENGTH
                    && Integer.parseInt (seed.split ("_")[0]) <= MAX_MAP_LENGTH;
            boolean legal_padding = Integer.parseInt (seed.split ("_")[1]) >= MIN_MAP_PADDING
                    && Integer.parseInt (seed.split ("_")[1]) <= MAX_MAP_PADDING;
            return legal_length && legal_padding;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param start the chunk to check
     * @return the list of the chunks that are connected to the start
     */
    protected static Chunk @NotNull [] getCompatibleChunks (Chunk start) {
        List<Chunk> compatibleChunks = new ArrayList<> ();

        for (Chunk chunk : Map.CHUNKS)
            if (start.isCompatible (chunk))
                compatibleChunks.add (chunk);

        return compatibleChunks.toArray (new Chunk[0]);
    }

    /**
     * @param seed number generated from
     * @return the map generated from the seed
     */
    protected Chunk[] generateFromSeed (String seed) {
        assert Map.legalSeed (seed): "Seed %s is not legal".formatted (seed);
        List<Chunk> map = new ArrayList<> ();
        Chunk[] compatibleChunks = Map.CHUNKS;

        // seed parameters
        int length = Integer.parseInt (seed.split ("_")[0]);
        int general_padding = Integer.parseInt (seed.split ("_")[1]);

        for (int i = 0; i < length; i++) {
            int padding = general_padding % compatibleChunks.length;
            map.add (compatibleChunks[padding]);
            compatibleChunks = Map.getCompatibleChunks (compatibleChunks[padding]);
        }

        return map.toArray (new Chunk[0]);
    }
}
