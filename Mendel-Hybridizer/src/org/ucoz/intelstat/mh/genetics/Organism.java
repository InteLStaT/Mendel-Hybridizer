package org.ucoz.intelstat.mh.genetics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Organism {

	private Genotype gt;
	private Phenotype pt;
	
	public Organism(Genotype gt) {
		if(gt == null) {
			throw new IllegalArgumentException("genotype can't be null");
		}
		this.gt = gt;
		this.pt = gt.phenotype();
	}
	
	public Genotype genotype() {
		return gt;
	}
	
	public Phenotype phenotype() {
		return pt;
	}
	
	public String description() {
		return description(", ");
	}
	
	public String description(String separator) {
		return pt.description(separator);
	}
	
	public Set<Gamete> possibleGametes() {
		Set<Set<Allele>> ptPrototype = new HashSet<>();
		Iterator<AllelePair> gtiter = gt.pairs().iterator();
		AllelePair curAp = gtiter.next();
		Set<Allele> s1 = new HashSet<>();
		Set<Allele> s2 = new HashSet<>();
		Set<Set<Allele>> curunion = new HashSet<>();
		s1.add(curAp.allele1());
		s2.add(curAp.allele2());
		ptPrototype.add(s1);
		ptPrototype.add(s2);
		while(gtiter.hasNext()) {
			curAp = gtiter.next();
			s1 = new HashSet<>();
			s2 = new HashSet<>();
			s1.add(curAp.allele1());
			s2.add(curAp.allele2());
			curunion.clear();
			curunion.add(s1);
			curunion.add(s2);
			ptPrototype = multiply(curunion, ptPrototype); 
		}
		Set<Phenotype> pts = new TreeSet<>();
		for(Set<Allele> alleleSet : ptPrototype) {
			pts.add(new Phenotype(alleleSet));
		}
		Set<Gamete> gametes = new TreeSet<>();
		for(Phenotype pt : pts) {
			gametes.add(new Gamete(pt));
		}
		return gametes;
	}
	
	private Set<Set<Allele>> multiply(Set<Set<Allele>> set1, Set<Set<Allele>> set2) {
		Set<Set<Allele>> res = new HashSet<>();
		for(Set<Allele> s1 : set1) {
			for(Set<Allele> s2 : set2) {
				Set<Allele> subres = new HashSet<>(s1);
				subres.addAll(s2);
				res.add(subres);
			}
		}
		return res;
	}
}
