package Chunk;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Chunk(Way start, Way end, Image layout, Image sprite) {

    /**
     * @param chunk the chunk to check
     * @return true if this chunk can be connected to the given chunk
     */
    @Contract (pure = true)
    public boolean isCompatible (Chunk chunk) {
        return (this.end == chunk.start) && !this.equals (chunk);
    }

    /**
     * @return the list of chunks
     */
    public static Chunk @NotNull [] getChunks () {
        String[] names = getNameFromFolder ();
        Image[] layouts = getImagesFromFolder ("src/Chunk/ChunkLayouts");
        Image[] sprites = getImagesFromFolder ("src/Chunk/ChunkShowed");
        List<Chunk> chunks = new ArrayList<> ();

        assert names.length == layouts.length : "The number of chunks is not the same as the number of layouts";
        assert names.length == sprites.length : "The number of chunks is not the same as the number of sprites";
        for (int i = 0; i < names.length; i++) {
            chunks.add (new Chunk (
                    Way.fromString (names[i].substring (names[i].indexOf ("_") + 1, names[i].length () - 1)),
                    Way.fromString (names[i].substring (names[i].indexOf ("_") + 2)),
                    layouts[i],
                    sprites[i]
            ));
        }

        return chunks.toArray (new Chunk[0]);
    }

    /**
     * @return the list of chunks name in the given folder
     */
    private static String @NotNull [] getNameFromFolder () {
        File folder = new File("src/Chunk/ChunkLayouts");
        File[] listOfFiles = folder.listFiles();
        List<String> fileNames = new ArrayList<> ();

        assert listOfFiles != null;
        for (File file : listOfFiles)
            if (file.isFile())
                fileNames.add (file.getName ().substring (0, file.getName ().length () - 4));

        return fileNames.toArray (new String[0]);
    }

    /**
     * @param path the path of the folder containing the chunks
     * @return the list of chunks in the given folder
     */
    private static Image @NotNull [] getImagesFromFolder (String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        List<Image> images = new ArrayList<> ();

        assert listOfFiles != null;
        for (File file : listOfFiles)
            if (file.isFile())
                images.add (Toolkit.getDefaultToolkit ().getImage (file.getPath ()));

        return images.toArray (new Image[0]);
    }

    @Override
    public String toString () {
        return "Chunk{" +
                "start=" + start +
                ", end=" + end +
                ", layout=" + layout +
                ", sprite=" + sprite +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Chunk chunk)) return false;
        return start == chunk.start && end == chunk.end;
    }

    @Override
    public int hashCode () {
        return Objects.hash (start, end);
    }
}
