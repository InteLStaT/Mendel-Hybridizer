package org.ucoz.intelstat.mh.genetics;

import java.util.Map;

import org.apache.commons.math3.fraction.Fraction;

public interface Generation {

	Generation nextGeneration();
	
	Map<Phenotype, Fraction> phenotypicRatios();

	Map<Genotype, Fraction> genotypicRatios();
	
	String abbreviation();
}