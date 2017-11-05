package org.ucoz.intelstat.mh.genetics;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class OrganismTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPossibleGametes() {
		long start = System.nanoTime();
		AllelePair p1 = new AllelePair('A', 'a');
		AllelePair p2 = new AllelePair('b', 'B');
		AllelePair p3 = new AllelePair('C', 'c');
		AllelePair p4 = new AllelePair('Z', 'Z');
		Set<AllelePair> pairs = new HashSet<>();
		pairs.add(p1);
		pairs.add(p2);
		pairs.add(p3);
		pairs.add(p4);
		Genotype gt = new Genotype(pairs);
		Organism o = new Organism(gt);
		Set<Gamete> gs = o.possibleGametes();
		for(Gamete g : gs) {
			System.out.println(g.phenotype());
		}
		long time = System.nanoTime() - start;
		System.out.println(time/1000000.0 + " ms");
	}

}
