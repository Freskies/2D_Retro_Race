package Chunk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Chunk class.
 *
 * @author Marrelli Marco
 * @version 1.0
 * @see Chunk
 * @since 25/04/2022
 */
public class ChunkTest {

    /**
     * Testes getChunk(), isConnectable() methods.
     *
     * @author Giacchini Valerio
     */
    public static void test () {
        ChunkTest test = new ChunkTest ();
        test.getChunks ();
        test.isConnectable ();
    }

    /**
     * Testes getChunks() method.
     *
     * @author Marrelli Marco
     */
    @Test
    void getChunks () {
        String failed = "Test getChunks: Failed!\n%s";

        assertNotNull (Chunk.getChunks (), failed.formatted ("Chunks list is null!"));

        System.out.println ("Test getChunks: Success!");
    }

    /**
     * Testes isConnectable() method.
     *
     * @author Marrelli Marco
     */
    @Test
    void isConnectable () {
        Chunk c1lr = new Chunk (1, Way.LEFT, Way.RIGHT, null, null);
        Chunk c2rr = new Chunk (2, Way.RIGHT, Way.RIGHT, null, null);

        assertTrue (c1lr.isConnectable (c2rr), "Test isConnectable: Failed!\nChunk is not connectable!");
        assertFalse (c2rr.isConnectable (c1lr), "Test isConnectable: Failed!\nChunk is connectable!");
        assertFalse (c1lr.isConnectable (c1lr), "Test isConnectable: Failed!\nChunk is connectable!");
        System.out.println ("Test isConnectable: Success!");
    }
}