package org.ucoz.intelstat.mh.genetics;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.math3.fraction.Fraction;

public class RandomCombinationTest {

	static List<Genotype> gts = new ArrayList<>(4);

	static void add(String gt, int count) throws ParseException {
		gts.addAll(Collections.nCopies(count, Genetics.parseGenotype(gt)));
	}

	public static void main(String[] args) {

		try {
			load2();
		} catch (ParseException e) {
		}
		//System.out.println("== TEST 1 ========================");
		//test1(gts);
		System.out.println("== TEST 2 ========================");
		test2(gts);
	}

	static void load1() throws ParseException {
		add("AABB", 1);
		add("aabb", 1);
		add("AAbb", 1);
		add("aaBB", 1);
		add("AABb", 2);
		add("AaBB", 2);
		add("Aabb", 2);
		add("aaBb", 2);
		add("AaBb", 4);
	}

	static void load2() throws ParseException {
		add("aaBb", 1);
		add("Aabb", 1);
		add("aabb", 1);
		add("AaBb", 1);
	}

	static void test1(List<Genotype> gts) {
		Map<Genotype, Integer> res = new HashMap<>();

		for (int i = 0; i < 10000000; i++) {
			Genotype gt1 = gts.get(ThreadLocalRandom.current().nextInt(4));
			Genotype gt2 = gts.get(ThreadLocalRandom.current().nextInt(4));
			Map<Genotype, Integer> subres = hybridizeMap(gt1, gt2);
			subres.forEach((gt, inc) -> res.put(gt, res.getOrDefault(gt, 0) + inc));
		}
		int total = res.values().stream().reduce(0, (subtotal, elem) -> subtotal + elem);
		for (Entry<Genotype, Integer> e : res.entrySet()) {
			System.out.println(e.getKey() + " " + new Fraction((double) e.getValue() / total, 512) + " = "
					+ ((double) e.getValue() / total));
		}

	}

	static void test2(List<Genotype> gts) {

		Map<Genotype, Integer> res = new HashMap<>();
		Map<Genotype, Integer> res2 = new HashMap<>();

		for (int i = 0; i < 10000000; i++) {
			Genotype gt1 = gts.get(ThreadLocalRandom.current().nextInt(4));
			Genotype gt2 = gts.get(ThreadLocalRandom.current().nextInt(4));
			List<Genotype> subres = hybridizeList(gt1, gt2);
			Genotype gt = subres.get(ThreadLocalRandom.current().nextInt(subres.size()));
			res.put(gt, res.getOrDefault(gt, 0) + 1);
			subres.forEach((GT) -> res2.put(GT, res2.getOrDefault(GT, 0) + 1));
		}
		int total = res.values().stream().reduce(0, (subtotal, elem) -> subtotal + elem);
		for (Entry<Genotype, Integer> e : res.entrySet()) {
			System.out.println(e.getKey() + " " + new Fraction((double) e.getValue() / total, 81) + " = "
					+ ((double) e.getValue() / total));
		}
		System.out.println("---------------------------------");
		int total2 = res2.values().stream().reduce(0, (subtotal, elem) -> subtotal + elem);
		for (Entry<Genotype, Integer> e : res2.entrySet()) {
			System.out.println(e.getKey() + " " + new Fraction((double) e.getValue() / total2, 81) + " = "
					+ ((double) e.getValue() / total2));
		}
	}

	protected static Map<Genotype, Integer> hybridizeMap(Genotype gt1, Genotype gt2) {
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

	static List<Genotype> hybridizeList(Genotype gt1, Genotype gt2) {
		Set<Gamete> gam1 = new Organism(gt1).possibleGametes();
		Set<Gamete> gam2 = new Organism(gt2).possibleGametes();
		Fraction inc = new Fraction(1, gam1.size() * gam2.size());
		Organism o;
		List<Genotype> res = new ArrayList<>();
		for (Gamete g1 : gam1) {
			for (Gamete g2 : gam2) {
				o = g1.fuse(g2);
				res.add(o.genotype());
			}
		}
		return res;
	}

	public static class GtPtComparator implements Comparator<Genotype> {

		@Override
		public int compare(Genotype o1, Genotype o2) {
			return o1.phenotype().compareTo(o2.phenotype());
		}

	}

}
