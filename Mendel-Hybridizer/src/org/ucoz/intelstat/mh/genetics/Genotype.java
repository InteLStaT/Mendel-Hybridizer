package org.ucoz.intelstat.mh.genetics;

import java.util.Set;
import java.util.TreeSet;

public class Genotype {

	private TreeSet<AllelePair> pairs;
	
	public Genotype(AllelePair[] pairarray) {
		pairs = new TreeSet();
	}
	
	public Genotype(Set<AllelePair> pairset) {
	}
	
	
}
