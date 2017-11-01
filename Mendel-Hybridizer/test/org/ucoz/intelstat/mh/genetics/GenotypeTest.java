package org.ucoz.intelstat.mh.genetics;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

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
		Genotype gt = new Genotype(pairs);
	}

	@Test
	public void testLetterRepresentation() {
		Set<AllelePair> pairs = new TreeSet<>(
				Arrays.asList(new AllelePair('A', 'A'), new AllelePair('B', 'b'), new AllelePair('c', 'c')));
		assertEquals("AABbcc", new Genotype(pairs).letterRepresentation());
	}

	@Test
	public void testPhenotype() {
		Set<AllelePair> pairs = new TreeSet<>(
				Arrays.asList(new AllelePair('A', 'A'), new AllelePair('Z', 'z'), new AllelePair('Z', 'Z')));
		Genotype gt = new Genotype(pairs);
		Phenotype pt = gt.phenotype();
		assertEquals("AZ", pt.letterRepresentation());
	}
	
	@Test
	public void testEquals() {
		Set<AllelePair> pairs = new TreeSet<>(
				Arrays.asList(new AllelePair('Z', 'z'), new AllelePair('A', 'A'), new AllelePair('Z', 'Z')));
		Genotype gt1 = new Genotype(pairs);
		Genotype gt2 = new Genotype(pairs);
		assertEquals(gt1, gt2);
	}
}
