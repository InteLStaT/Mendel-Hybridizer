package org.ucoz.intelstat.mh.genetics;

import java.text.ParseException;
import java.util.HashMap;

public class GeneticsTest2 {

	public static void main(String[] args) {
		try {
			new Phenotype(new Allele[] {new Allele('a')});
			new Genotype(new AllelePair[] {new AllelePair('a', 'A')});
			long start = System.nanoTime();
			Phenotype pt = Genetics.parsePhenotype("AbC");
			long time = System.nanoTime() - start;
			System.out.println(time/1000000.0);
			System.out.println(pt);
			
			start = System.nanoTime();
			Genotype gt = Genetics.parseGenotype("AAccbB");
			time = System.nanoTime() - start;
			System.out.println(time/1000000.0);	
			System.out.println(gt);
			
			System.out.println("\n\n");
			AllelePair pair1 = new AllelePair('a', 'A');
			AllelePair pair2 = new AllelePair('A', 'a');
			Genotype gt1 = new Genotype(new AllelePair[] {pair1});
			Genotype gt2 = new Genotype(new AllelePair[] {pair2});
			HashMap<Genotype, String> map = new HashMap<>();
			map.put(gt1, "asd");
			map.put(gt2, "lol");
			System.out.println(map);
			System.out.println(gt1.equals(gt2));
			System.out.println(gt1.hashCode() + ", " + gt2.hashCode());
		} catch (ParseException e) {
		}
	}
	
}
