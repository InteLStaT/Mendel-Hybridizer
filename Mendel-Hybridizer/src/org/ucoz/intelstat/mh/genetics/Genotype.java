package org.ucoz.intelstat.mh.genetics;

import java.util.Collections;
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
		if (pairarray == null) {
			throw new IllegalArgumentException("array can't be null");
		}
		if (pairarray.length == 0) {
			throw new IllegalArgumentException("array can't be empty");
		}
		pairs = new TreeSet<AllelePair>();
		Collections.addAll(pairs, pairarray);
	}

	public Genotype(Set<AllelePair> pairset) {
		if (pairset == null) {
			throw new IllegalArgumentException("set can't be null");
		}
		if (pairset.size() == 0) {
			throw new IllegalArgumentException("set can't be empty");
		}
		pairs = new TreeSet<AllelePair>(pairset);
	}

	public String letterRepresentation() {
		StringBuilder builder = new StringBuilder();
		for (AllelePair ap : pairs) {
			builder.append(ap.letterRepresentation());
		}
		return builder.toString();
	}

	@Override
	public String toString() {
		return letterRepresentation();
	}

	public void descriptor(AlleleDescriptor ad) {
		for (AllelePair ap : pairs) {
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

	public Phenotype phenotype() {
		Set<Allele> domAlleles = new TreeSet<>();
		for(AllelePair ap : pairs) {
			domAlleles.add(ap.expressedAllele());
		}
		return new Phenotype(domAlleles);
	}

}
