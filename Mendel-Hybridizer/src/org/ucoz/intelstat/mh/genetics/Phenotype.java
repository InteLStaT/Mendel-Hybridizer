package org.ucoz.intelstat.mh.genetics;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Phenotype implements Comparable<Phenotype> {

	private Set<Allele> alleles;

	public Phenotype(Set<Allele> alleleset) {
		if (alleleset == null) {
			throw new IllegalArgumentException("set can't be null");
		}
		if (alleleset.size() == 0) {
			throw new IllegalArgumentException("set can't be empty");
		}
		alleles = new TreeSet<>(alleleset);
	}

	public Phenotype(Allele[] allelearray) {
		if (allelearray == null) {
			throw new IllegalArgumentException("array can't be null");
		}
		if (allelearray.length == 0) {
			throw new IllegalArgumentException("array can't be empty");
		}
		alleles = new TreeSet<>();
		Collections.addAll(alleles, allelearray);
	}

	public String letterRepresentation() {
		StringBuilder builder = new StringBuilder();
		for (Allele a : alleles) {
			builder.append(a.letterRepresentation());
		}
		return builder.toString();
	}

	public String description() {
		return description(", ");
	}

	public String description(String separator) {
		StringBuilder builder = new StringBuilder();
		Iterator<Allele> it = alleles.iterator();
		builder.append(it.next().description());
		while (it.hasNext()) {
			builder.append(separator).append(it.next().description());
		}
		return builder.toString();
	}

	public void descriptor(AlleleDescriptor ad) {
		for (Allele a : alleles) {
			a.descriptor(ad);
		}
	}

	public boolean containsAllelesOfSameGeneAs(Phenotype other) {
		if(other == null) {
			throw new IllegalArgumentException("other phenotype can't be null");
		}
		if(this.alleleCount() != other.alleleCount()) {
			return false;
		}
		Iterator<Allele> it1 = this.alleles.iterator();
		Iterator<Allele> it2 = other.alleles.iterator();
		while(it1.hasNext()) {
			if(!it1.next().isAlleleOfSameGeneAs(it2.next())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return letterRepresentation();
	}
	
	public Set<Allele> alleles(){
		return Collections.unmodifiableSet(alleles);
	}

	public int alleleCount() {
		return alleles.size();
	}
	
	public boolean equals(Phenotype other) {
		if(other == null) {
			return false;
		}
		if(this.alleleCount() != other.alleleCount()) {
			return false;
		}
		Iterator<Allele> it1 = this.alleles.iterator();
		Iterator<Allele> it2 = other.alleles.iterator();
		while(it1.hasNext()) {
			if(!it1.next().equals(it2.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean equals(Object other) {
		Phenotype pt = (Phenotype) other;
		return this.equals(pt);
	}

	@Override
	public int hashCode() {
		return letterRepresentation().hashCode();
	}
	
	@Override
	public int compareTo(Phenotype other) {
		Iterator<Allele> it1 = this.alleles.iterator();
		Iterator<Allele> it2 = other.alleles.iterator();
		while(it1.hasNext() && it2.hasNext()) {
			int comparison = it1.next().compareTo(it2.next());
			if(comparison == 0) {
				continue;
			} else {
				return comparison;
			}
		}
		return 0;
	}
}