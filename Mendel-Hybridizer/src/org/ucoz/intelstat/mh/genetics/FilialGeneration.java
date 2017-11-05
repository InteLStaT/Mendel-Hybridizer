package org.ucoz.intelstat.mh.genetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.math3.fraction.Fraction;

public class FilialGeneration implements Generation {

	protected Generation prev;

	protected FilialGeneration(Generation previous) {
		prev = previous;
	}

	@Override
	public Generation nextGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Phenotype, Fraction> phenotypicRatios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Genotype, Fraction> genotypicRatios() {
		Map<Genotype, Fraction> prevgtr = prev.genotypicRatios();
		ArrayList<Genotype> gts = new ArrayList<>(prevgtr.keySet());
		Map<Genotype, Fraction> ratios = new HashMap<>();
		Map<Genotype, Fraction> subres;
		Fraction subratio;
		for (int i = 0; i < gts.size(); i++) {
			for (int j = i; j < gts.size(); j++) {
				subres = hybridize(gts.get(i), gts.get(j));
				subratio = prevgtr.get(gts.get(i)).multiply(prevgtr.get(gts.get(j)));
				for (Entry<Genotype, Fraction> e : subres.entrySet()) {
					ratios.put(e.getKey(),
							ratios.getOrDefault(e.getKey(), Fraction.ZERO).add(e.getValue().multiply(subratio)));
				}
			}
		}
		return ratios;
	}

	protected static Map<Genotype, Fraction> hybridize(Genotype gt1, Genotype gt2) {
		Set<Gamete> gam1 = new Organism(gt1).possibleGametes();
		Set<Gamete> gam2 = new Organism(gt2).possibleGametes();
		Fraction inc = new Fraction(1, gam1.size() * gam2.size());
		Organism o;
		Map<Genotype, Fraction> res = new HashMap<>();
		for (Gamete g1 : gam1) {
			for (Gamete g2 : gam2) {
				o = g1.fuse(g2);
				res.put(o.genotype(), res.getOrDefault(o.genotype(), Fraction.ZERO).add(inc));
			}
		}
		return res;
	}

}
