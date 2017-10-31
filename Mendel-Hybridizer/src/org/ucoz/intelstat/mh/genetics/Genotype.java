package org.ucoz.intelstat.mh.genetics;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Genotype {

	private TreeSet<AllelePair> pairs;

	/**
	 * Constructs a genotype with the given allele pairs. Duplicates are
	 * removed. {@code AllelePair}s a1 and a2 are considered equal if
	 * {@code a1.compareTo(a2) == 0}. The array is processed sequentially.
	 * 
	 * @param pairarray
	 */
	public Genotype(AllelePair[] pairarray) {
		pairs = new TreeSet<AllelePair>();
		Collections.addAll(pairs, pairarray);
	}

	public Genotype(Set<AllelePair> pairset) {
		pairs = new TreeSet<AllelePair>(pairset);
	}

	public String letterRepresentation() {
		StringBuilder builder = new StringBuilder();
		Iterator<AllelePair> it = pairs.iterator();
		while (it.hasNext()) {
			builder.append(it.next().letterRepresentation());
		}
		return builder.toString();
	}

	public void descriptor(AlleleDescriptor ad) {
		for(AllelePair ap : pairs) {
			ap.descriptor(ad);
		}
	}
	
	/**
	 * Returns an immutable set of the allele pairs in this genotype.
	 * 
	 * @return
	 */
	public Set<AllelePair> pairs() {
		return Collections.unmodifiableSet(pairs);
	}

	// public Phenotype phenotype() {
	//
	// }

}
