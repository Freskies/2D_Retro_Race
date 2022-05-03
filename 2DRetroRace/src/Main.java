import map.Chunk;
import map.Map;

public class Main {

	public static void main (String[] args) {
		Map map = new Map (Chunk.getChunks ());
		System.out.println (map);
	}
}
