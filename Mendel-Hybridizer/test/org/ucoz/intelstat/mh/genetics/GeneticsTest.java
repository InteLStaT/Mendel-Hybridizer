package org.ucoz.intelstat.mh.genetics;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class GeneticsTest extends TestCase {

	@Override
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParsePhenotype() {
		try {
			Phenotype pt = Genetics.parsePhenotype("ABc");
			assertEquals("ABc", pt.letterRepresentation());
		} catch (ParseException e) {
			fail("Threw when it shouldn't have");
		}
		try {
			Phenotype pt = Genetics.parsePhenotype("AbCdEfG");
			assertEquals("AbCdEfG", pt.letterRepresentation());
		} catch (ParseException e) {
			fail("Threw when it shouldn't have");
		}
		try {
			Phenotype pt = Genetics.parsePhenotype("AaBc");
			fail("Didn't throw when it should have");
		} catch (ParseException e) {	
		}
		try {
			Phenotype pt = Genetics.parsePhenotype("Aba");
			fail("Didn't throw when it should have");
		} catch (ParseException e) {	
		}
	}

	@Test
	public void testParseGenotype() {
		try {
			Genotype gt = Genetics.parseGenotype("AABbcc");
			assertEquals("AABbcc", gt.letterRepresentation());
		} catch (ParseException e) {
			fail("Threw when it shouldn't have on naturally ordered case");
		}
		try {
			Genotype gt = Genetics.parseGenotype("AAbBccdD");
			assertEquals("AABbccDd", gt.letterRepresentation());
		} catch (ParseException e) {
			fail("Threw when it shouldn't have on unnaturally orded case");
		}
		try {
			Genotype gt = Genetics.parseGenotype("AbCd");
			fail("Didn't throw when it should have on no pair");
		} catch (ParseException e) {
		}
		try {
			Genotype gt = Genetics.parseGenotype("AabBaa");
			fail("Didn't throw when it should have on duplicate");
		} catch (ParseException e) {
		}
		
	}

}
