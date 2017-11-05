package org.ucoz.intelstat.mh.genetics;

import java.text.ParseException;
import java.util.Map;

public class InheritanceTest {

	public static void main(String[] args) {
		
		try {
			Genotype gt1 = Genetics.parseGenotype("Aa");
			Genotype gt2 = Genetics.parseGenotype("Aa");
			
			System.out.println("--- Genotypes");
			System.out.println(gt1);
			System.out.println(gt2);

			Organism o1 = new Organism(gt1);
			Organism o2 = new Organism(gt2);
			
			System.out.println("--- Possible gametes");
			System.out.println(o1.possibleGametes());
			System.out.println(o2.possibleGametes());
			
			ParentalGeneration pg = new ParentalGeneration(o1, o2);
			
			Map pptr = pg.phenotypicRatios();
			Map pgtr = pg.genotypicRatios();

			System.out.println("--- Parental phenotypic and genotypic ratios");
			System.out.println(pptr);
			System.out.println(pgtr);
			
			FilialGeneration fg1 = pg.nextGeneration();
			
			//Map fptr1 = fg1.phenotypicRatios();
			long start = System.nanoTime();
			Map fgtr1 = fg1.genotypicRatios();
			long time = System.nanoTime() - start;
			
			System.out.println("--- Filial genotypic ratios");
			//System.out.println(fptr1);
			System.out.println(time/1000000.0 + " ms");
			System.out.println(fgtr1);
			
		} catch (ParseException e) {
		}
	}
	
}
