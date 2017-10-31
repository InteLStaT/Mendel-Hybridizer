package org.ucoz.intelstat.mh.genetics;

import org.junit.Test;

import junit.framework.TestCase;

public class AllelePairTest extends TestCase {

	@Test
	public void testAllelePair() {
		try {
			new AllelePair(new Allele('a'), new Allele('b'));
			new AllelePair(new Allele('a'), new Allele('B'));
			fail("Didn't throw when it should have on not allele of same gene");
		} catch (IllegalArgumentException e) {
		}

		try {
			new AllelePair(new Allele('a'), new Allele('a'));
			new AllelePair(new Allele('a'), new Allele('A'));
		} catch (IllegalArgumentException e) {
			fail("Threw when it shouldn't have");
		}

	}

	@Test
	public void testIsHomozygous() {
		assertTrue(new AllelePair(new Allele('a'), new Allele('a')).isHomozygous());
		assertFalse(new AllelePair(new Allele('a'), new Allele('A')).isHomozygous());
	}

	@Test
	public void testIsHeterozygous() {
		assertFalse(new AllelePair(new Allele('a'), new Allele('a')).isHeterozygous());
		assertTrue(new AllelePair(new Allele('a'), new Allele('A')).isHeterozygous());
	}

	@Test
	public void testExpressedAllele() {
		assertEquals(new Allele('A'), new AllelePair(new Allele('a'), new Allele('A')).expressedAllele());
		assertEquals(new Allele('a'), new AllelePair(new Allele('a'), new Allele('a')).expressedAllele());
	}

	@Test
	public void testAllele1() {

	}

	@Test
	public void testAllele2() {

	}

	@Test
	public void testDescriptor() {

	}

	@Test
	public void testDescription() {
		assertEquals("A", new AllelePair(new Allele('A'), new Allele('A')).description());
		assertEquals("A", new AllelePair(new Allele('A'), new Allele('a')).description());
		assertEquals("a", new AllelePair(new Allele('a'), new Allele('a')).description());
	}

	@Test
	public void testLetterRepresentation() {
		assertEquals("AA", new AllelePair(new Allele('A'), new Allele('A')).letterRepresentation());
		assertEquals("Aa", new AllelePair(new Allele('A'), new Allele('a')).letterRepresentation());
		assertEquals("aa", new AllelePair(new Allele('a'), new Allele('a')).letterRepresentation());
	}

	@Test
	public void testHeterozygoteAllele() {
		assertEquals(new AllelePair(new Allele('A'), new Allele('a')), AllelePair.heterozygote(new Allele('a')));
		assertEquals(new AllelePair(new Allele('a'), new Allele('A')), AllelePair.heterozygote(new Allele('A')));
	}

	@Test
	public void testHeterozygoteChar() {

	}

	@Test
	public void testHomozygoteAllele() {

	}

	@Test
	public void testHomozygoteChar() {

	}

	@Test
	public void testIsAllelePairOfSameGeneAs() {
		assertTrue(new AllelePair(new Allele('a'), new Allele('A'))
				.isAllelePairOfSameGeneAs(new AllelePair(new Allele('a'), new Allele('a'))));
		assertTrue(new AllelePair(new Allele('A'), new Allele('A'))
				.isAllelePairOfSameGeneAs(new AllelePair(new Allele('a'), new Allele('a'))));
		assertFalse(new AllelePair(new Allele('A'), new Allele('A'))
				.isAllelePairOfSameGeneAs(new AllelePair(new Allele('b'), new Allele('b'))));
	}

	@Test
	public void testEqualsObject() {
		assertFalse(new AllelePair(new Allele('a'), new Allele('A')).equals(null));
		assertTrue(new AllelePair(new Allele('a'), new Allele('A'))
				.equals(new AllelePair(new Allele('A'), new Allele('a'))));
		assertEquals(new AllelePair(new Allele('a'), new Allele('A'))
				.equals(new AllelePair(new Allele('A'), new Allele('A'))), false);
		assertEquals(new AllelePair(new Allele('a'), new Allele('A'))
				.equals(new AllelePair(new Allele('b'), new Allele('B'))), false);
	}

	@Test
	public void testEqualsAllelePair() {
	}

	@Test
	public void testCompareTo() {
		assertTrue(new AllelePair(new Allele('a'), new Allele('A')).compareTo(new AllelePair(new Allele('a'), new Allele('A'))) == 0);
		assertTrue(new AllelePair(new Allele('a'), new Allele('A')).compareTo(new AllelePair(new Allele('a'), new Allele('a'))) == 0);
		assertTrue(new AllelePair(new Allele('a'), new Allele('A')).compareTo(new AllelePair(new Allele('A'), new Allele('A'))) == 0);
		assertTrue(new AllelePair(new Allele('a'), new Allele('A')).compareTo(new AllelePair(new Allele('b'), new Allele('b'))) < 0);
		assertTrue(new AllelePair(new Allele('z'), new Allele('z')).compareTo(new AllelePair(new Allele('D'), new Allele('D'))) > 0);
	}

}
