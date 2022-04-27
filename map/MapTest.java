package map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Map.
 * @author Marrelli Marco
 * @version 0.1.0
 * @see Map
 * @since 25/04/2022
 */
public class MapTest {

	/**
	 * Testes generateSeed(), legalSeed(), getConnectableChunks(), generateFromSeed() methods.
	 * @author Giacchini Valerio
	 */
	public static void test () {
		MapTest test = new MapTest ();
		test.generateSeed ();
		test.legalSeed ();
		test.getConnectableChunks ();
		test.generateFromSeed ();
	}

	/**
	 * Testes generateSeed() method.
	 * @author Marrelli Marco
	 */
	@Test
	void generateSeed () {
		String failed = "Test generateSeed: Failed!\n%s";

		String seed = Map.generateSeed ();

		assertNotNull (seed, failed.formatted ("Seed (%s)is null ".formatted (seed)));
		assertFalse (seed.isBlank (), failed.formatted ("Seed (%s) is blank".formatted (seed)));
		assertFalse (seed.isEmpty (), failed.formatted ("Seed (%s) is empty".formatted (seed)));
		assertEquals (seed.length (), Map.SEED_LENGTH,
				failed.formatted ("Seed (%s) length is not equal as SEED_LENGTH (%d)"
						.formatted (seed, Map.SEED_LENGTH)));
		assertTrue (Map.legalSeed (seed), failed.formatted ("Seed (%s) is not legal".formatted (seed)));

		System.out.println ("Test generateSeed: Success!");
	}

	/**
	 * Testes legalSeed() method.
	 * @author Marrelli Marco
	 */
	@Test
	void legalSeed () {
		String failed = "Test legalSeed: Failed!\n%s";

		String emptySeed = "";
		String blankSeed = " ".repeat (Map.SEED_LENGTH);
		String alfaSeed = "t".repeat (Map.SEED_LENGTH);
		String lengthShorterThanSeed = "5".repeat (Map.SEED_LENGTH - 1);
		String lengthLongerThanSeed = "5".repeat (Map.SEED_LENGTH + 1);
		String legalSeed = Map.generateSeed ();

		assertFalse (Map.legalSeed (null),
				failed.formatted ("Null Seed (%s) marked as legal").formatted (legalSeed));
		assertTrue (Map.legalSeed (legalSeed),
				failed.formatted ("Legal Seed (%s) marked as illegal").formatted (legalSeed));
		assertFalse (Map.legalSeed (emptySeed),
				failed.formatted ("Empty Seed (%s) marked as legal").formatted (legalSeed));
		assertFalse (Map.legalSeed (blankSeed),
				failed.formatted ("Blank Seed (%s) marked as legal").formatted (legalSeed));
		assertFalse (Map.legalSeed (alfaSeed),
				failed.formatted ("Alfa Seed (%s) marked as legal").formatted (legalSeed));
		assertFalse (Map.legalSeed (lengthShorterThanSeed),
				failed.formatted ("Seed with a shorter length than permitted (%s) marked as legal")
						.formatted (legalSeed));
		assertFalse (Map.legalSeed (lengthLongerThanSeed),
				failed.formatted ("Seed with a longer length than permitted (%s) marked as legal")
						.formatted (legalSeed));

		System.out.println ("Test legalSeed: Success!");
	}

	/**
	 * Testes getConnectableChunks() method.
	 * @author Marrelli Marco
	 */
	@Test
	void getConnectableChunks () {
		String failed = "Test getConnectableChunks: Failed!\n%s";

		Chunk chunk = new Chunk (1, Way.LEFT, Way.LEFT, null, null);

		assertNotNull (Map.getConnectableChunks (chunk),
				failed.formatted ("Chunk has no connectable chunks (null, default)"));
		assertFalse (Map.getConnectableChunks (chunk).isEmpty (),
				failed.formatted ("Chunk has no connectable chunks (empty, default)"));

		Map.CHUNKS = new Chunk[] {
				new Chunk (2, Way.LEFT, Way.RIGHT, null, null),
				new Chunk (3, Way.RIGHT, Way.RIGHT, null, null),
				new Chunk (4, Way.RIGHT, Way.LEFT, null, null),
		};

		assertNotNull (Map.getConnectableChunks (chunk),
				failed.formatted ("Chunk has no connectable chunks (null, forced)"));
		assertFalse (Map.getConnectableChunks (chunk).isEmpty (),
				failed.formatted ("Chunk has no connectable chunks (empty, forced)"));

		System.out.println ("Test getConnectableChunks: Success!");
	}

	/**
	 * Testes generateFromSeed() method.
	 * @author Marrelli Marco
	 */
	@Test
	void generateFromSeed () {
		String failed = "Test generateFromSeed: Failed!\n%s";

		String seed1 = "3".repeat (Map.SEED_LENGTH);
		String seed2 = "7".repeat (Map.SEED_LENGTH);

		Map map1 = new Map (seed1);
		Map map2 = new Map (seed1);
		Map map3 = new Map (seed2);

		assertEquals (map1, map2, failed.formatted ("Seed %s generates two different maps".formatted (seed1)));
		assertNotEquals (map1, map3,
				failed.formatted ("Seed %s and Seed %s generate two equal maps".formatted (seed1, seed2)));

		System.out.println ("Test generateFromSeed: Success!");
	}
}