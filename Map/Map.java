package Map;

import Chunk.Chunk;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Map class is used to store all the chunks in the game.
 * You can iterate over the map to get all the chunks.
 * A Map can be generated from personalized seed.
 * Same seed will generate the same map.
 * @author Giacchini Valerio
 * @version 2.6
 * @since 22/04/2022
 * @see Chunk
 */
public class Map implements Iterable<Chunk> {
    /**
     * List of the all chunks
     */
    protected static Chunk[] CHUNKS = Chunk.getChunks ();

    /**
     * the number of digits of the seed
     */
    public static final int SEED_LENGTH = 5;


    /**
     * Minimal number of chunks in the map
     */
    public static final int MIN_MAP_LENGTH = 5;
    /**
     * Maximal number of chunks in the map
     */
    public static final int MAX_MAP_LENGTH = 8;

    /**
     * the list of the chunks that compose the map
     */
    protected Chunk[] map;

    /**
     * the seed that generate this map
     */
    protected String seed;

    public Map () {
        this.generateFromSeed (Map.generateSeed ());
    }

    public Map (String seed) {
        this.generateFromSeed (seed);
    }

    /**
     * generate a string with SEED_LENGTH digits
     * @return the seed of the map
     * @see Map#SEED_LENGTH
     */
    public static @NotNull String generateSeed () {
        StringBuilder sb = new StringBuilder ();
        for (int i = 0; i < Map.SEED_LENGTH; i++)
            sb.append (new Random ().nextInt (10));
        return sb.toString ();
    }

    /**
     * @param seed the seed to check
     * @return true if the seed is legal (SEED_LENGTH digits)
     * @see Map#SEED_LENGTH
     */
    public static boolean legalSeed (String seed) {
        return seed.matches ("\\d*") && seed.length () == Map.SEED_LENGTH;
    }

    /**
     * @param start the chunk to check
     * @return the list of the chunks that can be connected to the start
     * @see Chunk#isConnectable(Chunk)
     */
    protected static List<Chunk> getConnectableChunks (Chunk start) {
        List<Chunk> connectableChunks = new ArrayList<> ();

        for (Chunk chunk : Map.CHUNKS)
            if (start.isConnectable (chunk))
                connectableChunks.add (chunk);

        return connectableChunks;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder ();

        sb.append ("Map.").append (this.seed).append ("{\n");
        for (Chunk chunk : this)
            sb.append ("  ").append (chunk).append ("\n");

        return sb.append ("}").toString ();
    }

    /**
     * generate the map from the seed
     * it changes the map field and the seed field
     * @param seed the seed to generate the map
     */
    protected void generateFromSeed (String seed) {
        assert Map.legalSeed (seed): "Seed %s is illegal".formatted (seed);
        this.seed = seed;

        // Random based on the map seed (deterministic with the same seed)
        Random random = new Random (seed.hashCode ());

        List<Chunk> map = new ArrayList<> ();

        // the first time all chucks are compatible because the map is empty
        List<Chunk> connectableChunks = Arrays.asList (Map.CHUNKS);

        int mapLength = random.nextInt (Map.MIN_MAP_LENGTH, Map.MAX_MAP_LENGTH);

        for (int i = 0; i < mapLength; i++) {
            Collections.shuffle (connectableChunks, random);
            map.add (connectableChunks.get (0));
            connectableChunks = Map.getConnectableChunks (connectableChunks.get (0));
        }

        this.map = map.toArray (new Chunk[0]);
    }

    /**
     * @return every chunk in the map
     * @see Chunk
     */
    @NotNull
    @Override
    public Iterator<Chunk> iterator () {
        return new Iterator<> () {
            private final Iterator<Chunk> iterator = Arrays.asList (map).iterator ();

            @Override
            public boolean hasNext () {
                return this.iterator.hasNext ();
            }

            @Override
            public Chunk next () {
                return this.iterator.next ();
            }
        };
    }
}
