package Chunk;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A chunk is a part of the map.
 *
 * @param id     The id of the chunk
 * @param start  The start position of the road in the chunk
 * @param end    The end position of the road in the chunk
 * @param layout The layout of the chunk (road, grass, water, etc)
 * @param sprite The sprite of the chunk
 * @author Giacchini Valerio
 * @version 2.2
 * @see Way
 * @see Map
 * @since 22/04/2022
 */
public record Chunk(int id, Way start, Way end, Image layout, Image sprite) {

    /**
     * @return the list of chunks name in the given folder
     * @author Giacchini Valerio
     * @see Chunk#getChunks()
     */
    private static String @NotNull [] getNameFromFolder () {
        File folder = new File ("src/Chunk/ChunkLayouts");
        File[] listOfFiles = folder.listFiles ();
        List<String> fileNames = new ArrayList<> ();

        assert listOfFiles != null;
        for (File file : listOfFiles)
            if (file.isFile ())
                fileNames.add (file.getName ().substring (0, file.getName ().length () - 4));

        return fileNames.toArray (new String[0]);
    }

    /**
     * @param path the path of the folder containing the chunks
     * @return the list of chunks in the given folder
     * @author Giacchini Valerio
     * @see Chunk#getChunks()
     */
    private static Image @NotNull [] getImagesFromFolder (String path) {
        File folder = new File (path);
        File[] listOfFiles = folder.listFiles ();
        List<Image> images = new ArrayList<> ();

        assert listOfFiles != null;
        for (File file : listOfFiles)
            if (file.isFile ())
                images.add (Toolkit.getDefaultToolkit ().getImage (file.getPath ()));

        return images.toArray (new Image[0]);
    }

    /**
     * @return the list of chunks created (from the folder)
     * @author Giacchini Valerio
     */
    public static Chunk @NotNull [] getChunks () {
        String[] names = getNameFromFolder ();
        Image[] layouts = getImagesFromFolder ("src/Chunk/ChunkLayouts");
        Image[] sprites = getImagesFromFolder ("src/Chunk/ChunkShowed");
        List<Chunk> chunks = new ArrayList<> ();

        assert names.length == layouts.length : "The number of chunks is not the same as the number of layouts";
        assert names.length == sprites.length : "The number of chunks is not the same as the number of sprites";

        // example of a chunk name "<id>_<start><end>" --> 1_lr --> id = 1, start = left, end = right
        for (int i = 0; i < names.length; i++)
            chunks.add (new Chunk (
                    Integer.parseInt (names[i].substring (0, names[i].indexOf ("_"))),
                    Way.fromString (names[i].substring (names[i].indexOf ("_") + 1, names[i].length () - 1)),
                    Way.fromString (names[i].substring (names[i].indexOf ("_") + 2)),
                    layouts[i],
                    sprites[i]
            ));

        return chunks.toArray (new Chunk[0]);
    }

    /**
     * @param chunk the chunk to check
     * @return true if this chunk can be connected to the given chunk
     * @author Giacchini Valerio
     */
    @Contract (pure = true)
    public boolean isConnectable (Chunk chunk) {
        return (this.end == chunk.start) && !this.equals (chunk);
    }

    @Override
    public String toString () {
        return "Chunk{" +
                "id=" + id +
                ", start=" + this.start +
                ", end=" + this.end +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Chunk chunk)) return false;
        return this.id == chunk.id && this.start == chunk.start && this.end == chunk.end;
    }
}
