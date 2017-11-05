package org.ucoz.intelstat.mh.genetics;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Gamete implements Comparable<Gamete> {

	private Phenotype pt;
	
	public Gamete(Phenotype pt) {
		if(pt == null) {
			throw new IllegalArgumentException("phenotype can't be null");
		}
		this.pt = pt;
	}
	
	public Organism fuse(Gamete other) {
		if(other == null) {
			throw new IllegalArgumentException("other gamete can't be null");
		}
		if(!this.pt.containsAllelesOfSameGeneAs(other.pt)) {
			throw new IllegalArgumentException("phenotypes don't represent the same set of traits");
		}
		Set<AllelePair> pairs = new TreeSet<>();
		Iterator<Allele> it1 = this.pt.alleles().iterator();
		Iterator<Allele> it2 = other.pt.alleles().iterator();
		while(it1.hasNext()) {
			pairs.add(new AllelePair(it1.next(), it2.next()));
		}
		return new Organism(new Genotype(pairs));
	}

	public Phenotype phenotype() {
		return pt;
	}
	
	public boolean equals(Gamete other) {
		if(other == null) {
			return false;
		}
		return this.pt.equals(other.pt);
	}
	
	@Override
	public boolean equals(Object other) {
		Gamete g = (Gamete) other;
		return this.equals(g);
	}

	@Override
	public int compareTo(Gamete other) {
		return this.pt.compareTo(other.pt);
	}
	
}
