package org.ucoz.intelstat.mh.genetics;

import java.util.Map;

import org.apache.commons.math3.fraction.Fraction;

public abstract interface Generation {

	public abstract Generation nextGeneration();
	
	public abstract Map<Phenotype, Fraction> phenotypicRatios();

	public abstract Map<Genotype, Fraction> genotypicRatios();
}