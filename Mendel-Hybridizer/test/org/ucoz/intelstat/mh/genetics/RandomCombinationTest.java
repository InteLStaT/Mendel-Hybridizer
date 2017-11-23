package org.ucoz.intelstat.mh.genetics;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.math3.fraction.Fraction;

public class RandomCombinationTest {

	public static void main(String[] args) {
		List<Genotype> gts = new ArrayList<>(4);
		Map<Genotype, Integer> res = new HashMap<>();
		try {
			gts.add(Genetics.parseGenotype("AABB"));
			gts.add(Genetics.parseGenotype("AaBb"));
			gts.add(Genetics.parseGenotype("AaBb"));
			gts.add(Genetics.parseGenotype("aabb"));
			for(int i = 0; i < 1000000; i++) {
				Genotype gt1 = gts.get(ThreadLocalRandom.current().nextInt(4));
				Genotype gt2 = gts.get(ThreadLocalRandom.current().nextInt(4));
				Map<Genotype, Integer> subres = hybridize(gt1, gt2);
				subres.forEach((gt, inc) -> res.put(gt, res.getOrDefault(gt, 0) + inc));
			}
			int total = res.values().stream().reduce(0, (subtotal, elem) -> subtotal + elem);
			for(Entry<Genotype, Integer> e : res.entrySet()) {
				System.out.println(e.getKey() + " " + new Fraction((double) e.getValue()/total, 64));
			}
		} catch (ParseException e) {
			//org.apache.commons.math3.genetics.
		}
	}

	protected static Map<Genotype, Integer> hybridize(Genotype gt1, Genotype gt2) {
		Set<Gamete> gam1 = new Organism(gt1).possibleGametes();
		Set<Gamete> gam2 = new Organism(gt2).possibleGametes();
		Fraction inc = new Fraction(1, gam1.size() * gam2.size());
		Organism o;
		Map<Genotype, Integer> res = new HashMap<>();
		for (Gamete g1 : gam1) {
			for (Gamete g2 : gam2) {
				o = g1.fuse(g2);
				res.put(o.genotype(), res.getOrDefault(o.genotype(), 0) + 1);
			}
		}
		return res;
	}

}
