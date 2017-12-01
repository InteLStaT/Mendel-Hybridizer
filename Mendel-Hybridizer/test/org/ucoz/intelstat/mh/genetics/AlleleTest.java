package org.ucoz.intelstat.mh.genetics;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class AlleleTest extends TestCase {

	Allele recessiveAllele;
	Allele dominantAllele;
	Allele otherDominant;
	Allele otherRecessive;
	AlleleDescriptor desc;
	Allele domDesc;
	Allele recDesc;

	@Override
	@Before
	public void setUp() {
		recessiveAllele = new Allele('a');
		dominantAllele = new Allele('A');
		otherRecessive = new Allele('z');
		otherDominant = new Allele('Z');
		desc = new AlleleDescriptor.Builder().set('b', "rec").set('B', "dom").build();
		domDesc = new Allele('B', desc);
		recDesc = new Allele('b', desc);
	}

	@Test
	public void testAlleleChar() {
		try {
			new Allele('a');
			new Allele('z');

		} catch (IllegalArgumentException e) {
			fail("Shouldn't have thrown.");
		}
		try {
			new Allele('A');
			new Allele('Z');
		} catch (IllegalArgumentException e) {
			fail("Shouldn't have thrown (dominant).");
		}
		try {
			new Allele(',');
			fail("Didn't throw when it should have not letter");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testAlleleCharAlleleDescriptor() {
		try {
			new Allele('a', null);
			fail("Didn't throw when it should have");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testOppositeAllele() {
		assertEquals(dominantAllele, recessiveAllele.oppositeAllele());
		assertEquals(recessiveAllele, dominantAllele.oppositeAllele());
	}

	@Test
	public void testIsAlleleOfSameGeneAs() {
		assertTrue(recessiveAllele.isAlleleOfSameGeneAs(dominantAllele));
		assertTrue(dominantAllele.isAlleleOfSameGeneAs(recessiveAllele));
		assertFalse(recessiveAllele.isAlleleOfSameGeneAs(otherRecessive));
		assertFalse(recessiveAllele.isAlleleOfSameGeneAs(otherDominant));

	}

	@Test
	public void testIsDominant() {
		assertTrue(dominantAllele.isDominant());
		assertFalse(recessiveAllele.isDominant());
	}

	@Test
	public void testIsRecessive() {
		assertFalse(dominantAllele.isRecessive());
		assertTrue(recessiveAllele.isRecessive());
	}

	@Test
	public void testDescription() {
		assertEquals("a", recessiveAllele.description());
		assertEquals("A", dominantAllele.description());
		assertEquals("dom", domDesc.description());
		assertEquals("rec", recDesc.description());

	}

	@Test
	public void testDescriptor() {

	}

	@Test
	public void testDescriptorAlleleDescriptor() {

	}

	@Test
	public void testLetterRepresentation() {

	}

	@Test
	public void testToString() {

	}

	@Test
	public void testEqualsObject() {
		assertEquals(dominantAllele, dominantAllele);
		assertEquals(recessiveAllele, recessiveAllele);
		assertEquals(dominantAllele, new Allele('A'));
		assertEquals(recessiveAllele, new Allele('a'));
	}

	@Test
	public void testEqualsAllele() {
		assertEquals(dominantAllele, dominantAllele);
		assertEquals(recessiveAllele, recessiveAllele);
		assertEquals(dominantAllele, new Allele('A'));
		assertEquals(recessiveAllele, new Allele('a'));
	}

	@Test
	public void testCompareTo() {
		assertTrue(recessiveAllele.compareTo(dominantAllele) > 0);
		assertTrue(dominantAllele.compareTo(recessiveAllele) < 0);
	}

}
