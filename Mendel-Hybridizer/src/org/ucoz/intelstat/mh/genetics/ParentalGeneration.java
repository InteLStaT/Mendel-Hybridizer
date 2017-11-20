package org.ucoz.intelstat.mh.genetics;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.fraction.Fraction;

public class ParentalGeneration implements Generation {

	Organism parent1, parent2;
	
	public ParentalGeneration(Organism p1, Organism p2) {
		if(p1 == null || p2 == null) {
			throw new IllegalArgumentException("parents can't be null");
		}
		if(!p1.phenotype().containsAllelesOfSameGeneAs(p2.phenotype())) {
			throw new IllegalArgumentException("parents don't have compatible genotypes");
		}
		parent1 = p1;
		parent2 = p2;
	}

	@Override
	public Map<Phenotype, Fraction> phenotypicRatios() {
		Map<Phenotype, Fraction> ratios = new HashMap<>();
		if(parent1.phenotype().equals(parent2.phenotype())) {
			ratios.put(parent1.phenotype(), new Fraction(1, 1));
		} else {
			ratios.put(parent1.phenotype(), new Fraction(1, 2));
			ratios.put(parent2.phenotype(), new Fraction(1, 2));
		}
		return ratios;
	}

	@Override
	public Map<Genotype, Fraction> genotypicRatios() {
		Map<Genotype, Fraction> ratios = new HashMap<>();
		if(parent1.genotype().equals(parent2.genotype())) {
			ratios.put(parent1.genotype(), new Fraction(1, 1));
		} else {
			ratios.put(parent1.genotype(), new Fraction(1, 2));
			ratios.put(parent2.genotype(), new Fraction(1, 2));
		}
		return ratios;
	}
	
	@Override
	public FilialGeneration nextGeneration() {
		return new FilialGeneration(this) {
			{
				ordinal = 1;
			}
			@Override
			public Map<Genotype, Fraction> genotypicRatios() {
				Map<Genotype, Fraction> ratios = hybridize(parent1.genotype(), parent2.genotype());
				return ratios;
			}
		};
	}

	@Override
	public String abbreviation() {
		return "P";
	}
	
	@Override
	public String toString() {
		return abbreviation() + "(" + parent1.genotype() + ", " + parent2.genotype() + ")";
	}

}
