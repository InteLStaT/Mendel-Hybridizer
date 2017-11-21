package org.ucoz.intelstat.mh.genetics;

import java.text.ParseException;
import java.util.Set;
import java.util.TreeSet;

public class Genetics {

	private Genetics() {
	}

	public static Phenotype parsePhenotype(String src) throws ParseException {
		Set<Allele> alleles = new TreeSet<>();
		char c;
		for (int i = 0; i < src.length(); i++) {
			c = src.charAt(i);
			try {
				Allele a = new Allele(c);
				if(alleles.contains(a) || alleles.contains(a.oppositeAllele())) {
					throw new ParseException("same allele present twice", i);
				}
				alleles.add(a);
			} catch (IllegalArgumentException e) {
				throw new ParseException("encounted non-letter character", i);
			}
		}
		return new Phenotype(alleles);
	}
	
	public static Genotype parseGenotype(String src) throws ParseException {
		if(src.length() % 2 != 0) {
			throw new IllegalArgumentException("length of source must be even");
		}
		Set<AllelePair> pairs = new TreeSet<>();
		char c1, c2;
		Allele a1, a2;
		AllelePair pair;
		for (int i = 0; i < src.length()/2; i++) {
			c1 = src.charAt(2*i);
			c2 = src.charAt(2*i+1);
			try {
				a1 = new Allele(c1);
			} catch (IllegalArgumentException e) {
				throw new ParseException("encountered non-letter character", i);
			}
			try {
				a2 = new Allele(c2);
			} catch (IllegalArgumentException e) {
				throw new ParseException("encountered non-letter character", i+1);
			}
			try {
				pair = new AllelePair(a1, a2);
			} catch (IllegalArgumentException e) {
				throw new ParseException("letter pair not alleles", i);
			}
			if(pairs.contains(pair)) {
				throw new ParseException("same allele pair present twice", i);
			}
			pairs.add(pair);
		}
		return new Genotype(pairs);
	}
}
