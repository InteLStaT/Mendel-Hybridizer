package org.ucoz.intelstat.mh.genetics;

public final class AllelePair {

	private Allele allele1;
	private Allele allele2;

	public AllelePair(Allele a1, Allele a2) {
		if (a1 == null || a2 == null) {
			throw new IllegalArgumentException("null allele not allowed");
		}
		if (!a1.isAlleleOfSameGeneAs(a2)) {
			throw new IllegalArgumentException("not alleles of the same gene");
		}
		allele1 = a1;
		allele2 = a2;
	}

	public boolean isHomozygous() {
		return allele1.equals(allele2);
	}

	public boolean isHeterozygous() {
		return !isHomozygous();
	}

	/**
	 * Returns the expressed allele in the phenotype
	 * 
	 * @return
	 */
	public Allele expressedAllele() {
		if (allele1.isDominant()) {
			return allele1;
		} else {
			return allele2;
		}
	}

	public Allele allele1() {
		return allele1;
	}

	public Allele allele2() {
		return allele2;
	}

	/**
	 * Sets the allele descriptor of both alleles of this allele pair to the
	 * given one.
	 */
	public void descriptor(AlleleDescriptor ad) {
		allele1.descriptor(ad);
		allele2.descriptor(ad);
	}

	/**
	 * Returns a heterozygous allele pair with one of two alleles being the
	 * given one.
	 * 
	 * @param a
	 * @return
	 */
	public static AllelePair heterozygote(Allele a) {
		return new AllelePair(a, a.oppositeAllele());
	}

	public static AllelePair heterozygote(char a) {
		Allele allele = new Allele(a);
		return new AllelePair(allele, allele.oppositeAllele());
	}

	/**
	 * Returns a homozygous allele pair with both of the alleles being the given
	 * one.
	 * 
	 * @param a
	 * @return
	 */
	public static AllelePair homozygote(Allele a) {
		return new AllelePair(a, a);
	}

	public static AllelePair homozygote(char a) {
		Allele allele = new Allele(a);
		return new AllelePair(allele, allele);
	}

	/**
	 * Returns true if this allele pair contains the alleles of the same gene as
	 * in the other allele pair.
	 * 
	 * @return
	 */
	public boolean isAllelePairOfSameGeneAs(AllelePair other) {
		return this.allele1.isAlleleOfSameGeneAs(other.allele1);
	}
}
