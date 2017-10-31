package org.ucoz.intelstat.mh.genetics;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * To run the test case, change the access modifier of pairs to anything
 * non-private.
 * 
 * @author InteLStaT
 *
 */
public class GenotypeTest extends TestCase {

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testGenotypeAllelePairArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenotypeSetOfAllelePair() {
		Set<AllelePair> pairs = new TreeSet<>();
		pairs.add(new AllelePair('a', 'a'));
		pairs.add(new AllelePair('b', 'B'));
		pairs.add(new AllelePair('B', 'B'));
		pairs.add(new AllelePair('a', 'a'));
		pairs.stream().forEach(System.out::println);
		System.out.println();
		Genotype gt = new Genotype(pairs);
		gt.pairs.stream().forEach(System.out::println);
	}

	@Test
	public void testLetterRepresentation() {
		Set<AllelePair> pairs = new TreeSet<>(
				Arrays.asList(new AllelePair('A', 'A'), new AllelePair('B', 'b'), new AllelePair('c', 'c')));
		assertEquals("AABbcc", new Genotype(pairs).letterRepresentation());
	}
}
